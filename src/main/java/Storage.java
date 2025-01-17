import java.io.*;
import java.util.ArrayList;

/**
 * storage class.
 */
public class Storage {
    File file;
    ArrayList<String> tasks;
    ArrayList<String> marks;
    ArrayList<String> type;
    int count = 1;

    /**
     * storage constructor which takes in the filepath.
     */
    Storage(String filePath){
        // Creates file if it is not created yet
        this.file = new File("duke.txt");
        this.tasks = new ArrayList<String>();
        this.marks = new ArrayList<String>();
        this.type = new ArrayList<String>();

        try {
            // Use relative path instead of relative path
            file.createNewFile();  // creates file if it has not been created
            System.out.println("duke.txt file found at " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * load method helps to load the data from duke.txt file into the program.
     */
    void load() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            while (line != null) {

                // Load data to system
                String letterType = line.substring(0, 1);
                String numberMark = line.substring(4, 5);
                String taskName = line.substring(8, line.length());

                String strMark = null;
                if (numberMark.equals("1")) {
                    strMark = "[X]";
                } else if (numberMark.equals("0")) {
                    strMark = "[ ]";
                }

                this.tasks.add(taskName);
                this.type.add("[" + letterType + "]");
                this.marks.add(strMark);
                this.count++;

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for the task type list.
     */
    ArrayList<String> getTypes() {
        return this.type;
    }

    /**
     * getter for the task name list.
     */
    ArrayList<String> getTasks() {
        return this.tasks;
    }

    /**
     * getter for the task mark list.
     */
    ArrayList<String> getMarks() {
        return this.marks;
    }

    /**
     * getter for the number of task.
     */
    int getCount(){
        return this.count;
    }

    /**
     * updateCount method to update the number of task.
     */
    void updateCount(int newCount){
        this.count = newCount;
    }

    /**
     * updateFile method helps to update the task list after commands are being called.
     */
    public void updateFile(Integer count, ArrayList<String> tasks, ArrayList<String> marks, ArrayList<String> type) {
        try{
            this.file.delete();
            this.file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter myWriter = new FileWriter("duke.txt");
             BufferedWriter info = new BufferedWriter(myWriter)){
            for(int i = 1; i < count ; i++){
                String typeLetter = type.get(i-1).substring(1,2);  // from e.g. "[E]"
                String numMark = null;
                if (marks.get(i-1).equals("[X]")){
                    numMark = "1";
                }
                else if (marks.get(i-1).equals("[ ]")){
                    numMark = "0";
                }
                info.write(typeLetter + " | " + numMark + " | " + tasks.get(i-1));
                info.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
