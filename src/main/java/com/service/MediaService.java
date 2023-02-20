package com.service;

import cn.dev33.satoken.stp.StpUtil;
import com.constant.AceEnvironment;
import com.models.common.TranscodeConfig;
import com.models.entity.Roles;
import com.models.entity.Users;
import com.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname: MediaService
 * @Date: 2022/10/11 下午 04:40
 * @Author: kalam_au
 * @Description:
 */

@Service
public class MediaService {
    private static final Logger log = LogManager.getLogger(MediaService.class.getName());

    private final String videoM3u8;
    private final String videoPath;
    private final FilesService filesService;
    private final RolesService rolesService;
    private final Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));

    @Autowired
    public MediaService(FilesService filesService, RolesService rolesService) {
        this.filesService = filesService;
        this.rolesService = rolesService;
        this.videoM3u8 = AceEnvironment.getVideoM3u8();
        this.videoPath = AceEnvironment.getVideoPath();
    }

    public List getThumbnail() throws IOException {
        List<String> videoList = FileUtil.getFileNames(videoPath);
        List<String> names = FileUtil.getNames(videoList);
        return getActualList(names);
    }

    private List getActualList(List<String> t1) throws IOException {
        //根据folder实际文件控制数据库, 删除不存文件数据
        List<String> fName = FileUtil.getNames(t1);
        List<com.models.entity.Files> filesList = filesService.findFilesByPathAndFileNameNotIn(videoPath, fName);
        filesService.deleteAll(filesList);
        return getVideoPathList();
    }

    /** 根据playId切片
     * @param playId
     * @return
     * @throws IOException
     */
    public List getM3U8ByPlayId(String playId) throws IOException {
        List<String> videoList = FileUtil.getFileNames(videoPath);
        List<String> names = FileUtil.getNames(videoList);
        com.models.entity.Files f = filesService.findFilesByFileName(playId);
        getMultipartFileList(f.getLocation());
        return getActualList(names);
    }


    /** 文件夹里没有切片的video都切
     * @return
     * @throws IOException
     */
    public List getM3U8() throws IOException {
        List<String> videoList = FileUtil.getFileNames(videoPath);
        List<String> t1 = FileUtil.getNames(videoList);
        List<String> t2 = FileUtil.getDirs(videoM3u8);

        Map mp = ListUtil.getDeduplicateElements(t1, t2);
        videoList = (List<String>) mp.get(ListUtil.LIST_1);

        if (NullUtil.isNotNull(videoList)) {
            for (String s : videoList) {
                com.models.entity.Files f = filesService.findFilesByFileName(s);
                getMultipartFileList(f.getLocation());
            }
        }
        List<String> videoM3u8List = (List<String>) mp.get(ListUtil.LIST_2);
        if (NullUtil.isNotNull(videoM3u8List)) {
            for (String folderName : videoM3u8List) {
                boolean isOk = FileUtil.deleteDirectories(videoM3u8 + folderName);
                log.info("{} => delete m3u8 folder: {}", isOk, folderName);
            }
        }
        return getActualList(t1);
    }

    /** VideoPath 文件夹内video名字
     * @return
     * @throws IOException
     */
    private List getVideoPathList() throws IOException {
        List list = FileUtil.getFileNames(videoPath);
        List filesList = FileUtil.getNames(list);

        Users users = (Users) StpUtil.getSession().get("user");
        List<Roles> rolesList = rolesService.getRolesByUserId(users.getUserId());
        String fragment = com.models.entity.Files.FRAGMENT;
        String uploaded = com.models.entity.Files.UPLOADED;
        List<String> status = new ArrayList<>();
        status.add(fragment);
        status.add(uploaded);
        if (Roles.ADMIN.equals(rolesList.get(0).getRoleCode())) {
            //根据数据库排序
            return filesService.findFilesByFileNameInAndStatusInOrderByCreatedDateDesc(filesList, status);

        } else {
            //根据数据库排序
            return filesService.findFilesByFileNameInAndStatusInAndOwnerOrderByCreatedDateDesc(filesList, status, users.getUserId().toString());
        }
    }

    private void getMultipartFileList(String path) throws IOException {
        File f = new File(path);
        File[] fs = f.listFiles();
        if (NullUtil.isNull(fs)) {
            MultipartFile m = FileUtil.fileToMultipartFile(f);
            cutToM3U8(m);
        } else {
            for (File file : fs) {
                MultipartFile m = FileUtil.fileToMultipartFile(file);
                cutToM3U8(m);
            }
        }
    }

    private void cutToM3U8(MultipartFile video) throws IOException {
        Long size = video.getSize();
        log.info("文件信息: title= {}, size= {}mb", video.getOriginalFilename(), size / 1048576);
        TranscodeConfig transcodeConfig = new TranscodeConfig();
        log.info("转码配置: {}", transcodeConfig.toString());

        // 原始文件名称，也就是视频的标题
        String title = video.getOriginalFilename();
        String fileName = StringUtil.split(title, ".")[0];
        // String suffix = title.substring(title.lastIndexOf("."));

        // io到临时文件
        Path tempFile = tempDir.resolve(title);
        log.info("io到临时文件: {}", tempFile.toString());
        try {
            log.info("Generate by UUID.randomUUID()!!!");
            //  String titleName = UUID.get();
            video.transferTo(tempFile);
            // 删除后缀
            //title = title.substring(0, title.lastIndexOf("."));
            // 创建视频目录
            Path targetFolder = Files.createDirectories(Paths.get(videoM3u8, fileName));
            log.info("创建文件夹目录：{}", targetFolder);
            Files.createDirectories(targetFolder);
            // 执行转码操作
            log.info("开始转码");
            try {
                FFmpegUtil fFmpegUtil = new FFmpegUtil();
                fFmpegUtil.transcodeToM3u8(tempFile.toString(), targetFolder.toString(), transcodeConfig);
            } catch (Exception e) {
                log.error("转码异常：{}", e.getMessage());
                Map<String, Object> result = new HashMap<>();
                result.put("success", false);
                result.put("message", e.getMessage());
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
                return;
            }
            // 封装结果
            Map<String, Object> videoInfo = new HashMap<>();
            videoInfo.put("title", fileName);
            videoInfo.put("m3u8", String.join("/", "", fileName, "index.m3u8"));
            videoInfo.put("poster", String.join("/", "", fileName, "poster.jpg"));

            // 文件存储全路径
            File targetFile = new File(videoPath + title);
            video.transferTo(targetFile);
            String posterLocation = targetFolder + FileUtil.separator + "poster.jpg";
            String thumbnailLocation = targetFolder + FileUtil.separator + "thumbnail.jpg";

            ImageUtil imageUtil = new ImageUtil();
            FileUtil.copy(posterLocation, thumbnailLocation);
            imageUtil.square(thumbnailLocation, false);
            ImageUtil.compress(thumbnailLocation);


            com.models.entity.Files f = filesService.findFilesByFileName(fileName);
            f.setStatus(com.models.entity.Files.FRAGMENT);
            f.setRemark("FFmpeg m3u8 processing complete !!!");

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", videoInfo);
            filesService.save(f);

        } finally {
            // 始终删除临时文件
            Files.delete(tempFile);
        }
    }

}

