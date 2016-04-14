package com.qger.java.jvm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * </p>
 * Date:2016/3/30
 *
 * @author: jiangbo
 */
public class JVM {

    public static void execute(String projectPath) {
        File file = getProjectMainPom(projectPath);
        if (file == null || !file.exists()) {
            return;
        }

        String absolutePath = file.getAbsolutePath();
        String command = "mvn clean compile -f" + " " + absolutePath + " " + "-Dmaven.test.skip=true";

        Runtime runtime = Runtime.getRuntime();
        try {
            String[] cmd = new String[3];
            cmd[0] = shellExecutor();
            cmd[1]="/C";
            cmd[2] = command;
            Process process = runtime.exec(cmd);

            final BufferedReader brInfo
                    = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            final BufferedReader brError
                    = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));
            while (brInfo.readLine() != null) {
                System.out.println(brInfo.readLine());
            }
            if(brError.readLine() != null) {
                System.out.println(brError.readLine());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shellExecutor() {
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Windows")) {
            System.out.println("os.name:" + os);
            return "cmd.exe";
        }
        return "sh";
    }

    private static File getProjectMainPom(String projectPath) {
        File directory = new File(projectPath);
        if (!directory.exists()) {
            throw new IllegalArgumentException("no such path");
        }

        if (!directory.isDirectory()) {
            throw new IllegalStateException("not directory");
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {

            if (file.getName().contains("pom.xml")) {
                return file;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        execute("D:\\programming\\java\\src\\qger\\java-advance");
    }


}
