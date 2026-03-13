package com.iluwatar.adapter.ui;

import com.iluwatar.adapter.Captain;
import com.iluwatar.adapter.FishingBoatAdapter;
import com.iluwatar.adapter.bridge.Boat;
import com.iluwatar.adapter.bridge.CargoBoat;
import com.iluwatar.adapter.bridge.MotorPropulsion;
import com.iluwatar.adapter.bridge.PassengerBoat;
import com.iluwatar.adapter.bridge.SailPropulsion;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX application that visually demonstrates the Adapter and Bridge design patterns
 * using the boat domain model.
 */
public class BoatApp extends Application {

    private TextArea logArea;
    private ComboBox<String> boatTypeCombo;
    private ComboBox<String> propulsionCombo;
    private Label boatEmoji;
    private Label propulsionLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adapter & Bridge - Simulador de Barcos");

        // ── Root layout ──
        HBox root = new HBox(20);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #1a1a2e;");

        // ── Left panel: controls ──
        VBox controlPanel = createControlPanel();
        controlPanel.setMinWidth(320);
        controlPanel.setMaxWidth(320);

        // ── Right panel: log + animation ──
        VBox outputPanel = createOutputPanel();
        HBox.setHgrow(outputPanel, Priority.ALWAYS);

        root.getChildren().addAll(controlPanel, outputPanel);

        Scene scene = new Scene(root, 860, 540);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private VBox createControlPanel() {
        VBox panel = new VBox(16);
        panel.setPadding(new Insets(20));
        panel.setStyle(
                "-fx-background-color: #16213e;"
                + "-fx-background-radius: 12;"
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 12, 0, 0, 4);"
        );

        // Title
        Label title = new Label("🚢 Simulador de Barcos");
        title.setFont(Font.font("Segoe UI", 20));
        title.setStyle("-fx-text-fill: #e94560; -fx-font-weight: bold;");

        // Boat type selector
        Label boatLabel = styledLabel("Tipo de barco:");
        boatTypeCombo = new ComboBox<>();
        boatTypeCombo.getItems().addAll(
                "Bote de Pesca (Adapter)",
                "Barco de Pasajeros (Bridge)",
                "Barco de Carga (Bridge)"
        );
        boatTypeCombo.setValue("Bote de Pesca (Adapter)");
        styleCombo(boatTypeCombo);
        boatTypeCombo.setMaxWidth(Double.MAX_VALUE);

        // Propulsion selector
        Label propLabel = styledLabel("Sistema de propulsión:");
        propulsionCombo = new ComboBox<>();
        propulsionCombo.getItems().addAll("Vela", "Motor");
        propulsionCombo.setValue("Vela");
        styleCombo(propulsionCombo);
        propulsionCombo.setMaxWidth(Double.MAX_VALUE);
        propulsionCombo.setDisable(true);

        // Propulsion info label
        propulsionLabel = new Label("ℹ  El Adapter usa su propia navegación");
        propulsionLabel.setStyle("-fx-text-fill: #888; -fx-font-size: 11;");
        propulsionLabel.setWrapText(true);

        // Enable/disable propulsion based on boat type
        boatTypeCombo.setOnAction(e -> {
            boolean isAdapter = boatTypeCombo.getValue().contains("Adapter");
            propulsionCombo.setDisable(isAdapter);
            propulsionLabel.setText(isAdapter
                    ? "ℹ  El Adapter usa su propia navegación"
                    : "ℹ  Selecciona el sistema de propulsión (Bridge)");
        });

        // Navigate button
        Button navButton = new Button("⛵  Navegar");
        navButton.setMaxWidth(Double.MAX_VALUE);
        navButton.setStyle(
                "-fx-background-color: #e94560;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 15;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 8;"
                + "-fx-cursor: hand;"
                + "-fx-padding: 10 0;"
        );
        navButton.setOnMouseEntered(e ->
                navButton.setStyle(navButton.getStyle().replace("#e94560", "#ff6b81")));
        navButton.setOnMouseExited(e ->
                navButton.setStyle(navButton.getStyle().replace("#ff6b81", "#e94560")));
        navButton.setOnAction(e -> onNavigate());

        // Clear button
        Button clearButton = new Button("🗑  Limpiar log");
        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setStyle(
                "-fx-background-color: #0f3460;"
                + "-fx-text-fill: #aaa;"
                + "-fx-font-size: 12;"
                + "-fx-background-radius: 8;"
                + "-fx-cursor: hand;"
                + "-fx-padding: 8 0;"
        );
        clearButton.setOnAction(e -> logArea.clear());

        // Pattern info
        Label infoTitle = styledLabel("Patrones utilizados:");
        Label infoText = new Label(
                "• Adapter: FishingBoatAdapter adapta\n  FishingBoat → RowingBoat\n\n"
                + "• Bridge: Boat (abstracción) se\n  desacopla de PropulsionSystem\n  (implementación)"
        );
        infoText.setStyle("-fx-text-fill: #7a7aa0; -fx-font-size: 11;");
        infoText.setWrapText(true);

