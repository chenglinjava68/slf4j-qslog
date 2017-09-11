package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;

/**
 * Created by xyyz150 on 2016/9/19.
 */
public class StaticMDCBinder {


    /**
     * The unique instance of this class.
     */
    public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

    private StaticMDCBinder() {
    }

    /**
     * Currently this method always returns an instance of
     * {@link StaticMDCBinder}.
     */
    public MDCAdapter getMDCA() {
        return new QsLogMDCAdapter();
    }

    public String  getMDCAdapterClassStr() {
        return QsLogMDCAdapter.class.getName();
    }
}
