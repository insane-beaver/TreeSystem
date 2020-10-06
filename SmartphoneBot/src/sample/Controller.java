package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controller {
    public static int index = 0;
    public static Question current_question = null;
    public static boolean edit = false;
    public static ArrayList<Question> QuestionList = new ArrayList<>();
    public static ArrayList<Logic> LogicList = new ArrayList<>();
    public static Boolean done = false, done1 = false;
    private ArrayList<String> stringArrayList = new ArrayList<>();

    public MenuItem open_quest;
    public MenuItem open_log;
    public MenuItem save_quest;
    public MenuItem save_log;
    public Button add_quest;
    public ListView<String> Questions;
    public ListView<String> Logics;
    public ChoiceBox first_pos;
    public ChoiceBox second_pos;
    public ChoiceBox selected_log;

    public void Refresh(MouseEvent mouseEvent) {
        if(done) {
            done = false;
            Refresh_list();
        }
        if(done1) {
            done1 = false;
            Refresh_logic();
        }
    }

    private void Refresh_list()
    {
        stringArrayList.clear();
        for (Question question: QuestionList) {
            ArrayList<Answer> answers = question.getAnswers();
            String str = question.getImdex()+": "+question.getText()+":   "+answers.get(0).getText()+" || "+answers.get(1).getText();
            stringArrayList.add(str);
        }
        ObservableList<String> questions = FXCollections.observableArrayList(stringArrayList);
        Questions.setItems(questions);
        ArrayList<Integer> items = new ArrayList<Integer>();
        for(Integer i=0; i<QuestionList.size();i++)
            items.add(i);
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(items);

        first_pos.setItems(integerObservableList);
        second_pos.setItems(integerObservableList);

    }

    private void Refresh_logic() {
        stringArrayList.clear();
        int item=0;
        for (Logic logic: LogicList) {
            String str = item+": Батько: "+logic.getParent()+"   Сини: "+logic.getChild1()+" "+logic.getChild2();
            stringArrayList.add(str);
            item++;
        }
        ObservableList<String> logics = FXCollections.observableArrayList(stringArrayList);
        Logics.setItems(logics);

        ArrayList<Integer> items = new ArrayList<Integer>();
        for(Integer i=0; i<LogicList.size();i++)
            items.add(i);
        ObservableList<Integer> integerObservableList = FXCollections.observableArrayList(items);
        selected_log.setItems(integerObservableList);
    }


    public void initialize() {
    }

    public void AddQuest(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/add_question.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Нове запитання");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void CreateLogic(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/create_logic.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Логіка");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void SaveQuest(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Питання програми", "*.zap"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showSaveDialog(add_quest.getScene().getWindow());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(QuestionList);

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Файл " + file.getName() + " було успішно збережено",
                    "Готово",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void SaveLog(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Логіка програми", "*.zalo"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showSaveDialog(add_quest.getScene().getWindow());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(LogicList);

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Файл " + file.getName() + " було успішно збережено",
                    "Готово",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void SaveAll(ActionEvent actionEvent) {
        Universal universal = new Universal(QuestionList,LogicList);

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Універсальний файл", "*.uql"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showSaveDialog(add_quest.getScene().getWindow());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(universal);

            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Файл " + file.getName() + " було успішно збережено",
                    "Готово",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void OpenQuest(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Питання програми", "*.zap"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showOpenDialog(add_quest.getScene().getWindow());
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            QuestionList = (ArrayList<Question>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        index = QuestionList.get(QuestionList.size()-1).getImdex()+1;
        Refresh_list();
    }

    public void OpenLog(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Логіка програми", "*.zalo"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showOpenDialog(add_quest.getScene().getWindow());
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            LogicList = (ArrayList<Logic>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void OpenAll(ActionEvent actionEvent) {
        Universal universal = null;
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Універсальний файл", "*.uql"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*"));

        File file = fileChooser.showOpenDialog(add_quest.getScene().getWindow());
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            universal = (Universal) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        QuestionList = universal.getQlist();
        LogicList = universal.getLlist();

        index = QuestionList.get(QuestionList.size()-1).getImdex()+1;
        Refresh_list();
        Refresh_logic();
    }

    public void RefreshPos(ActionEvent actionEvent) {
        int fi, si, index1;
        Question question;
        fi = (int) first_pos.getSelectionModel().getSelectedItem();
        si = (int) second_pos.getSelectionModel().getSelectedItem();
        question = QuestionList.get(fi);
        QuestionList.set(fi,QuestionList.get(si));
        QuestionList.set(si,question);

        index1=QuestionList.get(fi).getImdex();
        QuestionList.get(fi).setImdex(QuestionList.get(si).getImdex());
        QuestionList.get(si).setImdex(index1);

        Refresh_list();
    }

    public void Delete(ActionEvent actionEvent) {
        int i = (int) first_pos.getSelectionModel().getSelectedItem();
        QuestionList.remove(i);

        Refresh_list();
    }

    public void StartTest(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/testing.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Тестування");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void Edt_Quest(ActionEvent actionEvent) {
        edit=true;
        int i = (int) first_pos.getSelectionModel().getSelectedItem();
        current_question = QuestionList.get(i);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/add_question.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Нове запитання");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void DeleteLog(ActionEvent actionEvent) {
        int i = (int) selected_log.getSelectionModel().getSelectedItem();
        LogicList.remove(i);

        Refresh_logic();
    }
}
