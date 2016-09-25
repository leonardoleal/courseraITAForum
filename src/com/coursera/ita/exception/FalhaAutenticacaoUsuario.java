package com.coursera.ita.exception;

public class FalhaAutenticacaoUsuario extends Exception {

	private static final long serialVersionUID = 1L;

	public FalhaAutenticacaoUsuario() {
		super("Não foi possível autenticar o usuário.");
	}
}
