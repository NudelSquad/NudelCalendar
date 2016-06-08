package nudelsquad.nudelcalendar.search;

/**
 * Created by waser2 on 08.06.2016.
 */
public class SearchItem {
    private int id_;
    private String name_;
    private String date_;
    private Boolean is_a_task_;

    public SearchItem(int id_, String name_, String date_, Boolean is_a_task_) {
        this.id_ = id_;
        this.name_ = name_;
        this.date_ = date_;
        this.is_a_task_ = is_a_task_;
    }

    public int getId_() {
        return id_;
    }

    public String getName_() {
        return name_;
    }

    public String getDate_() {
        return date_;
    }

    public Boolean getIs_a_task_() {
        return is_a_task_;
    }
}
