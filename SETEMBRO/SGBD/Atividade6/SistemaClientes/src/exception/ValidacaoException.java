package exception;

//RuntimeException maior robustez no código, verifica antes da compilação do código, é uma classe java dentro da biblioteca padrão.
public class ValidacaoException extends RuntimeException{
    private static final long serialVersionUID = 1L; //garante que a versão da classe usada para salvar o objeto seja compatível com a versão usada para carregá-lo.
	public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
