import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class LocalBeam {

	
	private static int maxNodes =0;
	private static int cost;

	public static int getMaxNodes() {
		return maxNodes = 0;
	}
	
	public static int getCost(){
		return cost;
	}
	
	public void setMaxNodes(int i){
		maxNodes=i;
	}
	private static void addCost(int i){
		cost+=i;
		
	}
	
	public static void search(EightPuzzleBoard initial, int k){
		
		PriorityQueue<SearchNode> map;
		
		//Priority Queue created based off maxNodes. If maxNodes is default will give it 1000.
		
		if (getMaxNodes()==0) {
			map = new PriorityQueue<SearchNode>(10000,new NodeComparator());
		} else {
			map = new PriorityQueue<SearchNode>(maxNodes,new NodeComparator());
		}
		
		//initiates memory as a LinkedList of int arrays
		LinkedList<int[]> memory = new LinkedList<int[]>();
		
		//Creates the initial memory and map;

		memory.add(initial.getData());
		SearchNode parent = new SearchNode(initial);
		
		if(!parent.getCurrent().isGoal()) {
			LinkedList<SearchNode> children = SearchNode.genNext(parent, "h2"); 
			int i =0;
			LinkedList<SearchNode> tempMap = new LinkedList<SearchNode>();
			while(i<children.size()) {
				SearchNode child = children.poll();
				addCost(1);
				if (checkMemory(memory,child.getCurrent().getData())==false){
					
					tempMap.add(child);						
					
					memory.add(child.getCurrent().getData());
					
					i++;
					
				}
			}
			int j=0;
			while (j<k && j<tempMap.size()){
				map.add(tempMap.pop());
				j++;
			}
			
			
			SearchNode goal = search(map,memory,k);
			System.out.println("The total cost was: " + Integer.toString(getCost()));
			System.out.println(printPath(goal));
			
		} else {
			System.out.println("The total cost was: 0" );
			System.out.println("No Path, was goal state");
		}
	}
	
	private static SearchNode search(PriorityQueue<SearchNode> map, LinkedList<int[]> memory, int k){
		
		while(!map.peek().getCurrent().isGoal()){
			int i = 0;
			PriorityQueue<SearchNode> childMap = new PriorityQueue<SearchNode>(100, new NodeComparator());
			while(i<k && i<map.size()) {
				SearchNode parent = map.poll();
				LinkedList<SearchNode> children = SearchNode.genNext(parent, "h2"); 
				while (children.peek()!=null) {
					addCost(1);
					SearchNode grandchild = children.poll();
					if (checkMemory(memory,grandchild.getCurrent().getData())==false){
	
						childMap.add(grandchild);										
						
					}
				}	
				i++;
			}
			int j=0;
			while(j<k && j<childMap.size()){
				SearchNode temp = childMap.poll();
				if(temp.getCurrent().isGoal()){
					System.out.println("Reached Goal");
					return temp;
				} else {
					map.add(temp);
					memory.add(temp.getCurrent().getData());
					
					j++;
				}
				
			}
			
		}
		return new SearchNode(new EightPuzzleBoard());
	}
	
	
	static class NodeComparator implements Comparator<SearchNode> {
	     public int compare(SearchNode sn1, SearchNode sn2)
	     {
	         return sn1.compareTo(sn2);
	     }
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
}

