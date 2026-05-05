import java.io.*;

import java.util.*;

public class Leaderboard {
    private static final String LEADERBOARD_FILE = "src\\leaderboard.txt";
    private List<Player> players;

    // If clear is true the leaderboard should be cleared,
    // otherwise it should load the leaderboard.
    public Leaderboard(boolean clear) {
        if (clear)
            clearLeaderboard();
        else
            loadLeaderboard();
    }

    // Adds a player to the leaderboard and updates the leaderboard file.
    // Additionally, it will print “Player added successfully”
    public void addPlayer(String name, int score) {
        players.add(new Player(name, score));
        saveLeaderboard();
        System.out.println("Player added successfully");
    }

    // Displays the leaderboard sorted by score in descending order.
    public void displayLeaderboard() {
        // Sort players by score (descending order)
        while (!sorted()) {
            Random rand = new Random();
            for (int i = 0; i < players.size(); i++) {
                int randomIndex = rand.nextInt(players.size());
                Player temp = players.get(randomIndex);
                players.set(randomIndex, players.get(i));
                players.set(i, temp);
            }
        }
        // Display the sorted leaderboard
        for(Player p : players) {
            System.out.println("1. " + p.getName() + ": " + p.getScore());
        }
    }

    private boolean sorted() {
        boolean sorted = true;
        Player temp = players.get(0);
        for (int i = 1; i < players.size(); i++) {
            if (temp.getScore() < players.get(i).getScore())
                sorted = false;
            temp = players.get(i);
        }
        return sorted;
    }

    // Clears the leaderboard, both in memory and the file.
    public void clearLeaderboard() {
        players = new ArrayList<>();
        saveLeaderboard();
    }

    // Loads the leaderboard from the file into memory.
    public void loadLeaderboard() {
        try (Scanner fileScanner = new Scanner(new File(LEADERBOARD_FILE))) {
            // Implement code here to read the file and add names to the leaderboard
            while (fileScanner.hasNextLine()) {
                players.add(new Player(fileScanner.nextLine(), Integer.parseInt(fileScanner.nextLine())));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Leaderboard file not found. A new one will be created.");
        }
    }

    // Saves the current leaderboard to the file.
    public void saveLeaderboard() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            // Add players to the leaderboard file
            for (Player p : players) {
                writer.write(p.getName() + "\n");
                writer.write(p.getScore() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard file: " + e.getMessage());
        }
    }
}