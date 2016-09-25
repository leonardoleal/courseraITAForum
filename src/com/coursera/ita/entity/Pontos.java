package com.coursera.ita.entity;

public enum Pontos {

	NOVO_TOPICO(10)
	,NOVO_COMENTARIO(3);

	private int pontos;
	Pontos(int pontos) {
		this.pontos = pontos;
	}

	public int getValue(){
		return pontos;
	}
}
