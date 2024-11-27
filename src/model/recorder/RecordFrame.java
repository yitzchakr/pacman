package model.recorder;

public class RecordFrame {
    int score ;
    int lives ;
    char [][] frame;
    int coins;

    public RecordFrame(int score, int lives, char[][] frame,int coins) {
        this.score = score;
        this.lives = lives;
        this.frame = frame;
        this.coins= coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public char[][] getFrame() {
        return frame;
    }

    public void setFrame(char[][] frame) {
        this.frame = frame;
    }
}
