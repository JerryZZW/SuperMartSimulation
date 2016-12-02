package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data fields in this class 
// You may modify any functions or data members here
// You must use Customer, Cashier and CheckoutArea classes
// to implement SuperMart simulator

class SuperMart {

  // input parameters
  private int numCashiers, customerQLimit;
  private int chancesOfArrival, maxServiceTime;
  private int simulationTime, dataSource;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;

  // internal data
  private int counter;	             // customer ID counter
  private CheckoutArea checkoutarea; // checkout area object
  private Scanner dataFile;	     // get customer data from file
  private Random dataRandom;	     // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int serviceTime;

  // initialize data fields
  private SuperMart()
  {
	// add statements
      numCashiers = 0;
      customerQLimit = 0;
      chancesOfArrival = 0;
      maxServiceTime = 0;
      simulationTime = 0;
      dataSource = 0;
      
      numGoaway = 0;
      numServed = 0;
      totalWaitingTime = 0;
      
      counter = 0;
      checkoutarea = null;
      dataFile = null;
      dataRandom = null;
      
      anyNewArrival = false;
      serviceTime = 0;
  }
  
  //check and read the input of simulation time
  private void checkAndRead_SimulationTime() {
      while (true) {//until users enter correct input, break the loop
          System.out.print("Enter simulation time (positive integer)       : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
              simulationTime = userInput.nextInt();
              if (simulationTime > 0) {//check if the integer is positive
                  if (simulationTime <= 10000) {//check if the input exceed the limit
                      break;//break the loop
                  }
                  else {
                      System.out.println("The maximum simulation time is 10000!");
                  }
              }
              else {
                  System.out.println("Please enter an postive integer!");
              }
          }
          else {
              System.out.println("Please enter an postive integer!");
          }
      }
  }
  
  //check and read the input of the number of cashiers
  private void checkAndRead_NumOfCashiers() {
      while (true) {//until users enter correct input, break the loop
          System.out.print("Enter the number of cashiers                   : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
               numCashiers = userInput.nextInt();
              if (numCashiers > 0) {//check if the integer is positive
                  if (numCashiers <= 10) {//check if the input exceed the limit
                      break;//break the loop
                  }
                  else {
                      System.out.println("The maximum number of cashiers is 10!");
                  }
              }
              else {
                  System.out.println("Please enter an postive integer!");
              }
          }
          else {
              System.out.println("Please enter an postive integer!");
          }
      }
  }
  
  //check and read the input of the chances of new customers
  private void checkAndRead_ChancesOfNewCustomer() {
      while (true) {//until users enter correct input, break the loop
          System.out.print("Enter chances (0% < & <= 100%) of new customer : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
              chancesOfArrival = userInput.nextInt();
              if (chancesOfArrival > 0) {//check if the integer is positive
                  if (chancesOfArrival <= 100) {//check if the input exceed the limit
                      break;//break the loop
                  }
                  else {
                      System.out.println("The probability of a new customer is 1% - 100%!");
                  }
              }
              else {
                  System.out.println("Please enter an postive integer!");
              }
          }
          else {
              System.out.println("Please enter an postive integer!");
          }
      }
  }
  
  //check and read the input of service time
  private void checkAndRead_ServiceTime() {
      while (true) {//until users enter correct input, break the loop
          System.out.print("Enter maximum service time of customers        : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
               maxServiceTime = userInput.nextInt();
              if (maxServiceTime > 0) {//check if the integer is positive
                  if (maxServiceTime <= 500) {//check if the input exceed the limit
                      break;//break the loop
                  }
                  else {
                      System.out.println("The maximum service time of customers is 500!");
                  }
              }
              else {
                  System.out.println("Please enter an postive integer!");
              }
          }
          else {
              System.out.println("Please enter an postive integer!");
          }
      }
  }
  
  //check and read the input of customer queue limit
  private void checkAndRead_CustomerQLimit() {
      while (true) {//until users enter correct input, break the loop
          System.out.print("Enter customer queue limit                     : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
              customerQLimit = userInput.nextInt();
              if (customerQLimit > 0) {//check if the integer is positive
                  if (customerQLimit <= 50) {//check if the input exceed the limit
                      break;//break the loop
                  }
                  else {
                      System.out.println("The maximum customer queue limit is 50!");
                  }
              }
              else {
                  System.out.println("Please enter an postive integer!");
              }
          }
          else {
              System.out.println("Please enter an postive integer!");
          }
      }
  }
  
