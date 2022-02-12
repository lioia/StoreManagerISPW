import com.example.shoppingpoint.controller.AcceptOfferController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAcceptOfferController {
    @Test
    public void TestIncrementOfRequestPrice() {
        AcceptOfferController controller = new AcceptOfferController();
        float output = controller.getIncrementOfRequestPrice(150, 75);
        assertEquals(output, 50f);
    }
}