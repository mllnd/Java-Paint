package xyz.mllnd;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import xyz.mllnd.PaintedObject;

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

}
