
package org.example.challenge1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author almacro
 */
class PointTest {

    @Test
    void testPointArithmetic() {
        Point p1 = new Point(10f, 5f);
        Point p2 = new Point(5f, -2f);

        Point p3 = p1.add(p2);
        assertEquals(p3.getX(), 15f);
        assertEquals(p3.getY(), 3f);

        Point p4 = p1.subtract(p2);
        assertEquals(p4.getX(), 5f);
        assertEquals(p4.getY(), 7f);
    }
}

