package org.jobscanner.util;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SentenceDetectorWrapper
{
    public SentenceDetectorWrapper(){
    }
    
    public static List<String> extractSentences(String userInput, String sentenceBinFilename) {

        String[] sentences = {};

        try (InputStream model = new FileInputStream(sentenceBinFilename)) {

            SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(new SentenceModel(model));

            sentences = sentenceDetectorME.sentDetect(userInput);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(sentences);

    }
}
