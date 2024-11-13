
package org.example.challenge4;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author almacro
 */
public class AverageGroupByJobTitleTest {
    @Test
    public void testAverageSalaryGroupByJobTitle() {
        Map<String,Float> map = AverageGroupByJobTitle.averageSalaryGroupByJobTitle();
        assertNotNull(map);
        assertEquals(map.entrySet().size(), 4);
    }
}
