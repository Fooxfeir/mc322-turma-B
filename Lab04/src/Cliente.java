import java.util.ArrayList;

public abstract class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;
	private double valorSeguro;

	//Constructor
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		listaVeiculos = new ArrayList<Veiculo>();
		this.valorSeguro = 0;
	} 
	
	public void adiocionarVeiculo (Veiculo novo) {
		listaVeiculos.add(novo);
	}
	
	public void removerVeiculo (Veiculo velho) {
		for (int i = 0; i < listaVeiculos.size(); i ++) {
			if (velho.compararVeiculos(listaVeiculos.get(i)) == 0) {
				listaVeiculos.remove(i);
			}
		}
	}
	
	public abstract double calculaScore();

	public abstract String identificar(); 
		
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos
				+ ", valorSeguro=" + valorSeguro + "]";
	}

	//Getters e setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Veiculo> getListaVeiculos(){
		return listaVeiculos;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	
}
