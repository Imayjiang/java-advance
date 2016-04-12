package com.qger.java.network.intrinsic.oio;

import java.io.IOException;
import java.security.PrivilegedAction;

/**
 * </p>
 * Date:2016/4/3
 *
 * @author: jiangbo
 */
public class SocketTest {
    /* the windows version. */
    private static float version;

    /* java.net.preferIPv4Stack */
    private static boolean preferIPv4Stack = false;

    /* sun.net.useExclusiveBind */
    private static String exclBindProp;

    /* If the version supports a dual stack TCP implementation */
    private static boolean useDualStackImpl = false;

    /* True if exclusive binding is on for Windows */
    private static boolean exclusiveBind = true;

    static {
        java.security.AccessController.doPrivileged( new PrivilegedAction<Object>() {
            public Object run() {
                version = 0;
                try {
                    version = Float.parseFloat(System.getProperties().getProperty("os.version"));
                    preferIPv4Stack = Boolean.parseBoolean(
                            System.getProperties().getProperty("java.net.preferIPv4Stack"));
                    exclBindProp = System.getProperty("sun.net.useExclusiveBind");
                } catch (NumberFormatException e ) {
                    assert false : e;
                }
                return null; // nothing to return
            } });

        // (version >= 6.0) implies Vista or greater.
        if (version >= 6.0 && !preferIPv4Stack) {
            useDualStackImpl = true;
        }

        if (exclBindProp != null) {
            // sun.net.useExclusiveBind is true
            exclusiveBind = exclBindProp.length() == 0 ? true
                    : Boolean.parseBoolean(exclBindProp);
        } else if (version < 6.0) {
            exclusiveBind = false;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(11);
        new ServerSocket();
        Thread.sleep(30 * 1000);
        for (int i = 0; i < 100; i++) {
            new ClientSocket(8000);
        }



//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (true) {
//                    if ((++i) % 2 == 0) {
////
//                    }else {
//                        try {
//                            Thread.sleep(900);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }).start();
    }

}
