import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    /**
     * Main class to run the application
     */
    public static void main(String[] args) {

        // variables that will track the quantity of tickets by type.
        // This will help us keep a record of the number of tickets sold for each type.
        int adultTicketsQuantity;
        int infantTicketQuantity;
        int childTicketQuantity;
        int totalTicketQuantity = 0;
        // Variable that keeps the Total of tickets sold.
        int purchaseCost = 0;
        // Variable used for the do-while loop
        boolean transctionAccepted = false;

        // -------------------------------------------------------------------------------------------------

        // Start of the purchase system
        System.out.println("Purchase Ticket System\nWelcome");

        // The list of the ticket available and price
        System.out.println("Event Ticket types available and Prices");
        System.out.println("Infant £" + TicketType.Infant.getPrice());
        System.out.println("Child £" + TicketType.Child.getPrice());
        System.out.println("Adult £" + TicketType.Adult.getPrice() + "\n");

        System.out.println("The maximum number of tickets allowed per transaction is 20\n" +
                "Infants and children must be accompanied by an adult");

        // -------------------------------------------------------------------------------------------------
        // Do and while loop to filter and validate user (Purchaser) input
        do {
            System.out.println("Maximum tickets allowed for purchase is 20:\nAdults: ");

            // Uses the Scanner to get the user input
            Scanner inputAdultsTicketsQuantity = new Scanner(System.in);

            // Validate that the input is int, if not prompt the user to change for a int.
            while (!inputAdultsTicketsQuantity.hasNextInt()){
                System.out.println("Please enter a whole number, please");
                inputAdultsTicketsQuantity.next();
            }

            // the variable receives the adult quantity tickets the user required
            adultTicketsQuantity = inputAdultsTicketsQuantity.nextInt();

            // If the number of adult tickets is 0 or greater than 20, the user will be prompted to enter a
            // valid number of tickets that is not zero
             if (adultTicketsQuantity == 0 & adultTicketsQuantity <= 20){
                 System.out.println("No adult ticket selected, please enter between 1 and 20 tickets adult tickets");
             }

             // If the ticket quantity falls within the specified range, then proceed with the process
             if (adultTicketsQuantity > 0 & adultTicketsQuantity <= 20){
                // After validating the quantities of adult tickets, proceed with the purchaser system
                 System.out.println("Child tickets ");
                 Scanner inputChildQuantity = new Scanner(System.in);

                 // If the user does not enter a whole number, prompt to correct the entry.
                 while(!inputChildQuantity.hasNextInt()){
                     System.out.println("Please enter a whole number, please");
                     inputChildQuantity.next();
                 }
                 // Assign the value of the Scanner object to child ticket quantity
                 childTicketQuantity = inputChildQuantity.nextInt();

                 System.out.println("Infant tickets ");
                 Scanner inputInfantQuantity = new Scanner(System.in);
                 while(!inputInfantQuantity.hasNextInt()){
                     System.out.println("Please enter a whole number, please");
                     inputInfantQuantity.next();
                 }
                 infantTicketQuantity = inputInfantQuantity.nextInt();

                 // The sum of the tickets is saved in the variable.
                 totalTicketQuantity = adultTicketsQuantity + childTicketQuantity + infantTicketQuantity;

                 // ----------------------------------------------------------------------------------------------
                 // If the quantity of tickets exceeds the limit of 20, prompt the user to correct it or exit
                 if (totalTicketQuantity > 20){
                     System.out.println("It appears that you are trying to purchase more than 20 tickets. " +
                             "Please note that the maximum number of tickets that can be purchased is 20. " +
                             "Please adjust your ticket purchase accordingly.");

                     System.out.println("Would you like to proceed with modifying the quantities of the tickets? (Y/N)");
                     Scanner inputContinue = new Scanner(System.in);
                     String inputClient = inputContinue.nextLine();
                     // In case the response is Yes (Y), the do-while loop process will restart
                     if (inputClient.equalsIgnoreCase("y")){
                         continue;
                     }
                     // If the response is No (N) the application exit at this point
                     else if(inputClient.equalsIgnoreCase("n")) {
                         System.out.println("Thank you for using the application. The program will now exit");
                         exit(1);
                     }
                 }
                 // ---------------------------------------------------------------------------------------------

                 // Creates the Ticket instances
                 Ticket adultTicket = new Ticket(TicketType.Adult.getPrice(),adultTicketsQuantity, TicketType.Adult);
                 Ticket childTicket = new Ticket(TicketType.Child.getPrice(), childTicketQuantity, TicketType.Child);
                 Ticket infantTicket = new Ticket(TicketType.Infant.getPrice(), infantTicketQuantity, TicketType.Child);

                 // Get the total price of the tickets
                 purchaseCost = adultTicket.getSumOfPrice() + childTicket.getSumOfPrice() + infantTicket.getSumOfPrice();
                 // Output the Total cost of the transaction
                 System.out.println("Total Cost: " + purchaseCost);
                 // Get user confirmation
                 System.out.println("Would you like to proceed with purchase? (Y/N)");

                 Scanner inputPurchase = new Scanner(System.in);
                 String approvalPurchase = inputPurchase.nextLine();
                 // if purchaser returns Y, the adult
                 if (approvalPurchase.equalsIgnoreCase("y")){
                     transctionAccepted = true;
                 } else if (approvalPurchase.equalsIgnoreCase("n")) {
                     System.out.println("Thank you. The purchase application is now exiting.");
                     exit(1);
                 }

             }
        }
        while (!transctionAccepted);
        // --------------------------------------------------------------------------------------------------------
        // System makes reservations
        SeatReservationServiceImpl makeReservation = new SeatReservationServiceImpl();
        makeReservation.reserveSeat(1, totalTicketQuantity);
        // System makes the transaction
        TicketPaymentServiceImpl makePayment = new TicketPaymentServiceImpl();
        makePayment.makePayment(1, purchaseCost);
        System.out.println("Reservations made successfully, you can collect your tickets\n");
    }
}
