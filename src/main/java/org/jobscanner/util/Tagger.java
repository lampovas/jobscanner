package org.jobscanner.util;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tagger
{
    public Tagger() {
    }
    
    public static String[] getPOSTags(String[] tokens, String posBinFilename) {

        String[] posTags = {};

        try (InputStream model = new FileInputStream(posBinFilename)) {

            POSTaggerME posTaggerME = new POSTaggerME(new POSModel(model));

            posTags = posTaggerME.tag(tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posTags;
    }
}
