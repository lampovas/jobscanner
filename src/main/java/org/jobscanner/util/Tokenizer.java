package org.jobscanner.util;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tokenizer
{
    public Tokenizer(){
    }
    
    public static String[] extractTokens(String sentence, String tokenizerBinFilename) {

        String[] tokens = {};

        try (InputStream model = new FileInputStream(tokenizerBinFilename)) {

            TokenizerME tokenizerME = new TokenizerME(new TokenizerModel(model));

            tokens = tokenizerME.tokenize(sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokens;
    }
}
