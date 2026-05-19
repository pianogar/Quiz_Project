import java.util.ArrayList;
import java.util.Scanner;

public class Quizer {
    private static int score = 0;
    private static int answered = 0;

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
            answered++;
        }
        Leaderboard lb = new Leaderboard(false);

        System.out.println("Add a multiple choice question!");
        String questionType = "MULTIPLE_CHOICE";
        System.out.println("What is the text for your question?");
        String text = scr.nextLine();
        System.out.println("What are your answer choices? (input nothing to continue)");
        ArrayList<String> answerChoicesList = new ArrayList<>();
        String answerChoice = scr.nextLine();
        while (!answerChoice.equals("")) {
            answerChoicesList.add(answerChoice);
            answerChoice = scr.nextLine();
        }
        String[] answerChoices = new String[answerChoicesList.size()];
        for (int i = 0; i < answerChoices.length; i++) {
            answerChoices[i] = answerChoicesList.get(i);
        }
        System.out.print("What is the answer? (");
        for (int i = 'a'; i < 'a' + answerChoices.length - 1; i++) {
            System.out.print((char) i + ",");
        }
        System.out.print((char) ('a' + answerChoices.length - 1) + ")");
        String answer = scr.nextLine();
        Question q = new Question(questionType, text, answerChoices, answer);
        quiz.addQuestion(q);

    }

    public static int getScore() {
        return score;
    }

    public static int getAnswered() {
        return answered;
    }
}
