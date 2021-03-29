package com.baselet.gui.command;

import com.baselet.control.constants.Constants;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.DrawPanel;
import com.baselet.element.interfaces.GridElement;
import com.baselet.element.old.element.Actor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RemoveElementTest {


    private DiagramHandler diagramHandler;
    private RemoveElement removeElement;


    @Before
    public void setUp(){
        diagramHandler = Mockito.mock(DiagramHandler.class);
        Program.init("", RuntimeType.STANDALONE);
        Mockito.when(diagramHandler.getDrawPanel()).thenReturn(new DrawPanel(diagramHandler,true));
        Mockito.when(diagramHandler.getGridSize()).thenReturn(Constants.DEFAULTGRIDSIZE);
    }


    @Test
    public void GivenDiagramWithElement_WhenRemoveElement_ThenEmptyDiagram(){
        //Given
        GridElement gridElement = new Actor(); //Create new grid
        AddElement addElement = new AddElement(gridElement,5,5,false); //Create element with grid
        addElement.execute(diagramHandler); // Add Element to Diagram.

        //When
        removeElement = new RemoveElement(gridElement);
        removeElement.execute(diagramHandler);

        //Then
        List<GridElement> elements = diagramHandler.getDrawPanel().getGridElements();
        assertTrue(elements.isEmpty());
    }

    @Test
    public void GivenDiagramWithMultipleElements_WhenRemoveElements_ThenEmptyDiagram(){
        //Given
        GridElement gridElement1 = new Actor(); //Create new grid
        AddElement addElement1 = new AddElement(gridElement1,5,5,false); //Create element with grid
        GridElement gridElement2 = new Actor(); //Create new grid
        AddElement addElement2 = new AddElement(gridElement2,5,5,false); //Create element with grid
        GridElement gridElement3 = new Actor(); //Create new grid
        AddElement addElement3 = new AddElement(gridElement3,5,5,false); //Create element with grid

        addElement1.execute(diagramHandler); // Add Element 1 to Diagram.
        addElement2.execute(diagramHandler); // Add Element 2 to Diagram.
        addElement3.execute(diagramHandler); // Add Element 3 to Diagram.

        //When
        List<GridElement> allElements = diagramHandler.getDrawPanel().getGridElements();

        removeElement = new RemoveElement(allElements);
        removeElement.execute(diagramHandler);

        //Then
        List<GridElement> elementsAfterDeletion = diagramHandler.getDrawPanel().getGridElements();
        assertTrue(elementsAfterDeletion.isEmpty());
    }



}
