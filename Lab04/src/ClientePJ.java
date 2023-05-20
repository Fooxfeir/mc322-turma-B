
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
