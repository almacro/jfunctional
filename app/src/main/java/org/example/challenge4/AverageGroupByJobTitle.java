/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.challenge4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author almacro
 */
public class AverageGroupByJobTitle {

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
    }
    static Employee[] employeesArr = {
        new Employee("John", 34, "developer", 80000f),
        new Employee("Hannah", 24, "developer", 95000f),
        new Employee("Bart", 50, "sales executive", 100000f),
        new Employee("Sophie", 49, "construction worker", 40000f),
        new Employee("Darren", 38, "writer", 50000f),
        new Employee("Nancy", 29, "developer", 75000f)
    };
    
    static Map<String,Float> averageSalaryGroupByJobTitle() {
        List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));
        /*
        Map<String,Float> averageSalariesMap = employees.stream()
                .collect(Collectors.groupingBy((employee) -> employee.jobTitle))
                .entrySet()
                .stream()
                .map(e -> {
                    List<Float> salaries = e.getValue().stream()
                            .map((employee) -> employee.salary)
                            .collect(Collectors.toList());
                    Float total = salaries.stream()
                            .reduce(0f, (acc, x) -> acc + x);
                    Float averageSalary = total / salaries.size();
                    return new HashMap.SimpleEntry<String, Float>(e.getKey(), averageSalary);
                })
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));
        */
        
        Map<String, Float> averageSalariesMap = employees.stream()
                .collect(Collectors.groupingBy((employee) -> employee.jobTitle))
                .entrySet()
                .stream()
                .collect(Collectors.toMap((entry) -> entry.getKey(),
                        (entry) -> entry.getValue()
                                        .stream()
                                        .map((employee) -> employee.salary)
                                        .reduce(0f, (acc, x) -> acc + x) / entry.getValue().size()));
        
        System.out.println(averageSalariesMap);
        return averageSalariesMap;
    }
}
