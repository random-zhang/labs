package com.jie.ks3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.lowagie.text.DocumentException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import static com.jie.ks3.createWord.exportDocT;

public class myChartDialog extends JDialog implements ActionListener {
    float a1, a2, a3, a4;
    long time1,time2,time3,time4;
    JButton button = new JButton("生成详细数据");
    JFreeChart chart;
    public myChartDialog(float a1, float a2, float a3, float a4,long time1,long time2,long time3,long time4 ) {
        setTitle("柱状图");
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.time1=time1;
        this.time2=time2;
        this.time3=time3;
        this.time4=time4;
        Container c = getContentPane();
        c.add(createPanel(), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(button);
        button.addActionListener(this);
        c.add(panel, BorderLayout.SOUTH);
        pack();//以合适的大小显示
        setVisible(true);
    }

    public CategoryDataset createDataset() //创建柱状图数据集
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(a1, a1 + "", "FCFS");
        dataset.setValue(a2, a2 + "", "SSTF");
        dataset.setValue(a3, a3 + "", "SCAN");
        dataset.setValue(a4, a4 + "", "CSCAN");
        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        JFreeChart chart = ChartFactory.createBarChart("hi", "四种算法", "平均移道量", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle("随机10000次各算法平均移道量", new Font("宋体", Font.BOLD + Font.ITALIC, 20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot = (CategoryPlot) chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis = plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));//设置横坐标字体
        return chart;
    }
    public JPanel createPanel() {
        chart= createChart(createDataset());
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
    }
    public static void main(String[] args) {
        myChartDialog chart = new myChartDialog(22, 11, 23, 33,10,10,10,10);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button) {//生成文件
           Date  date=new Date();
            SimpleDateFormat  f=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            String str=f.format(date);
            String outPath="D:\\jfreechart\\"+"柱状图"+str+".png";
            saveAsFile(chart,outPath, 600, 400);
            try {
                exportDocT(outPath,a1,a2,a3,a4,time1,time2,time3,time4);
                new File(outPath).delete();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void saveAsFile(JFreeChart chart, String outputPath,int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtils.writeChartAsPNG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {

                    // do nothing
                }
            }
        }
    }
}
