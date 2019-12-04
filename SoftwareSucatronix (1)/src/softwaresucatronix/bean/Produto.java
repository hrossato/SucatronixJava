package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfaces.InterfaceProduto;

/**
 *
 * @author T-Gamer
 */
public class Produto implements InterfaceProduto {

    private int idProduto;

    private String nomeProduto;

    private float estoqueAtualProduto;

    private float precoProduto;

    /**
     *
     * @param nome
     */
    @Override
    public void setNomeProduto(String nome) {
        if (nome.isEmpty()) {
            System.out.println("Este campo deve ser preenchido");
        } else {
            this.nomeProduto = nome;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /**
     *
     * @param estoqueQtd
     */
    @Override
    public void setEstoqueAtualProduto(float estoqueQtd) {
        if (estoqueQtd >= 0) {
            this.estoqueAtualProduto = estoqueQtd;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public float getEstoqueAtualProduto() {
        return this.estoqueAtualProduto;
    }

    /**
     *
     * @param valor
     */
    @Override
    public void setPrecoProduto(float valor) {
        if (valor >= 0) {
            this.precoProduto = valor;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public float getPrecoProduto() {
        return this.precoProduto;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setIdProduto(int id) {
        if (id >= 0) {
            this.idProduto = id;
        } else {
            System.out.println("Este campo deve ser preenchido com um numero maior ou igual a 0 válido");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getIdProduto() {
        return this.idProduto;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Produto(id, nome, estoqueAtual, preco) VALUES (?, ?, ?, ?)");
            statement.setInt(1, this.idProduto);
            statement.setString(2, this.nomeProduto);
            statement.setDouble(3, this.estoqueAtualProduto);
            statement.setFloat(4, this.precoProduto);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void read() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, estoqueAtual, preco FROM Produto WHERE id = ?");
            statement.setInt(1, this.idProduto);
            ResultSet rs = statement.executeQuery();
            if (rs.last()) {
                int currentRow = rs.getRow();
                if (currentRow == 1) {
                    this.idProduto = rs.getInt("id");
                    this.nomeProduto = rs.getString("nome");
                    this.estoqueAtualProduto = rs.getFloat("estoqueAtual");
                    this.precoProduto = rs.getFloat("preco");
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
    public List<Produto> readAll() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, estoqueAtual, preco FROM Produto");
            ResultSet rs = statement.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.idProduto = rs.getInt("id");
                produto.nomeProduto = rs.getString("nome");
                produto.estoqueAtualProduto = rs.getFloat("estoqueAtual");
                produto.precoProduto = rs.getFloat("preco");
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }
    
    @Override
    public List<Produto> readAllEmEstoque() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, nome, estoqueAtual, preco FROM Produto WHERE estoqueAtual > 0");
            ResultSet rs = statement.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.idProduto = rs.getInt("id");
                produto.nomeProduto = rs.getString("nome");
                produto.estoqueAtualProduto = rs.getFloat("estoqueAtual");
                produto.precoProduto = rs.getFloat("preco");
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Produto SET nome = ?, estoqueAtual = ?, preco = ? WHERE id = ?");
            statement.setInt(4, this.idProduto);
            statement.setString(1, this.nomeProduto);
            statement.setFloat(2, this.estoqueAtualProduto);
            statement.setFloat(3, this.precoProduto);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Produto WHERE id = ?");
            statement.setInt(1, this.idProduto);
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public String toString() {
        return nomeProduto + " (" + estoqueAtualProduto + " disponíveis)";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idProduto;
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
        final Produto other = (Produto) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }
    
    
    
}
