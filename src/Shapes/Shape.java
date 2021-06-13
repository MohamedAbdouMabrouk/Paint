/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public abstract class Shape implements Cloneable{

    Color color;
    boolean fill;

    public Shape(boolean fill, Color color) {
        this.color = color;
        this.fill = fill;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public abstract void draw(Graphics g);
    public abstract void move(Point pressPoint, Point dragPoint);
    public abstract void resize(boolean i);
    public abstract boolean select(Point point);
    public abstract void decorator(char s);
}
