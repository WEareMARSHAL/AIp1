
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class EightPuzzleBoard {
		
		private int[] data;		
		private final int[] GOAL = new int[] {0,1,2,3,4,5,6,7,8};     //
		private int blankIndex;
		

		//initial function
		//Creates board from string and checks if it is solvable and equal to goal state
		
		public EightPuzzleBoard() {	
				this.setGoal();
		}
		
		public EightPuzzleBoard(String s){
			
			this.setData(s);
		}
		
		public EightPuzzleBoard(int [] temp, int blank){
			this.setData(temp, blank);
		}
		//Gets the current board state as an integer array
		
		public int[] getData() {
			return this.data;
		}
		
		//Sets the current board state
		//Will only take input as a string contain "b" and the integers 1-8
		
		public void setData(String input) {
			if (input.length() == 9 && input.matches(".*[b].*") &&input.matches(".*[1-8].*")) {
				this.data = new int[9];
				String[] iter =  input.split("");
				for (int i=0; i<iter.length; i++) {
					if (Objects.equals(iter[i],"b")) {
						this.data[i] = 0;
						this.blankIndex=i;
					} else {
						this.data[i] = Integer.parseInt(iter[i]);
					}
				}
				this.isGoal();
				System.out.println("System Set");			
			} else {
				System.out.println("Invalid String Format");
			}
		}
		
		
		public void setData(int[] input, int blank) {
			this.data = new int[9];
			for (int i=0; i<this.data.length; i++) {
				this.data[i] = input[i];
			}
			this.setBlankIndex(blank);
		}
		
		private void setBlankIndex(int i){
			this.blankIndex=i;
		}
		
		public int getBlankIndex(){
			return this.blankIndex;
		}
		
		private void setGoal() {
			this.setData(this.GOAL,0);
			this.blankIndex=0;
			//this.isSolvable=true;
		}
		
		public boolean isGoal(){		
			
			//tester
			
			//System.out.println("Goal Called");
			
			//tester
		
			return compareArrays(GOAL,this.data);
				
		}
		
		public static void move(String direction, EightPuzzleBoard b) {
			int[] move = b.getData();
			int blank = b.getBlankIndex();
			
			if (Objects.equals(direction, "up") && blank>2) {
				move[blank] = move[blank-3];
				move[blank-3]=0;
				blank-=3;
				
			}else if (Objects.equals(direction, "down") && blank<6) {
				move[blank] = move[blank+3];
				move[blank+3]=0;
				blank+=3;
				
			}else if (Objects.equals(direction, "right") && (blank+1) % 3 != 0) {
				move[blank] = move[blank+1];
				move[blank+1]=0;
				blank+=1;	
				
			}else if (Objects.equals(direction, "left") && (blank) % 3 != 0) {
				move[blank] = move[blank-1];
				move[blank-1]=0;
				blank-=1;
			}

			b.setData(move,blank);
		}
		
		public static void randomizeState(int iter, EightPuzzleBoard b) {
			int i =0;
			b.setGoal();
			while (i<iter) {

				int random = (int)(Math.random()*4)+1;
				
				if (Objects.equals(random,1) && b.getBlankIndex()>2) {
					move("up",b);
					i++;
				} else if (Objects.equals(random,2) && b.getBlankIndex()<6) {
					move("down",b);
					i++;
				}else if (Objects.equals(random,3) && (b.getBlankIndex()+1) % 3 != 0) {
					move("right",b);
					i++;
				}else if (Objects.equals(random,4) && (b.getBlankIndex()) % 3 != 0){
					move("left",b);
					i++;
				}
			}
		}
		
		
		public int h1(){
			
			int h = 9;
			for(int i = 0; i<9; i++){
				if (this.GOAL[i]==this.data[i]) {
					h--;
				}
			}
			

			return h;
		}
		
		public int h2(){
			int h2=0;
			int index = 0;
			for (int y = 0; y < 9; y++){
					int board = this.data[index];
					int hor = Math.abs((board % 3) - (index % 3)); 
					int vert = Math.abs(board-index);
					h2+=hor + vert;
					index+=1;
			}
			return h2;
		}
		
		
		private static boolean compareArrays(int[] array1, int[] array2) {
	        boolean b = true;
	        if (array1 != null && array2 != null){
	          if (array1.length != array2.length)
	              b = false;
	          else
	              for (int i = 0; i < array2.length; i++) {
	                  if (array2[i] != array1[i]) {
	                      b = false;    
	                  }                 
	            }
	        }else{
	          b = false;
	        }
	        return b;
	    }


}
