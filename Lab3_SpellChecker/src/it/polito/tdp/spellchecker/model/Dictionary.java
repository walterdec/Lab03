package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private Set<String> dizionario = new HashSet<String>();
	private List<RichWord> input = new ArrayList<RichWord>();
	
	public void loadDictionary(String language) {
		dizionario.clear();
		if(language.equals("Italiano")) {
			try {
				FileReader fr = new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word=br.readLine())!= null) {
					dizionario.add(word);
				}
				br.close();
			} catch(IOException e) {
				System.out.println("Errore nel caricamento del file\n");
			}
		}
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("rsc/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word=br.readLine())!= null) {
					dizionario.add(word);
				}
				br.close();
			} catch(IOException e) {
				System.out.println("Errore nel caricamento del file\n");
			}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		input.clear();
		for(String s : inputTextList) {
			RichWord rw = new RichWord(s);
			input.add(rw);
			if(dizionario.contains(s)) {
				rw.setCorretta(true);
			}
		}
		
		return input;
	}
	
}
