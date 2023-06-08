

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	

//Constructor
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}
	
	
	//compara os valores de dois veículos diferentes, retornando 0
	//se ambos tiverem os mesmos valores
	public int compararVeiculos(Veiculo comparado) {
		if (placa.compareTo(comparado.getPlaca()) != 0) {
			return 1;
		}
		else if (marca.compareTo(comparado.getMarca()) != 0) {
			return 1;
		}
		else if (modelo.compareTo(comparado.getModelo()) != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + "]";
	}

	//Getters e setters
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
}
