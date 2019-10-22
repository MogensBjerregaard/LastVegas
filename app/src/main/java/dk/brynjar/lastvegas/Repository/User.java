package dk.brynjar.lastvegas.Repository;

public class User {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private int credit;

    private boolean isUserLoggedIn;

    public User(String firstName, String lastName, String email, String phone){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone=phone;
        credit = 0;
        isUserLoggedIn = false;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public int getCredit(){
        return credit;
    }
    public void setCredit(int credit){
        if (credit<0) throw new IllegalArgumentException("Negative credit not allowed.");
        this.credit = credit;
    }
    public boolean isCreditZero(){
        return credit==0? true:false;
    }
    public void logUserIn(){
        isUserLoggedIn=true;
    }
    public void logUserOut(){
        isUserLoggedIn=false;
    }
}
