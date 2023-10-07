package ATM_classes;
public interface atmMachine 
{
    //Interface implementing elements of a atm machine
    void screen(BankDataBase obj);
    void keyPad(int choose,UserInfo user);
    void cashDispenser(int twoth,int fivehun,int twohun,int hun,int fif,int twen,int ten);//notes of each denomination
    void depositSlot(UserInfo user);
}