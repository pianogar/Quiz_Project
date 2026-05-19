import java.util.ArrayList;
import java.util.Arrays;

public class Question implements Questionable {
    private String text;
    private String questionType;
    private String[] answerChoices;
    private String answer;

    public Question(String questionType, String text, String[] answerChoices, String answer) {
        this.questionType = questionType;
        this.text = text;
        this.answerChoices = answerChoices;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public String toString() {
        if (questionType.equals("TRUE_FALSE")) {
            return "True or False:\n" + text;
        } else if (questionType.equals("MULTIPLE_CHOICE")) {
            String result = text;
            for (int i = 0; i < answerChoices.length; i++) {
                result = result + "\n" + (char) ('a' + i) + ". " + answerChoices[i];
            }
            return result;
        } else {
            switch (Integer.parseInt(answerChoices[0])) {
                case 1: {// number of correct questions
                    String[] newAnswers = new String[4];
                    ArrayList<Integer> random = new ArrayList<>();
                    for (int i = 0; i <= Quizer.getAnswered(); i++) {
                        if (i != Quizer.getScore()) {
                            random.add(i);
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        if (i != answer.charAt(0) - 'a') {
                            newAnswers[i] = random.remove((int) (Math.random() * random.size())) + "";
                        } else {
                            newAnswers[i] = Quizer.getScore() + "";
                        }
                    }
                    String result = text;
                    for (int i = 0; i < newAnswers.length; i++) {
                        result = result + "\n" + (char) ('a' + i) + ". " + newAnswers[i];
                    }
                    return result;
                }
                default:
                    return null;
            }
        }
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}
