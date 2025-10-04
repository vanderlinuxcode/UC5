package exception;

import java.util.Map;

public class ValidacaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final Map<String, String> erros;

    public ValidacaoException(Map<String, String> erros) {
        super("Erro de validação nos campos.");
        this.erros = erros;
    }

    public Map<String, String> getErros() {
        return erros;
    }

}
