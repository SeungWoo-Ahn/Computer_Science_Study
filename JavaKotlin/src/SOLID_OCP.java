interface Payment {
    void processPayment();
}

interface ICreditCardPayment extends Payment {}

interface IPayPalPayment extends Payment {}

// PaymentProcessor 에서 새 결제 유형을 추가하려면
// 기존 PaymentProcessor 클래스를 수정해야 하므로
// 개방-폐쇄 원칙을 위반함
class PaymentProcessor {
    public void processPayment(Payment payment) {
        if (payment instanceof ICreditCardPayment) {

        } else if (payment instanceof IPayPalPayment) {

        }
    }
}

// 새 결제 유형을 추가하려면, Payment 인터페이스를 구현하는 새 클래스를 생성하여
// PaymentProcessor 를 수정할 때는 닫힌 상태로, 확장할 땐 열린 상태로 유지
class CreditCardPayment implements Payment {
    @Override
    public void processPayment() {

    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment() {

    }
}

class NicePaymentProcessor {
    public void processPayment(Payment payment) {
        payment.processPayment();
    }
}