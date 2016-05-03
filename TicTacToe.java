import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Chenxu ZHAO
 * @version 27/04/2016
 */

public class TicTacToe {

    /* utilize a two dimensional arrays to represent the grid's state
    * the value of gridState[gridRow][gridColumn] represent the corresponding state
    * -1 represents player O put a chess piece
    * 1 represents player X put a chess piece
    * 0 represents no chess piece
    * */
    private String PlayerOName;
    private String PlayerXName;

    private static String addPlayer = "addplayer";
    private static String exit = "exit";
    private static String removePlayer = "removeplayer";
    private static String editPlayer = "editplayer";
    private static String resetStats = "resetstats";
    private static String displayPlayer = "displayplayer";
    private static String rankings = "rankings";
    private static String playGame = "playgame";

    public static Scanner input = new Scanner(System.in);

    /*Constructor*/
    public TicTacToe() {
        super();
    }

    public void run() {
        System.out.println("Welcome to Tic Tac Toe!");
        String command = "";
        while (!command.equals(exit)) {
            System.out.println();
            command = input.next();
            if (addPlayer.equalsIgnoreCase(command)) {

            } else if (exit.equalsIgnoreCase(command)) {
                break;
            } else if (removePlayer.equalsIgnoreCase(command)) {
            } else if (editPlayer.equalsIgnoreCase(command)) {
            } else if (resetStats.equalsIgnoreCase(command)) {
            } else if (displayPlayer.equalsIgnoreCase(command)) {
            } else if (rankings.equalsIgnoreCase(command)) {
            } else if (playGame.equalsIgnoreCase(command)) {
            }
        }
    }

    /* main method*/
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.run();
    }


}

class Player {

    private String userName;
    private String familyName;
    private String givenName;
    private int playedGames;
    private int wonGames;
    private int drawnGames;

    /*Constructor*/
    public Player() {
        super();
    }

    /*get value of userName*/
    public String getUserName() {
        return userName;
    }

    /*set value of userName*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*get value of familyName*/
    public String getFamilyName() {
        return familyName;
    }

    /*set value of familyName*/
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /*get value of givenName*/
    public String getGivenName() {
        return givenName;
    }

    /*set value of givenName*/
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /*get value of playedGames*/
    public int getPlayedGames() {
        return playedGames;
    }

    /*set value of playedGames*/
    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    /*get value of wonGames*/
    public int getWonGames() {
        return wonGames;
    }

    /*set value of wonGames*/
    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    /*get value of drawnGames*/
    public int getDrawnGames() {
        return drawnGames;
    }

    /*set value of drawnGames*/
    public void setDrawnGames(int drawnGames) {
        this.drawnGames = drawnGames;
    }
}

class PlayerManager {

    private ArrayList<Player> players;


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /*Constructor*/
    public PlayerManager() {
        super();
    }

    public boolean checkPlayerName(String playerName) {
        int count = 0;
        while (count < players.size()) {
            if (players.get(count).getUserName().equals(playerName)) {
                System.out.println("The user name has been used already");
                return false;
            }
            count++;
        }
        return true;
    }

    /*add new players*/
    public void addNewPlayer(String playerNames) {
        String[] playerInformation = playerNames.split(",");

    }

    /*delete all information about existed players*/
    public void removeALLPlayers() {

    }

    /*edit players' names*/
    public void modifyPlayerName(String playerName, String modifiedName) {

    }

    /*reset a player's statistics information*/
    public void resetPlayerStatistics(String userName) {

    }

    /*display player's information*/
    public void displayPlayerInfo(String userName) {

    }

    public void displayRank() {

    }
}

class GameManager {

    private int[][] gridState;

    /*Constructor*/
    public GameManager() {
        super();
    }

    /*play a game*/
    public void playGame() {

    }

    /* get gridState[gridRow][gridColumn]*/
    public int getPracGridState(int gridRow, int gridColumn) {
        return this.gridState[gridRow][gridColumn];
    }

    /* set a practical position's state*/
    public void setOneGridState(int gridRow, int gridColumn, int stateCode) {
        this.gridState[gridRow][gridColumn] = stateCode;
    }

    /* get the whole state of a grid*/
    public int[][] getGridState() {
        return this.gridState;
    }

    /*set the whole grid's state*/
    public void setGridState(int[][] gridState) {
        this.gridState = gridState;
    }

    /* initialize game: get the users' names, set and print grid without chess piece*/
    public void initGame(Scanner scanner) {
        //print welcome words and set players' name
        System.out.println("Welcome to Tic Tac Toe!\n");
        System.out.println("Enter Player O's name:");
        //setPlayerOName(scanner.next());
        System.out.println("Enter Player X's name:");
        //setPlayerXName(scanner.next());

        //initialize grid's state
        initGridState();
        //print the initial grid
        printGrid();
    }

