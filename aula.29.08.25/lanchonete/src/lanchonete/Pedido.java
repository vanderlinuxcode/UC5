package lanchonete;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Pedido {
    private static int contador = 1; // contador global de pedidos
    private String numero;           // número formatado com 3 dígitos
    private List<Produto> itens;
    private double total;

    public Pedido() {
        this.numero = formatarNumero(contador++);
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    private String formatarNumero(int num) {
        DecimalFormat df = new DecimalFormat("000");
        return df.format(num);
    }

    public void adicionarProduto(Produto produto) {
        itens.add(produto);
        total += produto.getValor();
    }

    public void exibirPedido() {
        System.out.println("Pedido #" + numero);
        for (Produto p : itens) {
            p.exibirInfo();
        }
        System.out.printf("Total do pedido: R$ %.2f\n", total);
    }

    public double getTotal() {
        return total;
    }

    public String getNumero() {
        return numero;
    }
}
