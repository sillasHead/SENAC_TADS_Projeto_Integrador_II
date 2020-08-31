/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import utils.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Computador;

/**
 *
 * @author sillas.clpinto
 */
public class ComputadorDAO {

    static Connection conexao = null;
    static PreparedStatement sql = null;

    public static boolean gravarComputador(Computador computador) {
        boolean retorno;

        try {
            conexao = ConexaoBD.abrirConexao(); //abrindo e criando um objeto de conexao

            sql = conexao.prepareStatement("insert into computador (marca, hd, processador) " //instrucao sql
                    + "values (?, ?, ?)"/*, Statement.RETURN_GENERATED_KEYS*/);
            //retorna o id
            sql.setString(1, computador.getMarca()); //adicionando os parametros na instrucao sql
            sql.setString(2, computador.getHd());
            sql.setString(3, computador.getProcessador());

            int linhasAfetadas = sql.executeUpdate(); //executando a instrucao sql

            if (linhasAfetadas > 0) {
                retorno = true;
                /*
                ResultSet key = sql.getGeneratedKeys(); //recuperando id
                if (key.next()) {
                    computador.setId(key.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter id");
                }
                 */
            } else {
                retorno = false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            try {
                if (sql != null) { //liberando memoria
                    sql.close();
                }

                if (conexao != null) { //fechando conexao
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static boolean alterarComputador(Computador computador) {
        boolean retorno;

        try {
            conexao = ConexaoBD.abrirConexao();

            sql = conexao.prepareStatement("update computador set marca = ?, hd = ?, processador = ? where id = ?");
            sql.setString(1, computador.getMarca());
            sql.setString(2, computador.getHd());
            sql.setString(3, computador.getProcessador());
            sql.setInt(4, computador.getId());

            int linhasAfetadas = sql.executeUpdate();

            retorno = linhasAfetadas > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }

                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }
    
    public static boolean removerComputador(int id) {
        boolean retorno;

        try {
            conexao = ConexaoBD.abrirConexao();

            sql = conexao.prepareStatement("delete from computador where id = ?");
            sql.setInt(1, id);

            int linhasAfetadas = sql.executeUpdate();

            retorno = linhasAfetadas > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }

                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    public static ArrayList<Computador> consultarComputador(String filtro) {
        ArrayList<Computador> computadores = new ArrayList<>();
        ResultSet rs = null;

        try {
            conexao = ConexaoBD.abrirConexao();

            sql = conexao.prepareStatement("select * from computador where marca like concat('%', ?, '%')");
            sql.setString(1, filtro);

            rs = sql.executeQuery(); //criando objeto de consulta

            while (rs.next()) { //enquanto houver mais uma linha
                Computador computador = new Computador();
                computador.setId(rs.getInt("id"));
                computador.setMarca(rs.getString("marca"));
                computador.setHd(rs.getString("hd"));
                computador.setProcessador(rs.getString("processador"));

                computadores.add(computador);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            computadores = null;
        } finally {
            try {
                if (sql != null) {
                    sql.close();
                }
                
                if (rs != null) {
                    rs.close();
                }

                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
            }
        }

        return computadores;
    }
}