  //check and read the input from file or random number
  private void checkAndRead_DataFromRandomOrFile() {
      while (true) {//check and read the input of customer queue limit
          System.out.print("Enter 0/1 to get data from random/file         : ");
          Scanner userInput = new Scanner(System.in);//read the input
          if (userInput.hasNextInt()) {//check if users enter an integer
              dataSource = userInput.nextInt();
              if (dataSource == 0) {//if users enter 0, read the input from random number
                  dataRandom = new Random();
                  break;//break the loop
              }
              else if (dataSource == 1) {//if users enter 1, read the input from file
                  System.out.print("Enter filename                                 : ");
                  String fileName = userInput.next();
                  try {//open the file
                      File f = new File(fileName);
                      String path = f.getAbsolutePath();
                      System.out.println();
                      System.out.println("File path:" + path);
                      dataFile = new Scanner(f);//read the file
                  } catch (FileNotFoundException e) {//if the file is not found, remind users
                      System.out.println("Error opening the file: " + fileName);
                      System.exit(0);
                  }
                  break;//break the loop
              }
              else {
                  System.out.println("Please enter 1 or 0!");
              }
          }
          else {
              System.out.println("Please enter 1 or 0!");
          }
      }
  }
  
  //display result at 2 decimal places
  private double displayAt_2_decimal(double num) {
        long num1 = Math.round(num*100);
        double newNum = num1/100.0;
        return newNum;
  }
  
  //use the above method to check and read the user input
  private void setupParameters()
  {
        // read input parameters from user
        // setup dataFile or dataRandom
        // add statements
        checkAndRead_SimulationTime();
        checkAndRead_NumOfCashiers();
        checkAndRead_ChancesOfNewCustomer();
        checkAndRead_ServiceTime();
        checkAndRead_CustomerQLimit();
        checkAndRead_DataFromRandomOrFile();
  }

  // use by step 1 in doSimulation()
  private void getCustomerData()
  {
	// get next customer data : from file or random number generator
	// set anyNewArrival and serviceTime
        // add statements
        if (dataSource == 0) {//if user enter 0, read the input from random number
            anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
            serviceTime = dataRandom.nextInt(maxServiceTime)+1;
        }
        else {//if user enter 1, read the input from file
            anyNewArrival = (((dataFile.nextInt()%100)+1)<= chancesOfArrival);
            serviceTime = (dataFile.nextInt()%maxServiceTime)+1;
        }
  }
  
