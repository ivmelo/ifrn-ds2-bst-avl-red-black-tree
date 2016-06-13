package com.avl;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * Created on 09/03/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author robinson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class TesteBSTAVL {
	
    static String read(){
    	
		StringBuffer buff = new StringBuffer();
		
		int car;
		
		while(true){
			
			try{
				car=System.in.read();
				if ((car<0) || (car=='\n')) break;
				buff.append((char)car);
			}catch(IOException ioe){
				return null;
			}
			
		}
		
		return buff.toString();
	}
    
	public static void main(String[] args){
	    
	    try{
			String arq="arv9f.avl";
			System.out.println("Abrindo o arquivo "+arq);
			FileInputStream fis = new FileInputStream(arq);
			System.setIn(fis);
		
	    }catch(IOException e){
			System.out.println("Erro ao abrir o arquivo");
		}
		
	    System.out.println("Iniciando AVL");
		String ent;
		StringTokenizer tokenizer;		
		AVL Arv = new AVL();
		System.out.println("Realizando Operacoes");
		
		while ((ent=read())!=null){
			tokenizer = new StringTokenizer(ent);
			int n = Integer.parseInt(tokenizer.nextToken());
			if(n == 0)
			    break;			
			if(n > 0){
			    Arv.inserir(new Integer(n));			    
			}else{
			    Arv.remover(new Integer(Math.abs(n)));
			}			
		}
		
		System.out.println("Rotacoes...");
		ArrayList<String> op = Arv.getOperacoes();
		
		for (String s : op){
			System.out.println(s);
		}
		
	}
}


