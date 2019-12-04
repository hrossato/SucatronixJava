/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import softwaresucatronix.bean.Fornecedor;

/**
 *
 * @author guilhermeoc97
 */
public class FornecedorComboBoxModel extends AbstractListModel<Fornecedor> implements ComboBoxModel<Fornecedor> {

    private List<Fornecedor> fornecedor;
    private Fornecedor fornecedorSelecionado;

    public FornecedorComboBoxModel() {
        this.fornecedorSelecionado = null;
        this.fornecedor = new Fornecedor().readAll();
    }
    
    @Override
    public int getSize() {
        return this.fornecedor.size();
    }

    @Override
    public Fornecedor getElementAt(int index) {
        return this.fornecedor.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Fornecedor && this.fornecedor.contains((Fornecedor) anItem)) {
            this.fornecedorSelecionado = (Fornecedor) anItem;
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.fornecedorSelecionado;
    }
    
}
