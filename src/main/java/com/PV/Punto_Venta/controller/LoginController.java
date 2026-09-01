package com.PV.Punto_Venta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private PasswordField TfPassword;

    @FXML
    private TextField TfUsername;

    @FXML
    private Button btnLogn;

    @FXML
    public void initialize() {
        // Inicialización básica
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        // Aquí podrías agregar validación de usuario/contraseña
//        String usuario = TfUsername.getText();
//        String password = TfPassword.getText();
//
//        if (usuario.isEmpty() || password.isEmpty()) {
//            System.out.println("Por favor llena todos los campos");
//            return;
//        }

        // Navegar a la pantalla de inicio
        abrirPantallaInicio(event);
    }

    private void abrirPantallaInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/INICIO.fxml"));
            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setFullScreenExitKeyCombination(javafx.scene.input.KeyCombination.NO_MATCH);
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);

            scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                    keyEvent.consume();
                }
            });


            stage.setTitle("Punto de Venta - Pantalla Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la pantalla inicio.fxml: " + e.getMessage());
        }
    }



}