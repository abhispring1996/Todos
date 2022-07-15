package designpatterns.strategy;

public interface PaymentGateway {

    Long payViaCC(String cardNumber,int cvv,int expiryMonth,int expiryYear);

    PaymentStatus getStatus(Long id);

}
