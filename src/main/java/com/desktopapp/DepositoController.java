package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.UserData;

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

public class DepositoController {
    public static Scene CreateScene(UserData loggedUser) throws Exception {
        URL sceneUrl = DepositoController.class
                .getResource("Deposito.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        HomeController controller = loader.getController();

        controller.setLbUsuario(loggedUser.getUsername());
       
        return scene;
    }

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField ptSenha;

    @FXML
    protected Button btDepositar;

    @FXML
    protected Button btVoltar;

    // @FXML
    // protected void voltar(ActionEvent e) throws Exception {

    //     Stage crrStage = (Stage) btVoltar
    //             .getScene().getWindow();
    //     crrStage.close();

    //     Stage stage = new Stage();
    //     Scene scene = HomeController.CreateScene();
    //     stage.setScene(scene);
    //     stage.show();
    // }
    
}