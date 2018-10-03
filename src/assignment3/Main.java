/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2018
 */

package assignment3;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    // static variables and constants only here.
    private static List<String> dictionary;
    private static Graph g;


    public static void main(String[] args) throws Exception {
        Scanner kb;	// input Scanner for commands
        PrintStream ps;	// output file, for student testing and grading only
        // If arguments are specified, read/write from/to files instead of Std IO.
        if (args.length != 0) {
            kb = new Scanner(new File(args[0]));
            ps = new PrintStream(new File(args[1]));
            System.setOut(ps);			// redirect output to ps
        } else {
            kb = new Scanner(System.in);// default input from Stdin
            ps = System.out;			// default output to Stdout
        }
        initialize();

        // TODO methods to read in words, output ladder
        BFS.searchBFS("SMART", "ZINKY", g);
    }

    public static void initialize() {
        //Put the dictionary in alphabetical order
        dictionary = makeDictionary().stream().sorted().collect(Collectors.toList());

        /* TODO remove this
         * only for testing, prints all of the pairs for the word "smart" since that was just the example they gave us
         */
        g = new Graph(dictionary);
    }

    /**
     * @param keyboard Scanner connected to System.in
     * @return ArrayList of Strings containing start word and end word.
     * If command is /quit, return empty ArrayList.
     */
    public static ArrayList<String> parse(Scanner keyboard) {
        // TO DO
        return null;
    }

    public static ArrayList<String> getWordLadderDFS(String start, String end) {
        return DFS.searchDFS(start.toUpperCase(), end.toUpperCase(), g);
    }

    public static ArrayList<String> getWordLadderBFS(String start, String end) {
        return BFS.searchBFS(start.toUpperCase(), end.toUpperCase(), g);
    }


    public static void printLadder(ArrayList<String> ladder) {
        int ladderSize = ladder.size();
        System.out.println("a " + (ladderSize - 2) + "-rung word ladder exists between "
                + ladder.get(0) + " and " + ladder.get(ladderSize - 1) + ".");
        for(String s : ladder){
            System.out.println(s);
        }
    }
    // TODO
    // Other private static methods here


    /* Do not modify makeDictionary */
    public static Set<String>  makeDictionary () {
        Set<String> words = new HashSet<String>();
        Scanner infile = null;
        try {
            //TODO uncomment this line, wouldn't work on my laptop unless I did it the other way for some reason
            //infile = new Scanner (new File("/assignment3/five_letter_words.txt"));
            infile = new Scanner(Main.class.getClassLoader().getResourceAsStream
                    ("five_letter_words.txt"));
        } catch (Exception e /*FileNotFoundException e*/) {
            System.out.println("Dictionary File not Found!");
            e.printStackTrace();
            System.exit(1);
        }
        while (infile.hasNext()) {
            words.add(infile.next().toUpperCase());
        }
        return words;
    }
}
