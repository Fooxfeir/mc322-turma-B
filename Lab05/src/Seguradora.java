import java.util.ArrayList;
import java.util.Scanner;

public class Seguradora {
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;

	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
	}

	/*
	 * Tries to add the client to the clients array
	 * If the client is already in the array, returns false
	 * If the client was not present, returns true
	 */
	public boolean cadastrarCliente(ClientePF cliente) {
		Cliente atual;
		for (int i = 0; i < listaClientes.size(); i++) {
			atual = listaClientes.get(i);
			if (atual instanceof ClientePF) {
				if (((ClientePF) atual).compararCliente(cliente))
					return false;
			}
		}
		listaClientes.add(cliente);
		return true;
	}
	
	public boolean cadastrarCliente(ClientePJ cliente) {
		Cliente atual;
		for (int i = 0; i < listaClientes.size(); i++) {
			atual = listaClientes.get(i);
			if (atual instanceof ClientePJ) {
				if (((ClientePJ) atual).compararCliente(cliente))
					return false;
			}
		}
		listaClientes.add(cliente);
		return true;
	}
	
	/*
	 * Removes the specified client from the clients array
	 * Returns true if successful
	 * Returns false if the client is not found
	 */
	public boolean removerCliente(String identificador) {
		Cliente atual;
		for (int i = 0; i < listaClientes.size(); i ++) {
			atual = listaClientes.get(i);
			if (identificador.compareTo(atual.identificar()) == 0) {
				listaClientes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	public boolean gerarSeguro(ClientePF cliente, Veiculo veiculo){
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual a data de início do seguro?");
		String entrada = sc.next();
		Date dataInicio = new Date(entrada);
		System.out.println("Qual a data de fim do seguro?");
		entrada = sc.next();
		Date dataFinal = new Date(entrada);
		
		SeguroPF novoSeguro = new SeguroPF(dataInicio, dataFinal, this, veiculo, cliente);
		novoSeguro.calcularValor();
		
		listaSeguros.add(novoSeguro);
		return true;
	}
	
	@SuppressWarnings("resource")
	public boolean gerarSeguro(ClientePJ cliente, Frota frota) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual a data de início do seguro?");
		String entrada = sc.next();
		Date dataInicio = new Date(entrada);
		
		System.out.println("Qual a data de fim do seguro?");
		entrada = sc.next();
		Date dataFinal = new Date(entrada);
		
		SeguroPJ novoSeguro = new SeguroPJ(dataInicio, dataFinal, this, frota, cliente);
		novoSeguro.calcularValor();
		
		listaSeguros.add(novoSeguro);
		return true;
	}
	
	public boolean cancelarSeguro(int id) {
		for (Seguro i : listaSeguros) {
			if (i.getId() == id) {
				listaSeguros.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Displays all the clients registered 
	 */
	public void listarClientes() {
		for (Cliente i : listaClientes) {
			System.out.println(i.toString());
		}
	}	
	
	public void listarVeiculoPorCliente(String identificador){
		for (Cliente cliente : this.getListaClientes()) {
			if (cliente.identificar().compareTo(identificador) == 0) {
				if (cliente instanceof ClientePF) {
					ClientePF clientePF = (ClientePF) cliente;
					for (Veiculo veiculo : clientePF.getListaVeiculos()) {
						System.out.println(veiculo);
					}
				}
				if (cliente instanceof ClientePJ) {
					ClientePJ clientePJ = (ClientePJ) cliente;
					for (Frota frota : clientePJ.getListaFrota()) {
						for (Veiculo veiculo : frota.getListaVeiculos()) {
							System.out.println(veiculo);
						}
					}
				}
			}
		}
	}
	
	/*
	 * Retorna a lista de seguros associada a um cliente
	 */
	public ArrayList<Seguro> getSegurosPorCliente(String identificador){
		ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
		for (Seguro i : listaSeguros) {
			if (i.identificarSegurado().compareTo(identificador) == 0) {
				segurosCliente.add(i);
			}
		}
		return segurosCliente;
	}
	
	/*
	 * Retorna a lista de sinistros associada a um cliente
	 */
	public ArrayList<Sinistro> getSinistrosPorCliente(String identificador) {
		ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
		for (Seguro i : listaSeguros) {
			if (i.identificarSegurado().compareTo(identificador) == 0) {
				sinistrosCliente.addAll(i.getListaSinistros());
			}
		}
		return sinistrosCliente;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCondutor(String cpf) {
		ArrayList<Sinistro> sinistrosCondutor = new ArrayList<Sinistro>();
		for (Seguro seguro : listaSeguros) {
			for (Sinistro sinistro : seguro.getListaSinistros()) {
				if (sinistro.getCondutor().getCpf().compareTo(cpf) == 0) {
					sinistrosCondutor.add(sinistro);
				}
			}
		}
		return sinistrosCondutor;
	}
	
	
	/*
	 * Visualizes all the accidents in the system 
	 */
	public void listarSinistros() {
		for (Seguro seguro : listaSeguros) {
			for (Sinistro sinistro : seguro.getListaSinistros()) {
				System.out.println(sinistro);
			}
		}
	} 
	
	/*
	 * Returns the total insurance
	 */
	public double calcularReceita() {
		double receita = 0;
		for (Seguro i : listaSeguros) {
			receita += i.getValorMensal();
		}
		return receita;
	}
	
	//Getters and setters
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	

	
}
