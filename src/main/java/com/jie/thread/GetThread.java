package com.jie.thread;

public class GetThread extends Thread {
    private Student s;

    public GetThread(Student s) {
        this.s = s;
    }
    public void run() {
        for (int i = 0; i < 6; i++) {
            synchronized (s) {
                // 判断没有值，就等待
                if (!s.flag) {
                    try {
                        s.wait(); //t2在这等待了。
                        //t2线程在这里等待，那么，它就会释放锁对象。
                        //将来，当它再次获取到执行权的时候，是从哪里等待，哪里醒来。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(s.Class+ "   学生编号:" + s.count);
                s.count += 1;
                // 修改标记
                s.flag = false;
                s.notify(); //唤醒其他的线程
            }
            //可能t2,可能t1
        }
    }
}
      