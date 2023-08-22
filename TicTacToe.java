import java.util.*;

public class TicTacToe {
    public static Scanner sc = new Scanner(System.in);
    public static OneCell[][] matrix;
    public static char player;

    public static void main(String[] args) {
        // Clearing the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        startGame();
    }

    // Method to start a new game
    public static void startGame() {

        // Creating the matrix using OneCell objects and setting them to a default value
        matrix = new OneCell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = new OneCell(" ", false);
            }
        }


        // Setting the player
        System.out.println("Would you like to start with an X or an O?");
        char playerSetting = sc.next().charAt(0);
        if (playerSetting == 'X' || playerSetting == 'x') {
            setPlayer('X');
        }
        else if (playerSetting == 'O' || playerSetting == 'o') {
            setPlayer('O');
        }


        // Running the actual game
        printGrid(matrix);
        while(!(gameIsOver())) {
            takeUserInput();
            printGrid(matrix);
            changePlayer();
        }
        if (gameIsOver()) {
            System.out.println("Game over");
        }
    }


    // Method to print the grid
    public static void printGrid(OneCell[][] matrix) {
        int i = 0;
        for (; i < 2; i++) {
            System.out.println(" " + matrix[i][0].character + " | " + matrix[i][1].character + " | " + matrix[i][2].character + " ");            
            System.out.println("___|___|___");            
        }
        
        System.out.println(" " + matrix[i][0].character + " | " + matrix[i][1].character + " | " + matrix[i][2].character + " ");                     
        System.out.println("   |   |   \n");                     
        
    }


    // Method to set the player
    public static void setPlayer(char p) {
        player = p;
    }
    // Method to change the player
    public static void changePlayer() {
        if (player == 'X') {
            player = 'O';
        }
        else if (player == 'O') {
            player = 'X';
        }
    }


    // Method to take the input for coordinates from player
    public static void takeUserInput() {
        int[] coordinates = new int[2];
        System.out.println("Enter the coordinates where you would like to enter the " + player +":");
        coordinates[0] = sc.nextInt();
        coordinates[1] = sc.nextInt();
        System.out.println();

        // Setting the cell up with the input if it is empty
        if (matrix[coordinates[0]][coordinates[1]].isOccupied()) {
            System.out.println("That cell is already occupied. Please enter an empty cell");
            takeUserInput();
        }
        // To deal with invalid inputs
        else if ((coordinates[0] > 2) || (coordinates[1] > 2)) {
            System.out.println("This is an invalid input. Please enter inputs smaller than 3.");
            takeUserInput();
        }
        else {
            matrix[coordinates[0]][coordinates[1]].setCharacter(player);
            matrix[coordinates[0]][coordinates[1]].setOccupancy(true);
        }
    }


    // Methods to check emptiness of rows, columns and diagonals
    // Methods to check if the diagonals are empty
    public static boolean downwardsDiagonalIsEmpty() {
        boolean cond = true;

        for (int i = 0; i < 3; i++) {
            if (matrix[i][i].character != " ") {
                cond = false;
            }
        }

        return cond;
    }
    public static boolean upwardsDiagonalIsEmpty() {
        boolean cond = true;

        for (int i = 0; i < 3; i++) {
            if (matrix[2-i][i].character != " ") {
                cond = false;
            }
        }

        return cond;
    }

    // Method to check if a row on an index is full or not
    public static boolean rowIsEmpty(int rowindex) {
        // Will return false if any of the elements in the row is empty
        for (int i = 0; i < 3; i++) {
            if (matrix[rowindex][i].character != " ") {
                return false;
            }
        }

        return true;
    }
    // Method to check if a column on an index is full or not
    public static boolean columnIsEmpty(int columnindex) {
        // Will return false if any of the elements in the column is empty
        for (int i = 0; i < 3; i++) {
            if (matrix[i][columnindex].character != " ") {
                return false;
            }
        }

        return true;
    }
    

    // Method to check if the game is over or not
    public static boolean gameIsOver() {

        for (int i = 0; i < 3; i++) {
            // If one of the rows is full with Xs or Os
            if (!(rowIsEmpty(i))) {
                if ((matrix[i][0].character.equals(matrix[i][1].character)) && (matrix[i][1].character.equals(matrix[i][2].character))) {
                    System.out.println("Row " + i + " is full");
                    return true;
                }
            }

            // If one of the columns is full with Xs or Os
            if (!(columnIsEmpty(i))) {
                if ((matrix[0][i].character.equals(matrix[1][i].character)) && (matrix[1][i].character.equals(matrix[2][i].character))) {
                    return true;
                }
            }
        }
        // If one of the diagonals is full with Xs or Os
        if (!(downwardsDiagonalIsEmpty())) {
            if ((matrix[0][0].character.equals(matrix[1][1].character)) && (matrix[1][1].character.equals(matrix[2][2].character))) {
                return true;
            }
        }
        if (!(upwardsDiagonalIsEmpty()))  {
            if ((matrix[2][0].character.equals(matrix[1][1].character)) && (matrix[1][1].character.equals(matrix[0][2].character))) {
                return true;
            }
        }

        
        // If all the cells are full
        boolean cond = true;
        for (OneCell[] i: matrix) {
            for (OneCell j: i) {
                if (j.occupied == false) {
                    cond = false;
                }
            }
        }
        return cond;

    }

    
}
