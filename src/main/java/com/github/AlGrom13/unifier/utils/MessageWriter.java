package com.github.AlGrom13.unifier.utils;

public class MessageWriter {

    private static final String USAGE_MESSAGE = "Args: <input_file_path>";
    private static final String WRONG_FILE_PATH_MESSAGE = "Wrong file path";
    private static final String WRONG_DATA_TYPE_MESSAGE = "%s: wrong data for process";
    private static final String OUTPUT_ERROR_MESSAGE = "Problems with writing out file";

    public static void printUsage() {
        System.out.println(USAGE_MESSAGE);
    }

    public static void printFilePathError() {
        System.out.println(WRONG_FILE_PATH_MESSAGE);
    }

    public static void printDataTypeError(String className) {
        System.out.println(String.format(WRONG_DATA_TYPE_MESSAGE, className));
    }

    public static void printOutputError(Exception e) {
        System.out.println(OUTPUT_ERROR_MESSAGE);
        System.out.println(e.getMessage());
    }
}
