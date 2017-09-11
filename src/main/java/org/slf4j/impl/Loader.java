package org.slf4j.impl;

import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by xyyz150 on 2017-09-11.
 */
public class Loader {
    static private boolean java1 = true;

    static private boolean ignoreTCL = false;

    static public URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;

        try {
            if(!java1 && !ignoreTCL) {
                classLoader = getTCL();
                if(classLoader != null) {
                    System.out.println("Trying to find [" + resource + "] using context classloader "
                            + classLoader + ".");
                    url = classLoader.getResource(resource);
                    if(url != null) {
                        return url;
                    }
                }
            }

            // We could not find resource. Ler us now try with the
            // classloader that loaded this class.
            classLoader = Loader.class.getClassLoader();
            if(classLoader != null) {
                System.out.println("Trying to find [" + resource + "] using " + classLoader
                        + " class loader.");
                url = classLoader.getResource(resource);
                if(url != null) {
                    return url;
                }
            }
        } catch(IllegalAccessException t) {
            System.out.println(t);
        } catch(InvocationTargetException t) {
            if (t.getTargetException() instanceof InterruptedException
                    || t.getTargetException() instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            System.out.println( t);
        } catch(Throwable t) {
            //
            //  can't be InterruptedException or InterruptedIOException
            //    since not declared, must be error or RuntimeError.
            System.out.println( t);
        }

        // Last ditch attempt: get the resource from the class path. It
        // may be the case that clazz was loaded by the Extentsion class
        // loader which the parent of the system class loader. Hence the
        // code below.
        System.out.println("Trying to find [" + resource +
                "] using ClassLoader.getSystemResource().");
        return ClassLoader.getSystemResource(resource);
    }

    private static ClassLoader getTCL() throws IllegalAccessException,
            InvocationTargetException {

        Method method = null;
        try {
            method = Thread.class.getMethod("getContextClassLoader", null);
        } catch (NoSuchMethodException e) {
            return null;
        }

        return (ClassLoader) method.invoke(Thread.currentThread(), null);
    }
}
