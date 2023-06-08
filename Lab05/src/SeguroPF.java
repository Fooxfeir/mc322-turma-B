
public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;

	public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora,
					int valorMensal, Veiculo veiculo, ClientePF cliente) {
		super(dataInicio, dataFim, seguradora, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	@Override
	public double calcularValor() {
		double modificador = 0;
		double base = CalcSeguro.VALOR_BASE.getModificador();
		int anoCliente = cliente.getDataNascimento().getAno();
		int anoSeguro = this.getDataInicio().getAno();
		int idade = anoSeguro - anoCliente;
		if (idade >= 18 && idade < 30) {
			modificador = CalcSeguro.FATOR_18_30.getModificador();
		}
		else if (idade >= 30 && idade < 60) {
			modificador = CalcSeguro.FATOR_30_60.getModificador();
		}
		else if (idade >= 60 && idade < 90) {
			modificador = CalcSeguro.FATOR_60_90.getModificador();
		}
		int carros = 0;
		for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
			carros++;
		}
		int sinistrosCliente = this.getSeguradora().getSinistrosPorCliente(cliente.identificar()).size();
		int sinistrosCondutor = 0;
		for (Condutor condutor : listaCondutores) {
			sinistrosCondutor += this.getSeguradora().getSinistrosPorCondutor(condutor.getCpf()).size();
		}
		
		double valor = base * modificador * (1 + 1/(carros + 2));
		valor *= (2 + sinistrosCliente/10);
		valor *= (5 + sinistrosCondutor/10);
		return valor;
	}
	
	@Override
	public String identificarSegurado() {
		return cliente.getCpf();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ClientePF getCliente() {
		return cliente;
	}

	public void setCliente(ClientePF cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "SeguroPF [veiculo=" + veiculo + ", cliente=" + cliente + "]";
	}
	
}
