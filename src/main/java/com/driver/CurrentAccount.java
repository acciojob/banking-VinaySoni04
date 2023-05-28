package com.driver;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isNumberValid(tradeLicenseId)){
            String rearrangeId=arrangeId(tradeLicenseId);
            if(rearrangeId==""){
                throw new Exception("Valid License cannot be generated");
            }
            else{
                this.tradeLicenseId=tradeLicenseId;
            }
        }
    }

    public boolean isNumberValid(String licenseId){
        for(int i=0;i<licenseId.length();i++){
            if(licenseId.charAt(i)==licenseId.charAt(i+1))
                return false;
        }
        return true;
    }

    private String arrangeId(String s) {
        int n=s.length();
        int[] count=new int[26];
        Arrays.fill(count,0);
        for(char ch:s.toCharArray()){
            count[(int)ch-(int)'A']++;
        }
        char chMax=getCountChar(count);
        int maxCount=count[(int)chMax-(int)'A'];
        if(maxCount>(n+1)/2)
            return "";
        String res="";
        for(int i=0;i<n;i++)
            res+=' ';
        int ind=0;
        while(maxCount>0){
            res=res.substring(0,ind)+chMax+res.substring(ind+1);
            ind=ind+2;
            maxCount--;
        }
        count[(int)chMax-(int)'A']=0;
        for (int i=0;i<26;i++){
            while(count[i]>0) {
                ind=(ind>=n)?1:ind;
                res=res.substring(0,ind)+(char)((int)'A'+i)+res.substring(ind+1);
                ind+=2;
                count[i]--;
            }
        }
        return res;
    }

    private char getCountChar(int[] count) {
        int max=0;
        char ch=0;
        for(int i=0;i<26;i++){
            if(count[i]>max){
                max=count[i];
                ch=(char)((int)'A'+i);
            }
        }
        return ch;
    }
}
