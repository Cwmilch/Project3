package assignment3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BFS {
	private static Queue<String> wordQueue;
	private static Stack<String> wordPath;
	
	static {
		wordQueue = new LinkedList<>();
		wordPath = new Stack<>();
	}
	
	// Method uses BFS to find if the end word can be found through a word ladder from the start word
	public static Stack<String> searchBFS(String start, String end, Graph dictionary) {
		Map<String, String> paths = new HashMap<>();
		Node word = dictionary.getNode(start);
		
		// Adds list of pairs to the queue for checking
		for (int i = 0; i < word.getPairs().size(); i++) {
			String value = dictionary.getDictionary().get(word.getPairs().get(i));
			paths.put(value, start);
			wordPath.push(value);
			
			if(dictionary.getNode(value) == null || dictionary.getNode(value).getVisited()) {
				continue;
			}
			else if(value.equals(end)) {
				String key = value;
				while(paths.containsKey(key)) {
					System.out.println(key);
					key = paths.get(key);
					
				}
				return wordPath;
			}
			
			wordQueue.add(value);
		}
		
		while (!wordQueue.isEmpty()) {
			String value = wordQueue.remove();
			wordPath.push(value);
			Node n = dictionary.getNode(value);
			
			for (int i = 0; i < n.getPairs().size(); i++) {
				String s = dictionary.getDictionary().get(n.getPairs().get(i));
				paths.put(s, value);
				if(dictionary.getNode(s) == null || dictionary.getNode(s).getVisited()) {
					continue;
				}
				else if(s.equals(end)) {
					String key = s;
					while(paths.containsKey(key)) {
						System.out.println(key);
						key = paths.get(key);
						
					}
					return wordPath;
				}
				
				wordQueue.add(s);
			}
		}
		
		return null;
	}
	
}
