package ATM_classes;
import java.util.Scanner;
public final class admin 
{
    // Admin class handles administrative operations.

    // Password for admin access.
    protected String password="Krishna";
    void adminOperations( BankDataBase obj)
    {   // Method for performing admin operations like adding or modifying accounts.
        Scanner sc=new Scanner(System.in);
        System.out.println("What operations will you like to perform ?:\n1)add new account 2) modify an account ");
        int operations=sc.nextInt();
        switch(operations)
        {
            case 1:
            // Adding a new account with user-provided information.
            System.out.println("Enter the account number:\n");
            int accountNumber=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the accountType:\n");
            String accountType=sc.next();
            System.out.println("Enter the balance:\n");
            int balance=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the pin:\n");
            String pin=sc.next();
            System.out.println("Enter the email:\n");
            String email=sc.nextLine();
            System.out.println("Enter the mobileNumber:\n");
            long mobileNumber=sc.nextLong();
            obj.addaccount(accountNumber, accountType, balance, pin, email, mobileNumber);
            break;
            case 2:
            // Modifying an existing account.
            System.out.println("Kindly enter the accountnumber of the user:\n");
            accountNumber=sc.nextInt();
            obj.changeInfo(accountNumber);
            break;
            default:
            System.out.println("Invalid option");
        }
    }
}