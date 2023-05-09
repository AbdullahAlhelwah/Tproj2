module com.example.tournamnetproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tournamnetproj to javafx.fxml;
    exports com.example.tournamnetproj;
}