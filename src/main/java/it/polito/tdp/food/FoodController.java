/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.PorzioneConnessa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...");
    	if(this.txtPassi.getText().isBlank()) {
    		this.txtResult.setText("Non hai inserito nessun numero max passi");
    		return;
    	}
    	
    	Integer N;
    	try {
    		N=Integer.parseInt(this.txtPassi.getText().trim());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Il numero ma di passi inserito non è un numero intero");
    		return;
    	}
    	this.txtResult.appendText("\n");
    	List<String> result=this.model.avviaRicorsione(N, this.boxPorzioni.getValue());
    	if(result==null || result.isEmpty()) {
    		this.txtResult.setText("Non esiste tale cammino");
    	}else {
    		for(String s: result) {
    			this.txtResult.appendText(s+"\n");
    		}
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...");
    	if(this.boxPorzioni.getValue()==null) {
    		this.txtResult.setText("Inserire un tipo");
    		return;
    	}
    	
    	List<PorzioneConnessa> result=this.model.getVicini(this.boxPorzioni.getValue());
    	this.txtResult.appendText("\n");
    	if(result.isEmpty()) {
    		this.txtResult.appendText("Nodo isolato");
    	}else {
    	for(PorzioneConnessa p: result) {
    		this.txtResult.appendText(p.toString()+"\n");
    	}
    	}    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	if(this.txtCalorie.getText().isBlank()) {
    		this.txtResult.setText("Non hai inserito nessuna caloria");
    		return;
    	}
    	
    	Integer c;
    	try {
    		c=Integer.parseInt(this.txtCalorie.getText().trim());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("La caloria inserita non è un numero intero");
    		return;
    	}
    	
    	String result=this.model.creaGrafo(c);
    	this.txtResult.appendText("\n");
    	this.txtResult.appendText(result);
    	this.btnCorrelate.setDisable(false);
    	this.boxPorzioni.getItems().addAll(this.model.ritornaVertici());
    	this.btnCammino.setDisable(false);
    	
    	
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.btnCammino.setDisable(true);
    	this.btnCorrelate.setDisable(true);
    }
}
