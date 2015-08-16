package com.qger.java.classloader;

/**
 * </p>
 * author: jiangbo
 * time: 2015/08/16 下午7:58.
 */
public class ClassIdentifierUseCase {

    public static void main(String[] args) throws ClassNotFoundException {
        testHostClassLoader();
        testClassIdentifier();
    }

    /**
     * the classLoader of the Class.forName usage is the same as $Class.class,which
     * are both the host classLoader,like the sample below,the host class is the
     * {@link ClassIdentifierUseCase},so the {@link com.qger.java.classloader.BizEntity}
     * is loaded by the {@link sun.misc.Launcher.AppClassLoader}
     *
     * @throws ClassNotFoundException
     */
    private static void testHostClassLoader() throws ClassNotFoundException {
        Class bizEntityClass = Class.forName(BizEntity.class.getName());

        StandardClassLoader userDefinedClassLoader
                = new StandardClassLoader(StandardClassLoader.USER_DEFINED_ROOT_PATH);
        Class userDefinedLoadedClass = userDefinedClassLoader.loadClass(BizEntity.class.getName());

        System.out.println("class#forName:" + bizEntityClass.getClassLoader());
        System.out.println("$Class.class:" + BizEntity.class.getClassLoader());
        System.out.println("userDefined:" + userDefinedLoadedClass.getClassLoader());

        System.out.println(bizEntityClass.equals(BizEntity.class));
        System.out.println(bizEntityClass.equals(userDefinedLoadedClass));
    }

    /**
     * the class identifier is composed of two elements:
     * <ul>
     *     <li>the instance classLoader</li>
     *     <li>canonical name of class </li>
     * </ul>
     * @throws ClassNotFoundException
     */
    private static void testClassIdentifier() throws ClassNotFoundException {
        StandardClassLoader userDefinedClassLoader
                = new StandardClassLoader(StandardClassLoader.USER_DEFINED_ROOT_PATH);

        StandardClassLoader userDefinedClassLoader1
                = new StandardClassLoader(StandardClassLoader.USER_DEFINED_ROOT_PATH);
        Class sameClassLoaderInstance0 = userDefinedClassLoader.loadClass(BizEntity.class.getName());
        Class sameClassLoaderInstance1 = userDefinedClassLoader.loadClass(BizEntity.class.getName());
        System.out.println(sameClassLoaderInstance0.equals(sameClassLoaderInstance1));

        Class diffClassLoaderInstance = userDefinedClassLoader1.loadClass(BizEntity.class.getName());
        System.out.println(sameClassLoaderInstance0.equals(diffClassLoaderInstance));
    }

}
