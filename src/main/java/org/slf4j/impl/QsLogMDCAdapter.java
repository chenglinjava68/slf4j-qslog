package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xyyz150 on 2016/9/19.
 * like {@link org.slf4j.helpers.NOPMDCAdapter}
 */
public class QsLogMDCAdapter implements MDCAdapter {

    public void clear() {
    }

    public String get(String key) {
        return null;
    }


    public void put(String key, String val) {

    }

    public void remove(String key) {

    }

    public Map getCopyOfContextMap() {
        return null;
    }

    public void setContextMap(Map contextMap) {
    }
}