        panel.getChildren().addAll(
                title,
                boatLabel, boatTypeCombo,
                propLabel, propulsionCombo, propulsionLabel,
                navButton, clearButton,
                infoTitle, infoText
        );

        return panel;
    }

    private VBox createOutputPanel() {
        VBox panel = new VBox(12);

        // Animation area
        StackPane animationPane = new StackPane();
        animationPane.setMinHeight(120);
        animationPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #0a1628, #16213e);"
                + "-fx-background-radius: 12 12 0 0;"
        );

        // Water waves
        Label waves = new Label("〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰");
        waves.setStyle("-fx-text-fill: #1a5276; -fx-font-size: 18;");
        StackPane.setAlignment(waves, Pos.BOTTOM_CENTER);

        // Boat emoji
        boatEmoji = new Label("🚢");
        boatEmoji.setFont(Font.font(48));
        boatEmoji.setTranslateX(-180);

        animationPane.getChildren().addAll(waves, boatEmoji);

        // Log area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setStyle(
                "-fx-control-inner-background: #0f3460;"
                + "-fx-text-fill: #53d769;"
                + "-fx-font-family: 'Consolas', 'Courier New', monospace;"
                + "-fx-font-size: 12;"
                + "-fx-background-radius: 0 0 12 12;"
                + "-fx-border-width: 0;"
        );
        logArea.setPromptText("Los logs de navegación aparecerán aquí...");
        VBox.setVgrow(logArea, Priority.ALWAYS);

        panel.getChildren().addAll(animationPane, logArea);
        return panel;
    }

    private void onNavigate() {
        String boatType = boatTypeCombo.getValue();
        String propulsion = propulsionCombo.getValue();

        logArea.appendText("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        if (boatType.contains("Adapter")) {
            // ── Adapter pattern ──
            logArea.appendText("[ADAPTER] Capitán necesita un bote de remos...\n");
            logArea.appendText("[ADAPTER] Usando FishingBoatAdapter para adaptar FishingBoat\n");

            var captain = new Captain(new FishingBoatAdapter());
            captain.row();

            logArea.appendText("[ADAPTER] ✓ The fishing boat is sailing\n");
            logArea.appendText("[ADAPTER] El capitán navegó exitosamente con el adaptador\n");

            animateBoat("🚣");

        } else if (boatType.contains("Pasajeros")) {
            // ── Bridge pattern: PassengerBoat ──
            logArea.appendText("[BRIDGE] Creando Barco de Pasajeros...\n");
            logArea.appendText("[BRIDGE] Propulsión: " + propulsion + "\n");

            Boat boat;
            if ("Motor".equals(propulsion)) {
                boat = new PassengerBoat(new MotorPropulsion());
                logArea.appendText("[BRIDGE] ✓ The passenger boat is moving\n");
                logArea.appendText("[BRIDGE] ✓ The boat is being propelled by motor\n");
            } else {
                boat = new PassengerBoat(new SailPropulsion());
                logArea.appendText("[BRIDGE] ✓ The passenger boat is moving\n");
                logArea.appendText("[BRIDGE] ✓ The boat is being propelled by sails\n");
            }
            boat.move();

            animateBoat("🛳");

        } else {
            // ── Bridge pattern: CargoBoat ──
            logArea.appendText("[BRIDGE] Creando Barco de Carga...\n");
            logArea.appendText("[BRIDGE] Propulsión: " + propulsion + "\n");

            Boat boat;
            if ("Motor".equals(propulsion)) {
                boat = new CargoBoat(new MotorPropulsion());
                logArea.appendText("[BRIDGE] ✓ The cargo boat is moving\n");
                logArea.appendText("[BRIDGE] ✓ The boat is being propelled by motor\n");
            } else {
                boat = new CargoBoat(new SailPropulsion());
                logArea.appendText("[BRIDGE] ✓ The cargo boat is moving\n");
                logArea.appendText("[BRIDGE] ✓ The boat is being propelled by sails\n");
            }
            boat.move();

            animateBoat("🚢");
        }

        logArea.appendText("\n");
    }

    private void animateBoat(String emoji) {
        boatEmoji.setText(emoji);
        boatEmoji.setTranslateX(-180);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), boatEmoji);
        transition.setFromX(-180);
        transition.setToX(180);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
    }

    private Label styledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: #a2a2c2; -fx-font-size: 13; -fx-font-weight: bold;");
        return label;
    }

    private void styleCombo(ComboBox<String> combo) {
        combo.setStyle(
                "-fx-background-color: #0f3460;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 13;"
                + "-fx-background-radius: 6;"
                + "-fx-border-color: #e94560;"
                + "-fx-border-radius: 6;"
                + "-fx-border-width: 1;"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
