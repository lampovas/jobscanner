package org.jobscanner.responders;

import java.util.HashMap;
import java.util.Map;

public class JobSearchResponder extends AbstractResponder {

    public JobSearchResponder() {
        super("en-sent.bin", "en-token.bin", "en-pos-maxent.bin",
                "en-lemmatizer.bin", "categorizer-job.txt");
    }
    @Override
    protected String getDefaultAnswer() {
        return "I'm sorry, but I did not find any matches!";
    }

    @Override
    protected Map<String, String> getResponses() {
        Map<String, String> responses = new HashMap<>();
        responses.put("python", "I have found occurrences for python!");
        responses.put("go", "I have found occurrences for golang!");
        responses.put("java", "I have found occurrences for java!");
        return responses;
    }
}
