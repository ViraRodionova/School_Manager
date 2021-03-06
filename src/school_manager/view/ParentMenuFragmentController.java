/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Parent;

/**
 *
 * @author abrasha
 */
public class ParentMenuFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Parent parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.parent = DatabaseManager.getParentById(mainApp.getAccountInfo().getId());
        if (parent != null)
            mainApp.setUserName(parent.toString());
        else
            mainApp.setStatus("Error getting parent from db");
    }

    @FXML
    public void buttonMyProfileClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("ParentProfileFragment.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            ParentProfileFragmentController parentProfileController = loader.getController();
            parentProfileController.setMainApp(mainApp);
            parentProfileController.setParent(parent);

            mainApp.setContent(pane);
            mainApp.setStatus("Parent profile loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error loading parent profile.");
        }

    }

    @FXML
    public void buttonMySettingsClicked() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource("ParentSettingsFragment.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            ParentSettingsFragmentController parentSettingsController = loader.getController();
            parentSettingsController.setMainApp(mainApp);
            parentSettingsController.setParent(parent);

            mainApp.setContent(pane);
            mainApp.setStatus("Parent settings loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mainApp.setStatus("Error loading parents settings.");
        }

    }

}
