package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xyyz150 on 2016/9/19.
 */
public class QsLogLoggerFactory implements ILoggerFactory {

    // key: name (String), value: a QsLogLoggerAdapter;
    ConcurrentMap<String, Logger> loggerMap;


    public QsLogLoggerFactory() {
        loggerMap = new ConcurrentHashMap<String, Logger>();
    }

    /**
     * @param name
     * @return
     * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
     */
    @Override
    public Logger getLogger(String name) {
        synchronized (this) {
            Logger slf4jLogger = loggerMap.get(name);
            if (slf4jLogger != null) {
                return slf4jLogger;
            } else {
                QsLogger log4jLogger = QsLogger.getQsLogger(name);
                Logger newInstance = new QsLogLoggerAdapter(log4jLogger);
                Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
                return oldInstance == null ? newInstance : oldInstance;
            }
        }
    }
}
