package expresLambda;

public class Main {

	public static void main(String[] args) {

		System.out.println("=== Inicio do teste ===");

		// Implementação da classe anônima Runnable 
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Estudando a expressão lambda 1.");
			}
		};
		
		// Expressão Lambda anonimous
		Runnable r2;
		
		r2 = () // LIsta de argumentos
		
		-> // Seta 
		
		System.out.println("Estudando a expressão lambda 2.");// corpo

		r1.run();
		r2.run();
	}

}
