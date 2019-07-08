package com.jie.test;

public class main {
    public static void main(String[] args)
    {
        Factory factory = new Factory();
        Producer p = new Producer(factory, "生产者");
        Consumer c = new Consumer(factory, "消费者");
        p.start();
        c.start();
    }
}
