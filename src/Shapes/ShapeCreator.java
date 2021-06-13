/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;


/**
 *
 * @author mamm_
 */
public class ShapeCreator {

    Color color;
    boolean fill;

    public ShapeCreator(boolean fill, Color color) {
        this.color = color;
        this.fill = fill;
    }

    public Shape newShape(char mode, int x, int y) {
        switch (mode) {
            case 'l':
                return new Line(x, y, x, y, fill, color);
            case 'r':
                return new Rectangle(x, y, 0, 0, fill, color);
            case 't':
                return new Triangle(fill, color);
            case 's':
                return new Square(x, y, fill, color);
            case 'o':
                return new Oval(x, y, 0, 0, fill, color);
            case 'c':
                return new Circle(x, y, 0, 0, fill, color);
        }
        return null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
