package dk.brynjar.lastvegas.Repository;

public class CreditCard {

    private int number;
    private int expirationMonth;
    private int expirationYear;
    private int cvc;
    public CreditCard(int number, int expirationMonth, int expirationYear, int cvc){
        this.number=number;
        this.expirationMonth=expirationMonth;
        this.expirationYear=expirationYear;
        this.cvc=cvc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

}
