<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" href="./assets/css/estilo.css" />
        <title>Forúm ITA - Cadastrar</title>
    </head>

    <body>
        <div align="center">
            <h1>Fórum ITA</h1>

            <h3>Cadastro no fórum</h3>

            <p class="msg alerta">
                ${msg}
            </p>

            <form action="cadastrar" method="post" class="cadastro">
                <label for="nome">Nome</label>
                <input type="text" name="nome" value="${usuario.nome}" />

                <label for="email">E-mail</label>
                <input type="text" name="email" value="${usuario.email}" />

                <label for="login">Login</label>
                <input type="text" name="login" value="${usuario.login}" />

                <label for="senha">Senha</label>
                <input type="password" name="senha" />

                <input type="submit" name="submit" value="Cadastrar"/>
                <a href="login">Voltar</a>
            </form>
        </div>
    </body>
</html>