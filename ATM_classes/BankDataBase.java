package ATM_classes;
import java.util.*;
public class BankDataBase
{
    Scanner sc=new Scanner(System.in);
    ArrayList<UserInfo> users=new ArrayList<UserInfo>();//list of users with provision to always add more users
    public BankDataBase()
    {
        UserInfo user_temp=new UserInfo(1, "Savings", 100000, "123", "userone@gmail.com", 9999999999L);
        users.add(user_temp);
    }
    protected int findUserIndex(int accountNumber)
    {   //find user in arraylist based on account number
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).accountNumber==accountNumber)
            {
                return i;
            }
        } 
        return -1;
    }
    protected void addaccount(int accountNumber,String accountType,int balance,String pin,String email,long mobileNumber)
    {   //adds new account....
        UserInfo temp=new UserInfo(accountNumber,accountType,balance,pin,email,mobileNumber);
        users.add(temp);
    }
    private int change=0;
    protected void changeInfo(int accountNumber)
    {   //changes info of current user
        if(users.size()==0)
        {//checks if no account is there or not
            System.out.println("There are no accounts loaded into machine yet\nPlease Enter some accounts and Try again");
            return;
        }
        int i=findUserIndex(accountNumber);
        while(i==-1)
        {
            System.out.println("invalid account number kindly re-enter account number");
            accountNumber=sc.nextInt();
            changeInfo(accountNumber);
            return;
        }
        change=1;
        boolean exit=false;
        while(!exit)
        {   //methods to change account details
            System.out.println("What do you want to change:\n1)accountNumber\n2)Balance\n3)accountType\n4)pin\n5)email\n6)mobile number\n7)BlockStatus");
            int option=sc.nextInt();
            sc.nextLine();
            switch(option)
            {
                case 1:
                System.out.print("Enter new account number: ");
                int newAccountNumber = sc.nextInt();
                users.get(i).accountNumber = newAccountNumber;
                break;
                case 2:
                System.out.print("Enter new balance: ");
                int newBalance = sc.nextInt();
                users.get(i).balance = newBalance;
                break;
                case 3:
                System.out.print("Enter new account type: ");
                String newAccountType = sc.nextLine();
                users.get(i).accountType = newAccountType;
                break;
                case 4:
                System.out.print("Enter new PIN: ");
                String newPin = sc.nextLine();
                users.get(i).setPin(newPin);
                    break;
                case 5:
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                users.get(i).email = newEmail;
                break;
                case 6:
                System.out.print("Enter new mobile number: ");
                long newMobileNumber = sc.nextLong();
                users.get(i).mobileNumber = newMobileNumber;
                break;
                case 7:
                System.out.print("Enter new block status (true/false): ");
                boolean newBlockStatus = sc.nextBoolean();
                users.get(i).isBlocked = newBlockStatus;
                break;
                default:
                System.out.println("Invalid option");
            }
            System.out.println("do you want to exit or continue changing? 1) continue changing 2)exit");
            option=sc.nextInt();
            if(option==1)
            {
                continue;
            }
            exit=true;
        }
    }   
}
