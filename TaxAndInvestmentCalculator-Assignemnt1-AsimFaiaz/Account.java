/* Aurthor: Asim Faiaz
   Student no: 3296512
   Description: This is the Account class which contains only one method.
                Method 1: calcInvestment() which returns a string type and calculate investment
*/

import java.util.*;

//Importing packages to get a round value on the calculations
import java.text.NumberFormat; 
import java.util.Formatter;


public class Account
{
    private double rate;
    private int numberOfWeeks;
    private double amount;

    // constructor
    public Account()
    {
        rate = 0;
        numberOfWeeks = 0;
        amount = 0;
    }
    
    
    public void Account(double totalRate, int totalWeeks, double totalAmount)
    {
        rate = totalRate/100;
        numberOfWeeks = totalWeeks;
        amount = totalAmount;
    }
    
    //Investment rate
    public double getRate()
    {
        return(rate);
    }
    
    public void setRate(double inputRate)
    {
        rate = inputRate/100;
    }
    
    //Number of weeks
    public int getnumberOfWeeks()
    {
        return numberOfWeeks; 
    }
    
    public void setnumberOfWeeks(int inputNOW)
    {
        numberOfWeeks = inputNOW;
    }
    
    //Investment amount
    public double getAmount()
    {
        return amount;
    }
    
    public void setAmount(double checkAmount)
    {
        amount = checkAmount;
    }
    
     /*
     * Description: This method is aimed to calculate the investment using the user input
     */
    public String calcInvestment()
    {
        //-----------------------------------------
        /*This two lines will force the program to keep 2 dacimal places. Since the method is returing String, 
         the same code in the calculatorInterface doesn't work so I need to do here again just for this perticular method
        */
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        //------------------------------------------
        
        String printOut = "";        //String to get the result and use the return value inside calculatorInteraface
        double total = 0;            //Total Calculation
        int weeksInterval = 4;       //Every 4 weeks as per assignment specs
        int i = 0;                    
        do{
            i+= 4;  

        if(i > numberOfWeeks)
            {
                /*
                 * This IF statement is here to check the weeks left.
                 * For example: If the user asked 18 weeks, after week 16 there will be 2 week left and this statement will change the value of i,
                 * make the rate 0, since no rate will be added if week less than 4
                 */
                
                weeksInterval = numberOfWeeks % 4;
                rate = 0.00;
                i = numberOfWeeks;
            }
             total = (total + amount * weeksInterval ) * (1 + rate/13);
             
              printOut += i + "            " + "$" + nf.format(total) + "\r\n";
              
              
        }while(i < numberOfWeeks); 

        return printOut;
        
    }
}
