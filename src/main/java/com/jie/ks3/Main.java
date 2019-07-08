package com.jie.ks3;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.*;
public class Main  extends JFrame implements ActionListener, ItemListener, MenuListener {
   //JTextField
    int direction=-1;
    JTextField t5=new JTextField();
    JTextField t6=new JTextField();
    JTextField t7=new JTextField();
    JRadioButton radio1=new JRadioButton("磁道增大方向");
    JRadioButton radio2=new JRadioButton("磁道减小方向");
    JTextArea area=new JTextArea(3,10);
    JButton b1=new JButton("先来先服务调度法");
    JButton b2=new JButton("最短寻道时间优先调度法");
    JButton b3=new JButton("扫描调度法");
    JButton b4=new JButton("循环扫描算法");
    JButton ba1=new JButton("先来先服务调度法动画演示");
    JButton ba2=new JButton("最短寻道时间优先调度法动画演示");
    JButton ba3=new JButton("扫描调度法动画演示");
    JButton ba4=new JButton("循环扫描算法动画演示");
    JButton bu4=new JButton("随机产生四种算法各10000组数据");
    JButton bu5=new JButton("清空文字框");
    JButton bu6=new JButton("手动输入");
    JMenu saveMenu = new JMenu("保存");
    JMenu animationMenu = new JMenu("动画");
    JLabel l7=new JLabel("输入磁盘访问的请求数：");
    JLabel  l5=new JLabel("起始道号：");
    JLabel l6=new JLabel("终止道号：");
    boolean isAuto=true;
    Main(){
        setTitle("移动臂调度算法的模拟");    //设置显示窗口标题
        JMenuBar menuBar = new JMenuBar();
      //  menuBar.add(saveMenu);
        //menuBar.add(animationMenu);
        add(menuBar,BorderLayout.NORTH);
        saveMenu.addMenuListener(this);
        animationMenu.addMenuListener(this);
        setBounds(300, 0, 1000, 1000);   //设置窗口显示尺寸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
        Container c=getContentPane();    //获取当前窗口的内容窗格
        JPanel jpCenter=new JPanel();    //创建一个JPanel对象
        jpCenter.setLayout(new GridLayout(5,1));
        JPanel j1=new JPanel();//创建一个JPanel对象
        j1.setLayout(new GridLayout(3,2));
        JLabel l1=new JLabel("移动调度算法如下                                                  ");
        j1.add(l5);
        j1.add(t5);
        j1.add(l6);
        j1.add(t6);
        j1.add(l7);
        j1.add(t7);
        jpCenter.add(j1);
        JPanel j2=new JPanel();
        j2.setLayout(new GridLayout(2,2));
        j2.add(b1);
        j2.add(ba1);
        j2.add(b2);
        j2.add(ba2);
        jpCenter.add(j2);
        JPanel j3=new JPanel();
        j3.setLayout(new GridLayout(2,1));
        JPanel j5=new JPanel();
        JPanel j6=new JPanel();
        j6.setLayout(new GridLayout(2,2));
        JLabel l8=new JLabel("设置磁道方向：");
        j5.add(l8);
        j5.add(radio1);
        j5.add(radio2);
        ButtonGroup group=new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        //单选按钮设置监听器
        radio1.addItemListener(this);
        radio2.addItemListener(this);
        j6.add(b3);
        j6.add(ba3);
        j6.add(b4);
        j6.add(ba4);
        j3.add(j5);
        j3.add(j6);
        jpCenter.add(j3);
        JPanel j4=new JPanel();
        j4.add(bu4);
        j4.add(bu5);
        j4.add(bu6);
        bu5.addActionListener(this);
        jpCenter.add(j4);
        area.setEditable(false);
        jpCenter.add(new JScrollPane(area));
        //设置监听效果
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        ba1.addActionListener(this);
        ba2.addActionListener(this);
        ba3.addActionListener(this);
        ba4.addActionListener(this);
        bu4.addActionListener(this);
        bu6.addActionListener(this);
        add(jpCenter,BorderLayout.CENTER);
        setVisible(true);    //设置窗口是否可见
    }
    public void updateArea(String text){
        area.append(text);
    }

