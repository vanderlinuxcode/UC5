package app;

//Classe principal: ControleFinanceiro
public class ControleFinanceiro {
	// Atributos
	private double saldo;
	private double pagamentoLuz;

	// Construtor
	public ControleFinanceiro(double saldoInicial) {
		this.saldo = saldoInicial;
	}

	// Despesas obrigatórias
	public ControleFinanceiro(double despesasPagar) {
		this.pagamentoLuz = despesasPagar;
	}

	// Método para registrar entrada de dinheiro
	public void registrarEntrada(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println("Entrada registrada: R$ " + valor);
		} else {
			System.out.println("Valor inválido para entrada!");
		}
	}

	// Método para registrar saída de dinheiro
	public void registrarSaida(double valor) {
		if (valor > 0 && valor <= saldo) {
			saldo -= valor;
			System.out.println("Saída registrada: R$ " + valor);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido!");
		}
	}

	// Método para registrar despesas obrigatórias se tiver saldo, exemplo com energia
	public void contaLuz(double pagamentoLuz) {
		if (saldo >= pagamentoLuz) {
			saldo -= pagamentoLuz;
			System.out.println("Despesa com Energia: R$ " + pagamentoLuz);
		} else {
			System.out.println("Saldo insuficiente para efetuar pagamento.");
			System.out.println("Valor devedor Energia: R$ " + pagamentoLuz);
		}
	}

	// Método para exibir o saldo atual
	public void exibirSaldo() {
		saldo -= pagamentoLuz;
		System.out.println("Saldo: R$ " + saldo);
	}

	// Método main para executar os testes
	public static void main(String[] args) {
		// Criando um objeto ControleFinanceiro com saldo inicial de R$ 500
		ControleFinanceiro controle = new ControleFinanceiro(500);

		
		// Testando entradas e saídas
		controle.exibirSaldo();
		controle.registrarEntrada(200);
		controle.exibirSaldo();
		controle.registrarSaida(100);
		controle.exibirSaldo();
		controle.registrarSaida(200);
		controle.exibirSaldo();
		controle.contaLuz(290); // Despesa obrigatória com energia
		controle.exibirSaldo(); // Exibe saldo final
	}
}
