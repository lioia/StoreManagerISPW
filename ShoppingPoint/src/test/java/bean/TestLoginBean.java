package bean;

import com.shoppingpoint.bean.LoginBean;
import com.shoppingpoint.exception.BeanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginBean {
    // Lioi Alessandro
    @Test
    public void testLoginBean() {
        assertAll("LoginBean",
                () -> assertThrows(BeanException.class, () -> new LoginBean("a", "b")),
                () -> assertThrows(BeanException.class, () -> new LoginBean("right_length", "short")),
                () -> assertThrows(BeanException.class, () -> new LoginBean("a", "right_length")),
                () -> assertDoesNotThrow(() -> new LoginBean("right_length", "right_length"))
        );
    }
}
