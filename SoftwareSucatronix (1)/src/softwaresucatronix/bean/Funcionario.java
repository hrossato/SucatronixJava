package softwaresucatronix.bean;

import com.kosprov.jargon2.api.Jargon2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfacegrafica.TelaPrincipalVendedor;
import softwaresucatronix.interfaces.InterfaceFuncionario;

public class Funcionario implements InterfaceFuncionario {

    private int idFuncionario;

    private String nomeFuncionario;

    private String cpfFuncionario;

    private String rgFuncionario;

    private String emailFuncionario;

    private String telefoneFuncionario;

    private Date dataNascimentoFuncionario;

    private String cepFuncionario;

    private String ruaFuncionario;

    private String numeroFuncionario;

    private String complementoFuncionario;

    private String bairroFuncionario;

    private String senhaFuncionario;

    private String tipoFuncionario;

    private boolean statusFuncionario;

    /**
     *
     * @param nome
     */
    @Override
    public void setNomeFuncionario(String nome) {
        if (nome.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.nomeFuncionario = nome;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeFuncionario() {
        return this.nomeFuncionario;
    }

    /**
     *
     * @param cpf
     */
    @Override
    public void setCpfFuncionario(String cpf) {

        this.cpfFuncionario = cpf;

    }

    /**
     *
     * @return
     */
    @Override
    public String getCpfFuncionario() {
        return this.cpfFuncionario;
    }

    /**
     *
     * @param rg
     */
    @Override
    public void setRgFuncionario(String rg) {
        this.rgFuncionario = rg;

    }

    /**
     *
     * @return
     */
    @Override
    public String getRgFuncionario() {
        return this.rgFuncionario;
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmailFuncionario(String email) {
        this.emailFuncionario = email;

    }

    /**
     *
     * @return
     */
    @Override
    public String getEmailFuncionario() {
        return this.emailFuncionario;
    }

    /**
     *
     * @param telefone
     */
    @Override
    public void setTelefoneFuncionario(String telefone) {
        if (telefone.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.telefoneFuncionario = telefone;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getTelefoneFuncionario() {
        return this.telefoneFuncionario;
    }

    /**
     *
     * @param dataNascimento
     */
    @Override
    public void setDataNascimentoFuncionario(Date dataNascimento) {
        this.dataNascimentoFuncionario = dataNascimento;

    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataNascimentoFuncionario() {
        return this.dataNascimentoFuncionario;
    }

    /**
     *
     * @param cep
     */
    @Override
    public void setCepFuncionario(String cep) {

        if (cep.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.cepFuncionario = cep;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getCepFuncionario() {
        return this.cepFuncionario;
    }

    /**
     *
     * @param senha
     */
    @Override
    public void setSenhaFuncionario(String senha) {
        if (senha.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.senhaFuncionario = senha;
        }
    }

    /**
     *
     * @param tipo
     */
    @Override
    public void setTipoFuncionario(String tipo) {
        this.tipoFuncionario = tipo;

    }

    /**
     *
     * @return
     */
    @Override
    public String getTipo() {
        return this.tipoFuncionario;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setIdFuncionario(int id) {
        if (id >= 0) {
            this.idFuncionario = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return this.idFuncionario;
    }

    @Override
    public void create() {
        Jargon2.Hasher hasher = Jargon2.jargon2Hasher()
            .type(Jargon2.Type.ARGON2id)
            .memoryCost(65536)
            .timeCost(3)
            .parallelism(4)
            .saltLength(16)
            .hashLength(16);
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Funcionario(id, nome, cpf, rg, email, telefone, dataNascimento, cep, rua, numero, complemento, bairro, senha, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, this.idFuncionario);
            statement.setString(2, this.nomeFuncionario);
            statement.setString(3, this.cpfFuncionario);
            statement.setString(4, this.rgFuncionario);
            statement.setString(5, this.emailFuncionario);
            statement.setString(6, this.telefoneFuncionario);
            statement.setDate(7, new java.sql.Date(this.dataNascimentoFuncionario.getTime()));
            statement.setString(8, this.cepFuncionario);
            statement.setString(9, this.ruaFuncionario);
            statement.setString(10, this.numeroFuncionario);
            statement.setString(11, this.complementoFuncionario);
            statement.setString(12, this.bairroFuncionario);
            statement.setString(13, hasher.password(this.senhaFuncionario.getBytes()).encodedHash());
            statement.setString(14, this.tipoFuncionario);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, cpf, rg, email, telefone, dataNascimento, cep, rua, numero, complemento, bairro, tipo FROM Funcionario WHERE id = ?");
            statement.setInt(1, this.idFuncionario);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    this.idFuncionario = rs.getInt("id");
                    this.nomeFuncionario = rs.getString("nome");
                    this.cpfFuncionario = rs.getString("cpf");
                    this.rgFuncionario = rs.getString("rg");
                    this.emailFuncionario = rs.getString("email");
                    this.telefoneFuncionario = rs.getString("telefone");
                    this.dataNascimentoFuncionario = rs.getDate("dataNascimento");
                    this.cepFuncionario = rs.getString("cep");
                    this.ruaFuncionario = rs.getString("rua");
                    this.numeroFuncionario = rs.getString("numero");
                    this.complementoFuncionario = rs.getString("complemento");
                    this.bairroFuncionario = rs.getString("bairro");
                    this.tipoFuncionario = rs.getString("tipo");
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
    public List<Funcionario> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, cpf, rg, email, telefone, dataNascimento, cep, rua, numero, complemento, bairro, tipo FROM Funcionario");
            ResultSet rs = statement.executeQuery();
            List<Funcionario> funcionarios = new ArrayList<>();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.idFuncionario = rs.getInt("id");
                funcionario.nomeFuncionario = rs.getString("nome");
                funcionario.cpfFuncionario = rs.getString("cpf");
                funcionario.rgFuncionario = rs.getString("rg");
                funcionario.emailFuncionario = rs.getString("email");
                funcionario.telefoneFuncionario = rs.getString("telefone");
                funcionario.dataNascimentoFuncionario = rs.getDate("dataNascimento");
                funcionario.cepFuncionario = rs.getString("cep");
                funcionario.ruaFuncionario = rs.getString("rua");
                funcionario.numeroFuncionario = rs.getString("numero");
                funcionario.complementoFuncionario = rs.getString("complemento");
                funcionario.bairroFuncionario = rs.getString("bairro");
                funcionario.tipoFuncionario = rs.getString("tipo");
                funcionarios.add(funcionario);
            }
            return funcionarios;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Funcionario SET nome = ?, cpf = ?, rg = ?, email = ?, telefone = ?, dataNascimento = ?, cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, senha = ?, tipo = ? WHERE id = ?");
            statement.setInt(13, this.idFuncionario);
            statement.setString(1, this.nomeFuncionario);
            statement.setString(2, this.cpfFuncionario);
            statement.setString(3, this.rgFuncionario);
            statement.setString(4, this.emailFuncionario);
            statement.setString(5, this.telefoneFuncionario);
            statement.setDate(6, new java.sql.Date(this.dataNascimentoFuncionario.getTime()));
            statement.setString(7, this.cepFuncionario);
            statement.setString(8, this.ruaFuncionario);
            statement.setString(9, this.numeroFuncionario);
            statement.setString(10, this.complementoFuncionario);
            statement.setString(11, this.bairroFuncionario);
            statement.setString(12, this.tipoFuncionario);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Funcionario WHERE id = ?");
            statement.setInt(1, this.idFuncionario);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idFuncionario;
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
        final Funcionario other = (Funcionario) obj;
        if (this.idFuncionario != other.idFuncionario) {
            return false;
        }
        return true;
    }
    
    public Funcionario login(String email, char[] senha) {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, cpf, rg, email, telefone, dataNascimento, cep, rua, numero, complemento, bairro, senha, tipo FROM Funcionario WHERE email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    Jargon2.Verifier verifier = Jargon2.jargon2Verifier();
                    if (verifier.hash(rs.getString("senha")).password(new String(senha).getBytes()).verifyEncoded()) {
                        this.idFuncionario = rs.getInt("id");
                        this.nomeFuncionario = rs.getString("nome");
                        this.cpfFuncionario = rs.getString("cpf");
                        this.rgFuncionario = rs.getString("rg");
                        this.emailFuncionario = rs.getString("email");
                        this.telefoneFuncionario = rs.getString("telefone");
                        this.dataNascimentoFuncionario = rs.getDate("dataNascimento");
                        this.cepFuncionario = rs.getString("cep");
                        this.ruaFuncionario = rs.getString("rua");
                        this.numeroFuncionario = rs.getString("numero");
                        this.complementoFuncionario = rs.getString("complemento");
                        this.bairroFuncionario = rs.getString("bairro");
                        this.tipoFuncionario = rs.getString("tipo");
                        return this;
                    }
                } else if (currentRow < 1) {
                    // Poucas linhas retornadas
                } else if (currentRow > 1) {
                    // Muitas linhas retornadas
                }
            }
        } catch (SQLException x) {

        }
        return null;
    }

}
