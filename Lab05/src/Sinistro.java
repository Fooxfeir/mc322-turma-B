

public class Sinistro {
	private static int gerador = 0;
	private final int id;
	private String endereco;
	private String data;
	private Seguro seguro;
	private Condutor condutor;
	
	public Sinistro(String endereco, String data, Seguro seguro, Condutor condutor) {
		gerador++;
		this.id = gerador;
		this.endereco = endereco;
		this.data = data;
		this.seguro = seguro;
		this.condutor = condutor;
	}
	
	//Getters e setters
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public static void setGerador(int gerador) {
		Sinistro.gerador = gerador;
	}

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

	@Override
	public String toString() {
		return "Sinistro [id=" + id + ", endereco=" + endereco + ", data=" + data + ", seguro=" + seguro + ", condutor="
				+ condutor + "]";
	}
	
	
}
