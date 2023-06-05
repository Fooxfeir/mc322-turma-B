import java.util.ArrayList;

public class ClientePJ extends Cliente {
	private final String cnpj;
	private Date dataFundacao;
	private ArrayList<Frota> listaFrota;
	private int qtdeFuncionarios;
	
	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao,
			int qtdeFuncionarios) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		listaFrota = new ArrayList<Frota>();
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

	public ArrayList<Frota> getListaFrota() {
		return listaFrota;
	}

	public void setListaFrota(ArrayList<Frota> listaFrota) {
		this.listaFrota = listaFrota;
	}

	@Override
	public String toString() {
		return "ClientePJ [cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + ", listaFrota=" + listaFrota
				+ ", qtdeFuncionarios=" + qtdeFuncionarios + "]";
	}

	

}
