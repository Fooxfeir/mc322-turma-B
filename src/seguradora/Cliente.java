package seguradora;

public class Cliente {
	private String nome;
	private String cpf;
	private int idade;
	private String endereco;
	private String dataNascimento;

	//Constructor
	public Cliente(String nome, String cpf, int idade, String endereco, String dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.endereco = endereco;
		this.setDataNascimento(dataNascimento);
	}
	
	public boolean validarCPF(String cpf) {
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
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + "]";
	}

	//Getters e setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
