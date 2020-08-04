package com.lt.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    private int j;
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ThreadTest tt = new ThreadTest();
        for(int i=0;i<2;i++)
        {
            new Thread(tt.new Adder()).start();
            new Thread(tt.new Subtractor()).start();
        }
    }
    private class Subtractor implements Runnable
    {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true)
            {
                lock.lock();
                try
                {
                    System.out.println("j--=" + j--  +  Thread.currentThread().getName());
                    new ThreadLocal().set("name");

                    Runtime.getRuntime();
                }finally
                {
                    lock.unlock();
                }
            }
        }
    }
    private class Adder implements Runnable
    {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true)
            {
                lock.lock();
                try
                {
                    System.out.println("j++=" + j++  +  Thread.currentThread().getName());
                }finally
                {
                    lock.unlock();
                }
            }
        }
    }
}
