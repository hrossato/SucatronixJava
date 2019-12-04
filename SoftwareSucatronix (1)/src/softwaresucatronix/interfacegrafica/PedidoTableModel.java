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
import softwaresucatronix.bean.ProdutoPedido;
import softwaresucatronix.bean.Pedido;

/**
 *
 * @author guilhermeoc97
 */
public class PedidoTableModel extends AbstractTableModel {

    private List<Pedido> pedidos;
    
    public PedidoTableModel() {
        this.pedidos = new ArrayList<>();
    }
    
    public PedidoTableModel(boolean fetch) {
        if (fetch) {
            this.pedidos = new Pedido().readAll();
        } else {
            this.pedidos = new ArrayList<>();
        }
    }
    
    public PedidoTableModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public void addPedido(Pedido pedido, boolean immediately) {
        if (immediately) {
            pedido.create();
            this.pedidos = new Pedido().readAll();
        } else {
            this.pedidos.add(pedido);
        }
        this.fireTableRowsInserted(0, this.pedidos.size() + 1);
    }
    
    public void addPedido(Pedido pedido) {
        this.addPedido(pedido, false);
    }
    
    public void removePedido(Pedido pedido) {
        pedido.read();
        pedido.getProdutosPedido().forEach((ProdutoPedido item) -> item.delete());
        pedido.delete();
        this.pedidos.remove(pedido);
        this.fireTableRowsDeleted(0, this.pedidos.size() + 1);
    }
    
    public void avancaStatusPedido(Pedido pedido) {
        if (!pedido.getSituacaoPedido().equals("Recebida")) {
            switch (pedido.getSituacaoPedido()) {
                case "Pendente":
                    pedido.setSituacaoPedido("Realizada");
                    break;
                case "Realizada":
                    pedido.setSituacaoPedido("Enviada");
                    break;
                case "Enviada":
                    pedido.setSituacaoPedido("Recebida");
                    break;
                default:
                    return;
            }
            pedido.update();
            if (pedido.getSituacaoPedido().equals("Recebida")) {
                pedido.getProdutosPedido().forEach((item) -> {
                    item.getProduto().setEstoqueAtualProduto(item.getProduto().getEstoqueAtualProduto() + item.getQuantidade());
                    item.getProduto().update();
                });
            }
            if (this.pedidos.indexOf(pedido) > -1) {
                this.pedidos.set(this.pedidos.indexOf(pedido), pedido);
                this.fireTableCellUpdated(this.pedidos.indexOf(pedido), 1);
            }
        }
    }
    
    public void clearModel() {
        this.pedidos.clear();
        this.fireTableRowsDeleted(0, 0);
    }
    
    public List<Pedido> getModel() {
        return this.pedidos;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Fornecedor";
            case 1:
                return "Situação";
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
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedido.getFornecedorPedido().getNomeFornecedor();
            case 1:
                return pedido.getSituacaoPedido();
            case 2:
                return new SimpleDateFormat("dd/MM/yyyy").format(pedido.getDataPedido());
            case 3:
                return new DecimalFormat("R$ #,##0.00").format(pedido.getProdutosPedido()
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
