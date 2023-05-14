package com.javarush.irb;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readFile() throws IOException {
        Scanner scanner = new Scanner(new File(filePath)).useDelimiter(System.lineSeparator());
        ArrayList<String> strings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
        scanner.close();
        return strings;
    }

    public void saveFile(ArrayList<String> strings, String action) throws IOException {
        FileWriter fileWriter = new FileWriter(getNewFilePath(filePath, action));
        for (String line : strings) {
            fileWriter.write(line + System.lineSeparator());
        }
        fileWriter.close();
    }

    public String getNewFilePath(String oldFilePath, String action) {
        int dotIndex = oldFilePath.lastIndexOf(".");
        int actionIndex = oldFilePath.lastIndexOf("[");
        String newFileName;
        if (action.equalsIgnoreCase(String.valueOf(Actions.ENCRYPT))) {
            newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                    Actions.ENCRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
        } else if (action.equalsIgnoreCase(String.valueOf(Actions.DECRYPT))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFileName = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else if (action.equalsIgnoreCase(String.valueOf(Actions.BRUTE_FORCE))) {
            if (oldFilePath.contains("[ENCRYPTED]")) {
                newFileName = oldFilePath.substring(0, actionIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            } else {
                newFileName = oldFilePath.substring(0, dotIndex) + "[" +
                        Actions.BRUTE_FORCE + "_" + Actions.DECRYPT + "ED" + "]" + oldFilePath.substring(dotIndex);
            }
        } else {
            newFileName = oldFilePath.substring(0, dotIndex) + "[ERR]" + oldFilePath.substring(dotIndex);
        }
        return newFileName;
    }
}
