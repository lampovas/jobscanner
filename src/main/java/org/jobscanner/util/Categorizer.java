package org.jobscanner.util;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Categorizer
{
    public Categorizer(){
    }

    public static String getCategory(String categorizerTxtFilename, String[] lemmas){

        DoccatModel categorizerModel;
        try {
            categorizerModel = Categorizer.getCategorizerModel(categorizerTxtFilename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "";
        }

        return Categorizer.getBestCategory(categorizerModel,lemmas);
    }
    
    private static DoccatModel getCategorizerModel(String categorizerTxtFilename) throws IOException {

        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(categorizerTxtFilename));
        ObjectStream<String> lineObjectStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineObjectStream);

        DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{new BagOfWordsFeatureGenerator()});

        TrainingParameters trainingParameters = ModelUtil.createDefaultTrainingParameters();

        trainingParameters.put(TrainingParameters.CUTOFF_PARAM, 0);

        return DocumentCategorizerME.train("en", sampleStream, trainingParameters, factory);
    }

    private static String getBestCategory(DoccatModel categorizerModel, String[] lemmas){

        DocumentCategorizerME documentCategorizerME = new DocumentCategorizerME(categorizerModel);

        double[] probabilities = documentCategorizerME.categorize(lemmas);
        
        return documentCategorizerME.getBestCategory(probabilities);
    }
}
