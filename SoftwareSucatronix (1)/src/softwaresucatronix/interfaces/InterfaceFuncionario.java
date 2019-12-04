/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfaces;

import java.util.Date;
import java.util.List;
import softwaresucatronix.bean.Funcionario;

/**
 *
 * @author T-Gamer
 */
public interface InterfaceFuncionario {

    public void setNomeFuncionario(String nome);

    public String getNomeFuncionario();

    public void setCpfFuncionario(String cpf);

    public String getCpfFuncionario();

    public void setRgFuncionario(String rg);

    public String getRgFuncionario();

    public void setEmailFuncionario(String email);

    public String getEmailFuncionario();

    public void setTelefoneFuncionario(String telefone);

    public String getTelefoneFuncionario();

    public void setDataNascimentoFuncionario(Date dataNascimento);

    public Date getDataNascimentoFuncionario();

    public void setCepFuncionario(String cep);

    public String getCepFuncionario();

    public void setSenhaFuncionario(String senha);

    public void setTipoFuncionario(String tipo);

    public String getTipo();

    public void setIdFuncionario(int id);

    public int getId();

    public void create();

    public void read();

    public List<Funcionario> readAll();

    public void update();

    public void delete();

}
