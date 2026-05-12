package model;

import java.awt.*;
import java.io.Serializable;

public class Shape implements Serializable {
    private Color color;

    public Shape(Color color){
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(this.color);
    }
}
