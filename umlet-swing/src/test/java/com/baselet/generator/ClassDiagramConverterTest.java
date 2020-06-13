package com.baselet.generator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassDiagramConverterTest {

    private List<Exception> exceptionList;
    private final static Exception[] EXCEPTIONS = {new ClassCastException(), new ArrayIndexOutOfBoundsException()
            , new NullPointerException(), new UnsupportedOperationException(), new NoSuchFieldException()};
    private final static String[] MESSAGES = {"Shoe is not an number", "Not enough shoes in the list", "No socks found for the shoes"
    ,"Shoes are not worn on the head", "Shoe do not come with an engine"};
    private String resultString = "";

    @Before
    public void setup() {
        exceptionList = new ArrayList<Exception>();
        for (int i = 0; i < 5 ; i++) {
            exceptionList.add(new Exception(MESSAGES[i], EXCEPTIONS[i]));
        }

        for (String message : MESSAGES) {
            if (!resultString.equals("")) {
                resultString += "\n";
            }
            resultString += message;
        }
    }

    @Test
    public void convertFailuresToString() {
        String exceptionString = ClassDiagramConverter.convertFailuresToString(exceptionList);

        assertEquals(resultString, exceptionString);
    }
}
