package InterfaceAbstractStarterProgram;
import java.util.*;

/**
 * Implement MarkovModel to generate random text consisting of letters chosen randomly from the training text
 */
public class MarkovModel extends AbstractMarkovModel {
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
        
        if (myOrder == 0) { // Markov Zero 
            for(int k=0; k < numChars; k++){ // Choose all letters randomly from myText
                int index = myRandom.nextInt(myText.length());
                output.append(myText.charAt(index));
            }
        }
        else { // Not Markov Zero, predict the next letter using previous letter(s) depending on the order
            // randomly choose the first letter for the output random text sb
            int index = myRandom.nextInt(myText.length() - myOrder);
            String key = myText.substring(index, index + myOrder);
            output.append(key);
        
            // for subsequent letters, use the previous letter(s) to predict the next letter
            for(int k=0; k < numChars - myOrder; k++){
                ArrayList<Character> follows = getFollows(key);
                if (follows.size() == 0) { // if key does not have any following char, stop the loop as no prediction can be made for the next char
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
}





















