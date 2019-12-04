/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.Date;
import java.util.List;
import softwaresucatronix.bean.Cliente;
import softwaresucatronix.bean.Funcionario;
import softwaresucatronix.bean.Produto;
import softwaresucatronix.bean.ProdutoVenda;
import softwaresucatronix.bean.Venda;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceVenda {

    public void setIdVenda(int id);

    public int getIdVenda();

    public void setClienteVenda(Cliente cliente);

    public Cliente getClienteVenda();

    public void setDataVenda(Date data);

    public Date getDataVenda();

    public void setFuncionarioVenda(Funcionario funcionario);

    public Funcionario getFuncionarioVenda();

    public void setSituacaoVenda(String situacao);

    public String getSituacaoVenda();

    public void setProdutosVenda(List<ProdutoVenda> produto);

    public List<ProdutoVenda> getProdutosVenda();

    public void create();

    public void read();

    public List<Venda> readAll();
    
    public List<Venda> readAllOrcamento();

    public void update();

    public void delete();

}
