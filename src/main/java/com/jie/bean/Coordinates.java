package com.jie.bean;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinates {//坐标系
        private List<Point> coordinates;
        private double sv;
         private double st;
         Coordinates(){
             coordinates=new ArrayList<>();
         }
      Coordinates(Double st,Double sv,String coordinates){
        this.st=st;
        this.sv=sv;
        setCoordinates(coordinates);
     }
    public List<Point> getCoordinates() {
        return this.coordinates;
    }
    public void setCoordinates(String list) {
             System.out.println(list);
        if(list!=null&&!list.equals("")){//list=(x,x)(y,y)
            this.coordinates=new ArrayList<>();
           /* Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
            Matcher matcher = pattern.matcher(list);
            while (matcher.find()) {
                String s=matcher.group();//aa,a
                System.out.println(s);
                insertPoint(new Point(s));
            }*/
           try{
               coordinates= JSON.parseArray(list,Point.class);
           }catch (Exception e){
               e.printStackTrace();
           }

        }
        //this.list = list;
    }
    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public double getSv() {
            return sv;
        }
        public void setSv(double sv) {
            this.sv = sv;
        }
        public double getSt() {
            return st;
        }
        public void setSt(double st) {
            this.st = st;
        }
        public Coordinates(String coordinates){
           setCoordinates(coordinates);
        }
        public boolean insertPoint(double cv,double ct){//插入坐标
            return coordinates.add(new Point(ct,cv));
        }
        public boolean insertPoint(Point point){//插入坐标
        return coordinates.add(point);
    }
        public void clearPoint(){//插入坐标
            coordinates=new ArrayList<>();
        }
        @Override
        public String toString() {//把坐标转换成字符串
            String string="";
            for (int i=0;i<coordinates.size();i++) {
                string+=coordinates.get(i).toString();
            }
            return string;
        }


    }