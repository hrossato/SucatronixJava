/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.ProdutoVenda;
import softwaresucatronix.bean.Venda;

/**
 *
 * @author guilhermeoc97
 */
public class VendaTableModel extends AbstractTableModel {

    private List<Venda> vendas;
    
    public VendaTableModel() {
        this.vendas = new ArrayList<>();
    }
    
    public VendaTableModel(boolean fetch) {
        if (fetch) {
            this.vendas = new Venda().readAll();
        } else {
            this.vendas = new ArrayList<>();
        }
    }
    
    public VendaTableModel(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    public void addVenda(Venda venda, boolean immediately) {
        if (immediately) {
            venda.create();
            this.vendas = new Venda().readAll();
        } else {
            this.vendas.add(venda);
        }
        this.fireTableRowsInserted(0, this.vendas.size() + 1);
    }
    
    public void addVenda(Venda venda) {
        this.addVenda(venda, false);
    }
    
    public void removeVenda(Venda venda) {
        venda.read();
        venda.getProdutosVenda().forEach((ProdutoVenda item) -> item.delete());
        venda.delete();
        this.vendas.remove(venda);
        this.fireTableRowsDeleted(0, this.vendas.size() + 1);
    }
    
    public void clearModel() {
        this.vendas.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Venda> getModel() {
        return this.vendas;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cliente";
            case 1:
                return "Funcionário";
            case 2:
                return "Data";
            case 3:
                return "Valor total";
            default:
                return "";
        }
    }
    
    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda venda = vendas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return venda.getClienteVenda().getNomeCliente();
            case 1:
                return venda.getFuncionarioVenda().getNomeFuncionario();
            case 2:
                return new SimpleDateFormat("dd/MM/yyyy").format(venda.getDataVenda());
            case 3:
                return new DecimalFormat("R$ #,##0.00").format(venda.getProdutosVenda()
                        .stream().mapToDouble((item) -> 
                            item.getQuantidade() * item.getProduto().getPrecoProduto()
                        ).reduce(0f, (acc, item) -> {
                            acc += item;
                            return acc;
                        }));
            default:
                return null;
        }
    }
    
}
