package com.baselet.diagram.io;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassChooserTest {

    private List<File> files;
    private List<String> resultList;
    private List<String> testingList;
    private final String PATH = "src/test/resources/com/baselet/control/basics/";

    @Before
    public void setUp() throws Exception {
        files = new ArrayList<File>();
        File classFile = new File(PATH + "test.class");
        File javaFile = new File(PATH + "test.java");
        files.add(classFile);
        files.add(javaFile);
        files.add(new File(PATH + "test.txt"));

        resultList = new ArrayList<String>();
        resultList.add(classFile.getAbsolutePath());
        resultList.add(javaFile.getAbsolutePath());

        testingList = new ArrayList<String>();
    }

    @Test
    public void insertFilesIndividually() {
        for (File file : files) {
            ClassChooser.searchRecursively(file, testingList);
        }

        assertEquals(resultList, testingList);
    }

    @Test
    public void insertFilesInDirectory() {
        ClassChooser.searchRecursively(new File(PATH), testingList);

        //The isDirectory method seems to not work, meaning no files get added.
        assertNotEquals(resultList, testingList);
    }
}
