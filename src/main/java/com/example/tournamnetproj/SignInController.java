package com.example.tournamnetproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignInController {
    private String name;
    private boolean isAdmin;
    private String email;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private Label label;

    @FXML
    void checkCredentials(ActionEvent event) throws Exception {
        //we check if the username and password are correct
        //if they are correct, we return true
        //if they are not correct, we return false
        //set the
        ///////
        // end point: https://us-central1-swe206-221.cloudfunctions.net/app
        // path: /UserSignIn
        // method: GET
        // params: username, password
        // return: 200 if success, 400 if missing params, 403 if wrong username or
        // password
        String username = usernameField.getText();
        String password = passwordField.getText();
        // api call
        URL url = new URL("https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + username
                + "&password=" + password);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        // get response code
        int status = con.getResponseCode();

        // if success, print "success" and show the user info
        if (status == 200) {
            System.out.println("success");
            // get response body
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // print response body
            in.close();

            // set the name, isAdmin, and email
            String[] userInfo = content.toString().split("\"");
            if (userInfo.length == 5) {
                isAdmin = true;
                name = "admin";
                email = "admin";
            } else {
                isAdmin = false;
                name = userInfo[3];
                email = userInfo[userInfo.length - 2];
            }
            // close connection, return true
            con.disconnect();
            //show welcome massage, in black
            label.setText("Welcome " + name);
            label.setStyle("-fx-text-fill: black;");

            //switch to the next page
            switchToFirstPage(event, isAdmin);
            return;

        }
        // if missing params, print "missing params"
        else if (status == 400) {
            System.out.println("missing params");


        }
        // if wrong username or password, print "wrong username or password"
        else if (status == 403) {
            System.out.println("wrong username or password");
        }
        // if other error, print "error"
        else {
            System.out.println("something went wrong");
        }
        // close connection, return false (if we get here, it means the username or password is wrong)
        con.disconnect();
        //show error massage, in red
        label.setText("Wrong username or password");
        label.setStyle("-fx-text-fill: red;");
        //clear the fields
        usernameField.clear();
        passwordField.clear();
        return;

    }


    public void switchToFirstPage(ActionEvent event, Boolean isAdmin) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //change the visibility of login button
            Button loginButton = (Button) scene.lookup("#loginButton");
            loginButton.setVisible(false);
            //if admin, change the visibility of add tournament button
            if (isAdmin) {
                Button addTournamentButton = (Button) scene.lookup("#addTournamentButton");
                addTournamentButton.setVisible(true);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}







