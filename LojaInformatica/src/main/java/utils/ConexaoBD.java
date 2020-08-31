/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sillas.clpinto
 */
public class ConexaoBD {

    public static String status = "Nao conectado"; //mensagem referente status de conexao com bd
    public static String driver = "com.mysql.cj.jdbc.Driver"; //connector/J

    public static String server = "localhost"; //servidor local
    public static String porta = ":3306/"; //porta de acesso do banco de dados
    public static String database = "lojainformatica"; //nome do banco de dados

    public static String login = "root"; //usuario de acesso ao bd
    public static String senha = ""; //senha de acesso ao bd

    public static Connection conexao;

    public ConexaoBD() {
    }

    public static Connection abrirConexao() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://" + server + porta + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

        if (conexao == null) {
            try {
                Class.forName(driver); //carregando classe responsavel pelo driver
                conexao = DriverManager.getConnection(url, login, senha); //objeto de conexao

                if (conexao != null) {
                    status = "conexao realizada com sucesso";
                } else {
                    status = "erro ao realizar conexao";
                }
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");
            } catch (SQLException e) {
                throw new SQLException("Erro ao estabelecer a conexao (Ex: login ou senha errados).");
            }
        } else {
            try {
                if (conexao.isClosed()) { //se a conexao estiver fechada, abrir
                    conexao = DriverManager.getConnection(url, login, senha);
                }
            } catch (SQLException e) {
                throw new SQLException("Falha ao fechar a conexao");
            }
        }

        return conexao;
    }

    public static String getStatusConexao() {
        return status;
    }
    
    public static boolean fecharConexao() throws SQLException {
        boolean retorno;
        
        try {
            if (conexao != null) {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            }
            
            status = "Nao conectado";
            retorno = true;
        } catch (SQLException e) {
            retorno = false;
        }
        
        return retorno;
    }
}
