package xyz.mllnd;

import java.awt.*;
import java.util.Vector;

public class PaintedObject {
    Vector<Point> v;
    int brushSize;
    Color color;
    PaintedObject(int brush, Color col) {
        // The vector itself
        v = new Vector<Point>();
        brushSize = brush;
        color = col;
    }
}
