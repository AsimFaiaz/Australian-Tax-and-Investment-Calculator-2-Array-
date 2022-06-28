/*Author: Asim Faiaz
 *Student No: 3296512
 *Date: 29-04-2022
 *Description: This is the Client class. This class contains methods:
 *             Method 1: calTax()       which will return double type - Calculation yearly tax
 *             Method 2: calTaxWeekly() which will return double type - Calculation weekly tax
 *             Method 3: netSYearly()   which will return double type - Calculation Net income yearly
 *             Method 4: netSWeeklyly() which will return double type - Calculation Net income weekly
 *             Method 5: Medicare()     which will return double type - Calculation medicare levy
 *             Method 6: savingAccInv(double amount, double rate, int numberOfWeeks)  which takes 3 parameters and return a String type
 */

import java.util.*;
public class Client
{
    private String name;
    private double grossSalary;
    private boolean resident;
    private double medicare;
    private double weeklyExpenses;

    /*--------------------------

    This two are not in use because i broke them out into Weekly and Yearly for myself easier to use in the methods

    private double tax;
    private double netSalary;
    -------------------------*/

    //Create these variables for myself easier to use
    private double netSW;              //Income Weekly
    private double netSY;              //Income Yearly
    private double taxW;               //Tax weekly
    private double taxY;               //Tax yearly
    private double counter;            //Temporary variable to hold the amount

    //private Account savingAccount;
    private Account[] save = new Account[2];
    //private int accNum = 0;

    public Client()
    {
        name = "";
        resident = false;
        medicare = 0;
        weeklyExpenses = 0;
        netSW = 0;              
        netSY = 0;               
        taxW  = 0;               
        taxY  = 0;       
    }

    //Name
    public String getName()
    {
        return (name);
    }

    public void setName(String inputName)
    {
        name = inputName;
    }

    //Gross Salary
    public double getgrossSalary()
    {
        return (grossSalary);
    }

    public void setgrossSalary(double inputSalary)
    {
        grossSalary = inputSalary;
    }

    public String getresident()
    {
        if(resident)
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
    }

    public void setresident(Boolean checkResident)
    {
        resident = checkResident;
    }   

    //Living costs
    public double getweeklyExpenses()
    {
        return(weeklyExpenses);
    }

    public void setweeklyExpenses(double checkExp)
    {
        weeklyExpenses = checkExp;
    }
    
    /*----------------------------------------------------------------
    Methods
     *This part is for all the method that will be use in this prorgam
    -----------------------------------------------------------------*/

    /*
     * Description: This method will calculate yearly tax from user's gross income.
     * Formula: 0 to 18000 no tax so it will be 0; for the rest we need to minus the gross income(User input) with the smallest number of the condition, eg. in between 18001 to 45000 the 
     *          smallest number is 18001. So if we minus the gross income with that, we will get the rests and multiply that amount with whatever the condition is.
     * Check condition 1 : First the program will check if the user is australian resident or not
     * Check condition 2 : After checking the residency condition then the program will check the gross income and calculate accordingly
     */
    public double calTax()
    {

        if(resident)                            //Checking the boolean value from user input
        {
            //Income 0 to 18,200
            //Doesn't need to do this statement indeed because the initial value will be 0 anyway, still doing to show the result and make things clear
            if(grossSalary > 0 && grossSalary <= 18200)
            {
                taxY = 0;                                       //No tax yearly
            }

            //Income 18201 to 45000
            if(grossSalary >= 18201 && grossSalary <= 45000)
            {
                taxY = (grossSalary - 18200) * 0.19;            //(19/100 = 0.19)
            }

            //Income 45001 to 120000
            if(grossSalary >= 45001 && grossSalary <= 120000)
            {
                taxY = ((grossSalary - 45000) * 0.325) + 5092;  //(32.5/100 = 0.325)  
            }

            //Income 120001 to 180000
            if(grossSalary >= 120001 && grossSalary <= 180000)
            {
                taxY = ((grossSalary - 120000) * 0.37) + 29467;  //(37/100 = 0.37)
            }

            //Income 1800001 to up
            if(grossSalary >= 180001)
            {
                taxY = ((grossSalary - 180000) * 0.45) + 51667;  //(45/100 = 0.45)
            }
        }

        if(!resident)                               //Chceking boolean value from user input
        {
            //Non resident income 0 to 120000      
            if(grossSalary > 0 && grossSalary <= 120000)
            {
                taxY = grossSalary * 0.325;                        //(32.5/100 = 0.325)
            }

            //Non resident income 120001 to 180000   
            if(grossSalary >= 120001 && grossSalary <= 180000)
            {
                taxY = ((grossSalary - 120000) * 0.37) + 39000;    //(37/100 = 0.37)
            }

            //Non resident income 180001 to up  
            if(grossSalary >= 180001)
            {
                taxY = ((grossSalary - 180000) * 0.45) + 61200;    //(45/100 = 0.45)
            }
        }

        return taxY;

    }

