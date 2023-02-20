package com.constant;

import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Classname: AceEnvironment
 * @Date: 19/7/2021 12:26 下午
 * @Author: garlam
 * @Description:
 */

@Component
@Order(1)
public class AceEnvironment {
    private static final Logger log = LogManager.getLogger(AceEnvironment.class.getName());

    //container of application.yml value
    public static Environment environment;
    private static String filePath;
    private static String misc;
    //  private static String filesTemp;
    private static String imagesPath;
    private static String imagesThumbnail;
    private static String videoPath;
    private static String videoM3u8;


    @Autowired
    public AceEnvironment(Environment environment) {
        AceEnvironment.environment = environment;
        setUp();
    }


    public static void setUp() {
        folderSetUp();
    }


    private static void folderSetUp() {
        String msg;
        String osName = OsUtil.getOsName();
        if (osName.contains(OsUtil.WINDOWS)) {
            String c = "C:\\";
            String ace = "ACE\\";

            String filePath = c + ace + "files\\";

            String imgPath = c + ace + "images\\";

            String thumbnail = imgPath + "thumbnail\\";

            String video = c + ace + "videos\\";
            String m3u8 = video + "m3u8\\";

            String miscellaneous = c + ace + "misc\\";

            //create folder
            FileUtil.mkDirs(filePath);
            FileUtil.mkDirs(thumbnail);
            FileUtil.mkDirs(m3u8);
            FileUtil.mkDirs(miscellaneous);

            //set value for system use
            setFilePath(filePath);
            setImagesPath(imgPath);
            setImagesThumbnail(thumbnail);
            setVideoPath(video);
            setVideoM3u8(m3u8);
            setMisc(miscellaneous);

            msg = "ACE environment setup complete : Windows !!!";
        } else if (osName.contains(OsUtil.MAC)) {
            msg = "ACE environment setup complete : MAC !!! => ACE Environment setup incomplete !!!";
        } else if (osName.contains(OsUtil.LINUX)) {
            String optWorkspace = "/opt/workspace/";
            String ace = "ace/";

            String filePath = optWorkspace + ace + "files/";

            String imgPath = optWorkspace + ace + "images/";
            String thumbnail = imgPath + "thumbnail/";

            String video = optWorkspace + ace + "videos/";
            String m3u8 = video + "m3u8/";

            String miscellaneous = optWorkspace + ace + "misc/";

            //create folder
            FileUtil.mkDirs(filePath);
            FileUtil.mkDirs(thumbnail);
            FileUtil.mkDirs(m3u8);
            FileUtil.mkDirs(miscellaneous);


            //set value for system use
            setFilePath(filePath);
            setImagesPath(imgPath);
            setImagesThumbnail(thumbnail);
            setVideoPath(video);
            setVideoM3u8(m3u8);
            setMisc(miscellaneous);
            msg = "ACE environment setup complete : LINUX !!!";
        } else {
            msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
            return;
        }
        Console.println(msg, Console.BLUE, Console.BOLD);
    }


    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        AceEnvironment.filePath = filePath;
    }

    public static String getImagesPath() {
        return imagesPath;
    }

    public static void setImagesPath(String imagesPath) {
        AceEnvironment.imagesPath = imagesPath;
    }

    public static String getImagesThumbnail() {
        return imagesThumbnail;
    }

    public static void setImagesThumbnail(String imagesThumbnail) {
        AceEnvironment.imagesThumbnail = imagesThumbnail;
    }

    public static String getVideoM3u8() {
        return videoM3u8;
    }

    public static void setVideoM3u8(String videoM3u8) {
        AceEnvironment.videoM3u8 = videoM3u8;
    }

    public static String getVideoPath() {
        return videoPath;
    }

    public static void setVideoPath(String videoPath) {
        AceEnvironment.videoPath = videoPath;
    }

    public static String getMisc() {
        return misc;
    }

    public static void setMisc(String misc) {
        AceEnvironment.misc = misc;
    }
}

