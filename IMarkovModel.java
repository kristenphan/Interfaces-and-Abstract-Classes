package InterfaceAbstractStarterProgram;

/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface IMarkovModel {
    /**
     * Set up a Markov object with the desired order e.g Markov Zero, MarkovOne, etc.
     */
    public void setOrder(int o);
    
    /**
     * Read in training text
     */
    public void setTraining(String text);
    
    /**
     * Set up the random number generator
     */
    public void setRandom(int seed);
    
    /**
     * Generate a random text in accordance with the order of the Markov program
     */
    public String getRandomText(int numChars);
}
