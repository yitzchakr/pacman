package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class LeaderBoard {
    GameManager gameManager;
    Scanner scanner;
    BufferedWriter writer;
    public int highScore;
    ArrayList <Integer>highScores;
    HashMap<Integer,String>scoresMap;



    public LeaderBoard(GameManager gameManager) {
        this.gameManager = gameManager;
        try {
            scanner = new Scanner(new File("src/resources/highScores.txt"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadSores();
        highScore = highScores.getFirst();
        scanner.close();

    }
    private void loadSores(){

        highScores = new ArrayList<>();
        scoresMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int currentScore = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            String currentName = line.substring(line.indexOf(" ") + 1);
            scoresMap.put(currentScore, currentName);
            highScores.add(currentScore);

        }
    }

    public void updateScores( String name) {
        int currentScore = gameManager.score;


        scoresMap.put(currentScore, name);
        highScores.add(currentScore);
        highScores.sort(Collections.reverseOrder());
        if (highScores.size() > 10) {
            highScores.removeLast();
        }

        try {
            writer = new BufferedWriter(new FileWriter("src/resources/highScores.txt"));
            for (int s : highScores) {
                writer.write(s + " " + scoresMap.get(s));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
           throw new RuntimeException();
        }
        scanner.close();

    }

    public boolean isHighScore() {
        return highScores.size() < 10 || gameManager.score > highScores.getLast();
    }


    public String getHighScores() {
            String info ="";
        for (int score : highScores){
            info=info.concat("\n"+score+" "+scoresMap.get(score));
        }
        return info;
    }
}
