package main.java.xyz.mllnd.javapaint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Vector;

public class JavaPaint extends Applet implements MouseListener, MouseMotionListener {

    /**
     * Add listeners and buttons to the frame
     */
    JavaPaint() {
        addMouseListener(this);
        addMouseMotionListener(this);
        new PaintButton(this, "Black", "black");
        new PaintButton(this, "White", "white");
        new PaintButton(this, "Blue", "blue");
        new PaintButton(this, "Red", "red");
        new PaintButton(this, "Yellow", "yellow");
        new PaintButton(this, "Green", "green");
        new PaintButton(this, "Small", "small");
        new PaintButton(this, "Medium", "medium");
        new PaintButton(this, "Big", "big");
        new PaintButton(this, "Huge", "huge");
    }

    /**
     * The full "picture"
     */
    Vector<PaintObject> v = new Vector<PaintObject>();

    /**
     * A separate object, created on mousePressed event and "ended" on mouseReleased event
     */
    private PaintObject object;

    /**
     * Is user drawing currently?
     */
    boolean drawing = false;

    /**
     * Default color
     */
    Color color = Color.BLACK;

    /**
     * Default brush size
     */
    int brushSize = 1;


    /**
     * Main method, invoked on program start
     * @param args Arguments passed to method
     */
    public static void main(String[] args) {
        Frame frame = new Frame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setVisible(true);
        frame.setSize(dim.width, dim.height);
        frame.add(new JavaPaint());
        frame.setLocation(0, 0);
        System.out.println("Starting JavaPaint...");
        System.out.println("Created by Markkus Olaf Millend IVSB12, 2017");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.out.println("Thanks for trying out JavaPaint! Shutting down...");
                System.out.println("Created by Markkus Olaf Millend IVSB12, 2017");
                System.exit(0);
            }
        });
    }

    /**
     * Mouse pressed event listener
     * @param e MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case 1:
                object = new PaintObject(brushSize, color);
                drawing = true;
                System.out.println("Starting painting!");
                break;
            case 3:
                // Maybe deleting?
                break;
        }
    }

    /**
     * Mouse released event listener
     * @param e MouseEvent
     */
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

    /**
     * Mouse dragged event listener
     * @param e MouseEvent
     */
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

    /**
     * "Paints" the points on the frame
     * @param g Graphics
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i=0; i<v.size(); i++) {
            PaintObject m = v.get(i);
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
