package com.jie.ks3;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class animationDialog extends JDialog {
    DecimalFormat df = new DecimalFormat("0.000");
    //MyPanel DPanel = new MyPanel(); //创建画板
    int start,end,num,begin,mode,direction,temp;
    Request[] requests;
    Work work;
    MyPanel panel=new MyPanel();
    animationDialog(int start,int end,int num,int begin,Request[] requests,int mode,Work work) {
        setBounds(200,0,1400,1000);
        this.start=start;
        this.end=end;
        this.num=num;
        this.begin=begin;
        this.requests=requests;
        this.mode=mode;
        this.work=work;
        // panel.repaint();
        add(panel);
    }
    animationDialog(int start,int end,int num,int begin,Request[] requests,int mode,int direction) {
        setBounds(200,0,1400,1000);
        this.start=start;
        this.end=end;
        this.num=num;
        this.begin=begin;
        this.requests=requests;
        this.mode=mode;
        this.direction=direction;
        // panel.repaint();
        add(panel);
    }
    public void run(){
        new Thread(){
            @Override
            public void run() {
                switch(mode){
                    case 1:{
                        FCFS(work,this,panel);
                        break;
                    }
                    case 2:{
                        SSTF(work,this,panel);
                        break;
                    }
                    case 3:{
                        try {
                            SCAN(direction,this,panel);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 4:{
                        try {
                            CSCAN(direction,this,panel);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }.start();
    }
    public class MyPanel extends JPanel{
        @Override
        public void paint (Graphics g) {
            super.paint(g);
            g.setColor(new Color(	216,191,216));
            int width=getWidth();
            int height=getHeight();
            int a=800/end;
            for(int i=start;i<end;i++){
                g.drawOval(width/2-((i+1)*a)/2,height/2-((i+1)*a)/2,(i+1)*a,(i+1)*a);
            }
            for(int i=0;i<requests.length;i++){
                if(requests[i].getFlag()==0)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.red);
                g.fillOval(width/2,height/2-(requests[i].getNumber()*a)/2,5,5);
            }
            g.setColor(Color.BLACK);
            g.fillOval(width/2,height/2-((begin)*a)/2,5,5);
            g.drawString("当前磁头所在磁道号"+begin,width/2+10,height/2-((begin)*a)/2);
            g.drawString("未访问请求",20,50);
            g.drawString("已访问请求",width-200,50);
            g.drawString("请求号：         请求磁道：      　访问次序：          移道量：    ",width-300,100);
            float sum=0;
            for(int i=0;i<requests.length;i++){
                Request request=requests[i];
                if(request.getFlag()==0){
                    g.drawString("请求号："+request.getId()+"         请求磁道："+request.getNumber(),20,100+i*40);
                }else{
                    //g.drawString("请求号："+request.getId()+"         请求磁道："+request.getNumber()+"　　　访问次序："+request.getOrder(),width-300,100+i*40);
                    g.drawString(request.getId()+"                             "+request.getNumber()+"                             "+request.getOrder()+"          "+request.getOffset(),width-300,140+i*40);
                    sum+=request.getOffset();
                }
            }
            g.drawString("平均寻道量:"+sum/requests.length,width-150,100+(requests.length+1)*40);
        }
    }
    public float FCFS(Work work,Thread thread,JPanel panel) {//先来先服务算法
        double sum = 0;
        int order=1;
        for (Request r : requests) {
            sum += Math.abs(this.begin - r.getNumber());
            r.setOffset( Math.abs(this.begin - r.getNumber()));
           // this.temp=this.begin - r.getNumber();
            this.begin = r.getNumber();
            r.setFlag(1);
            r.setOrder(order++);
            try {
                thread.sleep(3000);
                panel.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.temp=(int)sum;
        return Float.parseFloat(df.format(sum / requests.length));
    }
    public float SSTF(Work work,Thread thread,JPanel panel){//最短寻道优先
        int t = 0;
        double sum = 0;
        for (int i = 0; i < requests.length; i++) {
            int temp = work.getEnd();
            for (int j = 0; j < requests.length; j++) {
                if (Math.abs(requests[j].getNumber() - begin) < temp && requests[j].getFlag() == 0) {
                    temp = Math.abs(requests[j].getNumber() - begin);
                    t = j;
                }
            }
            requests[t].setOffset( temp);
            this.begin = requests[t].getNumber();
            requests[t].setFlag(1);
            requests[t].setOrder(i+1);
            try {
                thread.sleep(1000);
                panel.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += temp;
        }
         this.temp=(int)sum;
        return Float.parseFloat(df.format(sum /requests.length));
    }
    public float SCAN(int direction,Thread thread,JPanel panel) throws InterruptedException {//扫描算法
        thread.sleep(3000);
        Map<Integer, Request> map = new HashMap<Integer, Request>();
        TreeSet<Integer> ts1 = new TreeSet<Integer>();
        TreeSet<Integer> ts2 = new TreeSet<Integer>();
        for (Request r : requests) {
            if (r.getNumber() >= begin) {
                map.put(r.getNumber(), r);
                ts1.add(r.getNumber());
            } else {
                map.put(r.getNumber(), r);
                ts2.add(r.getNumber());
            }
        }
        double sum = 0;
        int order=0;
        if (direction == 1) {
            for (int temp : ts1) {
                order+=1;
                sum += Math.abs(temp - begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin = temp;
                map.get(temp).setFlag(1);

                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
            for (int temp : ts2.descendingSet()) {
                order+=1;
                sum += Math.abs(temp - begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin = temp;
                map.get(temp).setFlag(1);

                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
        } else {
            for (int temp : ts2.descendingSet()) {
                order+=1;
                sum += Math.abs(temp - begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin = temp;
                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
            for (int temp : ts1) {
                order+=1;
                sum += Math.abs(temp - begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin = temp;

                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
        }
        this.temp=(int)sum;
        return Float.parseFloat(df.format(sum /requests.length));
    }
    public float CSCAN(int direction,Thread thread,JPanel panel) throws InterruptedException {      //循环扫描算法
        Map<Integer, Request> map = new HashMap<Integer, Request>();
        TreeSet<Integer> ts1 = new TreeSet<Integer>();
        TreeSet<Integer> ts2 = new TreeSet<Integer>();
        thread.sleep(3000);
        for(Request r:requests){
            if(r.getNumber()>=begin){
                map.put(r.getNumber(), r);
                ts1.add(r.getNumber());
            }
            else{
                map.put(r.getNumber(), r);
                ts2.add(r.getNumber());
            }
        }
        double sum = 0;
        int order=0;
        if(direction==1){
            for(int temp:ts1){//
                order+=1;
                sum+=Math.abs(temp-begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin=temp;

                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
            for(int temp:ts2){
                order+=1;
                sum+=Math.abs(temp-begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin=temp;
                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
        }else{
            for(int temp:ts2.descendingSet()){
                order+=1;
                sum+=Math.abs(temp-begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin=temp;

                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
            for(int temp:ts1.descendingSet()){
                order+=1;
                sum+=Math.abs(temp-begin);
                map.get(temp).setOffset(Math.abs(temp-begin));
                begin=temp;
                map.get(temp).setFlag(1);
                map.get(temp).setOrder(order);
                thread.sleep(3000);
                panel.repaint();
            }
        }
        this.temp=(int)sum;
        return Float.parseFloat(df.format(sum /requests.length));
    }
}
