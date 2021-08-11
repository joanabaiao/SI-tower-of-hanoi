
/**
 * @author Joana Baião && Filipe Amado
 */

import java.util.Scanner;

public class Projeto1 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("******************** Torre de Hanoi ********************");

		// PEDIR NÚMERO DE DISCOS (maior que 3 e menor que 10)
		System.out.println("Número de discos (de 3 a 10): ");
		while (scan.hasNextInt() == false) {
			System.out.println("Valor inválido! Introduza o número de discos (de 3 a 10): ");
			scan.nextLine();
		}
		int n = scan.nextInt();
		scan.nextLine();

		while (n < 3 || n > 10) {
			System.out.println("Valor inválido! Introduza o número de discos (de 3 a 10): ");
			while (scan.hasNextInt() == false) {
				System.out.println("Valor inválido! Introduza o número de discos (de 3 a 10): ");
				scan.nextLine();
			}
			n = scan.nextInt();
			scan.nextLine();
		}

		// FRASE A INFORMAR O NÚMERO MÍNIMO DE JOGADAS DE ACORDO COM O NÚMERO DE DISCOS
		int min_jogadas = (int) Math.pow(2, n) - 1; // número mínimo de jogadas (valor ideal)
		System.out.println("O número mínimo de jogadas é " + min_jogadas + ".");
		System.out.println("********************************************************");
		System.out.println();

		// JOGO INICIAL
		int linhas = n + 3;
		int[] pinoA = new int[linhas]; // Criação de um array para cada pino
		int[] pinoB = new int[linhas];
		int[] pinoC = new int[linhas];
		int total = 0; // número total de jogadas

		/*
		 * Inicialmente existem n discos no pino A, sendo o tamanho do disco menor igual
		 * a 3 qualquer que seja o número de discos. As linhas com discos são definidas
		 * por um número inteiro que caracteriza o tamanho do disco. Os pinos, a base e
		 * a linha identificativa têm o valor 0.
		 */

		for (int i = 0; i < pinoA.length; i++) {
			pinoB[i] = 0;
			pinoC[i] = 0;
			if (i == 0 || i == pinoA.length - 1 || i == pinoA.length - 2) {
				pinoA[i] = 0;
			} else {
				pinoA[i] = (2 * (i + 1)) - 1;
			}
		}
		System.out.println("Número de movimentos: " + total);
		System.out.println();
		esquemaABC(pinoA, pinoB, pinoC, n); // Chama o método "esquema" que faz o desenho dos pinos

		// CICLO DO JOGO - termina quando todos os discos estiverem em C, ou seja
		// n_discosC=n;
		int n_discosA = n; // Número de discos em cada pino
		int n_discosB = 0;
		int n_discosC = 0;

		boolean terminar = false;
		while (n_discosC != n && terminar == false) {

			// OPÇÃO DE DESISTIR Y
			System.out.println();
			System.out.println("Quer desistir? Sim [Y]. Caso contrário prima outra tecla qualquer.");
			String desistir = scan.nextLine();

			if (desistir.equals("Y") || desistir.equals("y")) {
				System.out.println("Desistiu! Jogo terminado.");
				terminar = true;
			}

			else if (terminar == false) {

				// ESCOLHA DOS MOVIMENTOS E VALIDAÇÃO
				System.out.println("Escolha um movimento possível: ");
				System.out.println("1: A->B      2: A->C      3: B->A      4: B->C      5: C->A      6: C->B");

				while (scan.hasNextInt() == false) {
					System.out.println("Valor inválido! Escolha novamente um movimento possível: ");
					scan.nextLine();
				}
				int jogada = scan.nextInt();
				scan.nextLine();

				while (jogada < 1 || jogada > 6) {
					System.out.println("Valor inválido! Escolha novamente um movimento possível: ");
					while (scan.hasNextInt() == false) {
						System.out.println("Valor inválido! Escolha novamente um movimento possível: ");
						scan.nextLine();
					}
					jogada = scan.nextInt();
					scan.nextLine();
				}

				// MOVIMENTO 1 (A->B)
				if (jogada == 1) {
					if (movimento(pinoA, pinoB, n_discosA, n_discosB) == true) {
						total++;
						n_discosA--;
						n_discosB++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// MOVIMENTO 2 (A->C)
				else if (jogada == 2) {
					if (movimento(pinoA, pinoC, n_discosA, n_discosC) == true) {
						total++;
						n_discosA--;
						n_discosC++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// MOVIMENTO 3 (B->A)
				else if (jogada == 3) {
					if (movimento(pinoB, pinoA, n_discosB, n_discosA) == true) {
						total++;
						n_discosB--;
						n_discosA++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// MOVIMENTO 4 (B->C)
				else if (jogada == 4) {
					if (movimento(pinoB, pinoC, n_discosB, n_discosC) == true) {
						total++;
						n_discosB--;
						n_discosC++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// MOVIMENTO 5 (C->A)
				else if (jogada == 5) {
					if (movimento(pinoC, pinoA, n_discosC, n_discosA) == true) {
						total++;
						n_discosC--;
						n_discosA++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// MOVIMENTO 6 (C->B)
				else if (jogada == 6) {
					if (movimento(pinoC, pinoB, n_discosC, n_discosB) == true) {
						total++;
						n_discosC--;
						n_discosB++;
					} else {
						System.out.println("ERRO: O movimento escolhido não é válido!");
					}
				}

				// ESQUEMA APÓS MOVIMENTO ESCOLHIDO
				System.out.println();
				System.out.println("Número de movimentos: " + total); // Mostra o numero total de movimentos realizados
				System.out.println();
				esquemaABC(pinoA, pinoB, pinoC, n); // Esquema
			}
		}

		// MENSAGEM FINAL
		if (n_discosC == n) {
			System.out.println("Parabéns! Acabou o jogo. Realizou " + total + " movimentos."); // Mensagem a informar
																								// que o jogador acabou

			// Comparação entre o número de jogadas realizadas e o número mínimo
			if (total == min_jogadas) {
				System.out.println("Completou o jogo com o número mínimo de jogadas!");
			} else {
				System.out.println("O número mínimo de jogadas para " + n + " discos é " + min_jogadas
						+ ". Realizou mais " + (total - min_jogadas) + " jogadas que o ideal.");
			}
		}

		scan.close();
	}

	// ESQUEMA DO JOGO
	public static void esquemaABC(int[] pinoA, int[] pinoB, int[] pinoC, int n) {
		int colunas = 2 * n + 3;
		for (int i = 0; i < pinoA.length; i++) {
			// Esquema do pino A
			for (int j = 0; j < colunas; j++) {
				if (i == pinoA.length - 1) {
					if (j == (colunas - 1) / 2) {
						System.out.print("A");
					} else {
						System.out.print(" ");
					}
				} else if (i == pinoA.length - 2) {
					System.out.print("#");
				} else if (pinoA[i] == 0) {
					if (j == (colunas - 1) / 2) {
						System.out.print("|");
					} else {
						System.out.print(" ");
					}
				} else {
					int k = (colunas - pinoA[i]) / 2;
					if (j < k || j >= (pinoA[i] + k)) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
			}
			// Esquema do pino B
			for (int j = 0; j < colunas; j++) {
				if (i == pinoB.length - 1) {
					if (j == (colunas - 1) / 2) {
						System.out.print("B");
					} else {
						System.out.print(" ");
					}
				} else if (i == pinoB.length - 2) {
					System.out.print("#");
				} else if (pinoB[i] == 0) {
					if (j == (colunas - 1) / 2) {
						System.out.print("|");
					} else {
						System.out.print(" ");
					}
				} else {
					int k = (colunas - pinoB[i]) / 2;
					if (j < k || j >= (pinoB[i] + k)) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
			}
			// Esquema do pino C
			for (int j = 0; j < colunas; j++) {
				if (i == pinoC.length - 1) {
					if (j == (colunas - 1) / 2) {
						System.out.print("C");
					} else {
						System.out.print(" ");
					}
				} else if (i == pinoC.length - 2) {
					System.out.print("#");
				} else if (pinoC[i] == 0) {
					if (j == (colunas - 1) / 2) {
						System.out.print("|");
					} else {
						System.out.print(" ");
					}
				} else {
					int k = (colunas - pinoC[i]) / 2;
					if (j < k || j >= (pinoC[i] + k)) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
			}
			System.out.println();
		}
	}

	// MOVIMENTOS (pino inicial I -> pino final F)
	public static boolean movimento(int[] pinoI, int[] pinoF, int n_discosI, int n_discosF) {
		boolean movimentoIF = false;
		int linhaF = (pinoF.length - 3 - n_discosF);

		for (int i = 0; i < pinoI.length - 1; i++) {
			if (pinoI[i] != 0 && pinoF[linhaF] == 0) {
				if ((pinoF[linhaF + 1] > pinoI[i]) || pinoF[pinoF.length - 3] == 0) {
					movimentoIF = true;
					int troca = pinoI[i];
					pinoI[i] = 0;
					pinoF[linhaF] = troca;
				}
			}
		}

		return movimentoIF;
	}
}