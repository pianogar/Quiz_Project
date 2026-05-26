import java.util.ArrayList;

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
                case 2: {// last three letters answered
                    String[] newAnswers = new String[4];
                    ArrayList<String> answers = Quizer.getAnswers();
                    int length = answers.size();
                    String word = answers.get(length - 3) + answers.get(length - 2) + answers.get(length - 1);
                    ArrayList<String> wordOptions = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            for (int k = 0; k < 3; k++) {
                                String s = word.charAt(i) + "" + word.charAt(j) + "" + word.charAt(k);
                                boolean exists = false;
                                for (String check : wordOptions) {
                                    if (s.equals(check))
                                        exists = true;
                                }
                                if (!s.equals(word) && !exists)
                                    wordOptions.add(s);
                            }
                        }
                    }
                    while (wordOptions.size() < 3) {
                        String s = (char) ('a' + (int) (Math.random() * 4)) + ""
                                + (char) ('a' + (int) (Math.random() * 4)) + ""
                                + (char) ('a' + (int) (Math.random() * 4));
                        boolean exists = false;
                        for (String check : wordOptions) {
                            if (s.equals(check))
                                exists = true;
                        }
                        if (!s.equals(word) && !exists)
                            wordOptions.add(s);
                    }
                    for (int i = 0; i < 4; i++) {
                        if (i != answer.charAt(0) - 'a') {
                            newAnswers[i] = wordOptions.remove((int) (Math.random() * wordOptions.size()));
                        } else {
                            newAnswers[i] = word;
                        }
                    }
                    String result = text;
                    for (int i = 0; i < newAnswers.length; i++) {
                        result = result + "\n" + (char) ('a' + i) + ". " + newAnswers[i];
                    }
                    return result;
                }
                case 3: {// highest player
                    ArrayList<String> newAnswers = new ArrayList<>();
                    ArrayList<Player> players = new ArrayList<>();
                    for (Player p : Quizer.getPlayers()) {
                        players.add(p);
                    }
                    int numOfPlayers = players.size();
                    answer = (char) ((int) (Math.random() * numOfPlayers) + 'a') + "";
                    Player highestPlayer = players.remove(0);
                    int highScore = highestPlayer.getScore();
                    for (int i = 0; i < players.size(); i++) {
                        if(players.get(i).getScore() < highScore)
                            break;
                        else {
                            players.remove(i);
                            numOfPlayers--;
                            i--;
                        }
                    }
                    for (int i = 0; i < numOfPlayers; i++) {
                        if (i != answer.charAt(0) - 'a') {
                            newAnswers.add(players.remove((int) (Math.random() * players.size())).getName());
                        } else {
                            newAnswers.add(highestPlayer.getName());
                        }
                    }
                    String result = text;
                    for (int i = 0; i < newAnswers.size(); i++) {
                        result = result + "\n" + (char) ('a' + i) + ". " + newAnswers.get(i);
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
