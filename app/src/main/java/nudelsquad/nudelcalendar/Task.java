package nudelsquad.nudelcalendar;

import java.util.ArrayList;

/**
 * Created by emanuel on 27/04/16.
 * If Members are changed please increase DB version in DBHandlerTask.java
 */
public class Task {
    private int TASK_ID;
    private String TASK_NAME;
    private String TASK_DATUM;
    private String TASK_TEXT;
    private int TASK_COLOR;
    private int TASK_EVENTID;
    private boolean TASK_CHECKED;

    private static ArrayList<Task> openTasks = new ArrayList<Task>();

    public Task(){}

    public Task(int TASK_ID, String TASK_NAME, String TASK_DATUM, String TASK_TEXT, int TASK_COLOR, int TASK_EVENTID, boolean TASK_CHECKED) {
        this.TASK_ID = TASK_ID;
        this.TASK_NAME = TASK_NAME;
        this.TASK_DATUM = TASK_DATUM;
        this.TASK_TEXT = TASK_TEXT;
        this.TASK_COLOR = TASK_COLOR;
        this.TASK_EVENTID = TASK_EVENTID;
        this.TASK_CHECKED = TASK_CHECKED;
    }

    public Task(String TASK_NAME, String TASK_DATUM, String TASK_TEXT, int TASK_COLOR, int TASK_EVENTID, boolean TASK_CHECKED) {
        this.TASK_NAME = TASK_NAME;
        this.TASK_DATUM = TASK_DATUM;
        this.TASK_TEXT = TASK_TEXT;
        this.TASK_COLOR = TASK_COLOR;
        this.TASK_EVENTID = TASK_EVENTID;
        this.TASK_CHECKED = TASK_CHECKED;
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

    public String getTASK_DATUM() {
        return TASK_DATUM;
    }

    public void setTASK_DATUM(String TASK_DATUM) {
        this.TASK_DATUM = TASK_DATUM;
    }

    public String getTASK_TEXT() {
        return TASK_TEXT;
    }

    public void setTASK_TEXT(String TASK_TEXT) {
        this.TASK_TEXT = TASK_TEXT;
    }

    public int getTASK_COLOR() {
        return TASK_COLOR;
    }

    public void setTASK_COLOR(int TASK_COLOR) {
        this.TASK_COLOR = TASK_COLOR;
    }

    public int getTASK_EVENTID() {
        return TASK_EVENTID;
    }

    public void setTASK_EVENTID(int TASK_EVENTID) {
        this.TASK_EVENTID = TASK_EVENTID;
    }

    public boolean getTASK_CHECKED() {
        return TASK_CHECKED;
    }

    public void setTASK_CHECKED(boolean TASK_CHECKED) {
        this.TASK_CHECKED = TASK_CHECKED;
    }

    public static ArrayList<Task> getOpenTasks() {
        return openTasks;
    }

}
