package com.desktopapp;

import java.net.URL;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import org.hibernate.Query;

import com.desktopapp.model.PagamentosData;
import com.desktopapp.model.UserData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PagamentoExiController {

    private UserData user;

    public void setUser(UserData user) {
        this.user = user;
    }

    public UserData getUser() {
        return this.user;
    }

    public static Scene CreateScene(UserData loggedUser) throws Exception {
        URL sceneUrl = PagamentoExiController.class
                .getResource("PagamentoExi.fxml");

        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Scene scene = new Scene(loader.load());
        PagamentoExiController controller = loader.getController();
        controller.setUser(loggedUser);

        ObservableList<PagamentosItem> list = FXCollections.observableArrayList();

        TableColumn<PagamentosItem, String> codigoCol = new TableColumn<>("Código");
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<PagamentosItem, String> nomeCol = new TableColumn<>("Dividendo");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<PagamentosItem, Double> valorCol = new TableColumn<>("Valor");
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<PagamentosItem, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("paid"));

        TableColumn deleteCol = new TableColumn("Excluir");
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("del"));

        Callback<TableColumn<PagamentosItem, String>, TableCell<PagamentosItem, String>> cellFactory = new Callback<TableColumn<PagamentosItem, String>, TableCell<PagamentosItem, String>>() {

            @Override
            public TableCell<PagamentosItem, String> call(TableColumn<PagamentosItem, String> param) {

                TableCell<PagamentosItem, String> cellBt = new TableCell<PagamentosItem, String>() {
                    Button bt = new Button("deletar");

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            bt.setOnAction(e -> {
                                PagamentosItem colitem = getTableView().getItems().get(getIndex());
                                String codigo = colitem.getCodigo();

                                list.remove(getIndex());

                                Session session = HibernateUtil
                                        .getSessionFactory()
                                        .getCurrentSession();
                                Transaction transaction = session.beginTransaction();

                                Query queryPag = session
                                        .createQuery("from PagamentosData u where u.codigo = :cod");
                                queryPag.setParameter("cod", codigo);
                                List<PagamentosData> exData = queryPag.list();

                                session.delete(exData.get(0));

                                transaction.commit();
                            });

                            super.updateItem(item, empty);
                            setGraphic(bt);
                            setText(null);
                        }
                    };
                };

                return cellBt;
            }
        };
        deleteCol.setCellFactory(cellFactory);

        controller.tbPag.getColumns().addAll(
                codigoCol, nomeCol, valorCol, statusCol, deleteCol);

        Session session = HibernateUtil
                .getSessionFactory()
                .getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query queryPag = session
                .createQuery("from PagamentosData u where u.idcliente = :id");
        queryPag.setParameter("id", loggedUser);

        List<PagamentosData> pagamentosData = queryPag.list();

        transaction.commit();

        for (PagamentosData pags : pagamentosData) {
            list.add(new PagamentosItem(pags));
        }

        controller.tbPag.setItems(list);

        return scene;
    }

    @FXML
    protected TableView<PagamentosItem> tbPag;

    @FXML
    protected Button btVoltar;

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