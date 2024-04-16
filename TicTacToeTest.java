

public class TicTacToeTest {
    public static void main(String args[]) {
        TicTacToe game = new TicTacToe();
        boolean gameFinished = false;
        while (!gameFinished) {
            int[] pos = game.choosePosition();
            gameFinished = game.playerWon(pos[0], pos[1]);
            game.drawGameBoard();
        }
        System.out.println("game finished");
    }
}
