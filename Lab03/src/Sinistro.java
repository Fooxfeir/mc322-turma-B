

public class Sinistro {
	private static int gerador = 0;
	private final int id;
	private String endereco;
	private String data;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	//Constructor
	public Sinistro(String endereco, String data, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		this.endereco = endereco;
		this.data = data;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
		gerador++;
		id = gerador;
	}
	
	//Getters e setters
	public int getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

	public static int getGerador() {
		return gerador;
	}
	

	public Seguradora getSeguradora() {
		return seguradora;
	}
	

	public Veiculo getVeiculo() {
		return veiculo;
	}
	

	public Cliente getCliente() {
		return cliente;
	}
}
