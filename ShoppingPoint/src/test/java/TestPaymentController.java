import com.example.shoppingpoint.controller.PaymentController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaymentController {
    @Test
    public void TestDiscountPercentage() {
        PaymentController controller = new PaymentController();
        float output = controller.calculateDiscountPercentage(100, 80);
        assertEquals(output, 20.0);
    }

    @Test
    public void TestPointsUsed() {
        PaymentController controller = new PaymentController();
        int output = controller.calculatePointsUsed(10, 30, 2);
        assertEquals(output, 20);
    }
}
