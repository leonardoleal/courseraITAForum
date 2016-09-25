package com.coursera.ita.entity;

import java.io.Serializable;

public class Topico implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id_topico;
	private String titulo;
	private String conteudo;
	private Usuario usuario;

	public Topico() {}

	public Topico(int id) {
		this.id_topico = id;
	}

	public int getId() {
		return id_topico;
	}

	public void setId(int id_topico) {
		this.id_topico = id_topico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
