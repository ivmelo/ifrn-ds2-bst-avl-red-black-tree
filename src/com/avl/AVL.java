package com.avl;
import java.util.ArrayList;


public class AVL {
	No raiz = null;
	ArrayList<String> operacoes = new ArrayList<String>();
// ------------------ INSER«√O ----------------------- 
	public void inserir(int num) {
		No n = new No(num);
		System.out.println("Inserindo " + num);
		inserirNo2(this.raiz, n);
	}
	
	// atualiza o balanceamento dos nos sem verificar a altura ;)
	// este metodo eh chamado passando o lado no qual o filho foi adicionado
	public void atualizarBalanceamento(No node, char position) {
		
		// se o filho foi inserido a esquerda
		if (position == 'l') {
			// adiciona-se um ao fator balanceamento
			node.setFatorBalanceamento(node.getFatorBalanceamento() + 1);
		} else {
			// subtrai-se do fator balanceamento
			node.setFatorBalanceamento(node.getFatorBalanceamento() - 1);
		}
		
		// verifica se esta desbalanceada, efetua as rotacoes, e re-balanceia
		verificarBalanceamento2(node);
		
		// se o fator for diferente de zero, 
		// eh necessario continuar atualizando os pais
		if (node.getFatorBalanceamento() != 0) {
			
			// se nao for o raiz, continua atualizando recursivamente
			if (node.getPai() != null) {
				if (node == node.getPai().getFilhoEsquerdo()) {
					// filho esquerdo
					atualizarBalanceamento(node.getPai(), 'l');
				}
				else {
					// filho direito
					atualizarBalanceamento(node.getPai(), 'r');
				}
			}
		}
		
	}
	
	public void inserirNo2(No noInicial, No noInserir) {
		if (noInicial == null) { // no nulo, arvore vazia.
			this.raiz = noInserir; // insere na raiz da arvore.
		} else {
			if (noInserir.getChave() < noInicial.getChave()) {
				if (noInicial.getFilhoEsquerdo() == null) { // filho esquerdo livre, inserir aqui.
					noInicial.setFilhoEsquerdo(noInserir);
					noInserir.setPai(noInicial);
					
					atualizarBalanceamento(noInserir.getPai(), 'l');
									
				} else { // filho esquerdo presente, tentar inserir no filho do esquerdo.
					
					inserirNo2(noInicial.getFilhoEsquerdo(), noInserir);					
				}
			}
			
			else if (noInserir.getChave() > noInicial.getChave()) { // ou se o valor noInserir for maior que o valor noInicial
				if (noInicial.getFilhoDireito() == null) { // e se o filho direito for nulo
					noInicial.setFilhoDireito(noInserir); // setar o valor noInserir como filho dieito do valor noInicial
					noInserir.setPai(noInicial); // o pai do valor inserido ser· o valor noInicial
										
					atualizarBalanceamento(noInserir.getPai(), 'r');

				} 
				else { // se o filho direito j· tiver preenchido 
					//System.out.println("verificando... right");

					inserirNo2(noInicial.getFilhoDireito(), noInserir); //chama o mÈtodo novamente, passando o filho direito como noInicial
				}

			}
		}
		
	}
	
	public void verificarBalanceamento2(No atual) {
		//setBalanceamento(atual);
		int balanceamento = atual.getFatorBalanceamento();
		
		if (balanceamento == -2) { // a sub-·rvore direita È maior que a esquerda
			if (atual.getFilhoDireito().getFatorBalanceamento() < 0) { // se o filho direito da sub-·rvore for maior que o esquerdo
				atual = rotacaoEsquerda(atual); // faz uma rotaÁ„o simples a esquerda
				operacoes.add("RotaÁ„o simples a esquerda");
			}
			else { // faz uma rotaÁ„o dupla a esquerda
				atual.setFilhoDireito(rotacaoDireita(atual.getFilhoDireito())); 
				atual = rotacaoEsquerda(atual);
				operacoes.add("RotaÁ„o dupla a esquerda");
			}
		} 
		else if (balanceamento == 2) { // a sub-·rvore esquerda È maior que a direita
			if (atual.getFilhoEsquerdo().getFatorBalanceamento() > 0) { // se o filho esquerdo da sub-·rvore for maior que o direito
				atual = rotacaoDireita(atual); // faz uma rotaÁ„o simples a direita
				operacoes.add("RotaÁ„o simples a direita");
			} 
			else { // faz uma rotaÁ„o dupla a direita
				atual.setFilhoEsquerdo(rotacaoEsquerda(atual.getFilhoEsquerdo()));
				atual = rotacaoDireita(atual);
				operacoes.add("RotaÁ„o dupla a direita");
			}
		}

		if (atual.getPai() != null) { // se o pai for != de null ainda n„o È a raiz 
			//verificarBalanceamento(atual.getPai()); // pega a pai do nÛ atual, para chegar a raiz
		} 
		else {
			this.raiz = atual;
		}
	}


