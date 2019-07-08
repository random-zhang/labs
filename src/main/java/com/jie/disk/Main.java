package com.jie.disk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int start,end,num;
       Scanner in=new Scanner(System.in);//获取用户输入
        System.out.println("设定磁道起止道号：");
        System.out.print("起始道号：");
        start=in.nextInt();
        System.out.print("终止道号：");
        end=in.nextInt();
        System.out.print("输入磁盘访问的请求数：");
        num=in.nextInt();
        Work work=new Work(start,end,num);
        scheduler s=new scheduler(work);
        System.out.println();
        System.out.println("*************先来先服务调度法*************");
        s.FCFS();
        System.out.println();
        System.out.println("***********最短寻道时间优先调度法************");
        s.SSTF();
        System.out.println();
        System.out.println("****************扫描调度法*****************");
        s.SCAN();
    }

}
