import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;

	//Constructor
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		listaVeiculos = new ArrayList<Veiculo>();
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

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos
				+ "]";
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
}
