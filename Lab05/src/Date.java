
public class Date {
	private int dia;
	private int mes;
	private int ano;
	
	public Date(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public Date(String entrada) {
		String[] data = entrada.split("/");
		this.dia = Integer.parseInt(data[0]);
		this.mes = Integer.parseInt(data[1]);
		this.ano = Integer.parseInt(data[2]);
	}

	public boolean compareTo(Date comparado) {
		if (this.dia != comparado.getDia()) {
			return false;
		}
		else if (this.mes != comparado.getMes()) {
			return false;
		}
		else if (this.ano != comparado.getAno()) {
			return false;
		}
		return true;
	}
	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	

	@Override
	public String toString() {
		return "Date [dia=" + dia + ", mes=" + mes + ", ano=" + ano + "]";
	}
}
