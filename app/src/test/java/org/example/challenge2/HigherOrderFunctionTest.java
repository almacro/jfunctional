package org.example.challenge2;

import java.util.function.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author almacro
 */
public class HigherOrderFunctionTest {

    @Test
    public void testIsNaturalNumberCheck() {
        Function<Integer, Integer> fibonacci = n -> {
            // if (n == 1 || n == 2) {
            //     return 1;
            // }

            // return fibonacci.apply(n - 1) + fibonacci.apply(n - 2);
            int a = 1;
            int b = 1;

            if (n == 1) {
                return a;
            }

            if (n == 2) {
                return b;
            }

            for (int i = 0; i < n; i++) {
                int c = a + b;
                a = b;
                b = c;
            }

            return b;
        };
        Function<Integer, Integer> fibSafe = HigherOrderFunction.argIsNaturalNumber(fibonacci);
        int result1 = fibSafe.apply(7);
        int result2 = fibSafe.apply(-5);
    }
}
