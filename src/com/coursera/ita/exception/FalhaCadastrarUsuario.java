package com.coursera.ita.exception;

public class FalhaCadastrarUsuario extends Exception {
	private static final long serialVersionUID = 1L;

	private final static String MSG = "Não foi possível cadastrar o usuário. ";

	public FalhaCadastrarUsuario() {
		super(MSG);
	}

	public FalhaCadastrarUsuario(String complementoMsg) {
		super(MSG + complementoMsg);
	}

}
