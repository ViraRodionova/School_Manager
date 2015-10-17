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
import school_manager.MainApp;
import school_manager.model.Teacher;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.User;


/**
 * FXML Controller class
 *
 * @author bepa
 */
public class TeacherMenuFragmentController implements Initializable, MainReferenced {

    MainApp mainApp;
    Teacher teacher;
    RootLayoutController rootLayoutController;
    DatabaseManager DBmanager;
    
    @FXML
    private Button buttonProfile;
    
    @FXML
    private Button buttonMyLesson;
    
    @FXML
    private Button buttonSchedule;
    
    @FXML
    private Button buttonClasses;
    
    @FXML
    private Button buttonSettings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //buttomMyProfileClicked();
    }    

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void setRootLayout(RootLayoutController rootContr){
        this.rootLayoutController = rootContr;
    }
    
    public void setTeacher(int userId){
        this.teacher = DBmanager.getTeacherById(userId);
        rootLayoutController.nameLabel.setText(this.teacher.getInitials());
    } 
    
    @FXML
    public void buttonMyLessonClicked(){
        
        FXMLLoader loader = new FXMLLoader();
        
        try{   
            
            loader.setLocation(getClass().getResource("TeacherLessonFragment.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            
            TeacherLessonFragmentController teacherLessonController = loader.getController();
            teacherLessonController.setMainApp(mainApp);
            teacherLessonController.setTeacher(teacher);
            
            mainApp.setContent(pane);
            mainApp.setStatus("Lesson loaded.");
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            mainApp.setStatus("Error loading lesson...");
        }
    }
    
    @FXML
    public void buttonMyProfileClicked(){
        FXMLLoader loader = new FXMLLoader();
        
        try{
            
            loader.setLocation(getClass().getResource("TeacherProfileFragment.fxml"));
            BorderPane pane = (BorderPane) loader.load();
            
            TeacherProfileFragmentController teacherProfileController = loader.getController();
            teacherProfileController.setMainApp(mainApp);
            teacherProfileController.setTeacher(teacher);
            
            mainApp.setContent(pane);
            mainApp.setStatus("Teacher profile has been set.");
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            mainApp.setStatus("Error loading teacher profile.");
        }
    }
    
}
