package controller;

import com.shoppingpoint.controller.AcceptOfferController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAcceptOfferController {
    // Ronzello Gianluca
    @Test
    public void testIncrementOfRequestPrice() {
        AcceptOfferController controller = new AcceptOfferController();
        float output = controller.getIncrementOfRequestPrice(150, 75);
        assertEquals(50f, output);
    }
}
