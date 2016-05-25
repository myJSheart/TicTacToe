import java.io.*;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Chenxu ZHAO 726503
 * @Vision: 20/05/2015
 */

 /* Main class which provides management of command lines to manage whole system
  * and static main method to run this system
 * */
public class TicTacToe {

    //user's input
    public static Scanner input = new Scanner(System.in);

    //Commands that can be input by users
    private static String exitgame = "exit";//exit system
    private static String addPlayer = "addplayer";//add new human player
    private static String addAIPlayer = "addaiplayer";//add new ai player
    private static String removePlayer = "removeplayer";//remove users
    private static String editPlayer = "editplayer";//modify users' informaiton
    private static String resetStats = "resetstats";//reset users' statistics informaiton
    private static String displayPlayer = "displayplayer";//display users' informaiton
    private static String rankings = "rankings";//display rank informaiton
    private static String playGame = "playgame";//play a game between two users

    /*Constructor*/
    public TicTacToe() {
        super();
    }

    /* Operate the game system with command lines*/
    public void operateSystem(PlayerManager playerManager, GameManager gameManager) {
        playerManager.resumeInformation();
        System.out.println("Welcome to Tic Tac Toe!");
        String[] command;
        while (true) {
            try {
                System.out.println();
                System.out.print(">");

                command = input.nextLine().split(" ");//split user's input by space

                if (command[0].equals("")){//null command
                    continue;
                }

                if (addPlayer.equalsIgnoreCase(command[0])) {
                    if (command[1].split(",").length != 3) {//not enough arguments
                        throw new Exception("Incorrect number of arguments supplied to command.");
                    }
                    playerManager.addPlayer(command[1].split(",")[0],
                            command[1].split(",")[1],
                            command[1].split(",")[2]);
                } else if (exitgame.equalsIgnoreCase(command[0])) {
                    playerManager.storageInformation();
                    System.out.println();
                    System.exit(0);
                } else if (removePlayer.equalsIgnoreCase(command[0])) {
                    if (command.length == 1) {
                        System.out.println("Are you sure you want to remove all players? (y/n)");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            playerManager.removeAllPlayers();
                        }
                    } else {
                        playerManager.removePlayer(command[1].split(",")[0].trim());
                    }
                } else if (editPlayer.equalsIgnoreCase(command[0])) {
                    if (command[1].split(",").length != 3) {//not enough arguments
                        throw new Exception("Incorrect number of arguments supplied to command.");
                    }
                    playerManager.modifyPlayer(command[1].split(",")[0],
                            command[1].split(",")[1],
                            command[1].split(",")[2]);
                } else if (resetStats.equalsIgnoreCase(command[0])) {
                    if (command.length == 1) {
                        System.out.println("Are you sure you want to " +
                                "reset all player statistics? (y/n)");
                        if (input.nextLine().equalsIgnoreCase("y")) {
                            playerManager.resetAllPlayers();
                        }
                    } else {
                        playerManager.resetPlayer(command[1].split(",")[0].trim());
                    }
                } else if (displayPlayer.equalsIgnoreCase(command[0])) {
                    if (command.length == 1) {
                        playerManager.displayAllPlayers();
                    } else {
                        playerManager.displayPlayer(command[1].split(",")[0].trim());
                    }
                } else if (rankings.equalsIgnoreCase(command[0])) {
                    playerManager.rankPlayer();
                } else if (playGame.equalsIgnoreCase(command[0])) {
                    if (command[1].split(",").length != 2) {//not enough arguments
                        throw new Exception("Incorrect number of arguments supplied to command.");
                    }
                    if (playerManager.checkTwoPlayers(command[1].split(",")[0].trim(),
                            command[1].split(",")[1].trim())) {
                        gameManager.playGame(playerManager, input, command[1].split(",")[0].trim()
                                , command[1].split(",")[1].trim());
                    }
                } else if (addAIPlayer.equalsIgnoreCase(command[0])) {
                    if (command[1].split(",").length != 3) {//not enough arguments
                        throw new Exception("Incorrect number of arguments supplied to command.");
                    }
                    playerManager.addAIPlayer(command[1].split(",")[0],
                            command[1].split(",")[1],
                            command[1].split(",")[2]);
                } else {//the command is invalid
                    throw new Exception("\'" + command[0] + "\' is not a valid command.");
                }
            } catch (Exception e) {//print the exception message
                System.out.println(e.getMessage());
            }
        }
    }

    /*run this system*/
    public void run() {
        PlayerManager playerManager = new PlayerManager();
        GameManager gameManager = new GameManager();
        operateSystem(playerManager, gameManager);
    }

    /*main method*/
    public static void main(String args[]) {
        TicTacToe gameSystem = new TicTacToe();
        gameSystem.run();
    }

}

