package com.coursera.ita.rn;

import java.util.List;

import com.coursera.ita.entity.Pontos;
import com.coursera.ita.entity.Topico;
import com.coursera.ita.exception.FalhaInserirTopico;
import com.coursera.ita.exception.FalhaSelecionarTopico;
import com.coursera.ita.persistence.TopicoPostgreDAO;
import com.coursera.ita.persistence.UsuarioPostgreDAO;
import com.coursera.ita.persistence.dao.TopicoDAO;
import com.coursera.ita.persistence.dao.UsuarioDAO;

public class TopicoRN {

	public List<Topico> buscarTodosCabecalhos() {
		TopicoDAO topicoDAO = new TopicoPostgreDAO();

		return topicoDAO.buscarTodosCabecalhos();
	}

	public void cadastrar(Topico topico) throws FalhaInserirTopico {
		TopicoDAO topicoDAO = new TopicoPostgreDAO();
		UsuarioDAO usuarioDAO = new UsuarioPostgreDAO();

		if (topico.getTitulo() == null || topico.getTitulo().isEmpty()
				|| topico.getConteudo() == null || topico.getConteudo().isEmpty()
				|| topico.getUsuario().getLogin() == null || topico.getUsuario().getLogin().isEmpty()) {
			throw new FalhaInserirTopico("Todos os campos devem ser preenchidos.");
		}

		topicoDAO.inserir(topico);
		usuarioDAO.adcionarPontos(topico.getUsuario(),
					Pontos.NOVO_TOPICO.getValue());
	}

	public Topico buscarPorId(String id) throws FalhaSelecionarTopico {
		TopicoDAO topicoDAO = new TopicoPostgreDAO();

		try {
			Integer id_topico = Integer.parseUnsignedInt(id);
			return topicoDAO.buscarPorId(id_topico);
		} catch (Exception e) {
			throw new FalhaSelecionarTopico("Parâmetro inválido!!");
		}
	}

}
