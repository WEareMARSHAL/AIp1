import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Solver {

	public static void main(String args[]) throws IOException {
		Scanner scan = new Scanner(System.in);
		String command = "";
		EightPuzzleBoard active = new EightPuzzleBoard();
		
		// Testing shit
		int x =10;
		int rando = 10000;
		long h1time = 0;
		long h2time = 0;
		for(int i=0; i< x;i++){
			EightPuzzleBoard.randomizeState( rando,active);
			long startTime = System.nanoTime();
			AStar.search(active,"h1");
			long endTime = System.nanoTime();
			h1time+=endTime-startTime;
			long startTime2 = System.nanoTime();
			AStar.search(active,"h2");
			long endTime2 = System.nanoTime();
			h2time+=endTime2-startTime2;
			EightPuzzleBoard.randomizeState(0,active);
		}
		System.out.println("H1 Time: " +Long.toString(h1time/x/1000000)+ " ms");
		System.out.println("H2 Time: " +Long.toString(h2time/x/1000)+ " ms");
		do {
			System.out.println("Enter a command:\nsetState 'String',  randomizeState 'int', printState \n"
					+ "move 'String', "
					+ "\n(Type 'Exit' to close)");
			try {
				command = scan.next();
				if (command.contains("setState")){
					command=scan.next();
					System.out.println(command);
					active = new EightPuzzleBoard(command);
				} else if (command.contains("randomizeState")){
					int rand=scan.nextInt();
					EightPuzzleBoard.randomizeState(rand,active);
					System.out.println(Arrays.toString(active.getData()));
				} else if (command.contains("printState")){
					System.out.println(Arrays.toString(active.getData()));
				} else if (command.contains("move")){
					command=scan.next();

					EightPuzzleBoard.move(command, active);
				

					System.out.println(Arrays.toString(active.getData()));
					System.out.println("Blank Index: " + Integer.toString(active.getBlankIndex()));
					System.out.println("H1: " + Integer.toString(active.h1()));
				} else if (command.contains("solve")){
					command=scan.next();
					if (command.contains("A-Star")){
						command=scan.next();
						AStar.search(active,command);
					} else if (command.contains("beam")){
						int k=scan.nextInt();
						LocalBeam.search(active, k);
					}
				}
				
			}catch(InputMismatchException e){
				System.out.println("Invalid input ");
			}
		} while(!Objects.equals(command, "Exit"));
				
	}
	
	
}
