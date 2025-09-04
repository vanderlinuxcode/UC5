package app;

//Classe principal: ControleFinanceiro
public class ControleFinanceiro {
	// Atributos
	private double saldo;
	private double pagamentoLuz;
	private double pagamentoAgua;
	private double pagamentos;

	// Construtor
	public ControleFinanceiro(double saldoInicial, double contaLuz, double contaAgua) {
		this.saldo = saldoInicial;
		this.pagamentoLuz = contaLuz;
		this.pagamentoAgua = contaAgua;
	}

	// Método para registrar entrada de dinheiro
	public void registrarEntrada(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println("Entrada registrada: R$" + valor);
		} else {
			System.out.println("Valor inválido para entrada!");
		}
	}

	// Método para registrar saída de dinheiro
	public void registrarSaida(double valor) {
		if (valor > 0 && valor <= saldo) {
			saldo -= valor;
			System.out.println("Saída registrada: R$" + valor);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido!");
		}
	}

	// Método para registrar despesas obrigatórias, energia.
	public void despesaComLuz() {
		if (saldo >= pagamentoLuz) {
			saldo -= pagamentoLuz;
			System.out.println("Despesa com Energia: R$" + pagamentoLuz);
		} else {
			System.out.println("Saldo insuficiente para efetuar pagamento.");
			System.out.println("Valor devedor Energia: R$" + pagamentoLuz);
		}
	}

	// Método para registrar despesas obrigatórias, água.
	public void despesaComAgua() {
		if (saldo >= pagamentoAgua) {
			saldo -= pagamentoAgua;
			System.out.println("Despesa com Água: R$" + pagamentoAgua);
		} else {
			System.out.println("Saldo insuficiente para efetuar pagamento.");
			System.out.println("Valor devedor Energia: R$" + pagamentoAgua);
		}
	}

	// Método para exibir total de pagamentos obrigatórios
	public void despesaTotal() {
		pagamentos = pagamentoLuz + pagamentoAgua;
		System.out.println("Pagamentos com Luz e Água: R$" + pagamentos);
	}

	// Método para exibir o saldo atual
	public void exibirSaldo() {
		System.out.println("Saldo: R$ " + saldo + " disponível.");
		System.out.println("-------------------------------");

	}

	// Método main para executar os testes
	public static void main(String[] args) {
		// Criando um objeto ControleFinanceiro com saldo inicial de R$ 500
		ControleFinanceiro controle = new ControleFinanceiro(500, 190.05, 155.08);

		// Testando entradas e saídas
		System.out.println("    Receita e Despesa     ");
		System.out.println(" ");
		controle.exibirSaldo();
		controle.registrarEntrada(200.67);
		controle.exibirSaldo();
		controle.registrarSaida(100.54);
		controle.exibirSaldo();
		controle.registrarSaida(155.15);
		controle.exibirSaldo();
		controle.despesaComLuz(); // Despesa obrigatória com energia
		controle.exibirSaldo(); // Exibe saldo final
		controle.despesaComAgua(); // Despesa obrigatória com água
		controle.exibirSaldo(); // Exibe saldo final
		controle.despesaTotal(); // Exibe a despesa obrigatória total
	}
}
