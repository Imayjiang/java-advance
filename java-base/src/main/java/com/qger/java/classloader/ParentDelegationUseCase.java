package com.qger.java.classloader;

/**
 * classLoader                        path
 * bootStrapClassLoader        $JAVA_HOME/jre/lib/rt.jar
 * extClassLoader              $JAVA_HOME/jre/lib/ext/*.jar
 * appClassLoader              $CLASS_PATH
 * </p>
 * Date:2015/8/16   Time:22:26
 *
 * @author: jiangbo.wjb
 */
public class ParentDelegationUseCase {

    public static void main(String[] args) throws ClassNotFoundException {
        showParentClassLoaders();
        delegateToParents();
    }

    private static void showParentClassLoaders() throws ClassNotFoundException {
        ClassLoader current = Thread.currentThread().getContextClassLoader();
        System.out.println("current:                     " + current);
        ClassLoader parent = current.getParent();
        System.out.println("current\'s parent:           " + parent.getClass());
        ClassLoader grandparent = parent.getParent() == null ? null : parent.getParent();
        System.out.println("the grandparent of current:  " + (grandparent != null ? grandparent.getClass() : "null"));
    }


    private static void delegateToParents() throws ClassNotFoundException {
        ClassLoader current = Thread.currentThread().getContextClassLoader();
        Class appLoaderClazz = current.loadClass(BizEntity.class.getName());
        Class stringClazz = current.loadClass(java.lang.String.class.getName());

        System.out.println("current          :  " + current);
        System.out.println("app loader       :  " + appLoaderClazz.getClassLoader());
        System.out.println("bootstrap loader :  " + stringClazz.getClassLoader());
    }
}
