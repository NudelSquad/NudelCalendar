package nudelsquad.nudelcalendar;

/**
 * Created by emanuel on 27/04/16.
 * If Members are changed please increase DB version in DBHandlerTask.java
 */
public class Task {
    private int TASK_ID;
    private  String TASK_NAME;
    private int TASK_DUE;
    private String TASK_NOTES;
    private boolean TASK_DONE;
    //TODO Implement Reminder Logic?


    /**
     * Empty Constructur
     */
    public Task() {
    }


    /**
     * Constructor with minimal requirements
     * @param TASK_NAME
     * @param TASK_DUE
     */
    public Task(String TASK_NAME, int TASK_DUE) {
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
    }


    /**
     * Constructor with notes
     * @param TASK_NAME
     * @param TASK_DUE
     * @param TASK_NOTES
     */
    public Task(String TASK_NAME, int TASK_DUE, String TASK_NOTES) {
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
        this.TASK_NOTES = TASK_NOTES;
    }


    /**
     * Constructor with maximum Elements
     * @param TASK_ID
     * @param TASK_NAME
     * @param TASK_DUE
     * @param TASK_NOTES
     * @param TASK_DONE
     */
    public Task(int TASK_ID, String TASK_NAME, int TASK_DUE, String TASK_NOTES, boolean TASK_DONE) {
        this.TASK_ID = TASK_ID;
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
        this.TASK_NOTES = TASK_NOTES;
        this.TASK_DONE = TASK_DONE;
    }

    /**
     * Get ID
     * @return int taskID
     */
    public int getTASK_ID() {
        return TASK_ID;
    }

    /**
     * Set ID
     * @param TASK_ID
     */
    public void setTASK_ID(int TASK_ID) {
        this.TASK_ID = TASK_ID;
    }

    /**
     * Get Task Name
     * @return String
     */
    public String getTASK_NAME() {
        return TASK_NAME;
    }

    /**
     * Set Task Name
     * @param TASK_NAME
     */
    public void setTASK_NAME(String TASK_NAME) {
        this.TASK_NAME = TASK_NAME;
    }

    /**
     * Get Task Due date
     * @return int
     */
    public int getTASK_DUE() {
        return TASK_DUE;
    }

    /**
     * Set Task Due Date
     * @param TASK_DUE
     */
    public void setTASK_DUE(int TASK_DUE) {
        this.TASK_DUE = TASK_DUE;
    }

    /**
     * Get Task Notes
     * @return TASK_NOTES
     */
    public String getTASK_NOTES() {
        return TASK_NOTES;
    }

    /**
     * Set Task Notes
     * @param TASK_NOTES
     */
    public void setTASK_NOTES(String TASK_NOTES) {
        this.TASK_NOTES = TASK_NOTES;
    }

    /**
     * Get Boolean if Task is done
     * @return boolean
     */
    public boolean isTASK_DONE() {
        return TASK_DONE;
    }

    /**
     * Set Boolean if Task is done
     * @param TASK_DONE
     */
    public void setTASK_DONE(boolean TASK_DONE) {
        this.TASK_DONE = TASK_DONE;
    }
}
