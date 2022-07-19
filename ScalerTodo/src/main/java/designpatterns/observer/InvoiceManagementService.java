package designpatterns.observer;

public class InvoiceManagementService implements Subscriber{

    InvoiceManagementService(){
        FlipKart.registerSubscriber(this,Events.ORDERS_PLACED);

        // TODO -> can add multiple events here
    }

    @Override
    public void listen(Order order) {
        generateInvoice(order);
    }

    private void generateInvoice(Order order){
        System.out.println("Generating invoice for order");
    }
}
