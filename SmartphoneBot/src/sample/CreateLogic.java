package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class CreateLogic {
    private ArrayList<Question> arrayList = new ArrayList<>();
    public ChoiceBox par;
    public ChoiceBox ch1;
    public ChoiceBox ch2;
    public Label done_label;

    public void initialize() {
        arrayList = Controller.QuestionList;
        ArrayList<Integer> items = new ArrayList<Integer>();
        for(Integer i=0; i<arrayList.size();i++)
            items.add(i);
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(items);

        par.setItems(integerObservableList);
        ch1.setItems(integerObservableList);
        ch2.setItems(integerObservableList);

    }

    public void Done(ActionEvent actionEvent) {
        int i = (int) par.getSelectionModel().getSelectedItem();
        int ia = (int) ch1.getSelectionModel().getSelectedItem();
        int ib = (int) ch2.getSelectionModel().getSelectedItem();

        Logic logic = new Logic(i,ia,ib);
        Controller.LogicList.add(logic);
        Controller.done1=true;

        done_label.setText("Батько: "+i+"   Сини: "+ia+" "+ib);

    }
}
