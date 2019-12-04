/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.List;
import softwaresucatronix.bean.Produto;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceProduto {

    public void setNomeProduto(String nome);

    public String getNomeProduto();

    public void setEstoqueAtualProduto(float estoqueQtd);

    public float getEstoqueAtualProduto();

    public void setPrecoProduto(float valor);

    public float getPrecoProduto();

    public void setIdProduto(int id);

    public int getIdProduto();

    public void create();

    public void read();

    public List<Produto> readAll();
    
    public List<Produto> readAllEmEstoque();

    public void update();

    public void delete();

}
