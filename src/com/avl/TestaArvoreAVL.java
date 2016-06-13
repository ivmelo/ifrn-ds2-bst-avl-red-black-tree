package com.avl;
import java.util.ArrayList;

public class TestaArvoreAVL {
	
	public static void main(String[] args) {

		// arv2f.avl
		System.out.println("\n********************************************");
		System.out.println("Nova ·rvore...");
		AVL arvore3 = new AVL(); 	


		System.out.println("--------------------------------------------");
		System.out.println("Imprimindo em ordem...");
		ArrayList<No> res3 = arvore3.mostrarEmOrdem();
		for (No a : res3){
			System.out.println(a.getChave() + "(" + a.getFatorBalanceamento() + ")");
		}
		
		/*
		System.out.println("--------------------------------------------");
		System.out.println("RotaÁıes...");
		ArrayList<String> op2 = arvore3.getOperacoes();
		for (String s : op2){
			System.out.println(s);
		}
		*/
		

	}

}
