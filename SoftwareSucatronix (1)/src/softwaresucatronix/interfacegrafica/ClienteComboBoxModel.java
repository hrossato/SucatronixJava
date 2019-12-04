/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import softwaresucatronix.bean.Cliente;

/**
 *
 * @author guilhermeoc97
 */
public class ClienteComboBoxModel extends AbstractListModel<Cliente> implements ComboBoxModel<Cliente> {

    private List<Cliente> cliente;
    private Cliente clienteSelecionado;

    public ClienteComboBoxModel() {
        this.clienteSelecionado = null;
        this.cliente = new Cliente().readAll();
    }
    
    @Override
    public int getSize() {
        return this.cliente.size();
    }

    @Override
    public Cliente getElementAt(int index) {
        return this.cliente.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Cliente && this.cliente.contains((Cliente) anItem)) {
            this.clienteSelecionado = (Cliente) anItem;
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.clienteSelecionado;
    }
    
}
