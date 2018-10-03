package assignment3;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class DFS {
	
	private static Stack<String> path;

	static {
		path = new Stack<String>();
	}
	
	public static ArrayList<String> searchDFS(String start, String end, Graph graph) {
		Node current = graph.getNode(start);
		
		if (current == null) {
			return null;
		}
		
		current.setVisited(true);
		path.push(start);
		
		if (start.equals(end)) {
			ArrayList<String> list = new ArrayList<String>(path);
			path.removeAllElements();
			return list;
		}
		
		for (Integer i : current.getPairs()) {
			String next = graph.getDictionary().get(i);
			Node nextNode = graph.getNode(next);
			
			if (nextNode.getVisited()) {
				continue;
			}
			
			ArrayList<String> result = searchDFS(next, end, graph);
			
			if (result != null) {
				return result;
			}
		}
		
		path.pop();
		return null;
		
	}
}
