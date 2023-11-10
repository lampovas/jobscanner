package org.jobscanner.responders;

import org.jobscanner.util.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractResponder
{
    private String sentenceBinFilename, tokenizerBinFilename, posBinFilename;
    private String lemmatizerBinFilename, categorizerTxtFilename;

    protected abstract String getDefaultAnswer();

    protected abstract Map<String, String> getResponses();

    public AbstractResponder(String sentenceBinFilename, String tokenizerBinFilename,
                             String posBinFilename, String lemmatizerBinFilename,
                             String categorizerTxtFilename){
        this.sentenceBinFilename = sentenceBinFilename;
        this.tokenizerBinFilename = tokenizerBinFilename;
        this.posBinFilename = posBinFilename;
        this.lemmatizerBinFilename = lemmatizerBinFilename;
        this.categorizerTxtFilename = categorizerTxtFilename;

    }

    public String getResponse(String category){
        String defaultAnswer = getDefaultAnswer();
        Map<String, String> responses = getResponses();
        return  !responses.containsKey(category) ? defaultAnswer : responses.get(category);
    }

    public List<String> getAnswers(String question){
        return SentenceDetectorWrapper.extractSentences(question,this.sentenceBinFilename).stream()
                .map(sentence ->  sentenceToResponse(sentence)).collect(Collectors.toList());
    }
    public String sentenceToResponse(String sentence){

        String[] tokens = Tokenizer.extractTokens(sentence,this.tokenizerBinFilename);

        String[] pos = Tagger.getPOSTags(tokens, this.posBinFilename);

        String[] lemmas = Lemmatizer.extractLemmas(tokens, pos, this.lemmatizerBinFilename);

        String category = Categorizer.getCategory(this.categorizerTxtFilename,lemmas);

        return this.getResponse(category);
    }

}
