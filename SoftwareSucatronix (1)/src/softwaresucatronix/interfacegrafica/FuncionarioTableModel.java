/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.Funcionario;

/**
 *
 * @author guilhermeoc97
 */
public class FuncionarioTableModel extends AbstractTableModel {

    private List<Funcionario> funcionarios;
    
    public FuncionarioTableModel() {
        this.funcionarios = new ArrayList<>();
    }
    
    public FuncionarioTableModel(boolean fetch) {
        if (fetch) {
            this.funcionarios = new Funcionario().readAll();
        } else {
            this.funcionarios = new ArrayList<>();
        }
    }
    
    public FuncionarioTableModel(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public void addFuncionario(Funcionario funcionario, boolean immediately) {
        if (immediately) {
            funcionario.create();
            this.funcionarios = new Funcionario().readAll();
        } else {
            this.funcionarios.add(funcionario);
        }
        this.fireTableRowsInserted(0, this.funcionarios.size() + 1);
    }
    
    public void addFuncionario(Funcionario funcionario) {
        this.addFuncionario(funcionario, false);
    }
    
    public void removeFuncionario(Funcionario funcionario) {
        funcionario.delete();
        this.funcionarios.remove(funcionario);
        this.fireTableRowsDeleted(0, this.funcionarios.size() + 1);
    }
    
    public void clearModel() {
        this.funcionarios.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Funcionario> getModel() {
        return this.funcionarios;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "E-Mail";
            case 2:
                return "Telefone";
            default:
                return "";
        }
    }
    
    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = funcionarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return funcionario.getNomeFuncionario();
            case 1:
                return funcionario.getEmailFuncionario();
            case 2:
                return funcionario.getTelefoneFuncionario();
            default:
                return null;
        }
    }
    
}