    /* initialize the grid's state, create a 3 x 3 grid and set all values 0(with no chess piece)*/
    public void initGridState() {
        setGridState(new int[3][3]);
        int gridRow = 0;
        int gridColumn = 0;

        for (; gridRow < 3; gridRow++) {
            for (; gridColumn < 3; gridColumn++) {
                setOneGridState(gridRow, gridColumn, 0);
            }
        }
    }

    /* print grid state
    * -1 for Player O's chess piece
    * one space for no chess piece
    * 1 for Player X's chess piece
    * */
    public void printGrid() {
        for (int gridRow = 0; gridRow < 3; gridRow++) {
            for (int gridColumn = 0; gridColumn < 3; gridColumn++) {
                switch (getPracGridState(gridRow, gridColumn)) {
                    case -1:
                        System.out.print("O");
                        break;
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("X");
                        break;
                    default:
                        System.out.print("Wrong State Code!");
                        break;
                }
                if (gridColumn != 2) {
                    System.out.print("|");
                } else {
                    System.out.print("\n");
                }
            }
            if (gridRow != 2) {
                System.out.println("-----");
            }
        }
    }

    /* analyse and return game's state
    * decide who's the winner after 4 times movement
    * if one winner wins, return his name
    * if has moved 9 times without winner, this game is a draw
    * if this game can continue, return "Continue"
    * resultCode is the sum of grid's state
    * if resultCode = -1 or 1, this game has one winner
    * */
    public String getGameState(int movement) {
        int gridRow = 0;
        int gridColumn = 0;
        int resultCode = 0;

        if (movement < 5) {
            return "Continue";
        }

        //for each row
        for (gridRow = 0; gridRow < 3; gridRow++) {
            for (gridColumn = 0; gridColumn < 3; gridColumn++) {
                resultCode += getPracGridState(gridRow, gridColumn);
            }
            if (!getWinnerName(resultCode).equals("Continue")) {
                return getWinnerName(resultCode);
            }
            resultCode = 0;
        }

        //for each column
        for (gridColumn = 0; gridColumn < 3; gridColumn++) {
            for (gridRow = 0; gridRow < 3; gridRow++) {
                resultCode += getPracGridState(gridRow, gridColumn);
            }
            if (!getWinnerName(resultCode).equals("Continue")) {
                return getWinnerName(resultCode);
            }
            resultCode = 0;
        }

        //for diagonal
        for (gridRow = 0, gridColumn = 0; gridRow < 3 && gridColumn < 3;
             gridRow++, gridColumn++) {
            resultCode += getPracGridState(gridRow, gridColumn);
        }
        if (!getWinnerName(resultCode).equals("Continue")) {
            return getWinnerName(resultCode);
        }
        resultCode = 0;

        //for anti-diagonal
        for (gridRow = 0, gridColumn = 2; gridColumn > -1 && gridRow < 3;
             gridColumn--, gridRow++) {
            resultCode += getPracGridState(gridRow, gridColumn);
        }
        if (!getWinnerName(resultCode).equals("Continue")) {
            return getWinnerName(resultCode);
        }

        //has moved 9 times without winner
        if (movement == 9) {
            return "Draw";
        }

        return "Continue";
    }

    /* get winner name by analysing the resultCode
    * if resultCode equals 3, Player X won
    * if resultCode equals -3, Player O won
    * */
    private String getWinnerName(int resultCode) {
        if (resultCode == 3) {
            return "X won";
        } else if (resultCode == -3) {
            return "Y won";
        } else {
            return "Continue";
        }
    }

    /* run the game after initialization until one wins or draw
    * movement times belongs to 1 to 9, odd is player O's turn, even is X's turn
    * when the game is completed, print the result(a draw or winner's name)
    * */
    public void runGame(Scanner scanner, int movement) {
        String gameStateString = "Continue";
        int gridRow = 0;
        int gridColumn = 0;

        while (gameStateString.equals("Continue")) {
            movement++;

            if (movement % 2 == 0) {
                //System.out.println(getPlayerXName() + "'s move:");
            } else {
                //System.out.println(getPlayerOName() + "'s move:");
            }

            //access and store next chess piece's position
            gridRow = scanner.nextInt();
            gridColumn = scanner.nextInt();

            if (movement % 2 == 0) {
                setOneGridState(gridRow, gridColumn, 1);
            } else {
                setOneGridState(gridRow, gridColumn, -1);
            }

            //print grid and get game's state
            printGrid();
            gameStateString = getGameState(movement);
        }

        //After completion, print the result
        /*if (gameStateString.equals("Draw")) {
            System.out.println("Game over. It was a draw!");
        } else if (gameStateString.equals(getPlayerOName())) {
            System.out.println("Game over. " + getPlayerOName() + " won!");
        } else if (gameStateString.equals(getPlayerXName())) {
            System.out.println("Game over. " + getPlayerXName() + " won!");
        }*/
    }

}