package com.javarush.irb;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Constants constants = new Constants();
        FileService fileService = new FileService();
        CaesarCipher cryptographer =  new CaesarCipher(constants);
        Runner runner = new Runner(fileService, cryptographer);
        runner.run(args);
    }
}