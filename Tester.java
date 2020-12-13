package InterfaceAbstractStarterProgram;
import java.util.*;
import edu.duke.*;
import java.lang.System;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Tester {
    public void setupModel(IMarkovModel markov, int order, int seed, String trainText) {
        markov.setOrder(order);
        markov.setRandom(seed);
        markov.setTraining(trainText);
    }
    
    /**
     * Test getFollows() method from 
     * E.g.:
     * input: 
     * trainText = "this is a test yes this is a ttest."
     * order = 1
     * output: 
     * getFollows(".") = ['h', 'e', ' ', 'h', 't', 'e', '.']
     * getFollows("e") = ['s', 's', 's']
     * getFollows("s ") = ['i', 'a', 't', 'i', 'a']
     */
    public void testGetFollows() {
        String trainText = "this is a test yes this is a ttest.";
        int order = 2;
        MarkovModel mm = new MarkovModel();
        mm.setOrder(order);
        mm.setTraining(trainText);
        String key1 = ".";
        String key2 = "e";
        String key3 = "s ";
        ArrayList<Character> follows1 = mm.getFollows(key1);
        ArrayList<Character> follows2 = mm.getFollows(key2);
        ArrayList<Character> follows3 = mm.getFollows(key3);
        System.out.println("trainText = " + trainText + "\n" + 
                           "key1 = " + key1 + "\n" + 
                           "follows1 = " + follows1.toString() + "\n" + 
                           "key2 = " + key2 + "\n" + 
                           "follows2 = " + follows2.toString() + "\n" + 
                           "key3 = " + key3 + "\n" + 
                           "follows3 = " + follows3.toString());   
    }
    
    public void testGetFollowsWithFile() {   
        FileResource fr = new FileResource();
        String trainText = fr.asString();
        int order = 1;
        MarkovModel mm = new MarkovModel();
        mm.setOrder(order);
        mm.setTraining(trainText);
        String key = "he";
        ArrayList<Character> follows = mm.getFollows(key);
        System.out.println("key = " + key + "\n" + 
                           "follows len = " + follows.size());   
    }
    
    public void testMarkovModel() {
        String trainText = "yes-this-is-a-thin-pretty-pink-thistle";
        int[] orders = {0, 1, 2, 3};
        int seed = 13;
        int size = 20;
        MarkovModel mm = new MarkovModel();
        for (int order: orders) {
            setupModel(mm, order, seed, trainText);
            System.out.println("running with " + mm);
            String output = mm.getRandomText(size);
            System.out.println("order = " + order + "\n" +
                               "output = " + output);
        }
    }
        
    /**
     * Test buildMap() method from EfficientMarkovModel class
     * E.g.
     * input: training text = "yes-this-is-a-thin-pretty-pink-thistle"; order = 2;
     * output: 
     * number of keys: 25 (including the last pair of key "le" that has no following char)
     * size of largest list of following characters in map = 3
     * keys that have the largest ARrayList (of size 3): "hi, "s-", "-t", "is", "th"
     */
    public void testHashMap() {
        // create an order-2 EfficientMarkovModel; 
        EfficientMarkovModel emm = new EfficientMarkovModel();
        int order = 2;
        int seed = 100; // seed value does not affect the map
        FileResource fr = new FileResource(); // file "hawthorne.txt"
        String st = fr.asString();
        st = st.replace('\n', ' ');
        st = "yes-this-is-a-thin-pretty-pink-thistle";
        setupModel(emm, order, seed, st);
        emm.printHashMapInfo();
    }
    
    public void testEfficientMarkovModel() { // PENDING: DISCREPANCY BETWEEN 2 MODELS
        String trainText = "yes-this-is-a-thin-pretty-pink-thistle";
        int[] orders = {0, 1, 2, 3};
        int seed = 13;
        int size = 20;
        MarkovModel mm = new MarkovModel();
        EfficientMarkovModel emm = new EfficientMarkovModel();
        for (int order: orders) {
            setupModel(mm, order, seed, trainText);
            System.out.println("running with " + mm);
            String mmOutput = mm.getRandomText(size);
            System.out.println("order = " + order + "\n" +
                               "mmOutput = " + mmOutput);
        }
        
        System.out.println("current timestamp: " + System.nanoTime() + "----------------------------------------------------------");
        
        for (int order: orders) {
            setupModel(emm, order, seed, trainText);
            System.out.println("running with " + emm);
            String emmOutput = emm.getRandomText(size);
            System.out.println("order = " + order + "\n" +
                               "emmOutput (should be the same as MarkovModel) = " + emmOutput);
        }
        
        System.out.println("current timestamp: " + System.nanoTime() + "----------------------------------------------------------");
    }
    
    public static void main(String[] args) {
        Tester t = new Tester();
        t.testGetFollows();
    }
}