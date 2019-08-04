import java.util.Calendar;

public class Palindrome {
    /**
     * Creates a deque from the given word.
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /**
     * Return true if the word is palindrome, false otherwise.
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    /**
     * Return true if the word is palindrome according to given CharacterComparator.
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelperCc(d, cc);
    }

    /**
     * Helper method for isPalindrome(String)
     * @param d
     * @return
     */
    private boolean isPalindromeHelper(Deque<Character> d) {
        if (d.size() <= 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        if (first.equals(last)) {
            return isPalindromeHelper(d);
        } else {
            return false;
        }

    }

    /**
     * Helper method for isPalindrome(String, CharacterComparator)
     * @param d
     * @return
     */
    private boolean isPalindromeHelperCc(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        if (cc.equalChars(first, last)) {
            return isPalindromeHelperCc(d, cc);
        } else {
            return false;
        }

    }
}
