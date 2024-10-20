package edu.grinnell.csc207.util;


import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


class MyTests {
    
    /**
     * 
     */
    @Test
    public void testGet() {
        Matrix<String> strMat = new MatrixV0<String>(10, 10, "Train");
        Matrix<String> strHybridMat = new MatrixV0<String>(10, 10, "Train" + "Track");
        Matrix<String> strNullMat = new MatrixV0<String>(10, 10);

        assertEquals("Train", strMat.get(0, 0), "Can acces a string from a matrix");
        assertEquals("Train" + "Track", strHybridMat.get(0, 0), "Can acces a string from a matrix");
        assertEquals(null, strNullMat.get(0, 0), "Can access null form a matrix");

    }

    /**
     * 
     */
    @Test
    public void testSet() {
        Matrix<String> strMat = new MatrixV0<String>(10, 10, "Train");
        Matrix<String> strHybridMat = new MatrixV0<String>(10, 10, "Train" + "Track");
        Matrix<String> strNullMat = new MatrixV0<String>(10, 10);

        strNullMat.set(0, 0, strHybridMat.get(0, 0));
        strMat.set(0, 0, null);
        strNullMat.set(1, 1, "NEW");
        strMat.set(1, 0, "Updated");
        strNullMat.set(3, 0, strHybridMat.get(0, 0));

        assertEquals("TrainTrack", strNullMat.get(0, 0), "Can set a value from another matrix");
        assertEquals(null, strMat.get(0, 0), "Can set a value to null");
        assertEquals("NEW", strNullMat.get(1, 1), "Can set a null to a new string");
        assertEquals("Updated", strMat.get(1, 0), "Can set an old value to a new value");
        assertEquals("TrainTrack", strNullMat.get(3, 0), "Can set a value to an appended string");
    }

    /**
     * 
     */
    @Test
    public void testHeight() {
        Matrix<String> strMat = new MatrixV0<String>(10, 13, "Train");
        Matrix<String> strHybridMat = new MatrixV0<String>(10, 1, "Train" + "Track");
        Matrix<String> strNullMat = new MatrixV0<String>(10, 3);

        assertEquals(13, strMat.height(), "Height of normal matrix");
        assertEquals(1, strHybridMat.height(), "Height of matrix of appended strings");
        assertEquals(3, strNullMat.height(), "Height of matrix of null");

    }

    /**
     * 
     */
    @Test
    public void testWidth() {
        Matrix<String> strMat = new MatrixV0<String>(1, 13, "Train");
        Matrix<String> strHybridMat = new MatrixV0<String>(3, 1, "Train" + "Track");
        Matrix<String> strNullMat = new MatrixV0<String>(14, 3);

        assertEquals(1, strMat.width(), "Width of normal matrix");
        assertEquals(3, strHybridMat.width(), "Width of matrix of appended strings");
        assertEquals(14, strNullMat.width(), "Width of matrix of null");
    }

    /**
     * 
     */
    @Test
    public void testInsertRow() {
        Matrix<String> strMat = new MatrixV0<String>(1, 3);

        strMat.insertRow(1);
        assertMatrixEquals(new String[][] {{null}, {null}, {null}, {null}}, strMat, "Insert Row in middle of matrix");
        strMat.insertRow(0);
        assertMatrixEquals(new String[][] {{null}, {null}, {null}, {null}, {null}}, strMat, "Insert Row in middle of matrix");
        strMat.insertRow(5);
        assertMatrixEquals(new String[][] {{null}, {null}, {null}, {null}, {null}, {null}}, strMat, "Insert Row At the end of matrix");
    }

    /**
     * 
     */
    @Test
    public void testInsertRowArr() throws ArraySizeException {

        Matrix<String> strFillMat = new MatrixV0<String>(1, 3, "dress");
        String[] strArr = new String[] {null};

        strFillMat.insertRow(1, strArr);
        assertMatrixEquals(new String[][] {{"dress"}, {null}, {"dress"}, {"dress"}}, strFillMat, "Insert Row in middle of matrix");
        strFillMat.insertRow(0, strArr);
        assertMatrixEquals(new String[][] {{null}, {"dress"}, {null}, {"dress"}, {"dress"}}, strFillMat, "Insert Row in middle of matrix");
        strFillMat.insertRow(5, strArr);
        assertMatrixEquals(new String[][] {{null}, {"dress"}, {null}, {"dress"}, {"dress"}, {null}}, strFillMat, "Insert Row At the end of matrix");
    }

