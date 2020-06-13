package com.baselet.standalone;

import com.baselet.control.enums.RuntimeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

public class MainStandaloneTest {

    private File file;
    private final String PATH = "src/test/resources/com/baselet/standalone";

    @Before
    public void setup() {
        file = new File(PATH + "/in_newAllInOne.uxf");
    }

    @Test
    public void assertExistenceOfConvertedUxfFileToPng() {
        //Needs to be initialized
        MainStandalone.readBuildInfoAndInitVersion(RuntimeType.STANDALONE);
        MainStandalone.doConvert(file, "png", PATH);
        assertTrue(new File(PATH + "/in_newAllInOne.png").exists());
    }

    @After
    public void teardown() {
        //Deletes the new file if the conversion was successful to make sure redoing the test gives the same result
        File newFile = new File(PATH + "/in_newAllInOne.png");
        if (newFile.exists()) {
            newFile.delete();
        }
    }
}
