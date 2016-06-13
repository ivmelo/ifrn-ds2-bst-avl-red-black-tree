
public class ArvoreRN {
	
	No raiz = null;
	
	public void inserir(int num) {
		No n = new No(num);
		System.out.println("Inserindo " + num);
		inserirNo(this.raiz, n);
	}
	
	public void inserirNo(No noInicial, No noInserir) {
		if (noInicial == null) { // no nulo, arvore vazia.
			this.raiz = noInserir; // insere na raiz da arvore.
			atualizarBalanceamento(noInserir);
		} else {
			if (noInserir.getChave() < noInicial.getChave()) {
				if (noInicial.getFilhoEsquerdo() == null) { // filho esquerdo livre, inserir aqui.
					noInicial.setFilhoEsquerdo(noInserir);
					noInserir.setPai(noInicial);
					
					atualizarBalanceamento(noInserir);
									
				} else { // filho esquerdo presente, tentar inserir no filho do esquerdo.
					
					inserirNo(noInicial.getFilhoEsquerdo(), noInserir);					
				}
			}
			
			else if (noInserir.getChave() > noInicial.getChave()) { // ou se o valor noInserir for maior que o valor noInicial
				if (noInicial.getFilhoDireito() == null) { // e se o filho direito for nulo
					noInicial.setFilhoDireito(noInserir); // setar o valor noInserir como filho dieito do valor noInicial
					noInserir.setPai(noInicial); // o pai do valor inserido ser· o valor noInicial
										
					atualizarBalanceamento(noInserir);

				} 
				else { // se o filho direito j· tiver preenchido 
					//System.out.println("verificando... right");

					inserirNo(noInicial.getFilhoDireito(), noInserir); //chama o mÈtodo novamente, passando o filho direito como noInicial
				}

			}
		}
		
	}
	
	public void atualizarBalanceamento(No no) {
		// raiz e sempre negra
		if (no.getPai() == null) {
			no.setCor(no.NEGRO);
		}
		
		if (no.getPai() == null || no.getPai().cor == no.NEGRO) {
			// caso 1, fazer nada
			System.out.println("caso 1");

		} else if (no.getPai() != null && no.getPai().getCor() == no.RUBRO && no.getAvo() != null && no.getAvo().getCor() == no.NEGRO) {
			//caso 2
			if (no.getPai().getIrmao() != null && no.getPai().getIrmao().getCor() == no.RUBRO) {
				// caso 2, basta recolorir e ir subindo na arvore...
				System.out.println("caso 2");
				
				No avo = no.getAvo();
				if (avo.getFilhoDireito() != null) {
					avo.getFilhoDireito().setCor(no.NEGRO);
				}
				if (avo.getFilhoEsquerdo() != null) {
					avo.getFilhoEsquerdo().setCor(no.NEGRO);
				}
				avo.setCor(no.RUBRO);
				
				// se avo eh raiz, avo vai ser negro.
				if (avo.ehRaiz())
					avo.setCor(no.NEGRO);
				
				// atualizar pai recursivamente (ir subindo na arvore)
				atualizarBalanceamento(avo);
			} else if (no.getPai().getIrmao() == null || no.getPai().getIrmao().getCor() == no.NEGRO) {
				System.out.println("caso 3");
				
				if (no.getAvo().getFilhoEsquerdo() == no.getPai()) {
					if (no.getPai().getFilhoEsquerdo() == no) {
						//caso 3a - rotacao direita simples
						System.out.println("caso 3a");
						No temp = no.getAvo();
						temp = rotacaoDireita(temp);
						
						recolorirCaso3(temp);
					} else {
						// caso 3d - rotacao direita dupla
						System.out.println("caso 3d");
						
						No tempPai = no.getPai();
						No tempAvo = no.getAvo();
						
						imprimir(tempAvo);
						
						tempPai = rotacaoEsquerda(tempPai);
						imprimir(tempAvo);
						tempAvo = rotacaoDireita(tempAvo);
						imprimir(tempAvo);
						
						recolorirCaso3(tempAvo);
					}
				} else {
					if (no.getPai().getFilhoDireito() == no) {
						// caso 3b - rotacao esquerda simples
						System.out.println("caso 3b");
						No temp = no.getAvo();
						temp = rotacaoEsquerda(temp);
						
						// ajustar cores
						recolorirCaso3(temp);
					} else {
						// caso 3c - rotacao esquerda dupla
						System.out.println("caso 3c");
						
						No tempPai = no.getPai();
						No tempAvo = no.getAvo();
						
						// Mostrar manipulacao no avo...
						imprimir(tempAvo);
						tempPai = rotacaoDireita(tempPai);
						imprimir(tempAvo);
						tempAvo = rotacaoEsquerda(tempAvo);
						imprimir(tempAvo);
						
						recolorirCaso3(tempAvo);
						
					}
				}
			}
		} 
	}
	
	// Pinta no de negro (n) e filhos de rubro (r).
	//      n
	//     / \
	//    r   r
	public No recolorirCaso3 (No no) {
		// ajustar cores
		if(no.getFilhoDireito() != null)
			no.getFilhoDireito().setCor(no.RUBRO);
		
		if(no.getFilhoEsquerdo() != null)
			no.getFilhoEsquerdo().setCor(no.RUBRO);
		
		no.setCor(no.NEGRO);
		
		return no;
	}
	
	//     ROTACAO A DIREITA
	//
	//       X            Y
	//      / \          / \
	//     Y   b   =>   a   X
	//    / \              / \
	//   a   z            z   b
	// 
	//     a - Y - z - X - b
	public No rotacaoDireita(No inicial) {
			
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
		} else {
			raiz = esquerdo;
		}
		
		return esquerdo;
	}
	
	//     ROTACAO A ESQUERDA
	//
	//       X            Y
	//      / \          / \
	//     a   Y   =>   X   b
	//        / \      / \
	//       z   b    a   z
	//
	//     a - X - z - Y - b
	//
	public No rotacaoEsquerda(No inicial) {

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
		} else {
			raiz = direito;
		}

		return direito;
	}
	
	// Imprime arvore e quebra a linha
	public void imprimir(No no) {
		imprimirNo(no);
		System.out.println();
	}
	
	// Imprime nos recursivamente RN
	public void imprimirNo(No no) {
		System.out.print("(");
		
		if (no.getFilhoEsquerdo() != null) {
			imprimirNo(no.getFilhoEsquerdo());
		}
		
		System.out.print(no.getChave());
		
		// Imprime cor do no
		if (no.getCor() == no.NEGRO)
			System.out.print('N');
		else
			System.out.print('R');
		
		if (no.getFilhoDireito() != null) {
			imprimirNo(no.getFilhoDireito());
		}
		
		System.out.print(")");
	}

}
