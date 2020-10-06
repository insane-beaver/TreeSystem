package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private int imdex;
    private String text;
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    public Question() {
    }
    public Question(int index, String text, ArrayList<Answer> answers) {
        this.imdex = index;
        this.text = text;
        this.answers = answers;
    }

    public int getImdex() {
        return imdex;
    }
    public void setImdex(int imdex) {
        this.imdex = imdex;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
