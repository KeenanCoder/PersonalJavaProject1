import java.util.Scanner;
//import others

import model.MainBoard;

public class MegaTTTDriver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		MainBoard mainBoard = new MainBoard();
		mainBoard.printMainBoard();

		char[] players = {'X', 'O'};
		int turn = 0;

		//loops the game while the whole board is not filled yet
		while(!mainBoard.isGameOver()){
			char player = players[turn % 2];
			System.out.println("\nPlayer " + player + "'s turn");

			int mainRow, mainCol, miniRow, miniCol;

			try{
			System.out.println("Enter the main row you want (0-2): ");
			mainRow = scanner.nextInt();
			System.out.println("Enter the main column you want (0-2: ");
			mainCol = scanner.nextInt();
			System.out.println("Enter the mini row you want (0-2): ");
			miniRow = scanner.nextInt();
			System.out.println("Enter the mini column you want (0-2): ");
			miniCol = scanner.nextInt();
			}
			catch (Exception e){
				System.out.println("Invalid input. Try again");
				scanner.nextLine();
				continue;
			}

			if(mainBoard.makeMove(mainRow, mainCol, miniRow, miniCol, player)){
				mainBoard.printMainBoard();
				turn++;
			}
		}
		char winner = mainBoard.getMainBoardWin();
		if(winner != ' '){
			System.out.println("\nPlayer " + winner + " wins the game!");
		}
		else{
			System.out.println("\nIt's a tie!");
		}
		scanner.close();
	}
}
