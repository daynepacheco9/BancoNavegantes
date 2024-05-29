package com.desktopapp;

import com.desktopapp.model.UserData;

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

public class PagamentoController {
    private UserData user;

    public void setUser(UserData user) {
        this.user = user;
    }

    public UserData getUser() {
        return this.user;
    }

    public static Scene CreateScene(UserData loggedUser) throws Exception {
        URL sceneUrl = PagamentoController.class
                        .getResource("CriarPagamento.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        PagamentoController controller = loader.getController();
        

        return scene;
}

    @FXML
    protected TextField tfCodigo;

    @FXML
    protected TextField tfCPF;

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField ptSenha;

    @FXML
    protected Button btPagar;

    @FXML
    protected Button btVoltar;

    // @FXML
    // protected void voltar(ActionEvent e) throws Exception {

    // Stage crrStage = (Stage) btVoltar
    // .getScene().getWindow();
    // crrStage.close();

    // Stage stage = new Stage();
    // Scene scene = HomeController.CreateScene();
    // stage.setScene(scene);
    // stage.show();
    // }

    @FXML
        protected void voltar(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btVoltar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene(this.getUser());
        stage.setScene(scene);
        stage.show();
        }
}