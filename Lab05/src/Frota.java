import java.util.ArrayList;

public class Frota {
	private String code;
	private ArrayList<Veiculo> listaVeiculos;
	
	public Frota(String code) {
		this.code = code;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public boolean addVeiculo(Veiculo veiculo) {
		listaVeiculos.add(veiculo);
		return true;
	}
	/*
	 * Retorna verdadeiro se remover o veiculo especificado
	 * Retorna falso caso contrário
	 */
	public boolean removeVeiculo(String placa) {
		for (Veiculo veiculo : listaVeiculos) {
			if (veiculo.getPlaca().compareTo(placa) == 0) {
				listaVeiculos.remove(veiculo);
				return true;
			}
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	
}