    /**
     * 
     */
    @Test
    public void testInsertCol() {
        Matrix<String> strMat = new MatrixV0<String>(3, 1);

        strMat.insertCol(1);
        assertMatrixEquals(new String[][] {{null, null, null, null}}, strMat, "Insert Colum in the Middle of Matrix");
        strMat.insertCol(0);
        assertMatrixEquals(new String[][] {{null, null, null, null, null}}, strMat, "Insert Colum at the Front of Matrix");
        strMat.insertCol(5);
        assertMatrixEquals(new String[][] {{null, null, null, null, null, null}}, strMat, "Insert Colum at the End of Matrix");
    }

    /**
     * @throws ArraySizeException 
     * 
     */
    @Test
    public void testInsertColArr() throws ArraySizeException {
        Matrix<String> strMat = new MatrixV0<String>(3, 1);
        String[] strArr = new String[] {"Impress"};

        strMat.insertCol(1, strArr);
        assertMatrixEquals(new String[][] {{null, "Impress", null, null}}, strMat, "Insert Colum in the Middle of Matrix");
        strMat.insertCol(0, strArr);
        assertMatrixEquals(new String[][] {{"Impress", null, "Impress", null, null}}, strMat, "Insert Colum at the Front of Matrix");
        strMat.insertCol(5, strArr);
        assertMatrixEquals(new String[][] {{"Impress", null, "Impress", null, null, "Impress"}}, strMat, "Insert Colum at the End of Matrix");

    }

    /**
     * @throws ArraySizeException 
     * 
     */
    @Test
    public void testDeleteRow() throws ArraySizeException {
        Matrix<String> strMat = new MatrixV0<String>(1, 3, "dress");
        String[] strArr = new String[] {null};

        strMat.insertRow(1, strArr);
        strMat.insertRow(0, strArr);
        strMat.insertRow(5, strArr);

        strMat.deleteRow(5);
        assertMatrixEquals(new String[][] {{null}, {"dress"}, {null}, {"dress"}, {"dress"}}, strMat, "Insert Row At the end of matrix");
    }

    /**
     * @throws ArraySizeException 
     * 
     */
    @Test
    public void testDeleteCol() throws ArraySizeException {
        Matrix<String> strMat = new MatrixV0<String>(3, 1);
        String[] strArr = new String[] {"Dress"};

        strMat.insertCol(1, strArr);
        strMat.insertCol(0, strArr);
        strMat.insertCol(5, strArr);

        strMat.deleteCol(5);
        assertMatrixEquals(new String[][] {{"Dress", null, "Dress", null, null}}, strMat, "Remove colum at the beginning of Matrix");
        strMat.deleteCol(1);
        assertMatrixEquals(new String[][] {{"Dress", "Dress", null, null}}, strMat, "Remove colum at the beginning of Matrix");
        strMat.deleteCol(0);
        assertMatrixEquals(new String[][] {{"Dress", null, null}}, strMat, "Remove colum at the beginning of Matrix");

    }

    /**
     * 
     */
    @Test
    public void testFillReg() {
        Matrix<String> strMat = new MatrixV0<String>(5, 5);

        strMat.fillRegion(1, 1, 4, 4, "HUH?");
        assertMatrixEquals(new String[][] 
        {{null, null, null, null, null},
         {null, "HUH?", "HUH?", "HUH?", null},
         {null, "HUH?", "HUH?", "HUH?", null},
         {null, "HUH?", "HUH?", "HUH?", null},
         {null, null, null, null, null}}, 
        strMat, "Filled the center of a Matrix");

        assertThrows(IndexOutOfBoundsException.class,
        () -> {strMat.fillRegion(5, 0, 0, 0, null);},
        "Correct Exception IndexOutOfBoundsException Thrown when Index values are out of matrix.");

    }

    /**
     * 
     */
    @Test
    public void testFillLine() {
        Matrix<String> strMat = new MatrixV0<String>(5, 5);

        strMat.fillRegion(1, 1, 4, 4, "HUH?");
        strMat.fillLine(0, 0, 1, 1, 5, 5, "Nothing");

        assertMatrixEquals(new String[][] 
        {{"Nothing", null, null, null, null},
         {null, "Nothing", "HUH?", "HUH?", null},
         {null, "HUH?", "Nothing", "HUH?", null},
         {null, "HUH?", "HUH?", "Nothing", null},
         {null, null, null, null, "Nothing"}}, 
        strMat, "Can Create a diagonal line.");
    }

        /**
     * 
     */
    @Test
    public void testEquals() {
        Matrix<String> strMat = new MatrixV0<String>(5, 5);
        Matrix<String> strMatDos = new MatrixV0<String>(5, 5);

        assertTrue(strMat.equals(strMatDos), "Two matrix of the same construction are equal");
    }
}
