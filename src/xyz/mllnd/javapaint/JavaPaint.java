package xyz.mllnd.javapaint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Vector;

public class JavaPaint extends Applet implements MouseListener, MouseMotionListener, ActionListener {

    JavaPaint() {
        addMouseListener(this);
        addMouseMotionListener(this);
        buttonAdder("Black", "black");
        buttonAdder("White", "white");
        buttonAdder("Blue", "blue");
        buttonAdder("Red", "red");
        buttonAdder("Yellow", "yellow");
        buttonAdder("Green", "green");
        buttonAdder("Small", "small");
        buttonAdder("Medium", "medium");
        buttonAdder("Big", "big");
        buttonAdder("Huge", "huge");
    }

    Vector<PaintObject> v = new Vector<PaintObject>(); // Full picture
    private PaintObject object; // The object that is currently being drawn
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

    private void buttonAdder(String label, String command) {
        Button btn = new Button(label);
        btn.setActionCommand(command);
        btn.addActionListener(this);
        add(btn);
    }

    public void actionPerformed(ActionEvent e) {
        String colorCommands[] = {"black", "white", "blue", "red", "yellow", "green"};
        Color colors[] = {Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};
        for (int i=0; i<colorCommands.length; i++) {
            if (colorCommands[i].equals(e.getActionCommand())) {
                color = colors[i];
            }
        }
        String sizeCommands[] = {"small", "medium", "big", "huge"};
        int sizes[] = {1, 5, 10, 50};
        for (int i=0; i<sizeCommands.length; i++) {
            if (sizeCommands[i].equals(e.getActionCommand())) {
                brushSize = sizes[i];
            }
        }
    }

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
