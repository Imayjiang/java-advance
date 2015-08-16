package com.qger.java.classloader;

import java.io.*;
import java.security.SecureClassLoader;

/**
 * </p>
 * author: jiangbo
 * time: 2015/08/16 下午1:34.
 */
public class StandardClassLoader extends SecureClassLoader {

    private final static String BASE_SCAN_PACKAGE = "com.qger.java.classloader";

    private final static String CLASS_FILE_SUFFIX = ".class";

    private final String JAR_BASE_PATH;

    public StandardClassLoader(String jarPath) {
        this.JAR_BASE_PATH = jarPath;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        if (s.contains(BASE_SCAN_PACKAGE)) {
            return super.loadClass(s);
        }
        return super.findClass(s);
    }

    private byte[] getPendingLoadClassData(String name) throws IOException {
//        String path = JAR_BASE_PATH + name.replace(".", File.separator) + CLASS_FILE_SUFFIX;
//
//        InputStream in = new FileInputStream(path);
//        OutputStream out = new ByteArrayOutputStream();
//        byte[] buf = new byte[2048];
//        int num = 0;
//        while((num = in.read(buf)) != -1){}
        throw new RuntimeException("no implementation yet!");
    }

}
