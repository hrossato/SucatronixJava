/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.List;
import softwaresucatronix.bean.Produto;
import softwaresucatronix.bean.ProdutoSolicitacao;
import softwaresucatronix.bean.ProdutoVenda;
import softwaresucatronix.bean.Solicitacao;
import softwaresucatronix.bean.Venda;

/**
 *
 * @author guilhermeoc97
 */
public interface InterfaceProdutoSolicitacao {
    
    public void setSolicitacao(Solicitacao solicitacao);
    
    public Solicitacao getSolicitacao();
    
    public void setProduto(Produto produto);
    
    public Produto getProduto();
    
    public void setQuantidade(float quantidade);
    
    public float getQuantidade();
    
    public void create();
    
    public List<ProdutoSolicitacao> readAllBySolicitacao();
    
    public void update();
    
    public void delete();
    
}
