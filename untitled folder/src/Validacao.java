
public class Validacao {
	
	public static boolean validaCPF(String cpf) {
		//verifies the size of the string
		cpf = cpf.replaceAll("[^\\d]", "");
		if (cpf.length() != 11){
			return false;
		}
		
		//verifies if the string is made of the same numbers
		int igual = 1;
		for (int i = 0; i < 11; i++) {
			if (cpf.charAt(i) != cpf.charAt(0)) {
				igual = 0;
				break;
			}
		}
		if (igual == 1)
			return false;
		
		//verifying numbers
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			int digito = Integer.parseInt("" + cpf.charAt(i));
			soma += (i + 1)*digito;
		}
		int resto = soma % 11;
		if (resto == 10)
			resto = 0;
		int verificador = Integer.parseInt("" + cpf.charAt(9));
		if (resto != verificador) {
			return false;
		}
		
		soma = 0;
		for (int i = 0; i < 10; i++) {
			int digito = Integer.parseInt("" + cpf.charAt(i));
			soma += i*digito;
		}
		resto = soma % 11;
		if (resto == 10)
			resto = 0;
		verificador = Integer.parseInt("" + cpf.charAt(10));
		if (resto != verificador) {
			return false;
		}
		
		return true;
	}
	
	public static boolean validaCNPJ(String cnpj) {
		//verifies the size of the string
		cnpj = cnpj.replaceAll("[^\\d]", "");
		if (cnpj.length() != 14){
			return false;
		}
		
		//verifies if the string is made of the same numbers
		int igual = 1;
		for (int i = 0; i < 12; i++) {
			if (cnpj.charAt(i) != cnpj.charAt(0)) {
				igual = 0;
				break;
			}
		}
		if (igual == 1)
			return false;
		
		//verifying numbers
		int soma = 0;
		int algarismo[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int algarismo1[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		for (int i = 0; i < 12; i++) {
			int digito = Integer.parseInt("" + cnpj.charAt(i));
			soma += algarismo[i]*digito;
		}
		int resto = soma % 11;
		if (resto < 2)
			resto = 0;
		else {
			resto = 11 - resto;
		}
		int verificador = Integer.parseInt("" + cnpj.charAt(12));
		if (resto != verificador) {
			return false;
		}
		
		soma = 0;
		for (int i = 0; i < 13; i++) {
			int digito = Integer.parseInt("" + cnpj.charAt(i));
			soma += algarismo1[i]*digito;
		}
		resto = soma % 11;
		if (resto < 2)
			resto = 0;
		else
			resto = 11 - resto;
		verificador = Integer.parseInt("" + cnpj.charAt(13));
		if (resto != verificador) {
			return false;
		}
		
		return true;
	}
	
	public static boolean validaNome(String nome) {
		nome = nome.strip();
		for (int i = 0; i < nome.length(); i++) {
			if (nome.charAt(i) >= '0' && nome.charAt(i) <= '9') {
				return false;
			}
		}
		return true;
	}
}
