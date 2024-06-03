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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TransferenciaController {

    private UserData user;

    public void setUser(UserData user) {
        this.user = user;
    }

    public UserData getUser() {
        return this.user;
    }

    TransferenciaController controller;

    public TransferenciaController getController() {
        return controller;
    }

    public void setController(TransferenciaController controller) {
        this.controller = controller;
    }

    public static Scene CreateScene(UserData loggedUser) throws Exception {

        URL sceneUrl = TransferenciaController.class
                .getResource("Transferencia.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        TransferenciaController controller = loader.getController();

        controller.setUser(loggedUser);
        controller.setController(controller);

        return scene;
    }

    @FXML
    protected TextField tfCPFDes;

    @FXML
    protected TextField tfCPFUsu;

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField pfSenha;

    @FXML
    protected Button btTransferir;

    @FXML
    protected Button btVoltar;

    @FXML
    protected void transferir(ActionEvent e) throws Exception {

        Matcher regexCPFDes = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(tfCPFDes.getText());
        if (!regexCPFDes.matches()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF do destinatário inválido!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Matcher regexCPFUsu = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(tfCPFUsu.getText());
        if (!regexCPFUsu.matches()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF do usuário  inválido!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        } if (!regexCPFUsu.group().replaceAll("[^0-9]", "").equals(this.getUser().getUsercpf())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF do usuário  inválido!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

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

        if ((this.getUser().getUserbalance() - Double.parseDouble(tfValor.getText())) < 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Saldo insuficiente!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        String cpf = regexCPFDes.group().replaceAll("[^0-9]", "");

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query queryCPF = session
                .createQuery("from UserData u where u.usercpf = :cpf");
        queryCPF.setParameter("cpf", cpf);
        List<UserData> CPFs = queryCPF.list();


        if (CPFs.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF do destinatário não encontrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        UserData destinatario = CPFs.get(0);

        this.getUser().setUserbalance(this.getUser().getUserbalance() - Double.parseDouble(tfValor.getText()));
        destinatario.setUserbalance(destinatario.getUserbalance() + Double.parseDouble(tfValor.getText().replace(",", ".")));

        session.merge(this.getUser());
        session.merge(destinatario);

        transaction.commit();

        Stage crrStage = (Stage) btVoltar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeController.CreateScene(this.getUser());
        stage.setScene(scene);
        stage.show();
    }

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