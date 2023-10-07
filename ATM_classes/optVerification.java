package ATM_classes;
import java.util.Scanner;
public class optVerification
{
    private int trials=3;
    //method to generate otp
    private int generateOtp()
    {   
        double temp=(Math.random())*10000;
        return (int)temp;//generates a 4 digit otp
    }
    //otp generation overloading
    private int generateOtp(int digits)//FUNCTION OVERLOADING
    {   
        return (int)((Math.random())*Math.pow(10,(double)digits));
    }
    protected boolean verifier(int accountNumber,BankDataBase obj)
    { 
        //sends  otp to a user ......
        int i=obj.findUserIndex(accountNumber);
        while(trials>0 && obj.users.get(i).isBlocked==false)
        {
            int endingDigits=(int)(obj.users.get(i).mobileNumber%1000),otp_temp;
            if(endingDigits<100)
            {
                System.out.printf("Kindly enter the opt sent to your registered mobile number XXXXX-XXX%d%d\n",0,endingDigits);
                otp_temp=generateOtp(4);
                System.out.println("NOTE your otp is : (I wont show this in reality)\n"+ otp_temp); 
            }
            else
            {
                System.out.printf("Kindly enter the opt sent to your registered mobile number XXXXX-XXX%d\n",endingDigits);
                otp_temp=generateOtp();
                System.out.printf("NOTE your otp is : %d (I wont show this in reality)\n",otp_temp);  
            }
            Scanner sc=new Scanner(System.in);
            int opt=sc.nextInt();
            sc.nextLine();
            if(opt==otp_temp)
            {
                System.out.printf("Otp verification succesful!!\nKindly enter new pin:",0,endingDigits);
                obj.users.get(i).pin=sc.nextLine();
                return true;
            }
            else
            {
                System.out.printf("Invalid otp!! you have %d trials remaining!!: ",--trials);
                if(trials==0)
                {
                    return false;
                }
            }
        }
        return false;
    }
    //checks user password
    protected boolean passwordverifier(int accountNumber,BankDataBase obj)
    {
        int choice=0;
       int i = obj.findUserIndex(accountNumber);
    while (trials > 0 && !obj.users.get(i).isBlocked) {
        Scanner sc = new Scanner(System.in);
        String pass = sc.next();
        if (pass.equals(obj.users.get(i).pin)) { // Corrected the comparison here
            delay(90L);
            System.out.println("Password verification successful!!");
            System.out.println("------------------------------------");
            return true;
        } else {
            System.out.printf("Invalid password!! You have %d trials remaining!!:\n1) Choose to reset password\n2) Re-enter password\nKindly re-eneter your password:", --trials);
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1 && trials!=0) {
                verifier(accountNumber, obj);// Use the verifier method to reset the password
            }
            else if (trials == 0) {
                return false;
            }
        }
    }
        return false;
    }
    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}