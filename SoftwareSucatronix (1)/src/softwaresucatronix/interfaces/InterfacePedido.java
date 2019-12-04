/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.Date;
import java.util.List;
import softwaresucatronix.bean.Fornecedor;
import softwaresucatronix.bean.Funcionario;
import softwaresucatronix.bean.Produto;
import softwaresucatronix.bean.ProdutoPedido;
import softwaresucatronix.bean.Pedido;

/**
 *
 * @author T-Gamer
 */
public interface InterfacePedido {

    public void setIdPedido(int id);

    public int getIdPedido();

    public void setFornecedorPedido(Fornecedor fornecedor);

    public Fornecedor getFornecedorPedido();

    public void setDataPedido(Date data);

    public Date getDataPedido();

    public void setFuncionarioPedido(Funcionario funcionario);

    public Funcionario getFuncionarioPedido();

    public void setSituacaoPedido(String situacao);

    public String getSituacaoPedido();

    public void setProdutosPedido(List<ProdutoPedido> produto);

    public List<ProdutoPedido> getProdutosPedido();

    public void create();

    public void read();

    public List<Pedido> readAll();

    public void update();

    public void delete();

}
