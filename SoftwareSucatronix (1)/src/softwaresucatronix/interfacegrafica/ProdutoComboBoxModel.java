/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import softwaresucatronix.bean.Produto;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoComboBoxModel extends AbstractListModel<Produto> implements ComboBoxModel<Produto> {

    private List<Produto> produtos;
    private Produto produtoSelecionado;

    public ProdutoComboBoxModel(boolean stocked) {
        this.produtoSelecionado = null;
        if (stocked) {
            this.produtos = new Produto().readAllEmEstoque();
        } else {
            this.produtos = new Produto().readAll();
        }
    }
    
    @Override
    public int getSize() {
        return this.produtos.size();
    }

    @Override
    public Produto getElementAt(int index) {
        return this.produtos.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Produto && this.produtos.contains((Produto) anItem)) {
            this.produtoSelecionado = (Produto) anItem;
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.produtoSelecionado;
    }
    
}
