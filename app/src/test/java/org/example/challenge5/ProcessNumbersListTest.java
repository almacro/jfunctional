
package org.example.challenge5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author almacro
 */
public class ProcessNumbersListTest {
    @Test
    public void testProcessNumbersList() {
        Integer[] numbersArr = {1, 5, 3, 6, 8, 9, 2};
        List<Integer> numbers = new ArrayList<>(Arrays.asList(numbersArr));
        List<String> result = ProcessNumbersList.filterAndTransform(numbers);
        assertEquals(result, Arrays.asList(new String[]{"three", "six", "nine"}));
    }
}
