package sample;

import java.io.Serializable;

public class Logic implements Serializable {
    private int parent, child1, child2;

    public Logic(int parent, int child1, int child2) {
        this.parent = parent;
        this.child1 = child1;
        this.child2 = child2;
    }

    public int getParent() {
        return parent;
    }
    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getChild1() {
        return child1;
    }
    public void setChild1(int child1) {
        this.child1 = child1;
    }

    public int getChild2() {
        return child2;
    }
    public void setChild2(int child2) {
        this.child2 = child2;
    }

    public Boolean IsParent(int parent)
    {
        if (this.parent==parent) return true;
        else return false;
    }
}
