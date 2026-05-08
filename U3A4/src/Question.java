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
        } else {
            String result = text;
            for (int i = 0; i < answerChoices.length; i++) {
                result = result + "\n" + (char) (97 + i) + ". " + answerChoices[i];
            }
            return result;
        }
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}
