package com.desktopapp;

import com.desktopapp.model.PagamentosData;
import com.desktopapp.model.UserData;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                .getResource("Pagamento.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        PagamentoController controller = loader.getController();

        controller.setUser(loggedUser);

        return scene;
    }

    @FXML
    protected TextField tfCodigo;

    @FXML
    protected TextField tfCPF;

    @FXML
    protected TextField tfValor;

    @FXML
    protected PasswordField pfSenha;

    @FXML
    protected Button btPagar;

    @FXML
    protected Button btVoltar;

    @FXML
    protected void pagar(ActionEvent e) throws Exception {
        Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
                .matcher(tfCPF.getText());
        Matcher regexCodigo = Pattern.compile("^\\d{8,}$")
                .matcher(tfCodigo.getText());
        Matcher regexValue = Pattern.compile("^\\d+(?:[\\.,]\\d{1,2})?$").matcher(tfValor.getText());

        
        if (!regexCPF.matches()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "CPF inválido!",
                ButtonType.OK);
                alert.showAndWait();
                return;
            }
            
        String cpf = regexCPF.group().replaceAll("[^0-9]", "");
        
        if (tfValor.getText().isEmpty() || tfValor.getText() == null) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Insira um valor!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (!regexValue.matches()) {
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

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query queryCodigo = session
                .createQuery("from PagamentosData p where p.codigo = :codigo");
        queryCodigo.setParameter("codigo", tfCodigo.getText());
        List<PagamentosData> Codigo = queryCodigo.list();

        if (Codigo.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Codigo não existe!",
                    ButtonType.OK);
            alert.showAndWait();
            transaction.commit();
            return;
        }

        PagamentosData codigo = Codigo.get(0);

        if (codigo.isPaid() == true) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Já foi pago!",
                    ButtonType.OK);
            alert.showAndWait();
            transaction.commit();
            return;
        }

        if (!codigo.getIdcliente().getUsercpf().equals(cpf)) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "CPF Incorreto",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (Double.parseDouble(tfValor.getText().replace(",", ".")) != codigo.getValor()) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Valor inválido",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        this.getUser().setUserbalance(this.getUser().getUserbalance() - Double.parseDouble(tfValor.getText()));
        codigo.getIdcliente()
                .setUserbalance(codigo.getIdcliente().getUserbalance() + Double.parseDouble(tfValor.getText()));

        codigo.setPaid(true);
        session.merge(this.getUser());
        session.merge(codigo.getIdcliente());
        session.merge(codigo);

        transaction.commit();

        Stage crrStage = (Stage) btPagar
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