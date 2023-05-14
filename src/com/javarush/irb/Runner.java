package com.javarush.irb;

import java.io.IOException;

public class Runner {

    ConsoleClient cli = new ConsoleClient();
    FileService fileService;
    CaesarCipher cryptographer;

    public Runner(FileService fileService, CaesarCipher cryptographer) {
        this.fileService = fileService;
        this.cryptographer = cryptographer;
    }

    public void run(String[] args) throws IOException {
        if (args.length > 0) {
            try {
                String action = String.valueOf(args[0]);
                fileService.setFilePath(String.valueOf(args[1]));
                if (args.length > 2) {
                    int offset = Integer.parseInt(args[2]);
                    if (action.equalsIgnoreCase(String.valueOf(Actions.ENCRYPT))) {
                        cryptographer.encrypt(fileService, offset);
                    } else if (action.equalsIgnoreCase(String.valueOf(Actions.DECRYPT))) {
                        cryptographer.decrypt(fileService, offset);
                    }
                } else if (action.equalsIgnoreCase(String.valueOf(Actions.BRUTE_FORCE))) {
                    cryptographer.bruteForce(fileService);
                }
            } catch (IOException e) {
                cli.printException(e);
                cli.run(fileService, cryptographer);
            }
        } else {
            System.out.println("\n Welcome User!\n This is Cipher Caesar Cryptographer.");
            cli.run(fileService, cryptographer);
        }
    }
}