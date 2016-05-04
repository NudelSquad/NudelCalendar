package nudelsquad.nudelcalendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emanuel on 20/04/16.
 */
public class Event {
    private int EVENT_ID;
    private int EVENT_START;
    private int EVENT_END;
    private String EVENT_NAME;
    private String EVENT_TYPE;
    private String EVENT_LOCATION;
    private int EVENT_COLOR;
    //TODO Implement Reminder

    public Event() {

    }

    public Event(int EVENT_ID, int EVENT_START, int EVENT_END, String EVENT_NAME, String EVENT_TYPE, String EVENT_LOCATION, int EVENT_COLOR) {
        this.EVENT_ID = EVENT_ID;
        this.EVENT_START = EVENT_START;
        this.EVENT_END = EVENT_END;
        this.EVENT_NAME = EVENT_NAME;
        this.EVENT_TYPE = EVENT_TYPE;
        this.EVENT_LOCATION = EVENT_LOCATION;
        this.EVENT_COLOR = EVENT_COLOR;
    }



    public int getEVENT_ID() {
        return EVENT_ID;
    }

    public void setEVENT_ID(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public int getEVENT_START() {
        return EVENT_START;
    }

    public void setEVENT_START(int EVENT_START) {
        this.EVENT_START = EVENT_START;
    }

    public int getEVENT_END() {
        return EVENT_END;
    }

    public void setEVENT_END(int EVENT_END) {
        this.EVENT_END = EVENT_END;
    }

    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    public String getEVENT_TYPE() {
        return EVENT_TYPE;
    }

    public void setEVENT_TYPE(String EVENT_TYPE) {
        this.EVENT_TYPE = EVENT_TYPE;
    }

    public String getEVENT_LOCATION() {
        return EVENT_LOCATION;
    }

    public void setEVENT_LOCATION(String EVENT_LOCATION) {
        this.EVENT_LOCATION = EVENT_LOCATION;
    }

    public int getEVENT_COLOR() {
        return EVENT_COLOR;
    }

    public void setEVENT_COLOR(int EVENT_COLOR) {
        this.EVENT_COLOR = EVENT_COLOR;
    }
}
