package com.coursera.ita.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.ita.entity.Usuario;
import com.coursera.ita.exception.FalhaAutenticacaoUsuario;
import com.coursera.ita.rn.UsuarioRN;

@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String logout = request.getParameter("logout");
		if (logout != null && logout.equals("true")) {
			request.getSession().invalidate();
			request.setAttribute("msg", "Deslogado com sucesso!");
		}

		request.getRequestDispatcher("index.jsp")
								.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));

		try {
			request.getSession().setAttribute("usuario",
							new UsuarioRN().autenticar(usuario));

			response.sendRedirect("topicos");

		} catch (FalhaAutenticacaoUsuario fau) {
			request.setAttribute("login", usuario.getLogin());
			request.setAttribute("msg", fau.getMessage());

			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}
	}

}
