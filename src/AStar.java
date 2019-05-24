
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;


public class AStar {

	private static int maxNodes =0;

	public static int getMaxNodes() {
		return maxNodes;
	}
	
	public void setMaxNodes(int i){
		maxNodes=i;
	}
	
	/**
	 * Initial function for search method
	 * @param initial
	 * 			
	 * Starting board state
	 * 
	 * 
	 * @param heuristic
	 * 
	 * 
	 */
	
	public static void search(EightPuzzleBoard initial, String heuristic){
		
		PriorityQueue<SearchNode> map;
		
		//Priority Queue created based off maxNodes. If maxNodes is default will give it 1000.
		
		if (getMaxNodes()==0) {
			map = new PriorityQueue<SearchNode>(1000,new NodeComparator());
		} else {
			map = new PriorityQueue<SearchNode>(maxNodes,new NodeComparator());
		}
		
		//initiates memory as a LinkedList of int arrays
		LinkedList<int[]> memory = new LinkedList<int[]>();
		
		//Creates the initial memory and map;
		SearchNode root = new SearchNode(initial);
		map.add(root);
		memory.add(initial.getData());
		SearchNode parent = map.poll();
		int cost = 0;
			
			
			
			
			//Main Loop which checks for goal state
			while (!parent.getCurrent().isGoal()) {
				
				/**
				 * Generates a LinkedList of SearchNodes to be iterated through
				 * based off of the given heuristic
				 */
				LinkedList<SearchNode> children = SearchNode.genNext(parent, heuristic); 
							
				/**
				 * Loops through the list of generated children nodes, checking them against the memory
				 * and adding them to both the memory and map if they have not been evaluated already
				 */
				while (children.size()!=0) {
					SearchNode child = children.poll();
					if (checkMemory(memory,child.getCurrent().getData())==false){

						map.add(child);						
						
						memory.addFirst(child.getCurrent().getData());
						
					} 
				}
				parent = map.poll();
				cost+=1;
				
				
				
				
			}
			System.out.println("The total cost was: " + Integer.toString(cost));
			System.out.println(printPath(parent));
						

	}
	
	
	private static boolean checkMemory(LinkedList<int[]> memory, int[] data) {
		int i=0;
		while (i>-1 && i<memory.size()) {
			if (Arrays.equals(data, memory.get(i))) {
				return true;
			}
			i++;
		}

		return false;
	}
	
	private static String printPath(SearchNode s) {
		String result = "";
		int path = 0;
		while(s.getParent()!=null){
			result = " "+s.getPath() +result;	
			s=s.getParent();
			path++;
		}
		System.out.println("Total moves: "+Integer.toString(path));
		result  = "The path was:" + result;
		return result;
	}
	

	
	static class NodeComparator implements Comparator<SearchNode> {
	     public int compare(SearchNode sn1, SearchNode sn2)
	     {
	         return sn1.compareTo(sn2);
	     }
	 }
	
}
