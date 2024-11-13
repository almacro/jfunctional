package org.example.challenge3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author almacro
 */
public class StreamsTest {
    
    @Test
    public void testPerson() {
        System.out.println( Streams.listPeopleNames(Streams.peopleArr) );
    }
    
    @Test
    public void testCar() {
        System.out.println( Streams.toCarMakes( Streams.listBlueCars(Streams.carsArr) ) );
    }
    
    @Test
    public void testEmployee() {
        System.out.println( Streams.sumEmployeeSalaries(Streams.employeesArr) );
    }
}
