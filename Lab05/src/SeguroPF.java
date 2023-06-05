
public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;

	public SeguroPF(int id, Date dataInicio, Date dataFim, Seguradora seguradora,
					int valorMensal, Veiculo veiculo, ClientePF cliente) {
		super(id, dataInicio, dataFim, seguradora, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	@Override
	public double calcularValor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean gerarSinistro() {
		// TODO Auto-generated method stub
		return false;
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
	
}
