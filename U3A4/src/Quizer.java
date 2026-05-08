import java.util.ArrayList;
import java.util.Scanner;

public class Quizer {
    private static int score = 0;
    public static void main(String[] args) {
        Quiz quiz = new Quiz("Example Quiz.txt");
        Scanner scr = new Scanner(System.in);
        for (Question q : quiz.getQuestions()) {
            System.out.println(q.toString());
            String answer = scr.nextLine();
            if (q.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Incorrect!");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        Leaderboard lb = new Leaderboard(false);
        System.out.println("Add a question!\nQuestion Type:\na. True/False\nb. Multiple Choice");
        String questionType = scr.nextLine();
        if(questionType.equals("a")) {
            questionType = "TRUE_FALSE";
        } else {
            questionType = "MULTIPLE_CHOICE";
        }
        System.out.println("What is the text for your question?");
        String text = scr.nextLine();
        System.out.println("What are your answer choices? (input nothing to continue)");
        ArrayList<String> answerChoicesList = new ArrayList<>();
        String answerChoice = scr.nextLine();
        while(!answerChoice.equals("")) {
            answerChoicesList.add(answerChoice);
            answerChoice = scr.nextLine();
        }
        String[] answerChoices = new String[answerChoicesList.size()];
        for(int i = 0; i < answerChoices.length; i++) {
            answerChoices[i] = answerChoicesList.get(i);
        }
        System.out.println("What is the answer?");
        String answer = scr.nextLine();
        Question q = new Question(questionType, text, answerChoices, answer);
        quiz.addQuestion(q);
    }
}
