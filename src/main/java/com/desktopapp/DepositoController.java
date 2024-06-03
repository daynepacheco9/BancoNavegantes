package com.desktopapp;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.desktopapp.model.UserData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DepositoController {

    private UserData user;
    
    public void setUser(UserData user) {
        this.user = user;
    }

    public UserData getUser() {
        return this.user;
    }

    DepositoController controller;

    public DepositoController getController() {
        return controller;
    }

    public void setController(DepositoController controller) {
        this.controller = controller;
    }

    public static Scene CreateScene(UserData loggedUser) throws Exception {
        URL sceneUrl = DepositoController.class
                .getResource("Deposito.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        DepositoController controller = loader.getController();

        controller.setUser(loggedUser);
        controller.setController(controller);
          
        return scene;
    }

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField pfSenha;

    @FXML
    protected Button btDepositar;

    @FXML
    protected Button btVoltar;

    @FXML
    protected void depositar(ActionEvent e) throws Exception {

        Matcher regexValue = Pattern.compile("^\\d+(?:[\\.,]\\d{1,2})?$").matcher(tfValor.getText());

        if (tfValor.getText().isEmpty() || tfValor.getText() == null) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Insira um valor!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        } if (!regexValue.matches()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Insira um valor válido!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (!this.getUser().getUserpass().equals(pfSenha.getText())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Senha incorreta!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        this.getUser().setUserbalance(this.getUser().getUserbalance() + Double.parseDouble(tfValor.getText().replace(",", ".")));

        session.merge(this.getUser());
        transaction.commit();

        Stage crrStage = (Stage) btDepositar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene(this.getUser());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void voltar(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btDepositar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene(this.getUser());
        stage.setScene(scene);
        stage.show();
    }
    
}