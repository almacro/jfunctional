package org.example.demo;

import java.util.function.Function; 
import java.util.function.BiFunction; 

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author almacro
 */
public class Demo {
    protected static class MyMath {
        static Integer triple(Integer x) { return 3 * x; }
        
        static Integer add(Integer x, Integer y) { return x + y; }
        static Integer subtract(Integer x, Integer y) { return x - y; }
        
        static Integer combine2and3(BiFunction<Integer, Integer, Integer> combineFunc) {
            return combineFunc.apply(2, 3);
        }
        
        static Integer times2(Integer x) { return x * 2; }
        static Integer times3(Integer x) { return x * 3; }
        static Integer times4(Integer x) { return x * 4; }
        
        static Function<Integer, Integer> createMultiplier(Integer y) {
            return (Integer x) -> x * y;
        }
    }
    
    protected static class Person {
        private String name;
        private Integer age;
        
        public Person(String name, Integer age) { 
            this.name = name; 
            this.age = age; 
        }

        public String toString() {
            return "Person{ name: "+name+", age: "+age+" }";
        }
    }
    
    protected static class DataLoader {
        public final NoArgFunction<Person> loadPerson;
        public DataLoader(Boolean isDevelopment) {
            this.loadPerson = isDevelopment 
                    ? this::loadPersonFake 
                    : this::loadPersonReal;
        }
        
        private Person loadPersonReal() {
            System.out.println("Loading person...");
            return new Person("Real Person", 30);
        }
        
        private Person loadPersonFake() {
            System.out.println("Returning fake person object...");
            return new Person("Fake Person", 100);
        }
    }
    
    /* demo from chapter 2, video 1: "The function interface" */
    static void demo2_1() {
        Function<Integer, Integer> myTriple = MyMath::triple;
        Integer r = myTriple.apply(5);
        System.out.println(r);
    }
    
    /* demo from chapter 2, video 2: "Lambda expressions" */
    static void demo2_2() {
        Function<Integer, Integer> absoluteValue = (x) -> x < 0 ? -x : x;
        
        System.out.println(absoluteValue.apply(-100));
    }
    
    /* demo from chapter 2, video 3: "BiFunctions and beyond" */
    static void demo2_3() {
        BiFunction<Integer, Integer, Integer> add = (x,y) -> x + y;
        
        System.out.println(add.apply(32, 32));
        
        TriFunction<Integer, Integer, Integer, Integer> add3 = (x,y,z) -> x + y + z;
        
        System.out.println(add3.apply(54, 3, 4));
        
        NoArgFunction<String> sayHello = () -> "Hello";
        
        System.out.println(sayHello.apply());
    }
    
    /* demo from chapter 2, video 4: "Functions as data" */
    static void demo2_4() {
        final boolean IS_DEVELOPMENT = true;
        DataLoader dataLoader = new DataLoader(IS_DEVELOPMENT);
        System.out.println(""+dataLoader.loadPerson.apply());
    }
    
    /* demo from chapter 2, video 5: "Passing functions as arguments" */
    static void demo2_5() {
        System.out.println(MyMath.combine2and3(MyMath::add));
        System.out.println(MyMath.combine2and3(MyMath::subtract));
        System.out.println(MyMath.combine2and3((x,y) -> 2 * x + 2 * y));
    }
    
    /* demo from chapter 2, video 6: "Returning functions" */
    static void demo2_6() {
        NoArgFunction<NoArgFunction<String>> createGreeter =
                () -> () -> "Hello functional!";
        NoArgFunction<String> greeter = createGreeter.apply();
        System.out.println(greeter.apply());
        
        Function<Integer, Integer> timesTwo = MyMath.createMultiplier(2);
        Function<Integer, Integer> timesThree = MyMath.createMultiplier(3);
        Function<Integer, Integer> timesFour = MyMath.createMultiplier(4);

        System.out.println(timesTwo.apply(6));
        System.out.println(timesThree.apply(6));
        System.out.println(timesFour.apply(6));
    }
    
    /* demo from chapter 2, video 7: "Closure" */
    static void demo2_7() {
        NoArgFunction<NoArgFunction<String>> createGreeter= () -> {
            String name = "Tim";
            return () -> "Hello, " + name;
        };
        NoArgFunction<String> greeter = createGreeter.apply();
        System.out.println(greeter.apply());
    }
    
    /* demo from chapter 2, video 8: "Higher-order functions" */
    static void demo2_8() {
        BiFunction<Float, Float, Float> divide = (x,y) -> x / y;
        Function<BiFunction<Float, Float, Float>, BiFunction<Float, Float, Float>> secondArgIsNotZeroCheck = 
                (func) -> (x,y) -> {
                    if(y == 0f) { 
                        System.out.println("Error: second argument is zero!");
                        return 0f;
                    }
                    return func.apply(x,y);
                };
        BiFunction<Float, Float, Float> divideSafe = secondArgIsNotZeroCheck.apply(divide);
        
        System.out.println(divideSafe.apply(10f, 0f));
        System.out.println(divideSafe.apply(10f, 5f));
    }
    
