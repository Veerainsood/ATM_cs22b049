package ATM_classes;
import java.util.Scanner;
import java.lang.Thread.*;
public class BankAtm extends UserOperations implements atmMachine 
{   //Implements bankAtm functionality
    private int capacity[]=new int[7];//
    private int temp=0;
    private int userAccountNumber;//no access from outside
    Scanner sc=new Scanner(System.in);
    optVerification verifier=new optVerification();
    int checkifpass=0,checkifpassAdmin=0;
    admin Krishna = new admin();
    public BankAtm()
    {
        this.capacity[0]=100;
        this.capacity[1]=100;
        this.capacity[2]=100;
        this.capacity[3]=100;
        this.capacity[4]=100;
        this.capacity[5]=100;
        this.capacity[6]=100;
    }
    private BankDataBase obj=new BankDataBase();
    public void screen(BankDataBase obj)
    {   this.obj=obj;
        boolean adminblock=false;
        if(temp==0)
        {   //these is just for checking proper validity of user and for admin interface
            if(checkifpass==0 && checkifpassAdmin==0)
            {
                System.out.println("\t--------------------Welcome to Bank of Krishna--------------------\nDear user please enter your account number:\n");
                userAccountNumber=sc.nextInt();
                sc.nextLine();
                delay(90L);
            }
            if(userAccountNumber==9999 && adminblock==false || checkifpassAdmin==1 && userAccountNumber==9999)
            {
                String password="";
                if(checkifpassAdmin==0)
                {
                    System.out.printf("Welcome Krishna please enter your password:\n");
                    password=sc.next();
                    delay(90L);
                }
                if((checkifpassAdmin==0) ? password.equals(Krishna.password): (checkifpassAdmin!=0))
                {
                    checkifpassAdmin=1;
                    System.out.println("Will you like to place money into the machine?? or change something in the database?\n1)change in database(add accounts / modify accounts)\n2)add money into atm\n3)exit");
                    int choice=sc.nextInt();
                    if(choice==2)
                    {
                        System.out.println("Enter the number of notes for each denomination:");
                        System.out.print("₹10 notes: ");
                        int tenrupee = sc.nextInt();
                        System.out.print("₹20 notes: ");
                        int twentyrupee = sc.nextInt();
                        System.out.print("₹50 notes: ");
                        int fifty = sc.nextInt();
                        System.out.print("₹100 notes: ");
                        int hundr = sc.nextInt();
                        System.out.print("₹200 notes: ");
                        int twohund = sc.nextInt();
                        System.out.print("₹500 notes: ");
                        int fivehund = sc.nextInt();
                        System.out.print("₹2000 notes: ");
                        int twothou = sc.nextInt();
                        // Updated the ATM's capacity with the new denominations
                        setCapacity(
                            capacity[0] + tenrupee,
                            capacity[1] + twentyrupee,
                            capacity[2] + fifty,
                            capacity[3] + hundr,
                            capacity[4] + twohund,
                            capacity[5] + fivehund,
                            capacity[6] + twothou
                            );
                        }
                    else if(choice==1)
                    {
                        Krishna.adminOperations(obj);  
                    }
                    else
                    {
                        checkifpassAdmin=0;
                        screen(obj);
                    }
                }
                else
                {
                    adminblock=true;
                    System.err.println("Your password is incorrect , please contact the security section to make changes");
                }
            }
            while(obj.findUserIndex(userAccountNumber)==-1 && userAccountNumber!=9999)
            {
                System.out.printf("The entered account number %d does not exits\nPlease re-eneter the correct account number or visit our nearest branch\n",userAccountNumber);
                userAccountNumber=sc.nextInt();
                delay(90L);
            }
            int i=obj.findUserIndex(userAccountNumber);
            if(userAccountNumber!=9999)//user is not admin
            {
                if(checkifpass==0 && obj.users.get(i).isBlocked==false)
                {
                    System.out.printf("Welcome user please enter your password:\n");
                }
                if((checkifpass == 0) ? verifier.passwordverifier(userAccountNumber, obj) : (checkifpass != 0))
                {
                    checkifpass=1;
                    System.out.println("------------------------------------");
                    System.out.println("Dear user please select one of the following:-\n1)Balance Inquiriy\n2)Cash Withdrawl\n" + //
                    "3)Cash Deposit\n4)Change Info\n5)Exit");
                    int choose;
                    choose=sc.nextInt();
                    delay(90L);
                    keyPad(choose,obj.users.get(i));
                }
                else
                {
                    System.out.println("your account is blocked!! please contact the nearst branch!!");
                    obj.users.get(i).isBlocked=true;
                    return;
                }
            }
            else
            {
                screen(obj);
            }
        }
    }

