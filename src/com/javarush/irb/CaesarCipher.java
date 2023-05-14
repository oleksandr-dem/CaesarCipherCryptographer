package com.javarush.irb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CaesarCipher {
    Constants constants;
    ArrayList<String> originalLinesList;
    ArrayList<String> resultLinesList;

    public CaesarCipher(Constants constants) {
        this.constants = constants;
    }

    public String cipher(String cipherLine, int offset) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipherLine.length(); i++) {
            for (int j = 0; j < Math.max(constants.SYMBOLS.length(), constants.getAlphabetLength()); j++) {
                if (j < constants.SYMBOLS.length() && cipherLine.charAt(i) == constants.SYMBOLS.charAt(j)) {
                    result.append(constants.SYMBOLS.charAt(j));
                } else if (j < constants.NUMBERS.length() && cipherLine.charAt(i) == constants.NUMBERS.charAt(j)) {
                    result.append(constants.NUMBERS.charAt(j));
                } else if (j < constants.LOWERCASE.length() && cipherLine.charAt(i) == constants.LOWERCASE.charAt(j)) {
                    result.append(constants.LOWERCASE.charAt((j + offset) % constants.getAlphabetLength()));
                } else if (j < constants.UPPERCASE.length() && cipherLine.charAt(i) == constants.UPPERCASE.charAt(j)) {
                    result.append(constants.UPPERCASE.charAt((j + offset) % constants.getAlphabetLength()));
                }
            }
        }
        return result.toString();
    }

    public void encrypt(FileService fileService, int offset) throws IOException {
        originalLinesList = new ArrayList<>(fileService.readFile());
        resultLinesList = new ArrayList<>();
        for (String line : originalLinesList) {
            resultLinesList.add(cipher(line, offset % constants.getAlphabetLength()));
        }
        fileService.saveFile(resultLinesList, String.valueOf(Actions.ENCRYPT));
    }

    public void decrypt(FileService fileService, int offset) throws IOException {
        originalLinesList = new ArrayList<>(fileService.readFile());
        resultLinesList = new ArrayList<>();
        for (String line : originalLinesList) {
            resultLinesList.add(cipher(line, constants.getAlphabetLength() -
                    (offset % constants.getAlphabetLength())));
        }
        fileService.saveFile(resultLinesList, String.valueOf(Actions.DECRYPT));
    }

    public void bruteForce(FileService fileService) throws IOException {
        int offset;
        Scanner scanner = new Scanner(new File(fileService.getFilePath()));
        ArrayList<String> wordsList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordsList.add(scanner.next());
        }
        scanner.close();
        for (offset = 1; offset < constants.getAlphabetLength(); offset++) {
            ArrayList<String> tempListOfWordsWithOffset = new ArrayList<>();
            for (String singleWord : wordsList) {
                tempListOfWordsWithOffset.add(cipher(singleWord,
                        constants.getAlphabetLength() - (offset % constants.getAlphabetLength())));
            }
            for (String word : tempListOfWordsWithOffset) {
                for (String commonWord : constants.COMMON_WORDS) {
                    if (word.equalsIgnoreCase(String.valueOf(commonWord))) {
                        originalLinesList = new ArrayList<>(fileService.readFile());
                        resultLinesList = new ArrayList<>();
                        for (String line : originalLinesList) {
                            resultLinesList.add(cipher(line,
                                    constants.getAlphabetLength() - (offset % constants.getAlphabetLength())));
                        }
                        fileService.saveFile(resultLinesList, String.valueOf(Actions.BRUTE_FORCE));
                        break;
                    }
                }
            }
            tempListOfWordsWithOffset.clear();
        }
    }
}