    /* demo from chapter 3, video 1: "Map in Java" */
    static void demo3_1() {
        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        
        //procedural
        /*
        List<Integer> doubled = new ArrayList<>();
        for(int i=0; i<listOfIntegers.size(); i++) {
            doubled.add(listOfIntegers.get(i)*2);
        }
        */
        
        //functional
        Function<Integer,Integer> timesTwo = x -> 2 * x;
        List<Integer> doubled = listOfIntegers.stream()
                .map(timesTwo)
                .collect(Collectors.toList());
        
        System.out.println(doubled);
    }
    
    /* demo from chapter 3, video 2: "Filter in Java" */
    static void demo3_2() {
        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        
        //procedural
        /*
        List<Integer> evens = new ArrayList<>();
        for(int i=0; i<listOfIntegers.size(); i++) {
            Integer n = listOfIntegers.get(i);
            Boolean isEven = n % 2 == 0;
            if(isEven) {
                evens.add(n);
            }
        }
        */
        //functional
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evens = listOfIntegers.stream()
                .filter(isEven)
                .collect(Collectors.toList());
        System.out.println(evens);
        
        String[] wordsArr = {"hello", "functional", "programming", "is", "cool"};
        List<String> words = new ArrayList<>(Arrays.asList(wordsArr));
        Function<Integer, Predicate<String>> createLengthTest = (minLength) -> {
          return (str) -> str.length() > minLength;
        };
        Predicate<String> isLongerThan5 = createLengthTest.apply(5);
        List<String> longWords = words.stream()
                .filter(isLongerThan5)
                .collect(Collectors.toList());
        System.out.println(longWords);
    }
    
    /* demo from chapter 3, video 3: "Reduce in Java" */
    static void demo3_3() {
        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        
        BinaryOperator<Integer> getSum = (acc,x) -> acc + x;
        Integer sum = listOfIntegers.stream().reduce(0, getSum);
        System.out.println(sum);
    }
    
    /* demo from chapter 3, video 4: "Collect in Java" */
    static void demo3_4() {
        String[] wordsArr = {"hello", "functional", "programming", "is", "cool"};
        List<String> words = new ArrayList<>(Arrays.asList(wordsArr));
        
        /*
        List<String> longWords = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.toList());
        
        Set<String> longWords = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.toSet());
        
        String longWords = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.joining(", "));
        
        long longWords = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.counting());
        
        Map<Integer, List<String>> longWords = words.stream()
                .filter(w -> w.length() > 5)
                .collect(Collectors.groupingBy((word) -> word.length()));
        */
        Map<Boolean, List<String>> longWords = words.stream()
                .collect(Collectors.partitioningBy((word) -> word.length() > 6));
        
        System.out.println(longWords);
    }
    
    /* demo from chapter 3, video 7: "Combining list functions" */
    static class Employee {

        private final String name;
        private final Integer age;
        private final String jobTitle;
        private final Float salary;

        public Employee(String name, Integer age, String jobTitle, Float salary) {
            this.name = name;
            this.age = age;
            this.jobTitle = jobTitle;
            this.salary = salary;
        }

