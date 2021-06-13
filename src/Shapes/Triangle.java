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

public class Triangle extends Shape {

    public int x_axis[] = new int[3];
    public int y_axis[] = new int[3];
    int trig = 0;

    Context max = new Context(new OperationMax());
    Context min = new Context(new OperationMin());

    public Triangle(boolean fill, Color color) {
        super(fill, color);
    }


    public void pressed(int x, int y) {
        trig++;
        switch (trig) {
            case 1:
                x_axis[0] = x;
                y_axis[0] = y;
                x_axis[1] = x;
                y_axis[1] = y;
                x_axis[2] = x;
                y_axis[2] = y;
                break;
            case 2:
                x_axis[1] = x;
                y_axis[1] = y;
                x_axis[2] = x;
                y_axis[2] = y;
                break;
            case 3:
                x_axis[2] = x;
                y_axis[2] = y;
                trig = 0;
                Board.trig = false;
                break;
        }
    }

    public void moved(int x, int y) {
        switch (trig) {
            case 1:
                x_axis[1] = x;
                y_axis[1] = y;
                x_axis[2] = x;
                y_axis[2] = y;
                break;
            case 2:
                x_axis[2] = x;
                y_axis[2] = y;
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (!fill) {
            g.drawPolygon(x_axis, y_axis, 3);
        } else {
            g.fillPolygon(x_axis, y_axis, 3);
        }

    }

    @Override
    public boolean select(Point point) {
        int minX = min.executeStrategy3(x_axis[0], x_axis[1], x_axis[2]);
        int minY = min.executeStrategy3(y_axis[0], y_axis[1], y_axis[2]);
        int maxX = max.executeStrategy3(x_axis[0], x_axis[1], x_axis[2]);
        int maxY = max.executeStrategy3(y_axis[0], y_axis[1], y_axis[2]);
        java.awt.Rectangle r = new java.awt.Rectangle(minX, minY, maxX - minX, maxY - minY);
        return r.contains(point);
    }

    @Override
    public void move(Point pressPoint, Point dragPoint) {
        int pX = pressPoint.x;
        int pY = pressPoint.y;
        int dX = dragPoint.x;
        int dY = dragPoint.y;
        for (int i = 0; i < 3; i++) {
            x_axis[i] = Math.abs(x_axis[i] - (pX - dX));
            y_axis[i] = Math.abs(y_axis[i] - (pY - dY));
        }
    }

    @Override
    public void resize(boolean i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        Triangle t = (Triangle) super.clone();
        t.y_axis = (int[]) y_axis.clone();
        t.x_axis = (int[]) x_axis.clone();
        for (int i = 0; i < 3; i++) {
            t.y_axis[i] -= 20;
            t.x_axis[i] -= 20;
        }
        return t;
    }

    @Override
    public void decorator(char s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
