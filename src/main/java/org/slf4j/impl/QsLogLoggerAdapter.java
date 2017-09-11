package org.slf4j.impl;

import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

/**
 * Created by xyyz150 on 2016/9/19.
 */
public final class QsLogLoggerAdapter extends MarkerIgnoringBase implements
        LocationAwareLogger, Serializable {
    private static final long serialVersionUID = 6182834495872498289L;

    final QsLogger logger;

    /**
     * Following the pattern discussed in pages 162 through 168 of "The complete
     * log4j manual".
     */
    final static String FQCN = QsLogLoggerAdapter.class.getName();

    // Does the log4j version in use recognize the TRACE level?
    // The trace level was introduced in log4j 1.2.12.
    final boolean traceCapable;

    // WARN: QsLogLoggerAdapter constructor should have only package access so
    // that
    // only QsLogLoggerFactory be able to create one.
    QsLogLoggerAdapter(QsLogger logger) {
        this.logger = logger;
        this.name = logger.getName();
        traceCapable = isTraceCapable();
    }

    private boolean isTraceCapable() {
        try {
            logger.isTraceEnabled();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        }
    }


    @Override
    public boolean isTraceEnabled() {
        if (traceCapable) {
            return logger.isTraceEnabled();
        } else {
            return logger.isDebugEnabled();
        }
    }

    @Override
    public void trace(String msg) {
        logger.log(LogLevel.TRACE, msg, null);
    }


    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(LogLevel.TRACE, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(LogLevel.TRACE, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.log(LogLevel.TRACE, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.log(LogLevel.TRACE, msg, t);
    }


    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }


    @Override
    public void debug(String msg) {
        logger.log(LogLevel.DEBUG, msg, null);
    }


    @Override
    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(LogLevel.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(LogLevel.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.log(LogLevel.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.log(LogLevel.DEBUG, msg, t);
    }


    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.log(LogLevel.INFO, msg, null);
    }

    @Override
    public void info(String format, Object arg) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(LogLevel.INFO, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(LogLevel.INFO, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String format, Object... argArray) {
        if (logger.isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(LogLevel.INFO, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.log(LogLevel.INFO, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.log(LogLevel.WARN, msg, null);
    }

    @Override
    public void warn(String format, Object arg) {
        if (logger.isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(LogLevel.WARN, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object... argArray) {
        if (logger.isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(LogLevel.WARN, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(LogLevel.WARN, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.log(LogLevel.WARN, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.log(LogLevel.ERROR, msg, null);
    }

    @Override
    public void error(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        logger.log(LogLevel.ERROR, ft.getMessage(), ft.getThrowable());
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(LogLevel.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String format, Object... argArray) {
        if (logger.isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(LogLevel.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.log(LogLevel.ERROR, msg, t);
    }

    @Override
    public void log(Marker marker, String callerFQCN, int level, String msg,
                    Object[] argArray, Throwable t) {
        LogLevel log4jLevel;
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                log4jLevel = LogLevel.TRACE;
                break;
            case LocationAwareLogger.DEBUG_INT:
                log4jLevel = LogLevel.DEBUG;
                break;
            case LocationAwareLogger.INFO_INT:
                log4jLevel = LogLevel.INFO;
                break;
            case LocationAwareLogger.WARN_INT:
                log4jLevel = LogLevel.WARN;
                break;
            case LocationAwareLogger.ERROR_INT:
                log4jLevel = LogLevel.ERROR;
                break;
            default:
                throw new IllegalStateException("Level number " + level
                        + " is not recognized.");
        }
        logger.log(log4jLevel, msg, t);
    }
}
