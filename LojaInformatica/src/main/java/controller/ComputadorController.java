/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.JtableController;
import database.ComputadorDAO;
import java.util.ArrayList;
import javax.swing.JTable;
import model.Computador;

/**
 *
 * @author sillas.clpinto
 */
public class ComputadorController {

    public static boolean gravarComputador(String marca, String hd, String processador) {
        Computador computador = new Computador();
        computador.setMarca(marca);
        computador.setHd(hd);
        computador.setProcessador(processador);

        return ComputadorDAO.gravarComputador(computador);
    }
    
    public static boolean alterarComputador(String marca, String hd, String processador, int id) {
        Computador computador = new Computador();
        computador.setMarca(marca);
        computador.setHd(hd);
        computador.setProcessador(processador);
        computador.setId(id);

        return ComputadorDAO.alterarComputador(computador);
    }
    
    public static boolean alterarComputador(int indice, JTable tbl) { //alterando pela propria tabela
        return ComputadorDAO.alterarComputador(JtableController.getLinhaSelecionada(indice, tbl));
    }
    
    public static boolean removerComputador(int id) {
        return ComputadorDAO.removerComputador(id);
    }
    
    public static ArrayList<Computador> consultarComputador(String filtro) {
        return ComputadorDAO.consultarComputador(filtro);
    }
}
