package designpatterns.adapter;

import designpatterns.adapter.razorpay.RazorPayGateway;

public class RazorPayAdapter implements PaymentGateway{
    private final RazorPayGateway razorPayGateway = new RazorPayGateway();

    @Override
    public Long payViaCC(String cardNumber, int cvv, int expiryMonth, int expiryYear) {

        String cvvNumber = String.valueOf(cvv);
        String expiry = expiryMonth +"/"+ expiryYear;

        String txnId= razorPayGateway.payByCreditCard(cardNumber,cvvNumber,expiry);

        return Long.parseLong(txnId);
    }

    @Override
    public PaymentStatus getStatus(Long id) {
        boolean status= razorPayGateway.checkPaymentStatus(String.valueOf(id));

        if(status==true){
            return PaymentStatus.SUCCESS;
        }

        return PaymentStatus.FAILURE;
    }
}
