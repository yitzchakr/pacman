package model.recorder;

import model.GameManager;

import java.io.*;
import java.util.*;

public class LeaderBoard {
    GameManager gameManager;
    Scanner scanner;
    BufferedWriter writer;
    public int highScore;
    ArrayList <Score>highScores;



    public LeaderBoard(GameManager gameManager) {
        this.gameManager = gameManager;
        try {
            scanner = new Scanner(new File("src/resources/highScores.txt"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadScores();
        highScore = highScores.getFirst().getPoints();
        scanner.close();

    }
    private void loadScores(){

        highScores = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int currentScore = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            String currentName = line.substring(line.indexOf(" ") + 1);

            highScores.add(new Score(currentScore,currentName));

        }
    }

    public void updateScores( String name) {
        int currentScore = gameManager.score;


        highScores.add(new Score(currentScore,name));
        highScores.sort(Comparator.comparing((Score:: getPoints),Collections.reverseOrder()));
        if (highScores.size() > 10) {
            highScores.removeLast();
        }

        try {
            writer = new BufferedWriter(new FileWriter("src/resources/highScores.txt"));
            for (Score s : highScores) {
                writer.write(s.getPoints() + " " + s.getName());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
           throw new RuntimeException();
        }
        scanner.close();

    }

    public boolean isHighScore() {
        return highScores.size() < 10 || gameManager.score > highScores.getLast().getPoints();
    }


    public String getHighScores() {
            String info ="";
        for (Score score : highScores){
            info=info.concat("\n"+score.getPoints()+" "+score.getName());
        }
        return info;
    }
}
