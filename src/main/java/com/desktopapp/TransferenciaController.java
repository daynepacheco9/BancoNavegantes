package com.desktopapp;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TransferenciaController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = TransferenciaController.class
                .getResource("Transferencia.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TextField tfCPFDes;

    @FXML
    protected TextField tfCPFUsu;

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField ptSenha;

    @FXML
    protected Button btTransferir;

    @FXML
    protected Button btVoltar;

    @FXML
    protected void voltar(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btVoltar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    
}