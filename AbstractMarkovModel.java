package InterfaceAbstractStarterProgram;
import java.util.*;

/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText; // training text
    protected Random myRandom; // random number generate
    protected int myOrder = -1; // the order of the Markov algo 
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setOrder(int o) {
        myOrder = o;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    /**
     * A common helper function for all Markov models (see Tester class for testing)
     * Return an ArrayList of all characters (i.e. letters) that follows @param key in the training text
     * @param key can be either a character or a word
     */
    protected ArrayList<Character> getFollows(String key) {
        // create an array list to store all characters following @param key
        ArrayList<Character> follows = new ArrayList<Character>();
        
        // iterate through training text and add letters to follows
        String subStr = myText;
        while (subStr.length() > key.length()) {
            int idx = subStr.indexOf(key);
            if (idx != -1 && (idx + key.length()) < subStr.length()) { // @param key found, add the next char to follows
                follows.add(subStr.charAt(idx + key.length()));
                subStr = subStr.substring(idx + 1);
            }
            else { // no @param key found
                break;
            }
        }
        
        return follows;
    }
    
    /**
     * Print a Markov object along with its order
     */
    public String toString() {
        if (myOrder != -1) {
            return "MarkovModel of order " + myOrder;
        }
        else {
            return "The order of the Markov model has not been initialized";
        }
    }
    
    /**
     * Abstract method to be implemented by subclasses
     * Generate a random text of length numChars by calling getFollows() method
     */
    abstract public String getRandomText(int numChars);
}






































