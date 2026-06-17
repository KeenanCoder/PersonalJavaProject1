import java.util.Scanner;
//import others

import model.Board;

public class MegaTTTDriver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Board board = new Board();

		int input1 = scanner.nextInt();
		int input2 = scanner.nextInt();
		char input3 = scanner.next().charAt(0);

		board.setPosition(input1, input2, input3);
		board.setPosition(input1, input2, input3);
		board.setPosition(input1, input2, input3);

		

		board.printBoard();
	}
}
