package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.entity.Usuario;
import com.coursera.ita.exception.FalhaCadastrarUsuario;
import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "cadastrar", urlPatterns = { "/cadastrar" })
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cadastrar.jsp")
								.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));

		try {
			new UsuarioRN().cadastrar(usuario);

			response.sendRedirect("index.jsp");

		} catch (FalhaCadastrarUsuario fcu) {
			request.setAttribute("usuario", usuario);
			request.setAttribute("msg", fcu.getMessage());

			request.getRequestDispatcher("cadastrar.jsp")
			.forward(request, response);
		}
	}

}
