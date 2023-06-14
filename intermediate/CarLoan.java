public class CarLoan {
    public static void main(String[] args) {
        //Define variables
        int carLoan = 10000;
        int loanLength = 3;
        int interestRate = 5;
        int downPayment = 2000;

        //Check for invalid car loans
        if (loanLength <= 0 || interestRate <= 0) {
            System.out.println("Error! You must take out a valid car loan.");
        } else if (downPayment >= carLoan) 
        //Check if car can be paid in full
        {
            System.out.println("The car can be paid in full.");
        } else {

            //Calculate remaining money to be paid
            int remainingBalance = carLoan - downPayment;
            int months = loanLength * 12;
            int monthlyBalance = remainingBalance / months;

            //Calculate interest and final number
            int interest = monthlyBalance * interestRate / 100;
            int monthlyPayment = monthlyBalance + interest;

            //Print
            System.out.println(monthlyPayment);
        }


    }     
}
