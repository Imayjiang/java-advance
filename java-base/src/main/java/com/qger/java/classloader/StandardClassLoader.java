package com.qger.java.classloader;

import java.io.*;
import java.security.SecureClassLoader;

/**
 * </p>
 * author: jiangbo
 * time: 2015/08/16 下午1:34.
 */
public class StandardClassLoader extends SecureClassLoader {

    public static final String USER_DEFINED_ROOT_PATH
            = "D:\\programming\\java\\src\\qger\\java-advance\\java-base\\target\\classes\\";

    private static final String LOAD_COMPONENT_PACKAGE_NAME
            = "com.qger.java.classloader";

    private String jarPath;

    StandardClassLoader(String jarPath) {
        this.jarPath = jarPath;
    }

    @Override
    public Class<?> loadClass(final String name) throws ClassNotFoundException {
        if (!name.startsWith(LOAD_COMPONENT_PACKAGE_NAME)) {
            return super.loadClass(name);
        }
        Class<?> clazz = findLoadedClass(name);
        if (clazz != null) {
            return clazz;
        }
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        if (!name.startsWith(LOAD_COMPONENT_PACKAGE_NAME)) {
            return super.findClass(name);
        }
        byte[] classData = getPendingLoadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException("not found");
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getPendingLoadClassData(final String classFullName) {
        String path = jarPath + classFullName.replace('.', File.separatorChar) + ".class";
        try {
            InputStream in = new FileInputStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
