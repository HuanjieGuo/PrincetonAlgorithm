import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Objects;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        int i = 0;
        while (!StdIn.isEmpty()){
            i++;
            String word = StdIn.readString();
            if (word.isEmpty()) break;
            if(Objects.isNull(champion)) champion = word;
            else if(StdRandom.bernoulli(1.0/i)){
                champion = word;
            }
        }
        StdOut.println(champion);
    }
}
