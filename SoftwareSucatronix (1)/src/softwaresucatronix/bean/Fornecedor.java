package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfaces.InterfaceFornecedor;

/**
 *
 * @author T-Gamer
 */
public class Fornecedor implements InterfaceFornecedor {

    private int idFornecedor;

    private String nomeFornecedor;

    private String emailFornecedor;

    private String telefoneFornecedor;

    private String cepFornecedor;

    private String ruaFornecedor;

    private String numeroFornecedor;

    private String complementoFornecedor;

    private String bairroFornecedor;

    /**
     *
     * @param id
     */
    @Override
    public void setIdFornecedor(int id) {
        this.idFornecedor = id;

    }

    /**
     *
     * @return
     */
    @Override
    public int getIdFornecedor() {
        return this.idFornecedor;
    }

    /**
     *
     * @param nome
     */
    @Override
    public void setNomeFornecedor(String nome) {
        if (nome.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.nomeFornecedor = nome;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmailFornecedor(String email) {
        if (email.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.emailFornecedor = email;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getEmailFornecedor() {
        return this.emailFornecedor;
    }

    /**
     *
     * @param telefone
     */
    @Override
    public void setTelefoneFornecedor(String telefone) {
        if (telefone.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.telefoneFornecedor = telefone;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getTelefoneFornecedor() {
        return this.telefoneFornecedor;
    }

    /**
     *
     * @param cep
     */
    @Override
    public void setCepFornecedor(String cep) {
        if (cep.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.cepFornecedor = cep;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getCepFornecedor() {
        return this.cepFornecedor;
    }

    /**
     *
     * @param rua
     */
    @Override
    public void setRuaFornecedor(String rua) {
        if (rua.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.ruaFornecedor = rua;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getRuaFornecedor() {
        return this.ruaFornecedor;
    }

    /**
     *
     * @param numero
     */
    @Override
    public void setNumeroFornecedor(String numero) {
        if (numero.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.numeroFornecedor = numero;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getNumeroFornecedor() {
        return this.numeroFornecedor;
    }

    /**
     *
     * @param bairro
     */
    @Override
    public void setBairroFornecedor(String bairro) {
        if (bairro.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.bairroFornecedor = bairro;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getBairroFornecedor() {
        return this.bairroFornecedor;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Fornecedor(id, nome, email, telefone, cep, rua, numero, complemento, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, this.idFornecedor);
            statement.setString(2, this.nomeFornecedor);
            statement.setString(3, this.emailFornecedor);
            statement.setString(4, this.telefoneFornecedor);
            statement.setString(5, this.cepFornecedor);
            statement.setString(6, this.ruaFornecedor);
            statement.setString(7, this.numeroFornecedor);
            statement.setString(8, this.complementoFornecedor);
            statement.setString(9, this.bairroFornecedor);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, email, telefone, cep, rua, numero, complemento, bairro FROM Fornecedor WHERE id = ?");
            statement.setInt(1, this.idFornecedor);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    this.idFornecedor = rs.getInt("id");
                    this.nomeFornecedor = rs.getString("nome");
                    this.emailFornecedor = rs.getString("email");
                    this.telefoneFornecedor = rs.getString("telefone");
                    this.cepFornecedor = rs.getString("cep");
                    this.ruaFornecedor = rs.getString("rua");
                    this.numeroFornecedor = rs.getString("numero");
                    this.complementoFornecedor = rs.getString("complemento");
                    this.bairroFornecedor = rs.getString("bairro");
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
    public List<Fornecedor> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, email, telefone, cep, rua, numero, complemento, bairro FROM Fornecedor WHERE id = ?");
            statement.setInt(1, this.idFornecedor);
            ResultSet rs = statement.executeQuery();
            List<Fornecedor> fornecedores = new ArrayList<>();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.idFornecedor = rs.getInt("id");
                fornecedor.nomeFornecedor = rs.getString("nome");
                fornecedor.emailFornecedor = rs.getString("email");
                fornecedor.telefoneFornecedor = rs.getString("telefone");
                fornecedor.cepFornecedor = rs.getString("cep");
                fornecedor.ruaFornecedor = rs.getString("rua");
                fornecedor.numeroFornecedor = rs.getString("numero");
                fornecedor.complementoFornecedor = rs.getString("complemento");
                fornecedor.bairroFornecedor = rs.getString("bairro");
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Fornecedor SET nome = ?, email = ?, telefone = ?, cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ? WHERE id = ?");
            statement.setInt(9, this.idFornecedor);
            statement.setString(1, this.nomeFornecedor);
            statement.setString(2, this.emailFornecedor);
            statement.setString(3, this.telefoneFornecedor);
            statement.setString(4, this.cepFornecedor);
            statement.setString(5, this.ruaFornecedor);
            statement.setString(6, this.numeroFornecedor);
            statement.setString(7, this.complementoFornecedor);
            statement.setString(8, this.bairroFornecedor);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Fornecedor WHERE id = ?");
            statement.setInt(1, this.idFornecedor);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void setComplementoFornecedor(String complemento) {
        this.complementoFornecedor = complemento;
    }

    @Override
    public String getComplementoFornecedor() {
        return this.complementoFornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idFornecedor;
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.idFornecedor != other.idFornecedor) {
            return false;
        }
        return true;
    }
    
    
}
