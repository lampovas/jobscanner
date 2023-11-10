package org.jobscanner;

import org.jobscanner.responders.ExampleResponder;
import org.jobscanner.responders.JobSearchResponder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

        runUdemyExample();
    }

    private static void runUdemyExample(){
        String question = "good morning";
        ExampleResponder exampleResponder = new ExampleResponder();
        exampleResponder.getAnswers(question).forEach(answer -> System.out.println(answer));
    }

    private static void runJobExample(){
        List<String> allLines, tempAnswers;
        try {
            allLines = Files.readAllLines(Paths.get("jobVacancies.txt"));
        } catch (IOException e) {
            System.out.println("Error while reading file jobVacancies.txt");
            return;
        }
        JobSearchResponder jobSearchResponder = new JobSearchResponder();

        for(int i=0; i< allLines.size(); ++i){
            int currentI = i;
            tempAnswers = jobSearchResponder.getAnswers(allLines.get(i));
            tempAnswers.forEach(answer -> System.out.println("Line: "+ currentI +", Answer: "+answer));
        }

    }
}
