package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;

import softwaresucatronix.interfaces.InterfaceVenda;

/**
 *
 * @author T-Gamer
 */
public class Venda implements InterfaceVenda {

    private int idVenda;

    private Cliente clienteVenda;

    private Date dataVenda;

    private Funcionario funcionarioVenda;

    private String situacaoVenda;

    private List<ProdutoVenda> produtosVenda;

    /**
     *
     * @param id
     */
    @Override
    public void setIdVenda(int id) {
        if (id >= 0) {
            this.idVenda = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getIdVenda() {
        return this.idVenda;
    }

    /**
     *
     * @param cliente
     */
    @Override
    public void setClienteVenda(Cliente cliente) {
        this.clienteVenda = cliente;

    }

    /**
     *
     * @return
     */
    @Override
    public Cliente getClienteVenda() {
        return this.clienteVenda;
    }

    /**
     *
     * @param data
     */
    @Override
    public void setDataVenda(Date data) {
        this.dataVenda = data;

    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataVenda() {
        return this.dataVenda;
    }

    /**
     *
     * @param funcionario
     */
    @Override
    public void setFuncionarioVenda(Funcionario funcionario) {
        this.funcionarioVenda = funcionario;
    }

    /**
     *
     * @return
     */
    @Override
    public Funcionario getFuncionarioVenda() {
        return this.funcionarioVenda;
    }

    /**
     *
     * @param situacao
     */
    @Override
    public void setSituacaoVenda(String situacao) {
        this.situacaoVenda = situacao;

    }

    /**
     *
     * @return
     */
    @Override
    public String getSituacaoVenda() {
        return this.situacaoVenda;
    }

    /**
     *
     * @param produto
     */
    @Override
    public void setProdutosVenda(List<ProdutoVenda> produto) {
        this.produtosVenda = produto;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProdutoVenda> getProdutosVenda() {
        return this.produtosVenda;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Venda(id, cliente, data, funcionario, situacao) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, this.idVenda);
            statement.setInt(2, this.clienteVenda.getIdCliente());
            statement.setDate(3, new java.sql.Date(this.dataVenda.getTime()));
            statement.setInt(4, this.funcionarioVenda.getId());
            statement.setString(5, this.situacaoVenda);
            statement.executeUpdate();
            connection.commit();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            this.setIdVenda(Long.valueOf(rs.getLong(1)).intValue());
            this.produtosVenda.forEach((ProdutoVenda item) -> {
                item.setVenda(this);
                item.create();
            });
        } catch (SQLException x) {
            System.err.println(x);
        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, cliente, data, funcionario, situacao FROM Venda WHERE id = ?");
            statement.setInt(1, this.idVenda);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    ProdutoVenda produtoVenda = new ProdutoVenda();
                    produtoVenda.setVenda(this);
                    this.idVenda = rs.getInt("id");
                    this.dataVenda = rs.getDate("data");
                    this.situacaoVenda = rs.getString("situacao");
                    this.clienteVenda = new Cliente();
                    this.clienteVenda.setIdCliente(rs.getInt("cliente"));
                    this.clienteVenda.read();
                    this.funcionarioVenda = new Funcionario();
                    this.funcionarioVenda.setIdFuncionario(rs.getInt("funcionario"));
                    this.funcionarioVenda.read();
                    this.produtosVenda = produtoVenda.readAllByVenda();
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
    public List<Venda> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, cliente, data, funcionario, situacao FROM Venda");
            ResultSet rs = statement.executeQuery();
            ProdutoVenda produtoVenda = new ProdutoVenda();
            List<Venda> vendas = new ArrayList<>();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.idVenda = rs.getInt("id");
                venda.dataVenda = rs.getDate("data");
                venda.situacaoVenda = rs.getString("situacao");
                venda.clienteVenda = new Cliente();
                venda.clienteVenda.setIdCliente(rs.getInt("cliente"));
                venda.clienteVenda.read();
                venda.funcionarioVenda = new Funcionario();
                venda.funcionarioVenda.setIdFuncionario(rs.getInt("funcionario"));
                venda.funcionarioVenda.read();
                produtoVenda.setVenda(venda);
                venda.produtosVenda = produtoVenda.readAllByVenda();
                vendas.add(venda);
            }
            return vendas;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE Venda SET cliente = ?, data = ?, funcionario = ?, situacao = ? WHERE id = ?");
            statement.setInt(5, this.idVenda);
            statement.setInt(1, this.clienteVenda.getIdCliente());
            statement.setDate(2, new java.sql.Date(this.dataVenda.getTime()));
            statement.setInt(3, this.funcionarioVenda.getId());
            statement.setString(4, this.situacaoVenda);
            statement.executeUpdate();
            this.produtosVenda.forEach((ProdutoVenda item) -> item.update());
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Venda WHERE id = ?");
            statement.setInt(1, this.idVenda);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idVenda;
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
        final Venda other = (Venda) obj;
        if (this.idVenda != other.idVenda) {
            return false;
        }
        return true;
    }
    
    
}
