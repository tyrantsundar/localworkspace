package SankeAndLadder;

import libraryManagement.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Board board;

    public Game() {
        this.board = new Board(10,10);
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    private static int rollDice(){
//        System.out.println("Enter Value ");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextInt();
        double randomNum = Math.random() % 6;
        return (int)randomNum+1;
    }
    public void gameOn(){
        boolean isGameOver = false;
        while (!isGameOver){
            int playersWonCount = 0;
            for(Player player : players){
                if(player.isWon()){
                    playersWonCount++;
                    continue;
                }
                int diceNum = rollDice();
                System.out.println("PlayerName "+player.getName()+" Dice Num >> "+diceNum);
                int currPosition = player.getCurrPosition();
                int newPosition =   currPosition+diceNum;
                if(newPosition == board.getDestinationPoint()){
                    player.setCurrPosition(newPosition);
                    player.setWon();
                }else if(newPosition > board.getDestinationPoint()){
                    System.out.println("Current Position is "+currPosition+"\nYou cant move beyond destination point "+board.getDestinationPoint());
                }else{
                    if(board.isSpecialElementPresent(newPosition)) {
                        newPosition = board.getSpecialElementEndPosition(newPosition);
                    }
                    player.setCurrPosition(newPosition);
                }
                System.out.println("PlayerName "+player.getName()+"Current Position >> "+newPosition);
            }
            if(playersWonCount == players.size()){
                isGameOver = true;
            }
        }
        System.out.println("Game Over !!!");
        for(Player player : players){
            System.out.println("Thanks for your participation -> "+player.getName());
        }
    }

}
