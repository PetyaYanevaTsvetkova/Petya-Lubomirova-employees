module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.javafxtask.employee_scores to javafx.fxml;
    exports com.javafxtask.employee_scores;
    exports com.javafxtask.employee_scores.service;
    opens com.javafxtask.employee_scores.service to javafx.fxml;
    exports com.javafxtask.employee_scores.model;
    opens com.javafxtask.employee_scores.model to javafx.fxml;
}