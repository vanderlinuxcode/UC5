package interfaceLambda;

public class TreinoLambda {

	// interface 1
	interface Num {
		double getValue();
	}

	// interface 2
	interface valorNumerico {
		boolean getValue(int n);
	}

	// interface 3
	interface ValorNumerico1 {
		boolean getValue(int n, int n1);
	}

	public static void main(String[] args) {

//		// Expressão lambda utilizada como uma constante
//		Num n;
//		n = () -> 555.11;
//		System.out.println(n.getValue());
//
//		// Utilização da classe math com a estrutura lambda
//		Num n2 = () -> Math.random() * 100;
//		Num n3 = () -> Math.random() * 100;
//
//		// declaração interface 2
//		double resul, resul1;
//
//		// interface 1
//		System.out.println(resul = n2.getValue());
//		System.out.println(resul1 = n3.getValue());
//
//		// interface 2
//		valorNumerico isPar = (int resul2) -> (resul2 % 2) == 0;
//		System.out.println(isPar.getValue(100));
//		System.out.println(isPar.getValue(90));
//
//		// interface 3
//		ValorNumerico1 isDiv = (x, y) -> (x % y) == 0;
//		System.out.println(isDiv.getValue(10, 2));
//		System.out.println(isDiv.getValue(10, 3));
//		
//		// interface 3		
//		ValorNumerico1 expressao1 = (x, y) -> (x % y) == 0;
//		
		// Chamado aqui de bloco Lambda interface 3
		ValorNumerico1 expressao2 = (x, y) -> {
			int w = x * y;
			return w >= 100;
		};
		System.out.println(expressao2.getValue(10, 10));
	}
}