abstract class Player {

    /*Default Constructor*/
    public Player() {
        super();
    }

    /*Constructor with argunments*/
    public Player(String userName, String familyName, String givenName) {
        setUserName(userName);
        setFamilyName(familyName);
        setGivenName(givenName);
        setPlayedGames(0);
        setWonGames(0);
        setDrawnGames(0);
    }

    private String userName;//user's user name
    private String familyName;//user's family name
    private String givenName;//user's given name
    private int playedGames;//the number of games played by a user
    private int wonGames;//the number of games won by a user
    private int drawnGames;//the number of games drawn by a user

    abstract public Move makeMove(char[][] gameBoard);
    /* get and set methods for all private variable*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public int getDrawnGames() {
        return drawnGames;
    }

    public void setDrawnGames(int drawnGames) {
        this.drawnGames = drawnGames;
    }

}

/**
 * Providing management of players
 */
class PlayerManager {

    private ArrayList<Player> players = new ArrayList<>();

    /*Constructor*/
    public PlayerManager() {
        super();
    }

    /*get method for Players*/
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /*set method for Players*/
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /* Add a new player
    * If this user is existed already, print out waring informaiton*/
    public void addPlayer(String userName, String familyName, String givenName) {

        //Get the index of the player
        int index = checkRepUserName(userName);
        if (index == -1) {
            players.add(new HumanPlayer(userName, familyName, givenName));
        } else {
            System.out.println("The username has been used already.");
        }
    }

    /* Add an AI player
    * If this user is existed already, print out waring informaiton*/
    public void addAIPlayer(String userName, String familyName, String givenName) {

        //Get the index of the player
        int index = checkRepUserName(userName);
        if (index == -1) {
            players.add(new AIPlayer(userName, familyName, givenName));
        } else {
            System.out.println("The username has been used already.");
        }
    }

    /* Remove one player
    * If this player does not exist, print out warning information*/
    public void removePlayer(String userName) {
        try {
            players.remove(checkRepUserName(userName));
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("The player does not exist.");
        }
    }

    /* Remove all players*/
    public void removeAllPlayers() {
        players.clear();
    }

    /* Modify the name of a player
    * if this user does not exist, print warning information*/
    public void modifyPlayer(String userName, String familyName, String givenName) {
        //Get the index of the player
        int index = checkRepUserName(userName);
        if (index != -1) {
            players.get(checkRepUserName(userName)).setFamilyName(familyName);
            players.get(checkRepUserName(userName)).setGivenName(givenName);
        } else {
            System.out.println("The player does not exist.");
        }
    }

    /* Reset the statistic of one players
    * if this user does not exist, print warning information*/
    public void resetPlayer(String userName) {
        //Get the index of the player
        int index = checkRepUserName(userName);
        if (index != -1) {
            players.get(index).setPlayedGames(0);
            players.get(index).setDrawnGames(0);
            players.get(index).setWonGames(0);
        } else {
            System.out.println("The player does not exist.");
        }
    }

    /* Reset the statistic of all players
    * if this user does not exist, print warning information*/
    public void resetAllPlayers() {
        if (players.size() > 0) {
            for (Player player : players) {
                resetPlayer(player.getUserName());
            }
        } else {
            System.out.println("The player does not exist.");
        }
    }

