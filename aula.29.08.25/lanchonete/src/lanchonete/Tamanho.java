package lanchonete;
// criação de enumeração "enum" 
public enum Tamanho {
	PEQUENO(300),
	MÉDIO(500),
	GRANDE(700);
	
	// Determina que não poderá ser alterado depois de definido.
	private final int volumeMl;
	
	Tamanho(int volumeMl){
		this.volumeMl = volumeMl;
	}
	
	public int getvolumeMl() {
		return volumeMl;
	}
	
	@Override
	public String toString() {
	// define que a palavra "pequeno" tenha a letra inicial pequena.
		return name().charAt(0) + name().substring(1).toLowerCase(); 
	}
}
