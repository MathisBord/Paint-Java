package model;

import java.io.Serializable;

public class Point implements Serializable {
    private int x;
    private int y;


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(Point p){
        this.x = p.getX();
        this.y = p.getY();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    static void main(String[] args) {
        Point p = new Point(12, 83);
        System.out.println(p.toString());

    }
}
