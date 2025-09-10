    package com.rental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class RentalService {
    private static final Map<String, Tool> tools = new HashMap<>();
    private static final Map<String, ToolPricing> toolPricings = new HashMap<>();

    static {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid"));

        toolPricings.put("Ladder", new ToolPricing(new BigDecimal("1.99"), true, true, false));
        toolPricings.put("Chainsaw", new ToolPricing(new BigDecimal("1.49"), true, false, true));
        toolPricings.put("Jackhammer", new ToolPricing(new BigDecimal("2.99"), true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        Tool tool = tools.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        ToolPricing pricing = toolPricings.get(tool.getToolType());
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, pricing);

        BigDecimal preDiscountCharge = pricing.getDailyCharge().multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAmount = preDiscountCharge.multiply(new BigDecimal(discountPercent)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        return new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent, dueDate, pricing.getDailyCharge(),
                chargeDays, preDiscountCharge, discountAmount, finalCharge);
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolPricing pricing) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);
        while (!currentDate.isAfter(dueDate)) {
            if (isChargeable(currentDate, pricing)) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }

    private boolean isChargeable(LocalDate date, ToolPricing pricing) {
        if (isHoliday(date)) {
            return pricing.isHolidayCharge();
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return pricing.isWeekendCharge();
        }
        return pricing.isWeekdayCharge();
    }

    private boolean isHoliday(LocalDate date) {
        // Independence Day
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4) {
            return true;
        }
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
            // Observed on Friday if 4th is a Saturday
            return isIndependenceDay(date.plusDays(1));
        }
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
            // Observed on Monday if 4th is a Sunday
            return isIndependenceDay(date.minusDays(1));
        }

        // Labor Day
        if (date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }

        return false;
    }

    private boolean isIndependenceDay(LocalDate date) {
        return date.getMonth() == Month.JULY && date.getDayOfMonth() == 4;
    }

    private static class ToolPricing {
        private BigDecimal dailyCharge;
        private boolean weekdayCharge;
        private boolean weekendCharge;
        private boolean holidayCharge;

        public ToolPricing(BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
            this.dailyCharge = dailyCharge;
            this.weekdayCharge = weekdayCharge;
            this.weekendCharge = weekendCharge;
            this.holidayCharge = holidayCharge;
        }

        public BigDecimal getDailyCharge() {
            return dailyCharge;
        }

        public boolean isWeekdayCharge() {
            return weekdayCharge;
        }

        public boolean isWeekendCharge() {
            return weekendCharge;
        }

        public boolean isHolidayCharge() {
            return holidayCharge;
        }
    }
}
