package lanchonete;

public enum TipoHamburguer {
	// criação de enumeração "enum" 
		VEGETARIANO(200),
		VEGANO(200),
		TRADICIONAL(200);
		
		// Determina que não poderá ser alterado depois de definido.
		private final int escolha;
		
		// construtor
		TipoHamburguer(int escolha){
			this.escolha = escolha;
		}
		
		public int getescolha() {
			return escolha;
		}
		
		@Override
		public String toString() {
		// define que a palavra "pequeno" tenha a letra inicial pequena.
			return name().charAt(0) + name().substring(1).toLowerCase(); 
		}
}