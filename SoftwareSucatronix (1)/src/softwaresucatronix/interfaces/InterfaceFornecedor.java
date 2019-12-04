/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.List;
import softwaresucatronix.bean.Fornecedor;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceFornecedor {

    public void setIdFornecedor(int id);

    public int getIdFornecedor();

    public void setNomeFornecedor(String nome);

    public String getNomeFornecedor();

    public void setEmailFornecedor(String email);

    public String getEmailFornecedor();

    public void setTelefoneFornecedor(String telefone);

    public String getTelefoneFornecedor();

    public void setCepFornecedor(String cep);

    public String getCepFornecedor();

    public void setRuaFornecedor(String rua);

    public String getRuaFornecedor();

    public void setNumeroFornecedor(String numero);

    public String getNumeroFornecedor();
    
    public void setComplementoFornecedor(String complemento);

    public String getComplementoFornecedor();

    public void setBairroFornecedor(String bairro);

    public String getBairroFornecedor();

    public void create();

    public void read();

    public List<Fornecedor> readAll();

    public void update();

    public void delete();

}
