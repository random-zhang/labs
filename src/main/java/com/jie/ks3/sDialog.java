package com.jie.ks3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sDialog extends JDialog  implements ActionListener {
    JButton b1=new JButton("输入请求列表");
    JTextField t1=new JTextField();
    sDialog(JFrame parent, boolean modal){
        super(parent,modal);
        setTitle("自定义请求");
        JLabel label=new JLabel("输入请求列表");
        b1.addActionListener(this);
        JPanel p1=new JPanel();
        p1.add(label);
        p1.add(t1);
        getContentPane().add(p1);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==b1){

        }
    }
    public static void  main(String[] args){
        new sDialog(null,true);
    }
}
