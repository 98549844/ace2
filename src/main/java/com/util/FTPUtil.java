package com.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: FTPUtil
 * @Date: 2023/2/6 下午 05:22
 * @Author: kalam_au
 * @Description:
 */

//@Component
public class FTPUtil {
    private static final Logger log = LogManager.getLogger(FTPUtil.class.getName());


    @Value("${ftp.host}")
    private final String host = "192.168.2.75";
    @Value("${ftp.username}")
    private final String username = "garlam";
    @Value("${ftp.password}")
    private final String password = "P@ssw0rd";
    @Value("${ftp.port}")
    private final int port = 21;
    @Value("${ftp.path}")
    private final String path = "./";

    private final static String ERROR_NO_FILE = "文件不存在!";
    private final static String ERROR_FILE = "FTP文件下载失败!";
    private final static String ERROR_CONN = "连接FTP失败!";

    public static void main(String[] args) {
        log.error("没进行测试");
    }

    /**
     * 读取ftp文件流
     *
     * @param filePath ftp文件路径
     * @throws Exception
     */
    public void readFile(String filePath, HttpServletResponse resp) {
        OutputStream out = null;
        FTPClient ftpClient = this.connectFtp();
        try {
            out = resp.getOutputStream();
            Assert.isTrue(existFile(ftpClient, filePath), ERROR_NO_FILE);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // commons-net提供的下载Ftp文件并写入输出流的方法。
            ftpClient.retrieveFile(filePath, out);
            ftpClient.logout();
        } catch (Exception e) {
            log.error("FTP文件下载失败！" + e.toString());
            Assert.isTrue(false, ERROR_FILE + e.getMessage());
        } finally {
            this.closeFtpClient(ftpClient);
        }
    }

    /**
     * 读取ftp文件流，返回字节流
     *
     * @param filePath ftp文件路径
     * @throws Exception
     */
    public InputStream readFileIs(String filePath) {
        InputStream is = null;
        FTPClient ftpClient = this.connectFtp();
        try {
            Assert.isTrue(existFile(ftpClient, filePath), ERROR_NO_FILE);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // commons-net提供的下载Ftp文件并写入输出流的方法。
            is = ftpClient.retrieveFileStream(filePath);
            ftpClient.logout();
        } catch (Exception e) {
            log.error("FTP文件下载失败！" + e.toString());
            Assert.isTrue(false, ERROR_FILE + e.getMessage());
        } finally {
            this.closeFtpClient(ftpClient);
        }
        return is;
    }

    /**
     * 获取FTP某一特定目录下的所有文件名称
     *
     * @param ftpClient  已经登陆成功的FTPClient
     * @param ftpDirPath FTP上的目标文件路径
     */
    public List<String> getFileNameList(FTPClient ftpClient, String ftpDirPath) {
        List<String> list = new ArrayList();
        try {
            // 通过提供的文件路径获取FTPFile对象列表
            FTPFile[] files = ftpClient.listFiles(ftpDirPath);
            // 遍历文件列表，打印出文件名称
            for (int i = 0; i < files.length; i++) {
                FTPFile ftpFile = files[i];
                // 此处只打印文件，未遍历子目录（如果需要遍历，加上递归逻辑即可）
                if (ftpFile.isFile()) {
                    list.add(ftpFile.getName());
                }
            }
        } catch (IOException e) {
            log.error("错误" + e);
        }
        return list;
    }

    /**
     * 登陆FTP并获取FTPClient对象
     *
     * @return
     */
    public FTPClient connectFtp() {
        FTPClient ftpClient = null;
        String errorMsg = null;
        try {
            ftpClient = new FTPClient();
            //设置连接超时时间
            ftpClient.setConnectTimeout(1000 * 30);
            // 连接FTP服务器
            if (port == 0) {
                ftpClient.connect(host);
            } else {
                ftpClient.connect(host, port);
            }
            // 登陆FTP服务器
            ftpClient.login(username, password);
            // 中文支持
            ftpClient.setControlEncoding("UTF-8");
            // 设置文件类型为二进制（如果从FTP下载或上传的文件是压缩文件的时候，不进行该设置可能会导致获取的压缩文件解压失败）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                Assert.isTrue(false, ERROR_CONN + "用户名或密码错误");
            } else {
                log.info("FTP连接成功!");
            }
        } catch (Exception e) {
            log.info("登陆FTP失败，请检查FTP相关配置信息是否正确！" + e);
            Assert.isTrue(false, ERROR_CONN + e.getMessage());
            return null;
        }
        return ftpClient;
    }

    /**
     * 关闭FTP连接
     *
     * @param ftpClient
     */
    public void closeFtpClient(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 判断ftp服务器文件是否存在
     *
     * @param ftpClient
     * @param path
     * @return
     * @throws IOException
     */
    private static boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }
}

/*
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private FTPUtils ftpUtils;

    *//**
 * 读取ftp上的pdf文件
 *//*
    @GetMapping("/readPdfFile")
    public void readPdfFile(HttpServletResponse resp, String path) throws Exception {
        valid(path);
        //直接返回pdf
        ftpUtils.readFile(path,resp);
        String filename = path.substring(path.lastIndexOf("/")+1);
        resp.setContentType("application/pdf;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
    }*/


