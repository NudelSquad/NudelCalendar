package nudelsquad.nudelcalendar;

/**
 * Created by emanuel on 20/04/16.
 */
public class Event {
    private int EVENT_ID;
    private String EVENT_START;
    private String EVENT_END;
    private String EVENT_DATUM;
    private String EVENT_NAME;
    private String EVENT_TYPE;
    private String EVENT_LOCATION;
    private String EVENT_AUDIOPATH="";
    private int EVENT_COLOR;

    public Event(int EVENT_ID, String EVENT_NAME, String EVENT_START,  String EVENT_END, String EVENT_DATUM, String EVENT_TYPE,  String EVENT_LOCATION, int EVENT_COLOR, String EVENT_AUDIOPATH) {
        this.EVENT_START = EVENT_START;
        this.EVENT_ID = EVENT_ID;
        this.EVENT_END = EVENT_END;
        this.EVENT_DATUM = EVENT_DATUM;
        this.EVENT_TYPE = EVENT_TYPE;
        this.EVENT_NAME = EVENT_NAME;
        this.EVENT_LOCATION = EVENT_LOCATION;
        this.EVENT_COLOR = EVENT_COLOR;
        this.EVENT_AUDIOPATH =EVENT_AUDIOPATH;
    }

    public Event(String EVENT_NAME, String EVENT_START, String EVENT_END, String EVENT_DATUM, String EVENT_TYPE,  String EVENT_LOCATION, int EVENT_COLOR, String EVENT_AUDIOPATH) {
        this.EVENT_START = EVENT_START;
        this.EVENT_END = EVENT_END;
        this.EVENT_DATUM = EVENT_DATUM;
        this.EVENT_TYPE = EVENT_TYPE;
        this.EVENT_NAME = EVENT_NAME;
        this.EVENT_LOCATION = EVENT_LOCATION;
        this.EVENT_COLOR = EVENT_COLOR;
        this.EVENT_AUDIOPATH =EVENT_AUDIOPATH;
    }

    public int getEVENT_ID() {
        return EVENT_ID;
    }

    public void setEVENT_ID(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public String getEVENT_START() {
        return EVENT_START;
    }

    public void setEVENT_START(String EVENT_START) {
        this.EVENT_START = EVENT_START;
    }

    public String getEVENT_END() {
        return EVENT_END;
    }

    public void setEVENT_END(String EVENT_END) {
        this.EVENT_END = EVENT_END;
    }

    public String getEVENT_DATUM() {
        return EVENT_DATUM;
    }

    public void setEVENT_DATUM(String EVENT_DATUM) {
        this.EVENT_DATUM = EVENT_DATUM;
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

    public String getEVENT_AUDIOPATH() {
        return EVENT_AUDIOPATH;
    }

    public void setEVENT_AUDIOPATH(String EVENT_AUDIOPATH) {
        this.EVENT_AUDIOPATH = EVENT_AUDIOPATH;
    }

    @Override
    public String toString() {
        return "Event{" +
                "EVENT_ID=" + EVENT_ID +
                ", EVENT_START='" + EVENT_START + '\'' +
                ", EVENT_END='" + EVENT_END + '\'' +
                ", EVENT_DATUM='" + EVENT_DATUM + '\'' +
                ", EVENT_NAME='" + EVENT_NAME + '\'' +
                ", EVENT_TYPE='" + EVENT_TYPE + '\'' +
                ", EVENT_LOCATION='" + EVENT_LOCATION + '\'' +
                ", EVENT_COLOR=" + EVENT_COLOR +
                ", EVENT_AUDIOPATH=" + EVENT_AUDIOPATH +
                '}';
    }
}
