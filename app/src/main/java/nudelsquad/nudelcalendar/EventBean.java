package nudelsquad.nudelcalendar;

/**
 * Created by Marco on 20.04.2016.
 */
public class EventBean {
    private String start_time_, end_time_, name_, type_, place_;
    private int color_;
    //private Color color_;
    private int eventid_;

    public EventBean(String start_time_, String end_time_, String name_, String type_, String place_, int color_, int eventid_) {
        this.start_time_ = start_time_;
        this.end_time_ = end_time_;
        this.name_ = name_;
        this.type_ = type_;
        this.place_ = place_;
        this.color_ = color_;
        this.eventid_ = eventid_;
    }

    public int getEventid_() {
        return eventid_;
    }

    public void setEventid_(int eventid_) {
        this.eventid_ = eventid_;
    }

    public void setColor_(int color_) {
        this.color_ = color_;
    }

    public void setStart_time_(String start_time_) {
        this.start_time_ = start_time_;
    }

    public void setEnd_time_(String end_time_) {
        this.end_time_ = end_time_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public void setPlace_(String place_) {
        this.place_ = place_;
    }

    public String getStart_time_() {
        return start_time_;
    }

    public String getEnd_time_() {
        return end_time_;
    }

    public String getName_() {
        return name_;
    }

    public String getType_() {
        return type_;
    }

    public String getPlace_() {
        return place_;
    }

    public int getColor_() {
        return color_;
    }
}
