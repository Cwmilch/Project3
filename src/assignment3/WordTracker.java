package assignment3;

import java.util.HashSet;
import java.util.Set;

public class WordTracker {
	Set<String> words;						// stores words that are already in graph
	
	public WordTracker() {
		Set<String> words = new HashSet<String>();
	}
	
	// Each time a new word is added to the graph, this function will be called to add the word to the WordTracker set
	// @param word: adds word to set
	public void addWord(String word) {
		words.add(word);
	}
	
	// Checks if word is already within the WordTracker set
	// @param word: checks if word is already located in set
	public boolean checkWord(String word) {
		return words.contains(word);
	}
}
