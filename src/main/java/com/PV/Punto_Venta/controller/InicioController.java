package com.PV.Punto_Venta.controller;

import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import javax.swing.*;

@Controller
public class InicioController {

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private TableColumn<?, ?> CTableAmount;

    @FXML
    private TableColumn<?, ?> CTableCode;

    @FXML
    private TableColumn<?, ?> CTableName;

    @FXML
    private TableColumn<?, ?> CTablePrice;

    @FXML
    private TableColumn<?, ?> CTableTotal;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelHour;

    @FXML
    private Label LabelNameTiket;

    @FXML
    private Label LabelTotalProducts;

    @FXML
    private Label LabelTotalSale;

    @FXML
    private Tab Tab;

    @FXML
    private TabPane TabPane;

    @FXML
    private TableView<?> Table;

    @FXML
    private TextField TextFieldCode;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnArtCommon;

    @FXML
    private Button btnCashRegister;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnCharge;

    @FXML
    private Button btnConfiguration;

    @FXML
    private Button btnDelateArt;

    @FXML
    private Button btnDelateTiket;

    @FXML
    private Button btnInsV;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnLastTiket;

    @FXML
    private Button btnPending;

    @FXML
    private Button btnSale;

    @FXML
    private Button btnSalesRecord;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUser;

    @FXML
    public void initialize() {

        iniciarReloj();


        CreatenewTiketF6();




    }





    private void iniciarReloj() {
        // Formato para la fecha y hora (Ejemplo: "04/09/2026 16:33:05" o "16:33:05")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy       hh:mm a");

        // Crear un ciclo que se ejecute cada 1 segundo
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LabelHour.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void CreateNewTab(ActionEvent event){
        CreateNewTiket();
    }

    public void CreatenewTiketF6(){
        Platform.runLater(() -> {
            TabPane.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F6) {
                    CreateNewTiket();
                }
            });
        });
    }



    public void CreateNewTiket(){

        String defaultName = "Ticket " + (TabPane.getTabs().size() + 1);

        TextInputDialog dialog = new TextInputDialog(defaultName);
        dialog.setTitle("Nuevo Ticket");
        dialog.setHeaderText("Nombre para la venta actual");
        dialog.setContentText("Ingrese el nombre del ticket:");

        Stage mainStage = (Stage) TabPane.getScene().getWindow();
        dialog.initOwner(mainStage);

        String cssPath = getClass().getResource("/css/InicioStyle.css").toExternalForm();
        dialog.getDialogPane().getStylesheets().add(cssPath);

        // 3. Mostrar el modal
        Optional<String> result = dialog.showAndWait();

        String ticketName = defaultName;
        if (result.isPresent() && !result.get().trim().isEmpty()) {
            ticketName = result.get().trim();
        }

        Tab newTab = new Tab(ticketName);
        newTab.setClosable(false);
        TableView<Object> tableView = createTableView();

        newTab.setContent(tableView);
        TabPane.getTabs().add(newTab);
        TabPane.getSelectionModel().select(newTab);

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

}
