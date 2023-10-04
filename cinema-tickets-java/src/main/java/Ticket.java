/**
Class to create the tickets objects.
 */
public class Ticket {

    private final int price;
    private final int ticketQuantity;
    private final TicketType ticketType;

    public Ticket(int price, int ticketQuantity, TicketType ticketType){
        this.price = price;
        this.ticketQuantity = ticketQuantity;
        this.ticketType = ticketType;
    }

    public double getPrice(){
        return price;
    }

    public int getTicketQuantity(){
        return ticketQuantity;
    }

    public TicketType getTicketType(){
        return ticketType;
    }

    public int getSumOfPrice(){
        return price * ticketQuantity;
    }
}
