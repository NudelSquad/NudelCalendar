package nudelsquad.nudelcalendar;

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

    /**
     * Empty Constructor
     */
    public Event() {

    }

    /**
     * Constructor with minimum requirements
     * @param EVENT_START
     * @param EVENT_END
     * @param EVENT_NAME
     */
    public Event(int EVENT_START, int EVENT_END, String EVENT_NAME) {
        this.EVENT_START = EVENT_START;
        this.EVENT_END = EVENT_END;
        this.EVENT_NAME = EVENT_NAME;
    }

    /**
     * Constructor with maximum requirements
     * @param EVENT_ID
     * @param EVENT_START
     * @param EVENT_END
     * @param EVENT_NAME
     * @param EVENT_TYPE
     * @param EVENT_LOCATION
     * @param EVENT_COLOR
     */
    public Event(int EVENT_ID, int EVENT_START, int EVENT_END, String EVENT_NAME, String EVENT_TYPE, String EVENT_LOCATION, int EVENT_COLOR) {
        this.EVENT_ID = EVENT_ID;
        this.EVENT_START = EVENT_START;
        this.EVENT_END = EVENT_END;
        this.EVENT_NAME = EVENT_NAME;
        this.EVENT_TYPE = EVENT_TYPE;
        this.EVENT_LOCATION = EVENT_LOCATION;
        this.EVENT_COLOR = EVENT_COLOR;
    }


    /**
     * Get Event ID
     * @return int
     */
    public int getEVENT_ID() {
        return EVENT_ID;
    }

    /**
     * Set Event ID
     * @param EVENT_ID
     */
    public void setEVENT_ID(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    /**
     * Get Event Start Time
     * @return int
     */
    public int getEVENT_START() {
        return EVENT_START;
    }

    /**
     * Set Event Start Time
     * @param EVENT_START
     */
    public void setEVENT_START(int EVENT_START) {
        this.EVENT_START = EVENT_START;
    }

    /**
     * Get Event Stop Time
     * @return int
     */
    public int getEVENT_END() {
        return EVENT_END;
    }

    /**
     * Set Event Stop Time
     * @param EVENT_END
     */
    public void setEVENT_END(int EVENT_END) {
        this.EVENT_END = EVENT_END;
    }

    /**
     * Get Event Name
     * @return String
     */
    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    /**
     * Set Event Name
     * @param EVENT_NAME
     */
    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    /**
     * Get Event Type
     * @return String
     */
    public String getEVENT_TYPE() {
        return EVENT_TYPE;
    }


    /**
     * Set Event Type
     * @param EVENT_TYPE
     */
    public void setEVENT_TYPE(String EVENT_TYPE) {
        this.EVENT_TYPE = EVENT_TYPE;
    }

    /**
     * Get Event Location
     * @return String
     */
    public String getEVENT_LOCATION() {
        return EVENT_LOCATION;
    }

    /**
     * Set Event Location
     * @param EVENT_LOCATION
     */
    public void setEVENT_LOCATION(String EVENT_LOCATION) {
        this.EVENT_LOCATION = EVENT_LOCATION;
    }

    /**
     * Get Event Color
     * @return int
     */
    public int getEVENT_COLOR() {
        return EVENT_COLOR;
    }

    /**
     * Set Event Color
     * @param EVENT_COLOR
     */
    public void setEVENT_COLOR(int EVENT_COLOR) {
        this.EVENT_COLOR = EVENT_COLOR;
    }
}
