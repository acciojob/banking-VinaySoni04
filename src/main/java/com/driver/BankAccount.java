package com.driver;

import lombok.Getter;

@Getter
public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        int rem=sum;
        String accNum="";
        if(digits*9<sum){
            throw new Exception("Account Number cannot be generated");
        }
        else {
            while (digits > 0 && rem > 0) {
                if (rem >= 9) {
                    rem = rem - 9;
                    accNum = accNum + "9";
                } else {
                    accNum = accNum + Integer.toString(rem);
                    rem = 0;
                }
                digits--;
            }
            while (digits > 0) {
                accNum = accNum + "0";
                digits--;
            }
            return accNum;
        }
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance-amount>=getMinBalance()){
            this.balance-=amount;
        }
        else{
            throw new Exception("Insufficient Balance");
        }
    }

}