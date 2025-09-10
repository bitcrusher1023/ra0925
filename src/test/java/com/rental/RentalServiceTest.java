package com.rental;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    private final RentalService rentalService = new RentalService();

    @Test
    void test1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        });
        assertEquals("Discount percent must be between 0 and 100.", exception.getMessage());
    }

    @Test
    void test2() {
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
        assertNotNull(agreement);
        assertEquals("LADW", agreement.getTool().getToolCode());
        assertEquals(new BigDecimal("3.58"), agreement.getFinalCharge());
        agreement.print();
    }

    @Test
    void test3() {
        RentalAgreement agreement = rentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
        assertNotNull(agreement);
        assertEquals("CHNS", agreement.getTool().getToolCode());
        assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge());
        agreement.print();
    }

    @Test
    void test4() {
        RentalAgreement agreement = rentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        assertNotNull(agreement);
        assertEquals("JAKD", agreement.getTool().getToolCode());
        assertEquals(new BigDecimal("8.97"), agreement.getFinalCharge());
        agreement.print();
    }

    @Test
    void test5() {
        RentalAgreement agreement = rentalService.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getToolCode());
        assertEquals(new BigDecimal("14.95"), agreement.getFinalCharge());
        agreement.print();
    }

    @Test
    void test6() {
        RentalAgreement agreement = rentalService.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getToolCode());
        assertEquals(new BigDecimal("1.49"), agreement.getFinalCharge());
        agreement.print();
    }
}