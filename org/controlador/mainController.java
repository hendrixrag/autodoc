/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HENDRIX ARRIETA - Ing de Sistemas UNEFA 
 */
package org.controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class mainController extends Application {

    private double xOffSet;
    private double yOffSet;

    @Override
    public void start(Stage primaryStage) {
        /*Parent root = FXMLLoader.load(getClass().getResource("nprincipal-view.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX 13");
        stage.setScene(scene);
        stage.show();*/
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainController.class.getResource("/org/vista/principal-view.fxml"));
            Pane ventana = loader.load();
            
            Parent root = loader.getRoot();
            
            //Metodo para mover la ventana de la aplicación
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    yOffSet = event.getSceneY();
                    xOffSet = event.getSceneX();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setY(event.getScreenY()- yOffSet);
                    primaryStage.setX(event.getScreenX()- xOffSet);
                }
            });
            //***********************************************
            
            Scene scene = new Scene(ventana);
            scene.getStylesheets().add(getClass().getResource("/org/vista/button.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("AutoDoc - Automatizar Documentación");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
