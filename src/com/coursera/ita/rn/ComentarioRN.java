package com.coursera.ita.rn;

import java.util.List;

import com.coursera.ita.entity.Comentario;
import com.coursera.ita.entity.Pontos;
import com.coursera.ita.exception.FalhaInserirComentario;
import com.coursera.ita.persistence.ComentarioPostgreDAO;
import com.coursera.ita.persistence.UsuarioPostgreDAO;
import com.coursera.ita.persistence.dao.ComentarioDAO;
import com.coursera.ita.persistence.dao.UsuarioDAO;

public class ComentarioRN {

	public List<Comentario> buscarPorTopicoId(int id) {
		ComentarioDAO comentarioDAO = new ComentarioPostgreDAO();

		return comentarioDAO.buscarPorIdTopico(id);
	}

	public void cadastrar(Comentario comentario) throws FalhaInserirComentario {
		ComentarioDAO comentarioDAO = new ComentarioPostgreDAO();
		UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();

		comentarioDAO.inserir(comentario);
		usuarioDAO.adcionarPontos(comentario.getUsuario(),
				Pontos.NOVO_COMENTARIO.getValue());
	}

}
