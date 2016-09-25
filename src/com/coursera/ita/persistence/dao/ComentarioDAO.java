package com.coursera.ita.persistence.dao;

import java.util.List;

import com.coursera.ita.entity.Comentario;

public interface ComentarioDAO {

	public List<Comentario> buscarPorIdTopico(int id);

	public void inserir(Comentario comentario);

}
