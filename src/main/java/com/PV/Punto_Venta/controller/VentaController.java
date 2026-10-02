package com.PV.Punto_Venta.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class VentaController {

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private Label LabelNameTiket;

    @FXML
    private Label LabelTotalProducts;

    @FXML
    private Label LabelTotalSale;

    @FXML
    private TabPane TabPane;

    @FXML
    private TextField TextFieldCode;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnArtCommon;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnCharge;

    @FXML
    private Button btnDelateArt;

    @FXML
    private Button btnDelateTiket;

    @FXML
    private Button btnInsV;

    @FXML
    private Button btnPending;

    @FXML
    private Button btnSearch;



    public void initialize() {
        if(TabPane.getTabs().isEmpty()) {CreateNewTiket();}
        CreatenewTiketF6();
    }


    //--------------- Ventana/Tab ---------------
    @FXML
    public void CreateNewTab(ActionEvent event){
        if(TabPane.getTabs().isEmpty()) {
            CreateNewTiket();
            ModalNewTicketName();
        }else{
            ModalNewTicketName();
            CreateNewTiket();
        }
    }

    public void CreatenewTiketF6(){
        Platform.runLater(() -> {
            TabPane.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F6) {
                    if(TabPane.getTabs().isEmpty()) {
                        CreateNewTiket();
                        ModalNewTicketName();
                    }else{
                        ModalNewTicketName();
                        CreateNewTiket();
                    }
                }
            });
        });
    }

    public void CreateNewTiket(){
        Tab newTab = new Tab("Ticket "+ (TabPane.getTabs().size() + 1));
        newTab.setClosable(false);
        TableView<Object> tableView = createTableView();

        newTab.setContent(tableView);
        TabPane.getTabs().add(newTab);
        TabPane.getSelectionModel().select(newTab);
    }

    public void ModalNewTicketName(){
        String defaultName = TabPane.getSelectionModel().getSelectedItem().getText();

        TextInputDialog dialog = new TextInputDialog(defaultName);
        dialog.setTitle("Nuevo Ticket");
        dialog.setHeaderText("Nombre para la venta actual");
        dialog.setContentText("Ingrese el nombre del ticket:");

        Stage mainStage = (Stage) TabPane.getScene().getWindow();
        dialog.initOwner(mainStage);

        String cssPath = getClass().getResource("/css/InicioStyle.css").toExternalForm();
        dialog.getDialogPane().getStylesheets().add(cssPath);

        Optional<String> result = dialog.showAndWait();

        String ticketName = defaultName;
        if (result.isPresent() && !result.get().trim().isEmpty()) {
            ticketName = result.get().trim();
        }

        TabPane.getSelectionModel().getSelectedItem().setText(ticketName);
    }

    private TableView<Object> createTableView(){
        TableView<Object> tableView = new TableView<>();

        TableColumn<Object, String> colCode = new TableColumn<>("CODIGO");
        colCode.setPrefWidth(124.8);
        colCode.setCellValueFactory(new PropertyValueFactory<>("co"));

        TableColumn<Object, String> colName = new TableColumn<>("NOMBRE");
        colName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colName.setPrefWidth(251.2);

        TableColumn<Object, String> colAmount = new TableColumn<>("CANTIDAD");
        colAmount.setPrefWidth(117.6);

        TableColumn<Object, String> colPrice = new TableColumn<>("PRECIO");
        colPrice.setPrefWidth(126.4);

        TableColumn<Object, String> colTotal = new TableColumn<>("TOTAL");
        colTotal.setPrefWidth(136.8);

        TableColumn<Object, String> colVoid1 = new TableColumn<>("");
        TableColumn<Object, String> colVoid2 = new TableColumn<>("");
        TableColumn<Object, String> colVoid3 = new TableColumn<>("");

        tableView.getColumns().addAll(colCode, colName, colAmount, colPrice, colTotal);
        tableView.setItems(FXCollections.observableArrayList());
        return tableView;
    }

    @FXML
    public void DelateTab(ActionEvent event){
        TabPane.getTabs().remove(TabPane.getSelectionModel().getSelectedIndex());
    }
    //--------------------------------------------
}
