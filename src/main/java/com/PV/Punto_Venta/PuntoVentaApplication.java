package com.PV.Punto_Venta;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PuntoVentaApplication extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() {
		// 1. Inicializamos Spring Boot ANTES de mostrar la interfaz
		// Usamos WebApplicationType.NONE si no es una app web REST
		this.springContext = new SpringApplicationBuilder(PuntoVentaApplication.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));

		// 2. ¡EL PASO CLAVE! Le decimos a JavaFX que cree los controladores usando Spring
		// Esto habilita @Autowired y la inyección de dependencias en tus controladores
		fxmlLoader.setControllerFactory(springContext::getBean);

		Scene scene = new Scene(fxmlLoader.load());
		stage.setScene(scene);
		stage.setTitle("Punto de Venta - Login");

		// Configuración para pantalla completa si es necesario
		stage.show();
	}

	@Override
	public void stop() {
		// 3. Cerramos el contexto de Spring limpiamente al cerrar la ventana de JavaFX
		this.springContext.close();
		Platform.exit();
	}

	public static void main(String[] args) {
		// Inicia el hilo de la aplicación JavaFX
		launch(args);
	}
}