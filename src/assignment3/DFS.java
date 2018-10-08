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
import java.util.Stack;

public class DFS {

    private static Stack<String> path;
    private static Graph g;

    static {
        path = new Stack<>();
    }

    public static ArrayList<String> searchDFS(String start, String end, Graph graph) {
        g = graph;
        ArrayList<String> result = searchDFS(graph.getNode(start), graph.getNode(end));
        if (result == null) {
            result = new ArrayList<>();
            result.add(start.toLowerCase());
            result.add(end.toLowerCase());
        }

        g.resetNodes();
        path.removeAllElements();
        return result;
    }

    private static ArrayList<String> searchDFS(Node start, Node end) {
        if (start == null) {
            return null;
        }

        start.setVisited(true);
        path.push(start.getValue().toLowerCase());

        if (start.equals(end)) {
            return new ArrayList<>(path);
        }

        for (Integer i : start.getPairs()) {
            String next = g.getDictionary().get(i);
            Node nextNode = g.getNode(next);

            if (nextNode.getVisited()) {
                continue;
            }

            ArrayList<String> result = searchDFS(nextNode, end);

            if (result != null) {
                return result;
            }
        }

        path.pop();
        return null;
    }
}
