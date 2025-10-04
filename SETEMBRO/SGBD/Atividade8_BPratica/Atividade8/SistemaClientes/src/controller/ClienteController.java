package controller;

import dao.ClienteDAO;
import model.Cliente;
import exception.ValidacaoException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClienteController {
    private final ClienteDAO dao;

    public ClienteController() {
        this.dao = new ClienteDAO();
    }
    
    // Validação dos dados informado pelo usuário
    public void cadastrarCliente(int id, String nome, String idadeTexto, String email, String telefone, String cpf) throws ValidacaoException, Exception {
        Map<String, String> erros = new LinkedHashMap<>();
        int idade = -1;

        // Validação do nome
        if (nome == null || nome.isBlank()) {
            erros.put("nome", "Nome é obrigatório.");
        }

        // Validação da idade
        if (idadeTexto == null || idadeTexto.isBlank()) {
            erros.put("idade", "Idade é obrigatória.");
        } else {
            try {
                idade = Integer.parseInt(idadeTexto);
                if (idade < 0 || idade > 100) {
                    erros.put("idade", "Idade deve estar entre 0 e 100.");
                }
            } catch (NumberFormatException ex) {
                erros.put("idade", "Idade deve ser numérico.");
            }
        }

        // Validação do email
        if (email == null || email.isBlank()) {
            erros.put("email", "Email é obrigatório.");
        } else if (!email.contains("@") || !email.contains(".")) {
            erros.put("email", "Email inválido. Deve conter '@' e '.'");
        }else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            erros.put("email", "Email inválido.");
        }

        // Validação do telefone
        if (telefone == null || telefone.isBlank()) {
            erros.put("telefone", "Telefone é obrigatório.");
        } else if (!telefone.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")) {
            erros.put("telefone", "Telefone inválido. Use o formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.");
        }

        // Validação do CPF
        if (cpf == null || cpf.isBlank()) {
            erros.put("cpf", "CPF é obrigatório.");
        } else if (!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            erros.put("cpf", "CPF inválido. Use o formato XXX.XXX.XXX-XX.");
        }

        // Se houver erros, lança exceção
        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        // Se tudo estiver válido, cria e insere o cliente
        Cliente cliente = new Cliente(id, nome, idade, email, telefone, cpf);
        dao.inserir(cliente);
    }

    public void salvarCliente(Cliente cliente) throws ValidacaoException {
        cliente.setCpf(formatarCpf(cliente.getCpf()));
        validarCliente(cliente);
        if (!dao.testarConexao()) {
            throw new ValidacaoException(Map.of("conexao", "Não há conexão com o banco de dados."));
        }

        dao.inserir(cliente);
    }
          
    public boolean atualizarCliente(Cliente cliente) throws ValidacaoException {
        cliente.setCpf(formatarCpf(cliente.getCpf()));
        validarCliente(cliente); // ← 
        return dao.atualizar(cliente);
    }

    public void excluirCliente(int id) {
        dao.excluir(id);
    }
    
    public List<Cliente> listarClientes() {
        return dao.listar();
    }

    public void validarCliente(Cliente cliente) {
        Map<String, String> erros = new HashMap<>();

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            erros.put("nome", "Nome é obrigatório.");
        }

        if (cliente.getEmail() == null || !cliente.getEmail().contains("@") || !cliente.getEmail().contains(".")) {
            erros.put("email", "Email deve possuir @.");
        }

        if (cliente.getTelefone() == null || !cliente.getTelefone().matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
            erros.put("telefone", "Telefone inválido. Use o formato (XX) XXXXX-XXXX.");
        }

        if (cliente.getCpf() == null || !cliente.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            erros.put("cpf", "CPF inválido. Use o formato XXX.XXX.XXX-XX.");
        }

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }
    }


    private String formatarCpf(String cpf) {
        if (cpf == null) return "";
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }
    
}

