package org.example.challenge6;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 *
 * @author almacro
 */
public class FindLongestString {
    static int getIndexOfLargest(int[] numbers) { 
        /*
        return IntStream.range(0, numbers.length)
                .reduce(0, (a,b) -> numbers[a] < numbers[b] ? b : a);
        */
        return IntStream.range(0, numbers.length)
                .reduce((i,j) -> numbers[i] >= numbers[j] ? i : j)
                .orElse(-1);
    }
    static int countLetters(String str) {
        /*
        return str.chars()
                .reduce(0, (acc, i) -> acc + (Character.isLetter(i) ? 1 : 0));
        */
        return str.length();
    }
    static String getByIndex(String[] words, int index) {
        /*
        Function<Integer, String> indexToWord = i -> words[i];
        return indexToWord.apply(index);
        */
        return words[index];
    }
    static String getLongestWord(String[] words) {
        /*
        BiFunction<Integer, Integer, Integer> wordSize = 
                (acc, i) -> acc + (Character.isLetter(i) ? 1 : 0);
        Function<String, Integer> longestIndex = s -> countLetters(s);
        
        int wordIndex = IntStream.range(0, words.length)
                .map(i -> countLetters(words[i]))
                .reduce(0, (a,b) -> a < b ? b : a);
        return words[wordIndex];
        */
        return getByIndex(
                words,
                getIndexOfLargest(Arrays.stream(words)
                        .mapToInt(FindLongestString::countLetters)
                        .toArray()));
                
    }
}
