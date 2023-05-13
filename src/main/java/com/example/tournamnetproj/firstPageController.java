package com.example.tournamnetproj;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class firstPageController implements Initializable {

    @FXML
    ListView<String> currentTournaments;

    @FXML
    ListView<String> prevTournaments;

    @FXML
    ListView<String> upcomingTournaments;

    @FXML
    TextFlow textFlow;

    Tournament[] tournaments = Main.tournaments;

    // this array will be used to populate the ListView.
    String[] currentTournament = new String[tournaments.length];
    {
        for(int i = 0; i < tournaments.length; i++) {
            currentTournament[i] = tournaments[i].getName();
        }
    }

    // this array is to display the details of a tournament.
    Tournament[] currentTournamentT = new Tournament[tournaments.length];
    {
        for(int i = 0; i < tournaments.length; i++) {
            currentTournamentT[i] = tournaments[i];
        }
    }

    String selectedTournament;

    // to set up the button action
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogInPage(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // populating the Lists
        currentTournaments.getItems().addAll(currentTournament);
        prevTournaments.getItems().addAll(currentTournament);
        upcomingTournaments.getItems().addAll(currentTournament);

        // this code listen for any change in the selected cell and change the label below
        displayInfo(currentTournaments);


        displayInfo(prevTournaments);

        displayInfo(upcomingTournaments);

    }

    private void displayInfo(ListView<String> currentTournaments) {
        currentTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournament = currentTournaments.getSelectionModel().getSelectedItem(); // get the name of the selected tournament

                //search for the tournament and display its details
                for(int i = 0; i < currentTournamentT.length; i++) {
                    if(currentTournamentT[i].getName().equals(selectedTournament)) {
                        String details = currentTournamentT[i].getDetails();
                        Text text = new Text(details);
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                    }
                }
            }
        });
    }


}