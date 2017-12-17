package xyz.mllnd.javapaint;

import java.awt.*;
import java.util.Vector;

public class PaintObject {
    Vector<Point> v;
    int brushSize;
    Color color;
    PaintObject(int brush, Color col) {
        // The vector itself
        v = new Vector<Point>();
        brushSize = brush;
        color = col;
    }
}
