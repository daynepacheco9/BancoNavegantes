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

public class CadastrarController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = CadastrarController.class
                .getResource("Cadastrar.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TextField tfNome;

    @FXML
    protected TextField tfCPF;

    @FXML
    protected DatePicker dpData;

    @FXML
    protected TextField tfFone;

    @FXML
    protected TextField tfEmail;

    @FXML
    protected PasswordField pfSenha;

    @FXML
    protected PasswordField pfCSenha;

    @FXML
    protected Button btCadastrar;

    @FXML
    protected void cadastrar(ActionEvent e) throws Exception {
        Stage crrStage = (Stage) btCadastrar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = LoginController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

}
