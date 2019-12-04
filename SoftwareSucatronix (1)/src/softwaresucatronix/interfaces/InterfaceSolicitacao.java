/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.Date;
import java.util.List;
import softwaresucatronix.bean.Funcionario;
import softwaresucatronix.bean.Produto;
import softwaresucatronix.bean.ProdutoSolicitacao;
import softwaresucatronix.bean.Solicitacao;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceSolicitacao {

    public void setIdSolicitacao(int id);

    public int getIdSolicitacao();

    public void setFuncionarioSolicitacao(Funcionario funcionario);

    public Funcionario getFuncionarioSolicitacao();

    public void setDataSolicitacao(Date data);

    public Date getDataSolicitacao();

    public void setAprovada(boolean aprovada);

    public boolean getAprovada();

    public void setProdutosSolicitacao(List<ProdutoSolicitacao> produtos);

    public List<ProdutoSolicitacao> getProdutosSolicitacao();

    public void create();

    public void read();

    public List<Solicitacao> readAll();

    public void update();

    public void delete();

}
