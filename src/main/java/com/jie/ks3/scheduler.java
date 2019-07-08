package com.jie.ks3;
import java.text.DecimalFormat;
import java.util.*;

public class scheduler {
    Work work;
    Request[] request;
    Main frame;
    int begin;//磁头初始位置
    boolean isAuto;
    DecimalFormat df = new DecimalFormat("0.000");
    public scheduler(Work work, Main frame,boolean isAuto,Request[] request,int begin) {//构造器，接收一个作业
        this.work = work;
        this.frame = frame;
        //磁头初始位置设为所允许访问磁道号范围的中间值。
        //如若磁道访问允许范围为100~300，则磁头初始位置在200处。
        // begin=(work.getStart()+work.getEnd())/2;

        this.isAuto=isAuto;
        if(isAuto){
            this.request = work.getRandomRequest();
            this.begin=setBegin();
        }else{
            this.request=request;
            this.begin=begin;
        }
    }

    public float FCFS() {//先来先服务算法
        Request[] req = request;
        frame.updateArea("磁道号区间：" + work.getStart() + "到" + work.getEnd() + "\n");
        frame.updateArea("磁头初始时所在磁道号：" + begin + "\n");
        double sum = 0;
        frame.updateArea("请求号        被访问的下一个磁道号               移动距离" + "\n");
        for (Request r : req) {
            frame.updateArea("--" + r.getId() + "----------------" + r.getNumber() + "-----------------" + Math.abs(begin - r.getNumber()) + "--" + "\n");
            sum += Math.abs(begin - r.getNumber());
            begin = r.getNumber();
        }
        frame.updateArea("***********平均寻道长度为：" + df.format(sum / req.length) + "************" + "\n");
        return Float.parseFloat(df.format(sum / req.length));
    }
    public float SSTF() {//最短寻道优先
        Request[] req = request;
        frame.updateArea("磁道号区间：" + work.getStart() + "到" + work.getEnd() + "\n");
        frame.updateArea("磁头初始时所在磁道号：" + begin + "\n");
        int t = 0;
        double sum = 0;
        frame.updateArea("请求号        被访问的下一个磁道号               移动距离" + "\n");
        for (int i = 0; i < req.length; i++) {
            int temp = work.getEnd();
            for (int j = 0; j < req.length; j++) {
                if (Math.abs(req[j].getNumber() - begin) < temp && req[j].getFlag() == 0) {
                    temp = Math.abs(req[j].getNumber() - begin);
                    t = j;
                }
            }
            frame.updateArea("--" + req[t].getId() + "----------------" + req[t].getNumber() + "-----------------" + temp + "--"+"\n");
            begin = req[t].getNumber();
            req[t].setFlag(1);
            sum += temp;
        }
        frame.updateArea("***********平均寻道长度为：" + df.format(sum / req.length) + "************" + "\n");
        return Float.parseFloat(df.format(sum / req.length));
    }

