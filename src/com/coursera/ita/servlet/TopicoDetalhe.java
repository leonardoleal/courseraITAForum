package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.entity.Comentario;
import com.coursera.ita.entity.Topico;
import com.coursera.ita.entity.Usuario;
import com.coursera.ita.exception.FalhaInserirComentario;
import com.coursera.ita.exception.FalhaSelecionarTopico;
import com.coursera.ita.rn.ComentarioRN;
import com.coursera.ita.rn.TopicoRN;
import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "topico-detalhe", urlPatterns = { "/topico-detalhe" })
public class TopicoDetalhe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		TopicoRN topicoRN = new TopicoRN();

		try {
			Topico topico = topicoRN.buscarPorId(request.getParameter("id"));
			request.setAttribute("topico", topico);

			request.setAttribute("comentarios",
						new ComentarioRN().buscarPorTopicoId(topico.getId()));
		} catch (FalhaSelecionarTopico e) {
			request.setAttribute("msg", e.getMessage());
		}

		request.getRequestDispatcher("topico-detalhe.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!UsuarioRN.isLogado(request.getSession())) { response.sendRedirect("login"); return; }

		Comentario comentario = new Comentario();
		comentario.setComentario(request.getParameter("comentario"));
		comentario.setTopico(new Topico(Integer.parseInt(request.getParameter("id"))));
		comentario.setUsuario((Usuario) request.getSession().getAttribute("usuario"));

		try {
			new ComentarioRN().cadastrar(comentario);
		} catch (FalhaInserirComentario fit) {
			request.setAttribute("msg", fit.getMessage());
		}

		doGet(request, response);
	}

}
