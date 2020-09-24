package com.gyb.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class JvmTest {
    private static int M = 1024*1024;

    private static final Integer K =  1024;
    public static void main(String[] args) {



        int size = K * K * 8;

        List<byte[]> lsit = new ArrayList<byte[]>();

        for (int i =0 ; i<K ;i++){
            System.out.println("JVM写入数据" + (i+1) +"M" );
            try{
               // Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            lsit.add(new byte[size]);
        }

    }


}
