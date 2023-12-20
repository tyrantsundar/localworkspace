package SankeAndLadder;

public class Player {
    private String name;
    private int currPosition;
    private boolean isWon;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon() {
        isWon = true;
    }
}
