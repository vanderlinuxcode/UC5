package lambdaStream;

import java.util.Arrays;
import java.util.List;

public class LambdaStream {

	public static void main(String[] args) {

		// antes do Java 8
		List<String> lista1 = Arrays.asList("Santa Catarina", "Paraná", "São Paulo", "Rio de Janeiro", "Brasília",
				"Ceará");
         
		// Forma funcional de listar
		// for(String s : lista1)
		// System.out.println(s);
		//**Forma customizada
		//lista1.forEach(x -> System.out.println(x));
		//***O próprio compilador que trata a execução
		lista1.forEach(System.out::println);
	}

}
