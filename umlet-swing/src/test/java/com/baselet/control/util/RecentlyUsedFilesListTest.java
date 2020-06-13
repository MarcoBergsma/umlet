package com.baselet.control.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RecentlyUsedFilesListTest {

    private List<String> filenames;
    private final String[] OPTIONS = {"file_a", "file_b", "file_c", "file_c", "file_d", "file_e", "file_f", "file_g", "file_h", "file_i", "file_j"};
    private RecentlyUsedFilesList recentlyUsedFilesList;

    @Before
    public void setup() {
        filenames = new ArrayList<String>();
        filenames.addAll(Arrays.asList(OPTIONS));
        recentlyUsedFilesList = new RecentlyUsedFilesList();
    }

    @Test
    /*
     * RecentlyUsedFileList should not allow multiple identical entries.
     */
    public void assertTrueNoDuplicateEntries() {
        recentlyUsedFilesList.add(filenames.get(0));
        recentlyUsedFilesList.add(filenames.get(0));

        assertEquals(1, recentlyUsedFilesList.getList().size());
    }

    @Test
    /*
     * Inserting a value should place the value at the start of the list
     */
    public void assertTrueLastOneInsertedIsFirstEntry() {
        recentlyUsedFilesList.add(filenames.get(0));
        recentlyUsedFilesList.add(filenames.get(1));

        assertEquals(filenames.get(1), recentlyUsedFilesList.getList().get(0));
    }

    @Test
    /*
     * RecentlyUsedFileList can have a maximum of 10 entries. An entry should be deleted if 11 is reached
     * */
    public void assertMaxListLengthIsRespected() {
        //Add eleven entries to the list
        for (String filename: filenames) {
            recentlyUsedFilesList.add(filename);
        }

        assertEquals(10, recentlyUsedFilesList.getList().size());
    }
}
