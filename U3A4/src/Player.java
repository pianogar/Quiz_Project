public class Player {
     // Create 2 fields: name and score
     private String name;
    private int score;

    // Create a constructor which accepts 2 parameters, one for the
    // name and one for the score
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Create accessor methods for both fields
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
