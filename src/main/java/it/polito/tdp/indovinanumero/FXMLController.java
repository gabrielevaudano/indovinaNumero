package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int numTentativi;
	private boolean inGioco = false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovoTentativo;

    @FXML
    private TextField txtTentativiRimasti;

    @FXML
    private HBox layoutTent;

    @FXML
    private TextField txtNumeroInserito;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtAreaMsg;

    @FXML
    void handleNuovoTentativo(ActionEvent event) {
    	// gestione dell'inizio di una nuova partita - logica del gioco
    	
    	this.segreto = (int) (Math.random() * NMAX) + 1;
    	this.numTentativi = 0;
    	this.inGioco = true;
    	
    	//gestione dell'interfaccia
    	layoutTent.setDisable(false);
    	this.txtTentativiRimasti.setText(Integer.toString(TMAX));
    	
    }

    @FXML
    void handleProva(ActionEvent event) {
    	int tentativo = Integer.parseInt(txtNumeroInserito.getText());
    	
    	if (Integer.parseInt(this.txtTentativiRimasti.getText())==0) {
    		layoutTent.setDisable(true);
    		txtAreaMsg.appendText("Hai perso! Il numero segreto è: " + (numTentativi+2));
    		inGioco = false;
    		return;
    	}
    	
    	//controllare sempre l'input
    	try {
	    	if (this.segreto == tentativo) {
	    		txtAreaMsg.appendText("Bravo, hai trovato la soluzione! Con " + this.numTentativi + "\n");
	    		this.layoutTent.setDisable(true);
	    		inGioco = false;
	    		return;
	    	}
	    	else if (this.segreto > tentativo)
	    		txtAreaMsg.appendText("Troppo basso!\n");
	    	else if (this.segreto < tentativo)
	    		txtAreaMsg.appendText("Troppo alto!\n");
    	} catch (NumberFormatException e)  {
    		txtAreaMsg.appendText("Non hai inserito un numero\n");
    	}
    	
    	numTentativi++;
    	txtTentativiRimasti.setText(Integer.toString(TMAX - numTentativi)); 
    	
    	
    }

    @FXML
    void initialize() {
        assert btnNuovoTentativo != null : "fx:id=\"btnNuovoTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativiRimasti != null : "fx:id=\"txtTentativiRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTent != null : "fx:id=\"layoutTent\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroInserito != null : "fx:id=\"txtNumeroInserito\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaMsg != null : "fx:id=\"txtAreaMsg\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
