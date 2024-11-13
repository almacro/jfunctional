package org.example.challenge5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author almacro
 */
public class ProcessNumbersList {
    static Map<Integer,String> initializeWordsMap() {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0, "zero");
        map.put(3, "three");
        map.put(6, "six");
        map.put(9, "nine");
        return map;
    };
    static Map<Integer,String> wordsMap = initializeWordsMap();
    static List<String> filterAndTransform(List<Integer> numbers) {
       /*
        String[] numberNames = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        return numbers.stream()
                .filter(n -> n % 3 == 0)
                .map(n -> numberNames[n])
                .collect(Collectors.toList());
        */
        /*
        return numbers.stream()
                .filter(n -> n % 3 == 0)
                .map(n -> {
                    String s = null;
                    switch(n) {
                      case 0: s = "zero"; break;
                      case 3: s = "three"; break;
                      case 6: s = "six"; break;
                      case 9: s = "nine"; break;
                    };
                    return s;
                })
                .collect(Collectors.toList());
        */
        
        return numbers.stream()
                .filter(n -> n % 3 == 0)
                .map(n -> wordsMap.get(n))
                .collect(Collectors.toList());
    }
}
