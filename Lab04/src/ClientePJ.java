
public class ClientePJ extends Cliente {
	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	
	public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public boolean compararCliente(ClientePJ comparado) {
		if (cnpj.compareTo(comparado.getCnpj()) != 0){
			return false;
		}
		return true;
	}
	
	public String identificar() {
		return cnpj;
	}
	
	public boolean validaCNPJ(String cnpj) {
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

	public double calculaScore() {
		double base = CalcSeguro.VALOR_BASE.getModificador();
		int carros = this.getListaVeiculos().size();
		double score = base * (1 + (qtdeFuncionarios)/100) * carros;
		return score;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	@Override
	public String toString() {
		return "ClientePJ [cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + ", qtdeFuncionarios=" + qtdeFuncionarios
				+ ", valor seguro" + super.getValorSeguro() + "]";
	}

}
