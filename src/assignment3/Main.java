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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Graph g;

    public static void main(String[] args) throws Exception {
        Scanner kb;    // input Scanner for commands
        PrintStream ps;    // output file, for student testing and grading only
        // If arguments are specified, read/write from/to files instead of Std IO.
        if (args.length != 0) {
            kb = new Scanner(new File(args[0]));
            ps = new PrintStream(new File(args[1]));
            System.setOut(ps);            // redirect output to ps
        } else {
            kb = new Scanner(System.in);// default input from Stdin
            ps = System.out;            // default output to Stdout
        }

        initialize();

        ArrayList<String> input = parse(kb);
        while (!input.isEmpty()) {
            printLadder(getWordLadderBFS(input.get(0), input.get(1)));
            input = parse(kb);
        }
    }

    public static void initialize() {
        //Put the dictionary in alphabetical order
        List<String> dictionary = makeDictionary().stream().sorted().collect(Collectors.toList());

        g = new Graph(dictionary);
    }

    /**
     * @param keyboard Scanner connected to System.in
     * @return ArrayList of Strings containing start word and end word.
     * If command is /quit, return empty ArrayList.
     */
    public static ArrayList<String> parse(Scanner keyboard) {
        String input = keyboard.nextLine();
        if (!input.equals("/quit")) {
            if (!input.equals("")) {
                String[] words = input.split(" ");
                return (ArrayList<String>) Arrays.stream(words).collect(Collectors.toList());
            }
        }

        return new ArrayList<>();
    }

    public static ArrayList<String> getWordLadderDFS(String start, String end) {
        return DFS.searchDFS(start.toUpperCase(), end.toUpperCase(), g);
    }

    public static ArrayList<String> getWordLadderBFS(String start, String end) {
        return BFS.searchBFS(start.toUpperCase(), end.toUpperCase(), g);
    }


    public static void printLadder(ArrayList<String> ladder) {
        if (ladder.size() > 2) {
            int ladderSize = ladder.size();
            System.out.println("a " + (ladderSize - 2) + "-rung word ladder exists between "
                    + ladder.get(0) + " and " + ladder.get(ladderSize - 1) + ".");
            for (String s : ladder) {
                System.out.println(s);
            }
        } else {
            System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1));
        }
    }

    // TODO
    // Other private static methods here
    private static void testMethods(String start, String end) {
        printLadder(getWordLadderBFS(start.toUpperCase(), end.toUpperCase()));
        printLadder(getWordLadderDFS(start.toUpperCase(), end.toUpperCase()));
    }

    /* Do not modify makeDictionary */
    public static Set<String> makeDictionary() {
        Set<String> words = new HashSet<String>();
        Scanner infile = null;
        try {
            infile = new Scanner(new File("assignment3/five_letter_words.txt"));
        } catch (FileNotFoundException e) {
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
