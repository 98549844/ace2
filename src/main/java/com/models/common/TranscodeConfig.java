package com.models.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: TranscodeConfig
 * @Date: 2022/10/8 下午 10:33
 * @Author: kalam_au
 * @Description:
 */


public class TranscodeConfig {
    private static final Logger log = LogManager.getLogger(TranscodeConfig.class.getName());

    private String poster;                // 截取封面的时间			HH:mm:ss.[SSS]
    private String tsSeconds;            // ts分片大小，单位是秒
    private String cutStart;            // 视频裁剪，开始时间		HH:mm:ss.[SSS]
    private String cutEnd;                // 视频裁剪，结束时间		HH:mm:ss.[SSS]


    public TranscodeConfig() {
        this.poster = "00:00:00.001";
        this.tsSeconds = "10";
        this.cutStart = "";
        this.cutEnd = "";
    }


    @Override
    public String toString() {
        String tsConfig = "TranscodeConfig [poster= " + poster + ", tsSeconds= " + tsSeconds + ", cutStart= " + cutStart + ", cutEnd= " + cutEnd + "]";
        log.info(tsConfig);
        return tsConfig;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTsSeconds() {
        return tsSeconds;
    }

    public void setTsSeconds(String tsSeconds) {
        this.tsSeconds = tsSeconds;
    }

    public String getCutStart() {
        return cutStart;
    }

    public void setCutStart(String cutStart) {
        this.cutStart = cutStart;
    }

    public String getCutEnd() {
        return cutEnd;
    }

    public void setCutEnd(String cutEnd) {
        this.cutEnd = cutEnd;
    }
}

