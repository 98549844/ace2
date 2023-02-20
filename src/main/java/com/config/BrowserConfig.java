package com.config;


import com.util.BeanUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import com.util.Console;
import com.util.TypeUtil;
import com.util.IpUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

/**
 * Application启动时自动打开默认Browser
 */
@Configuration
public class BrowserConfig {
    private static final Log log = LogFactory.getLog(BrowserConfig.class);

  //  static String url = "http://localhost:8088/";
    static String swaggerUrl = "http://localhost:8088/swagger-ui.html";
    static String docUrl = "http://localhost:8088/doc.html";
    public static String windowsBrowser = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

    private String getUrl() {
        ApplicationContext applicationContext = BeanUtil.getApplicationContext();
        IpUtil ip = applicationContext.getBean("ipUtil", IpUtil.class);
        Map m = ip.getHostInfo();
        String localhostIp = (String) m.get("ip");
        Integer localhostPort = (Integer) m.get("port");
        String url = "http://" + localhostIp + ":" + localhostPort + "/";
        return url;
    }

    public static void main(String[] args) throws IOException {
        ProcessBuilder proc = new ProcessBuilder(windowsBrowser, "www.baidu.com");
        proc.start();
    }

    /**
     * 打开windows默认Browser
     */
    private void openAceIndexOnWindows(boolean indexEnable) {
        if (!indexEnable) {
            return;
        }
        try {
            ProcessBuilder proc = new ProcessBuilder(windowsBrowser, getUrl());
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("ACE INDEX: ", getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * openSwaggerOnMac
     *
     * @param m
     * @param swaggerEnable
     * @throws IOException
     */
    private void openSwaggerOnMac(Map m, boolean swaggerEnable) throws IOException {
        if (!swaggerEnable) {
            return;
        }
        String macSwaggerUrl = swaggerUrl.replace("8088", TypeUtil.integerToString((Integer) m.get("port")));
        String Command = "open " + macSwaggerUrl;
        log.info("Swagger2:\t\t" + swaggerUrl);
        Process Child = Runtime.getRuntime().exec(Command);
    }

    /**
     * @param m
     * @param isDocHtmlEnable
     * @throws IOException
     */
    private void openDocHtmlOnMac(Map m, boolean isDocHtmlEnable) throws IOException {
        if (!isDocHtmlEnable) {
            return;
        }
        String macSwaggerUrl = docUrl.replace("8088", TypeUtil.integerToString((Integer) m.get("port")));
        String Command = "open " + macSwaggerUrl;
        log.info("doc.html:\t\t" + docUrl);
        Process Child = Runtime.getRuntime().exec(Command);
    }


    private void openAceIndexOnMac(boolean indexEnable) throws IOException {
        BeanUtil app = new BeanUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        //原版
       // String macUrl = url.replace("8088", TypeUtil.integerToString((Integer) m.get("port")));
        log.info("ACE INDEX:\t\t" + getUrl());
        if (indexEnable) {
            String Command = "open " + getUrl();
            Process Child = Runtime.getRuntime().exec(Command);
        }
    }

//    public void getCss() throws IOException {
//        ApplicationContextUtil app = new ApplicationContextUtil();
//        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
//        Map m = ip.getHostInfo();
//        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
//        log.info("Home Page:\t\t" + macUrl + "assets/css/bootstrap.min.css");
//        if (true) {
//            String Command = "open " + macUrl + "assets/css/bootstrap.min.css";
//            Process Child = Runtime.getRuntime().exec(Command);
//        }
//    }
//
//    public void getIndex() throws IOException {
//        ApplicationContextUtil app = new ApplicationContextUtil();
//        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
//        Map m = ip.getHostInfo();
//        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
//        log.info("Home Page:\t\t" + macUrl + "ace/index.html");
//        if (true) {
//            String Command = "open " + macUrl + "ace/index.html";
//            Process Child = Runtime.getRuntime().exec(Command);
//        }
//    }

    /**
     * 打开windows默认Browser
     */
    private void openSwaggerOnWindows(boolean swaggerEnable) {
        if (!swaggerEnable) {
            return;
        }
        try {
            ProcessBuilder proc = new ProcessBuilder(windowsBrowser, swaggerUrl);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("SWAGGER:\t\t", swaggerUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDocHtmlOnWindows(boolean docHtmlEnable) {
        if (!docHtmlEnable) {
            return;
        }
        try {
            ProcessBuilder proc = new ProcessBuilder(windowsBrowser, docUrl);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("doc.html:\t\t", docUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PrintUrl(String banner, String url) {
        System.out.print(LocalDateTime.now() + "  ");
        Console.print("INFO ", Console.GREEN);
        Console.println(banner + url, Console.BOLD, Console.BLUE);
    }

    private static String getOsInfo() {
        //Java获取当前操作系统的信息
        //https://blog.csdn.net/qq_35981283/article/details/73332040
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name").toUpperCase();
        System.out.println("OPERATION SYSTEM TYPE: " + osName);
        return osName;
    }


    public void openAceIndexAndSwagger(boolean openIndex, boolean openSwagger, boolean openDocHtml) throws IOException {
        String osName = BrowserConfig.getOsInfo();
        BrowserConfig browserConfig = new BrowserConfig();

        BeanUtil app = new BeanUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        if (openIndex) {
            if (osName.contains("WINDOWS")) {
                browserConfig.openAceIndexOnWindows(true);
            } else if (osName.contains("MAC OS")) {
                browserConfig.openAceIndexOnMac(true);
            }
        }
        if (openSwagger) {
            if (osName.contains("WINDOWS")) {
                browserConfig.openSwaggerOnWindows(true);
            } else if (osName.contains("MAC OS")) {
                browserConfig.openSwaggerOnMac(m, true);
            }
        }
        if (openDocHtml) {
            if (osName.contains("WINDOWS")) {
                browserConfig.openDocHtmlOnWindows(true);
            } else if (osName.contains("MAC OS")) {
                browserConfig.openDocHtmlOnMac(m, true);

            }
        }

    }


}