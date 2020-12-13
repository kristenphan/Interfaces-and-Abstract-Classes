package InterfaceAbstractStarterProgram;
import java.util.*;
import java.lang.*; 

/**
 * Implement MarkovModel to generate random text 
 * consisting of letters chosen randomly from the training text
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    // mapping all possible substrings of the training text to their subsequent letters
    private HashMap<String, ArrayList<Character>> map;
    
    public EfficientMarkovModel() {
        myRandom = new Random();
        map = new HashMap<String, ArrayList<Character>>();
    }
    
    @Override
    public void setTraining(String s) {
        myText = s.trim();
        if (myOrder != -1) {
            // build map of all possible substrings in myText and their associated following characters,
            // including the last key in myText with no following character
            buildMap(); 
        }
        else {
            System.out.println("Coudln't build map since myOrder has not been updated");
        }
    }
        
    /** 
     * Map all possible substrings of the training text (key) to their subsequent characters (values)
     * The length of the substrings depends on the order of the Markov object
     * This HashMap will then be used to look up characters following a specific substring when needed
     * A new map should be built for each new training text
     */
    public void buildMap() {
        // iterate through training text, find all possible substrings depending on the Markov order
        // and add them along with the following characters to the HashMap
        for (int i = 0; i < myText.length() - myOrder; i++) {
            String key = myText.substring(i, i + myOrder);
            Character nextCh = myText.charAt(i + myOrder);
            if (map == null || !map.containsKey(key)) { // map is emtpy or no key found, add key and nextChar to map
                map.put(key, new ArrayList<Character>());
                map.get(key).add(nextCh);
            }
            else { // key found, add nextCh to map
                map.get(key).add(nextCh);
            }
        }
        
        // add to the map the last key at the end of the training text which doesn't have a following char
        map.put(myText.substring(myText.length() - myOrder), new ArrayList<Character>());
    }
    
    public void printHashMapInfo() {
        if (map.isEmpty()) {
            System.out.println("Map is empty"); 
            System.exit(0);    
        }
        // print the hashmap (all keys + values)
        //for (String k: map.keySet()) {
        //    System.out.println("key = " + k + "; values = " + map.get(k).toString());
        //}
        
        // print the number of keys in the hashmap
        System.out.println("number of keys = " + map.size());
        
        // print the size of the largest value in the hashmap - the size of the largest ArrayList of characters
        int maxSz = -1;
        for (String k: map.keySet()) {
            ArrayList<Character> list = map.get(k);
            if (list.size() > maxSz) {
                maxSz = list.size();
            }
        }
        System.out.println("size of largest list of following characters in map = " + maxSz);
        
        // print the keys that have the maxium size value
        System.out.println("printing keys which have the max size value");
        for (String k: map.keySet()) {
            ArrayList<Character> list = map.get(k);
            if (list.size() == maxSz) {
                System.out.println("key = " + k);
                System.out.println("key is spaces: " + k.equals(" "));
            }
        }
        
        // print the size of the list of characters which follows of a specific key
        String targetKey = "o";
        for (String k: map.keySet()) {
            if (k.equals(targetKey)) {
                ArrayList<Character> list = map.get(k);
                if (list == null) {
                    System.out.println("Size of ArrayList of key " + k + ": null");
                }
                else {
                    System.out.println("Size of ArrayList of key " + k + list.size());
                }
            }
        }
    }

    @Override
    /**
     * Look up the key in the map and return a list of characters associated with said key
     */
    protected ArrayList<Character> getFollows(String key) {
        return map.get(key);
    }
    
    /**
     * Generate and reuturn random text that is @param numChars long
     * with each character (i.e. letter) is chosen randomly from the training text in accordance with the Markov order
     */
    public String getRandomText(int numChars){
        if (myText == null){ // no training text available
            return ""; 
        }
        
        // Create a string builder to store the output random text
        StringBuilder output = new StringBuilder();
        
        //buildMap();
        
        if (myOrder == 0) { // Markov Zero 
            for(int k=0; k < numChars; k++){ // Choose all letters randomly from myText
                int index = myRandom.nextInt(myText.length());
                output.append(myText.charAt(index));
            }
        }
        else {
            // Choose the first letter(s) of the output randomly from myText
            int index = myRandom.nextInt(myText.length() - myOrder);
            String key = myText.substring(index, index + myOrder);
            output.append(key);
            // for subsequent letters, use the previous letter(s) to predict the next letter by looking up the previous letter(s) as key in map
            for(int k=0; k < numChars - myOrder; k++) {
                ArrayList<Character> follows = getFollows(key);
                if (follows.size() == 0) { // if key does not have a following char, exit the loop as no prediction about the next char can be made
                    break;
                }
                int nextIdx = myRandom.nextInt(follows.size());
                Character ch = follows.get(nextIdx);
                output.append(ch);
                key = key.substring(1) + ch;
            }
        }
        
        return output.toString();
    }
    
    @Override
    public String toString() {
        return "EfficientMarkovModel of order " + myOrder;
    }
}





















