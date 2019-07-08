package com.jie.test;

public class Consumer extends Thread
{
    private Factory factory;
    private String name;
    public Consumer(Factory b, String n)
    {
        factory = b;
        name = n;
    }
    public void run()
    {
        int value = 0;
        for (int i = 1; i <6;i++)
        {
            value = factory.get();
            System.out.println("消费者" + name + " 消费了: " + value);
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
