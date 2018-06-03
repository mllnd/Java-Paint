package xyz.mllnd.javapaint;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public final class PaintObject {

    /**
     * The vector itself
     */
    Vector<Point> v;

    /**
     * Object brush size
     */
    int brushSize;

    /**
     * Object color
     */
    Color color;

    PaintObject(int brush, Color col) {
        v = new Vector<Point>();
        brushSize = brush;
        color = col;
    }
}
