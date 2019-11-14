package dk.brynjar.lastvegas.Repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditCard {

    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("expirationMonth")
    @Expose
    private int expirationMonth;
    @SerializedName("expirationYear")
    @Expose
    private int expirationYear;
    @SerializedName("cvc")
    @Expose
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
