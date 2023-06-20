
public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;

	public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, 
					Frota frota, ClientePJ cliente) {
		super(dataInicio, dataFim, seguradora);
		this.frota = frota;
		this.cliente = cliente;
	}

	@Override
	public double calcularValor() {
		double valor = CalcSeguro.VALOR_BASE.getModificador();
		valor *= 10 + (cliente.getQtdeFuncionarios())/10;
		valor *= 1 + 1/(this.getFrota().getListaVeiculos().size());
		int anosPosFundacao = this.getDataInicio().getAno() - cliente.getDataFundacao().getAno();
		valor *= 1 + (1/anosPosFundacao + 2);
		valor *= 2 + this.getSeguradora().getSinistrosPorCliente(cliente.identificar()).size()/10;
		int qtdeSinistrosCondutor = 0;
		for (Condutor condutor : this.getListaCondutores()) {
			qtdeSinistrosCondutor += condutor.getListaSinistros().size();
		}
		valor *= 5 + qtdeSinistrosCondutor/10;
		return valor;
	}
	
	@Override
	public String identificarSegurado() {
		return cliente.getCnpj();
	}

	public ClientePJ getCliente() {
		return cliente;
	}

	public void setCliente(ClientePJ cliente) {
		this.cliente = cliente;
	}

	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}
	

}
