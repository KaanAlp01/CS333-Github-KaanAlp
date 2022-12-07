package connect4;

import java.util.LinkedList;
import java.util.Scanner;

public class connect4 {
    private char[][] board;
    Scanner scan = new Scanner(System.in);
    private Boolean state;
    private int height;
    private int widht;
    private char pPuck;
    private char cPuck;
    private int level;

    public void initiateBoard() {
        this.state = false;
        this.height = 6;
        this.widht = 7;
        this.pPuck = 'X';
        this.cPuck = 'O';
        char[][] board = new char[height + 5][widht + 5];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < widht; j++) {
                board[i][j] = '#';
            }
        }
        this.board = board;
        System.out.println("Enter search level for the computer:");
        this.level = Integer.parseInt(scan.nextLine());

    }

    public void showBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < widht; j++) {
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }

    public void playerTurn() {
        System.out.println("Pick a column: (a number between 1-7)");
        int column = Integer.parseInt(scan.nextLine());
        putPuck(column, this.pPuck);
        this.state = checkState();
        if (this.state == true) {
            System.out.println();
            System.out.println("Congratulations player you won!");
        }

    }

    public void computerTurn() {
        putPuck(computerLogic(this.level), this.cPuck);
        this.state = checkState();
        if (this.state == true) {
            System.out.println();
            System.out.println("The Computer Won!! try again :/");
        }

    }

    private void putPuck(int column, char puck) {
        if (column <= widht) {
            column--;
            for (int row = height; row >= 0; row--) {
                if (board[row][column] == '#') {
                    board[row][column] = puck;
                    break;
                }
            }
        } else {
            System.out.println("Please enter a valid column.");
            column = Integer.parseInt(scan.nextLine());
            putPuck(column, puck);
        }

    }

    public Boolean getState() {
        return state;
    }

    private boolean checkState() {
        for (int row = height; row > 0; row--) {
            for (int column = 0; column < widht - 3; column++) {
                if (board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2] &&
                        board[row][column] == board[row][column + 3]) {
                    if (board[row][column] == pPuck) {
                        return true;
                    } else if (board[row][column] == cPuck)
                        return true;
                }
            }
        }
        for (int column = 0; column < widht; column++) {
            for (int row = height; row >= 3; row--) {
                if (board[row][column] == board[row - 1][column] &&
                        board[row][column] == board[row - 2][column] &&
                        board[row][column] == board[row - 3][column]) {

                    if (board[row][column] == pPuck) {
                        return true;
                    } else if (board[row][column] == cPuck)
                        return true;
                }
            }
        }
        for (int column = 0; column <= 3; column++) {
            for (int row = height; row >= 3; row--) {
                if (board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2] &&
                        board[row][column] == board[row - 3][column + 3]) {

                    if (board[row][column] == pPuck) {
                        return true;
                    } else if (board[row][column] == cPuck)
                        return true;
                }
            }
        }
        for (int column = 0; column <= 3; column++) {
            for (int row = 0; row < 3; row++) {
                if (board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2] &&
                        board[row][column] == board[row + 3][column + 3]) {
                    if (board[row][column] == pPuck) {
                        return true;
                    } else if (board[row][column] == cPuck)
                        return true;
                }
            }
        }
        return false;
    }


    private int computerLogic(int level) {
        LinkedList searchList = new LinkedList(); // will be used for bfs implentation
        int max_weight = 0;
        int weight = 10;
        int selecetedColumn = 1;
        for (int i = 0; i <= level; i++) {

        }
        for (int row = height; row >= 0; row--) {
            for (int column = 0; column < widht; column++) {
                for (int i = 0; i <= level; i++) {
                    searchList.add(board[height][widht]);
                    selecetedColumn = column;
                }
                if (board[row][column] == '#'){
                    if (row > 1 && board[row - 1][column] == '#');
                    else {
                        if (board[row][column] == pPuck) {
                            weight += 2;
                        }
                        if (board[row][column] == board[row][column + 1] && board[row][column] == board[row][column + 2] ||
                                board[row][column] == board[row + 1][column] && board[row][column] == board[row + 2][column] ||
                                board[row][column] == board[row + 1][column + 1] && board[row][column] == board[row + 2][column + 2])
                            weight += 5;
                        if (board[row][column] == board[row][column + 1] ||
                                board[row][column] == board[row + 1][column] ||
                                board[row][column] == board[row + 1][column + 1])
                            weight += 4;

                        if (board[row][column] == cPuck) {
                            weight += 1;
                        }
                        if (row >= 3) {
                            if (board[row][column] == board[row - 1][column + 1] && board[row][column] == board[row - 2][column + 2])
                                weight += 3;
                            if (board[row][column] == board[row - 1][column + 1])
                                weight +=2;
                        }

                        if (weight > max_weight) {
                            max_weight = weight;
                            selecetedColumn = column + 1;
                        }
                    }
                }
            }
            }

        return selecetedColumn;
    }
    }



