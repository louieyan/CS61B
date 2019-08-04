public class OffByN implements CharacterComparator {
    private int NN;

    public OffByN(int N) {
        NN = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == NN;
    }
}
