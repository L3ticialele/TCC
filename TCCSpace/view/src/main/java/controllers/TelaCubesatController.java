package controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import br.cefetmg.space.view.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author letic
 */
public class TelaCubesatController implements Initializable {
    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;

    @FXML
    private Label label;
    
    @FXML
    private Button botaoHome;

    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat");
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        MainFX.changedScreen("Equipes");
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        MainFX.changedScreen("Explorar");
    }
    
    
    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        MainFX.changedScreen("Tela Inicial");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
