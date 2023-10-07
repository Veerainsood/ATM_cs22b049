import ATM_classes.*;
/*Each user can have only one account of
each type(savings,IB etc.,.) at the bank. 

ATM users should be able to view their account
balance, withdraw cash(i.e., take money out of an account ) and deposit funds(i.e.,place money
into an account) etc.


The user interface of the automated teller machine contains the following hardware
components:
● a screen that displays messages to the user
● A keypad that receives numeric input from the user
● A cash dispenser that dispenses cash to the user and
● A deposit slot that receives deposit envelopes from the user. 

General Constraints:
Implementation of all OOPs concepts in each and every class used in this case study
- Abstraction
- Encapsulation
- Polymorphism
- Interfaces

Constraints:
1. ATM PIN(VARIABLE LENGTH)
2. CARDLESS ATM
3. DEPOSIT: should also accept coins of all denominations.
4. Should be able to update their basic profile at ATM’s and restrict changes in important
info.
*/
//Main
class ATM_Machine
{
    public static void main(String[] args) {
        BankAtm myAtm=new BankAtm();
        BankDataBase obj=new BankDataBase();
        myAtm.screen(obj);
    }
}