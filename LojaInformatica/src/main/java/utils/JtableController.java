/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.ComputadorController;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Computador;

/**
 *
 * @author sillas.clpinto
 */
public class JtableController {
    public static Computador getLinhaSelecionada(int indice, JTable table) { //pegar dados da linha selecionada na tabela
        Computador computador = new Computador();
        computador.setId((int)table.getValueAt(indice, 0));
        computador.setMarca((String)table.getValueAt(indice, 1));
        computador.setHd((String)table.getValueAt(indice, 2));
        computador.setProcessador((String)table.getValueAt(indice, 3));
        
        return computador;
    }
    
    public static void carregarTabela(JTable table, String filtro) { //carregar tabela com os dados do bd
        ArrayList<Computador> computadores = ComputadorController.consultarComputador(filtro);
        
        DefaultTableModel modelo = (DefaultTableModel)table.getModel();
        modelo.setRowCount(0); //limpando os dados da tabela
        
        computadores.forEach((Computador computador) -> { //preenchendo tabela
            modelo.addRow(new Object[] {
                computador.getId(),
                computador.getMarca(),
                computador.getHd(),
                computador.getProcessador()
            });
        });
    }
    
    public static void formatarTabela(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel)table.getModel();
        
        
    }
}
