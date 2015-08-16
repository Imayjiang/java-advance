package com.qger.java.classloader;

/**
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
        System.out.println("current:" + current);
        System.out.println("current's parent:" + current.getParent());
        System.out.println("the grandparent of current:" + current.getParent().getParent());
    }

    /**
     * classLoader                        path
     * bootStrapClassLoader        $JAVA_HOME/jre/lib/rt.jar
     * extClassLoader              $JAVA_HOME/jre/lib/ext/*.jar
     * appClassLoader              $CLASS_PATH
     *
     * @throws ClassNotFoundException
     */
    private static void delegateToParents() throws ClassNotFoundException {
        ClassLoader current = Thread.currentThread().getContextClassLoader();
        Class appLoaderClazz = current.loadClass(BizEntity.class.getName());
        Class stringClazz = current.loadClass(java.lang.String.class.getName());

        System.out.println("current:" + current);
        System.out.println("app loader:" + appLoaderClazz.getClassLoader());
        System.out.println("delegate to parents:" + stringClazz.getClassLoader());
    }
}
