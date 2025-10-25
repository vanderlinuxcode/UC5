package util;

public class Validador {

    public static boolean nomeValido(String nome) {
        return nome.matches("^[A-Za-zÀ-ÿ ]{2,}$");
    }

    public static boolean emailValido(String email) {
        return email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$");
    }

//    public static boolean cpfValido(String cpf) {
//        if (!cpf.matches("^\\d{11}$")) return false;
//        if (cpf.chars().distinct().count() == 1) return false;
//
//        int soma1 = 0, soma2 = 0;
//        for (int i = 0; i < 9; i++) {
//            int digito = Character.getNumericValue(cpf.charAt(i));
//            soma1 += digito * (10 - i);
//            soma2 += digito * (11 - i);
//        }
//
//        int verificador1 = soma1 % 11 < 2 ? 0 : 11 - (soma1 % 11);
//        soma2 += verificador1 * 2;
//        int verificador2 = soma2 % 11 < 2 ? 0 : 11 - (soma2 % 11);
//
//        return verificador1 == Character.getNumericValue(cpf.charAt(9)) &&
//               verificador2 == Character.getNumericValue(cpf.charAt(10));
//    }
//
//    public static boolean senhaValida(String senha) {
//        return senha.length() >= 6 &&
//               senha.matches(".*[A-Za-z].*") &&
//               senha.matches(".*\\d.*");
//    }
}
