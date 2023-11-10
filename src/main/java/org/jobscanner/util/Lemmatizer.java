package org.jobscanner.util;

import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Lemmatizer
{
    public Lemmatizer() {
    }

    public static String[] extractLemmas(String[] tokens, String[] posTags, String lemmatizerBinFilename) {

        String[] lemmas = {};

        try (InputStream model = new FileInputStream(lemmatizerBinFilename)) {

            LemmatizerME lemmatizerME = new LemmatizerME(new LemmatizerModel(model));

            lemmas = lemmatizerME.lemmatize(tokens, posTags);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lemmas;

    }
}
