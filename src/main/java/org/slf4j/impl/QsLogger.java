package org.slf4j.impl;

import org.slf4j.helpers.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xyyz150 on 2016/9/20.
 * QS日志，会通过服务把日志写入日志系统
 * 默认自动记录warn，error级别的日志
 */
public class QsLogger {

    String name;

    static String url;

    static boolean TraceEnabled = false;

    static boolean DebugEnabled = false;

    static boolean InfoEnabled = false;

    static boolean WarnEnabled = true;

    static boolean ErrorEnabled = true;

    static {

        try {
            String filePath = Loader.getResource("qslog.properties").getPath();
            FileInputStream fis = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(fis);
            url = prop.getProperty("qslog.url");

            String trace = prop.getProperty("qslog.trace");
            if (null != trace && trace.equalsIgnoreCase("true")) {
                TraceEnabled = true;
            }
            String debug = prop.getProperty("qslog.debug");
            if (null != debug && debug.equalsIgnoreCase("true")) {
                DebugEnabled = true;
            }
            String info = prop.getProperty("qslog.info");
            if (null != info && info.equalsIgnoreCase("true")) {
                InfoEnabled = true;
            }

            String warn = prop.getProperty("qslog.warn");
            if (null != warn && warn.equalsIgnoreCase("false")) {
                WarnEnabled = false;
            }

            String error = prop.getProperty("qslog.error");
            if (null != error && error.equalsIgnoreCase("false")) {
                ErrorEnabled = false;
            }
        } catch (IOException ex) {
            System.out.println("qslog.properties文件失败，文件不存在或者路径不正确！ ");
        } catch (Exception ex) {
            System.out.println("qslog.properties文件失败，文件不存在或者路径不正确！ ");
        }
    }

    public void log(LogLevel logLevel, String message, Throwable t) {
        if (url == null || url.isEmpty()) return;
        Map<String, String> param = new HashMap<String, String>();
        if (message != null && !message.isEmpty()) {
            param.put("info", message);
        }
        if (t != null) {
            param.put("error", getException(t));
        }
        if (param.isEmpty()) return;

        param.put("level", logLevel.name());
        param.put("name", getName());
        try {
            //存储日志到任何地方的代码
            System.out.print(url + param.get("info"));
//            LogHttpHelper.doPost(url, param);
        } catch (Exception e) {
            Util.report("发送远程日志异常" + e.toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean isTraceEnabled() {
        return TraceEnabled;
    }

    public static void setTraceEnabled(boolean traceEnabled) {
        TraceEnabled = traceEnabled;
    }

    public static boolean isDebugEnabled() {
        return DebugEnabled;
    }

    public static void setDebugEnabled(boolean debugEnabled) {
        DebugEnabled = debugEnabled;
    }

    public static boolean isInfoEnabled() {
        return InfoEnabled;
    }

    public static void setInfoEnabled(boolean infoEnabled) {
        InfoEnabled = infoEnabled;
    }

    public static boolean isWarnEnabled() {
        return WarnEnabled;
    }

    public static void setWarnEnabled(boolean warnEnabled) {
        WarnEnabled = warnEnabled;
    }

    public static boolean isErrorEnabled() {
        return ErrorEnabled;
    }

    public static void setErrorEnabled(boolean errorEnabled) {
        ErrorEnabled = errorEnabled;
    }

    public static QsLogger getQsLogger(String name) {
        QsLogger logger = new QsLogger();
        logger.setName(name);
        return logger;
    }

    String getException(Throwable t) {
        StringBuffer sb = new StringBuffer();
        String s = t.getClass().getName();
        String errorMessage = t.getLocalizedMessage();
        sb.append((errorMessage != null) ? (s + ": " + errorMessage) : s);
        Throwable cause = t.getCause();
        while (cause != null) {
            if (!cause.getClass().getName().equalsIgnoreCase(s)) {
                sb.append("\n原异常:").append(cause.toString());
                StackTraceElement[] stackTraces = cause.getStackTrace();
                if (stackTraces != null && stackTraces.length > 0) {
                    for (StackTraceElement stackTrace : stackTraces) {
                        sb.append("\n").append(stackTrace.toString());
                    }
                }
                cause = cause.getCause();
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
