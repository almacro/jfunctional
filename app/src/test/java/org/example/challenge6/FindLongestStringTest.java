package org.example.challenge6;


import java.util.function.Function;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;


/**
 *
 * @author almacro
 */
public class FindLongestStringTest {
    @Test
    public void foo1() {
        // indexOfLargestNumber
        int[] numbers = { 7, 17, 13, 19, 5 };
        int maxIndex = IntStream.range(0, numbers.length)
                .reduce(0, (a,b) -> numbers[a] < numbers[b] ? b : a);

        System.out.println(maxIndex);
    }
    @Test
    public void foo2() {
        // countLetters
        String s = "Hello composition";
        int count = s.chars()
                .reduce(0, (acc, i) -> acc + (Character.isLetter(i) ? 1 : 0));
        System.out.println(s.length());
        System.out.println(count);
    }
    @Test
    public void foo() {
        // getByIndex
        String[] words = {
            "apple", "banana", "cherry",
            "date", "fig", "grape",
            "kiwi", "lemon", "mango"
        };
        int index = 3;
        
        Function<Integer, String> indexToWord = i -> words[i];
        String word = indexToWord.apply(index);
        System.out.println(word);
    }
    @Test
    public void testFindLongestString() {
        int[] numbers = { 7, 17, 13, 19, 5 };

        String[] words = {
            "apple", "banana", "cherry",
            "date", "fig", "grape",
            "kiwi", "lemon", "mango"
        };
        String s = "Hello composition";
        int index = 3;

        int indexOfLargestNumber = FindLongestString.getIndexOfLargest(numbers);
        int numLetters = FindLongestString.countLetters(s);
        String aWord = FindLongestString.getByIndex(words, index);

        String compositionResult = FindLongestString.getLongestWord(words);
        System.out.println(compositionResult);
    }
}
