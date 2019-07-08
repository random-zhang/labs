package com.jie.test;

public class Factory{
    private int value;
    private boolean available = false;
    public synchronized int get() {
        while (!available) {
            try {
                // 等待生产者生产
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        // 通知生产者数据已经被取走，可以再次写入数据
        notifyAll();
        return value;
    }
    public synchronized void put(int value)
    {
        while (available)
        {
            try
            {
                // 等待消费者消费
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        this.value = value;
        available = true;
        // 通知消费者可以来取数据
        notifyAll();
    }
}
