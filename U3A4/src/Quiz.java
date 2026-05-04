import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private String path, subject, author, title;
    private int questionCount;
    private Question[] questions;

    public Quiz(String path) {
        try {
            // Load the Quiz
            loadQuiz();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void loadQuiz() throws Exception {
        // Instantiate the ArrayList
        ArrayList<String> lineList = new ArrayList<>();
        // Create the File object
        File file = new File("src\\quiz.txt");
        // Create the Scanner object
        Scanner scr = new Scanner(file);
        // Read the file line by line
        while (scr.hasNextLine()) {
            lineList.add(scr.nextLine());
        }
        String[] lines = lineList.toArray(String[]::new);
        this.subject = lines[0];
        this.author = lines[1];
        this.title = lines[2];
        this.questionCount = Integer.parseInt(lines[3]);
        questions = new Question[questionCount];
        int questionsParsed = 0;
        int currentIndex = 4;
        while (questionsParsed < questionCount) {
            String questionType = lines[currentIndex++];
            String text = lines[currentIndex++];
            String[] answers = null;

            // Decide whether the question is T/F or Multiple Choice
            // Instantiate the array so it contains the proper size
            if(questionType.equals("TRUE_FALSE")) {
                answers = new String[2];
            } else {
                answers = new String[lines[currentIndex].split("_").length];
            }

            // Add the answer choices to the array
            String[] strings = lines[currentIndex++].split("_");
            for(int i = 0; i < strings.length; i++) {
                answers[i] = strings[i];
            }
            // Store the correct answer into a String
            String answer = lines[currentIndex++];
            // Add the question to the questions array
            questions[questionsParsed] = new Question(questionType, text, answers, answer);
            questionsParsed++;
        }
    }

    public Question[] getQuestions() {
        return questions;
    }
}
