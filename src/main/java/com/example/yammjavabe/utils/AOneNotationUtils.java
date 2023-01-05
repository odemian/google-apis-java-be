package com.example.yammjavabe.utils;

public class AOneNotationUtils {
    static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static String indexToChar(int i) {
        if (i >= alphabet.length) {
            return indexToChar((int) Math.floor(i / alphabet.length) - 1)
                    + indexToChar(i % alphabet.length);
        }
        return Character.toString(alphabet[i]);
    }

    public static String convert(int row, int col) {
        return indexToChar(col) + (row + 1);
    }

    public static String convert (int row, int col, int numRows, int numCols) {
        return convert(row, col) + ":" + convert(row + numRows, col + numCols);
    }
}