	public void inserirNo(No noInicial, No noInserir) {
		if (noInicial == null) { // Se o no noInicial for nulo, a ·rvore est· vazia
			this.raiz = noInserir;	// o valor noInserir ser· a raiz da ·rvore
		} 
		else { // arvore com nÛ
			if (noInserir.getChave() < noInicial.getChave()) { // se o valor noInserir for menor que o valor noInicial
				if (noInicial.getFilhoEsquerdo() == null) { // e se o filho esquerdo for nulo
					noInicial.setFilhoEsquerdo(noInserir); // setar o valor noInserir como filho esquerdo do valor noInicial
					noInserir.setPai(noInicial); // o pai do valor inserido ser· o valor noInicial
					verificarBalanceamento(noInicial); // balanceamento do nÛ noInicial
					
				} 
				else { // se o filho esquerdo j· tiver preenchido 
					inserirNo(noInicial.getFilhoEsquerdo(), noInserir); //chama o mÈtodo novamente, passando o filho esquerdo como noInicial
				}
			} 
			else if (noInserir.getChave() > noInicial.getChave()) { // ou se o valor noInserir for maior que o valor noInicial
				if (noInicial.getFilhoDireito() == null) { // e se o filho direito for nulo
					noInicial.setFilhoDireito(noInserir); // setar o valor noInserir como filho dieito do valor noInicial
					noInserir.setPai(noInicial); // o pai do valor inserido ser· o valor noInicial
					verificarBalanceamento(noInicial); // balanceamento do nÛ noInicial
				} 
				else { // se o filho direito j· tiver preenchido 
					inserirNo(noInicial.getFilhoDireito(), noInserir); //chama o mÈtodo novamente, passando o filho direito como noInicial
				}
			} 
			else {
				// O nÛ noInserir È igual ao nÛ noInicial
			}
		}
	}
// --------------------------------------------------------
	
// ------------------ BALANCEAMENTO ----------------------- 

	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getFatorBalanceamento();
		
		if (balanceamento == -2) { // a sub-·rvore direita È maior que a esquerda
			if (altura(atual.getFilhoDireito().getFilhoDireito()) >= altura(atual.getFilhoDireito().getFilhoEsquerdo())) { // se o filho direito da sub-·rvore for maior que o esquerdo
				atual = rotacaoEsquerda(atual); // faz uma rotaÁ„o simples a esquerda
				operacoes.add("RotaÁ„o simples a esquerda");
			} 
			else { // faz uma rotaÁ„o dupla a esquerda
				atual.setFilhoDireito(rotacaoDireita(atual.getFilhoDireito())); 
				atual = rotacaoEsquerda(atual);
				operacoes.add("RotaÁ„o dupla a esquerda");
			}
		} 
		else if (balanceamento == 2) { // a sub-·rvore esquerda È maior que a direita
			if (altura(atual.getFilhoEsquerdo().getFilhoEsquerdo()) >= altura(atual.getFilhoEsquerdo().getFilhoDireito())) { // se o filho esquerdo da sub-·rvore for maior que o direito
				atual = rotacaoDireita(atual); // faz uma rotaÁ„o simples a direita
				operacoes.add("RotaÁ„o simples a direita");
			} 
			else { // faz uma rotaÁ„o dupla a direita
				atual.setFilhoEsquerdo(rotacaoEsquerda(atual.getFilhoEsquerdo()));
				atual = rotacaoDireita(atual);
				operacoes.add("RotaÁ„o dupla a direita");
			}
		}

