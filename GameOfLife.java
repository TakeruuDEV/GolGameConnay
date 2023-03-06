package GolPack;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
    private boolean[][] board;
    private int rows;
    private int cols;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new boolean[rows][cols];
    }

    public void initializeBoard() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j]) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private int countLiveNeighbors(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i < 0 || i >= rows || j < 0 || j >= cols) {
                    continue;
                }
                if (board[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void nextGeneration() {
        boolean[][] newBoard = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                if (board[i][j]) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        newBoard[i][j] = false;
                    } else {
                        newBoard[i][j] = true;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        newBoard[i][j] = true;
                    } else {
                        newBoard[i][j] = false;
                    }
                }
            }
        }
        board = newBoard;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter a number to create generations: ");
        int generationNumber = scanner.nextInt(); 
        GameOfLife game = new GameOfLife(20, 20);
        game.initializeBoard();
        for (int i = 0; i < generationNumber; i++) {
            System.out.println("\n\nGeneration: " + (i + 1));
            game.printBoard();
            game.nextGeneration();
        }
        scanner.close();
    }
}