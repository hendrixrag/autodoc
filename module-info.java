/*
 *Información de modulos autogenerada
 */

module autodoc {
    requires javafx.swt;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires com.jfoenix;
    requires mysql.connector.java;
    requires java.sql;
    
    opens org.controlador to javafx.fxml;
    exports org.controlador;
}
