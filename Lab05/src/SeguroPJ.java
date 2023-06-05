
public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;

	public SeguroPJ(int id, Date dataInicio, Date dataFim, Seguradora seguradora, int valorMensal, 
					Frota frota, ClientePJ cliente) {
		super(id, dataInicio, dataFim, seguradora, valorMensal);
		this.frota = frota;
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
