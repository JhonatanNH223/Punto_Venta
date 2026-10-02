package com.PV.Punto_Venta.controller;

import com.PV.Punto_Venta.service.LenguageService;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javafx.util.Duration;
import org.springframework.stereotype.Controller;

import java.io.IOException;
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
    private Label LabelHour;

    @FXML
    private Button btnCashRegister;

    @FXML
    private Button btnConfiguration;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnSale;

    @FXML
    private Button btnUser;

    @FXML
    private AnchorPane contenidoCentral;

    @Autowired
    private LenguageService languageService;

    @FXML
    public void mostrarVistaVenta() {
        cargarSubVista("/fxml/subview/VentaView.fxml");
    }

    @FXML
    public void mostrarVistaInventario() {
        cargarSubVista("/fxml/subview/InventarioView.fxml");
    }

    @FXML
    public void mostrarVistaCorte() {
        cargarSubVista("/fxml/subview/CorteView.fxml");
    }

    private void cargarSubVista(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath),
                    languageService.getBundle()
            );
            loader.setControllerFactory(springContext::getBean);

            Node vista = loader.load();

            // Anclar la subvista a los 4 bordes del contenedor central
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);

            // Limpiar y colocar el nuevo contenido
            contenidoCentral.getChildren().setAll(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Al abrir la app, cargar la sub-vista de Venta por defecto
        mostrarVistaVenta();
        iniciarReloj();
    }




    //--------------- Reloj/clock ---------------
    private void iniciarReloj() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy       hh:mm a");

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LabelHour.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    //-------------------------------------------


}
