package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class Testing {
    private ArrayList<Question> arrayList = new ArrayList<>();
    private Question current_question;
    private int i=0,j=0;

    public Label question;
    public ToggleGroup answers;
    public RadioButton answer1;
    public RadioButton answer2;
    public Button done;
    public Label result;

    public void initialize()
    {
        arrayList = Controller.QuestionList;

        Load_Question();
    }

    public void Done(ActionEvent actionEvent) {
        Logic logic = Search_logic();

        if (answer1.isSelected())
            i = logic.getChild1();
        if (answer2.isSelected())
            i = logic.getChild2();

        boolean cont = false;
        for(Logic item:Controller.LogicList) {
            if(item.IsParent(i))
                cont = true;
        }
        if (cont)
            Load_Question();
        else {
            current_question = arrayList.get(i);
            question.setText("Відповідно до ваших відповідей, найкращим смартфоном для вас буде:");
            result.setVisible(true);
            result.setText(current_question.getText());
            answer1.setVisible(false);
            answer2.setVisible(false);
            done.setDisable(true);
        }

    }

    private void Load_Question()
    {
        current_question = arrayList.get(i);
        ArrayList<Answer> answers = current_question.getAnswers();

        question.setText(current_question.getText());
        answer1.setText(answers.get(0).getText());
        answer2.setText(answers.get(1).getText());
    }

    private Logic Search_logic()
    {
        for(Logic logic:Controller.LogicList)
        {
            if(i==logic.getParent())
                return logic;
        }

        return null;
    }
}
