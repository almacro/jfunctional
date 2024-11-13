
package org.example.challenge3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author almacro
 */
public class Streams {
    
    static class Person {
        private final String name;
        private final Integer age;
        
        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        public String getName() { return name; }
        public Integer getAge() { return age;  }
    }
    
    static class Car {
        private final String make;
        private final String color;
        private final Float price;
        
        public Car(String make, String color, Float price) {
            this.make = make;
            this.color = color;
            this.price = price;
        }
        public String getMake()  { return make;  }
        public String getColor() { return color; }
        public Float  getPrice() { return price; } 
    }
    
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
        
        public String  getName()     { return name;     }
        public Integer getAge()      { return age;      }
        public String  getJobTitle() { return jobTitle; }
        public Float   getSalary()   { return salary;   } 
    }
    
    static Person[] peopleArr = {
            new Person("Brandon", 23),
            new Person("Hank", 43),
            new Person("Jenna", 33),
            new Person("Veronica", 56),
            new Person("Jack", 27)
        };
    static Car[] carsArr = {
            new Car("Chevy", "red", 45000f),
            new Car("Ford", "blue", 23000f),
            new Car("Toyota", "grey", 14000f),
            new Car("Lamborghini", "blue", 150000f),
            new Car("Renault", "blue", 150000f)
        };
    static Employee[] employeesArr = {
            new Employee("John", 34, "developer", 80000f),
            new Employee("Hannah", 24, "developer", 95000f),
            new Employee("Bart", 50, "sales executive", 100000f),
            new Employee("Sophie", 49, "construction worker", 40000f),
            new Employee("Darren", 38, "writer", 50000f),
            new Employee("Nancy", 29, "developer", 75000f)
        };
    
    // Get a list that contains all the people's names
    static List<String> listPeopleNames(Person[] ps) {
        List<Person> people = new ArrayList<>(Arrays.asList(ps));
        
        Function<Person, String> personName = p -> p.getName();
        List<String> peopleNames = people.stream()
                .map(personName)
                .collect(Collectors.toList());
        
        return peopleNames;
    }
        
    // Translate list of cars to list of makes
    static List<String> toCarMakes(List<Car> cars) {
        Function<Car, String> carMake = c -> c.getMake();
        List<String> carMakes = cars.stream()
                .map(carMake)
                .collect(Collectors.toList());
        
        return carMakes;
    }
    
    // Get a list of all the blue cars
    static List<Car> listBlueCars(Car[] cs) {
        List<Car> cars = new ArrayList<>(Arrays.asList(cs));
        
        Predicate<Car> carHasBlueColor = c -> c.getColor().equals("blue");
        List<Car> blueCars = cars.stream()
                .filter(carHasBlueColor)
                .collect(Collectors.toList());
        
        return blueCars;
    }
        
    // Find the sum of all of the employees' salaries
    static float sumEmployeeSalaries(Employee[] es) {
        List<Employee> employees = new ArrayList<>(Arrays.asList(es));
        
        Function<Employee, Float> employeeSalary = e -> e.getSalary();
        BinaryOperator<Float> sumSalary = (acc,x) -> acc + x;
        float sum = employees.stream()
                .map(employeeSalary)
                .reduce(0f, sumSalary);
        
        return sum;
    }
}
