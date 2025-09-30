package lambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class InterfacePredicate {
	// Interface Funcional Predicate funcão Predicate (instância condicional)
	// Através dessa interface Predicate podemos passar uma condição 
	// através de um parâmetro de função, ou seja, agora conseguimos
	// enviar uma expressão para dentro da API nativa do Java
	public static void filtro(List<String> lista, Predicate<String> condicao) {
		for (String s : lista)
			if (condicao.test(s))
				System.out.println(s);
	}
	public static void main(String[] args) {
		List<String> lista1 = Arrays.asList("Santa Catarina", "Paraná", "São Paulo", "Rio de Janeiro", "Brasília",
				"Ceará");

		System.out.println("Estados que iniciam com a letra S");
		filtro(lista1, (s) -> s.startsWith("S"));
		System.out.println("");
		System.out.println("Estados que terminam com a letra a");
		filtro(lista1, (s) -> s.endsWith("a"));
		System.out.println("");
		System.out.println("Imprime toda a lista");
		filtro(lista1, (s) -> true);
		System.out.println("");
		System.out.println("Não imprime a lista");
		filtro(lista1, (s) -> false);
		System.out.println("");
		System.out.println("Imprime os nomes com + de 10 caracteres");
		filtro(lista1, (s) -> s.length() > 10);
	}
}
