package own.practice.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountController {
    @FXML
    private Menu accountInfo;

    @FXML
    private Menu theme;

    @FXML
    private Label welcomeTxt;

    @FXML
    private MenuItem darkTheme;

    @FXML
    private MenuItem lightTheme;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button signoutBtn;

    @FXML
    private TextField passDisplay;

    @FXML
    private TextField userDisplay;

    public void initialize(String givenUser, String givenPass) {
        welcomeTxt.setText("Welcome, "+givenUser.substring(0,6));

    }

    public void switchDark(ActionEvent event) {
        if(!backgroundPane.getStyle().equals("-fx-background-color: #1f1f1f;")) {
            backgroundPane.setStyle("-fx-background-color: #1f1f1f;");
            menuBar.setStyle("-fx-background-color: #686867;");
            welcomeTxt.setStyle("-fx-text-fill: #FFF;");
            signoutBtn.setStyle("-fx-background-color: #686867;-fx-text-fill: #FFF;");
        }
    }

    public void switchDefault(ActionEvent event) {
        if(!backgroundPane.getStyle().equals("-fx-background-color: #FFFFFF;")) {
            backgroundPane.setStyle("-fx-background-color: #FFFFFF;");
            menuBar.setStyle("-fx-background-color: #e8dddd;");
            welcomeTxt.setStyle("-fx-text-fill: #000;");
            signoutBtn.setStyle("-fx-background-color: #e8e8d8;-fx-text-fill: #000;");
        }
    }

    public void switchLoginPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ApplicationLauncher.class.getResource("Login-View.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Login Page");
        stage.getIcons().clear();
        Image icon = new Image(ApplicationLauncher.class.getResourceAsStream("/Icon/loginPage.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
