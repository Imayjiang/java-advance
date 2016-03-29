/**
 *并发的核心是保证某个共享变量/共享代码块在某一时刻只有一个线程能执行访问|更新操作，
 *且操作对其他线程可见(即volatile的内存语义)。
 *
 *1) 线程的状态变迁
 * 新建、可运行、运行中、阻塞(休眠、锁阻塞、等待)、死亡
 *
 *2）JMM
 * 线程内存、主内存
 *线程内存：线程栈(栈帧{局部变量区、操作数区(return、入参)、栈运算区})
 *主内存
 *
 *3）锁
 * a：java内在锁
 * synchronize、wait、notify、notifyAll、volatile、
 * 可重入
 * b：JUC
 * volatile内存语义 + CAS(cpu指令级别支持)
 *
 * （volatile+CAS）——> AQS ——>LOCK + Atomic_dataType ——> queue
 * C：
 *
 *
 *
 *
 * </p>
 * Date:2016/3/26   
 *
 * @author: jiangbo
 */
package com.qger.java.concurrent;