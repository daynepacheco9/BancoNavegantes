package com.desktopapp;

import java.net.URL;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

        Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(tfCPF.getText());
        Matcher regexPhone = Pattern.compile("^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$").matcher(tfFone.getText());
        Matcher regexEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(tfEmail.getText());

        if (!regexCPF.matches()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "CPF inválido!",
                ButtonType.OK);
            alert.showAndWait();
            return;
        } if (!regexPhone.matches()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "Telefone inválido!",
                ButtonType.OK);
            alert.showAndWait();
            return;
        } if (!regexEmail.matches()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "E-mail inválido!",
                ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        UserData newUser = new UserData(tfNome.getText(), pfSenha.getText(), regexCPF.group().replaceAll("[^0-9]", ""),
                Date.valueOf(dpData.getValue()), tfFone.getText(), tfEmail.getText());
        session.save(newUser);

        transaction.commit();

        Stage crrStage = (Stage) btCadastrar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = LoginController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

}
