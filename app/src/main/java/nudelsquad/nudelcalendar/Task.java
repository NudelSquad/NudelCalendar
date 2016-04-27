package nudelsquad.nudelcalendar;

/**
 * Created by emanuel on 27/04/16.
 */
public class Task {
    private int TASK_ID;
    private  String TASK_NAME;
    private int TASK_DUE;
    private String TASK_NOTES;
    private boolean TASK_DONE;
    //TODO Implement Reminder Logic?


    public Task() {
    }

    public Task(String TASK_NAME, int TASK_DUE) {
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
    }

    public Task(String TASK_NAME, int TASK_DUE, String TASK_NOTES) {
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
        this.TASK_NOTES = TASK_NOTES;
    }

    public Task(int TASK_ID, String TASK_NAME, int TASK_DUE, String TASK_NOTES, boolean TASK_DONE) {
        this.TASK_ID = TASK_ID;
        this.TASK_NAME = TASK_NAME;
        this.TASK_DUE = TASK_DUE;
        this.TASK_NOTES = TASK_NOTES;
        this.TASK_DONE = TASK_DONE;
    }

    public int getTASK_ID() {
        return TASK_ID;
    }

    public void setTASK_ID(int TASK_ID) {
        this.TASK_ID = TASK_ID;
    }

    public String getTASK_NAME() {
        return TASK_NAME;
    }

    public void setTASK_NAME(String TASK_NAME) {
        this.TASK_NAME = TASK_NAME;
    }

    public int getTASK_DUE() {
        return TASK_DUE;
    }

    public void setTASK_DUE(int TASK_DUE) {
        this.TASK_DUE = TASK_DUE;
    }

    public String getTASK_NOTES() {
        return TASK_NOTES;
    }

    public void setTASK_NOTES(String TASK_NOTES) {
        this.TASK_NOTES = TASK_NOTES;
    }

    public boolean isTASK_DONE() {
        return TASK_DONE;
    }

    public void setTASK_DONE(boolean TASK_DONE) {
        this.TASK_DONE = TASK_DONE;
    }
}
