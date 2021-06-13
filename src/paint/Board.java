/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import Shapes.Circle;
import Shapes.Line;
import Shapes.Oval;
import Shapes.Rectangle;
import Shapes.Shape;
import Shapes.ShapeCreator;
import Shapes.Square;
import Shapes.Triangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

    enum Modes {
        line, rectangle, sqaure, circle, triangle, oval, select, move, resize, nothing;
    }
    public ArrayList<Shape> shapes = new ArrayList<>();
    public ArrayList<Shape> removed = new ArrayList<>();
    Shape index = null;
    public Point changePoint;
    Color color = Color.black;
    public Modes mode = Modes.nothing;
    public static boolean trig = false;
    private static Board mainBoard;

    public Board() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public static Board getMainBoard() {
        return mainBoard;
    }

    public static void setMainBoard(Board mainBoard) {
        Board.mainBoard = mainBoard;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<Shape> shapesI = shapes.iterator();
        while (shapesI.hasNext()) {
            shapesI.next().draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        ShapeCreator shape = new ShapeCreator(GUI.fill.isSelected(), color);
        Point point = me.getPoint();
        switch (mode) {
            case line:
                Line l = (Line) shape.newShape('l', point.x, point.y);
                shapes.add(l);
                removed.clear();
                repaint();
                break;
            case rectangle:
                Rectangle r = (Rectangle) shape.newShape('r', point.x, point.y);
                r.setX1(point.x);
                r.setY1(point.y);
                shapes.add(r);
                removed.clear();
                repaint();
                break;
            case sqaure:
                Square s = (Square) shape.newShape('s', point.x, point.y);
                s.setX1(point.x);
                s.setY1(point.y);
                shapes.add(s);
                removed.clear();
                repaint();
                break;
            case oval:
                Oval o = (Oval) shape.newShape('o', point.x, point.y);
                o.setX1(point.x);
                o.setY1(point.y);
                shapes.add(o);
                removed.clear();
                repaint();
                break;
            case circle:
                Circle c = (Circle) shape.newShape('c', point.x, point.y);
                c.setX1(point.x);
                c.setY1(point.y);
                shapes.add(c);
                removed.clear();
                repaint();
                break;
            case triangle:
                Triangle t1;
                if (!trig) {
                    t1 = (Triangle) shape.newShape('t', point.x, point.y);
                    shapes.add(t1);
                    removed.clear();
                    trig = true;
                } else {
                    t1 = (Triangle) shapes.get(shapes.size() - 1);
                }
                t1.pressed(point.x, point.y);
                repaint();
                break;
            case select:
                for (int i = shapes.size() - 1; i >= 0; i--) {
                    if (shapes.get(i).select(point)) {
                        index = shapes.get(i);
                        break;
                    }
                }
                System.out.println(index);
                break;
            case move:
                changePoint = point;
                System.out.println(changePoint);
                break;
            case resize:
                changePoint = point;
                System.out.println(changePoint);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        Point point = me.getPoint();
        switch (mode) {
            case line:
                Line l = (Line) shapes.get(shapes.size() - 1);
                l.setX2(point.x);
                l.setY2(point.y);
                break;
            case rectangle:
                Rectangle r = (Rectangle) shapes.get(shapes.size() - 1);
                r.edit(point.x, point.y);
                break;
            case sqaure:
                Square s = (Square) shapes.get(shapes.size() - 1);
                s.edit(point.x, point.y);
                break;
            case oval:
                Oval o = (Oval) shapes.get(shapes.size() - 1);
                o.edit(point.x, point.y);
                break;
            case circle:
                Circle c = (Circle) shapes.get(shapes.size() - 1);
                c.edit(point.x, point.y);
                break;
            case move:
                index.move(changePoint, point);
                changePoint.setLocation(point);
                break;
            case resize:
                index.resize(changePoint.y < point.y);
                if (index instanceof Line) {
                    Line nl = (Line) index;
                    nl.setX2(point.x);
                    nl.setY2(point.y);
                }
                changePoint.setLocation(point);
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        Point point = me.getPoint();
        switch (mode) {
            case triangle:
                Triangle t1;
                if (trig) {
                    t1 = (Triangle) shapes.get(shapes.size() - 1);
                    t1.moved(point.x, point.y);
                    repaint();
                }
                break;
        }

    }

    public void undo() {
        try {
            removed.add(shapes.remove(shapes.size() - 1));
            repaint();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void redo() {
        try {
            shapes.add(removed.remove(removed.size() - 1));
            repaint();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void copy() {
        Shape cS = null;
        try {
            cS = (Shape) ((Shape) index).clone();
        } catch (CloneNotSupportedException ex) {
            JOptionPane.showMessageDialog(this, "Please Select a Shape First");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Please Select a Shape First");
        }
        if (cS != null) {
            shapes.add(cS);
            index = cS;
        }
        repaint();
    }

    public void deleteAll() {
        shapes.clear();
        repaint();
    }
}
