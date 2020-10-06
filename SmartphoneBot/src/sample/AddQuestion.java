package sample;


import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddQuestion {
    private Question quest;
    private ArrayList<Answer> answs = new ArrayList<Answer>();

    public TextArea question;
    public TextField answer1;
    public TextField answer2;

    public void initialize() {
        if(Controller.edit)
        {
            question.setText(Controller.current_question.getText());
            ArrayList<Answer> item = Controller.current_question.getAnswers();
            answer1.setText(item.get(0).getText());
            answer2.setText(item.get(1).getText());
        }
    }

    public void Done(ActionEvent actionEvent) {
        String str = answer1.getText();
        Answer a = new Answer(1, str);
        answs.add(a);

        str = answer2.getText();
        a = new Answer(2, str);
        answs.add(a);

        str = question.getText();

        if(Controller.edit) {
            quest = new Question(Controller.current_question.getImdex(),str,answs);
            Controller.QuestionList.set(Controller.current_question.getImdex(),quest);
            Controller.edit = false;
        }
        else {
            quest = new Question(Controller.index, str, answs);
            Controller.index++;
            Controller.QuestionList.add(quest);
        }
        Controller.done = true;
        Stage stage = (Stage) question.getScene().getWindow();
        stage.close();
    }

}
