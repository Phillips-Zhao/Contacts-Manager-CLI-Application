import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsApp {

    public static List<Contact> contacts = new ArrayList<>();
    public static List<String> contactString = Collections.singletonList("");
    public static Path path = Paths.get("./src/contacts.txt").normalize();


    public static void contactApp() {

        try {
            contactString = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String line : contactString) {
            String[] contactInfo = line.split(" \\+ ");
            Contact contact = new Contact(contactInfo[0], Long.parseLong(contactInfo[1]));
            System.out.println(contact.getContactName() + " " + contact.getContactNumber());
            contacts.add(contact);
        }

        try {
            Files.write(path, contactString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printContacts () {
        for (Contact line : contacts) {
            System.out.println(line.getContactName() + " : " + line.getContactNumber());
        }
    }

    public static void addContact () {
        Path path = Paths.get("./src/contacts.txt").normalize();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name");
        String newContactName = scanner.nextLine();
        System.out.println("Enter the number");
        long newContactNumber = scanner.nextLong();
        Contact newContact = new Contact(newContactName, newContactNumber);
        System.out.println(newContact.toString());
        contacts.add(newContact);
    }
   public static void searchContact(){
        Scanner scanner = new Scanner(System.in);
       System.out.println("Search by name enter:1");
       System.out.println("Search by number enter:2");
       System.out.println("exit by number :3");
       int input = scanner.nextInt();
       boolean agree = true;
       do{
           if(input == 1){
               String inputName = scanner.nextLine();
               for(Contact line: contacts){
                   if(line.getContactName().equals(inputName)){
                       System.out.println(line);
                   } else {
                       System.out.println("We can't find the person you are looking for");
                   }
                   break;
               }
           }else if( input == 2) {
               int inputNumber = scanner.nextInt();
               for (Contact line : contacts) {
                   if (line.getContactNumber() == inputNumber) {
                       System.out.println(line);
                   } else{
                       System.out.println("We can't find the number you are looking for");
                   }
                   break;
               }
           } else if (input == 3){
               agree = false;

           } else{
               System.out.println("Please enter the valid number");
           }

       }while(agree);


   }
    public static void deleteContact(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by name enter:1");
        System.out.println("Search by number enter:2");
        System.out.println("exit by number :3");
        int input = scanner.nextInt();
        boolean agree = true;
        do{
            if(input == 1){
                String inputName = scanner.nextLine();
                for(Contact line: contacts){
                    if(line.getContactName().equalsIgnoreCase(inputName)){
                        System.out.println(line);
                        System.out.println("Are you sure you want to delete it? Y or N");
                        String deleteName = scanner.nextLine();
                        if (deleteName.equalsIgnoreCase("Y")) {
                            contacts.remove(line);
                        } else if (deleteName.equalsIgnoreCase("N")){
                            break;
                        }
                    } else {
                        System.out.println("We can't find the person you are looking for");
                    }
                    break;
                }
            }else if( input == 2) {
                int inputNumber = scanner.nextInt();
                for (Contact line : contacts) {
                    if (line.getContactNumber() == inputNumber) {
                        System.out.println(line);
                        System.out.println("Are you sure you want to delete it? Y or N");
                        String deleteName = scanner.nextLine();
                        if (deleteName.equalsIgnoreCase("Y")) {
                            contacts.remove(line);

                            break;

                        } else if (deleteName.equalsIgnoreCase("N")){
                            break;
                        }

                    } else{
                        System.out.println("We can't find the number you are looking for");
                    }
                    break;
                }
            } else if (input == 3){
                agree = false;

            } else{
                System.out.println("Please enter the valid number");
            }
            break;
        }while(agree);


    }

    public static void main(String[] args) {
        boolean agree = true;
        contactApp();
        addContact();
        printContacts();
        do{
            System.out.println("1. View Contacts.");
            System.out.println("2. Add a New Contact.");
            System.out.println("3. Search a Contact by Name");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    contactApp();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    agree = false;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println("Continue?Y or N");
            String input1= scanner.nextLine();
            if(input1.equalsIgnoreCase("Y")){
                continue;
            } else if (input1.equalsIgnoreCase("N")){
                agree = false;
            } else{
                System.out.println("Invalid option");
            }

        }while(agree);

    }



}
