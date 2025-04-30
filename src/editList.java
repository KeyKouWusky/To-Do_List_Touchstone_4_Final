import java.io.*;
import java.nio.file.*;
import java.util.*;

public class editList {
    private List<String> items;
    private List<String> completed;
    private File ToDoList;
    Scanner scan = new Scanner(System.in);

    //This method will check if there is a previously saved .txt file.
    //If one is found, this method will also scan through the file and sort
    //the tasks into ArrayLists so it can be edited by the user. If no file is
    //found, then it creates the file.
    public void loadList() {
        ToDoList = new File("To-Do List.txt");
        //This is to help determine what tasks are to-do, and which are completed.
        boolean isCompleted = false;
        try {
            //Creates the main ArrayLists that will be used throughout the program
            this.items = new ArrayList<>();
            this.completed = new ArrayList<>();

            if (ToDoList.exists()) {
                //This creates a third ArrayList for the purpose of getting
                //the content out of the .txt file and into the program
                List<String> unsorted = Files.readAllLines(ToDoList.toPath());
                //Scans each line and removes any extra whitespaces in the
                //file to keep formatting functional and clean
                for (String newLine : unsorted) {
                    String line = newLine.trim();

                    //Tells the program at which point in the Arraylist to
                    //start adding to the completed list
                    if (line.equalsIgnoreCase("completed")) {
                        isCompleted = true;
                    }

                    //Tells the program to ignore the to-do list title and
                    //the divider lines when checking for tasks.
                    else if (line.isEmpty()
                            || line.equals("To-Do List")
                            || line.equals("====================")) {
                        continue;

                    //Adds items to the to-do list ArrayList.
                    } else if (!isCompleted) {
                        items.add(line);
                    //Adds items to the completed ArrayList.
                    } else {
                        completed.add(line);
                    }
                }
            //Creates a new file if one wasn't found.
            } else {
                ToDoList.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method that displays the tasks in the console for the user to
    //interact with.
    public void view() {
        //Header for the to-do list
        System.out.println("====================");
        System.out.println("To-Do List");
        System.out.println("====================");
        //Prints each to-do task on its own line
        for (String list : items) {
            System.out.println("-" + list);
        }
        //Header for the completed list
        System.out.println(" ");
        System.out.println("====================");
        System.out.println("Completed");
        System.out.println("====================");
        //Prints each completed task on its own line
        for (String list : completed) {
            System.out.println("-" + list);
        }
        //Bottom divider and lists the commands the user can use
        System.out.println(" ");
        System.out.println("====================");
        System.out.println("Make one of the following selections to edit your list:");
        System.out.println("-(c)omplete\n-(a)dd\n-(d)elete\n-(r)eset\n-(q)uit\n");
    }

    //Method that adds tasks to the to-do list
    public void add() throws IOException {
        System.out.println("What would you like to add?");
        String newTask = scan.nextLine();
        items.add(newTask);
    }

    //Method that deletes tasks from the to-do list
    public void delete() throws IOException {
        System.out.println("What would you like to delete?");
        String taskToDelete = scan.nextLine();
        items.remove(taskToDelete);
    }

    //Method that will reset both the to-do list and the completed list
    public void reset() throws IOException {
        items.clear();
        completed.clear();
    }

    //Method that moves a to-do task into the completed portion
    public void complete() throws IOException {
        System.out.println("What would you like to check off your list?");
        String checkOff = scan.nextLine();
        completed.add(checkOff);
        items.remove(checkOff);
    }

    //Method that takes both the to-do list and the completed list, and both stores
    //and formats them back into the .txt file for later use.
    public void saveFile() throws IOException {
        //Allows proper printing inside the .txt file
        BufferedWriter writer = new BufferedWriter(new FileWriter("To-Do List.txt"));
        //Header for the to-do list
        writer.write("====================\n");
        writer.write("To-Do List\n");
        writer.write("====================\n");
        //Adds the to-do list items one by one into the to-do section
        for (String item : items) {
            writer.write(item);
            writer.newLine();
        }
        //Header for the completed list
        writer.write(" \n");
        writer.write("====================\n");
        writer.write("Completed\n");
        writer.write("====================\n");
        //Adds the completed items one by one into the to-do section
        for (String checkedOff : completed) {
            writer.write(checkedOff);
            writer.newLine();
        }
        writer.write(" \n");
        writer.close();
    }
}
