/**
 *
 *<h3>load a class with the specified name</h3>
 *<p>
 * <span>normally,there are three steps to load a class</span>
 * <ol>
 *     <li>find class with the name ,and return the byte array of the class</li>
 *     <li>define the class with the resulting byte array,which will return a {@link java.lang.Class} type</li>
 *     <li>resolve the class,usually link the specified class to the classLoader</li>
 * </ol>
 * in other words, the {@link java.lang.ClassLoader#loadClass} method is the integration of the above.
 *</p>
 *
 * <h3>load order</h3>
 * <span>the load order of a class base on <em>Parents Delegation Model</em></span>
 * when loading a class,the current classLoader will delegate to its parent classLoader.
 * when parent classLoader failed,the current then will try to load.
 * see {@link }for more detail
 * <p>
 *
 * </p>
 *
 *
 *<h3>class isolation</h3>
 * <p>
 * <ul>
 *     <li>runtime class identifier</li>
 *     <span>a runtime class(which means that the class is loaded) is identified by two elements:classLoader+$class#getCanonicalName</span>
 *     <li>Class.forName && $Class.class</li>
 *     {@link java.lang.Class#forName}method is the same as $Class.class,they are loaded by the ClassLoader which load the host class.
 *     see {@link com.qger.java.classloader.ClassIdentifierUseCase} for more detail
 * </ul>
 *</p>
 *<h3>hot deployment</h3>
 *<p>there is another responsibility of the classLoader,to provide a mechanism to resolve the hot deployment</p>
 *
 *
 * </p>
 * author: jiangbo 
 * time: 2015/08/16 下午2:51.
 */
package com.qger.java.classloader;