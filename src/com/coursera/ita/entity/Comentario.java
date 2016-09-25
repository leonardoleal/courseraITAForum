package com.coursera.ita.entity;

import java.io.Serializable;

public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id_comentario;
	private String comentario;
	private Usuario usuario;
	private Topico topico;

	public int getId() {
		return id_comentario;
	}

	public void setId(int id_comentario) {
		this.id_comentario = id_comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}
}
