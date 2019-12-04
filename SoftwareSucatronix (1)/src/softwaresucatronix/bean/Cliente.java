package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfaces.InterfaceCliente;

public class Cliente implements InterfaceCliente {

    private int idCliente;

    private String nomeCliente;

    private String emailCliente;

    private String telefoneCliente;

    private String cepCliente;

    private String ruaCliente;

    private String numeroCliente;

    private String complementoCliente;

    private String bairroCliente;

    /**
     *
     * @param nome
     */
    @Override
    public void setNomeCliente(String nome) {
        if (nome.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.nomeCliente = nome;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmailCliente(String email) {
        if (email.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.emailCliente = email;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getEmailCliente() {
        return this.emailCliente;
    }

    /**
     *
     * @param telefone
     */
    @Override
    public void setTelefoneCliente(String telefone) {
        if (telefone.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.telefoneCliente = telefone;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getTelefoneCliente() {
        return this.telefoneCliente;
    }

    /**
     *
     * @param cep
     */
    @Override
    public void setCepCliente(String cep) {
        if (cep.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.cepCliente = cep;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getCepCliente() {
        return this.cepCliente;
    }

    /**
     *
     * @param rua
     */
    @Override
    public void setRuaCliente(String rua) {
        if (rua.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.ruaCliente = rua;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getRuaCliente() {
        return this.ruaCliente;
    }

    /**
     *
     * @param numero
     */
    @Override
    public void setNumeroCliente(String numero) {
        if (numero.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.numeroCliente = numero;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getNumeroCliente() {
        return this.numeroCliente;
    }

    /**
     *
     * @param complemento
     */
    @Override
    public void setComplementoCliente(String complemento) {
        this.complementoCliente = complemento;
    }

    /**
     *
     * @return
     */
    @Override
    public String getComplementoCliente() {
        return this.complementoCliente;
    }

    /**
     *
     * @param bairro
     */
    @Override
    public void setBairroCliente(String bairro) {
        if (bairro.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.bairroCliente = bairro;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getBairroCliente() {
        return this.bairroCliente;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setIdCliente(int id) {
        if (id >= 0) {
            this.idCliente = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maio ou igual a 0 válido");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getIdCliente() {
        return this.idCliente;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Cliente(id, nome, email, telefone, cep, rua, numero, complemento, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, this.idCliente);
            statement.setString(2, this.nomeCliente);
            statement.setString(3, this.emailCliente);
            statement.setString(4, this.telefoneCliente);
            statement.setString(5, this.cepCliente);
            statement.setString(6, this.ruaCliente);
            statement.setString(7, this.numeroCliente);
            statement.setString(8, this.complementoCliente);
            statement.setString(9, this.bairroCliente);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, email, telefone, cep, rua, numero, complemento, bairro FROM Cliente WHERE id = ?");
            statement.setInt(1, this.idCliente);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    this.idCliente = rs.getInt("id");
                    this.nomeCliente = rs.getString("nome");
                    this.emailCliente = rs.getString("email");
                    this.telefoneCliente = rs.getString("telefone");
                    this.cepCliente = rs.getString("cep");
                    this.ruaCliente = rs.getString("rua");
                    this.numeroCliente = rs.getString("numero");
                    this.complementoCliente = rs.getString("complemento");
                    this.bairroCliente = rs.getString("bairro");
                } else if (currentRow < 1) {
                    // Poucas linhas retornadas
                } else if (currentRow > 1) {
                    // Muitas linhas retornadas
                }
            }
        } catch (SQLException x) {

        }
    }

    @Override
    public List<Cliente> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, email, telefone, cep, rua, numero, complemento, bairro FROM Cliente");
            ResultSet rs = statement.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.idCliente = rs.getInt("id");
                cliente.nomeCliente = rs.getString("nome");
                cliente.emailCliente = rs.getString("email");
                cliente.telefoneCliente = rs.getString("telefone");
                cliente.cepCliente = rs.getString("cep");
                cliente.ruaCliente = rs.getString("rua");
                cliente.numeroCliente = rs.getString("numero");
                cliente.complementoCliente = rs.getString("complemento");
                cliente.bairroCliente = rs.getString("bairro");
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Fornecedor SET nome = ?, email = ?, telefone = ?, cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ? WHERE id = ?");
            statement.setInt(9, this.idCliente);
            statement.setString(1, this.nomeCliente);
            statement.setString(2, this.emailCliente);
            statement.setString(3, this.telefoneCliente);
            statement.setString(4, this.cepCliente);
            statement.setString(5, this.ruaCliente);
            statement.setString(6, this.numeroCliente);
            statement.setString(7, this.complementoCliente);
            statement.setString(8, this.bairroCliente);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            statement.setInt(1, this.idCliente);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idCliente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(this.idCliente) + " - " + this.nomeCliente;
    }
    
    
    
}