    /*Display the information of one player
    * if this user does not exist, print warning information*/
    public void displayPlayer(String userName) {
        //Get the index of the player
        int index = checkRepUserName(userName);
        if (index != -1) {
            System.out.println(players.get(index).getUserName() + ","
                    + players.get(index).getFamilyName() + ","
                    + players.get(index).getGivenName() + ","
                    + players.get(index).getPlayedGames() + " games,"
                    + players.get(index).getWonGames() + " wins,"
                    + players.get(index).getDrawnGames() + " draws");
        } else {
            System.out.println("The player does not exist.");
        }
    }

    public void displayAllPlayers() {

        Comparator<Player> rule = new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        };

        Collections.sort(players, rule);

        if (players.size() > 0) {
            for (Player player : players) {
                displayPlayer(player.getUserName());
            }
        } else {
            //System.out.println("The player does not exist.");
        }

    }

    /*Rank the players based on winning history. Utilizing select the rank first every time*/
    public void rankPlayer() {

        //rules of sorting players
        Comparator<Player> rule = new Comparator<Player>() {
            @Override
            //compare two players: o1 and o2
            public int compare(Player o1, Player o2) {
                //both o1 and o2 played games
                if (o1.getPlayedGames() != 0 && o2.getPlayedGames() != 0) {
                    if ((1.0 * o1.getWonGames() / o1.getPlayedGames()) >
                            (1.0 * o2.getWonGames() / o2.getPlayedGames())) {
                        return -1;//o1's win rate larger than o2
                    } else if ((1.0 * o1.getWonGames() / o1.getPlayedGames())
                            < (1.0 * o2.getWonGames() / o2.getPlayedGames())) {
                        return 1;//o1's win rate less than o2
                    } else {//o1's win rate equal to o2's win rate
                        if ((1.0 * o1.getDrawnGames() / o1.getPlayedGames()) >
                                (1.0 * o2.getDrawnGames() / o2.getPlayedGames())) {
                            return -1;//o1's drawn rate lager than o2
                        } else if ((1.0 * o1.getDrawnGames() / o1.getPlayedGames())
                                == (1.0 * o2.getDrawnGames() / o2.getPlayedGames())) {
                            return o1.getUserName().compareTo(o2.getUserName());
                        } else {
                            return 1;//o1's drawn rate less than o2
                        }
                    }
                    //o1 didn't play game, while o2 played games
                } else if (o1.getPlayedGames() == 0 && o2.getPlayedGames() != 0) {
                    if (o2.getWonGames() > 0 || o2.getDrawnGames() > 0) {
                        return 1;//o2 won or drawn games
                    } else {
                        return o1.getUserName().compareTo(o2.getUserName());
                    }
                    //o2 didn't play game, while o1 played games
                } else if (o1.getPlayedGames() != 0 && o2.getPlayedGames() == 0) {
                    if (o1.getWonGames() > 0 || o1.getDrawnGames() > 0) {
                        return -1;//o1 won or drawn games
                    } else {
                        return o1.getUserName().compareTo(o2.getUserName());
                    }
                    //both o1 and o2 didn't play games
                } else if (o1.getPlayedGames() == 0 && o2.getPlayedGames() == 0) {
                    return o1.getUserName().compareTo(o2.getUserName());
                }

                return 0;
            }
        };

        //Sort the players
        Collections.sort(players, rule);

        //The number of players on the ranking list
        int rankNumber = 0;
        if (players.size() > 0) {
            if (players.size() > 10) {
                rankNumber = 10;
            } else {
                rankNumber = players.size();
            }
        } else {
            System.out.println("The player does not exist.");
        }

        //Print the result
        System.out.println(" WIN  | DRAW | GAME | USERNAME");
        for (int index = 0; index < rankNumber; index++) {
            //Unformatted win rate
            double winRate = 0;
            //Unformatted draw rate
            double drawRate = 0;
            //calculate winRate and drawRate when the number of played games does not equal 0
            if (players.get(index).getPlayedGames() != 0) {
                winRate = 1.0 * players.get(index).getWonGames()
                        / players.get(index).getPlayedGames();
                drawRate = 1.0 * players.get(index).getDrawnGames()
                        / players.get(index).getPlayedGames();
            }

            System.out.printf("%4.0f", new BigDecimal(winRate)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
            System.out.print("% |");
            System.out.printf("%4.0f", new BigDecimal(drawRate)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
            System.out.print("% |  ");
            // length of the third column
            int thirdLength = 4;
            while (thirdLength > 0) {
                if (thirdLength == 4) {
                    System.out.print(players.get(index).getPlayedGames());
                    thirdLength -= (players.get(index).getPlayedGames() + "").length();
                } else {
                    System.out.print(" ");
                    thirdLength--;
                }
            }
            System.out.println("| " + players.get(index).getUserName());
        }
    }

    /* Check whether the username is existed or not
    * @param the username of the checked user, index is the index of players
    * @return the index of the username, or -1 is not existed
    * */
    private int checkRepUserName(String userName) {
        for (int index = 0; index < players.size(); index++) {
            if (players.get(index).getUserName().equalsIgnoreCase(userName)) {
                return index;
            }
        }
        return -1;
    }

    /* Check two player's user name, if they are not existed,
    * show warning information and return false
    * */
    public boolean checkTwoPlayers(String player1, String player2) {
        if (checkRepUserName(player1) == -1 || checkRepUserName(player2) == -1) {
            System.out.println("Player does not exist.");
            return false;
        } else {
            return true;
        }
    }

    /*use username to get corresponding user's given name*/
    public String getGivenName(String userName) {
        for (Player player : players) {
            if (player.getUserName().equalsIgnoreCase(userName)) {
                return player.getGivenName();
            }
        }
        return null;
    }

    /*use username to get corresponding user's index number*/
    public int getIndex(String userName) {
        for (int index = 0; index < players.size(); index++) {
            if (players.get(index).getUserName().equalsIgnoreCase(userName)) {
                return index;
            }
        }
        return -1;
    }

    /*save users' data when exits*/
    public void storageInformation() {
        try {
            File playersFile = new File("players.dat");
            //delete this file if it is existed
            if (playersFile.exists()) {
                playersFile.delete();
            }
            //create a new file
            playersFile.createNewFile();
            //write in the file
            FileOutputStream playersOut = new FileOutputStream(playersFile, true);
            for (int count = 0; count < players.size(); count++) {
                playersOut.write((players.get(count).getUserName() + " "
                        + players.get(count).getFamilyName() + " "
                        + players.get(count).getGivenName() + " "
                        + players.get(count).getPlayedGames() + " "
                        + players.get(count).getWonGames() + " "
                        + players.get(count).getDrawnGames() + " "
                        + players.get(count).getClass() + "\n").getBytes());
            }
            playersOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /*recover users' data from players.dat*/
    public void resumeInformation() {
        try {
            File playersFile = new File("players.dat");
            //open and read this file if it is existed
            if (playersFile.exists()) {
                FileInputStream playersIn = new FileInputStream(playersFile);
                BufferedReader playersReader =
                 new BufferedReader(new InputStreamReader(playersIn));
                String playersLine = playersReader.readLine();
                //in case the file is null
                if (playersLine == null) {
                    return;
                }
                Player tempPlayer;
                while (playersLine != null) {
                    //decide the class type of this method
                    if (playersLine.split(" ")[7].equals("HumanPlayer")) {
                        tempPlayer = new HumanPlayer();
                    } else {
                        tempPlayer = new AIPlayer();
                    }
                    //read player's information and store them in an ArrayList of players
                    tempPlayer.setUserName(playersLine.split(" ")[0]);
                    tempPlayer.setFamilyName(playersLine.split(" ")[1]);
                    tempPlayer.setGivenName(playersLine.split(" ")[2]);
                    tempPlayer.setPlayedGames(Integer.parseInt(playersLine.split(" ")[3]));
                    tempPlayer.setWonGames(Integer.parseInt(playersLine.split(" ")[4]));
                    tempPlayer.setDrawnGames(Integer.parseInt(playersLine.split(" ")[5]));
                    players.add(tempPlayer);
                    playersLine = playersReader.readLine();
                }
                playersReader.close();
                playersIn.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

/*Manage one game and update statistics information after the game*/
class GameManager {

    private char[][] gameBoard;

    public GameManager() {
        super();
    }

    //get method of gameBoard
    public char[][] getGameBoard() {
        return gameBoard;
    }

    //set method of gridState
    public void setGridState(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /* initialize the grid's state
    * create a 3 x 3 grid and set all values 0(with no chess piece)*/
    public void initGridState() {
        setGridState(new char[3][3]);
        int gridRow = 0;
        int gridColumn = 0;

        for (gridRow = 0; gridRow < 3; gridRow++) {
            for (gridColumn = 0; gridColumn < 3; gridColumn++) {
                gameBoard[gridRow][gridColumn] = 32;
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
                System.out.print(gameBoard[gridRow][gridColumn]);
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

    /* initialize game: get the users' names, set and print grid without chess piece*/
    public void initGame() {
        initGridState();
        printGrid();
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

        if (movement < 5) {
            return "Continue";
        }

        //for each row
        for (gridRow = 0; gridRow < 3; gridRow++) {
            if (gameBoard[gridRow][0] == 'X'
                    && gameBoard[gridRow][1] == 'X'
                    && gameBoard[gridRow][2] == 'X') {
                return "X";
            } else if (gameBoard[gridRow][0] == 'O'
                    && gameBoard[gridRow][1] == 'O'
                    && gameBoard[gridRow][2] == 'O') {
                return "O";
            }
        }

        //for each column
        for (gridColumn = 0; gridColumn < 3; gridColumn++) {
            if (gameBoard[0][gridColumn] == 'X'
                    && gameBoard[1][gridColumn] == 'X'
                    && gameBoard[2][gridColumn] == 'X') {
                return "X";
            } else if (gameBoard[0][gridColumn] == 'O'
                    && gameBoard[1][gridColumn] == 'O'
                    && gameBoard[2][gridColumn] == 'O') {
                return "O";
            }
        }

        //for diagonal
        if (gameBoard[0][0] == 'X'
                && gameBoard[1][1] == 'X'
                && gameBoard[2][2] == 'X') {
            return "X";
        } else if (gameBoard[0][0] == 'O'
                && gameBoard[1][1] == 'O'
                && gameBoard[2][2] == 'O') {
            return "O";
        }

        //for anti-diagonal
        if (gameBoard[0][2] == 'X'
                && gameBoard[1][1] == 'X'
                && gameBoard[2][0] == 'X') {
            return "X";
        } else if (gameBoard[0][2] == 'O'
                && gameBoard[1][1] == 'O'
                && gameBoard[2][0] == 'O') {
            return "O";
        }

        //has moved 9 times without winner
        if (movement == 9) {
            return "Draw";
        }

        return "Continue";
    }

    /*check invalid movement and return corresponding waring information*/
    public boolean checkGridMovement(Move newMove) {
        if (newMove.getRow() < 0 || newMove.getRow() > 2
        || newMove.getColumn() < 0 || newMove.getColumn() > 2) {
            System.out.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
            return false;
        } else if (gameBoard[newMove.getRow()][newMove.getColumn()] != ' ') {
            System.out.println("Invalid move. The cell has been occupied.");
            return false;
        }
        return true;
    }

    /*play a game between two players: PlayerO(userNameO) and PlayerX(userNameX)*/
    public void playGame(PlayerManager playerManager,
    Scanner scanner, String userNameO, String userNameX) {

        //state of a game
        String gameStateString = "Continue";
        int movement = 0;//number of movement

        String givenNameO = playerManager.getGivenName(userNameO);
        String givenNameX = playerManager.getGivenName(userNameX);

        //initialize a game
        initGame();

        while (gameStateString.equals("Continue")) {
            movement++;

            //access and store next chess piece's position
            Move newMove;
            if (movement % 2 == 0) {
                System.out.println(givenNameX + "'s move:");
                newMove = playerManager.getPlayers().
                get(playerManager.getIndex(userNameX)).makeMove(gameBoard);
            } else {
                System.out.println(givenNameO + "'s move:");
                newMove = playerManager.getPlayers().
                get(playerManager.getIndex(userNameO)).makeMove(gameBoard);
            }

            if (!checkGridMovement(newMove)) {
                movement--;
                continue;
            }

            if (movement % 2 == 0) {
                gameBoard[newMove.getRow()][newMove.getColumn()] = 'X';
            } else {
                gameBoard[newMove.getRow()][newMove.getColumn()] = 'O';
            }

            //print grid and get game's state
            printGrid();
            gameStateString = getGameState(movement);
        }

        //two players' playedGames is added 1
        playerManager.getPlayers().get(playerManager.getIndex(userNameO))
                .setPlayedGames(playerManager.getPlayers().get(playerManager
                        .getIndex(userNameO)).getPlayedGames() + 1);
        playerManager.getPlayers().get(playerManager.getIndex(userNameX))
                .setPlayedGames(playerManager.getPlayers().get(playerManager
                        .getIndex(userNameX)).getPlayedGames() + 1);

        //update two players' statistics information according to the result
        if (gameStateString.equals("Draw")) {
            System.out.println("Game over. It was a draw!");
            //drawnGames + 1
            playerManager.getPlayers().get(playerManager.getIndex(userNameO))
                    .setDrawnGames(playerManager.getPlayers().get(playerManager
                            .getIndex(userNameO)).getDrawnGames() + 1);
            playerManager.getPlayers().get(playerManager.getIndex(userNameX))
                    .setDrawnGames(playerManager.getPlayers().get(playerManager
                            .getIndex(userNameX)).getDrawnGames() + 1);
        } else if (gameStateString.equals("O")) {
            //PlayerO's wonGames + 1
            System.out.println("Game over. " + givenNameO + " won!");
            playerManager.getPlayers().get(playerManager.getIndex(userNameO))
                    .setWonGames(playerManager.getPlayers().get(playerManager
                            .getIndex(userNameO)).getWonGames() + 1);
        } else if (gameStateString.equals("X")) {
            //PlayerO's wonGames + 1
            System.out.println("Game over. " + givenNameX + " won!");
            playerManager.getPlayers().get(playerManager.getIndex(userNameX))
                    .setWonGames(playerManager.getPlayers().get(playerManager
                            .getIndex(userNameX)).getWonGames() + 1);
        }
        //read the last '\n'
        scanner.nextLine();
    }

}

/*Record one move of a user*/
class Move {

    int row;
    int column;

    /*Constructor*/
    public Move() {

    }

    /*Constructor with arguments*/
    public Move(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    /*get and set method of row and column*/

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

/* Represent an ai player which extends from the abstract Player.
* Contain the strategy to play*/
class AIPlayer extends Player {

    /*default constructor*/
    public AIPlayer() {

    }

    /*constructor with arguments*/
    public AIPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    /*Return movement based on ai strategy*/
    @Override
    public Move makeMove(char[][] gameBoard) {
        int row;
        int column = 0;
        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++) {
                if (gameBoard[row][column] == 32 || gameBoard[row][column] == 0) {
                    return (new Move(row, column));
                }
            }
        }
        return (new Move(row, column));
    }
}

/* Represent a human player which extends from the abstract Player
* Read movement from the input by human*/
class HumanPlayer extends Player {

    /*Default Constructor*/
    public HumanPlayer() {

    }

    /*Constructor with arguments*/
    public HumanPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    /*Return movement from human user's input*/
    @Override
    public Move makeMove(char[][] gameBoard) {
        Move humanMove = new Move();
        humanMove.setRow(TicTacToe.input.nextInt());
        humanMove.setColumn(TicTacToe.input.nextInt());
        return humanMove;
    }
}
