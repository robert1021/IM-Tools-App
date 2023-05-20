module com.example.imtools {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.imtools to javafx.fxml;
    exports com.example.imtools;
}