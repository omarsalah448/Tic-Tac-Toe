import java.util.Scanner;

public class TicTacToe {
    private static boolean xTurn = true;
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static String board[][] = new String[ROWS][COLUMNS];
    private static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public TicTacToe() {
        initialize();
        drawGameBoard();
    }
    private static void initialize() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++)
                board[i][j] = " ";
        }
    }
    private static void printHorizontalLine() {
		System.out.print("+----");
		for (int i = 0; i < 2*COLUMNS-1; i++) {
			// if even 
			if (i % 2 == 0)
				System.out.print("+");
			// if odd
			else
				System.out.print("-----");
		}
		System.out.println();
	}
    private static void printHorizontalState(int startNumber, String[] boardRow) {
        System.out.print("| ");
		for (String position : boardRow) {
            if (position == " ")
                System.out.print(startNumber);
            else {
                System.out.print(ANSI_PURPLE);
                System.out.print(position);
                System.out.print(ANSI_RESET);
            }
            if (startNumber < 10 || position != " ") 
                System.out.print("  |  ");
            else 
                System.out.print(" |  ");
            startNumber++;
        }
        System.err.println();
	}
    public void drawGameBoard() {
        System.out.print(ANSI_CYAN);
        System.out.print("Assignment 1\n--------------\nOmar Salah, 6809");
        System.out.println(ANSI_RESET);
        for (int i = 0; i < ROWS; i++) {
            printHorizontalLine();
            printHorizontalState(i * COLUMNS, board[i]);
        }
        printHorizontalLine();
    }
    public int[] choosePosition() {
        System.out.print(ANSI_RED);
        if (xTurn)
            System.out.printf("Player X: Please Choose A Position: ");
        else
            System.out.printf("Player O: Please Choose A Position: ");
        System.out.print(ANSI_RESET);
        int position = scanner.nextInt();
        int col = position % COLUMNS;
        int row = (position - col) % ROWS;
        if (board[row][col] != " ") {
            System.out.println("Invalid Position");
            return choosePosition();
        }
        if (xTurn)
            board[row][col] = "X";
        else
            board[row][col] = "O";
        xTurn = !xTurn;
        return new int[] {row, col};
    }
    public boolean playerWon(int row, int col) {
        boolean won = horizontalWinCondition(row, col) 
               || verticalWinCondition(row, col) 
               || diagonalWinCondition(row, col)
               || backDiagonalWinCondition(row, col);
        if (won) {
            System.out.print(ANSI_GREEN);
            System.out.printf("Player %s won !\n", board[row][col]);
            System.out.println(ANSI_RESET);
        }
        return won;
    }
    private static boolean horizontalWinCondition(int row, int col) {
        return (equal(row, col, row, col+1, row, col+2)
                || equal(row, col-1, row, col, row, col+1)
                || equal(row, col-2, row, col-1, row, col));
    }
    private static boolean verticalWinCondition(int row, int col) {
        return (equal(row, col, row+1, col, row+2, col)
                || equal(row-1, col, row, col, row+1, col)
                || equal(row-2, col, row-1, col, row, col));
    }
    private static boolean diagonalWinCondition(int row, int col) {
        return (equal(row, col, row+1, col+1, row+2, col+2)
                || equal(row-1, col-1, row, col, row+1, col+1)
                || equal(row-2, col-2, row-1, col-1, row, col));
    }
    private static boolean backDiagonalWinCondition(int row, int col) {
        return (equal(row, col, row+1, col-1, row+2, col-2)
                || equal(row-1, col+1, row, col, row+1, col-1)
                || equal(row-2, col+2, row-1, col+1, row, col));
    }
    private static boolean equal(int r1, int c1, int r2, int c2, int r3, int c3) {
        if (r1 < 0 || r2 < 0 || r3 < 0) return false;
        if (c1 < 0 || c2 < 0 || c3 < 0) return false;
        if (r1 > ROWS-1 || r2 > ROWS-1  || r3 > ROWS-1 ) return false;
        if (c1 > COLUMNS-1 || c2 > COLUMNS-1 || c3 > COLUMNS-1) return false;
        String x = board[r1][c1], y = board[r2][c2], z = board[r3][c3];
        if (x == " " || y == " " || z == " ") return false;
        // System.out.printf("%s%d%d, %s%d%d, %s%d%d\n", x,r1,c1, y,r2,c2, z,r3,c3);

        return (x == y) && (y == z);
    }
}
