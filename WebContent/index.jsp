<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" href="./assets/css/estilo.css" />
        <title>Forúm ITA - Login</title>
    </head>

    <body>
        <div align="center">
            <h1>Fórum ITA</h1>

            <p class="msg alerta">
                ${msg}
            </p>

            <form action="login" method="post" class="login">
                <label for="login">Login</label>
                <input type="text" name="login" value="${login}" />

                <label for="senha">Senha</label>
                <input type="password" name="senha" />

                <input type="submit" name="submit" value="Entrar"/>
                <a href="cadastrar">Cadastrar-se</a>
            </form>
        </div>
    </body>
</html>