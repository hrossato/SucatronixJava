/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.Fornecedor;

/**
 *
 * @author guilhermeoc97
 */
public class FornecedorTableModel extends AbstractTableModel {

    private List<Fornecedor> fornecedores;
    
    public FornecedorTableModel() {
        this.fornecedores = new ArrayList<>();
    }
    
    public FornecedorTableModel(boolean fetch) {
        if (fetch) {
            this.fornecedores = new Fornecedor().readAll();
        } else {
            this.fornecedores = new ArrayList<>();
        }
    }
    
    public FornecedorTableModel(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    public void addFornecedor(Fornecedor fornecedor, boolean immediately) {
        if (immediately) {
            fornecedor.create();
            this.fornecedores = new Fornecedor().readAll();
        } else {
            this.fornecedores.add(fornecedor);
        }
        this.fireTableRowsInserted(0, this.fornecedores.size() + 1);
    }
    
    public void addFornecedor(Fornecedor fornecedor) {
        this.addFornecedor(fornecedor, false);
    }
    
    public void removeFornecedor(Fornecedor fornecedor) {
        fornecedor.delete();
        this.fornecedores.remove(fornecedor);
        this.fireTableRowsDeleted(0, this.fornecedores.size() + 1);
    }
    
    public void clearModel() {
        this.fornecedores.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Fornecedor> getModel() {
        return this.fornecedores;
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
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor fornecedor = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fornecedor.getNomeFornecedor();
            case 1:
                return fornecedor.getEmailFornecedor();
            case 2:
                return fornecedor.getTelefoneFornecedor();
            default:
                return null;
        }
    }
    
}
