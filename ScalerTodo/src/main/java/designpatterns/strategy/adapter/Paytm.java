package designpatterns.strategy;

// TODO -> Adapter Pattern to connect with Third party classes
public class Paytm {

    private PaymentGateway paymentGateway;

    public Paytm(PaymentGateway paymentGateway){
        this.paymentGateway= paymentGateway;
    }

    public void makePaymentViaCc(String cardNumber,int cvv,int expiryMonth,int expiryYear){

        Long txnId = paymentGateway.payViaCC(cardNumber,cvv,expiryMonth,expiryYear);

        while(!PaymentStatus.SUCCESS.equals(paymentGateway.getStatus(txnId))){
            System.out.println("Waiting for Success");
        }
    }
}
