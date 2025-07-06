package ph.gov.mgmt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ServiceUtilsTest {

    @Test
    @DisplayName("Should return an instance when new is called")
    void constructor_shouldReturnInstance_whenNewIsCalled() throws Exception {
        Constructor<ServiceUtils> constructor = ServiceUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    @Test
    @DisplayName("Should return true when left > right")
    void isGreaterThan_shouldReturnTrue_whenLeftGreaterThanRight() {
        assertTrue(ServiceUtils.isGreaterThan(new BigDecimal("1.00"), new BigDecimal("0.00")));
    }

    @Test
    @DisplayName("Should return false when left <= right")
    void isGreaterThan_shouldReturnFalse_whenLeftLessThanOrEqualToRight() {
        assertFalse(ServiceUtils.isGreaterThan(new BigDecimal("1.00"), new BigDecimal("2.00")));
        assertFalse(ServiceUtils.isGreaterThan(new BigDecimal("1.00"), new BigDecimal("1.00")));
    }

    @Test
    @DisplayName("Should return true when left < right")
    void isLesserThan_shouldReturnTrue_whenLeftLessThanRight() {
        assertTrue(ServiceUtils.isLesserThan(new BigDecimal("0.00"), new BigDecimal("1.00")));
    }

    @Test
    @DisplayName("Should return false when left >= right")
    void isLesserThan_shouldReturnFalse_whenLeftGreaterThenOrEqualToRight() {
        assertFalse(ServiceUtils.isLesserThan(new BigDecimal("1.00"), new BigDecimal("0.00")));
        assertFalse(ServiceUtils.isLesserThan(new BigDecimal("1.00"), new BigDecimal("1.00")));
    }

    @Test
    @DisplayName("Should return true when left = right")
    void isEquals_shouldReturnTrue_whenLeftEqualsToRight() {
        assertTrue(ServiceUtils.isEquals(new BigDecimal("1.00"), new BigDecimal("1.00")));
    }

    @Test
    @DisplayName("Should return false when left <> right")
    void isEquals_shouldReturnFalse_whenLeftNotEqualsToRight() {
        assertFalse(ServiceUtils.isEquals(new BigDecimal("0.01"), new BigDecimal("1.00")));
    }

    @Test
    @DisplayName("Should return true when left tag = right tag")
    void isSameTag_shouldReturnTrue_whenLeftSameTagAsRight() {
        assertTrue(ServiceUtils.isSameTag('Y', 'Y'));
    }

    @Test
    @DisplayName("Should return false when left tag <> right tag")
    void isSameTag_shouldReturnFalse_whenLeftNotSameTagAsRight() {
        assertFalse(ServiceUtils.isSameTag('N', 'A'));
    }

    @Test
    @DisplayName("Should return true when left same as right")
    void isSameTag_shouldReturnTrue_whenLeftSameAsRight() {
        assertTrue(ServiceUtils.isSameTag("START", "START"));
        assertTrue(ServiceUtils.isSameTag("StArT", "start", false));
    }

    @Test
    @DisplayName("Should return false when left not same as right")
    void isSameTag_shouldReturnFalse_whenLeftNotSameAsRight() {
        assertFalse(ServiceUtils.isSameTag("StArT", "start"));
        assertFalse(ServiceUtils.isSameTag("End", "Begin", false));
    }

}