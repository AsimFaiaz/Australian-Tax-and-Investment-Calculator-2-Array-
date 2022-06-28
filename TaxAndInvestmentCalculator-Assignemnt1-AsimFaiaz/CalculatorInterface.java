/* Assignemnt 2 - Tax and Investment Calculator
Aurthor: Asim Faiaz
Student no: 3296512
Description: The assignemnet is aimed to create a Calculator which will calculate Tax and investment for the user. However, Arrays will be introduced in this assignment
 */

import java.util.*;
import java.io.IOException;
import java.io.*;

//Importing packages to get a round value on the calculations
import java.text.NumberFormat; 
import java.util.Formatter;

//Importing packages for save in file option
//These are optional packages to handle the tasks
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CalculatorInterface
{
    static Scanner console = new Scanner (System.in);
    static CalculatorInterface calc = new CalculatorInterface();

    public Client[] client = new Client [5];  //1D array of client obj size 5
    private int noClient = 0;  //Number of clients

    public void run()
    {
        
        int choice;

        showOption();
        choice = console.nextInt();

        while(choice != 9)
        {
            switch(choice) //Switch used for the main menu
            {
                case 1: addClient();            //Starts process of creating client
                    break;
                case 2: deleteClient();         //Starts process of deleting client
                    break;
                case 3: displayClient();        //Starts process of displaying a client
                    break;
                case 4: displayAllClients();    //Starts process of displaying all client
                    break;
                case 5: addAccount();           //Starts process of creating account
                    break;
                case 6: displayAccount();       //Starts process of displaying account
                    break;
                case 7: deleteAccount();        //Starts process of deleting account
                    break;
                case 8: saveInFile();           //Starts process of save information to a .txt file
                    break;
                case 9: System.exit(0);         //Exits the program

                default: System.out.println("\n" + "ERROR! Invalid selection!" + "\n" + "Please choose from option 1 to 9 ONLY" + "\n");
            }

            showOption();
            choice = console.nextInt();
        }
    }

    public static void main(String[] args)
    {
        calc.run();
    }

    public static void showOption() //Shows options for input
    {
        System.out.println("Please select an option");
        System.out.println("1 - Add a client");
        System.out.println("2 - Delete a client");
        System.out.println("3 - Display a client");
        System.out.println("4 - Display all client/s");
        System.out.println("5 - Add account");
        System.out.println("6 - Display account/s");
        System.out.println("7 - Delete account");
        System.out.println("8 - Save in a file");
        System.out.println("9 - Exit");
        System.out.println();
    }

    //---------------------------------------------------
    /*
     * Method: This method will use for add a client
     * Check condition 1: The method will only allow to add 5 clients, If the user insert more that 5 clients the program will show an error message
     * Check condition 2: If the client name already exists, the prgram will show an error message
     */

    public void addClient()
    {
        String name = ""; 
        String checkName;

        while(name.equalsIgnoreCase(""))
        {    
            if(noClient < 5)
            {
                client[noClient] = new Client();

                //-------------------------
                //Asking for client Name

                System.out.println();
                System.out.print("Please enter your full name: ");
                checkName = console.next();

                while (checkName == null)
                {
                    //Checking if user insert name, will show error message otherwise
                    System.out.println();
                    System.out.print("ERROR: You must enter your name: ");
                    checkName = console.next();
                }

                //Checking if the client already exist in the system, if not exist, only then set the name of client
                for (int i = 0; i < client.length; i++)
                {
                    if (client[i].getName().equalsIgnoreCase(checkName))
                    {
                        System.out.println();
                        System.out.println("ERROR: Client already exists" + "\n");
                        System.out.println();
                        return;
                    }
                    else
                    {
                        name = checkName;
                        client[noClient].setName(name);
                        break;
                    }
                }
                //------------------------------

                //----------------------------
                //Asking for salary/Gross income
                System.out.print("Please enter your GROSS income: ");
                double grossSalary = console.nextDouble();

                while(grossSalary <= 0)
                {
                    //Checking if user insert any negative or null value
                    System.out.println();
                    System.out.print("ERROR: Wrong input! Please enter your GROSS income again(No Negative or 0): ");
                    grossSalary = console.nextDouble();
                }

                client[noClient].setgrossSalary(grossSalary);
                //------------------------------

                //------------------------------
                //Asking for residency status
                System.out.print("Are you an Australian resident? (Yes/No): " );
                String resident = console.next();

                while(!(resident.equalsIgnoreCase("Yes")  || resident.equalsIgnoreCase("No")))
                {
                    //Checking if user inputing other than Yes or No
                    System.out.println();
                    System.out.print("ERROR: Please type only Yes or No only: ");
                    resident = console.next();
                }

                boolean answer = false;     //Default

                if(resident.equalsIgnoreCase("Yes"))
                {
                    answer = true;
                }
                else if(resident.equalsIgnoreCase("No"))
                {
                    answer = false;
                }

                client[noClient].setresident(answer);
                //------------------------------

                //------------------------------
                //Asking for weekly expenses

                //------------------------------------------
                //This piece of code will trim the decimal values
                NumberFormat nf= NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                //------------------------------------------

                System.out.print("Please enter your weekly expenses: ");
                double weeklyExpenses = console.nextDouble();

                while(weeklyExpenses <= 0 || weeklyExpenses > client[noClient].netSWeekly())
                {
                    //Checking if user insert any negative or null value or more than the left amount
                    System.out.println();
                    System.out.print("ERROR: Your weekly Salary is $" + nf.format(client[noClient].netSWeekly()) + "\n" +"Please enter your weekly expenses again(No NEGETIVE OR 0): ");
                    weeklyExpenses = console.nextDouble();
                }

                client[noClient].setweeklyExpenses(weeklyExpenses);
                //-----------------------------

                System.out.println();
                System.out.println("You have added " + checkName.toUpperCase() + " into the system");
                System.out.println();
                System.out.println("-----------------------------------");

                //Incrementing the client counter
                noClient++;
            }
            else
            {
                //If there's more than 5 clients
                System.out.println();
                System.out.println("ERROR: MAXIMUM NUMBER OF CLIENTS REACHED." + "\n" + "The system only can take 5 clients");
                System.out.println();
                break;
            }
        }
    }
    //---------------------------------------------------

    //---------------------------------------------------
    /*
     * Method: This method will use for delete a client
     * Check condition 1: The method will check if the client exist or not in the system and then perform the delete action
     * Check condition 2: If there's no client (Empty) in the system the program will show ERROR message right away
     * Method used 1: Client[] listRearragane(Client[] client) - This method will rearrange the array list after deleting a client. For example: If the user delete the client number 4, Accoding
     *                to java, the list will be "1,2,3,5"; To avoid that we need to rearrange the list so the next one will come up and match the array sequence
     * Method used 2: clientExists(-) - This method will detarmine if the client exist or not within the system
     */

    public void deleteClient()
    {
        String checkName;

        //Checking if the counter is empty
        if (noClient == 0)
        {
            System.out.println();
            System.out.println("Sorry! NO client to delete");
            System.out.println();
            return;
        }

        //Asking for client name to delete
        System.out.println();
        System.out.print("Please enter a client name: ");
        checkName = console.next();

        while (checkName == null)
        {
            //Checking if user insert name, will show error message otherwise
            System.out.println();
            System.out.print("ERROR: You must enter client name: ");
            System.out.println();
            checkName = console.next();

        }

        //clientExist method called
        int clientExists = clientExists(checkName);

        //Checking if the client exist in the system
        if (clientExists == -1)
        {
            System.out.println();
            System.out.println("ERROR: " + checkName + " doesn't exist in the system");
            System.out.println();
        }
        else
        {
            for (int i = 0; i < client.length; i++)
            {
                if (client[i] != null && client[i].getName().equalsIgnoreCase(checkName))
                {
                    client[i] = null;
                    System.out.println("--");
                    System.out.println("Client name: " + checkName.toUpperCase() + " has been deleted");
                    System.out.println("--");
                    //Updating the arraylist
                    client = listRearragane(client);
                    //Droping the clientlist
                    noClient--;
                }
            }
        }
    }

    public Client[] listRearragane(Client[] client)
    {
        Client[] newClient = new Client [client.length];
        for(int i = 0, j = 0; i < client.length; i++)
        {
            if(client[i] != null)
            {
                newClient[j++] = client[i];
            }
        }
        return (newClient);

    }

    //--------------------------------
    /*
     *This method will use within the Delete Client method
     *Reason: This method will check if there's any client exist or not to delete within the system
     */

    public int clientExists(String nameCheck) 
    {
        boolean clientExists = false;

        for (int i = 0; i < noClient; i++)
        {
            if(nameCheck.equalsIgnoreCase(client[i].getName()))
            {
                clientExists = true;
                return i;
            }
            else
            {
                clientExists = false;
                System.out.println(i);
            }
        }
        return -1;
    }
    //----------------------------------------------

    //----------------------------------------------
    /*Method: This will use for displaying a client
     *Condition 1: This method will check first if any client exist in the system, show error otherwise
     *Condition 2: Then ask for the client name and check if the specific client exists in the system
     */
    public void displayClient() //This method shows a client
    {
        //------------------------------------------
        /*
         * This piece of code will trim the decimal values
         */
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        //------------------------------------------

        if (noClient == 0)
        {
            System.out.println();
            System.out.println("Sorry! NO client/clients to display");
            System.out.println();
            return;
        }

        String name = "";
        boolean nameExist = false;

        while(name.equalsIgnoreCase(""))
        {
            System.out.println();
            System.out.print("Please enter the name of a client: ");
            name = console.next();

            for (int i = 0; i < client.length; i++)
            {
                if (client[i] != null && client[i].getName().equalsIgnoreCase(name))
                {
                    nameExist = true;
                    System.out.println();
                    System.out.println(" ○ Name: " + client[i].getName().toUpperCase());          //Converting the name to uppercase
                    System.out.println();
                    System.out.println(" ○ Gross Salary:");
                    System.out.println("    •  Per Year: $" + client[i].getgrossSalary());
                    System.out.println("    •  Per Week: $" +nf.format(client[i].gsWeekly()));
                    System.out.println();
                    System.out.println(" ○ Residency Status: " + client[i].getresident());
                    System.out.println();
                    System.out.println(" ○ Tax Paid:");
                    System.out.println("    •  Per Week: $" + nf.format(client[i].calTaxWeekly()));
                    System.out.println("    •  Per Year: $" + nf.format(client[i].calTax()));
                    System.out.println();
                    System.out.println(" ○ Net Salary:");
                    System.out.println("    •  Per Week: $" + nf.format(client[i].netSWeekly()));
                    System.out.println("    •  Per Year: $" + nf.format(client[i].netSYearly()));
                    System.out.println();
                    System.out.println(" ○ Medicare levy: $" + client[i].Medicare());
                    System.out.println();
                    System.out.println(" ○ Weekly Expenses: $" + client[i].getweeklyExpenses());
                    System.out.println();
                }
            }

            if(!nameExist)
            {
                System.out.println();
                System.out.println("Client NOT exist within the system");
                System.out.println();
            }
        }
    }
    //---------------------------------------------

    //---------------------------------------------
    /*Method: This method will display all the clients exist in the system
     *Condition 1: If there's no client exist in the system, the program will show an error
     *Method used 1:  Client[] sortClientList(Client[] array) - This method is used for sorting the list of client in an alphabatic order by name (Extra work)
     */
    public void displayAllClients()
    {
        //------------------------------------------
        /*
         * This piece of code will trim the decimal values
         */
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        //------------------------------------------

        Client[] newClient = client;            // To avoid changes to main doctor array we are using temroray array for displaying sorted list
        newClient = sortClientList(client);     //Assigning

        System.out.println();
        System.out.println("The client/s name list has been sorted by name (Default)");
        System.out.println();

        if (noClient == 0)
        {
            System.out.println();
            System.out.println("Sorry! NO client/clients to display");
            System.out.println();
            return;
        }

        for (int i = 0; i < client.length; i++)
        {
            if (client[i] != null)
            {
                System.out.println("------------------------------------------");
                System.out.println("- Client " + (i + 1));
                System.out.println("--------");
                System.out.println(" ○ Name: " + client[i].getName().toUpperCase());
                System.out.println(" ○ Residency Status: " + client[i].getresident());
                System.out.println(" ○ Gross Salary (Per Week): $" +nf.format(client[i].gsWeekly()));
                System.out.println(" ○ Net Salary (Per Week): $" + nf.format(client[i].netSWeekly()));
                System.out.println(" ○ Tax (Per Week): $" + nf.format(client[i].calTaxWeekly()));
                System.out.println(" ○ Medicare levy: $" + nf.format(client[i].MediPW()));
                System.out.println(" ○ Weekly Expenses: $" + client[i].getweeklyExpenses());

                if(client[i].clientInfo() == 0)
                {
                    System.out.println(" ○ No Account");
                }
                else
                {
                    System.out.println(" ○ Number of Accounts: " + client[i].clientInfo());
                }
                System.out.println("------------------------------------------");
            }
        }
    }

    /*
     * Bonus/Extra work: This method will use for sorting the display client list in an alphabetic order 
     */
    public Client[] sortClientList(Client[] array)
    {
        Client[] newArray = new Client[noClient];
        boolean isSorted = false;
        while(!isSorted)
        {
            isSorted = true;
            for(int i = 0, j=0; i<array.length-1; i++)
            {
                if(array[i+1] != null && array[i].getName().compareToIgnoreCase(array[i+1].getName())>0 )
                {
                    //The value of current array is assigned to newArray
                    newArray[j] = array[i];
                    //The value of next array object is assigned to the current array object
                    array[i] = array[i+1];
                    //The value of newArray is assigned to the next array
                    array[i+1] = newArray[j];
                    isSorted = false;
                }
                j++;
            }
        }
        return(array);
    }
    //--------------------------------------------

    //--------------------------------------------
    /*Method: This method will use for adding a acount
     * Condition 1: The client will able to create 2 accounts ONLY
     * Condition 2: The method will get all the user input and set it to the client class method called addAcc(-,-,-,-)
     * Method 1: checkAccountSpace() - This method created in Client class to chcek the space of the client's account
     */
    public void addAccount()
    {
        //needs commenting
        //Chcek for managing the account balance

        String checkName;
        System.out.print("Please enter a client name: ");
        checkName = console.next();

        int x = 0;

        for (int i=0; i < noClient; i++)
        {
            if (checkName.equalsIgnoreCase(client[i].getName()))
            {
                //Check space method
                int j = client[i].checkAccountSpace();

                if (j<2) 
                {
                    /*
                     * This piece of code will trim the decimal values
                     */
                    NumberFormat nf= NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    //------------------------------------------

                    // asking the user for the amount, months and account type to setup the account in the returned 'j' value from the 
                    double checkDiff1 = client[i].netSWeekly() - client[i].getweeklyExpenses();
                    double totalAmount = 0; 

                    //Asking for amount
                    System.out.print("Please enter the investment amount: ");
                    totalAmount = console.nextDouble();

                    while(totalAmount <= 0 || totalAmount > checkDiff1)
                    {
                        System.out.println();
                        System.out.print("ERROR: You have " + nf.format(checkDiff1) + " available to invest" + "\n" + "Please enter again(No negative or 0): ");
                        totalAmount = console.nextDouble();
                    }

                    //Asking for interest rate
                    System.out.print("Please enter the interest rate: ");
                    double totalRate = console.nextDouble();

                    while (totalRate < 1 || totalRate > 20)
                    {   
                        System.out.println();
                        System.out.print("ERROR: Please enter in between 1% to 20% (No negative or 0): ");
                        totalRate = console.nextDouble();
                    }

                    //Asking for number of weeks
                    System.out.print("Please enter the Number of weeks: ");
                    int totalWeeks = console.nextInt();

                    while (totalWeeks <= 0)
                    {   
                        //Checking negative or 0
                        System.out.println();            
                        System.out.print("ERROR: Please enter valid number of weeks (Not negative or 0): ");
                        totalWeeks = console.nextInt();
                    }

                    //Sets up the account is setup usinfg the method 'addAccount' which exists within client class 
                    client[i].addAccount(j, totalRate, totalWeeks, totalAmount); 
                    System.out.println("--");
                    System.out.println("Account has been added for the client");
                    System.out.println("--");
                    return;
                }
                else
                {
                    //If the number of account is full for the user
                    System.out.println(); 
                    System.out.println("ERROR: Number of account is full!!");
                    System.out.println();
                }
                x++;
            }
        }
        if(x == 0)
        {
            System.out.println(); 
            System.out.println("ERROR: No client found!!");
        }
    }
    //-------------------------------------------

    //-------------------------------------------
    /*
     * Method: This method will display a account of client
     */
    public void displayAccount()
    {
        String checkName;
        System.out.print("Please enter a client name: ");
        checkName = console.next();

        int x = 0;

        for (int i=0; i < noClient; i++)
        {
            if(client[i].clientInfo() == 0)
            {
                System.out.println();
                System.out.println("No Account");
                System.out.println();
            }

            if (checkName.equalsIgnoreCase(client[i].getName()))
            {
                x++;
                System.out.println("Please enter the account number you want to display: ");
                int accountNumber = console.nextInt();
                while(!(accountNumber == 1 || accountNumber == 2))
                {
                    //Condition to handle user input
                    System.out.println(); 
                    System.out.print("ERROR: Please enter the account number you want to display!! For Account 1 Press '1' and  for Account 2 Press '2': ");
                    accountNumber = console.nextInt(); 
                }
                accountNumber--;
                //Calling the method from client class
                System.out.println(client[i].showAccount(accountNumber));
            }
        }

        if (x==0) 
        {
            System.out.println(); 
            System.out.println("ERROR: NO client exist!!");
            System.out.println();
        }
    }
    //------------------------------------------------
    
    //------------------------------------------------
    /*
     * This method will use for deleting a client
    */
    public void deleteAccount()
    {
        String checkName;
        System.out.print("Please enter a client name: ");
        checkName = console.next();

        int x = 0;

        for (int i=0; i < noClient; i++)
        {
            if (checkName.equalsIgnoreCase(client[i].getName()))
            {
                System.out.print("Please enter the account number you want to delete: ");
                int accountNumber = console.nextInt();
                
                while(!(accountNumber == 1 || accountNumber == 2))
                {
                    System.out.println(); 
                    System.out.print("ERROR: Please enter the account number you want to delete!! For Account 1 Press '1' and  for Account 2 Press '2': ");
                    accountNumber = console.nextInt(); 
                }
                accountNumber--;
                //Calling method from client class
                client[i].deleteAccount(accountNumber);
                x++;
                System.out.println("--");
                System.out.println("Account has deleted");
                System.out.println("--");
            }
        }

        if (x==0) 
        {
            System.out.println(); 
            System.out.println("ERROR: NO client found!!");
            System.out.println();
        }
    }
    //---------------------------------------
    
    //---------------------------------------
    /*
     * This method will use for save the information in a .txt format file
    */

    public void saveInFile()
    {
        String fileName;
        System.out.println();
        System.out.print("Please enter file name: ");
        fileName = console.next() + ".txt";
        System.out.println();
        PrintWriter outFile;

        //------------------------------------------
        //This piece of code will trim the decimal values
        NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        //------------------------------------------

        try
        {
            outFile = new PrintWriter(fileName);

            if (noClient == 0)
            {
                outFile.println();
                System.out.println("Sorry! NO client/clients to display!!");
                System.out.println();
                return;
            }

            for(int i = 0; i<noClient; i++)
            {
                outFile.println();
                outFile.println("➤ Client/s Informations:");
                outFile.println();
                outFile.println ("Client Number: " + (i+1));
                outFile.println();
                outFile.println(" ○ Name: " + client[i].getName());
                outFile.println();
                outFile.println(" ○ Gross Salary:");
                outFile.println("    •  Per Year: $" + client[i].getgrossSalary());
                outFile.println("    •  Per Week: $" +nf.format(client[i].gsWeekly()));
                outFile.println();
                outFile.println(" ○ Residency Status: " + client[i].getresident());
                outFile.println();
                outFile.println(" ○ Tax Paid:");
                outFile.println("    •  Per Week: $" + nf.format(client[i].calTaxWeekly()));
                outFile.println("    •  Per Year: $" + nf.format(client[i].calTax()));
                outFile.println();
                outFile.println(" ○ Net Salary:");
                outFile.println("    •  Per Week: $" + nf.format(client[i].netSWeekly()));
                outFile.println("    •  Per Year: $" + nf.format(client[i].netSYearly()));
                outFile.println();
                outFile.println(" ○ Medicare Levy: $" + client[i].Medicare());
                outFile.println();
                outFile.println(" ○ Weekly Expenses: $" + client[i].getweeklyExpenses());
                outFile.println();

                for (int j = 0; j<2; j++)
                {
                    if(client[i].checkAccount(j))
                    {
                        outFile.println();
                        outFile.println("➤ Account/s Informations:");
                        outFile.println();
                        outFile.println("Account: " + (j+1));
                        outFile.println();
                        outFile.println(" ○ Investment Amount: " + client[i].getAmount(j));
                        outFile.println(" ○ Investment Rate: " + client[i].getRate(j) * 100);
                        outFile.println(" ○ Investment Duration(Number of Weeks:): " + client[i].getnumberOfWeeks(j));
                        outFile.println();
                        outFile.println("     Investment");
                        outFile.println("Weeks         Balance");
                        outFile.println();
                        outFile.println(client[i].getInvestment(j));
                        outFile.println();
                    }
                    else
                    {
                        outFile.println("Sorry! No account " + (j+1) + " to display!!");
                    }
                }
            }
            outFile.close();
            System.out.println();
            System.out.println("Files successfully saved to " + fileName);
            System.out.println();
        }

        catch(Exception ex)
        {
            System.out.println("Error creating file " + fileName);
            System.exit(0);
        }
    }
    //---------------------------------------------------
}