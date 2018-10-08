/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Carter Milch
 * cwm2529
 * 16365
 * Tristan McDaniel
 * thm443
 * 16355
 * Slip days used: 0
 * Git URL:
 * Fall 2018
 */

package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    //All words used to create the Graph, sorted alphabetically to help with finding Nodes later on
    private List<String> dictionary;

    //All Nodes used to create the Graph
    private ArrayList<Node> nodes;

    public Graph(List<String> words) {
        dictionary = words;
        nodes = new ArrayList<>();
        findPaths();
    }

    /**
     * Creates a graph by creating a new Node for every word, and linking each Node to every word
     * that only differs from the Node's word by one character.
     */
    public void findPaths() {
        for (int i = 0; i < dictionary.size(); i++) {
            String current = dictionary.get(i);
            ArrayList<Integer> pairs = null;
            for (int j = 0; j < dictionary.size(); j++) {
                if (j == i)
                    continue; //Don't allow duplicates

                if (close(current, dictionary.get(j))) {
                    if (pairs == null) {
                        pairs = new ArrayList<>();
                    }
                    pairs.add(j);
                }
            }

            //Make sure the Node isn't isolated, i.e. no other words in the dictionary differ by only one character
            if (pairs != null) {
                nodes.add(new Node(current, pairs));
                pairs.clear();
            }
        }
    }

    /**
     * Determine if two Strings differ by only one letter
     *
     * @param a the first String to compare
     * @param b the second String to compare
     * @return true if the Strings only differ by one letter, false otherwise
     */
    private boolean close(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1)
                    return false;
            }
        }

        return diff != 0;
    }

    /**
     * Get the node whose value is the given String.
     *
     * @param nodeValue the String used to find the Node
     * @return the Node associated with the given String.
     */
    public Node getNode(String nodeValue) {
        return getNode(0, nodes.size(), nodeValue);
    }

    /**
     * Binary search to find the node that's associated with the given String.
     * Searches the heads of each graph for each word in the dictionary
     *
     * @param min       lower boundary of range to search
     * @param max       upper boundary of range to serach
     * @param nodeValue String associated with the Node to search for
     * @return the Node associated with the given string
     */
    private Node getNode(int min, int max, String nodeValue) {
        if (max == min) {
            return nodes.get(min).getValue().equals(nodeValue.toUpperCase()) ? nodes.get(min) : null;
        }

        int mid = (max + min) / 2;
        Node n = nodes.get(mid);

        int diff = n.getValue().toUpperCase().compareTo(nodeValue.toUpperCase());
        if (diff > 0) {
            return getNode(min, mid - 1, nodeValue);
        } else if (diff < 0) {
            return getNode(mid + 1, max, nodeValue);
        }

        return n;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void resetNodes() {
        for (Node n : nodes) {
            n.setVisited(false);
        }
    }
}
