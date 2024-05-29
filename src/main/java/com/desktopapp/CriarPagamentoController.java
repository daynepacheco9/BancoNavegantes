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

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CriarPagamentoController {

        private UserData user;

        public void setUser(UserData user) {
                this.user = user;
        }

        public UserData getUser() {
                return this.user;
        }

        public static Scene CreateScene(UserData loggedUser) throws Exception {
                URL sceneUrl = CriarPagamentoController.class
                                .getResource("CriarPagamento.fxml");

                FXMLLoader loader = new FXMLLoader(sceneUrl);
                Scene scene = new Scene(loader.load());
                CriarPagamentoController controller = loader.getController();
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
        protected Button btCriar;

        @FXML
        protected Button btVoltar;

        @FXML
        protected void criar(ActionEvent e) throws Exception {
                Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
                                .matcher(tfCPF.getText());

                if (!regexCPF.matches()) {
                        Alert alert = new Alert(
                                        AlertType.ERROR,
                                        "CPF inválido!",
                                        ButtonType.OK);
                        alert.showAndWait();
                        return;
                }

                Session session = HibernateUtil
                                .getSessionFactory()
                                .getCurrentSession();
                Transaction transaction = session.beginTransaction();

                Query queryCPF = session
                                .createQuery("from UserData u where u.usercpf = :cpf");
                queryCPF.setParameter("cpf", regexCPF.group().replaceAll("[^0-9]", ""));
                List<UserData> CPFs = queryCPF.list();

                if (CPFs.size() == 0) {
                        Alert alert = new Alert(
                                        AlertType.ERROR,
                                        "CPF não cadastrado!",
                                        ButtonType.OK);
                        alert.showAndWait();
                        transaction.commit();
                        return;
                }

                UserData dividendo = CPFs.get(0);

                if (!this.getUser().getUserpass().equals(pfSenha.getText())) {
                        Alert alert = new Alert(
                                        AlertType.ERROR,
                                        "Senha incorreta!",
                                        ButtonType.OK);
                        alert.showAndWait();
                        return;
                }

                PagamentosData newPag = new PagamentosData(tfCodigo.getText(), Double.parseDouble(tfValor.getText()),
                                this.getUser(), dividendo);



                session.save(newPag);

                transaction.commit();

                Stage crrStage = (Stage) btCriar
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