package xyz.mllnd.javapaint;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PaintButton extends Button implements ActionListener {

    private final String label;
    private final String command;
    private final JavaPaint javaPaint;

    public PaintButton(JavaPaint javaPaint, String label, String command) {
        super(label);
        this.label = label;
        this.javaPaint = javaPaint;
        this.command = command;
        setActionCommand(this.command);
        addActionListener(this);
        javaPaint.add(this);
    }

    /**
     * Button click listener, changes brush size and color
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String colorCommands[] = {"black", "white", "blue", "red", "yellow", "green"};
        Color colors[] = {Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};
        for (int i=0; i<colorCommands.length; i++) {
            if (colorCommands[i].equals(e.getActionCommand())) {
                javaPaint.color = colors[i];
            }
        }
        String sizeCommands[] = {"small", "medium", "big", "huge"};
        int sizes[] = {1, 5, 10, 50};
        for (int i=0; i<sizeCommands.length; i++) {
            if (sizeCommands[i].equals(e.getActionCommand())) {
                javaPaint.brushSize = sizes[i];
            }
        }
    }
}
