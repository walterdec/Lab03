package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String richword;
	private boolean corretta;
	

	public RichWord(String richword) {
		this.richword = richword;
		corretta=false;
	}
	
	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

	public String getRichword() {
		return richword;
	}

}