		if (atual.getPai() != null) { // se o pai for != de null ainda n„o È a raiz 
			verificarBalanceamento(atual.getPai()); // pega a pai do nÛ atual, para chegar a raiz
		} 
		else {
			this.raiz = atual;
		}
	}
	
	private void setBalanceamento(No no) {
		no.setFatorBalanceamento(altura(no.getFilhoEsquerdo()) - altura(no.getFilhoDireito()));
		System.out.println("balance: (" + no.getChave() + "," + no.getFatorBalanceamento() + ")");
	}
	
	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}
		if (atual.getFilhoEsquerdo() == null && atual.getFilhoDireito() == null) {
			return 0;
		}
		else if (atual.getFilhoEsquerdo() == null) {
			return 1 + altura(atual.getFilhoDireito());
		}
		else if (atual.getFilhoDireito() == null) {
			return 1 + altura(atual.getFilhoEsquerdo());
		}
		else {
			return 1 + Math.max(altura(atual.getFilhoEsquerdo()), altura(atual.getFilhoDireito()));
		}
		
	}
	
public No rotacaoDireita1(No inicial) {
		
		//inicial.setFatorBalanceamento(inicial.getFatorBalanceamento() + (1 - Math.min(inicial.getFatorBalanceamento(), 0)));
		//inicial.getFilhoEsquerdo().setFatorBalanceamento(inicial.getFilhoEsquerdo().getFatorBalanceamento() + (1 + Math.max(inicial.getFatorBalanceamento(), 0)));
	
		No esquerdo = inicial.getFilhoEsquerdo(); // o nÛ esquerdo ser· o filho esquerdo do nÛ inicial
		esquerdo.setPai(inicial.getPai()); // o pai do nÛ esquerdo ser· o antigo pai do nÛ inicial

		inicial.setFilhoEsquerdo(esquerdo.getFilhoDireito()); // o filho direito do nÛ esquerdo ser· o filho esquerdo do nÛ inicial

		if (inicial.getFilhoEsquerdo() != null) { // se esse filho n„o for null
			inicial.getFilhoEsquerdo().setPai(inicial); // seu novo pai ser· o nÛ inicial
		}

		esquerdo.setFilhoDireito(inicial); // o nÛ inicial ser· o filho direito do nÛ esquerdo
		inicial.setPai(esquerdo); // o nÛ inicial ter· como pai o nÛ esquerdo

		if (esquerdo.getPai() != null) { // se o pai do nÛ esquerdo for diferente de null (o nÛ esquerdo n„o ser· a raiz da ·rvore) 
			if (esquerdo.getPai().getFilhoDireito() == inicial) {  // se o filho direito do pai do nÛ esquerdo for o nÛ inicial
				esquerdo.getPai().setFilhoDireito(esquerdo); // passar· a ter como filho direito o nÛ esquerdo
			} 
			else if (esquerdo.getPai().getFilhoEsquerdo() == inicial) { // se o filho esquerdo do pai do nÛ esquerdo for o nÛ inicial
				esquerdo.getPai().setFilhoEsquerdo(esquerdo); // passar· a ter como filho esquerdo o nÛ esquerdo
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerdo);
		
		return esquerdo;
	}
	
	public No rotacaoEsquerda1(No inicial) {

		No direito = inicial.getFilhoDireito(); // o nÛ direito ser· o filho direito do nÛ inicial
		direito.setPai(inicial.getPai()); // o pai do nÛ direito ser· o antigo pai do nÛ inicial

		inicial.setFilhoDireito(direito.getFilhoEsquerdo()); // o filho esquerdo do nÛ direito ser· o filho direito do nÛ inicial

		if (inicial.getFilhoDireito() != null) { // se esse filho n„o for null
			inicial.getFilhoDireito().setPai(inicial); // seu novo pai ser· o nÛ inicial
		}

		direito.setFilhoEsquerdo(inicial); // o nÛ inicial ser· o filho esquerdo do nÛ direito
		inicial.setPai(direito); // o nÛ inicial ter· como pai o nÛ direito

		if (direito.getPai() != null) { // se o pai do nÛ direito for diferente de null (o nÛ direito n„o ser· a raiz da ·rvore) 
			if (direito.getPai().getFilhoDireito() == inicial) { // se o filho direito do pai do nÛ direito for o nÛ inicial
				direito.getPai().setFilhoDireito(direito);	// passar· a ter como filho direito o nÛ direito
			}
			else if (direito.getPai().getFilhoEsquerdo() == inicial) { // se o filho esquerdo do pai do nÛ direito for o nÛ inicial
				direito.getPai().setFilhoEsquerdo(direito); // passar· a ter como filho esquerdo o nÛ direito
			}
		}

		return direito;
	}

	
	
	public No rotacaoEsquerda(No inicial) {

		No direito = inicial.getFilhoDireito(); // o nÛ direito ser· o filho direito do nÛ inicial
		direito.setPai(inicial.getPai()); // o pai do nÛ direito ser· o antigo pai do nÛ inicial
		
		// Guarda os antigos fatores de balanceamento.
		int oldBalInicial = inicial.getFatorBalanceamento();
		int oldBalDireito = direito.getFatorBalanceamento();

		inicial.setFilhoDireito(direito.getFilhoEsquerdo()); // o filho esquerdo do nÛ direito ser· o filho direito do nÛ inicial

		if (inicial.getFilhoDireito() != null) { // se esse filho n„o for null
			inicial.getFilhoDireito().setPai(inicial); // seu novo pai ser· o nÛ inicial
		}

		direito.setFilhoEsquerdo(inicial); // o nÛ inicial ser· o filho esquerdo do nÛ direito
		inicial.setPai(direito); // o nÛ inicial ter· como pai o nÛ direito

		if (direito.getPai() != null) { // se o pai do nÛ direito for diferente de null (o nÛ direito n„o ser· a raiz da ·rvore) 
			if (direito.getPai().getFilhoDireito() == inicial) { // se o filho direito do pai do nÛ direito for o nÛ inicial
				direito.getPai().setFilhoDireito(direito);	// passar· a ter como filho direito o nÛ direito
			} 
			else if (direito.getPai().getFilhoEsquerdo() == inicial) { // se o filho esquerdo do pai do nÛ direito for o nÛ inicial
				direito.getPai().setFilhoEsquerdo(direito); // passar· a ter como filho esquerdo o nÛ direito
			}
		}
		
		System.out.println("inicial:" + inicial.getFatorBalanceamento());
		System.out.println("direito:" + direito.getFatorBalanceamento());

		
		// Calculo do fator de balanceamento depois da rotacao.
		// Tirado de: http://www.oopweb.com/Algorithms/Documents/AvlTrees/Volume/AvlTrees.htm
		//inicial.setFatorBalanceamento(oldBalInicial - 1 - Math.max(oldBalDireito, 0));	
		//direito.setFatorBalanceamento(oldBalDireito - 1 + Math.min(inicial.getFatorBalanceamento(), 0));

		//inicial.setFatorBalanceamento(inicial.getFatorBalanceamento() - (1 + Math.max(inicial.getFatorBalanceamento(), 0)));
		//direito.setFatorBalanceamento(direito.getFatorBalanceamento() - (1 - Math.min(inicial.getFatorBalanceamento(), 0)));
		
		//direito.setFatorBalanceamento(direito.getFatorBalanceamento() - 1);
		//inicial.setFatorBalanceamento(- (direito.getFatorBalanceamento()));
		
		//oldRoot->myBal -=  factor * (1 + MAX(factor * root->myBal, 0));
	    //root->myBal    +=  factor * (1 + MAX(factor * oldRoot->myBal, 0));
	    
		//int fat = 1;
		
		//inicial.setFatorBalanceamento(inicial.getFatorBalanceamento() - fat * (1 + Math.max(fat * direito.getFatorBalanceamento(), 0)));
	    //direito.setFatorBalanceamento(direito.getFatorBalanceamento() + fat * (1 + Math.max(fat * inicial.getFatorBalanceamento(), 0)));
		
		setBalanceamento(inicial);
		setBalanceamento(direito);
		
		return direito;
	}

	public No rotacaoDireita(No inicial) {

		No esquerdo = inicial.getFilhoEsquerdo(); // o nÛ esquerdo ser· o filho esquerdo do nÛ inicial
		esquerdo.setPai(inicial.getPai()); // o pai do nÛ esquerdo ser· o antigo pai do nÛ inicial
		
		// Guarda os antigos fatores de balanceamento.
		int oldBalInicial = inicial.getFatorBalanceamento();
		int oldBalEsquerdo = esquerdo.getFatorBalanceamento();

		inicial.setFilhoEsquerdo(esquerdo.getFilhoDireito()); // o filho direito do nÛ esquerdo ser· o filho esquerdo do nÛ inicial

		if (inicial.getFilhoEsquerdo() != null) { // se esse filho n„o for null
			inicial.getFilhoEsquerdo().setPai(inicial); // seu novo pai ser· o nÛ inicial
		}

		esquerdo.setFilhoDireito(inicial); // o nÛ inicial ser· o filho direito do nÛ esquerdo
		inicial.setPai(esquerdo); // o nÛ inicial ter· como pai o nÛ esquerdo

		if (esquerdo.getPai() != null) { // se o pai do nÛ esquerdo for diferente de null (o nÛ esquerdo n„o ser· a raiz da ·rvore) 
			if (esquerdo.getPai().getFilhoDireito() == inicial) {  // se o filho direito do pai do nÛ esquerdo for o nÛ inicial
				esquerdo.getPai().setFilhoDireito(esquerdo); // passar· a ter como filho direito o nÛ esquerdo
			} 
			else if (esquerdo.getPai().getFilhoEsquerdo() == inicial) { // se o filho esquerdo do pai do nÛ esquerdo for o nÛ inicial
				esquerdo.getPai().setFilhoEsquerdo(esquerdo); // passar· a ter como filho esquerdo o nÛ esquerdo
			}
		}
		
		//inicial.setFatorBalanceamento(oldBalInicial + 1 + Math.min(oldBalEsquerdo, 0));

		//inicial.setFatorBalanceamento(oldBalInicial + 1 - Math.min(oldBalEsquerdo, 0));	
		//esquerdo.setFatorBalanceamento(oldBalEsquerdo + 1 + Math.max(inicial.getFatorBalanceamento(), 0));
		
	       // update balances
	     //oldRoot->myBal +=  (1 - MIN(root->myBal, 0));
	    // root->myBal    +=  (1 + MAX(oldRoot->myBal, 0));
	     
	    //inicial.setFatorBalanceamento(inicial.getFatorBalanceamento() + (1 + Math.min(esquerdo.getFatorBalanceamento(), 0)));
	    //esquerdo.setFatorBalanceamento(esquerdo.getFatorBalanceamento() + (1 + Math.max(inicial.getFatorBalanceamento(), 0)));
	    
		//inicial.setFatorBalanceamento(inicial.getFatorBalanceamento() + (1 - Math.min(inicial.getFatorBalanceamento(), 0)));
		//esquerdo.setFatorBalanceamento(esquerdo.getFatorBalanceamento() + (1 + Math.max(inicial.getFatorBalanceamento(), 0)));
		
		
		setBalanceamento(inicial);
		setBalanceamento(esquerdo);

		return esquerdo;
	}

// --------------------------------------------------------
	
// ---------------------- REMO«√O ------------------------
	public void remover(int num) {
		System.out.println("Removendo " + num);
		removerNo(this.raiz, num);
	}

	public void removerNo(No atual, int num) {
		if (atual == null) { // ·rvore vazia
			operacoes.add("¡rvore vazia");
			return;
		} else {
			if (num < atual.getChave()) { 
				removerNo(atual.getFilhoEsquerdo(), num);
			} 
			else if (num > atual.getChave()) {
				removerNo(atual.getFilhoDireito(), num);
			} 
			else if (atual.getChave() == num) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No r;
		
		if (aRemover.getFilhoEsquerdo() == null || aRemover.getFilhoDireito() == null) {
			if (aRemover.getPai() == null) {
				// remove raiz
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;
		} 
		else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getFilhoEsquerdo() != null) {
			p = r.getFilhoEsquerdo();
		} 
		else {
			p = r.getFilhoDireito();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} 
		else {
			if (r == r.getPai().getFilhoEsquerdo()) {
				r.getPai().setFilhoEsquerdo(p);
			} 
			else {
				r.getPai().setFilhoDireito(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}
	
	public No sucessor(No s) {
		if (s.getFilhoDireito() != null) {
			No r = s.getFilhoDireito();
			while (r.getFilhoEsquerdo() != null) {
				r = r.getFilhoEsquerdo();
			}
			return r;
		} 
		else {
			No p = s.getPai();
			while (p != null && s == p.getFilhoDireito()) {
				s = p;
				p = s.getPai();
			}
			return p;
		}
	}
// --------------------------------------------------------
	
// ------------ IMPRIMIR ¡RVORE NA ORDEM ------------------ 
	final protected ArrayList<No> mostrarEmOrdem() {
		ArrayList<No> ret = new ArrayList<No>();
		mostrarEmOrdem(raiz, ret);
		return ret;
	}

	final protected void mostrarEmOrdem(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		mostrarEmOrdem(no.getFilhoEsquerdo(), lista);
		lista.add(no);
		mostrarEmOrdem(no.getFilhoDireito(), lista);
	}
// --------------------------------------------------------
	
	public ArrayList<String> getOperacoes() {
		return operacoes;
	}
	
}
