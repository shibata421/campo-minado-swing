package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
	Campo (int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho (Campo candidatoDeVizinho) {
		boolean linhaDiferente = linha != candidatoDeVizinho.linha;
		boolean colunaDiferente = coluna != candidatoDeVizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - candidatoDeVizinho.linha);
		int deltaColuna = Math.abs(coluna - candidatoDeVizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(candidatoDeVizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(candidatoDeVizinho);
			return true;
		} else {
			return false;
		}
	}
	
	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				// TODO Implementar nova versão
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
		minado = true;
	}
	
	boolean isMinado() {
		return minado;
	}
	
	boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	boolean isAberto() {
		return aberto;
	}
	
	boolean isFechado() {
		return !isAberto();
	}

	int getLinha() {
		return linha;
	}

	int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado ^ protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
}
