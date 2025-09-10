package com.rental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private int discountPercent;
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent, LocalDate dueDate,
                           BigDecimal dailyRentalCharge, int chargeDays, BigDecimal preDiscountCharge,
                           BigDecimal discountAmount, BigDecimal finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void print() {
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate);
        System.out.println("Due date: " + dueDate);
        System.out.println("Daily rental charge: " + dailyRentalCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + discountAmount);
        System.out.println("Final charge: " + finalCharge);
    }

    public Tool getTool() { return tool; }
    public int getRentalDays() { return rentalDays; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public int getDiscountPercent() { return discountPercent; }
    public LocalDate getDueDate() { return dueDate; }
    public BigDecimal getDailyRentalCharge() { return dailyRentalCharge; }
    public int getChargeDays() { return chargeDays; }
    public BigDecimal getPreDiscountCharge() { return preDiscountCharge; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getFinalCharge() { return finalCharge; }
}
