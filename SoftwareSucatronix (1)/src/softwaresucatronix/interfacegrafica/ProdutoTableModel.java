/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.Produto;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    
    public ProdutoTableModel() {
        this.produtos = new ArrayList<>();
    }
    
    public ProdutoTableModel(boolean fetch) {
        if (fetch) {
            this.produtos = new Produto().readAll();
        } else {
            this.produtos = new ArrayList<>();
        }
    }
    
    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProduto(Produto produto, boolean immediately) {
        if (immediately) {
            produto.create();
            this.produtos = new Produto().readAll();
        } else {
            this.produtos.add(produto);
        }
        this.fireTableRowsInserted(0, this.produtos.size() + 1);
    }
    
    public void addProduto(Produto produto) {
        this.addProduto(produto, false);
    }
    
    public void removeProduto(Produto produto) {
        produto.delete();
        this.produtos.remove(produto);
        this.fireTableRowsDeleted(0, this.produtos.size() + 1);
    }
    
    public void aumentaProduto(Produto produto) {
        produto.setEstoqueAtualProduto(produto.getEstoqueAtualProduto() + 1);
        produto.update();
        if (this.produtos.indexOf(produto) > -1) {
            this.produtos.set(this.produtos.indexOf(produto), produto);
            this.fireTableCellUpdated(this.produtos.indexOf(produto), 1);
        }
    }
    
    public void reduzProduto(Produto produto) {
        produto.setEstoqueAtualProduto(produto.getEstoqueAtualProduto() - 1);
        produto.update();
        if (this.produtos.indexOf(produto) > -1) {
            this.produtos.set(this.produtos.indexOf(produto), produto);
            this.fireTableCellUpdated(this.produtos.indexOf(produto), 1);
        }
    }
    
    public void clearModel() {
        this.produtos.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Produto> getModel() {
        return this.produtos;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Estoque inicial";
            case 2:
                return "Preço";
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getNomeProduto();
            case 1:
                return produto.getEstoqueAtualProduto();
            case 2:
                return produto.getPrecoProduto();
            default:
                return null;
        }
    }
    
}
