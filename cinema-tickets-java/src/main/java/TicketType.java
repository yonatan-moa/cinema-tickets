public enum TicketType {
    Infant(0),
    Child(10),
    Adult(20);

    private final int price;
    TicketType(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

}
