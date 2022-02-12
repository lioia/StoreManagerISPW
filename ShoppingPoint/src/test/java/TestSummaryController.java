import com.example.shoppingpoint.controller.SummaryController;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSummaryController {
    @Test
    public void TestCalculateIncrementalProfit() {
        SummaryController controller = new SummaryController();
        float output = controller.calculateIncrementalProfit(List.of(7f, 11f, 9f, 13f, 5f), List.of(20f, 12f, 7f, 6f, 3f, 2f));
        assertEquals(output, 11.111112f);
    }
}
