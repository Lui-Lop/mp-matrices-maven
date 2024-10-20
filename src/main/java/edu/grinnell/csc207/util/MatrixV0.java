package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *            The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * integer that stores hieght of matrix.
   */
  int height;

  /**
   * stores integer for width of matrix.
   */
  int width;

  /**
   * 2D array that houses information for matrix.
   */
  T[][] matri;

  /**
   * Default value for the matrix.
   */
  T def;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param wdth
   *              The width of the matrix.
   * @param hight
   *              The height of the matrix.
   * @param defa
   *              The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *                                    If either the width or height are
   *                                    negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int wdth, int hight, T defa) {
    Object[][] matrix = new Object[hight][wdth];
    this.matri = (T[][]) matrix;
    this.width = wdth;
    this.height = hight;
    this.def = defa;

    if (wdth < 0 || hight < 0) {
      throw new NegativeArraySizeException();
    } // if dimensions negative throw exception

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        this.matri[row][col] = this.def;
      } // loop over colum
    } // loop over row
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param wdth
   *              The width of the matrix.
   * @param hight
   *              The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *                                    If either the width or height are
   *                                    negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int wdth, int hight) {
    Object[][] matrix = new Object[hight][wdth];
    this.height = hight;
    this.width = wdth;
    this.matri = (T[][]) matrix;
    this.def = null;

    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if negative dimension, throw exception

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        this.matri[row][col] = this.def;
      } // loop over colum
    } // loop over row
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *            The row of the element.
   * @param col
   *            The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *                                   If either the row or column is out of
   *                                   reasonable bounds.
   */
  public T get(int row, int col) {
    if (row > this.height || col > this.width || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      return this.matri[row][col];
    } // if out of bounds, throw exception, else return value
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *            The row of the element.
   * @param col
   *            The column of the element.
   * @param val
   *            The value to set.
   *
   * @throws IndexOutOfBoundsException
   *                                   If either the row or column is out of
   *                                   reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row > this.height || col > this.width || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      this.matri[row][col] = val;
    } // checks if index out of bounds, if so throw exception
    return;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *            The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than the
   *                                   height.
   */
  public void insertRow(int row) {
    if (row > this.height || row < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      MatrixV0<T> dup = new MatrixV0<>(this.width, this.height + 1);

      for (int curRow = 0; curRow < dup.height; curRow++) {
        for (int col = 0; col < dup.width; col++) {
          if (curRow < row) {
            dup.matri[curRow][col] = this.matri[curRow][col];
          } else if (curRow == row) {
            dup.matri[curRow][col] = this.def;
          } else if (curRow > row) {
            dup.matri[curRow][col] = this.matri[curRow - 1][col];
          } // if at colum to be added, insert new row, else write from original
        } // loop over colums
      } // loop over rows
      this.height = dup.height;
      this.matri = dup.matri;
    } // checks if index is out of matrix, if so throw exception
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *             The number of the row to insert.
   * @param vals
   *             The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than the
   *                                   height.
   * @throws ArraySizeException
   *                                   If the size of vals is not the same as the
   *                                   width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (vals.length != this.width) {
      throw new ArraySizeException();
    } else if (row < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      MatrixV0<T> dup = new MatrixV0<>(this.width, this.height + 1);

      for (int curRow = 0; curRow < dup.height; curRow++) {
        for (int col = 0; col < dup.width; col++) {
          if (curRow < row) {
            dup.matri[curRow][col] = this.matri[curRow][col];
          } else if (curRow == row) {
            dup.matri[curRow][col] = vals[col];
          } else if (curRow > row) {
            dup.matri[curRow][col] = this.matri[curRow - 1][col];
          } // if at colum to be added, insert new row, else write from original
        } // loop over colum
      } // loop over row
      this.height = dup.height;
      this.matri = dup.matri;
    } // checks if index out of bounds, if so throw exception
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *            The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   the width.
   */
  public void insertCol(int col) {
    if (col > this.width || col < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      MatrixV0<T> dup = new MatrixV0<>(this.width + 1, this.height);

      for (int row = 0; row < dup.height; row++) {
        for (int curCol = 0; curCol < dup.width; curCol++) {
          if (curCol < col) {
            dup.matri[row][curCol] = this.matri[row][curCol];
          } else if (curCol == col) {
            dup.matri[row][curCol] = this.def;
          } else if (curCol > col) {
            dup.matri[row][curCol] = this.matri[row][curCol - 1];
          } // if at colum to be added, insert new colum, else write from original
        } // loop over colums
      } // loop over rows
      this.width = dup.width;
      this.matri = dup.matri;
    } // checks if index out of bounds, if so throw exception
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *             The number of the column to insert.
   * @param vals
   *             The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   the width.
   * @throws ArraySizeException
   *                                   If the size of vals is not the same as the
   *                                   height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col > this.width || col < 0) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.height) {
      throw new ArraySizeException();
    } else {
      MatrixV0<T> dup = new MatrixV0<>(this.width + 1, this.height);

      for (int row = 0; row < dup.height; row++) {
        for (int curCol = 0; curCol < dup.width; curCol++) {
          if (curCol < col) {
            dup.matri[row][curCol] = this.matri[row][curCol];
          } else if (curCol == col) {
            dup.matri[row][curCol] = vals[row];
          } else if (curCol > col) {
            dup.matri[row][curCol] = this.matri[row][curCol - 1];
          } // if at colum to be added, insert new colum, else write from original
        } // loop over colum
      } // loop over row
      this.width = dup.width;
      this.matri = dup.matri;
    } // if out of matrix bounds, throw error
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *            The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than or
   *                                   equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException();
    } else {

      MatrixV0<T> dup = new MatrixV0<>(this.width, this.height - 1);

      for (int curRow = 0; curRow < dup.height; curRow++) {
        for (int col = 0; col < dup.width; col++) {
          if (curRow < row) {
            dup.matri[curRow][col] = this.matri[curRow][col];
          } else if (curRow >= row) {
            dup.matri[curRow][col] = this.matri[curRow + 1][col];
          } // does not copy values of row to be deleted
        } // loop over colum
      } // loop over rows
      this.height = dup.height;
      this.matri = dup.matri;
    } // checks if out of matrix bounds, if so throw exception
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *            The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   or equal to the width.
   */
  public void deleteCol(int col) {
    if (col > this.width || col < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      MatrixV0<T> dup = new MatrixV0<>(this.width - 1, this.height);

      for (int row = 0; row < dup.height; row++) {
        for (int colu = 0; colu < dup.width; colu++) {
          if (colu < col) {
            dup.matri[row][colu] = this.matri[row][colu];
          } else if (colu >= col) {
            dup.matri[row][colu] = this.matri[row][colu + 1];
          } // checks if index is area to be deleted, if so skips to delete
        } // loop over colums
      } // loop over rows
      this.width = dup.width;
      this.matri = dup.matri;
    } // checks if in not in bounds to throw error if so, else deletes
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *                 The top edge / row to start with (inclusive).
   * @param startCol
   *                 The left edge / column to start with (inclusive).
   * @param endRow
   *                 The bottom edge / row to stop with (exclusive).
   * @param endCol
   *                 The right edge / column to stop with (exclusive).
   * @param val
   *                 The value to store.
   *
   * @throw IndexOutOfBoundsException
   *        If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow > endRow || startCol > endCol || startCol < 0
        || startRow < 0 || endCol > this.width
        || endRow > this.height) {
      throw new IndexOutOfBoundsException();
    } // checks if parameters are legal for matrix size

    for (int row = startRow; row < endRow; row++) {
      for (int col = startCol; col < endCol; col++) {
        this.matri[row][col] = val;
      } // loops over colums
    } // loops over rows
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *                 The row to start with (inclusive).
   * @param startCol
   *                 The column to start with (inclusive).
   * @param deltaRow
   *                 How much to change the row in each step.
   * @param deltaCol
   *                 How much to change the column in each step.
   * @param endRow
   *                 The row to stop with (exclusive).
   * @param endCol
   *                 The column to stop with (exclusive).
   * @param val
   *                 The value to store.
   *
   * @throw IndexOutOfBoundsException
   *        If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startRow > endRow || startCol > endCol || startCol < 0
        || startRow < 0 || endCol > this.width
        || endRow > this.height) {
      throw new IndexOutOfBoundsException();
    } // checks if paramers are legal for size of matrix
    int col = startCol;
    for (int row = startRow; row < endRow; row += deltaRow) {
      if (col < endCol) {
        this.matri[row][col] = val;
        col += deltaCol;
      } // check if col is within width bounds of matrix
    } // loop over rows
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    MatrixV0<T> dup = new MatrixV0<>(this.width, this.height);
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        dup.matri[row][col] = this.matri[row][col];
      } // loop over each colum
    } // loop over each
    return dup;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *              The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   *         height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix otherMatrix = (Matrix) other;
      if (this.height != otherMatrix.height()) {
        return false;
      } else if (this.width != otherMatrix.width()) {
        return false;
      } else {
        for (int row = 0; row < this.height; row++) {
          for (int col = 0; col < this.width; col++) {
            if (this.matri[row][col] != otherMatrix.get(row, col)) {
              return false;
            } // end of check each element
          } // loop over each colum
        } // loop over each row
      } // check if contents are equal
      return true;
    } else {
      // If it's not a matrix, it's not equal.
      return false;
    } // check if Matrix or not
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
