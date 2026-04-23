package org.example.projectmanagerapp.priority;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityLevelTest {

    @Test
    @DisplayName("Should return HIGH priority")
    void testHighPriority() {
        HighPriority highPriority = new HighPriority();
        assertEquals("HIGH", highPriority.getPriority());
    }

    @Test
    @DisplayName("Should return MEDIUM priority")
    void testMediumPriority() {
        MediumPriority mediumPriority = new MediumPriority();
        assertEquals("MEDIUM", mediumPriority.getPriority());
    }

    @Test
    @DisplayName("Should return LOW priority")
    void testLowPriority() {
        LowPriority lowPriority = new LowPriority();
        assertEquals("LOW", lowPriority.getPriority());
    }
}