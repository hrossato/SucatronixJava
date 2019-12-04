/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.List;
import softwaresucatronix.bean.Cliente;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceCliente {

    public void setNomeCliente(String nome);

    public String getNomeCliente();

    public void setEmailCliente(String email);

    public String getEmailCliente();

    public void setTelefoneCliente(String telefone);

    public String getTelefoneCliente();

    public void setCepCliente(String cep);

    public String getCepCliente();

    public void setRuaCliente(String rua);

    public String getRuaCliente();

    public void setNumeroCliente(String numero);

    public String getNumeroCliente();

    public void setComplementoCliente(String complemento);

    public String getComplementoCliente();

    public void setBairroCliente(String bairro);

    public String getBairroCliente();

    public void setIdCliente(int id);

    public int getIdCliente();

    public void create();

    public void read();

    public List<Cliente> readAll();

    public void update();

    public void delete();

}
