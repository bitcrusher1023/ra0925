# Tool Rental System

This project implements a point-of-sale tool rental application for a hardware store.  
The system is written in **Java** and includes a comprehensive **JUnit test suite**.  
It supports tool rentals, pricing rules for weekdays/weekends/holidays, discount handling,  
and generation of rental agreements.

---

## Features

- Checkout process with:
  - Tool code, rental days, discount percent, and checkout date as inputs
  - Validation of rental days and discount percent
- Rental agreement generation with:
  - Tool details (code, type, brand)
  - Rental days, checkout date, and due date
  - Daily rental charge
  - Charge days based on tool type and date rules
  - Pre-discount charge, discount amount, and final charge
- Holiday handling:
  - **Independence Day (July 4)** with weekend observation rules
  - **Labor Day (first Monday of September)**
- Pricing rules per tool type:
  - Ladder → Weekday & weekend charges
  - Chainsaw → Weekday & holiday charges
  - Jackhammer → Weekday only

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/<your-repo-name>.git
   cd <your-repo-name>
   ```

2. Run tests with Maven (or from your IDE):

   ```bash
    mvn test
    ```

3. Run the program directly by creating a small driver class or using RentalService in your own main method.

## Testing

The solution includes JUnit 5 tests covering the six required scenarios:

- Invalid discount (>100%)
- Ladder with weekend rules
- Chainsaw with holiday rules
- Jackhammer (weekday only)
- Extended rentals
- Discounted rentals
- Each test asserts the final charge and prints the rental agreement.

## AI Usage Documentation

I completed this assessment without using AI tools (e.g., ChatGPT, Copilot, Claude).
I wrote the code, tests, and logic independently to demonstrate my own design and problem-solving approach.

Because no AI assistance was used, there is no AI_INTERACTION_LOG.md file in this repository.