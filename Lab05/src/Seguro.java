import java.util.ArrayList;
import java.util.Scanner;

public abstract class Seguro {
	private final int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	protected ArrayList<Sinistro> listaSinistros;
	protected ArrayList<Condutor> listaCondutores;
	private int valorMensal;	
	
	public Seguro(int id, Date dataInicio, Date dataFim, Seguradora seguradora, int valorMensal) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();
		this.valorMensal = valorMensal;
	}
	
	//TODO: implementar removerSinistro e listarSinistro

	//Tenta adicionar o condutor recebido na lista de condutores
	//Retorna false se ele já estiver autorizado
	//Retorna true caso contrário
	public boolean autorizarCondutor(Condutor condutor) {
		String cpf = condutor.getCpf();
		for (Condutor i : listaCondutores) {
			if (i.getCpf().compareTo(cpf) == 0) {
				System.out.println("Condutor já possui autorização!");
				return false;
			}
		}
		listaCondutores.add(condutor);
		System.out.println("Condutor autorizado!");
		return true;
	}
	
	//Tenta remover o condutor de CPF especificado da lista de condutores
	//Retorna false se não encontrar
	//Retorna true se encontrar e remover com sucesso
	public boolean desautorizarCondutor(String cpf) {
		for (Condutor i : listaCondutores) {
			if (i.getCpf().compareTo(cpf) == 0) {
				listaCondutores.remove(i);
				System.out.println("Condutor desautorizado!");
				return true;
			}
		}
		System.out.println("Condutor não possui autorização!");
		return false;
	}
	
	public abstract double calcularValor();
	
	public abstract String identificarSegurado();
	
	public boolean gerarSinistro() {
		System.out.println("Informe a data do sinistro ");
		Scanner sc = new Scanner(System.in);
		String data = sc.next();
		System.out.println("Informe o endereço do sinistro ");
		String endereco = sc.next();
		System.out.println("Qual o CPF condutor envolvido no sinistro? ");
		String cpf = sc.next();
		Condutor condutorEnvolvido = null;
		for (Condutor i : listaCondutores) {
			if (cpf.compareTo(i.getCpf()) == 0) {
				condutorEnvolvido = i;
				break;
			}
		}
		if (condutorEnvolvido == null) {
			System.out.println("Esse condutor não foi autorizado! ");
			return false;
		}
		Sinistro novoSinistro = new Sinistro(endereco, data, this, condutorEnvolvido);
		listaSinistros.add(novoSinistro);
		condutorEnvolvido.adicionarSinistro(novoSinistro);
		return true;
	}
	
	/*
	 * Removes the specified accident from the list
	 * Updates the client's insurance
	 */
	public boolean removerSinistro(int id) {
		for (Sinistro i : listaSinistros) {
			if (i.getId() == id) {
				listaSinistros.remove(i);
				//TODO: atualizar preço do seguro
				return true;
			}
		}
		return false;
	}
	
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Seguradora getSeguradora() {
		return seguradora;
	}
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	public int getValorMensal() {
		return valorMensal;
	}
	public void setValorMensal(int valorMensal) {
		this.valorMensal = valorMensal;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}
	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}
	
	
}