    /*
     * Description: This method will calculate weekly tax amount
     * Formula: The amount of yearly tax divided by 52
     * Check condition 1: The method will fetch the yearly tax amount from the method "calTax()"
     * Check condition 2: After getting the amount the method will force to divided by 52.14 (1 year = 52.14 weeks sourced from google)
     */
    public double calTaxWeekly()
    {
        calTax();
        taxW = taxY / 52;
        return taxW;
    }

    /*
     * Description: This method will calculate Net income yearly (income after tax)
     * Formula: Gross income needs to minus with yearly tax and then minus the medicare levy if exist
     * Check condition 1: The method will fetch the yearly tax amount from the method "calTax()"
     * Check condition 2: After getting the amount the method will minus the gross income (from user input) with the yearly tax
     * Check condition 3: If there's and medicare levy exist that will deduct from the net income
     */
    public double netSYearly()
    {
        Medicare();
        calTax();
        netSY = (grossSalary - taxY) - medicare;
        return netSY;
    }

    /*
     * Description: This method will calculate Net income weekly
     * Formula: Yearly net income divided by 52
     * Check condition 1: The method will fetch the yearly net income amount from the method "netSYearly()"
     * Check condition 2: After getting the amount the method will force to divided by 52.14 (1 year = 52.14 weeks sourced from google)
     */
    public double netSWeekly()
    {
        netSYearly();
        netSW = netSY / 52;
        return netSW;
    }

    /*
     * Description: This method will calculate medicare levy 2%
     * Check condition 1: The method will check if the user is Australian resident
     * Check condition 2: The method will check if the user has gross income of more than 29032
     * Check condition 3: To get the medicare levy amount the program will multiply the gross income with 2%
     * Check condition 4: If all the conditions will be true the program will do the calculation, return 0 otherwise
     */
    public double Medicare()
    {
        if(resident && grossSalary > 29032)
        {
            medicare = grossSalary * 0.02;      //(2/100 = 0.02)
        }
        else
        {
            medicare = 0;
        }

        return medicare;
    }

    public double MediPW()
    {
        double mPerWeek;
        Medicare();
        mPerWeek = Medicare() / 52;
        return mPerWeek;
    }

    public double gsWeekly()
    {
        double grossPW;
        grossPW = grossSalary / 52;
        return grossPW;
    }

    /*
     * Checking account information for a client
     * This method will be used in multiple places
     */
    public int clientInfo()
    {
        int accounts = 0;

        for(int i = 0; i < 2; i++)
        {
            if(save[i] != null)
                accounts ++;
        }
        return accounts;
    }

    /*
     * Checking account space
     */
    public int checkAccountSpace()
    {
        //Checking account info for the client
        
        int x=2;
        
        //To check if any account available
        for(int i=0; i<2; i++)
        {
            if (save[i] == null)
            { 
                x=i;
                i = 2; 
            }
        }
         
        //If no account exists
        return x;
    }

    public void addAccount(int x,double totalRate, int totalWeeks, double totalAmount) 
    {
        //Creating a new object
        save[x] = new Account();
        //Setting up the addAccount
        save[x].Account(totalRate, totalWeeks, totalAmount);
    }
    
    /*
     * This method will be used in showAccount within the calculator interface
    */
    public String showAccount(int accountNumber)
    {
        boolean exist = false;
        String display;
        
        //Not printing anything. All will be returned and print through calculator interface
        if(save[accountNumber] == null)
        {
            return display = "No account exists";
        }

        else
        {
            display = "" + "\n";
            display += "Invest Amount: $" + save[accountNumber].getAmount() + "\n";
            display += "Total rate: " + save[accountNumber].getRate() * 100 + "%" + "\n";
            display += "Total weeks: " + save[accountNumber].getnumberOfWeeks() + "\n";
            display += "" + "\n";
            display +=      "Investment" + "\n";
            display += "Weeks         Balance" + "\n";
            display += save[accountNumber]. calcInvestment();
            return display;
        }
    }

    /*
    * This method will be used in deleteAccount within the calculator interface
    */
    public void deleteAccount(int accountNumber)
    {
        if (save[accountNumber] != null)
        {
            //Set the account to null if not
            save[accountNumber] = null;

            if(accountNumber == 0)
            {
                save[accountNumber] = save[accountNumber + 1];
                save[accountNumber + 1] = null;
            }
        }

        else
        {
            System.out.println("No account exist");
        }
    }

    //Checking if any account exists
    public boolean checkAccount(int account) 
    {
        boolean check = true; 
        if (save[account] == null) 
        {
            check = false; 
        }
        return check; 
    }

    //Geeting all the values to show in methods
    public double getRate(int account)
    {
        return save[account].getRate();
    }

    public int getnumberOfWeeks(int account)
    {
        return save[account].getnumberOfWeeks();
    }

    public double getAmount(int account)
    {
        return save[account].getAmount();
    }
    
    public String getInvestment(int account)
    {
        return save[account].calcInvestment();
    }
}

