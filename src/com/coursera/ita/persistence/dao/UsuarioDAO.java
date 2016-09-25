package com.coursera.ita.persistence.dao;

import java.util.List;

import com.coursera.ita.entity.Usuario;

public interface UsuarioDAO {

	public void inserir(Usuario usuario);

	public void adcionarPontos(Usuario usuario, int pontos);

	public Usuario buscarPorLoginESenha(Usuario usuario);

	public List<Usuario> buscarRankingPontos();

}
