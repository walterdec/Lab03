package it.polito.tdp.spellchecker.controller;

	import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ChoiceBox;
	import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

	public class SpellCheckerController {
		
		ObservableList<String> lingue = FXCollections
				.observableArrayList("Italiano", "English");
		
		private Dictionary model;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ChoiceBox<String> choiceBox;

	    @FXML
	    private TextArea inputTxtArea;

	    @FXML
	    private Button spellCheckBtn;

	    @FXML
	    private TextArea outputTxtArea;

	    @FXML
	    private Text wrongWordsMsg;

	    @FXML
	    private Button clearTextBtn;

	    @FXML
	    private Text timeMsg;

	    @FXML
	    void doClearText(ActionEvent event) {
	    	inputTxtArea.clear();
	    	outputTxtArea.clear();
	    	wrongWordsMsg.setText("");
	    	timeMsg.setText("");
	    }

	    @FXML
	    void doSpellCheck(ActionEvent event) {
	    	long inizio = System.currentTimeMillis();
	    	outputTxtArea.clear();
	    	int contatore=0;
	    	if(choiceBox.getSelectionModel().getSelectedItem().equals("Italiano")){
	    		model.loadDictionary("Italiano");
	    	}
			if(choiceBox.getSelectionModel().getSelectedItem().equals("English")){
	    		model.loadDictionary("English");
	    	}
			String [] inputSplit = inputTxtArea.getText().toLowerCase().replaceAll("[?.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").split(" ");
			List<String> paroleInput = new ArrayList<String>();
			for(int i=0; i<inputSplit.length; i++) {
				paroleInput.add(inputSplit[i]);
			}
			List<RichWord> input = new ArrayList<RichWord>();
			input = model.spellCheckText(paroleInput);
			for(RichWord rw : input) {
				if(!rw.isCorretta()) {
					outputTxtArea.appendText(rw.getRichword()+"\n");
					contatore++;
				}
			}
			if(contatore==0) {
				wrongWordsMsg.setFill(Color.GREEN);
				wrongWordsMsg.setText("The text is correct!");
			}
			else if(contatore==1){
				wrongWordsMsg.setFill(Color.RED);
				wrongWordsMsg.setText("The text contains 1 error");
			}
			else {
				wrongWordsMsg.setFill(Color.RED);
			wrongWordsMsg.setText("The text contains "+contatore+" errors");
			}
			
			long fine = System.currentTimeMillis();
			timeMsg.setText("Spell check completed in "+(double)(fine-inizio)/1000+" seconds");
	    }

	    @FXML
	    void initialize() {
	        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert inputTxtArea != null : "fx:id=\"inputTxtArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert spellCheckBtn != null : "fx:id=\"spellCheckBtn\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert outputTxtArea != null : "fx:id=\"outputTxtArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert wrongWordsMsg != null : "fx:id=\"wrongWordsMsg\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert clearTextBtn != null : "fx:id=\"clearTextBtn\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert timeMsg != null : "fx:id=\"timeMsg\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        choiceBox.setValue("Italiano");
	        choiceBox.setItems(lingue);
	        model = new Dictionary();

	    }
	}
