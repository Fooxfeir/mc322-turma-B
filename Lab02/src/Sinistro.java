

public class Sinistro {
	private static int gerador = 0;
	private int id;
	private String endereco;
	private String data;
	
	//Constructor
	public Sinistro(String endereco, String data) {
		this.endereco = endereco;
		this.data = data;
		gerador++;
		id = gerador;
	}
	
	//Getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
