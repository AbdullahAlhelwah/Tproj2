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

public class MyController implements Initializable {

    @FXML
    ListView<String> currentTournaments;

    @FXML
    ListView<String> prevTournaments;

    @FXML
    ListView<String> upcommingTournaments;

    @FXML
    TextFlow textFlow;

    //test samples. this should be read from a file.
    RoundRobin t1 = new RoundRobin("tournament", false, "football", null);
    RoundRobin t2 = new RoundRobin("tournament1", true, "football", null);
    RoundRobin t3 = new RoundRobin("tournament2", false, "football", null);
    RoundRobin t4 = new RoundRobin("tournament3", false, "football", null);
    RoundRobin t5 = new RoundRobin("tournament4", false, "football", null);

    // this array will be used to populate the ListView.
    String[] currentTournament = {t1.getName(),t2.getName(),t3.getName(),t4.getName(),t5.getName()};
    // this array is to display the details of a tournament.
    Tournament[] currentTournamentT = {t1,t2,t3,t4,t5};

    String selectedTournament;

    // to set up the button action
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogInPage(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
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
        upcommingTournaments.getItems().addAll(currentTournament);

        // this code listen for any change in the selected cell and change the lable below
        currentTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournament = currentTournaments.getSelectionModel().getSelectedItem(); // get the name of the selected tournament

                //search for the tournament and display its details
                for(int i = 0; i < currentTournamentT.length; i++) {
                    if(currentTournamentT[i].getName().equals(selectedTournament)) {
                        Text text = new Text("Additionally, you can use the Region class, which is the base class for all layout containers, to set the preferred size of the node. When the size of the window changes, the layout container will adjust the size of the node according to its preferred size.");
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                    }
                }
            }   
        });

        // "The name of the tournament is: " + currentTournamentT[i].getName() + 
        //                 "\nIs Individual? " + currentTournamentT[i].getIsIndividual() + 
        //                 "          The Sport: " + currentTournamentT[i].getSport() +
        //                 "\nHas Finished? " + currentTournamentT[i].getHasFinished()

        
        prevTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournament = currentTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < currentTournamentT.length; i++) {
                    if(currentTournamentT[i].getName().equals(selectedTournament)) {
                        Text text = new Text("Additionally, you can use the Region class, which is the base class for all layout containers, to set the preferred size of the node. When the size of the window changes, the layout container will adjust the size of the node according to its preferred size.");
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                    }
                }
            }   
        });

        upcommingTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournament = currentTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < currentTournamentT.length; i++) {
                    if(currentTournamentT[i].getName().equals(selectedTournament)) {
                        Text text = new Text("Additionally, you can use the Region class, which is the base class for all layout containers, to set the preferred size of the node. When the size of the window changes, the layout container will adjust the size of the node according to its preferred size.");
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                    }
                }
            }   
        });

    }

  
}
