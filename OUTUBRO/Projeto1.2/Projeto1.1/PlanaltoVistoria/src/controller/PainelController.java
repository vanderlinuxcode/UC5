package controller;

import model.FuncionarioModel;

public class PainelController {

    public void redirecionarCliente() {
        System.out.println("🔗 Redirecionando para Agendamento de Vistoria...");
    }

    public void redirecionarFuncionario(FuncionarioModel funcionario) {
        if (funcionario == null) {
            System.out.println("⚠️ Funcionário não encontrado.");
            return;
        }

        switch (funcionario.getCargo()) {
            case "VISTORIADOR":
                System.out.println("🔧 Painel do Vistoriador");
                break;
            case "CONTABIL":
                System.out.println("📊 Painel do Contábil");
                break;
            case "GERENTE":
                System.out.println("🧭 Painel do Gerente");
                break;
            default:
                System.out.println("⚠️ Cargo não reconhecido.");
        }
    }
}
