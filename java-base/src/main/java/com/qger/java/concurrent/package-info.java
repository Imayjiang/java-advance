/**
 *�����ĺ����Ǳ�֤ĳ���������/����������ĳһʱ��ֻ��һ���߳���ִ�з���|���²�����
 *�Ҳ����������߳̿ɼ�(��volatile���ڴ�����)��
 *
 *1) �̵߳�״̬��Ǩ
 * �½��������С������С�����(���ߡ����������ȴ�)������
 *
 *2��JMM
 * �߳��ڴ桢���ڴ�
 *�߳��ڴ棺�߳�ջ(ջ֡{�ֲ�����������������(return�����)��ջ������})
 *���ڴ�
 *
 *3����
 * a��java������
 * synchronize��wait��notify��notifyAll��volatile��
 * ������
 * b��JUC
 * volatile�ڴ����� + CAS(cpuָ���֧��)
 *
 * ��volatile+CAS������> AQS ����>LOCK + Atomic_dataType ����> queue
 * C��
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