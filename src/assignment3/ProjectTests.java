package assignment3;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class ProjectTests {
    private static Set<String> dict;
    private static Object[] dictArray;
    private static ByteArrayOutputStream outContent;

    @Before // this method is run before every test
    public void setUp() {
        Main.initialize();
        dict = Main.makeDictionary();
        dictArray = dict.toArray();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @SuppressWarnings("Duplicates")
    private boolean verifyLadder(ArrayList<String> ladder) {
        String prev = null;
        if (ladder == null || ladder.size() == 2)
            return true;
        for (String word : ladder) {
            if (!dict.contains(word.toUpperCase()) && !dict.contains(word.toLowerCase())) {
                return false;
            }
            if (prev != null && !differByOne(prev, word))
                return false;
            prev = word;
        }
        return true;
    }

    private static boolean differByOne(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && diff++ > 1) {
                return false;
            }
        }

        return true;
    }

    private static String getRandomString(){
        Random r = new Random();
        return dictArray[r.nextInt(dictArray.length)].toString().toUpperCase();
    }

    @Test(timeout = 300000)
    public void testBFSRandom() {
        for(int i = 0; i < 10; i++) {
            String a = getRandomString();
            String b = getRandomString();
            while (b.equals(a)) {
                b = getRandomString();
            }
            ArrayList<String> res = Main.getWordLadderBFS(a, b);

            if (res != null) {
                HashSet<String> set = new HashSet<>(res);
                Main.printLadder(res);
                assertEquals(set.size(), res.size());
            }

            assertNotNull(res);
            if (res.size() > 2)
                assertTrue(verifyLadder(res));
        }
    }

    @Test(timeout = 300000)
    public void testDFSRandom() {
        for(int i = 0; i < 10; i++) {
            String a = getRandomString();
            String b = getRandomString();
            while (b.equals(a)) {
                b = getRandomString();
            }
            ArrayList<String> res = Main.getWordLadderDFS(a, b);

            if (res != null) {
                HashSet<String> set = new HashSet<>(res);
                Main.printLadder(res);
                assertEquals(set.size(), res.size());
            }

            assertTrue(verifyLadder(res));
            assertNotNull(res);
        }
    }

    @Test(timeout = 30000)
    public void testDFS() {
        ArrayList<String> res = Main.getWordLadderDFS("whity", "limes");
        if (res != null) {
            HashSet<String> set = new HashSet<>(res);
            assertEquals(set.size(), res.size());
        }
        try {
            assertTrue(verifyLadder(res));
        }catch (AssertionError e){
            e.printStackTrace();
        }
        assertFalse(res == null || res.size() == 0 || res.size() == 2);

    }

    @Test (timeout = 30000)
    public void testBFS() {
        ArrayList<String> res = Main.getWordLadderBFS("whity", "limes");
        if (res != null) {
            HashSet<String> set = new HashSet<>(res);
            assertEquals(set.size(), res.size());
        }
        assertTrue(verifyLadder(res));
        assertFalse(res == null || res.size() == 0 || res.size() == 2);
    }

    @Test (timeout = 30000)
    public void testBFS1() {
        ArrayList<String> res = Main.getWordLadderBFS("gecko", "geeks");
        if (res != null) {
            HashSet<String> set = new HashSet<>(res);
            assertEquals(set.size(), res.size());
        }
        assertTrue(verifyLadder(res));
        assertFalse(res == null || res.size() == 0 || res.size() == 2);
    }

    @Test (timeout = 30000)
    public void testDFS1() {
        ArrayList<String> res = Main.getWordLadderDFS("gecko", "geeks");
        if (res != null) {
            HashSet<String> set = new HashSet<>(res);
            assertEquals(set.size(), res.size());
        }
        assertTrue(verifyLadder(res));
        assertFalse(res == null || res.size() == 0 || res.size() == 2);
    }
}
