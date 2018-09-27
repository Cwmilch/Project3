package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    //All words used to create the Graph, sorted alphabetically to help with finding Nodes later on
    private List<String> dictionary;

    //All Nodes used to create the Graph
    private ArrayList<Node> nodes;

    public Graph(List<String> words){
        dictionary = words;
        nodes = new ArrayList<>();
        findPaths();
    }

    /**
     * Creates a graph by creating a new Node for every word, and linking each Node to every word
     * that only differs from the Node's word by one character.
     */
    public void findPaths(){
        for(int i = 0; i < dictionary.size(); i++){
            String current = dictionary.get(i);
            ArrayList<Integer> pairs = null;
            for(int j = 0; j < dictionary.size(); j++){
                if(j == i)
                    continue; //Don't allow duplicates

                if(close(current, dictionary.get(j))){
                    if(pairs == null){
                        pairs = new ArrayList<>();
                    }
                    pairs.add(j);
                }
            }

            //Make sure the Node isn't isolated, i.e. no other words in the dictionary differ by only one character
            if(pairs != null) {
                //Only used this to make sure the pairs it came up with were valid
                /*System.out.println("Pairs for " + current + ":");
                for(Integer integer : pairs){
                    System.out.print(dictionary.get(integer) + ", ");
                }
                System.out.println();*/


                nodes.add(new Node(current, pairs));
                pairs.clear();
            }
        }
    }

    /**
     * Determine if two Strings differ by only one letter
     * @param a the first String to compare
     * @param b the second String to compare
     * @return true if the Strings only differ by one letter, false otherwise
     */
    private boolean close(String a, String b){
        int diff = 0;
        if(a.length() != b.length()){
            return false;
        }

        for(int i = 0; i < a.length(); i++){
            if(Character.toLowerCase(a.charAt(i)) != Character.toLowerCase(b.charAt(i))){
                diff++;
                if(diff > 1)
                    return false;
            }
        }

        return diff != 0;
    }

    /**
     * Get the node whose value is the given String.
     * @param nodeValue the String used to find the Node
     * @return the Node associated with the given String.
     */
    public Node getNode(String nodeValue){
        return getNode(0, nodes.size(), nodeValue);
    }

    /**
     * Binary search to find the node that's associated with the given String.
     * @param min lower boundary of range to search
     * @param max upper boundary of range to serach
     * @param nodeValue String associated with the Node to search for
     * @return the Node associated with the given string
     */
    private Node getNode(int min, int max, String nodeValue){
        if(max == min){
            return null;
        }

        int mid = (max + min) / 2;
        Node n = nodes.get(mid);

        int diff = n.getValue().toUpperCase().compareTo(nodeValue.toUpperCase());
        if(diff > 0){
            return getNode(min, mid, nodeValue);
        }else if(diff < 0){
            return getNode(mid + 1, max, nodeValue);
        }

        return n;
    }

    /**
     * @return the ArrayList of Nodes for the Graph.
     */
    public ArrayList<Node> getNodes(){
        return nodes;
    }

    public void resetNodeStatus(){
        for(Node n : nodes){
            n.setVisited(false);
        }
    }
}