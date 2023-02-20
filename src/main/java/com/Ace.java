package com;

import com.util.FileUtil;
import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws IOException {

        System.out.println(PathUtil.getSystemPath());

    }


    public static void startDavToMP4Video(String[] args) {
        String inputPath = "C:\\ACE\\temp\\" + "aaa.MOV";
        String outputPath = "C:\\ACE\\temp\\";
        String newFileName = "some_name";
        Ace ace = new Ace();
        ace.davToMP4Video(inputPath, outputPath, newFileName);
    }

    /**
     * @param inputPath   视频的地址
     * @param outputPath  视频转完格式存放地址
     * @param newFileName 新生成的视频名称
     */
    public boolean davToMP4Video(String inputPath, String outputPath, String newFileName) {
        //ffmpeg软件地址
        String ffmpegPath = "";
        if (!checkfile(inputPath)) {
            log.error(inputPath + " is not file");
            return false;
        }
        String video = "";
        String imgUrl = "";
        String oldVideoUrl = "";
        if (StringUtil.isNotBlank(inputPath)) {
            imgUrl = inputPath;
            video = imgUrl.replaceAll("\\\\", "/");
            oldVideoUrl = video.substring(0, video.lastIndexOf("/") + 1);
            video = video.substring(video.lastIndexOf("/") + 1);
        } else {
            log.error("inputPath is null ：" + inputPath);
            return false;
        }
        String oldFileName = video;
//		int type = checkContentType(inputPath);
        boolean status = false;
        log.info("直接转成mp4格式");
        status = processMp4(inputPath, inputPath, ffmpegPath, outputPath, newFileName);// 直接转成mp4格式

        if (status) {
            File folder = new File(oldVideoUrl);
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.getName().equals(oldFileName)) {
                    file.delete();
                }
            }
            log.info("视频转MP4成功!");
            return true;
        } else {
            log.error("视频转换失败，重试！");
            status = processMp4(inputPath, inputPath, ffmpegPath, outputPath, newFileName);// 直接转成mp4格式
            if (status) {
                File folder = new File(oldVideoUrl);
                File[] files = folder.listFiles();
                for (File file : files) {
                    if (file.getName().equals(oldFileName)) {
                        file.delete();
                    }
                }
                log.info("视频转MP4成功!");
                return true;
            }
            return false;
        }
    }


    private static boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            log.error(path + "不是文件夹！");
            return false;
        }
        return true;
    }

    private static boolean processMp4(String inputPath, String oldfilepath, String ffmpegPath, String outputPath, String fileName) {

        if (!checkfile(inputPath)) {
            log.error(oldfilepath + " is not file");
            return false;
        }
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath + "ffmpeg");
        command.add("-i");
        command.add(oldfilepath);
        command.add("-c:v");
        command.add("libx264");
        command.add("-mbd");
        command.add("0");
        command.add("-c:a");
        command.add("aac");
        command.add("-strict");
        command.add("-2");
        command.add("-pix_fmt");
        command.add("yuv420p");
        command.add("-movflags");
        command.add("faststart");
        command.add(outputPath + fileName + ".mp4");
        try {

            // 方案1
            //        Process videoProcess = Runtime.getRuntime().exec(ffmpegPath + "ffmpeg -i " + oldfilepath
            //                + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
            //                + outputPath + "a.flv");

            // 方案2
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

class PrintStream extends Thread {
    java.io.InputStream __is = null;

    public PrintStream(java.io.InputStream is) {
        __is = is;
    }

    @Override
    public void run() {
        try {
            while (this != null) {
                int _ch = __is.read();
                if (_ch != -1) {
//                    System.out.print((char) _ch);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


