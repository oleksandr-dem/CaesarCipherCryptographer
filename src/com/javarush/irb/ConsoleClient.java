package com.javarush.irb;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleClient {

    public void run(FileService fileService, CaesarCipher cryptographer) throws IOException {
        int offset;
        Scanner scanner = new Scanner(System.in);
        System.out.print(cryptographer.constants.MAIN_MENU);
        int actionNumber = scanner.nextInt();
        switch (actionNumber) {
            case 1 -> {
                try {
                    System.out.print(cryptographer.constants.ENCRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    System.out.print(cryptographer.constants.OFFSET_MENU);
                    offset = scanner.nextInt();
                    cryptographer.encrypt(fileService, offset);
                    System.out.println(cryptographer.constants.FILE_SAVED);
                } catch (IOException e) {
                    printException(e);
                    this.run(fileService, cryptographer);
                }
            }
            case 2 -> {
                try {
                    System.out.print(cryptographer.constants.DECRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    System.out.print(cryptographer.constants.OFFSET_MENU);
                    offset = scanner.nextInt();
                    cryptographer.decrypt(fileService, offset);
                    System.out.println(cryptographer.constants.FILE_SAVED);
                } catch (IOException e) {
                    printException(e);
                    this.run(fileService, cryptographer);
                }
            }
            case 3 -> {
                try {
                    System.out.print(cryptographer.constants.DECRYPT_MENU);
                    scanner.nextLine();
                    fileService.setFilePath(scanner.nextLine());
                    cryptographer.bruteForce(fileService);
                    System.out.println(cryptographer.constants.FILE_SAVED);
                } catch (IOException e) {
                    printException(e);
                    this.run(fileService, cryptographer);
                }
            }
            default -> {
                System.out.println(cryptographer.constants.WRONG_ACTION);
                this.run(fileService, cryptographer);
            }
        }
        scanner.close();
    }

    public void printException(IOException e) {
        System.out.println("\n >>>>>>> " + e.getMessage() + " <<<<<<<");
    }
}