package controller;

import com.shoppingpoint.controller.ReviewController;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReviewController {
    // Lioi Alessandro
    @Test
    public void testReviewAverage() {
        ReviewController controller = new ReviewController();
        float output = controller.calculateAverage(List.of(5f, 2.5f, 3f, 0f, 1.5f));
        assertEquals(3f, output);
    }
}
