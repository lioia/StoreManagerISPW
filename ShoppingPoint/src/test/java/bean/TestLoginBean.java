package bean;

import com.shoppingpoint.bean.LoginBean;
import com.shoppingpoint.exception.BeanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginBean {
    private static final String RIGHT_LENGTH = "right_length";
    // Lioi Alessandro
    @Test
    public void testLoginBean() {
        assertAll("LoginBean",
                () -> assertThrows(BeanException.class, () -> new LoginBean("a", "b")),
                () -> assertThrows(BeanException.class, () -> new LoginBean(RIGHT_LENGTH, "short")),
                () -> assertThrows(BeanException.class, () -> new LoginBean("a", RIGHT_LENGTH)),
                () -> assertDoesNotThrow(() -> new LoginBean(RIGHT_LENGTH, RIGHT_LENGTH))
        );
    }
}
