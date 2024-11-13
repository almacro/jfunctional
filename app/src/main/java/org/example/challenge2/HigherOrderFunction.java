package org.example.challenge2;

import java.util.function.Function;

/**
 *
 * @author almacro
 */
public class HigherOrderFunction {

    // Return a "safe" version of the function argument "f"
    public static Function<Integer, Integer> argIsNaturalNumber(Function<Integer, Integer> f) {/*
        Function<Function<Integer, Integer>, Function<Integer, Integer>> isNaturalNumberCheck = 
                (fn) -> (x) -> {
                    if(x < 1) {
                        System.out.println(x + " is not a natural number");
                        return 1;
                    }
                    return fn.apply(x);
                };
        return isNaturalNumberCheck.apply(f);
*/
        return x -> {
            if(x > 0) {
                return f.apply(x);
            } else {
                return 1;
            }
        };
    }
}
