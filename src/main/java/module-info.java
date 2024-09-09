module own.practice.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens own.practice.loginpage to javafx.fxml, org.json;
    exports own.practice.loginpage;
}