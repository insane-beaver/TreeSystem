package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Universal implements Serializable {
    private ArrayList<Question> qlist = new ArrayList<>();
    private ArrayList<Logic> llist = new ArrayList<>();

    public Universal(ArrayList<Question> qlist, ArrayList<Logic> llist) {
        this.qlist = qlist;
        this.llist = llist;
    }

    public ArrayList<Question> getQlist() {
        return qlist;
    }
    public void setQlist(ArrayList<Question> qlist) {
        this.qlist = qlist;
    }

    public ArrayList<Logic> getLlist() {
        return llist;
    }
    public void setLlist(ArrayList<Logic> llist) {
        this.llist = llist;
    }
}
