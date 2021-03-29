package com.baselet.diagram.io;

import com.baselet.control.constants.Constants;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.diagram.CurrentDiagram;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.DrawPanel;
import com.baselet.element.interfaces.GridElement;
import com.baselet.element.old.element.Actor;
import com.baselet.gui.command.AddElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiagramFileHandlerTest {

    private DiagramHandler diagramHandler;
    private DiagramFileHandler fileHandler;
    private CurrentDiagram currentDiagram;
    private File file;


    @Before
    public void setUp(){
        Program.init("", RuntimeType.STANDALONE);
        file = new File("src/test/resources/diagram.uxf");
        diagramHandler = Mockito.mock(DiagramHandler.class);
        fileHandler = new DiagramFileHandler(diagramHandler, file);

    }

    @Test
    public void GivenDiagram_WhenSaveDiagram_ThenDiagramSavedAsUXF() throws IOException{
        //Given
        new FileOutputStream(file); // File exists.

        //When
        diagramHandler.setChanged(true);

        long currentTime = System.currentTimeMillis();

        fileHandler.doSave();

        // Then
        assertTrue(file.lastModified() > currentTime);


    }



}
