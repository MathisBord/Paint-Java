package model;

import java.awt.*;

public enum Enumcolor {
    BLUE (Color.BLUE),
    RED (Color.RED),
    BLACK (Color.BLACK),
    YELLOW (Color.YELLOW),
    PINK (Color.PINK);

    private Color color;

    private Enumcolor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


}
