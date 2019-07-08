package com.jie.thread;

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.count = 1;
        SetThread st = new SetThread(s);
        GetThread gt = new GetThread(s);
        st.start();
        gt.start();

    }
}
