module com.example.controleestoque {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.controleestoque to javafx.fxml;
    exports com.example.controleestoque;
}