  private void doSimulation()
  {
        // add statements
        System.out.println();
        System.out.println("        ***  Start Simulation  ***");
        System.out.println();
        System.out.println("Customer #1 to #" + numCashiers + " are ready...");
        System.out.println();
	// Initialize CheckoutArea
        checkoutarea = new CheckoutArea(numCashiers, customerQLimit);
	// Time driver simulation loop
  	for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
                System.out.println("---------------------------------------------");
                System.out.println("Time : " + currentTime);
    		// Step 1: any new customer enters the checkout area?
    		getCustomerData();

    		if (anyNewArrival) {

      		    // Step 1.1: setup customer data
                    counter++;
                    Customer newCustomer = new Customer(counter, serviceTime, currentTime);
                    System.out.println("        customer #" + newCustomer.getCustomerID() + " arrives with checkout time " + newCustomer.getServiceTime() + " units");
      		    // Step 1.2: check customer waiting queue too long?
                    if (checkoutarea.isCustomerQTooLong()) {
                        System.out.println("        The queue is too long. " + "customer #" + newCustomer.getCustomerID() + " goes away.");
                        numGoaway++;
                    }
                    else {//if the customer queue is not too long, add the customer to customer queue
                        checkoutarea.insertCustomerQ(newCustomer);
                        System.out.println("        customer #" + newCustomer.getCustomerID() + " wait in the customer queue");
                    }
    		} else {
      		    System.out.println("\tNo new customer!");
    		}

    		// Step 2: free busy cashiers, add to free cashierQ
                while (true) {//until there is no busy cashier who can be set free, break the loop
                    if (checkoutarea.sizeBusyCashierQ() == 0) {//if there is no busy cashier
                        break;//break the loop
                    }
                    else {//if there are busy cashiers
                        if (checkoutarea.peekBusyCashierQ().getEndBusyTime() == currentTime) {//if the end busy clock time = current time, free busy cashiers
                            System.out.println("        customer #" + checkoutarea.peekBusyCashierQ().busyToFree().getCustomerID() + " is done");
                            System.out.println("        cashier  #" + checkoutarea.peekBusyCashierQ().getCashierID() + " is free");
                            checkoutarea.insertFreeCashierQ(checkoutarea.removeBusyCashierQ());//add to free cashier queue
                        }
                        else {
                            break;//break the loop
                        }
                    }
                }
    		// Step 3: get free cashiers to serve waiting customers
                while (true) {//until there is no free cashier or new customer, break the loop
                    if (checkoutarea.sizeFreeCashierQ() == 0) {
                        break;//break the loop
                    }
                    else {//if there is no new customer, break the loop
                        if (checkoutarea.sizeCustomerQ() == 0) {
                            break;
                        }
                        else {//get free cashiers to serve watting customers
                            Customer waitingCustomer = checkoutarea.removeCustomerQ();
                            Cashier serveCashier = checkoutarea.removeFreeCashierQ();
                            serveCashier.freeToBusy(waitingCustomer, currentTime);
                            checkoutarea.insertBusyCashierQ(serveCashier);
                            totalWaitingTime += (currentTime - waitingCustomer.getArrivalTime());
                            System.out.println("        customer #" + waitingCustomer.getCustomerID() + " gets a cashier");
                            System.out.println("        cashier  #" + serveCashier.getCashierID() + " starts serving customer #" + waitingCustomer.getCustomerID() + " for " + waitingCustomer.getServiceTime() + " units");
                            numServed++;
                        }
                    }
                }
  	} // end simulation loop

  }

  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in project statement
        // you need to display all free and busy cashiers
        System.out.println("============================================");
        System.out.println();
        System.out.println("End of simulation report");
        System.out.println();
        System.out.println("        # total arrival customers  : " + counter);
        System.out.println("        # customers gone-away      : " + numGoaway);
        System.out.println("        # customers served         : " + numServed);
        System.out.println();
        //print current cashiers info
        System.out.println("        *** Current Cashiers Info. ***");
        System.out.println();
        checkoutarea.printStatistics();
        System.out.println();
        System.out.println("        Total waiting time         : " + totalWaitingTime);
        System.out.println("        Average waiting time       : " + displayAt_2_decimal(((double)totalWaitingTime / numServed)));
        System.out.println();
        //print busy cashiers info
        System.out.println("        Busy Cashiers Info. :");
        System.out.println();
        if (checkoutarea.emptyBusyCashierQ()) {//check if there is any busy cashier
            System.out.println("                There is no busy cashiers.");
            System.out.println();
            }
        else {
            while (checkoutarea.sizeBusyCashierQ() > 0) {//until all the busy cashiers are printed, break the loop
                checkoutarea.peekBusyCashierQ().setEndBusyTime(simulationTime);
                checkoutarea.removeBusyCashierQ().printStatistics();
                System.out.println();
            }
        }
        //print free cashiers info
        System.out.println("        Free Cashiers Info. :");
        System.out.println();
        if (checkoutarea.emptyFreeCashierQ()) {//check if there is any free cashier
            System.out.println("                There is no free cashiers.");
        }
        else {
            while (checkoutarea.sizeFreeCashierQ() > 0) {//until all the free cashiers are printed, break the loop
                Cashier freeCashier = checkoutarea.removeFreeCashierQ();
                freeCashier.setEndFreeTime(simulationTime);
                freeCashier.printStatistics();
                System.out.println();
            }
        } 
  }

  // *** main method to run simulation ****

  public static void main(String[] args) {
   	SuperMart runSuperMart=new SuperMart();
   	runSuperMart.setupParameters();
   	runSuperMart.doSimulation();
   	runSuperMart.printStatistics();
  }

}
