package com.coursera.ita.exception;

public class FalhaSelecionarTopico extends Exception {
	private static final long serialVersionUID = 1L;

	private final static String MSG = "Não foi possível encontrar os detalhes do tópico. ";

	public FalhaSelecionarTopico() {
		super(MSG);
	}

	public FalhaSelecionarTopico(String complementoMsg) {
		super(MSG + complementoMsg);
	}
}
