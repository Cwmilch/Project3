package assignment3;

import java.util.*;

public class BFS {
    private static Queue<String> wordQueue;

    static {
        wordQueue = new LinkedList<>();
    }

    // Method uses BFS to find if the end word can be found through a word ladder from the start word
    public static ArrayList<String> searchBFS(String start, String end, Graph graph) {
        Map<String, String> paths = new HashMap<>();

        Node word = graph.getNode(start);
        if (word == null) {
            ArrayList<String> res = new ArrayList<>();
            res.add(start.toLowerCase());
            res.add(end.toLowerCase());
            return res;
        }

        word.setVisited(true);

        wordQueue.add(start.toLowerCase());

        while (!wordQueue.isEmpty()) {
            String value = wordQueue.remove();
            Node n = graph.getNode(value);
            n.setVisited(true);
            for (Integer i : n.getPairs()) {
                String s = graph.getDictionary().get(i);
                Node toAdd = graph.getNode(s);

                if (toAdd == null || toAdd.getVisited()) {
                    continue;
                } else {
                    paths.put(s, value);
                    if (s.equals(end)) {
                        ArrayList<String> res = new ArrayList<>();
                        String key = s;
                        while (paths.containsKey(key)) {
                            res.add(0, key.toLowerCase());
                            key = paths.get(key);
                        }
                        res.add(0, start.toLowerCase());

                        //Reset nodes so they aren't marked as visited if BFS is called multiple times
                        graph.resetNodes();
                        wordQueue.clear();
                        return res;
                    }
                }

                wordQueue.add(s);
            }
        }

        graph.resetNodes();
        ArrayList<String> res = new ArrayList<>();
        res.add(start.toLowerCase());
        res.add(end.toLowerCase());
        wordQueue.clear();
        return res;
    }

}