    public static void main(String[] agrs)
    {
        new Main();    //创建一个实例化对象
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton  button=(JButton)e.getSource();
        float a1=0,a2=0,a3=0,a4=0;
        long time1=0,time2=0,time3=0,time4=0;
        Random random=new Random();

        if (button==bu6){
            if(bu6.getActionCommand()=="手动输入"){
                l5.setText("请输入磁道最大值");
                l6.setText("设置移动臂初始位置");
                l7.setText("输入要访问的请求");
                bu6.setText("自动输入");
                isAuto=false;
            }else {
                l5.setText("起始道号：");
                l6.setText("终止道号");
                l7.setText("输入磁盘访问的请求数：");
                bu6.setText("自动输入");
                bu6.setText("手动输入");
                isAuto=true;
            }
            return;
        }
        if(button==bu4){//随机产生100组各种算法数据
            int start=1,end=300,num=10,n=10000;
            for(int i=0;i<4;i++){
                Date date1=new Date();
                for (int j=0;j<n;j++){
                    Work work=new Work(start,end,num);
                    scheduler s=new scheduler(work,this,true,null,0);
                    switch (i){
                        case 0:{
                            a1+=s.FCFS();
                            break;
                        }
                        case 1:{
                            a2+=s.SSTF();
                            break;
                        }
                        case 2:{
                            a3+=s.SCAN(random.nextInt(2));
                            break;
                        }
                        case 3:{
                            a4+=s.CSCAN(random.nextInt(2));
                            break;
                        }
                    }
                }
                Date date2=new Date();
                switch (i) {
                    case 0: {
                        time1=date2.getTime()-date1.getTime();
                        break;
                    }
                    case 1: {
                        time2=date2.getTime()-date1.getTime();
                        break;
                    }
                    case 2: {
                        time3=date2.getTime()-date1.getTime();
                        break;
                    }
                    case 3: {
                        time4=date2.getTime()-date1.getTime();
                        break;
                    }
                }
            }
            a1=a1/n;
            a2=a2/n;
            a3=a3/n;
            a4=a4/n;
            new myChartDialog(a1,a2,a3,a4,time1,time2,time3,time4);
            System.out.println(a1+"     "+a2+" "+a3+"   "+a4);
        return;
        }
        String s5=t5.getText().toString().trim();
        String s6=t6.getText().toString().trim();
        String s7=t7.getText().toString().trim();
        //判断字符串是否为整数
        if(s5==null||s5.equals("")||s6==null||s6.equals("")||s7==null||s7.equals("")){
           new MyDialog(this,true,"请输入完整").setVisible(true);
           return;
        }
        Work work=null;
        scheduler s=null;
        if(isAuto) {
            int start,end,num;
            try {
               start = Integer.parseInt(s5);
                end = Integer.parseInt(s6);
               num = Integer.parseInt(s7);
            } catch (Exception pe) {
                new MyDialog(this, true, "请输入整数").setVisible(true);
                return;
            }
            if (num == 0) {
                new MyDialog(this, true, "请输入有效的请求数").setVisible(true);
                return;
            }
            if ((end - start) <= 0) {
                new MyDialog(this, true, "终止道号号不能小于等于初始道号").setVisible(true);
                return;
            }
            if ((end - start) / num <= 1) {
                new MyDialog(this, true, "请求数过大或磁道数过小").setVisible(true);
                return;
            }
            work=new Work(start,end,num);
           s=new scheduler(work,this,true,null,0);
        }else{
            int start,end,num,begin;
            Request[] request=null;
            try {
                start =1;
                end = Integer.parseInt(s5);
                begin=Integer.parseInt(s6);
                String[] rs=s7.split(",");
                request=new Request[rs.length];
                num = rs.length;
                for(int i=0;i<rs.length;i++){
                     int number=Integer.parseInt(rs[i]);
                     request[i]=new Request(i,number);
                     if(number>end||number<1){
                         new MyDialog(this, true, "存在越界").setVisible(true);
                     }
                }
            } catch (Exception pe) {
                new MyDialog(this, true, "请输入整数").setVisible(true);
                return;
            }
            if (num == 0) {
                new MyDialog(this, true, "不能超过最大值").setVisible(true);
                return;
            }
            if ((end - start) <= 0) {
                new MyDialog(this, true, "终止道号号不能小于等于初始道号").setVisible(true);
                return;
            }
            if(begin<1||begin>end){
                new MyDialog(this, true, "起始值不能小于最小值，大于最大值").setVisible(true);
                return;
            }
            if ((end - start) / num <= 1) {
                new MyDialog(this, true, "请求数过大或磁道数过小").setVisible(true);
                return;
            }
            work=new Work(start,end,num);
            s=new scheduler(work,this,false,request,begin);
        }
        animationDialog dialog=null;
       if(button==b1){//先来先服务调度法
           s.FCFS();
       }
       if(button==b2){//最短寻道时间优先调度法
          s.SSTF();
           return;
       }
        if(button==b3){//扫描调度法
            //设置方向
            if(direction==-1){
                new MyDialog(this,true,"请选择方向").setVisible(true);
            }else
               s.SCAN(direction);
        }
        if(button==b4){//循环扫描算法
            s.CSCAN(direction);
        }
        if(button==ba1){
            dialog=new animationDialog(work.getStart(),work.getEnd(),work.getNum(),s.getBegin(),s.getReuests(),1,work);
            dialog.setVisible(true);
            dialog.run();
        }
        if(button==ba2){
            dialog=new animationDialog(work.getStart(),work.getEnd(),work.getNum(),s.getBegin(),s.getReuests(),2,work);
            dialog.setVisible(true);
            dialog.run();
        }
        if(button==ba3){
            dialog=new animationDialog(work.getStart(),work.getEnd(),work.getNum(),s.getBegin(),s.getReuests(),3,direction);
            dialog.setVisible(true);
            dialog.run();
        }
        if(button==ba4){
            dialog=new animationDialog(work.getStart(),work.getEnd(),work.getNum(),s.getBegin(),s.getReuests(),4,direction);
            dialog.setVisible(true);
            dialog.run();
        }
        if(button==bu5){
            area.setText("");
            validate();
        }

    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() ==radio1)
        {
            direction=1;
        } else
        {
            direction=0;
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if(e.getSource()==saveMenu){

        }
        if(e.getSource()==animationMenu){

        }
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) { }
    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }

}
