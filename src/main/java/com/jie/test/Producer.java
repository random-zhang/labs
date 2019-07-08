package com.jie.test;

import static java.lang.Thread.sleep;

public class Producer extends Thread {
    private Factory factory;
    private String name;
    public Producer(Factory b, String n)
    {
        factory = b;
        name = n;
    }
    public void run()
    {
        for (int i = 1; i <6;i++)
        {
            factory.put(i);
            System.out.println("生产者 " + name + "生产: " + i);
            try
            {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
