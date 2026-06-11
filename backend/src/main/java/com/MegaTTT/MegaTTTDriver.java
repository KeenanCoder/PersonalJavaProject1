//import java.util.Scanner;
//import others

import model.Board;

public class MegaTTTDriver {

	public static void main(String[] args) {

		Board board = new Board();

		board.setPosition(0, 0, 'O');
		board.setPosition(0, 1, 'X');
		board.setPosition(0, 2, 'O');

		board.setPosition(1, 0, 'X');
		board.setPosition(1, 1, 'X');
		board.setPosition(1, 2, 'O');

		board.setPosition(2, 0, 'O');
		board.setPosition(2, 1, 'O');
		board.setPosition(2, 2, 'X');

		board.printBoard();
	}
}
