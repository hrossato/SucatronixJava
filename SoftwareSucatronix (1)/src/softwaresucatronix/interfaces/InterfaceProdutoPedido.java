/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.List;
import softwaresucatronix.bean.Produto;
import softwaresucatronix.bean.ProdutoPedido;
import softwaresucatronix.bean.Pedido;

/**
 *
 * @author guilhermeoc97
 */
public interface InterfaceProdutoPedido {
    
    public void setPedido(Pedido pedido);
    
    public Pedido getPedido();
    
    public void setProduto(Produto produto);
    
    public Produto getProduto();
    
    public void setQuantidade(float quantidade);
    
    public float getQuantidade();
    
    public void create();
    
    public List<ProdutoPedido> readAllByPedido();
    
    public void update();
    
    public void delete();
    
}
