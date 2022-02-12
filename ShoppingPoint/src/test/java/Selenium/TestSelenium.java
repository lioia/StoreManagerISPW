package Selenium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSelenium {
    @Test
    public void testGetPascalFromAtm() throws InterruptedException {
        TestSeleniumLioiAlessandro testSeleniumLioiAlessandro = new TestSeleniumLioiAlessandro();
        float output = testSeleniumLioiAlessandro.getPascalFromAtm();
        assertEquals(output, 101325);
    }
}
