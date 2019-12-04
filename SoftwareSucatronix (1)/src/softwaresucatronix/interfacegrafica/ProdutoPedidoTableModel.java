/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.ProdutoPedido;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoPedidoTableModel extends AbstractTableModel {

    private final List<ProdutoPedido> produtos;
    
    public ProdutoPedidoTableModel() {
        this.produtos = new ArrayList<>();
    }
    
    public ProdutoPedidoTableModel(List<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }
    
    public void addProdutoPedido(ProdutoPedido produto) {
        if (this.produtos.contains(produto)) {
            produto.setQuantidade(produto.getQuantidade() + this.produtos.get(this.produtos.indexOf(produto)).getQuantidade());
            if (produto.getQuantidade() <= produto.getProduto().getEstoqueAtualProduto()) {
                produtos.set(this.produtos.indexOf(produto), produto);
                this.fireTableCellUpdated(this.produtos.indexOf(produto), 1);
            }
        } else {
            this.produtos.add(produto);
            this.fireTableRowsInserted(0, this.produtos.size() + 1);
        }
    }
    
    public void removeProdutoPedido(ProdutoPedido produto) {
        if (produto.getPedido()!= null) {
            produto.delete();
        }
        this.produtos.remove(produto);
        this.fireTableRowsDeleted(0, this.produtos.size() + 1);
    }
    
    public void clearModel() {
        this.produtos.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<ProdutoPedido> getModel() {
        return this.produtos;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Produto";
            case 1:
                return "Quantidade";
            default:
                return "";
        }
    }
    
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoPedido produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getProduto().getNomeProduto();
            case 1:
                return produto.getQuantidade();
            default:
                return null;
        }
    }
    
}
