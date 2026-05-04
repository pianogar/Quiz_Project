import java.util.Scanner;

public class Quizer {
    public static void main(String[] args) {
        Quiz quiz = new Quiz("Example Quiz.txt");
        Scanner scr = new Scanner(System.in);
        for (Question q : quiz.getQuestions()) {
            System.out.println(q.toString());
            String answer = scr.nextLine();
            if (q.checkAnswer(answer)) {
                System.out.println("Correct!");
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
    }
}