    public float SCAN(int direction) {//扫描算法
        Request[] req = request;
        frame.updateArea("磁道号区间：" + work.getStart() + "到" + work.getEnd() + "\n");
        frame.updateArea("磁头初始时所在磁道号：" + begin + "\n");
        if (direction == 1)
            frame.updateArea("磁头初始运动方向： 磁道号增大方向\n");
        else
            frame.updateArea("磁头初始运动方向： 磁道号减小方向\n");
        Map<Integer, Request> map = new HashMap<Integer, Request>();
        ArrayList<Integer> s1=new ArrayList<>(),s2=new ArrayList<>();
        for (Request r : req) {
            if (r.getNumber() >= begin) {
                map.put(r.getNumber(), r);
                s1.add(r.getNumber());
            } else {
                map.put(r.getNumber(), r);
                s2.add(r.getNumber());
            }
        }
        frame.updateArea("请求号        被访问的下一个磁道号               移动距离\n");
        double sum = 0;
        if (direction == 1) {
        	 Collections.sort(s1);//针对一个ArrayList内部的数据排序
            for (int temp : s1) {
                frame.updateArea("--" + map.get(temp).getId() + "----------------" + temp + "-----------------" + Math.abs(temp - begin) + "\n");
                sum += Math.abs(temp - begin);
                begin = temp;
            }
            Collections.sort(s2,Collections.reverseOrder());//针对一个ArrayList内部的数据排序
            for (int temp :s2) {
                frame.updateArea("--" + map.get(temp).getId() + "----------------" + temp + "-----------------" + Math.abs(temp - begin) + "\n");
                sum += Math.abs(temp - begin);
                begin = temp;
            }
        } else {
        	 Collections.sort(s2,Collections.reverseOrder());//针对一个ArrayList内部的数据排序
            for (int temp :s2) {
                frame.updateArea("--" + map.get(temp).getId() + "----------------" + temp + "-----------------" + Math.abs(temp - begin) + "\n");
                sum += Math.abs(temp - begin);
                begin = temp;
            }
            for (int temp : s1) {
                frame.updateArea("--" + map.get(temp).getId() + "----------------" + temp + "-----------------" + Math.abs(temp - begin) + "\n");
                sum += Math.abs(temp - begin);
                begin = temp;
            }
        }
        frame.updateArea("***********平均寻道长度为：" + df.format(sum / req.length) + "***********" + "\n");
        return Float.parseFloat(df.format(sum / req.length));
    }

    public float CSCAN(int direction) {      //循环扫描算法

        Request[] req = request;
        frame.updateArea("磁道号区间：" + work.getStart() + "到" + work.getEnd() + "\n");
        frame.updateArea("磁头初始时所在磁道号：" + begin + "\n");
        if (direction == 1)
            frame.updateArea("磁头初始运动方向： 磁道号增大方向\n");
        else
            frame.updateArea("磁头初始运动方向： 磁道号减小方向\n");
        Map<Integer, Request> map = new HashMap<Integer, Request>();
        TreeSet<Integer> ts1 = new TreeSet<Integer>();
        TreeSet<Integer> ts2 = new TreeSet<Integer>();
        for(Request r:req){
            if(r.getNumber()>=begin){
                map.put(r.getNumber(), r);
                ts1.add(r.getNumber());
            }
            else{
                map.put(r.getNumber(), r);
                ts2.add(r.getNumber());
            }
        }
        frame.updateArea("请求号        被访问的下一个磁道号               移动距离\n");
        double sum = 0;
        if(direction==1){
            for(int temp:ts1){//
                frame.updateArea("--"+map.get(temp).getId()+"----------------"+temp+"-----------------"+Math.abs(temp-begin)+"\n");
                sum+=Math.abs(temp-begin);
                begin=temp;
            }
            for(int temp:ts2){
                frame.updateArea("--"+map.get(temp).getId()+"----------------"+temp+"-----------------"+Math.abs(temp-begin)+"\n");
                sum+=Math.abs(temp-begin);
                begin=temp;
            }
        }else{
            for(int temp:ts2.descendingSet()){
                frame.updateArea("--"+map.get(temp).getId()+"----------------"+temp+"-----------------"+Math.abs(temp-begin)+"\n");
                sum+=Math.abs(temp-begin);
                begin=temp;
            }
            for(int temp:ts1.descendingSet()){
                frame.updateArea("--"+map.get(temp).getId()+"----------------"+temp+"-----------------"+Math.abs(temp-begin)+"\n");
                sum+=Math.abs(temp-begin);
                begin=temp;
            }
        }
        return Float.parseFloat(df.format(sum / req.length));
    }
    public int getBegin(){
        return  begin;
    }
    public Request[] getReuests(){//获取所有要访问的点

        return request;
    }
    private int setBegin(){
        Random random = new Random();
         int begin = random.nextInt(work.getEnd() - work.getStart()) + work.getStart();
        return  begin;
    }
}