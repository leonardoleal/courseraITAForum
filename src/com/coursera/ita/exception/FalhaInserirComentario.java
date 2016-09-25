package com.coursera.ita.exception;

public class FalhaInserirComentario extends Exception {
	private static final long serialVersionUID = 1L;

	private final static String MSG = "Não foi possível criar um novo comentário. ";

	public FalhaInserirComentario() {
		super(MSG);
	}

	public FalhaInserirComentario(String complementoMsg) {
		super(MSG + complementoMsg);
	}

}
