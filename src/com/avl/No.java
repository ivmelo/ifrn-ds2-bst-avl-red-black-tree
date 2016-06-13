package com.avl;

public class No {
	private No filhoEsquerdo;
	private No filhoDireito;
	private No pai;
	private int chave;
	int fatorBalanceamento;
	
	public No(int k) {
		setFilhoEsquerdo(setFilhoDireito(setPai(null)));
		setFatorBalanceamento(0);
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

	public void setFatorBalanceamento(int fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}
	
	public int getFatorBalanceamento() {
		return fatorBalanceamento;
	}
	
}
