import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;



public class Creation implements AccountCreation {

    public void createAccFun() throws IOException {
        int accNo = accNoCreation();
        String[] accLineInfo = getUserInfoFromUser();
        credWrite(accNo, accLineInfo);
        balWrite(accNo);
        userWrite(accNo, accLineInfo);
        System.out.println("\nAccount created successfully!\n");
        System.out.println("Your account number is: " + accNo);
        System.out.println("Your password is: " + accLineInfo[8] + "\n");
        Bankinterface.menu(accNo);
    }

    public String[] getUserInfoFromUser() throws IOException {
        String[] accLineInfo = new String[9];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your Name: ");
        String fullName = sc.nextLine();
        String[] fullNameArr = fullName.split(" ");
        if (fullNameArr.length != 2) {
            System.out.println("Please provide both first name and last name.");
            return getUserInfoFromUser();
        } else {
            accLineInfo[0] = fullNameArr[0];
            accLineInfo[1] = fullNameArr[1];
        }

        System.out.println("Enter your Date of Birth (YYYY-MM-DD): ");
        accLineInfo[2] = sc.nextLine();
        System.out.println("Enter your Gender: ");
        accLineInfo[3] = sc.nextLine();
        System.out.println("Enter your Address (Use dash instead of spaces):");
        accLineInfo[4] = sc.nextLine();
        System.out.println("Enter your Phone Number: ");
        accLineInfo[5] = sc.nextLine();
        while (isPhoneNumberValid(accLineInfo[5]) == false) {
            System.out.println("Phone number is invalid !");
            System.out.println("Enter your Number again : ");
            accLineInfo[5] = sc.nextLine();
        }
        System.out.println("Enter your Email: ");
        accLineInfo[6] = sc.nextLine();
        while (isEmailValid(accLineInfo[6]) == false) {
            System.out.println("E-mail id format is invalid !");
            System.out.println("Enter your Email again : ");
            accLineInfo[6] = sc.nextLine();
        }
        System.out.println("Enter your Aadhar Card Number (XXXX-XXXX-XXXX) : ");
        accLineInfo[7] = sc.nextLine();
        System.out.println("Create a Password for your Account: ");
        accLineInfo[8] = sc.nextLine();
        return accLineInfo;
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        int n = phoneNumber.length();
        for (int i = 0; i < n; i++) {
            if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57) {
                return false;
            }
        }
        return (n == 10);
    }

    public int accNoCreation() throws IOException {
        String lastLine = "";
        int accNo;
        try {
            File file = new File("credentials.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lastLine = scanner.nextLine();
            }
            if (Objects.equals(lastLine, "")) {
                accNo = 100001;
            } else {
                String[] subLine = lastLine.split(" ");
                accNo = Integer.parseInt(subLine[0]);
                accNo++;
            }
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
            accNo = -1;
        }

        return accNo;
    }

    public void credWrite(int accNo, String[] accLineInfo) throws IOException {

        try {
            FileWriter writer = new FileWriter("credentials.txt", true);
            writer.write("\n" + accNo + " " + accLineInfo[8]);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured!!");
            e.printStackTrace();
        }
    }

    public void balWrite(int accNo) throws IOException {
        int upperBound = 10000;
        int rInt = (int) (Math.random() * upperBound);
        int initialBal = rInt;
        try {
            FileWriter writer = new FileWriter("balanceDB.txt", true);
            writer.write("\n" + accNo + " " + initialBal);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured!!");
            e.printStackTrace();
        }

    }

    public void userWrite(int accNo, String[] accLineInfo) throws IOException {
        try {
            FileWriter writer = new FileWriter("userDB.txt", true);
            writer.write("\n" + accNo + " ");
            for (int i = 0; i < 8; i++) {
                writer.write(accLineInfo[i] + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured!!");
            e.printStackTrace();
        }

    }

}