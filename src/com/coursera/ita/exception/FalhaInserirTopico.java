package com.coursera.ita.exception;

public class FalhaInserirTopico extends Exception {
	private static final long serialVersionUID = 1L;

	private final static String MSG = "Não foi possível criar um novo tópico. ";

	public FalhaInserirTopico() {
		super(MSG);
	}

	public FalhaInserirTopico(String complementoMsg) {
		super(MSG + complementoMsg);
	}

}
