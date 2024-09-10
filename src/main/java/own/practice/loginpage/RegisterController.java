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

public class RegisterController {
    @FXML
    private Label ErrMessage;

    @FXML
    private Button loginPage;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private TextField username;

    @FXML
    private Label visiblePass;

    JSONObject userJson = new JSONObject();
    JSONArray usersArray = new JSONArray();
    String jsonPath = null;

    public void initialize() throws FileNotFoundException, IOException {
        jsonPath = "C:/Practice & Old School/Java/LoginPage/UserInfo/users.json";
        BufferedReader jsonReader = new BufferedReader(new FileReader(jsonPath));
        String userInfo = jsonReader.readLine();
        jsonReader.close();
        userJson = new JSONObject(userInfo);
        usersArray = userJson.getJSONArray("accounts");
        System.out.println(userJson+"\n"+usersArray);
    }

    public void register(ActionEvent event) throws IOException {
        ErrMessage.setText("");
        String givenUser = username.getText();
        String givenPass = password.getText();
        if(givenUser.isEmpty() || givenPass.isEmpty()) {
            ErrMessage.setText("Username and/or Password field is blank");
        }
        else if(!(givenUser.length() >= 6 && givenUser.length() <= 15) && !(givenPass.length() >= 8 && givenPass.length() <= 20)){
            ErrMessage.setText("Username length between 6 & 15 characters, Password length between 8 & 20.");
        }else {
            JSONObject userLogin = new JSONObject().put("username", givenUser).put("password", givenPass);
            JSONObject user = new JSONObject().put("user", userLogin);
            userJson.getJSONArray("accounts").put(user);
            FileWriter writeNewUser = new FileWriter(jsonPath);
            writeNewUser.write(userJson.toString());
            writeNewUser.close();
            FXMLLoader loader = new FXMLLoader(ApplicationLauncher.class.getResource("Account-View.fxml"));
            Parent root = loader.load();
            AccountController AccountController = loader.getController();
            AccountController.initialize(givenUser, givenPass);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Account Page");
            stage.getIcons().clear();
            Image icon = new Image(ApplicationLauncher.class.getResourceAsStream("/Icon/accountPage.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
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
