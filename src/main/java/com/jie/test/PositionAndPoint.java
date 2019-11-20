package com.jie.test;

import com.jie.bean.Point;

import java.io.Serializable;
import java.util.List;

public   class PositionAndPoint implements Serializable {
        int position;
        List<Point> points;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public List<Point> getPoints() {
            return points;
        }

        public void setPoints(List<Point> points) {
            this.points = points;
        }
    }
