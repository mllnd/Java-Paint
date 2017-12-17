package xyz.mllnd.javapaint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Vector;

public class JavaPaint extends Applet implements MouseListener, MouseMotionListener, ActionListener {

    /**
     * Add listeners and buttons to the frame
     */
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
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    /**
     * Method for adding buttons to the frame
     * @param label Button label text
     * @param command The "command" which is behind a button
     */
    private void buttonAdder(String label, String command) {
        Button btn = new Button(label);
        btn.setActionCommand(command);
        btn.addActionListener(this);
        add(btn);
    }

    /**
     * Button click listener, changes brush size and color
     * @param e ActionEvent
     */
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
