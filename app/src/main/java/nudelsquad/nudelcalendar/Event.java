package nudelsquad.nudelcalendar;

import java.util.Date;

/**
 * Created by emanuel on 20/04/16.
 */
public class Event {
    private int ID;
    private Date EVENT_START;
    private Date EVENT_END;
    private String EVENT_NAME;
    private String EVENT_TYPE;
    private String EVENT_LOCATION;
    private int EVENT_COLOR;
    //TODO Implement Reminder


    public int getID() {
        return ID;
    }

    public Date getEVENT_START() {
        return EVENT_START;
    }

    public void setEVENT_START(Date EVENT_START) {
        this.EVENT_START = EVENT_START;
    }

    public Date getEVENT_END() {
        return EVENT_END;
    }

    public void setEVENT_END(Date EVENT_END) {
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
