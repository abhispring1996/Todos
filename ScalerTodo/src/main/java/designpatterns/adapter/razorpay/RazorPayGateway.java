package designpatterns.adapter.razorpay;

public class RazorPayGateway {

    public String payByCreditCard(String cardNumber, String cvv, String expiry){
        System.out.println("Payment Done by RazorPay");
        return "Payment Done by RazorPay";
    }

    public boolean checkPaymentStatus(String id){
        return false;
    }
}
