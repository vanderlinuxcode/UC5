package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import dao.AgendamentoDAO;
import exception.ValidacaoException;
import model.Funcionario;
import model.Agendamento;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
	private AgendamentoDAO agendamentoDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
        this.agendamentoDAO = new AgendamentoDAO();
    }

    // CREATE — Cadastrar novo funcionário
    public int cadastrarFuncionario(Funcionario funcionario) throws ValidacaoException {
        if (funcionario == null) {
            throw new ValidacaoException(Map.of("funcionario", "Funcionário não pode ser nulo."));
        }

        Map<String, String> erros = validarDados(
            funcionario.getNome(),
            funcionario.getCpf(),
            funcionario.getCargo(),
            funcionario.getTelefone()
        );

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        funcionario.setCpf(formatarCpf(funcionario.getCpf()));
        funcionario.setNome(funcionario.getNome().trim());
        funcionario.setCargo(funcionario.getCargo().trim());
        funcionario.setTelefone(funcionario.getTelefone().trim());

        return funcionarioDAO.inserir(funcionario);
    }

    // READ — Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() throws ValidacaoException, Exception {
        return funcionarioDAO.listar();
    }

    // READ — Buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(int idFuncionario) throws Exception {
        if (idFuncionario <= 0) {
            throw new ValidacaoException(Map.of("idFuncionario", "ID inválido."));
        }

        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionario == null) {
            throw new ValidacaoException(Map.of("funcionario", "Funcionário não encontrado."));
        }

        return funcionario;
    }

    // UPDATE — Atualizar funcionário existente
    public boolean atualizarFuncionario(Funcionario funcionario) throws ValidacaoException {
        Map<String, String> erros = new LinkedHashMap<>();

        if (funcionario == null) {
            erros.put("funcionario", "Funcionário não pode ser nulo.");
            throw new ValidacaoException(erros);
        }

        if (funcionario.getIdFuncionario() <= 0) {
            erros.put("idFuncionario", "ID do funcionário inválido.");
        }

        erros.putAll(validarDados(funcionario.getNome(), funcionario.getCpf(), funcionario.getCargo(), funcionario.getTelefone()));

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        funcionario.setCpf(formatarCpf(funcionario.getCpf()));
        funcionario.setNome(funcionario.getNome().trim());
        funcionario.setCargo(funcionario.getCargo().trim());
        funcionario.setTelefone(funcionario.getTelefone().trim());

        boolean atualizado = funcionarioDAO.atualizar(funcionario);
        if (!atualizado) {
            throw new ValidacaoException(Map.of("atualizacao", "Nenhum registro foi atualizado. Verifique se o ID do funcionário existe."));
        }

        return true;
    }

    // DELETE — Excluir funcionário pelo ID
    public void excluirFuncionario(int idFuncionario) throws ValidacaoException {
        if (idFuncionario <= 0) {
            throw new ValidacaoException(Map.of("idFuncionario", "ID do funcionário inválido."));
        }

        funcionarioDAO.excluir(idFuncionario);
    }

    // VISTORIA — Registrar vistoria em agendamento
    public boolean registrarVistoria(int idAgendamento, String relatorio) throws ValidacaoException {
        Agendamento agendamento = agendamentoDAO.buscarPorId(idAgendamento);
        if (agendamento == null) {
            throw new ValidacaoException(Map.of("agendamento", "Agendamento não encontrado."));
        }

        agendamento.setRelatorioVistoria(relatorio);
        agendamento.setVistoriaRealizada(true);

        return agendamentoDAO.atualizar(agendamento);
    }

    // PAGAMENTO — Registrar pagamento em agendamento
    public boolean registrarPagamento(int idAgendamento, double valor) throws ValidacaoException {
        Agendamento agendamento = agendamentoDAO.buscarPorId(idAgendamento);
        if (agendamento == null) {
            throw new ValidacaoException(Map.of("agendamento", "Agendamento não encontrado."));
        }

        agendamento.setValorPago(valor);
        agendamento.setPagamentoRealizado(true);

        return agendamentoDAO.atualizar(agendamento);
    }

    // AGENDAMENTOS — Listar todos os agendamentos
    public List<Agendamento> listarAgendamentos() throws ValidacaoException {
        return agendamentoDAO.listarTodos();
    }

    // Validação centralizada dos dados
    private Map<String, String> validarDados(String nome, String cpf, String cargo, String telefone) {
        Map<String, String> erros = new LinkedHashMap<>();

        if (nome == null || nome.isBlank()) {
            erros.put("nome", "Nome é obrigatório.");
        }

        if (cpf == null || cpf.isBlank()) {
            erros.put("cpf", "CPF é obrigatório.");
        } else {
            cpf = cpf.replaceAll("\\D", "");
            if (cpf.length() != 11) {
                erros.put("cpf", "CPF inválido. Deve conter 11 dígitos.");
            }
        }

        if (cargo == null || cargo.isBlank()) {
            erros.put("cargo", "Cargo é obrigatório.");
        }

        if (telefone == null || telefone.isBlank()) {
            erros.put("telefone", "Telefone é obrigatório.");
        } else {
            telefone = telefone.replaceAll("\\D", "");
            if (telefone.length() < 10 || telefone.length() > 11) {
                erros.put("telefone", "Telefone inválido. Deve conter 10 ou 11 dígitos.");
            }
        }

        return erros;
    }

    // Formatação de CPF para consistência
    private String formatarCpf(String cpf) {
        if (cpf == null) return "";
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpf;
    }

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
}