        public String getName()     { return name;     }
        public Integer getAge()     { return age;      }
        public String getJobTitle() { return jobTitle; }
        public Float getSalary()    { return salary;   }
    }
    static void demo3_7() {
        Employee[] employeesArr = {
            new Employee("John", 34, "developer", 80000f),
            new Employee("Hannah", 24, "developer", 95000f),
            new Employee("Bart", 50, "sales executive", 100000f),
            new Employee("Sophie", 49, "construction worker", 40000f),
            new Employee("Darren", 38, "writer", 50000f),
            new Employee("Nancy", 29, "developer", 75000f)
        };
        List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));
        
        Float totalDeveloperSalaries = employees.stream()
                .filter((employee) -> employee.jobTitle.equals("developer"))
                .map((employee) -> employee.salary)
                .reduce(0f, (acc,x) -> acc + x);
        Long numberOfDevelopers = employees.stream()
                .filter((employee) -> employee.jobTitle.equals("developer"))
                .collect(Collectors.counting());
        Float averageDeveloperSalary = totalDeveloperSalaries / numberOfDevelopers;
        System.out.println(averageDeveloperSalary);
        
        Float totalSalaries = employees.stream()
                .map((employee) -> employee.salary)
                .reduce(0f, (acc,x) -> acc + x);
        Float totalNonDeveloperSalaries = totalSalaries - totalDeveloperSalaries;
        Long numberOfNonDevelopers = employees.size() - numberOfDevelopers;
        Float averageNonDeveloperSalary = totalNonDeveloperSalaries / numberOfNonDevelopers;
        System.out.println(averageNonDeveloperSalary);
    }
    
    /* demo from chapter 3, video 7: "Parallel streams" */
    static void demo3_8() {
        String[] wordsArr = {"hello", "apple", "functional", "programming", "is", "cool"};
        List<String> words = new ArrayList<>(Arrays.asList(wordsArr));
        
        List<String> processedWords = words.parallelStream()
                .map((word) -> {
                    System.out.println("Upperecasing: " + word);
                    return word.toUpperCase();
                })
                .map((word) -> {
                    System.out.println("Adding exclamation point to: " + word);
                    return word + "!";
                })
                .collect(Collectors.toList());
        System.out.println(processedWords);
    }
    
    /* demo from chapter 4, video 2: "Partial application" */
    static void demo4_2() {
        TriFunction<Integer, Integer, Integer, Integer> add = (x, y, z) -> x + y + z;
        
        Function<Integer, BiFunction<Integer, Integer, Integer>> addPartial =
                (x) -> (y, z) -> add.apply(x, y, z);
        
        BiFunction<Integer, Integer, Integer> add5 = addPartial.apply(5);
        
        System.out.println(add5.apply(6, 7));
        
        BiFunction<Integer, Integer, Function<Integer, Integer>> addPartial2 =
                (x, y) -> (z) -> add.apply(x, y, z);
        
        Function<Integer, Integer> add5And6 = addPartial2.apply(5, 6);
        
        System.out.println(add5And6.apply(7));
        
        // currying: pass args in one at a time
        Function<Integer, Function<Integer, Function<Integer, Integer>>> addPartial3 =
                (x) -> (y) -> (z) -> add.apply(x, y, z);
        
        Function<Integer, Function<Integer, Integer>> add3 = addPartial3.apply(3);
        Function<Integer, Integer> add3And4 = add3.apply(4);
        Integer sum = add3And4.apply(5);
        System.out.println(sum);
    }
    
    /* demo from chapter 4, video 3: "Recursion" */
    static void countDown(int x) {
        if(x >= 0) {
            System.out.println(x);
            countDown(x - 1);
        } else {
            System.out.println("done");
        }
    }
    static void countUp(int x) {
        if (x > 10) {
            System.out.println("done");
            return;
        }
        System.out.println(x);
        countUp(x + 1);
    }
    static void countUp(int x, int end) {
        if (x > end) {
            System.out.println("done");
            return;
        }
        System.out.println(x);
        countUp(x + 1, end);
    }
    static void demo4_3() {
        countDown(10);
        countUp(0);
        countUp(44, 50);
    }
    
    /* demo from chapter 4, video 4: "Composition" */
    static void demo4_4() {
        Function<Integer, Integer> timesTwo = (x) -> 2 * x;
        Function<Integer, Integer> minusOne = (x) -> x - 1;
        
        Function<Integer, Integer> timesTwoMinusOne = minusOne.compose(timesTwo);
        //Function<Integer, Integer> timesTwoMinusOne = timesTwo.andThen(minusOne);
        
        System.out.println(timesTwoMinusOne.apply(10));
        
        Employee[] employeesArr = {
            new Employee("John", 34, "developer", 80000f),
            new Employee("Hannah", 24, "developer", 95000f),
            new Employee("Bart", 50, "sales executive", 100000f),
            new Employee("Sophie", 49, "construction worker", 40000f),
            new Employee("Darren", 38, "writer", 50000f),
            new Employee("Nancy", 29, "developer", 75000f)
        };
        List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));
        
        Function<Employee, String> getName = (employee) -> employee.name;
        Function<String, String> reverse = (s) -> new StringBuilder(s).reverse().toString();
        Function<String, String> uppercase = (s) -> s.toUpperCase();
        
        Function<Employee, String> getReversedUppercaseName = getName.andThen(reverse).andThen(uppercase);
        
        List<String> results = employees.stream()
                .map(getReversedUppercaseName)
                .collect(Collectors.toList());
        
        System.out.println(results);
    }
    
    public static void main(String[] args) {
        //System.out.println("It's a Demo!");
        
        /* chapter 2 */
        //demo2_1();
        //demo2_2();
        //demo2_3();
        //demo2_4();
        //demo2_5();
        //demo2_6();
        //demo2_7();
        //demo2_8();
        
        /* chapter 3 */
        //demo3_1();
        //demo3_2();
        //demo3_3();
        //demo3_4();
        //demo3_7();
        //demo3_8();
        
        /* chapter 4 */
        //demo4_2();
        //demo4_3();
        demo4_4();
    }
}
