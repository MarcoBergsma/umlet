package com.baselet.diagram;

import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.element.interfaces.Diagram;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.*;

public class FontHandlerTest {

    private DiagramHandler diagramHandler;
    private FontHandler fontHandler;

    @Before
    public void setup() {
        Program.init("1", RuntimeType.STANDALONE);
        diagramHandler = new DiagramHandler(new File("src/test/resources/com/baselet/diagram/in_newAllInOne.uxf"));
        fontHandler = new FontHandler(diagramHandler);
    }

    @Test
    public void setDiagramDefaultFontFamilyWithValidFont() {
        fontHandler.setDiagramDefaultFontFamily("Monospaced");
        Font font = fontHandler.getFont();

        assertEquals("Monospaced", font.getName());
    }

    @Test
    public void setDiagramDefaultFontFamilyWithInvalidFont() {
        fontHandler.setDiagramDefaultFontFamily("Jokerman");
        Font font = fontHandler.getFont();

        //SansSerif is the default font. So if the set didn't work it should still be default.
        assertEquals("SansSerif", font.getName());
    }
}
