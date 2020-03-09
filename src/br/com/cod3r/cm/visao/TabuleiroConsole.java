package br.com.cod3r.cm.visao;

import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner sc = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while (continuar) {
				cicloDoJogo();
				
				System.out.println("Outra Partida? (S/n)");
				String resposta = sc.nextLine();
				
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
					throw new SairException();
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			sc.close();
		}
		
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (linha, coluna): ");
				
//				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
//											.map(e -> Integer.parseInt(e.trim()))
//											.iterator();
//				
//				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
//				
//				if ("1".equals(digitado)) {
//					tabuleiro.abrir(xy.next(), xy.next());
//				} else if ("2".equals(digitado)){
//					tabuleiro.alterarMarcacao(xy.next(), xy.next());
//				}
				
				String[] coordenadas = digitado.split(",");
				int coordenadaX = Integer.parseInt(coordenadas[0].trim());
				int coordenadaY = Integer.parseInt(coordenadas[1].trim());
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				System.out.println();
				
				if ("1".equals(digitado)) {
					tabuleiro.abrir(coordenadaX, coordenadaY);
				} else if ("2".equals(digitado)){
					tabuleiro.alterarMarcacao(coordenadaX, coordenadaY);
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
		
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = sc.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
