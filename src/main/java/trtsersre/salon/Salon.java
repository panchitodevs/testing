/*
TESSSTS
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package trtsersre.salon;


import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.regex.*;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

public class Salon {
	static List<String> selectedServices = new ArrayList<>();
	static List<String> servicePrices = new ArrayList <>();
	  
	public static void main(String[]args) throws Exception {
		DisplayPage();
		signUpPage();		
										  				   }
	
		static Scanner scan = new Scanner(System.in);								  				   
		static int response;
	public static void DisplayPage() throws Exception{

		Calendar cal = Calendar.getInstance();

		String SalonServices[] = { "Nail Treatment and Services", "Hair Services", "Facial Treatments", "Relaxation Massage" }; // end of salon services

		String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }; // end of months

		System.out.println(" |=================================================================================================================|");
		System.out.println(" |                                                                                               	           |");
		System.out.println(" |          				Welcome to Salon Appointment System                               	   |");
		System.out.println(" |                                                                                               	           |");
		System.out.println(" |=================================================================================================================|");
		System.out.print("  Date: " + months[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR));
		System.out.println("\t\t\t\t\t\t\t\t\t\tTime: " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + cal.get(Calendar.AM_PM));
		System.out.println(" ...................................................................................................................");
		System.out.println();	
					
} //end of display page()

	public static void signUpPage() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Don't have an account? sign up now and check out our services.");
		System.out.println("[1] Login");
		System.out.println("[2] Sign up");
		System.out.println("[3] View my Appointment");
		System.out.println("[4] Exit");

		while(true) {
			try{
				int response = scan.nextInt();
				scan.nextLine();

			switch (response) {
			case 1:
				loginDisplay();
				login();
				Services();
				break;
			case 2:
				register();
				signUpPage();
				loginDisplay();
				login();
				break;
			case 3:
				System.out.println("Please enter your username to check your receipt: ");
				String uName = scan.nextLine();
				ViewAppointment(uName);
				exit();
				break;
			case 4:
				System.out.println();
				System.out.println();
				System.out.println("|=======================================================================================================|");
				System.out.println("|                 Thank you for choosing us. We are looking forward to hear your feedback               |");
				System.out.println("|=======================================================================================================|");
				System.out.println();
				System.out.println();
				System.exit(3);
			default:
				throw new InputMismatchException("Invalid choice entered");
 						} //end of switch statement
					} //end of try block
			catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid choice.");
				scan.nextLine();
    			} // end of catch block
			} //end of while
		} //end of sign up page

	public static void loginDisplay() {
		System.out.println("|=================================================================================================================|");
		System.out.println("|                                                                                                                 |");
		System.out.println("|                                        Log in below to get started                                              |");
		System.out.println("|                                                                                                                 |");
		System.out.println("|=================================================================================================================|");
		System.out.println();
		} //end of login display 
//Store the username and password=============================================================================================================
 
	public static String userName;
	public static String passWord; 
//============================================================================================================================================
	public static void login() throws IOException, Exception {		  
        while (true) {
            try {           	
                System.out.println("Enter your username:");
                userName = scan.nextLine().trim();
              
                System.out.println("Enter your password:");
                passWord = scan.nextLine().trim();

            FileReader fileReader = new FileReader("Records.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean user = false;

		while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 7) {
            	String usnFile = parts[5].trim().substring("USERNAME: ".length());
            	String pwFile = parts[6].trim().substring("PASSWORD: ".length());

            if (usnFile.equals(userName) && pwFile.equals(passWord)) {
                user = true;
                break;
                	        } // end of inner if
                    	} // end of outer if
                	} // end of while

                bufferedReader.close();
        while(true) {
            try {
            if (!user) {
              	invalidAcc(false);

                int choice = scan.nextInt();
                scan.nextLine();

                switch(choice) {
                case 1:
                  	login();
                    break;                    	
                case 2:
                    register();
                   	break;
                case 3:
                   	System.out.println("Please enter your username to check your receipt: ");
					String uName = scan.nextLine();
					ViewAppointment(uName);
					exit();
					break;
                case 4:
                   	exit();
                   	break;
                default:
                   	System.out.println("Invalid choice entered");
                    } //end of swtich statement

                } else {
                    System.out.println("Logged in successfully!");
                    Services();
                    break;
                	} //end of if else statement
                } //end of try block
            catch (InputMismatchException e) {
            	System.out.println("Invalid input, try again! ");
            	scan.nextLine();
             			} //end of catch block
        			} //end of inner try block         	
   			    } //end of outer try block
            catch (IOException e) {
                System.out.println("An error occurred during login:" + e.getMessage());
            } // end of catch block
            catch(Exception e) {
            	System.out.println("An error occurred on login: " + e.getMessage());
        } //end of catch block
     } //end of outer while loop 
} // end of login

	public static void invalidAcc(boolean loginUser) {
		if(!loginUser) {
			System.out.println("Password or Username is incorrect.");
            System.out.println();
			}
            System.out.println("Do you want to continue logging in, Create an account, or View your Appointment?");
            System.out.println("[1] Continue to Login");
            System.out.println("[2] Create an Account");
            System.out.println("[3] View Appointments");
            System.out.println("[4] Exit the System");
		} //end of invalid acc

//Store the details============================================================================================================
	public static String fullName;
	public static String add;
	public static int age;
	public static String contactNo;
	public static String email;
//=============================================================================================================================
	public static void register() {

		while(true) {
	        try {
	            FileWriter fileWriter = new FileWriter("Records.txt", true); 
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            PrintWriter printWriter = new PrintWriter(bufferedWriter);

	    while(true){
	        System.out.println("Enter your Full Name: ");
	        fullName = scan.nextLine().trim();
	        if (fullName.matches("[a-zA-Z]+(?:\\s[a-zA-Z.]+)*(?:\\s\\d+(?:st|nd|rd|th))?")) {
	        	break;
	        }
	        else {
           		System.out.println("Please enter a valid name. (FIRST NAME, M.I, LAST NAME)");
	            } //end of if else statements
	        } //end of while loop

	    while(true) {
	        System.out.println("Enter your address:");
	        add = scan.nextLine().trim();
	        if (add.matches("[a-zA-Z0-9\\s.,-]+")) {
	          	break;
	            }
	        else {
	           	System.out.println("Please enter a valid address:");
	            } //end of if else statements
	    } //end of while loop

	    while(true) {
	    	try {
	            System.out.println("Enter your Age:");
	            age = scan.nextInt();

	        if (age > 0) {
	            break;
	             }
	             else {
	             	System.out.println("Please enter a valid age:");
	             } //end of if else statements
	         } //end of try block
	        catch(InputMismatchException e) {
	         	System.out.println("Invalid age input!");
	         	scan.nextLine();
	        } //end of catch block
	    } //end of while
	   			scan.nextLine();

	    while(true) {
	        System.out.println("Enter your contact number\nNOTE: It starts with (09) ");
	        contactNo = scan.nextLine().trim();
	            if (contactNo.matches("\\+?\\d{11}")){
	            	break;
	            	}
	            else {
	            	System.out.println("Please enter a valid Phone number!");
	            	} //end of if else statements
	        	} //end of while

       	while(true) {
            System.out.println("Enter your email address");
            email = scan.nextLine().trim();
	            if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {          	
	             	break;
	             }
	             else {
	             	System.out.println("Please enter a valid email address");
	             } //end of if else statements
	        } //end of while

	    while(true) {
	        System.out.println("Register your username \nNOTE: You may only use alphanumeric characters!");
	        userName = scan.nextLine().trim();
	            if (userName.matches("[a-zA-Z_]+") && !userName.isEmpty()) {
	                break; 
	            }
	            else {
	            	System.out.println("Please enter a valid username!");
	            } //end of if else statements
	        } //end of while

	    while(true) {
	            System.out.println("Register your password:");
	            passWord = scan.nextLine().trim();
	            if (passWord.matches("[a-zA-Z0-9_]+") && !passWord.isEmpty()){
	            	break; 
	            }
	            else {
	            	System.out.println("Please enter a valid password");
	            	} //end of if else statements

	        	} //end of while loop	

	            printWriter.println("FULLNAME: " + fullName + ", ADDRESS: " + add + 
					", AGE: " + age + ", CONTACT NUMBER: " + contactNo + ", EMAIL ADDRESS: " 
					+ email + ", USERNAME: " + userName + ", PASSWORD: " + passWord + 
					",\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");

	            printWriter.close();
	            System.out.println("Registration is successful");
	            break;

	        	} // end of try block
	        catch (InputMismatchException e){
	        	System.out.println("Invalid input, please enter again: " + e.getMessage());
	        }
	        catch (IOException e) {
	            System.out.println("An error occurred during registration: " + e.getMessage());
		} // end of catch block
	} //end of while
} // end of register

	public static void ViewAppointment(String uName) throws IOException {	
		StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            boolean found = false;
            
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("USERNAME: " + uName)) {
                found = true;
              				}
            if (found) {
                data.append(line).append("\n");
            			}
            if (line.startsWith("===") && found) {
                break;
           			}
            } //end of while 
            if (!found) {
                System.out.println("No appointments found for username: " + uName);
            	} 
            else {
                System.out.println(data);
            } //end of if else statements
        } //end of try block
        catch (IOException e) {
            System.out.println("An error occurred on reading the file: " + e.getMessage());
    } //end of catch block      
} //end of view appointment

	public static void Services() throws Exception {
		System.out.println(" |================================================================================================================|");
		System.out.println(" |                                          CHOOSE YOUR SERVICES                                                  |");
		System.out.println(" |================================================================================================================|");
		System.out.println(" ..................................................................................................................");
		System.out.println();
		System.out.println("[0] Nail Treatment and Services ");
		System.out.println("[1] Hair Services");			
		System.out.println("[2] Facial Treatment");
		System.out.println("[3] Relaxation Massage");
		System.out.println("[4] Exit");
		System.out.println();
		System.out.println(" ..................................................................................................................");
		System.out.println();

		while (true) {

			try {
				int response = scan.nextInt();

				switch (response) {
					case 0:
						NailTreatmentAndServices.start();
						break;
					case 1:
						HairServices.start();
						break;
					case 2:
						FacialTreatments.start();
						break;
					case 3:
						RelaxationMassage.start();
						break;
					case 4:
						System.out.println();
						System.out.println();
						System.out.println(
								"|=======================================================================================================|");
						System.out.println(
								"|                 Thank you for choosing us. We are looking forward to hear your feedback               |");
						System.out.println(
								"|=======================================================================================================|");
						System.out.println();

						System.out.println();
						System.exit(4);
					default:

						throw new InputMismatchException("Invalid choice entered: " + response);

				} // end of case statements
			
			} // end of try block
			catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid choice.");
				scan.nextLine();
		} // end of catch block
	} // end of while loop
} //end of services 

class NailTreatmentAndServices {
		static String ntService [] = {"[0] Manicure", "[1] Pedicure", "[2] Gel Polish", "[3] Softgel", "[4] Nail Art Extension", "[5] Hand Spa", "[6] FootSpa", "[7] Go back to menu ", "[8] Exit"};			
	public static void start() throws Exception{			
		for (String treatment : ntService) {
			System.out.println(treatment);
			System.out.println();
			  							}//end of for loop
			while (true) {	
				try {
					int choice = scan.nextInt();
						switch(choice) {
							case 0:
								serviceAndPrice.showServiceAndPrice("Manicure                    ", 160.00);
								break;						    
							case 1:
								serviceAndPrice.showServiceAndPrice("Pedicure                    ", 200.00);
								break;
							case 2:
								serviceAndPrice.showServiceAndPrice("Gel Polish                  ", 300.00);
								break;
							case 3:
								serviceAndPrice.showServiceAndPrice("Softgel                     ", 350.00);
								break;
							case 4:
								serviceAndPrice.showServiceAndPrice("Nail Art Extension          ", 500.00);
								break;
							case 5:
								serviceAndPrice.showServiceAndPrice("Hand Spa                    ", 300.00);
								break;
							case 6:
								serviceAndPrice.showServiceAndPrice("Foot Spa                    ", 500.00);
								break;
							case 7:
								Services();
								return;
							case 8:
								System.exit(8);
							default:
                   				System.out.println("Invalid input, please enter a valid choice.");
               					break;
												   } // end of case statement
									} //end of try block
					
					catch (InputMismatchException e) {
						System.out.println("Invalid input, please enter a valid choice!");
						scan.nextLine();
			} //end of catch block
	    }//end of while loop
	 } //end of start method
} //end of class Nail treatment

class HairServices {
	static String hService [] = {"[0] Women Hair Cut", "[1] Women Hair Cut with Shampoo", "[2] Men Hair Cut", "[3] Men Hair Cut with Shampoo ", "[4] Hair Iron", "[5] Rebond", "[6] Hair Color with Treatment", "[7] Hair Brazilian", "[8] Hair Spa", "[9] Go back to menu ", "[10] Exit"};

	public static void start() throws Exception{					
		for (String service : hService) {
			System.out.println(service);
			System.out.println();
										}//end of for loop
		while (true) {
			try {
				int choice = scan.nextInt();
					switch(choice) {
						case 0:
							serviceAndPrice.showServiceAndPrice("Womens Haircut              ", 230.00 );
							break;
						case 1:
							serviceAndPrice.showServiceAndPrice("Womens haircut with shampoo ", 260.00 );
							break;
						case 2:
							serviceAndPrice.showServiceAndPrice("Mens haircut                ", 220.00 );
							break;
						case 3:
							serviceAndPrice.showServiceAndPrice("Mens haircut with shampoo   ", 250.00);
							break;
						case 4:
							serviceAndPrice.showServiceAndPrice("Hair Iron                   ", 350.00 );
							break;
						case 5:
							serviceAndPrice.showServiceAndPrice("Rebond                      ", 1500.00);
							break;
						case 6:
							serviceAndPrice.showServiceAndPrice("Hair Color and Treatment    ", 1800.00);
							break;
						case 7:
							serviceAndPrice.showServiceAndPrice("Hair Brazilian              ", 800.00);
							break;
						case 8:
							serviceAndPrice.showServiceAndPrice("Hair Spa                    ", 500);
							break;							
						case 9:
							Services();
							return;
						case 10:
							System.exit(10);
						default:
           					System.out.println("Invalid input, please enter a valid choice.");
       						break;
				   } // end of case statement
				} //end of try block
			catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid choice!");
				scan.nextLine();
			} //end of catch block								
        }//end of while loop	
	} // end of start method
} //end of class hair services

class FacialTreatments {
	static String fTreatment [] = {"[0] Basic Facial", "[1] Anti Aging Facial", "[2] Acne Facial", "[3] Hydrating Facial", "[4] Brightening Facial", "[5] Go back to menu", "[6] Exit"};

	public static void start() throws Exception{
		for (String treatment : fTreatment) {
			System.out.println(treatment);
			System.out.println();
										}//end of for loop
			while (true) {
				try {
					int choice = scan.nextInt();
						switch(choice) {
							case 0:
								serviceAndPrice.showServiceAndPrice("Basic Facial                ", 500.00);
								break;						    
							case 1:
								serviceAndPrice.showServiceAndPrice("Anti Aging Facial           ", 800.00);
								break;
							case 2:
								serviceAndPrice.showServiceAndPrice("Acne Facial                 ", 600.00);
								break;
							case 3:
								serviceAndPrice.showServiceAndPrice("Hydrating Facial            ", 600.00);
								break;
							case 4:
								serviceAndPrice.showServiceAndPrice("Brightening Facial          ", 700.00);
								break;							
							case 5: 
								Services();
								return;
							case 6:
								System.exit(6);
							default:
                    			System.out.println("Invalid input, please enter a valid choice.");
                    			break;
					}//end of case statements
				} //end of try block
				catch (InputMismatchException e) {
					System.out.println("Invalid input, please enter a valid choice!");
					scan.nextLine();
			} //end of catch block
		}// end of while loop					
    }//end of start method											
}//end of class facial treatments

class RelaxationMassage {
	static String rMassage [] = {"[0] Swedish Massage", "[1] Arometherapy Massage", "[2] Hot Stone Massage", "[3] Deep Tissue Massage", "[4] Thai Massage", "[5] Go back to Menu", "[6] Exit"};

	public static void start() throws Exception{		
		for (String massage : rMassage) {
			System.out.println(massage);
			System.out.println();				
										}//end of for loop
			while (true) {
				try {
					int choice = scan.nextInt();
						switch(choice) {
							case 0:
								serviceAndPrice.showServiceAndPrice("Swedish Massage             ", 300.00 );
								break;						   
							case 1:
								serviceAndPrice.showServiceAndPrice("Aromatherapy Massage        ", 400.00);
								break;
							case 2:
								serviceAndPrice.showServiceAndPrice("Hot Stone Massage           ", 500.00);
								break;
							case 3:
								serviceAndPrice.showServiceAndPrice("Deep Tissue Massage         ", 500.00);
								break;
							case 4:
								serviceAndPrice.showServiceAndPrice("Thai Massage                ", 350.00);
								break;
							case 5: 
								Services();
								return;
							case 6:
								System.exit(6);
							default:
             					System.out.println("Invalid input, please enter a valid choice.");
                   				break;
					}//end of case statements
				} //end of try block
				catch (InputMismatchException e) {
					System.out.println("Invalid input, please enter a valid choice!");
					scan.nextLine();
			} //end of catch block
		}// end of while loop
    }// end of start method
}//end of class facial treatments

class serviceAndPrice {		
    static final int customer_limit = 5;
   	static final int daily_limit = 50;
   	static int totalServices = 0;
	  
	public static void showServiceAndPrice(String serviceName, double price) throws Exception {
  		Scanner scan = new Scanner(System.in);

			try {

				if (Salon.selectedServices.size() >= daily_limit) {
					System.out.println("Daily service limit reached(50/50). No more appointments allowed");
					return;
				}  //end of if statement
			System.out.println("The price of " + serviceName.trim() + " is " + price);
			System.out.println();
			System.out.println("Press [0] to confirm. \nPress [1] to decline and select other Service. \nPress [2] to confirm and add another Service. \nPress [3] to cancel the service and proceed to payment.");

			int serviceChoice;

			while(true) {
				try {
					serviceChoice = scan.nextInt();
					break;
				} //end of try block
				catch (InputMismatchException e) {
					System.out.println("Invalid input, please enter a valid choice!");
					scan.nextLine();
				} //end of catch block
			}//end of while loop			

			switch (serviceChoice) {

			case 0:
				Salon.selectedServices.add(serviceName);
				Salon.servicePrices.add(Double.toString(price));
				System.out.println(serviceName.trim() + " has been selected, below is your balance to pay and select a date of the appointment!");
				showSelectedServices();

				if(selectedServices.size() >= customer_limit) {
					ConfirmServices();
				}
				break;
			case 1:
				System.out.println("The " + serviceName.trim() + " has been cancelled, going back to menu! ");
				totalServices--;
				Services();
				break;
			case 2:
				Salon.selectedServices.add(serviceName); 
				Salon.servicePrices.add(Double.toString(price));
				System.out.println("The " + serviceName.trim() + " has been added to the queue, redirecting on menu to select more services!");

				if (Salon.selectedServices.size() >= customer_limit) {
                    ConfirmServices();
                    }
                else {
                    Services();
                    } //end of if else statements
                    break;
			case 3:
				System.out.println("The " + serviceName.trim() + " has been cancelled, going to pameynt." );
				totalServices--;

				if(Salon.selectedServices.isEmpty()) {
					System.out.println();
					System.out.println("You don't have any service in the list, select one service to continue.");
					Services();
				} //end of if 
				else {
				showSelectedServices();
				}
				break;
			default: 
				System.out.println("Invalid input, please enter a valid choice!");
				scan.nextLine();
			}// end of switch 
				
		} //end of try block

			catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid choice!");
				scan.nextLine();
	} //end of catch block

} //end of showServiceAndPrice

	public static void ConfirmServices() throws Exception{
		Scanner scan = new Scanner(System.in);

		System.out.println("Service is limited to (5/5) per customer. You cannot add more services.\n Proceed to payment? (Y/N)");
  		String cancelConfirm = scan.nextLine().trim().toUpperCase();
  			if (cancelConfirm.equals("Y")) {
  				System.out.println("Proceeding to payment!!");
  				showSelectedServices();
  			}

  			else if(cancelConfirm.equals("N")) {
  				System.out.println("Service cancelled");
  				return;
  			}

  			else {
  				System.out.println("Invalid input, please enter Y or N!");
  				ConfirmServices();
  				} //end of if else statements

  		try {
        	showSelectedServices();
    		} //end of try block
    	catch (Exception e) {
        	System.out.println("An error occurred: " + e.getMessage());
    	} //end of catch block
	} //end of confirm services
} //end of class service and price	

	public static void showSelectedServices() throws InputMismatchException {
		System.out.println();
		System.out.println(" |================================================================================================================|");
		System.out.println(" |                                         ******SELECTED SERVICES******                                          |");
		System.out.println(" |================================================================================================================|");
	
		while (true) {
			try {
			
				double totalPrice = 0.0;
			for (int i = 0; i < selectedServices.size(); i++) {
				System.out.println("\t" + (i + 1) + ". " + selectedServices.get(i) + " \t\t\t\t\t\t " + servicePrices.get(i));
				double price = Double.parseDouble(Salon.servicePrices.get(i));
				totalPrice += price;

				} //end of for loop
				System.out.println("__________________________________________________________________________________________________________________");
				System.out.println();
				System.out.println("\tTotal: \t\t\t\t\t\t\t\t\t\t " + totalPrice);
				System.out.println();
				paymentMethod(totalPrice);

				writeCustomerOrder(userName, totalPrice, year, month, day,hour, minute, period);
							
			} //end of try block
			catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter again! " + e.getMessage());
		} //end of catch block
	} //end of while loop
} //end of show selected services
//===============================================================================================================================================
	     public static int year = 0;
         public static int month = 0;
         public static int hour = 0;
         public static int day = 0;
         public static int minute = 0;
         public static String period = "";
//==============================================================================================================================================

	public static void DateSelection(double totalPrice) {
		Scanner scan = new Scanner(System.in);
			System.out.println("|================================================================================================================|");
			System.out.println("|\t\t\t\t\tCHOOSE THE DATE OF YOUR APPOINTMENT 					 |");
			System.out.println("|================================================================================================================|");
			System.out.println("NOTE: opens at 8:30 AM closes at 8:30 PM");
			System.out.println();
		
		while(true) {
		
		try{
		   	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
           	int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
         	int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
         	Calendar maxAppointmentDate = Calendar.getInstance();
         	maxAppointmentDate.add(Calendar.MONTH, 6);
    
		while (true) {
		try {
            System.out.println("[Please enter the year of your Appointment (e.g., 2021,2022,2023,2024.)] ");
            year = scan.nextInt();

            if (year >= currentYear && year <= maxAppointmentDate.get(Calendar.YEAR)) {
               	break;  
                } 
            else if (year < currentYear ){
                System.out.println("You cannot schedule appointments on previous years, please enter again!");
                    } 
            else if(year > maxAppointmentDate.get(Calendar.YEAR)) {
                    	System.out.println("You cannot select this year, we only accept schedules less than six months starting today!");
                    } //end of if else statements			
			} //end of try block
		catch (InputMismatchException e) {
			System.out.println("Invalid input of year");
			scan.nextLine();
			} //end of catch block
            } //end of while loop


		while (true) {
		try{
            System.out.println("[Please enter the month of your Appointment (e.g., 1-12)] \n[NOTE: We do not accept appointments beyond the next six months, otherwise you have to wait or adjust it earlier.]");   
            month = scan.nextInt() - 1;
                if (year == currentYear && month < currentMonth) {
                    System.out.println("You cannot enter the previous months, please enter again!");
                } 
                else if (year == maxAppointmentDate.get(Calendar.YEAR) && month > maxAppointmentDate.get(Calendar.MONTH)) {
                   	System.out.println("We don't accept appointments beyond the next six months, please enter again!");
                    }  
                else {
                    	break;
                    } //end of if else statements
                } //end of try block
        catch(InputMismatchException e) {
          	System.out.println("Invalid input of month");
          	scan.nextLine();
                } //end of catch block
            } //end of while loop
	

		while (true) {
		try {
            System.out.println("[Please enter the day of your Appointment (e.g., 1-31)]");
            day = scan.nextInt();
                if (day >= 1 && day <= dayInMonth(month)) {
                    if (year == currentYear && month == currentMonth && day <= currentDay) {
                        System.out.println("You can't schedule an appointment today, you can only select the next day onwards!");
                        } 
                    else if(year == maxAppointmentDate.get(Calendar.YEAR) && month == maxAppointmentDate.get(Calendar.MONTH) && day > maxAppointmentDate.get(Calendar.DAY_OF_MONTH)) {
                       	System.out.println("You cannot schedule appointments beyond the next six months, please enter again!");
                        } 
                     else {
                        break;
                        } //end of inner if else statements
                    } //end of outer if
                    else {
                    	System.out.println("Invalid input of day, please enter again!");
                    } //end of nested if else
			} //end of try block
			catch(InputMismatchException e) {
				System.out.println("Invalid day input");
			} //end of catch block
         } //end of while loop

		while (true) {
		try {
            System.out.println("[Please enter the hour of your Appointment (e.g., 1-12)]");
            hour = scan.nextInt();                
                if (hour >= 1 && hour <= 12) {
                    break;
                		 } 
                else {
                    System.out.println("Invalid hour input, please enter between 1 and 12");
                } //end of if else statements
            } //end of try block
        catch (InputMismatchException e) {
			System.out.println("Invalid input of hour");
			scan.nextLine();
		} //end of catch block
    } //end of while loop
     
 		while (true) {
 		try {
            System.out.println("[Please enter the minute of your Appointment (e.g., 0-59)]");
            minute = scan.nextInt();
            scan.nextLine(); // consume newline
                if (minute >= 0 && minute <= 59) {
                    break;
                    } 
                else {
                    System.out.println("Invalid minute input, please enter between 0 and 59");
                } //end of if else statements
            } //end of try block
        catch(InputMismatchException e) {
           	System.out.println("Invalid minute input");
           	scan.nextLine();
        } //end of catch block
    } //end of while loop

 		while (true) {
 		try {
            System.out.println("[Please choose whether it is AM or PM]");
            period = scan.nextLine().toUpperCase();
                if (period.equals("AM") || period.equals("PM")) {
                    break;
            	   				} 
            else {
                    System.out.println("Invalid input!! Please enter either 'AM' or 'PM'");
                			} //end of if else statements
            			} //end of try block
            catch (InputMismatchException e) {
            	System.out.println("Invalid input");
            	scan.nextLine();
            		} //end of catch block
        	    } //end of while loop

			String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

			Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
       		
       		String monthName = months[calendar.get(Calendar.MONTH)];
		
			System.out.println("Your appointment is scheduled for the year " + calendar.get(Calendar.YEAR) + " " + 
			monthName + " " + calendar.get(Calendar.DAY_OF_MONTH) + " at " + calendar.get(Calendar.HOUR_OF_DAY) 
			+ ":" + calendar.get(Calendar.MINUTE) + ":" + " " + period);
			System.out.println("==================================================================================================================");
			writeCustomerOrder(userName, totalPrice, year, month, day, hour, minute, period);
			OrderSummary(userName, totalPrice, year, month, day, hour, minute, period);
			break;
			}//end of try block

		catch (Exception e) {
			System.out.println("Invalid input, please enter again! ");
			scan.nextLine();
		} //end of catch block
	} //end of outer while
} //end of date selection

	public static int dayInMonth(int month) {
		switch(month) {
		case 1: //jan
		case 3: //mar
		case 5: //may
		case 7: //jul
		case 8: //aug
		case 10: //oct
		case 12: //dec
			return 31;
		case 4: //apr
		case 6: //jun
		case 9: //sep
		case 11: //nov
			return 30;
		case 2: //feb
			return 20;
		default:
			return -1;
		} //end of case statements
	} //end of dayInMonth

	public static void paymentMethod(double totalPrice){
		Scanner scan = new Scanner(System.in);		
			System.out.println("[SELECT A PAYMENT METHOD] ");
			System.out.println("==================================================================================================================");
			System.out.println();
			System.out.println("[1] Full Payment");
			System.out.println("[2] Down Payment/Installment");
			System.out.println();
			System.out.println("\t\t***TERMS AND CONDITIONS***");
			System.out.println();
			System.out.println("FULL PAYMENT: \nDoes not apply any VAT!!");
			System.out.println();
			System.out.println("DOWN PAYMENT/INSTALLMENT: \nVAT may differ on the amount you pay");
			System.out.println("Pay 75% - vat = 5% \nPay 50% - vat = 8% \nPay 25% - vat = 12%");
			System.out.println("The balance remaining will be paid on site");
			System.out.println();
			System.out.println("[STRICTLY NO CANCELLATION/REFUND OF THE APPOINTMENT]");			
			System.out.println("==================================================================================================================");
			System.out.println();
			
		while(true) {
			
		try{
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					FullPayment(totalPrice, userName);
					return;
				case 2: 
					dpInstallment(totalPrice, userName);
					return;
				default: 
					System.out.println("Invalid input, please enter again.PM");
					scan.nextLine();
				} //end of switch
			} //end of try block
		catch (InputMismatchException e) {
			System.out.println("Invalid input, please enter again!!");
			scan.nextLine();
		} //end of catch block
	} //end of while loop
} //end of payment method
		public static double FullPaymentMethod;
		public static void FullPayment(double totalPrice, String userName){
			Scanner scan = new Scanner(System.in);
			
		while(true) {	

			try {
				System.out.println("Please enter your full payment here.");
				FullPaymentMethod = scan.nextDouble();
				System.out.println();
			
				if (FullPaymentMethod < totalPrice) {
					System.out.println("Insufficient amount of money!!");
					System.out.println("Please enter your full payment here.");
                	FullPaymentMethod = scan.nextDouble();
				}//end of if 
				else if(FullPaymentMethod == totalPrice) {
					double totalChange = FullPaymentMethod - totalPrice;
            		System.out.println("Your change is: " + totalChange);
            		System.out.println();
            		break;
				}
			    else if(FullPaymentMethod > totalPrice) {
			   		double totalChange = FullPaymentMethod - totalPrice;
            		System.out.println("Your change is: " + totalChange);
            		System.out.println();
            		break;
			    } //end of if else statements	
			} //end of try block 

		catch (InputMismatchException e) {
			System.out.println("Invalid input, please enter again!!");
			scan.nextLine();
		} //end of catch block
	} //end of while loop		

			DateSelection(totalPrice);
			OrderSummary(userName, totalPrice, year, month, day, hour, minute, period);
} //end of full payment
		 
	public static double downPayment;
	public static void dpInstallment(double totalPrice, String userName){
		Scanner scan = new Scanner(System.in);
		double percent1 =  totalPrice * .25;
		double percent2 = totalPrice * .50;
		double percent3 = totalPrice * .75;
		double percent4 = totalPrice * 1;

			while(true){
			try {		
				System.out.println("Please enter the payment you can afford to pay right now. \nNOTE: No VAT is applied when you pay in full \nRead the terms and conditions!)");
				downPayment = scan.nextDouble();

			 if (downPayment >= percent4) {
				double dueBalance = totalPrice - downPayment;
				System.out.println("Your due balance is: " + dueBalance);
				break;
			}else if (downPayment >= percent3) {			 	
                double vat1 = totalPrice * 0.05;
                double dueBalance = totalPrice - downPayment;
                System.out.println("Your due balance to pay with VAT is: " + (dueBalance + vat1));
               	break;               
            } else if (downPayment >= percent2) {
                double vat2 = totalPrice * 0.08;
                double dueBalance = totalPrice - downPayment;
                System.out.println("Your due balance to pay with VAT is: " + (dueBalance + vat2));
                break;
            } else if (downPayment >= percent1) {
                double vat3 = totalPrice * 0.12;
                double dueBalance = totalPrice - downPayment;
                System.out.println("Your due balance to pay with VAT is: " + (dueBalance + vat3));
                break;
            } 
            else if(percent1 > downPayment){
                System.out.println("Insufficient down payment amount.");					
    		    } //end of if else statements
			} //end of try block

			catch (InputMismatchException e){
				System.out.println("Invalid input, please enter again!!");
				scan.nextLine();
		} //end of catch block
	} //end of while loop
			DateSelection(totalPrice);
			OrderSummary(userName, totalPrice, year, month, day, hour, minute, period);

} //end of dpInstallment

	public static void writeCustomerOrder(String userName, double totalPrice, int year, int month, int day, int hour, int minute, String period){
		
		try {
			FileWriter fileWriter = new FileWriter("Receipt.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);

			printWriter.println("USERNAME: " + userName);
			for (int i = 0; i <selectedServices.size(); i++) {
				String chosenService = selectedServices.get(i);
				String chosenServicePrice = servicePrices.get(i);
				printWriter.println((i + 1) + ". " + chosenService + "\t\t" + chosenServicePrice);
			
			} //end of for loop
				printWriter.println("TOTAL: \t\t" + totalPrice);
				printWriter.println("APPOINTMENT DATE(YY/MM/DD): " + year + "/" + (month + 1) + "/" + day + " " + hour + ":" + minute + " " + period);
				printWriter.println("==============================================================================================================");
				printWriter.close();

		} //end of try block
		catch (IOException e) {
			e.printStackTrace();
		}
	} //end of writeCustomerOrder
	public static void OrderSummary(String userName, double totalPrice, int year, int month, int day, int hour, int minute, String period) {
		System.out.println("|====================================|");
		System.out.println("|         CUSTOMER RECIEPT           |");
		System.out.println("|====================================|");
		System.out.println();
		System.out.println("Username: " + userName);
		System.out.println("Date and time of Service(MM/DD/YY): " + (month + 1) + "/" + day + "/" + year + " | " + hour + ":" + minute + " " + period);
			for (int i = 0; i <selectedServices.size(); i++) {
			String chosenService = selectedServices.get(i);
			String chosenServicePrice = servicePrices.get(i);
		System.out.println((i + 1) + ". " + chosenService + "\t\t" + chosenServicePrice);
		} //end of for loop
		System.out.println("========================================================");
		System.out.println("Total: \t\t\t\t\t" + totalPrice);
		System.out.println();
		exit();
	} //end of order summary
	public static void exit() {
		System.out.println("|=======================================================================================================|");
		System.out.println("|                 Thank you for choosing us. We are looking forward to hear your feedback               |");
		System.out.println("|=======================================================================================================|");
		System.out.println();
		System.exit(0);

	}
	
				   	}// end of class salon
