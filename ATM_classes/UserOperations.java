package ATM_classes;
import java.util.Scanner;
public abstract class UserOperations extends UserInfo
{   //otp verificatiion
    private optVerification verifier=new optVerification();
    protected void changePin(int accountNumber,BankDataBase obj)
    {
        int i=obj.findUserIndex(accountNumber);
        if(verifier.verifier(accountNumber,obj)==false)
        {
            System.out.println("Your account is blocked due to many failed login attempts!! please contact our customer care or visit our branch for more info!");    
            obj.users.get(i).isBlocked=true;
        }
    }
    //an atm should have a method of withrawing
    abstract void withdraw(UserInfo user);
    //kept abstract as different atms have different capacity of keeping notes thereby users can withdraw more form a machine than from other so machine dependent
    abstract void deposit(int amount,UserInfo user);//kept abstract as different atms have different capacity of keeping notes thereby users can deposit more form a machine than from other so machine dependent
    protected void viewBalance(UserInfo user)
    {   //views balance
        System.out.printf("Your account balance is : %,d ruppes\n",user.balance);
    }
    void changeInfo(UserInfo user)
    {   //changes info of user
        Scanner sc = new Scanner(System.in);
        System.out.println("Our Systems allow you to change the following info:");
        System.out.println("1) Your email");
        System.out.println("2) Your Daily Withdrawal Limit");
        System.out.print("Enter the number of the choice: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.print("Enter new email: ");
                user.email = sc.nextLine();
                System.out.println("Email changed successfully.");
                break;
            case 2:
                System.out.print("Enter new daily withdrawal limit: ");
                user.withdrawlLimit = sc.nextInt();
                System.out.println("Daily withdrawal limit changed successfully.");
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
