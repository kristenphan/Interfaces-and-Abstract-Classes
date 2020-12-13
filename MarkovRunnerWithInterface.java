package InterfaceAbstractStarterProgram;
import edu.duke.*; 

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
public class MarkovRunnerWithInterface {
    /**
     * Run any Markov model that implements the IMarkovModel interface and print the random text generated
     */
    public void setupModel(IMarkovModel markov, int order, int seed, String text, int size) {
        markov.setOrder(order);
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        String output = markov.getRandomText(size);
        printOut(output);
    }
    
    /**
     * Create different types of Markov models and call runModel() to run them
     */
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int order = 7;
        int seed = 953;
        int size = 100;
        EfficientMarkovModel em = new EfficientMarkovModel();
        setupModel(em, order, seed, st, size);
    }
        
    /**
     * Format and print the randomly generated text
     */
    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public static void main(String[] args) {
        MarkovRunnerWithInterface mi = new MarkovRunnerWithInterface();
        mi.runMarkov();
    }
}
