import java.util.ArrayList;
import java.util.Scanner;

public class Quizer {
    private static int score = 0;
    private static int answered = 0;
    private static ArrayList<String> answers = new ArrayList<>();
    private static Leaderboard lb = new Leaderboard(false);

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        Scanner scr = new Scanner(System.in);
        System.out.println(quiz.toString());
        System.out.println("Press enter to continue");
        scr.nextLine();
        for (Question q : quiz.getQuestions()) {
            clearScreen();
            System.out.println(q.toString());
            String answer = scr.nextLine();
            answers.add(answer);
            clearScreen();
            if (q.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
                wait(2000);
            } else {
                System.out.println("Incorrect!");
                wait(2000);
            }
            answered++;
        }
        clearScreen();
        int finalScore = (int)(score * (15.0/quiz.getQuestionCount()) + 0.5);
        System.out.println("You got "+ score + "/" + answered + " which converts to " + finalScore + "/15\nPlease enter your name for the leaderboard!");
        lb.addPlayer(scr.nextLine(), finalScore);
        clearScreen();
        lb.displayLeaderboard();
        System.out.println("Press enter to continue");
        scr.nextLine();
        clearScreen();
        System.out.println("Would you like to add a multiple choice question? (y/n)");
        String ans = scr.nextLine();
        if (ans.contains("y") || ans.contains("Y")) {
            boolean valid = false;
            String questionType = "";
            String text = "";
            String[] answerChoices = new String[0];
            String answer = "";
            while (!valid) {
                questionType = "MULTIPLE_CHOICE";
                clearScreen();
                System.out.println("What is the text for your question?");
                text = scr.nextLine();
                clearScreen();
                System.out.println("What are your answer choices? (input nothing to continue)");
                ArrayList<String> answerChoicesList = new ArrayList<>();
                String answerChoice = scr.nextLine();
                while (!answerChoice.equals("")) {
                    answerChoicesList.add(answerChoice);
                    answerChoice = scr.nextLine();
                }
                answerChoices = new String[answerChoicesList.size()];
                for (int i = 0; i < answerChoices.length; i++) {
                    answerChoices[i] = answerChoicesList.get(i);
                }
                clearScreen();
                System.out.print("What is the answer? (");
                for (int i = 'a'; i < 'a' + answerChoices.length - 1; i++) {
                    System.out.print((char) i + ",");
                }
                System.out.print((char) ('a' + answerChoices.length - 1) + ")");
                answer = scr.nextLine();
                clearScreen();
                System.out.println("Here is a preview of your question:");
                String result = text;
                for (int i = 0; i < answerChoices.length; i++) {
                    result = result + "\n" + (char) ('a' + i) + ". " + answerChoices[i];
                }
                System.out.println(result);
                System.out.println("(The answer is " + answer + ")");
                System.out.println("Is this what you would like? (y/n)");
                String imRunningOutOfIdeasBro = scr.nextLine();
                if (imRunningOutOfIdeasBro.contains("y") || imRunningOutOfIdeasBro.contains("Y"))
                    valid = true;
                else {
                    clearScreen();
                    System.out.println("Please remake the question.");
                    wait(1500);
                }
            }
            Question q = new Question(questionType, text, answerChoices, answer);
            quiz.addQuestion(q);
            scr.close();
        }
    }

    public static int getScore() {
        return score;
    }

    public static int getAnswered() {
        return answered;
    }

    public static ArrayList<String> getAnswers() {
        return answers;
    }

    public static ArrayList<Player> getPlayers() {
        return lb.getPlayers();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
