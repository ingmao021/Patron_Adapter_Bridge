module com.iluwatar.adapter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;

    opens com.iluwatar.adapter to javafx.fxml;
    opens com.iluwatar.adapter.ui to javafx.fxml;
    opens com.iluwatar.adapter.bridge to javafx.fxml;

    exports com.iluwatar.adapter;
    exports com.iluwatar.adapter.ui;
    exports com.iluwatar.adapter.bridge;
}
