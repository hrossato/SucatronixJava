/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import softwaresucatronix.dal.ModuloConexao;
import softwaresucatronix.interfaces.InterfaceProdutoVenda;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoVenda implements InterfaceProdutoVenda {
    
    public Produto produto;
    
    public Venda venda;
    
    public float quantidade;

    @Override
    public Produto getProduto() {
        return produto;
    }

    @Override
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public Venda getVenda() {
        return venda;
    }

    @Override
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public float getQuantidade() {
        return quantidade;
    }

    @Override
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public void create() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Produto_Venda(produto, quantidade, venda) VALUES (?, ?, ?)");
            statement.setInt(1, this.produto.getIdProduto());
            statement.setFloat(2, this.quantidade);
            statement.setInt(3, this.venda.getIdVenda());
            statement.executeUpdate();
        } catch (SQLException x) {
            System.err.println(x);
        }
    }

    @Override
    public List<ProdutoVenda> readAllByVenda() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT produto, quantidade, venda FROM Produto_Venda WHERE venda = ?");
            statement.setInt(1, this.venda.getIdVenda());
            ResultSet rs = statement.executeQuery();
            List<ProdutoVenda> produtosVenda = new ArrayList<>();
            while (rs.next()) {
                ProdutoVenda produtoVenda = new ProdutoVenda();
                produtoVenda.produto = new Produto();
                produtoVenda.produto.setIdProduto(rs.getInt("produto"));
                produtoVenda.produto.read();
                produtoVenda.quantidade = rs.getFloat("quantidade");
                produtoVenda.venda = this.venda;
                produtosVenda.add(produtoVenda);
            }
            return produtosVenda;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Produto_Venda SET quantidade = ? WHERE produto = ? AND venda = ?");
            statement.setInt(2, this.produto.getIdProduto());
            statement.setFloat(1, this.quantidade);
            statement.setInt(3, this.venda.getIdVenda());
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Produto_Venda WHERE venda = ?");
            statement.setInt(1, this.venda.getIdVenda());
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.produto);
        hash = 37 * hash + Objects.hashCode(this.venda);
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
        final ProdutoVenda other = (ProdutoVenda) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }
    
    
    
}
