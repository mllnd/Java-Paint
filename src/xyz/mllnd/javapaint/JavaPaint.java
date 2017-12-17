package xyz.mllnd.javapaint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Vector;

public class JavaPaint extends Applet implements MouseListener, MouseMotionListener {

    JavaPaint() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    Vector<PaintedObject> v = new Vector<PaintedObject>(); // Full picture
    private PaintedObject object; // The object that is currently being drawn
    boolean drawing = false; // Drawing status boolean
    Color color = Color.BLACK; // Initial color
    int brushSize = 1; // Initial brush size

    public static void main(String[] args) {
        Frame frame = new Frame();
        // Get screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setVisible(true);
        frame.setSize(dim.width, dim.height);
        frame.add(new JavaPaint());
        frame.setLocation(0, 0);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case 1:
                object = new PaintedObject(brushSize, color);
                drawing = true;
                System.out.println("Starting painting!");
                break;
            case 3:
                // Maybe deleting?
                break;
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (drawing) {
            drawing = false;
            if (object.v.size() > 1) {
                v.add(object);
                repaint(); // Refresh
            }
            System.out.println("Stopping painting! There are "+object.v.size()+" points in the painted object.");
        }
    }
    public void mouseDragged(MouseEvent e) {
        if (drawing) {
            object.v.add(e.getPoint());
            repaint(); // Refresh
        }
    }

    /* Unused listeners */
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i=0; i<v.size(); i++) {
            PaintedObject m = v.get(i);
            g2.setStroke(new BasicStroke(m.brushSize));
            g2.setColor(m.color);
            Point lastpoint = m.v.get(0); // Last point
            for (int j=1; j<m.v.size(); j++) {
                Point currentpoint = m.v.get(j);
                g2.draw(new Line2D.Float(lastpoint.x, lastpoint.y, currentpoint.x, currentpoint.y));
                lastpoint = currentpoint; // Set last point to current point
            }
        }
        if (drawing && object.v.size() > 1) {
            g2.setStroke(new BasicStroke(object.brushSize));
            g2.setColor(object.color);
            Point lastpoint = object.v.get(0);
            for (int j=1; j<object.v.size(); j++) {
                Point currentpoint = object.v.get(j);
                g2.draw(new Line2D.Float(lastpoint.x, lastpoint.y, currentpoint.x, currentpoint.y));
                lastpoint = currentpoint;
            }
        }
    }

}
