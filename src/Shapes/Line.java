/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import paint.Board;

public class Line extends Shape {

    private int x1, x2, y1, y2;

    public Line(int x1, int y1, int x2, int y2, boolean fill, Color color) {
        super(fill, color);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public boolean select(Point point) {
        int minX = Integer.min(x1, x2);
        int minY = Integer.min(y1, y2);
        int maxX = Integer.max(x1, x2);
        int maxY = Integer.max(y1, y2);
        java.awt.Rectangle r = new java.awt.Rectangle(minX, minY, maxX - minX, maxY - minY);
        return r.contains(point);
    }

    @Override
    public void move(Point pressPoint, Point dragPoint) {
        int pX = pressPoint.x;
        int pY = pressPoint.y;
        int dX = dragPoint.x;
        int dY = dragPoint.y;
        x1 = Math.abs(x1 - (pX - dX));
        y1 = Math.abs(y1 - (pY - dY));
        x2 = Math.abs(x2 - (pX - dX));
        y2 = Math.abs(y2 - (pY - dY));

    }

    @Override
    public void resize(boolean i) {
        
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Line l = (Line) super.clone();
        l.y1 -= 20;
        l.y2 -= 20;
        l.x1 -= 20;
        l.x2 -= 20;
        return l;
    }

    @Override
    public void decorator(char s) {
        int minX = Integer.min(x1, x2);
        int minY = Integer.min(y1, y2);
        int maxX = Integer.max(x1, x2);
        int maxY = Integer.max(y1, y2);
        switch (s) {
            case 'l':
                break;
            case 'r':
                Rectangle t = new Rectangle(minX, minY, maxX - minX, maxY - minY, fill, color);
                Board.getMainBoard().shapes.add(t);
                Board.getMainBoard().repaint();
                break;
            case 't':
                break;
            case 's':
                break;
            case 'o':
                break;
            case 'c':
                break;
        }
    }
}
