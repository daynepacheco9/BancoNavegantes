package com.desktopapp;

import java.net.URL;
import java.util.List;
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

    @FXML
    protected void tryLogin()throws Exception {

        Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(tfLogin.getText());

        if (!regexCPF.matches()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF inválido!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String cpf = regexCPF.group().replaceAll("[^0-9]", "");

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query queryCPF = session
                .createQuery("from UserData u where u.usercpf = :cpf");
        queryCPF.setParameter("cpf", cpf);
        List<UserData> CPFs = queryCPF.list();

        transaction.commit();

        if (CPFs.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF não cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        UserData loggedUser = CPFs.get(0);

        if (!loggedUser.getUserpass().equals(pfSenha.getText())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Senha incorreta!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Stage crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene(loggedUser);
        stage.setScene(scene);
        stage.show();
    }

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