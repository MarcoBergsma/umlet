package com.baselet.control.basics;

import com.baselet.control.basics.geom.Dimension;
import com.baselet.control.basics.geom.Point;
import com.baselet.control.basics.geom.Rectangle;
import com.baselet.diagram.draw.helper.ColorOwn;
import com.baselet.element.interfaces.CursorOwn;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.css.Rect;

import java.awt.*;

import static org.junit.Assert.*;

public class ConverterTest {

    private static Rectangle umletRectangle;
    private static java.awt.Rectangle swingRectangle;

    private static Dimension umletDimension;
    private static java.awt.Dimension swingDimension;

    private static Point umletPoint;
    private static java.awt.Point swingPoint;

    private static ColorOwn umletColor;
    private static Color swingColor;

    private static CursorOwn umletCursor;
    private static Cursor swingCursor;

    private final static int X = 1;
    private final static int Y = 1;
    private final static int HEIGHT = 5;
    private final static int WIDTH = 5;

    @BeforeClass
    public static void setUp() throws Exception {
        //Rectangles
        umletRectangle = new Rectangle(X,Y,WIDTH,HEIGHT);
        swingRectangle = new java.awt.Rectangle();
        swingRectangle.setBounds(X, Y, WIDTH, HEIGHT);
        //Dimension
        umletDimension = new Dimension(WIDTH, HEIGHT);
        swingDimension = new java.awt.Dimension();
        swingDimension.setSize(WIDTH, HEIGHT);
        //Point
        umletPoint = new Point(X, Y);
        swingPoint = new java.awt.Point();
        swingPoint.setLocation(X, Y);
        //Color
        umletColor = new ColorOwn(255, 0, 0, 255);
        swingColor = new Color(255, 0, 0);
        //Cursor
        umletCursor = CursorOwn.E;
        swingCursor = new Cursor(11);
    }

    @Test
    public void convertUmletRectangleToSwingRectangle() {
        java.awt.Rectangle newRectangle = Converter.convert(umletRectangle);

        assertEquals(newRectangle, swingRectangle);
    }

    @Test
    public void convertSwingRectangleToUmletRectangle() {
        Rectangle newRectangle = Converter.convert(swingRectangle);

        assertEquals(newRectangle, umletRectangle);
    }

    @Test
    public void convertUmletDimensionToSwingDimension() {
        java.awt.Dimension newDimension = Converter.convert(umletDimension);

        assertEquals(newDimension, swingDimension);
    }

    @Test
    public void convertSwingDimensionToUmletDimension() {
        Dimension newDimension = Converter.convert(swingDimension);

        assertEquals(newDimension, umletDimension);
    }

    @Test
    public void convertUmletPointToSwingPoint() {
        java.awt.Point newPoint = Converter.convert(umletPoint);

        assertEquals(newPoint, swingPoint);
    }

    @Test
    public void convertSwingPointToUmletPoint() {
        Point newPoint = Converter.convert(swingPoint);

        assertEquals(newPoint, umletPoint);
    }

    @Test
    public void convertUmletColorToSwingColor() {
        Color newColor = Converter.convert(umletColor);

        assertEquals(newColor, swingColor);
    }

    @Test
    public void convertSwingColorToUmletColor() {
        ColorOwn newColor = Converter.convert(swingColor);

        assertEquals(newColor, umletColor);
    }

    @Test
    public void convertUmletCursorToSwingCursor() {
        Cursor newCursor = Converter.convert(umletCursor);

        assertEquals(newCursor.getName(), swingCursor.getName());
        assertEquals(newCursor.getType(), swingCursor.getType());
        assertEquals(newCursor.getClass(), swingCursor.getClass());
    }
}
