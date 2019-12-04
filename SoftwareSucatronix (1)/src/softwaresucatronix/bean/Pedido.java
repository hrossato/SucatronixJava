package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;

import softwaresucatronix.interfaces.InterfacePedido;

/**
 *
 * @author T-Gamer
 */
public class Pedido implements InterfacePedido {

    private int idPedido;

    private Fornecedor fornecedorPedido;

    private Date dataPedido;

    private Funcionario funcionarioPedido;

    private String situacaoPedido;

    private List<ProdutoPedido> produtosPedido;

    /**
     *
     * @param id
     */
    @Override
    public void setIdPedido(int id) {
        if (id >= 0) {
            this.idPedido = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getIdPedido() {
        return this.idPedido;
    }

    /**
     *
     * @param fornecedor
     */
    @Override
    public void setFornecedorPedido(Fornecedor fornecedor) {
        this.fornecedorPedido = fornecedor;

    }

    /**
     *
     * @return
     */
    @Override
    public Fornecedor getFornecedorPedido() {
        return this.fornecedorPedido;
    }

    /**
     *
     * @param data
     */
    @Override
    public void setDataPedido(Date data) {
        this.dataPedido = data;

    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataPedido() {
        return this.dataPedido;
    }

    /**
     *
     * @param funcionario
     */
    @Override
    public void setFuncionarioPedido(Funcionario funcionario) {
        this.funcionarioPedido = funcionario;
    }

    /**
     *
     * @return
     */
    @Override
    public Funcionario getFuncionarioPedido() {
        return this.funcionarioPedido;
    }

    /**
     *
     * @param situacao
     */
    @Override
    public void setSituacaoPedido(String situacao) {
        this.situacaoPedido = situacao;

    }

    /**
     *
     * @return
     */
    @Override
    public String getSituacaoPedido() {
        return this.situacaoPedido;
    }

    /**
     *
     * @param produto
     */
    @Override
    public void setProdutosPedido(List<ProdutoPedido> produto) {
        this.produtosPedido = produto;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProdutoPedido> getProdutosPedido() {
        return this.produtosPedido;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Pedido(id, fornecedor, data, funcionario, situacao) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, this.idPedido);
            statement.setInt(2, this.fornecedorPedido.getIdFornecedor());
            statement.setDate(3, new java.sql.Date(this.dataPedido.getTime()));
            statement.setInt(4, this.funcionarioPedido.getId());
            statement.setString(5, this.situacaoPedido);
            statement.executeUpdate();
            this.produtosPedido.forEach((ProdutoPedido item) -> item.create());
            connection.commit();
        } catch (SQLException x) {
            
        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, fornecedor, data, funcionario, situacao FROM Pedido WHERE id = ?");
            statement.setInt(1, this.idPedido);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    ProdutoPedido produtoPedido = new ProdutoPedido();
                    produtoPedido.setPedido(this);
                    this.idPedido = rs.getInt("id");
                    this.dataPedido = rs.getDate("data");
                    this.situacaoPedido = rs.getString("situacao");
                    this.fornecedorPedido = new Fornecedor();
                    this.fornecedorPedido.setIdFornecedor(rs.getInt("fornecedor"));
                    this.fornecedorPedido.read();
                    this.funcionarioPedido = new Funcionario();
                    this.funcionarioPedido.setIdFuncionario(rs.getInt("funcionario"));
                    this.funcionarioPedido.read();
                    this.produtosPedido = produtoPedido.readAllByPedido();
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
    public List<Pedido> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, fornecedor, data, funcionario, situacao FROM Pedido");
            ResultSet rs = statement.executeQuery();
            ProdutoPedido produtoPedido = new ProdutoPedido();
            List<Pedido> vendas = new ArrayList<>();
            while (rs.next()) {
                Pedido venda = new Pedido();
                venda.idPedido = rs.getInt("id");
                venda.dataPedido = rs.getDate("data");
                venda.situacaoPedido = rs.getString("situacao");
                venda.fornecedorPedido = new Fornecedor();
                venda.fornecedorPedido.setIdFornecedor(rs.getInt("fornecedor"));
                venda.fornecedorPedido.read();
                venda.funcionarioPedido = new Funcionario();
                venda.funcionarioPedido.setIdFuncionario(rs.getInt("funcionario"));
                venda.funcionarioPedido.read();
                produtoPedido.setPedido(venda);
                venda.produtosPedido = produtoPedido.readAllByPedido();
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
            PreparedStatement statement = connection.prepareStatement("UPDATE Pedido SET fornecedor = ?, data = ?, funcionario = ?, situacao = ? WHERE id = ?");
            statement.setInt(5, this.idPedido);
            statement.setInt(1, this.fornecedorPedido.getIdFornecedor());
            statement.setDate(2, new java.sql.Date(this.dataPedido.getTime()));
            statement.setInt(3, this.funcionarioPedido.getId());
            statement.setString(4, this.situacaoPedido);
            statement.executeUpdate();
            this.produtosPedido.forEach((ProdutoPedido item) -> item.update());
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Pedido WHERE id = ?");
            statement.setInt(1, this.idPedido);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPedido;
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
        final Pedido other = (Pedido) obj;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }
    
    
}
