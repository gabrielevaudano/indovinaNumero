package it.polito.tdp.indovinanumero;

import java.security.InvalidParameterException;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private Model model; 

    @FXML
    private MenuButton nuovoTentativo;

    @FXML
    private MenuItem nuovoTentativoFacile;
    
    @FXML
    private MenuItem nuovoTentativoIntermedio;

    @FXML
    private MenuItem nuovoTentativoDifficile;

    @FXML
    private HBox layoutTent;

    @FXML
    private TextField txtNumeroInserito;

    @FXML
    private Button btnProva;
    
    @FXML
    private Button consiglio;

    @FXML
    private ProgressBar tentRimasti;

    @FXML
    private TextArea txtAreaMsg;

    @FXML
    void doNuovoTentativo(ActionEvent event) {
    	this.model.nuovaPartita();
    	this.tentRimasti.setProgress(0.01);
    	txtAreaMsg.setText("");

    	// gestione difficolta
    	if (event.getSource()==nuovoTentativoFacile)
    		model.setLivello("Facile");
    	else if (event.getSource() == nuovoTentativoIntermedio)
    		model.setLivello("Intermedio");
    	else if (event.getSource() == nuovoTentativoDifficile)
    		model.setLivello("Difficile");
    	
    	
    	//gestione dell'interfaccia
    	layoutTent.setDisable(false);
    }


    @FXML
    void doConsiglio(ActionEvent event) {
    	if (model.isInGioco())
    		txtAreaMsg.appendText(model.getConsiglio() + "\n");
    }
    
    @FXML
    void handleProva(ActionEvent event) {
    	String risultato = "";
    	int tentativo = 0;
    
    	// gestione errori ed eccezioni 
    	try {
    		 tentativo = Integer.parseInt(txtNumeroInserito.getText());
    	} catch (NumberFormatException e) {
    		txtAreaMsg.appendText("Non hai inserito un numero: " + e);
    		return;
    	}
    	
    	try {
    		risultato = this.model.tentativo(tentativo);
    	} catch (IllegalStateException ise) {
    		txtAreaMsg.appendText(ise.getMessage());
    		return;
    	} catch (InvalidParameterException ipe) {
    		txtAreaMsg.appendText(ipe.getMessage());
    		return;
    	}
    	
    	// presentazione risultati ad interfaccia utente
    	txtAreaMsg.appendText(risultato);
    	
    	// gestione progresso con progressbar
    	double progresso = ((double) model.getNumTentativi())/(model.getTMax());
    	this.tentRimasti.setProgress(progresso);
    	
    }

    
    public void setModel (Model model) {
    	this.model = model;
    }
}