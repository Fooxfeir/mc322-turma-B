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
	
	public boolean cadastrarFrota(Frota frota) {
		listaFrota.add(frota);
		return true;
	}
	
	/*
	 * Checa a lista de veiculos da frota especificada
	 * Caso o veículo especificado esteja presente, remove da lista
	 * Caso contrário, adiciona na lista
	 * Se a lista esvaziar, remove a frota
	 * 
	 * Retorna false se a frota especificada não existir
	 * Retorna true se modificar a frota especificada
	 */	
	public boolean atualizarFrota(Veiculo veiculo, String code) {
		for (Frota frota : listaFrota) {
			if (frota.getCode().compareTo(code) == 0) {
				for (Veiculo i : frota.getListaVeiculos()) {
					if (veiculo.compararVeiculos(i) == 0) {
						frota.getListaVeiculos().remove(i);
						if (frota.getListaVeiculos().size() == 0) {
							listaFrota.remove(frota);
						}
						return true;
					}
				}
				frota.getListaVeiculos().add(veiculo);
				return true;
			}	
		}
		return false;
	}
	
	/*
	 * Busca na lista de frotas a com código especificado
	 * Retorna a lista de veículos
	 * Retorna null se não encontrar a frota
	 */
	
	public ArrayList<Veiculo> getVeiculosPorFrota(String code){
		ArrayList<Veiculo> listaRetornada = null;
		for (Frota frota : listaFrota) {
			if (frota.getCode().compareTo(code) == 0) {
				listaRetornada = frota.getListaVeiculos();
				break;
			}
		}
		return listaRetornada;
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
