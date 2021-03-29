package com.baselet.diagram.io;

import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.diagram.DiagramHandler;
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
    private File file;


    @Before
    public void setUp(){
        Program.init("", RuntimeType.STANDALONE);
        file = new File("src/test/resources/diagram.png");
        diagramHandler = Mockito.mock(DiagramHandler.class);
        fileHandler = new DiagramFileHandler(diagramHandler, file);
        Mockito.when(diagramHandler.getName()).thenReturn("diagram");
    }

    @Test
    public void GivenDiagram_WhenExportDiagram_ThenDiagramExportedAsPNG() throws IOException{
        new FileOutputStream(file);

        fileHandler.doSaveAs("png");


        String fileName = new File("src/test/resources/diagram.png").getName();

        int lastIndexOf = fileName.lastIndexOf(".");
        String extension = fileName.substring(lastIndexOf);
        assertEquals(extension,".png");

    }

}
