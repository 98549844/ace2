package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Classname: HttpUtil
 * @Date: 5/7/2021 6:49 上午
 * @Author: garlam
 * @Description:
 */


public class HttpUtil {
    private static final Logger log = LogManager.getLogger(HttpUtil.class.getName());

    public static Map getHttpServletRequest(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    public static Map getRemoteInfo(HttpServletRequest request) {
        Map m = new HashMap();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        String remoteAddress = request.getRemoteAddr();
        int remotePort = request.getRemotePort();
        String protocol = request.getProtocol();
        String servletPath = request.getServletPath();

        m.put("remoteHost", remoteHost == null ? "null" : remoteHost);
        m.put("remoteUser", remoteUser == null ? "null" : remoteUser);
        m.put("remoteAddress", remoteAddress == null ? "null" : remoteAddress);
        m.put("remotePort", remotePort);
        m.put("protocol", protocol == null ? "" : protocol);
        m.put("servletPath", servletPath == null ? "null" : servletPath);
        return m;
    }

    public static String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public static List<String> requestInfo(HttpServletRequest request) {
        List<String> requestInfo = new ArrayList<>();

        String agent = request.getHeader("user-agent");
        requestInfo.add("Http header: " + agent);

        System.out.println(agent);
        StringTokenizer st = new StringTokenizer(agent, ";");
        st.nextToken();
        String clientOs = st.nextToken();
        System.out.println(clientOs);
        requestInfo.add("Client OS: " + clientOs);

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.arch"));
        requestInfo.add("os.name : " + System.getProperty("os.name"));
        requestInfo.add("os.version : " + System.getProperty("os.version"));
        requestInfo.add("os.arch : " + System.getProperty("os.arch"));

        System.out.println(request.getHeader("user-agent")); //返回客户端浏览器的版本号、类型
        System.out.println(request.getMethod()); //：获得客户端向服务器端传送数据的方法有get、post、put等类型
        System.out.println(request.getRequestURI()); //：获得发出请求字符串的客户端地址
        System.out.println(request.getServletPath()); //：获得客户端所请求的脚本文件的文件路径
        System.out.println(request.getServerName()); //：获得服务器的名字
        System.out.println(request.getServerPort()); //：获得服务器的端口号
        System.out.println(request.getRemoteAddr()); //：获得客户端的ip地址
        System.out.println(request.getRemoteHost()); //：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
        System.out.println(request.getProtocol());
        System.out.println(request.getHeaderNames()); //：返回所有request header的名字，结果集是一个enumeration（枚举）类的实例

        requestInfo.add("Header: " + request.getHeader("user-agent"));
        requestInfo.add("Method: " + request.getMethod());
        requestInfo.add("RequestURI: " + request.getRequestURI());
        requestInfo.add("ServletPath: " + request.getServletPath());
        requestInfo.add("ServerName: " + request.getServerName());
        requestInfo.add("ServerPort: " + request.getServerPort());
        requestInfo.add("RemoteAddr: " + request.getRemoteAddr());
        requestInfo.add("RemoteHost: " + request.getRemoteHost());
        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("HeaderNames: " + request.getHeaderNames());

        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Scheme: " + request.getScheme());
        System.out.println("Server Name: " + request.getServerName());
        System.out.println("Server Port: " + request.getServerPort());
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Character Encoding: " + request.getCharacterEncoding());
        System.out.println("Content Length: " + request.getContentLength());
        System.out.println("Content Type: " + request.getContentType());
        System.out.println("Auth Type: " + request.getAuthType());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Path Info: " + request.getPathInfo());
        System.out.println("Path Trans: " + request.getPathTranslated());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote User: " + request.getRemoteUser());
        System.out.println("Session Id: " + request.getRequestedSessionId());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Accept: " + request.getHeader("Accept"));
        System.out.println("Host: " + request.getHeader("Host"));
        System.out.println("Referer : " + request.getHeader("Referer"));
        System.out.println("Accept-Language : " + request.getHeader("Accept-Language"));
        System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
        System.out.println("Connection : " + request.getHeader("Connection"));
        System.out.println("Cookie : " + request.getHeader("Cookie"));

        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("Scheme: " + request.getScheme());
        requestInfo.add("Server Name: " + request.getServerName());
        requestInfo.add("Server Port: " + request.getServerPort());
        requestInfo.add("Protocol: " + request.getProtocol());
        requestInfo.add("Remote Address: " + request.getRemoteAddr());
        requestInfo.add("Remote Host: " + request.getRemoteHost());
        requestInfo.add("Character Encoding: " + request.getCharacterEncoding());
        requestInfo.add("Content Length: " + request.getContentLength());
        requestInfo.add("Content Type: " + request.getContentType());
        requestInfo.add("Auth Type: " + request.getAuthType());
        requestInfo.add("HTTP Method: " + request.getMethod());
        requestInfo.add("Path Info: " + request.getPathInfo());
        requestInfo.add("Path Trans: " + request.getPathTranslated());
        requestInfo.add("Query String: " + request.getQueryString());
        requestInfo.add("Remote User: " + request.getRemoteUser());
        requestInfo.add("Session Id: " + request.getRequestedSessionId());
        requestInfo.add("Request URI: " + request.getRequestURI());
        requestInfo.add("Servlet Path: " + request.getServletPath());
        requestInfo.add("Accept: " + request.getHeader("Accept"));
        requestInfo.add("Host: " + request.getHeader("Host"));
        requestInfo.add("Referer : " + request.getHeader("Referer"));
        requestInfo.add("Accept-Language : " + request.getHeader("Accept-Language"));
        requestInfo.add("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
        requestInfo.add("User-Agent : " + request.getHeader("User-Agent"));
        requestInfo.add("Connection : " + request.getHeader("Connection"));
        requestInfo.add("Cookie : " + request.getHeader("Cookie"));

        return requestInfo;
    }

}

