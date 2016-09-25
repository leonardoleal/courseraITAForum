package com.coursera.ita.persistence.dao;

import java.util.List;

import com.coursera.ita.entity.Topico;

public interface TopicoDAO {

	public List<Topico> buscarTodosCabecalhos();

	public void inserir(Topico topico);

	public Topico buscarPorId(int id);

}
