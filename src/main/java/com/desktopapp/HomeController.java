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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeController {

    private UserData user;
    
    public void setUser(UserData user) {
        this.user = user;
    }

    public static Scene CreateScene(UserData loggedUser) throws Exception {
        URL sceneUrl = HomeController.class
                .getResource("Home.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        HomeController controller = loader.getController();

        controller.setLbUsuario(loggedUser.getUsername());
        controller.setUser(loggedUser);
        // controller.setLbSaldo("R$ " + String.format("%.2d",loggedUser.getUserbalance()));
       
        return scene;
    }

    @FXML
    protected Label lbUsuario;

    @FXML
    protected Label lbSaldo;

    @FXML
    protected Button btVisualizar;

    @FXML
    protected Button btPagamento;

    @FXML
    protected Button btDeposito;

    @FXML
    protected Button btTransferencia;

    @FXML
    protected Button btPagExi;

    @FXML
    protected Button btCriar;

    @FXML
    protected Button btSair;

    @FXML
    protected void transferencia(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btTransferencia
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = TransferenciaController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void sair(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btSair
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = LoginController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void deposito(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btDeposito
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = DepositoController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void criar(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btCriar
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = CriarPagamentoController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void pagExi(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btPagExi
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = PagamentoExiController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void pagamento(ActionEvent e) throws Exception {

        Stage crrStage = (Stage) btPagamento
                .getScene().getWindow();
        crrStage.close();

        Stage stage = new Stage();
        Scene scene = PagamentoController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }



    public Label getLbUsuario() {
        return lbUsuario;
    }

    public void setLbUsuario(String usuario) {
        this.lbUsuario.setText(usuario);
    }

    public Label getLbSaldo() {
        return lbSaldo;
    }

    public void setLbSaldo(String saldo) {
        this.lbSaldo.setText(saldo);
    }

    public Button getBtVisualizar() {
        return btVisualizar;
    }

    public void setBtVisualizar(Button btVisualizar) {
        this.btVisualizar = btVisualizar;
    }

    public Button getBtPagamento() {
        return btPagamento;
    }

    public void setBtPagamento(Button btPagamento) {
        this.btPagamento = btPagamento;
    }

    public Button getBtDeposito() {
        return btDeposito;
    }

    public void setBtDeposito(Button btDeposito) {
        this.btDeposito = btDeposito;
    }

    public Button getBtTransferencia() {
        return btTransferencia;
    }

    public void setBtTransferencia(Button btTransferencia) {
        this.btTransferencia = btTransferencia;
    }

    public Button getBtPagExi() {
        return btPagExi;
    }

    public void setBtPagExi(Button btPagExi) {
        this.btPagExi = btPagExi;
    }

    public Button getBtSair() {
        return btSair;
    }

    public void setBtSair(Button btSair) {
        this.btSair = btSair;
    }

    

}