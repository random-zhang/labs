package com.jie.ks3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends Dialog implements ActionListener

{
    JLabel label;
    JButton ok = new JButton("确定");
    MyDialog( JFrame parent, boolean modal,String title)
    {
        super(parent,modal);
        setTitle("警告");
        //setSize(260,140);
        setBounds(500,800,300,200);
        setResizable(false);
        setLayout(new GridLayout(2,1));
        label=new JLabel(title);
        //label.setBounds(100, 100, 65, 20);
        // ok.setBounds(200, 100, 60, 25);
        ok.setSize(60,25);
        add(label);
        add(ok);
        ok.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)

    {
        if (e.getSource()==ok)
        {
            dispose();
        }
    }

}