package ATM_classes;
public class UserInfo {
    UserInfo(){}
    //user details.....
    UserInfo(int accountNumber,String accountType,int balance,String pin,String email,long mobileNumber)
    {
        this.accountNumber=accountNumber;
        this.accountType=accountType;
        this.balance=balance;
        this.isBlocked=false;
        this.pin=pin;
        this.email=email;
        this.mobileNumber=mobileNumber;
    }   
    protected int withdrawlLimit=1000000;
    protected int accountNumber;
    protected String accountType;
    protected int balance;
    protected boolean isBlocked=false;
    protected String pin;
    protected String email;
    protected long mobileNumber;
    protected String getPin()
    {   
        // getter pin
        return pin;
    }
    protected void setPin(String newPin)
    {
        //setteer
        this.pin=newPin;
    }
}
