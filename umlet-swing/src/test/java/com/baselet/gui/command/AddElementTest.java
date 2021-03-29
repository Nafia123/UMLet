package com.baselet.gui.command;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.baselet.control.constants.Constants;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.DrawPanel;
import com.baselet.element.interfaces.GridElement;
import com.baselet.element.old.element.Actor;
import org.junit.Before;
import org.junit.Test;

import com.github.javaparser.JavaParser;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddElementTest {


    private DiagramHandler diagramHandler;


    @Before
    public void setUp(){
        diagramHandler = Mockito.mock(DiagramHandler.class);
        Program.init("", RuntimeType.STANDALONE);
        Mockito.when(diagramHandler.getDrawPanel()).thenReturn(new DrawPanel(diagramHandler,true));
        Mockito.when(diagramHandler.getGridSize()).thenReturn(Constants.DEFAULTGRIDSIZE); //Stops division by zero on execute
    }


    @Test
    public void GivenElement_WhenAddElement_ThenElementOnPanel(){
        // Given
        GridElement gridElement = new Actor(); //Create new grid
        AddElement addElement = new AddElement(gridElement,5,5,false); //Create element with grid

        // When
        addElement.execute(diagramHandler); // Add new Element to Diagram

        //Then
        List<GridElement> result = diagramHandler.getDrawPanel().getGridElements(); // Get List of elements in Diagram
        assertTrue(result.contains(gridElement)); // Check if GridElement exist in Diagram

    }

    @Test
    public void GivenElements_WhenAddMultipleElements_ThenElementsOnPanel() {
        // Given
        GridElement gridElement1 = new Actor(); // Create first grid with actor element
        AddElement addElement1 = new AddElement(gridElement1, 5, 5, false); // Create first element with first grid
        GridElement gridElement2 = new Actor(); // Create second grid with actor element
        AddElement addElement2 = new AddElement(gridElement2, 5, 5, false); // Create second element with second grid

        // When
        addElement1.execute(diagramHandler);
        addElement2.execute(diagramHandler);

        // Then
        List<GridElement> expected = new ArrayList<GridElement>();
        expected.add(gridElement1);
        expected.add(gridElement2);
        List<GridElement> result = diagramHandler.getDrawPanel().getGridElements();
        assertTrue(result.containsAll(expected));

    }

    @Test
    public void WhenUndo_ThenAddedElementsReverts() {
        // Given
        GridElement gridElement = new Actor(); //Create new grid
        AddElement addElement = new AddElement(gridElement,5,5,false); //Create element with grid

        // When
        addElement.execute(diagramHandler); // Add new Element to Diagram
        addElement.undo(diagramHandler);

        //Then
        List<GridElement> result = diagramHandler.getDrawPanel().getGridElements(); // Get List of elements in Diagram
        assertFalse(result.contains(gridElement)); // Check if result does not contain the gridElement that was added before the undo

    }
}
