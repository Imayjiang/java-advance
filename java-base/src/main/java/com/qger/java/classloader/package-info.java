/**
 *<h3>the responsibility of a classLoader is to load a class with the specified name</h3>
 *<p>
 * normally,there are three steps to load a class
 * <ol>
 *     <li>find class with the name ,and return the byte array of the class</li>
 *     <li>define the class with the resulting byte array,which will return a {@link java.lang.Class} type</li>
 *     <li>resolve the class,usually link the specified class to the classLoader</li>
 * </ol>
 * in other words, the {@link java.lang.ClassLoader#loadClass} method is the integration of the above.
 *</p>
 *
 *<h3>about the class isolation</h3>
 * <ul>
 *     <li>runtime class identifier</li>
 *     a runtime class(which means that the class is loaded) is identified by two elements:classLoader+$class#getCanonicalName
 * </ul>
 *
 *<h3>hot deployment</h3>
 *<p>there is another responsibility of the classLoader,to provide a mechanism to resolve the hot deployment</p>
 *
 *
 * </p>
 * author: jiangbo 
 * time: 2015/08/16 下午2:51.
 */
package com.qger.java.classloader;