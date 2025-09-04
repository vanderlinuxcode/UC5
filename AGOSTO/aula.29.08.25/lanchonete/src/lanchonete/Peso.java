package lanchonete;
// criação de enumeração "enum" 
public enum Peso {
	PEQUENO(100),
	MÉDIO(200),
	GRANDE(300);
	
	// Determina que não poderá ser alterado depois de definido.
	private final int pesoGr;
	
	Peso(int pesoGr){
		this.pesoGr = pesoGr;
	}
	
	public int getpesoGr() {
		return pesoGr;
	}
	
	@Override
	public String toString() {
	// define que a palavra "pequeno" tenha a letra inicial pequena.
		return name().charAt(0) + name().substring(1).toLowerCase(); 
	}
	
	
}
