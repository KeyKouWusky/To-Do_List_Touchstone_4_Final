import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        editList list = new editList();

        //Either loads in a previously saved to-do file
        //or creates a new one if one isn't found
        list.loadList();
        System.out.println("Welcome!");

        String selection = "temp";
        do {
            //Pulls up the list(s) in the console
            list.view();
            //Gets input from user regarding their menu selection
            selection = scan.next();
            //Calls the method that will add the next input to their list
            if (Objects.equals(selection, "add") ||
                    (Objects.equals(selection, "a"))) {
                        list.add();
            }
            //Calls the method that will delete the next input from their list
            else if (Objects.equals(selection, "delete") ||
                    (Objects.equals(selection, "d"))) {
                        list.delete();
            }
            //Calls the method that will clear everything on their lists
            else if (Objects.equals(selection, "reset") ||
                    (Objects.equals(selection, "r"))) {
                        list.reset();
            }
            //Calls the method that will check off tasks and move them
            //to the completed section for personal reference
            else if (Objects.equals(selection, "complete") ||
                    (Objects.equals(selection, "c"))) {
                        list.complete();
            }
            //Calls the method that saves both the to-do list and the completed
            //list as a text file so it can be pulled back up later or act as
            //a backup
            list.saveFile();

        //shuts the program down when inputted to do so
        } while (!Objects.equals(selection, "quit") &&
                (!Objects.equals(selection, "q")));
    }
}