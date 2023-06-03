import java.util.ArrayList;
import java.util.Scanner;

public class Seguradora {
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	
	public Seguradora(String nome, String endereco, String telefone, String email) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		listaSinistros = new ArrayList<Sinistro>();
		listaClientes = new ArrayList<Cliente>();
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
	
	public boolean removerVeiculo(String identificador, String placa) {
		for (Cliente cliente : listaClientes) {
			if (cliente.identificar().compareTo(identificador) == 0) {
				int aux = 0;
				for (Veiculo veiculo : cliente.getListaVeiculos()) {
					if (veiculo.getPlaca().compareTo(placa) == 0) {
						cliente.getListaVeiculos().remove(aux);
						cliente.calculaScore();
						return true;
					}
					aux++;
				}
			}
		}
		return false;
	}
	
	/*
	 * Adds a new vehicle to a client's list
	 * Updates the client's insurance
	 */
	public void cadastrarVeiculo (String identificador, Veiculo novo) {
		for (Cliente i : listaClientes) {
			if (i.identificar().compareTo(identificador) == 0) {
				i.adiocionarVeiculo(novo);
				calcularPrecoSeguroCliente(i.identificar());
			}
		}
	}
	
	/*
	 * Displays all the vehicles in the system
	 */
	public void listarVeiculos() {
		for (Cliente cliente : listaClientes) {
			for (Veiculo veiculo : cliente.getListaVeiculos()) {
				System.out.println(veiculo);
			}
		}
	}
	
	/*
	 * Displays all the vehicles associated to the given client
	 */
	public void listarVeiculos(String identificador) {
		for (Cliente cliente : listaClientes) {
			if (cliente.identificar().compareTo(identificador) == 0) {
				for (Veiculo veiculo : cliente.getListaVeiculos()) {
					System.out.println(veiculo);
				}
			}
		}
	}
	
	/*
	 * Adds a new accident to the list of a client
	 * Updates the client's insurance
	 */
	@SuppressWarnings("resource")
	public boolean gerarSinistro() {
		System.out.println("Informe a data do sinistro ");
		Scanner sc = new Scanner(System.in);
		String data = sc.next();
		System.out.println("Informe o endereço do sinistro ");
		String endereco = sc.next();
		System.out.println("Qual o CPF/CNPJ do cliente do sinistro? ");
		String identificador = sc.next();
		Cliente clienteAtual = null;
		for (int i = 0; i < listaClientes.size(); i++) {
			clienteAtual = listaClientes.get(i);
			if (identificador.compareTo(clienteAtual.identificar()) == 0) {
				break;
			}
		}
		if (clienteAtual == null) {
			System.out.println("Cliente inexistente! ");
			return false;
		}
		System.out.println("Qual a placa do veículo do sinistro? ");
		String placaVeiculo = sc.next();
		ArrayList<Veiculo> veiculosCliente = clienteAtual.getListaVeiculos();
		Veiculo veiculoAtual = new Veiculo(null, null, null, 0);
		for (int i = 0; i < veiculosCliente.size(); i++) {
			veiculoAtual = veiculosCliente.get(i);
			if (placaVeiculo.compareTo(veiculoAtual.getPlaca()) == 0) {
				break;
			}
		}
		if (veiculoAtual.getPlaca() == null) {
			System.out.println("Veículo inexistente! ");
			return false;
		}
		Sinistro novoSinistro = new Sinistro(endereco, data, this, veiculoAtual, clienteAtual);
		listaSinistros.add(novoSinistro);
		calcularPrecoSeguroCliente(clienteAtual.identificar());
		return true;
	}
	
	/*
	 * Removes the specified accident from the list
	 * Updates the client's insurance
	 */
	public boolean removerSinistro(String identificador, String data) {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i ++) {
			atual = listaSinistros.get(i);
			if (atual.getCliente().identificar().compareTo(identificador) == 0) {
				if (atual.getData().compareTo(data) == 0) {
					listaSinistros.remove(i);
					calcularPrecoSeguroCliente(identificador);
				}
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
	
	
	/*
	 * Visualizes all the accidents of the specified client
	 */
	public boolean visualizarSinistro(String identificador) {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i++) {
			atual = listaSinistros.get(i);
			if (identificador.compareTo(atual.getCliente().identificar()) == 0) { 
				System.out.print(atual.toString());
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Visualizes all the accidents in the system 
	 */
	public void listarSinistros() {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i++) {
			atual = listaSinistros.get(i);
			System.out.print(atual.toString());

		}
	} 
	
	/*
	 * Updates the insurance of the specified client
	 */
	public void calcularPrecoSeguroCliente(String id) {
		Cliente cliente = null;
		double preco;
		for (Cliente i : listaClientes) {
			if (id.compareTo(i.identificar()) == 0) {
				cliente = i;
				break;
			}
		}
		int sinistros = 0;
		for (Sinistro i : listaSinistros) {
			if (cliente.identificar().compareTo(i.getCliente().identificar()) == 0) {
				sinistros++;
			}
		}
		preco = cliente.calculaScore() * (1 + sinistros);
		cliente.setValorSeguro(preco);		
	}
	
	/*
	 * Transfer all the vehicles from the first client to the second
	 * Updates the insurance of the two clients
	 */
	public void transferirSeguro(String idOrigem, String idDestino) {
		Cliente clienteOrigem = null;
		Cliente clienteDestino = null;
		for (Cliente clienteAtual : listaClientes) {
			if (clienteAtual.identificar().compareTo(idOrigem) == 0) {
				clienteOrigem = clienteAtual;
			}
			else if (clienteAtual.identificar().compareTo(idDestino) == 0) {
				clienteDestino = clienteAtual;
			}
		}
		if (clienteOrigem == null || clienteDestino == null) {
			System.out.println("Cliente não encontrado! ");
			return;
		}
		ArrayList<Veiculo> listaOrigem = clienteOrigem.getListaVeiculos();
		ArrayList<Veiculo> listaDestino = clienteDestino.getListaVeiculos();
		int quantidade_carros = listaOrigem.size();
		for (int i = 0; i < quantidade_carros; i++) {
			listaDestino.add(listaOrigem.get(0));
			listaOrigem.remove(0);										
		}
		clienteOrigem.setValorSeguro(0);
		calcularPrecoSeguroCliente(clienteDestino.identificar());		
	}
	
	/*
	 * Returns the total insurance
	 */
	public double calcularReceita() {
		double receita = 0;
		for (Cliente i : listaClientes) {
			receita += i.getValorSeguro();
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

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	
}
