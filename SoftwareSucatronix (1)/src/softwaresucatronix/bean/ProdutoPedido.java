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
import softwaresucatronix.interfaces.InterfaceProdutoPedido;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoPedido implements InterfaceProdutoPedido {
    
    public Produto produto;
    
    public Pedido pedido;
    
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
    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Produto_Pedido(produto, quantidade, pedido) VALUES (?, ?, ?)");
            statement.setInt(1, this.produto.getIdProduto());
            statement.setFloat(2, this.quantidade);
            statement.setInt(3, this.pedido.getIdPedido());
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public List<ProdutoPedido> readAllByPedido() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT produto, quantidade, pedido FROM Produto_Pedido WHERE pedido = ?");
            statement.setInt(1, this.getPedido().getIdPedido());
            ResultSet rs = statement.executeQuery();
            List<ProdutoPedido> produtosPedido = new ArrayList<>();
            while (rs.next()) {
                ProdutoPedido produtoPedido = new ProdutoPedido();
                produtoPedido.produto = new Produto();
                produtoPedido.produto.setIdProduto(rs.getInt("produto"));
                produtoPedido.produto.read();
                produtoPedido.quantidade = rs.getFloat("quantidade");
                produtoPedido.pedido = this.pedido;
                produtosPedido.add(produtoPedido);
            }
            return produtosPedido;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Produto_Pedido SET quantidade = ? WHERE produto = ? AND pedido = ?");
            statement.setInt(2, this.produto.getIdProduto());
            statement.setFloat(1, this.quantidade);
            statement.setInt(3, this.pedido.getIdPedido());
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Produto_Pedido WHERE pedido = ?");
            statement.setInt(1, this.pedido.getIdPedido());
            statement.executeUpdate();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.produto);
        hash = 59 * hash + Objects.hashCode(this.pedido);
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
        final ProdutoPedido other = (ProdutoPedido) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        return true;
    }
    
    
    
}
