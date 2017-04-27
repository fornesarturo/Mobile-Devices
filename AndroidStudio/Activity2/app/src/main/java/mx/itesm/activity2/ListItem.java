package mx.itesm.activity2;

/**
 * Created by forne on 17/02/2017.
 */

public class ListItem {
    private String head;
    private String description;

    public ListItem(String head, String description){
        this.head = head;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getHead() {
        return head;
    }
}
