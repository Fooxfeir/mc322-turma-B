import java.util.ArrayList;

public class ClientePF extends Cliente {
	private final String cpf;
	private String genero;
	private String educacao;
	private Date dataNascimento;
	private ArrayList<Veiculo> listaVeiculos;

	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero,
			String educacao, Date dataNascimento) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	public boolean compararCliente(ClientePF comparado) {
		if (cpf.compareTo(comparado.getCpf()) != 0) {
			return false;
		}
		return true;
	}
	
	public void cadastrarVeiculo (Veiculo novo) {
		listaVeiculos.add(novo);
	}
	
	public void removerVeiculo (String placa) {
		for (Veiculo veiculo : listaVeiculos) {
			if (placa.compareTo(veiculo.getPlaca()) == 0) {
				listaVeiculos.remove(veiculo);
			}
		}
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

	public String getCpf() {
		return cpf;
	}
	
	public ArrayList<Veiculo> getListaVeiculos(){
		return listaVeiculos;
	}

	@Override
	public String toString() {
		return "ClientePF [cpf=" + cpf + ", genero=" + genero + ", educacao=" + educacao + ", dataNascimento="
				+ dataNascimento + ", listaVeiculos=" + listaVeiculos + "]";
	}
	
	
}
