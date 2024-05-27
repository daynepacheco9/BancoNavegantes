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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginController.class
                .getResource("Login.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected TextField tfLogin;
    @FXML
    protected PasswordField pfSenha;
    @FXML
    protected Button btEntrar;
    @FXML
    protected Button btCadastro;

    // @FXML
    // protected void tryLogin(ActionEvent e) throws Exception {
    // Authentification auth = Authentification.tryLogin(
    // tfLogin.getText(), pfSenha.getText());
    // if (!auth.userExists()) {
    // Alert alert = new Alert(
    // AlertType.ERROR,
    // "Usuário inexistente.",
    // ButtonType.OK);
    // alert.showAndWait();
    // return;
    // }
    // if (auth.getUser() == null) {
    // Alert alert = new Alert(
    // AlertType.ERROR,
    // "Senha incorreta.",
    // ButtonType.OK);
    // alert.showAndWait();
    // return;
    // }
    // }

    @FXML
    protected void cadastro(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = CadastrarController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

}