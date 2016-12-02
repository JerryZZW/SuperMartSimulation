// DO NOT ADD NEW METHODS OR NEW DATA FIELDS!

package PJ3;

class Customer
{
    private int customerID;
    private int serviceTime;
    private int arrivalTime;

    // default constructor
    Customer()
    {
	// add statements
        customerID = 1;
        serviceTime = 0;
        arrivalTime = 0;
    }

    // constructor to set customerID, serviceTime and arrivalTime
    Customer(int customerid, int servicetime, int arrivaltime)
    {
	// add statements
  	arrivalTime = arrivaltime;
        customerID = customerid;
        serviceTime = servicetime;
    }
    //get service time
    int getServiceTime() 
    {
	// add statements
  	return serviceTime;
    }
    //get arrival time
    int getArrivalTime() 
    {
	// add statements
  	return arrivalTime;
    }
    //get customer ID
    int getCustomerID() 
    {
        return customerID;
    }
    //convert customer object to String
    public String toString()
    {
    	return "customerID="+customerID+":serviceTime="+
               serviceTime+":arrivalTime="+arrivalTime;

    }

    public static void main(String[] args) {
        // quick check!
	Customer mycustomer = new Customer(1,35,5);
	System.out.println("Customer Info --> "+mycustomer);

    }
}
