/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.Cliente;

/**
 *
 * @author guilhermeoc97
 */
public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> clientes;
    
    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }
    
    public ClienteTableModel(boolean fetch) {
        if (fetch) {
            this.clientes = new Cliente().readAll();
        } else {
            this.clientes = new ArrayList<>();
        }
    }
    
    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void addCliente(Cliente cliente, boolean immediately) {
        if (immediately) {
            cliente.create();
            this.clientes = new Cliente().readAll();
        } else {
            this.clientes.add(cliente);
        }
        this.fireTableRowsInserted(0, this.clientes.size() + 1);
    }
    
    public void addCliente(Cliente cliente) {
        this.addCliente(cliente, false);
    }
    
    public void removeCliente(Cliente cliente) {
        cliente.delete();
        this.clientes.remove(cliente);
        this.fireTableRowsDeleted(0, this.clientes.size() + 1);
    }
    
    public void clearModel() {
        this.clientes.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Cliente> getModel() {
        return this.clientes;
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
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getNomeCliente();
            case 1:
                return cliente.getEmailCliente();
            case 2:
                return cliente.getTelefoneCliente();
            default:
                return null;
        }
    }
    
}
