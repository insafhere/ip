import java.util.ArrayList;

/**
 * event class extend from command class.
 */
public class event extends Command {
    String fullCommand;

    event(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * execute method prints new event task and adds it into task list.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {

        System.out.println("Got it. I've added this task");

        // Modify text according to deadline
        String editedCommand = fullCommand.substring(6, fullCommand.length());
        int indexOfSlash = editedCommand.indexOf("/", 0);
        String day = editedCommand.substring(indexOfSlash + 4, editedCommand.length());
        String task = editedCommand.substring(0, indexOfSlash);
        editedCommand = task + "(at: " + day + ")";

        System.out.println("  [E][ ] " + editedCommand);
        System.out.println("Now you have " + storage.getCount() + " tasks in the list.");
        tasks.add(editedCommand);
        type.add("[E]");
        marks.add("[ ]");
        Integer count = storage.getCount();
        storage.updateCount(count+1);
    }

    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }
}