    public void keyPad(int choose,UserInfo user)
    {//keypad method
        switch(choose)
        {
            case 1:
            viewBalance(user);
            screen(obj);
            break;
            case 2:
            withdraw(user);
            screen(obj);
            break;
            case 3:
            depositSlot(user);
            screen(obj);
            break;
            case 4:
            changeInfo(user);
            screen(obj);
            break;
            case 5:
            temp=0;
            System.exit(0);
            default:
            System.err.println("You did not choose a valid response please try again!");
            screen(obj);
        } 
    }
    //₹10, ₹20, ₹50, ₹100 ₹200, ₹500, and ₹2000 are currently active
    //below functions setCapacity are implemented by admin and are OVERLOADED
    //below functions setCapacity are implemented by admin and are OVERLOADED
    //below functions setCapacity are implemented by admin and are OVERLOADED
    //below functions setCapacity are implemented by admin and are OVERLOADED
    //below functions setCapacity are implemented by admin and are OVERLOADED
    //below functions setCapacity are implemented by admin and are OVERLOADED
    protected void setCapacity(int tenrupee,int twentyrupee,int fifty ,int hundr,int twohund,int fivehund,int twothou)
    {
        this.capacity[0]=tenrupee;
        this.capacity[1]=twentyrupee;
        this.capacity[2]=fifty;
        this.capacity[3]=hundr;
        this.capacity[4]=twohund;
        this.capacity[5]=fivehund;
        this.capacity[6]=twothou;
    }
    protected void setCapacity(int hundr,int twohund,int fivehund,int twothou)//overloading
    {
        this.capacity[0]=0;
        this.capacity[1]=0;
        this.capacity[2]=0;
        this.capacity[3]=hundr;
        this.capacity[4]=twohund;
        this.capacity[5]=fivehund;
        this.capacity[6]=twothou;
    }
    void withdraw(UserInfo user)
    {   //function for withdrawing amount
        System.out.println("Kindly enter amount to be withrawn:");
        int amount=sc.nextInt();
        delay(90L);
        //checking if it is more than amount present in account
        if(amount>user.balance)
        {
            System.out.println("Insufficient Balance!!");
            screen(obj);
        }
        //if it is inside withdrawl limit
        else if(amount>user.withdrawlLimit)
        {
            System.out.println("The Amount entered is above your daily Balance Limit!!");
            screen(obj);
        }
        //making computation for withdrawl for notes...
        int twoth=0,fivehun=0,twohun=0,hun=0,fif=0,twen=0,ten=0;
        while((amount%2000)!=amount)
        {
            twoth=(amount/2000);
            amount=(amount%2000);
        }
        while((amount%500)!=amount)
        {
            fivehun=(amount/500);
            amount=(amount%500);
        }
        while((amount%200)!=amount)
        {
            twohun=(amount/200);
            amount=(amount%200);
        }
        while((amount%100)!=amount)
        {
            hun=(amount/100);
            amount=(amount%100);
        }
        while((amount%50)!=amount)
        {
            fif=(amount/50);
            amount=(amount%50);
        }
        while((amount%20)!=amount)
        {
            twen=(amount/20);
            amount=(amount%20);
        }
        while((amount%10)!=amount)
        {
            ten=(amount/10);
            amount=(amount%10);
        }
        //checking if amount of notes suffiecient for transaction
        if(twoth>capacity[6] || fivehun>capacity[5] ||twohun>capacity[4] ||hun>capacity[3] ||fif>capacity[2] ||twen>capacity[1] ||ten>capacity[0] )
        {
            System.out.println("Your request could not be processed due to lack of money in the machine we appologise for the inconvineinece");
            screen(obj);
        }
        //telling my computation to user
        else
        {   
            System.out.println("------------------------------------");
            System.out.printf("Your recieved:\n%d two thousand notes\n%d five hundered notes\n%d two hundred notes\n%d hundered notes\n%d fifty notes\n%d twenty notes\n%d ten notes\n",twoth,fivehun,twohun,hun,fif,twen,ten);
            System.out.println("------------------------------------");
            user.balance=user.balance-twoth*2000-fivehun*500-twohun*200-hun*100-fif*50-twen*20-ten*10 -amount;
            cashDispenser(twoth,fivehun,twohun,hun,fif,twen,ten);
        }
    }
    void deposit(int amount,UserInfo user)
    { //computation to deposit amount in user account + update number of  notes into the atm machine
        user.balance+=amount;
        int twoth=0,fivehun=0,twohun=0,hun=0,fif=0,twen=0,ten=0;
        while((amount%2000)!=amount)
        {
            twoth=(amount/2000);
            amount=(amount%2000);
        }
        while((amount%500)!=amount)
        {
            fivehun=(amount/500);
            amount=(amount%500);
        }
        while((amount%200)!=amount)
        {
            twohun=(amount/200);
            amount=(amount%200);
        }
        while((amount%100)!=amount)
        {
            hun=(amount/100);
            amount=(amount%100);
        }
        while((amount%50)!=amount)
        {
            fif=(amount/50);
            amount=(amount%50);
        }
        while((amount%20)!=amount)
        {
            twen=(amount/20);
            amount=(amount%20);
        }
        while((amount%10)!=amount)
        {
            ten=(amount/10);
            amount=(amount%10);
        }
        capacity[6]+=twoth;
        capacity[5]+=fivehun;
        capacity[4]+=twohun;
        capacity[3]+=hun;
        capacity[2]+=fif;
        capacity[1]+=twen;
        capacity[0]+=ten;
    }
    public void cashDispenser(int twoth,int fivehun,int twohun,int hun,int fif,int twen,int ten)
    {//updates notes in atm
        System.out.println("Here is your cash!!");
        capacity[6]-=twoth;
        capacity[5]-=fivehun;
        capacity[4]-=twohun;
        capacity[3]-=hun;
        capacity[2]-=fif;
        capacity[1]-=twen;
        capacity[0]-=ten;
        screen(obj);
    }
    public void depositSlot(UserInfo user)
    {//just a deposit slot
        System.out.println("Kindly enter amount to be deposited:");
        int amount=sc.nextInt();
        deposit(amount,user);
    }
    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
