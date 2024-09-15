package own.practice.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class AccountController {
    @FXML
    private Menu accountInfo;

    @FXML
    private Menu theme;

    @FXML
    private Label welcomeTxt, passTxt, userTxt;

    @FXML
    private MenuItem darkTheme;

    @FXML
    private MenuItem lightTheme;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button signoutBtn, imageBtn;

    @FXML
    private TextField passDisplay;

    @FXML
    private TextField userDisplay;

    @FXML
    private ImageView profilePic;

    public void initialize(String givenUser, String givenPass) {
        welcomeTxt.setText("Welcome, "+givenUser.substring(0,6));
        userDisplay.setText(givenUser);
        passDisplay.setText(givenPass);
        userDisplay.setVisible(false);
        passDisplay.setVisible(false);
        userTxt.setVisible(false);
        passTxt.setVisible(false);
        imageBtn.setVisible(false);
        profilePic.setImage(new Image(getClass().getResourceAsStream("/Images/blankProfilePic.png")));
    }

    public void chooseImage(ActionEvent event){
        FileChooser newProfilePic = new FileChooser();
        newProfilePic.setTitle("Choose Profile Picture");
        // Change directory
        newProfilePic.setInitialDirectory(new File("C:/Practice & Old School/Java/LoginPage/src/main/resources/Images"));
        newProfilePic.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        String profileImagePath = String.valueOf(newProfilePic.showOpenDialog(new Stage()));
        File profileImg = new File(profileImagePath);
        profilePic.setImage(new Image(profileImg.toURI().toString()));
        System.out.println(profileImagePath);
        if(!(profileImagePath.endsWith(".png") || profileImagePath.endsWith(".jpg")) || profileImagePath == null) {
            profilePic.setImage(new Image(getClass().getResourceAsStream("/Images/blankProfilePic.png")));
        }
    }

    public void ToggleUserInfo(ActionEvent event){
        if(!userTxt.isVisible()) {
            userDisplay.setVisible(true);
            passDisplay.setVisible(true);
            userTxt.setVisible(true);
            passTxt.setVisible(true);
            imageBtn.setVisible(true);
        }else{
            userDisplay.setVisible(false);
            passDisplay.setVisible(false);
            userTxt.setVisible(false);
            passTxt.setVisible(false);
            imageBtn.setVisible(false);

        }
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
