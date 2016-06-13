package com.rn;

public class No {
	public final byte RUBRO = 1;
	public final byte NEGRO = 0;
	
	private No filhoEsquerdo;
	private No filhoDireito;
	private No pai;
	private int chave;
	int cor;
	
	public No(int k) {
		setFilhoEsquerdo(setFilhoDireito(setPai(null)));
		setCor(RUBRO);
		setChave(k);
	}

	public No getFilhoEsquerdo() {
		return filhoEsquerdo;
	}

	public void setFilhoEsquerdo(No fE) {
		this.filhoEsquerdo = fE;		
	}
	
	public No getFilhoDireito() {
		return filhoDireito;
	}
	
	public No setFilhoDireito(No fD) {
		this.filhoDireito = fD;	
		return filhoDireito;
	}

	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		this.pai = pai;	
		return pai;
	}
	
	public int getChave() {
		return chave;
	}

	public void setChave(int ch) {
		this.chave = ch;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}
	
	public int getCor() {
		return cor;
	}
	
	// retorna avo
	public No getAvo() {
		if (this.getPai() == null || this.getPai().getPai() == null)
			return null;
		
		return this.getPai().getPai();
	}
	
	// retorna irmao
	public No getIrmao() {
		if (this.getPai() == null)
			return null;
		
		if (this.getPai().getFilhoEsquerdo() == this) {
			return this.getPai().getFilhoDireito();
		} else {
			return this.getPai().getFilhoEsquerdo();
		}
	}
	
	public boolean ehRaiz() {
		return this.getPai() == null;
	}
	
}
