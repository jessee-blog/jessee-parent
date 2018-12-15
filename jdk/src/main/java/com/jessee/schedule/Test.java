package com.jessee.schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args){
        excute();
    }

    /**
     * 固定频率
     */
    public static void excuteWithFixdRate(){
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final int[] n = {0};

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    atomicInteger.incrementAndGet();
                    TimeUnit.SECONDS.sleep(2);

                    System.out.println("当前时间：" + new Date() + ",atomicInteger:" + atomicInteger.get());
                    if (atomicInteger.get() == 5){
                        throw new RuntimeException("bug");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0,1, TimeUnit.SECONDS);
    }

    public static void excute(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.schedule(()->test(),5,TimeUnit.SECONDS);
    }

    public static void test(){
        System.out.println("当前时间：" + new Date() );
    }

    /**
     * 固定间隔
     */
    public static void excuteWithFixedDelay(){
        System.out.println("当前时间：" + new Date() );
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前时间：" + new Date() );
            }
        },5,1, TimeUnit.SECONDS);
    }

    /**
     * 每天固定时间执行
     */
    public static void excutePerDay(){
        System.out.println("当前时间：" + new Date() );

        long rate = 1000*60*60*24;
        long initDelay = getTimeMillis("18:10:00") - System.currentTimeMillis();

        System.out.println("initDelay：" + initDelay );

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前时间：" + new Date() );
            }
        },initDelay,rate, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
