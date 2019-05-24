import java.util.Arrays;
import java.util.LinkedList;


public class SearchNode implements Comparable<SearchNode>{
	private EightPuzzleBoard current;
	private SearchNode parent;
	private String path;
	private int g;
	private int h;
	private int cost;
	
	public SearchNode(EightPuzzleBoard b){
		this.current=b;
		this.parent=null;
		this.path=null;
		this.cost=0;
		this.path = "";
		this.g=1;
		this.h=0;
	}
	
	public SearchNode(SearchNode parent, EightPuzzleBoard current, String move, int cost, int h){
		this.parent=parent;
		this.current=current;
		this.cost=cost;
		this.path=move;

	    this.h=h;
		
	}
	
	public EightPuzzleBoard getCurrent() {
		return this.current;
	}
	
	
	public SearchNode getParent() {
		return this.parent;
	}
	
	
	public String getPath(){
		return this.path;
	}
	
	public int getCost(){
		return this.cost;
	}
	
	
	
	public static LinkedList<SearchNode> genNext(SearchNode node, String h) {
		LinkedList<SearchNode> data = new LinkedList<SearchNode>();
		EightPuzzleBoard b = new EightPuzzleBoard(node.getCurrent().getData(),node.getCurrent().getBlankIndex());
		EightPuzzleBoard mod = new EightPuzzleBoard(b.getData(),b.getBlankIndex());
		
		
		
		if (b.getBlankIndex()>2) {
			EightPuzzleBoard childUp = new EightPuzzleBoard(b.getData(),b.getBlankIndex());
			EightPuzzleBoard.move("up",mod);
			childUp.setData(mod.getData(),mod.getBlankIndex());
			if (h.equals("h1")) {
				SearchNode temp1 = new SearchNode(node,childUp,"up",node.getCost()+1,childUp.h1());
				data.add(temp1);
			} else if (h.equals("h2")) {
				SearchNode temp1 = new SearchNode(node,childUp,"up",node.getCost()+1,childUp.h2());
				data.add(temp1);
			}
			mod.setData(b.getData(),b.getBlankIndex());
		} 
		
		if (b.getBlankIndex()<6) {
			EightPuzzleBoard childDown = new EightPuzzleBoard(b.getData(),b.getBlankIndex());
			EightPuzzleBoard.move("down",mod);
			childDown.setData(mod.getData(),mod.getBlankIndex());
			if (h.equals("h1")) {
				SearchNode temp2 = new SearchNode(node,childDown,"down",node.getCost()+1,childDown.h1());
				data.add(temp2);
			} else if (h.equals("h2")) {
				SearchNode temp2 = new SearchNode(node,childDown,"down",node.getCost()+1,childDown.h2());
				data.add(temp2);
			}
			mod.setData(b.getData(),b.getBlankIndex());
		}
		if ((b.getBlankIndex()+1) % 3 != 0) {
			EightPuzzleBoard childRight = new EightPuzzleBoard(b.getData(),b.getBlankIndex());
			EightPuzzleBoard.move("right",mod);
			childRight.setData(mod.getData(),mod.getBlankIndex());
			if (h.equals("h1")) {
				SearchNode temp3 = new SearchNode(node,childRight,"right",node.getCost()+1,childRight.h1());
				data.add(temp3);
			} else if (h.equals("h2")) {
				SearchNode temp3= new SearchNode(node,childRight,"down",node.getCost()+1,childRight.h2());
				data.add(temp3);
			}
			mod.setData(b.getData(),b.getBlankIndex());
		}

		if ((b.getBlankIndex()) % 3 != 0){
			EightPuzzleBoard childLeft = new EightPuzzleBoard(b.getData(),b.getBlankIndex());
			EightPuzzleBoard.move("left",mod);
			childLeft.setData(mod.getData(),mod.getBlankIndex());
			if (h.equals("h1")) {
				SearchNode temp4 = new SearchNode(node,childLeft,"left",node.getCost()+1,childLeft.h1());
				data.add(temp4);
			} else if (h.equals("h2")) {
				SearchNode temp4= new SearchNode(node,childLeft,"left",node.getCost()+1,childLeft.h2());
				data.add(temp4);
			}
			mod.setData(b.getData(),b.getBlankIndex());
		}

		return data;
		
	}

	private static String printBoard(LinkedList<SearchNode> s) {
		String result = "";
		int i = 0;
		while(i <s.size()){
			result += " " + Arrays.toString(s.get(i).getCurrent().getData());	
			i++;
		}
		return result;
	}
	public int compareTo(SearchNode node){
		if ((this.g+this.h)>(node.g+node.h)) {
			return 1;
		} else if ((this.g+this.h)<(node.g+node.h)) {
			return -1;
		} else {
			if (this.cost<node.cost){
				return 1;
			} else if (this.cost<node.cost){
				return -1;
			} else {
				return 0;
			}
		}
		
	}

}
