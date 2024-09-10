package own.practice.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private Button registerPage;

    @FXML
    private Button signIn;

    @FXML
    private Button showPassBtn;

    @FXML
    private Label visiblePass;

    @FXML
    private TextField username;

    @FXML
    private Label ErrMessage;

    JSONArray usersArray = new JSONArray();

    public void initialize() throws FileNotFoundException, IOException {
        String jsonPath = "C:/Practice & Old School/Java/LoginPage/UserInfo/users.json";
        BufferedReader jsonReader = new BufferedReader(new FileReader(jsonPath));
        String userInfo = jsonReader.readLine();
        jsonReader.close();
        JSONObject userJson = new JSONObject(userInfo);
        usersArray = userJson.getJSONArray("accounts");
    }

    public void login (ActionEvent event) throws IOException {
        ErrMessage.setText("");
        String givenUser = username.getText();
        String givenPass = password.getText();
        for(int i = 0; i < usersArray.length(); i++) {
            JSONObject userItem = usersArray.getJSONObject(i).getJSONObject("user");
            if (givenUser.isEmpty() || givenPass.isEmpty()) {
                ErrMessage.setText("Username and/or Password field is blank");
            } else if(givenUser.equals(userItem.getString("username")) && givenPass.equals(userItem.getString("password"))) {
                FXMLLoader loader = new FXMLLoader(ApplicationLauncher.class.getResource("Account-View.fxml"));
                Parent root = loader.load();
                AccountController AccountController = loader.getController();
                AccountController.initialize(givenUser, givenPass);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Account Page");
                stage.getIcons().clear();
                Image icon = new Image(ApplicationLauncher.class.getResourceAsStream("/Icon/accountPage.png"));
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
            }else{
                ErrMessage.setText("Username and/or Password are incorrect!");
            }
        }

    }

    public void showPassword(ActionEvent event){
        if(!visiblePass.getText().equals(password.getText())){
            visiblePass.setText(password.getText());
        }else{
            visiblePass.setText("");
        }

    }

    public void switchToRegister (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ApplicationLauncher.class.getResource("Register-View.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Register Page");
        stage.getIcons().clear();
        Image icon = new Image(ApplicationLauncher.class.getResourceAsStream("/Icon/registerPage.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}