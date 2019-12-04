package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfaces.InterfaceSolicitacao;

public class Solicitacao implements InterfaceSolicitacao {

    private int idSolicitacao;

    private Funcionario funcionarioSolicitacao;

    private Date dataSolicitacao;

    private boolean aprovada;

    private List<ProdutoSolicitacao> produtosSolicitacao;

    /**
     *
     * @param id
     */
    @Override
    public void setIdSolicitacao(int id) {
        if (id >= 0) {
            this.idSolicitacao = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getIdSolicitacao() {
        return this.idSolicitacao;
    }

    /**
     *
     * @param funcionario
     */
    @Override
    public void setFuncionarioSolicitacao(Funcionario funcionario) {
        this.funcionarioSolicitacao = funcionario;
    }

    /**
     *
     * @return
     */
    @Override
    public Funcionario getFuncionarioSolicitacao() {
        return this.funcionarioSolicitacao;
    }

    /**
     *
     * @param data
     */
    @Override
    public void setDataSolicitacao(Date data) {
        this.dataSolicitacao = data;

    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataSolicitacao() {
        return this.dataSolicitacao;
    }

    /**
     *
     * @param aprovada
     */
    @Override
    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getAprovada() {
        return this.aprovada;
    }

    /**
     *
     * @param produtos
     */
    @Override
    public void setProdutosSolicitacao(List<ProdutoSolicitacao> produtos) {
        this.produtosSolicitacao = produtos;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProdutoSolicitacao> getProdutosSolicitacao() {
        return this.produtosSolicitacao;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Solicitacao(id, data, funcionario, aprovada) VALUES (?, ?, ?, ?)");
            statement.setInt(1, this.idSolicitacao);
            statement.setDate(2, new java.sql.Date(this.dataSolicitacao.getTime()));
            statement.setInt(3, this.funcionarioSolicitacao.getId());
            statement.setBoolean(4, this.aprovada);
            statement.executeUpdate();
            this.produtosSolicitacao.forEach((ProdutoSolicitacao item) -> item.create());
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, data, funcionario, aprovada FROM Solicitacao WHERE id = ?");
            statement.setInt(1, this.idSolicitacao);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    ProdutoSolicitacao produtoSolicitacao = new ProdutoSolicitacao();
                    produtoSolicitacao.setSolicitacao(this);
                    this.idSolicitacao = rs.getInt("id");
                    this.dataSolicitacao = rs.getDate("data");
                    this.aprovada = rs.getBoolean("aprovada");
                    this.funcionarioSolicitacao = new Funcionario();
                    this.funcionarioSolicitacao.setIdFuncionario(rs.getInt("funcionario"));
                    this.funcionarioSolicitacao.read();
                    this.produtosSolicitacao = produtoSolicitacao.readAllBySolicitacao();
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
    public List<Solicitacao> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, data, funcionario, aprovada FROM Solicitacao");
            ResultSet rs = statement.executeQuery();
            ProdutoSolicitacao produtoSolicitacao = new ProdutoSolicitacao();
            List<Solicitacao> solicitacoes = new ArrayList<>();
            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.idSolicitacao = rs.getInt("id");
                solicitacao.dataSolicitacao = rs.getDate("data");
                solicitacao.aprovada = rs.getBoolean("aprovada");
                solicitacao.funcionarioSolicitacao = new Funcionario();
                solicitacao.funcionarioSolicitacao.setIdFuncionario(rs.getInt("funcionario"));
                solicitacao.funcionarioSolicitacao.read();
                produtoSolicitacao.setSolicitacao(solicitacao);
                solicitacao.produtosSolicitacao = produtoSolicitacao.readAllBySolicitacao();
                solicitacoes.add(solicitacao);
            }
            return solicitacoes;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE Solicitacao SET data = ?, funcionario = ?, aprovada = ? WHERE id = ?");
            statement.setInt(4, this.idSolicitacao);
            statement.setDate(1, new java.sql.Date(this.dataSolicitacao.getTime()));
            statement.setInt(2, this.funcionarioSolicitacao.getId());
            statement.setBoolean(3, this.aprovada);
            statement.executeUpdate();
            this.produtosSolicitacao.forEach((ProdutoSolicitacao item) -> item.update());
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Solicitacao WHERE id = ?");
            statement.setInt(1, this.idSolicitacao);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idSolicitacao;
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
        final Solicitacao other = (Solicitacao) obj;
        if (this.idSolicitacao != other.idSolicitacao) {
            return false;
        }
        return true;
    }
    
    
}
