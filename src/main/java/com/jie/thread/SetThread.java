package com.jie.thread;

public class SetThread extends Thread {
    // TODO Auto-generated method stub
    private Student s;
    private int x = 0;

    public SetThread(Student s) {
        this.s = s;
    }

    public void run() {
        for (int i = 0; i < 6; i++) {
            synchronized (s) {
                // 判断，如果有值，就等待
                if (s.flag) {
                    try {
                        s.wait(); //t1就等待在这里了。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 设置值
                if (x % 2 == 0) {
                    s.Class = "A班学生";
                } else {
                    s.Class = "B班学生";
                }
                x++; //x=1,x=2,
                // 有值后，就修改标记
                s.flag = true;
                s.notify(); // 唤醒等待的线程,唤醒其他的线程，不代表其他的线程能够立即执行。
            }
            //可能t1抢到,也可能t2抢到
        }
    }

}