package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	// variabili logica di gioco del modello
	private int NMAX;
	private int TMAX;
	private int segreto;
	private int numTentativi;
	private boolean inGioco;
	
	private Set<Integer> elencoTentativi;
	
	public Model() {
		this.inGioco = false;
		this.numTentativi = 0;
		this.NMAX = 10;
		this.TMAX = log2(NMAX);
	}
	
	public void nuovaPartita() {
		this.segreto = (int) (Math.random()*NMAX) + 1;
		this.numTentativi = 0;
		this.inGioco = true;
		this.elencoTentativi = new HashSet<Integer>();
	}
	
	public String tentativo(int numero) {
		int numeroTentativo;
		
		if (!tentativoValido(numero))
			throw new InvalidParameterException("Inserisci un numero non ancora inserito tra 1 e " + NMAX);
			

    	//Il tentativo è valido --> possiamo provarlo
    	this.numTentativi++;

    	if (this.numTentativi > TMAX)
    		this.inGioco = false;
    	
    	//la partita e effettivamente in corso?
		if (!inGioco) 
			throw new IllegalStateException("La partita è terminata.\n");
	
    	// Algoritmo
    	if (this.segreto == numero) {
	    	this.inGioco = false;
	    	return "Tentativo corretto, hai vinto con " +  this.getNumTentativi() + " tentativi!\n";
	    }
    	else if (this.segreto > numero) {
    		elencoTentativi.add(numero);
    		return "Tentativo troppo basso!\n";
    	}
		else
		{
    		elencoTentativi.add(numero);
    		return "Tentativo troppo alto!\n";
		}
    	
	}
	
	public boolean isInGioco() {
		return inGioco;
	}

	private boolean tentativoValido(int tentativo) {
		if (tentativo < 1 || tentativo > NMAX)
			return false;
		if (this.elencoTentativi.contains(tentativo))
			return false;
		
		return true;
	}

	public int getTentativiRimasti() {
		return TMAX-numTentativi;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getNumTentativi() {
		return numTentativi;
	}

	public int getTMax() {
		return TMAX;
	}
	
	public void setLivello(String name) {
		switch(name) {
		case "Facile":
			this.NMAX = 20;
			this.TMAX = log2(NMAX);
			break;
		case "Intermedio":
			this.NMAX = 100;
			this.TMAX = log2(NMAX);
			break;
		case "Difficile":
			this.NMAX = 1000;
			this.TMAX = log2(NMAX);
			break;
		default:
			break;
		}
			
	}
	
	public String getConsiglio() {
		int min = 1;
		int max = NMAX;
		
		for (Integer n : elencoTentativi)
			if (n < segreto && (n > min))
				min = n;
			else if (n > segreto && (n < max))
				max = n;
		
		return "Consiglio: il numero è compreso tra " + min + " e " + max;
	}
	
	public static int log2(int x)
	{
	    return (int) (Math.log(x) / Math.log(2));
	}
}
