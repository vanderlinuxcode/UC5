package exception;

import java.util.Map;

public class ValidacaoException extends Exception {
    private static final long serialVersionUID = 1L;
	private Map<String, String> erros;

    // 🔹 Construtor com Map (já existente)
    public ValidacaoException(Map<String, String> erros) {
        super("Erro de validação");
        this.erros = erros;
    }

    // 🔹 Construtor com mensagem simples
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
