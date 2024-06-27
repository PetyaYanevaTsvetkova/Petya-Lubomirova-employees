package com.javafxtask.employee_scores;

import com.javafxtask.employee_scores.model.Result;
import com.javafxtask.employee_scores.service.Service;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RunnerUISolution extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Form");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        TextField textField = new TextField();
        gridPane.add(textField, 0, 0);

        Button submitButton = new Button("Submit path");
        gridPane.add(submitButton, 1, 0);

        submitButton.setOnAction(event -> {
            String path = textField.getText();
            Result result = Service.createResult(path);
            System.out.println(result);

            Label label = new Label("Result:");
            Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
            label.setFont(font);

            TableView<Object> table = new TableView<>();

            TableColumn firstEmployeeId = new TableColumn("#1EmployeeId");
            firstEmployeeId.setCellValueFactory(new PropertyValueFactory<>("firstEmployeeId"));

            TableColumn secondEmployeeId = new TableColumn("#2EmployeeId");
            secondEmployeeId.setCellValueFactory(new PropertyValueFactory<>("secondEmployeeId"));

            TableColumn projectId = new TableColumn("ProjectId");
            projectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));

            TableColumn daysWorked = new TableColumn("DaysWorked");
            daysWorked.setCellValueFactory(new PropertyValueFactory<>("daysWorked"));

            daysWorked.setPrefWidth(100);

            table.getColumns().addAll(firstEmployeeId, secondEmployeeId, projectId, daysWorked);

            table.setMaxSize(350, 250);

            ObservableList<Object> data = FXCollections.observableArrayList();
            Result result1 = Service.createResult(path);
            data.add(result1);

            table.setItems(data);

            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 50, 50, 60));
            vbox.getChildren().addAll(label, table);

            Scene scene = new Scene(vbox, 595, 230);
            stage.setTitle("Employees who worked on common project longest period");
            stage.setScene(scene);
            stage.show();
        });

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);

        stage.show();
    }
}
