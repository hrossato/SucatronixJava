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
import softwaresucatronix.interfaces.InterfaceProdutoSolicitacao;
import softwaresucatronix.interfaces.InterfaceProdutoVenda;

/**
 *
 * @author guilhermeoc97
 */
public class ProdutoSolicitacao implements InterfaceProdutoSolicitacao {
    
    public Produto produto;
    
    public Solicitacao solicitacao;
    
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
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    @Override
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
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
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Produto_Solicitacao(produto, quantidade, solicitacao) VALUES (?, ?, ?)");
            statement.setInt(1, this.produto.getIdProduto());
            statement.setFloat(2, this.quantidade);
            statement.setInt(3, this.solicitacao.getIdSolicitacao());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public List<ProdutoSolicitacao> readAllBySolicitacao() {
        try (Connection connection = ModuloConexao.conector()) {
            PreparedStatement statement = connection.prepareStatement("SELECT produto, quantidade, solicitacao FROM Produto_Solicitacao WHERE solicitacao = ?");
            statement.setInt(1, this.getSolicitacao().getIdSolicitacao());
            ResultSet rs = statement.executeQuery();
            List<ProdutoSolicitacao> produtosSolicitacao = new ArrayList<>();
            while (rs.next()) {
                ProdutoSolicitacao produtoSolicitacao = new ProdutoSolicitacao();
                produtoSolicitacao.produto = new Produto();
                produtoSolicitacao.produto.setIdProduto(rs.getInt("produto"));
                produtoSolicitacao.produto.read();
                produtoSolicitacao.quantidade = rs.getFloat("quantidade");
                produtoSolicitacao.solicitacao = this.solicitacao;
                produtosSolicitacao.add(produtoSolicitacao);
            }
            return produtosSolicitacao;
        } catch (SQLException x) {

        }
        return new ArrayList<>();
    }

    @Override
    public void update() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE Produto_Venda SET quantidade = ? WHERE produto = ? AND solicitacao = ?");
            statement.setInt(2, this.produto.getIdProduto());
            statement.setFloat(1, this.quantidade);
            statement.setInt(3, this.solicitacao.getIdSolicitacao());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public void delete() {
        try (Connection connection = ModuloConexao.conector()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Produto_Solicitacao WHERE solicitacao = ?");
            statement.setInt(1, this.solicitacao.getIdSolicitacao());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException x) {

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.produto);
        hash = 79 * hash + Objects.hashCode(this.solicitacao);
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
        final ProdutoSolicitacao other = (ProdutoSolicitacao) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.solicitacao, other.solicitacao)) {
            return false;
        }
        return true;
    }
    
    
    
}
