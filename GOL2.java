import java.util.Random;

public class GameOfLife {
    private int rows;
    private int cols;
    private int[][] board;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];

        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = random.nextInt(2);
            }
        }
    }

    public void display() {
        for (int[] row : this.board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "*" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] get_neighbors(int row, int col) {
        int[] neighbors = new int[8];
        int index = 0;
        for (int i = Math.max(0, row - 1); i < Math.min(rows, row + 2); i++) {
            for (int j = Math.max(0, col - 1); j < Math.min(cols, col + 2); j++) {
                if (i != row || j != col) {
                    neighbors[index++] = this.board[i][j];
                }
            }
        }
        return neighbors;
    }

    public int get_next_state(int row, int col) {
        int state = this.board[row][col];
        int live_neighbors = 0;
        for (int neighbor : get_neighbors(row, col)) {
            live_neighbors += neighbor;
        }
        if (state == 1 && (live_neighbors < 2 || live_neighbors > 3)) {
            return 0;
        } else if (state == 0 && live_neighbors == 3) {
            return 1;
        } else {
            return state;
        }
    }

    public void update_board() {
        int[][] new_board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                new_board[i][j] = get_next_state(i, j);
            }
        }
        this.board = new_board;
    }

    public static void main(String[] args) throws InterruptedException {
        int rows = 20;
        int cols = 40;
        GameOfLife game = new GameOfLife(rows, cols);

        while (true) {
            game.display();
            game.update_board();
            Thread.sleep(200);
        }
    }
}
