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

public class Oval extends Shape {

    private int x, y, width, height;
    private int x1, y1;

    public Oval(int x, int y, int width, int height, boolean fill, Color color) {
        super(fill, color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x2) {
        this.x1 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y2) {
        this.y1 = y2;
    }

    public void edit(int x2, int y2) {
        if (x1 < x2 && y1 < y2) {
            width = (x2 - x1);
            height = (y2 - y1);
        } else if (x1 < x2 && y1 > y2) {
            y = y2;
            width = (Math.abs(x2 - x1));
            height = (Math.abs(y2 - y1));
        } else if (x2 < x1 & y2 < y1) {
            x = x2;
            y = y2;
            width = (Math.abs(x2 - x1));
            height = (Math.abs(y2 - y1));
        } else if (x2 < x1 & y2 > y1) {
            x = x2;
            width = (Math.abs(x2 - x1));
            height = (Math.abs(y2 - y1));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (!fill) {
            g.drawOval(x, y, width, height);
        } else {
            g.fillOval(x, y, width, height);
        }
    }

    @Override
    public boolean select(Point point) {
        java.awt.Rectangle r = new java.awt.Rectangle(this.x, this.y, width, height);
        return r.contains(point);
    }

    @Override
    public void move(Point pressPoint, Point dragPoint) {
        int pX = pressPoint.x;
        int pY = pressPoint.y;
        int dX = dragPoint.x;
        int dY = dragPoint.y;
        x = Math.abs(x - (pX - dX));
        y = Math.abs(y - (pY - dY));
    }

    @Override

    public void resize(boolean i) {
        if (i) {
            height += 2;
            width += 2;
        } else {
            height -= 2;
            width -= 2;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Oval o = (Oval) super.clone();
        o.y -= 20;
        o.x -= 20;
        return o;
    }

    @Override
    public void decorator(char s) {
        switch (s) {
            case 'l':
                break;
            case 'r':
                Rectangle t = new Rectangle(x, y, width, height, fill, color);
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
