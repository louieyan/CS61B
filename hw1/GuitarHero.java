import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {
        GuitarString[] gs = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24.0) / 12.0);
            gs[i] = new GuitarString(frequency);
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) { // if keyboard doesn't contain key, indexOf will return -1
                    gs[index].pluck();
                    double sample = gs[index].sample();
                    StdAudio.play(sample);
                }
            }

            /* Need to add every string's sample */
            double sample = 0.0;
            for (GuitarString x : gs) {
                sample += x.sample();
            }
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
            for (GuitarString x : gs) {
                x.tic();
            }
        }


    }
}
