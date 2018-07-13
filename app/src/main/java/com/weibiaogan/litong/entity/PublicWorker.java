package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/7/12
 * describe:
 */
public class PublicWorker {
    private Prepaid prepaid;
    private List<Worker> area;
    private Proportion proportion;

    public Proportion getProportion() {
        return proportion;
    }

    public void setProportion(Proportion proportion) {
        this.proportion = proportion;
    }

    public Prepaid getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Prepaid prepaid) {
        this.prepaid = prepaid;
    }

    public List<Worker> getArea() {
        return area;
    }

    public void setArea(List<Worker> area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "PublicWorker{" +
                "prepaid=" + prepaid +
                ", area=" + area +
                '}';
    }
    public class Proportion{
        private int frist;
        private int second;
        private int three;

        public int getFrist() {
            return frist;
        }

        public void setFrist(int frist) {
            this.frist = frist;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getThree() {
            return three;
        }

        public void setThree(int three) {
            this.three = three;
        }
    }
}
