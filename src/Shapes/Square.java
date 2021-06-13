/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import Strategy.Context;
import Strategy.OperationMax;
import Strategy.OperationMin;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import paint.Board;

public class Square extends Shape {

    int x_axis[] = new int[4];
    int y_axis[] = new int[4];
    private int x, y;
    private int x1, y1;

    Context max = new Context(new OperationMax());
    Context min = new Context(new OperationMin());

    public Square(int x, int y, Boolean fill, Color color) {
        super(fill, color);
        this.x = x;
        this.y = y;
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

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public Boolean getFill() {
        return fill;
    }

    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    public void edit(int x2, int y2) {
        if (Math.abs(x2 - x1) > Math.abs(y2 - y1)) {
            if (x1 < x2 && y1 < y2) {
                x_axis[0] = x1;
                x_axis[1] = x2;
                x_axis[2] = x2;
                x_axis[3] = x1;
                y_axis[0] = y1;
                y_axis[1] = y1;
                y_axis[2] = y1 + x2 - x1;
                y_axis[3] = y1 + x2 - x1;
            } else if (x1 < x2 && y1 > y2) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x2;
                x_axis[3] = x2;
                y_axis[0] = y1;
                y_axis[1] = y1 - x2 + x1;
                y_axis[2] = y1 - x2 + x1;
                y_axis[3] = y1;
            } else if (x2 < x1 & y2 < y1) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x2;
                x_axis[3] = x2;
                y_axis[0] = y1;
                y_axis[1] = y1 + x2 - x1;
                y_axis[2] = y1 - x1 + x2;
                y_axis[3] = y1;
            } else if (x2 < x1 & y2 > y1) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x2;
                x_axis[3] = x2;
                y_axis[0] = y1;
                y_axis[1] = y1 + x1 - x2;
                y_axis[2] = y1 + x1 - x2;
                y_axis[3] = y1;
            }
        } else {
            if (x1 < x2 && y1 < y2) {
                x_axis[0] = x1;
                x_axis[1] = x1 + y2 - y1;
                x_axis[2] = x1 + y2 - y1;
                x_axis[3] = x1;
                y_axis[0] = y1;
                y_axis[1] = y1;
                y_axis[2] = y2;
                y_axis[3] = y2;
            } else if (x1 < x2 && y1 > y2) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x1 + y1 - y2;
                x_axis[3] = x1 + y1 - y2;
                y_axis[0] = y1;
                y_axis[1] = y2;
                y_axis[2] = y2;
                y_axis[3] = y1;
            } else if (x2 < x1 & y2 < y1) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x1 - y1 + y2;
                x_axis[3] = x1 - y1 + y2;
                y_axis[0] = y1;
                y_axis[1] = y2;
                y_axis[2] = y2;
                y_axis[3] = y1;
            } else if (x2 < x1 & y2 > y1) {
                x_axis[0] = x1;
                x_axis[1] = x1;
                x_axis[2] = x1 - y2 + y1;
                x_axis[3] = x1 - y2 + y1;
                y_axis[0] = y1;
                y_axis[1] = y2;
                y_axis[2] = y2;
                y_axis[3] = y1;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (!fill) {
            g.drawPolygon(x_axis, y_axis, 4);
        } else {
            g.fillPolygon(x_axis, y_axis, 4);
        }
    }

    @Override
    public boolean select(Point point) {
        int minX = min.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
        int minY = min.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
        int maxX = max.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
        int maxY = max.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
        java.awt.Rectangle r = new java.awt.Rectangle(minX, minY, maxX - minX, maxY - minY);
        return r.contains(point);
    }

    @Override
    public void move(Point pressPoint, Point dragPoint) {
        int pX = pressPoint.x;
        int pY = pressPoint.y;
        int dX = dragPoint.x;
        int dY = dragPoint.y;
        for (int i = 0; i < 4; i++) {
            x_axis[i] = Math.abs(x_axis[i] - (pX - dX));
            y_axis[i] = Math.abs(y_axis[i] - (pY - dY));
        }
    }

    @Override
    public void resize(boolean i) {
        int minX = min.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
        int minY = min.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
        int maxX = max.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
        int maxY = max.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
        if (i) {
            x1 = minX - 5;
            y1 = minY - 5;
            edit(maxX + 5, maxY + 5);
        } else {
            x1 = minX + 5;
            y1 = minY + 5;
            edit(maxX - 5, maxY - 5);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Square s = (Square) super.clone();
        s.y_axis = (int[]) y_axis.clone();
        s.x_axis = (int[]) x_axis.clone();
        for (int i = 0; i < 4; i++) {
            s.y_axis[i] -= 20;
            s.x_axis[i] -= 20;
        }
        return s;
    }

    @Override
    public void decorator(char s) {
        switch (s) {
            case 'l':
                break;
            case 'r':
                break;
            case 't':
                break;
            case 's':
                break;
            case 'o':
                break;
            case 'c':
                int minX = min.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
                int minY = min.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
                int maxX = max.executeStrategy4(x_axis[0], x_axis[1], x_axis[2], x_axis[3]);
                int maxY = max.executeStrategy4(y_axis[0], y_axis[1], y_axis[2], y_axis[3]);
                Circle c = new Circle(minX, minY, maxX - minX, maxY - minY, fill, color);
                Board.getMainBoard().shapes.add(c);
                Board.getMainBoard().repaint();
                break;
        }
    }

}
