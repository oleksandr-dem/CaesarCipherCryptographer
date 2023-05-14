package com.javarush.irb;

public class Constants {

    public final String UPPERCASE =     "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final String LOWERCASE =     "abcdefghijklmnopqrstuvwxyz";
    public final String SYMBOLS =       " !'\"`’()*+-=_.,/|\\:;?#%@[]{}<>^~«»&$¡¢£¤¥¦§¨©®¬¯°±º×¿";
    public final String NUMBERS =       "0123456789";
    public final String[] COMMON_WORDS = {"the","and","you","that","was","for","are","with","his","they",
            "this","have","from","one","had","word","but","not","what","all","were","when","your","can",
            "said","there","use","each","which","she","how","their","will","other","about","out","many",
            "then","them","these","some","her","would","make","like","him","into","time","has","look","two",
            "more","write","see","number","way","could","people","than","first","water","been","call","who",
            "oil","its","now","find","long","down","day","did","get","come","made","may","part","set","line"};
    public final String MAIN_MENU =
            """

                     You could choose between actions listed below:
                         1 -> to encrypt file
                         2 -> to decrypt file
                         3 -> bruteforce cipher (currently not working)

                     Enter number of preferred action: \
                    """;
    public final String ENCRYPT_MENU =  "\n Enter path to file to encrypt: ";
    public final String DECRYPT_MENU =  "\n Enter path to file to decrypt: ";
    public final String OFFSET_MENU =   "\n Enter cipher shift key: ";
    public final String FILE_SAVED =    "\n     File saved.";
    public final String WRONG_ACTION =  "\n Wrong action, please type number between 1 and 3.";

    public int getAlphabetLength() {
        return UPPERCASE.length();
    }
}