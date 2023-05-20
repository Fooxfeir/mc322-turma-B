
public class ClientePF extends Cliente {
	private final String cpf;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;
	
	public ClientePF(String nome, String endereco, String cpf, String genero, Date dataLicenca, String educacao,
			Date dataNascimento, String classeEconomica) {
		super(nome, endereco);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
	}
	
	public boolean compararCliente(ClientePF comparado) {
		if (cpf.compareTo(comparado.getCpf()) != 0) {
			return false;
		}
		return true;
	}

	public double calculaScore() {
		double modificador = 0;
		double base = CalcSeguro.VALOR_BASE.getModificador();
		int idade = dataNascimento.getAno() - dataLicenca.getAno();
		if (idade >= 18 && idade < 30) {
			modificador = CalcSeguro.FATOR_18_30.getModificador();
		}
		else if (idade >= 30 && idade < 60) {
			modificador = CalcSeguro.FATOR_30_60.getModificador();
		}
		else if (idade >= 60 && idade < 90) {
			modificador = CalcSeguro.FATOR_60_90.getModificador();
		}
		int carros = this.getListaVeiculos().size();
		double score = base * modificador * carros;
		return score;
	}
	
	public String identificar() {
		return cpf;
	}
	//Getters e setters


	public String getGenero() {
		return genero;
	}
	


	public void setGenero(String genero) {
		this.genero = genero;
	}
	


	public Date getDataLicenca() {
		return dataLicenca;
	}
	


	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}
	


	public String getEducacao() {
		return educacao;
	}
	


	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	


	public Date getDataNascimento() {
		return dataNascimento;
	}
	


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	


	public String getClasseEconomica() {
		return classeEconomica;
	}
	


	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	


	public String getCpf() {
		return cpf;
	}
	


	@Override
	public String toString() {
		return "ClientePF [cpf=" + cpf + ", genero=" + genero + ", dataLicenca=" + dataLicenca + ", educacao="
				+ educacao + ", dataNascimento=" + dataNascimento + ", classeEconomica=" + classeEconomica
				+ ", valor seguro:" + super.getValorSeguro() + "]";
	}
}
