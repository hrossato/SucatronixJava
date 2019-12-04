/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaresucatronix.interfacegrafica;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import softwaresucatronix.bean.ProdutoSolicitacao;
import softwaresucatronix.bean.Solicitacao;

/**
 *
 * @author guilhermeoc97
 */
public class SolicitacaoTableModel extends AbstractTableModel {

    private List<Solicitacao> solicitacoes;
    
    public SolicitacaoTableModel() {
        this.solicitacoes = new ArrayList<>();
    }
    
    public SolicitacaoTableModel(boolean fetch) {
        if (fetch) {
            this.solicitacoes = new Solicitacao().readAll();
        } else {
            this.solicitacoes = new ArrayList<>();
        }
    }
    
    public SolicitacaoTableModel(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
    
    public void addSolicitacao(Solicitacao solicitacao, boolean immediately) {
        if (immediately) {
            solicitacao.create();
            this.solicitacoes = new Solicitacao().readAll();
        } else {
            this.solicitacoes.add(solicitacao);
        }
        this.fireTableRowsInserted(0, this.solicitacoes.size() + 1);
    }
    
    public void addSolicitacao(Solicitacao solicitacao) {
        this.addSolicitacao(solicitacao, false);
    }
    
    public void removeSolicitacao(Solicitacao solicitacao) {
        solicitacao.read();
        solicitacao.getProdutosSolicitacao().forEach((ProdutoSolicitacao item) -> item.delete());
        solicitacao.delete();
        this.solicitacoes.remove(solicitacao);
        this.fireTableRowsDeleted(0, this.solicitacoes.size() + 1);
    }
    
    public void aprovaSolicitacao(Solicitacao solicitacao) {
        if (!solicitacao.getAprovada()) {
            solicitacao.setAprovada(true);
            boolean podeAtualizar = solicitacao.getProdutosSolicitacao().stream().map((item) -> {
                return item.getProduto().getEstoqueAtualProduto() > item.getQuantidade();
            }).reduce(true, (acc, item) -> {
                return acc && item;
            });
            if (podeAtualizar) {
                solicitacao.getProdutosSolicitacao().forEach((item) -> {
                    item.getProduto().setEstoqueAtualProduto(item.getProduto().getEstoqueAtualProduto() - item.getQuantidade());
                    item.getProduto().update();
                });
                solicitacao.update();
                if (this.solicitacoes.indexOf(solicitacao) > -1) {
                    this.solicitacoes.set(this.solicitacoes.indexOf(solicitacao), solicitacao);
                    this.fireTableCellUpdated(this.solicitacoes.indexOf(solicitacao), 1);
                }
            }
        }
    }
    
    public void clearModel() {
        this.solicitacoes.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Solicitacao> getModel() {
        return this.solicitacoes;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Funcionário";
            case 1:
                return "Aprovada?";
            case 2:
                return "Data";
            case 3:
                return "Valor total";
            default:
                return "";
        }
    }
    
    @Override
    public int getRowCount() {
        return solicitacoes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Solicitacao solicitacao = solicitacoes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return solicitacao.getFuncionarioSolicitacao().getNomeFuncionario();
            case 1:
                return solicitacao.getAprovada() ? "Sim" : "Não";
            case 2:
                return new SimpleDateFormat("dd/MM/yyyy").format(solicitacao.getDataSolicitacao());
            case 3:
                return new DecimalFormat("R$ #,##0.00").format(solicitacao.getProdutosSolicitacao()
                        .stream().mapToDouble((item) -> 
                            item.getQuantidade() * item.getProduto().getPrecoProduto()
                        ).reduce(0f, (acc, item) -> {
                            acc += item;
                            return acc;
                        }));
            default:
                return null;
        }
    }
    
}
