package assignment3;

import java.util.ArrayList;

public class Node {

    //All words that have a "path" to the Node
    private ArrayList<Integer> pairs;

    //Whether or not the Node has been visited, used in Graph traversal
    private boolean visited;

    //The String associated with the Node
    private String value;

    public Node(String value, ArrayList<Integer> pairs){
        this.value = value;
        this.pairs = new ArrayList<>(pairs);
        visited = false;
    }

    public ArrayList<Integer> getPairs(){
        return pairs;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }
    
    public boolean getVisited() {
    	return this.visited;
    }

    public String getValue(){
        return value;
    }
}
