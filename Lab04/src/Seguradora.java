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
	public boolean removerCliente(ClientePF cliente) {
		Cliente atual;
		for (int i = 0; i < listaClientes.size(); i ++) {
			atual = listaClientes.get(i);
			if (atual instanceof ClientePF) {
				if (((ClientePF) atual).compararCliente(cliente)) {
					listaClientes.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean removerCliente(ClientePJ cliente) {
		Cliente atual;
		for (int i = 0; i < listaClientes.size(); i ++) {
			atual = listaClientes.get(i);
			if (atual instanceof ClientePJ) {
				if (((ClientePJ) atual).compararCliente(cliente)) {
					listaClientes.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public void listarClientes(String tipoCliente) {
		if (tipoCliente.compareToIgnoreCase("PF") == 0) {
			for (int i = 0; i < listaClientes.size(); i++) {
				Cliente atual = listaClientes.get(i);
				if (atual instanceof ClientePF) {
					System.out.println(((ClientePF) atual).toString());
				}
			}
		}
		else {
			for (int i = 0; i < listaClientes.size(); i++) {
				Cliente atual = listaClientes.get(i);
				if (atual instanceof ClientePJ) {
					System.out.println(((ClientePJ) atual).toString());
				}
			}
		}
	}
	
	/*
	 * Adds a new accident to the list
	 */
	@SuppressWarnings("resource")
	public boolean gerarSinistro() {
		System.out.println("Informe a data do sinistro ");
		Scanner sc = new Scanner(System.in);
		String data = sc.next();
		System.out.println("Informe o endereço do sinistro ");
		String endereco = sc.next();
		System.out.println("Qual o nome do cliente do sinistro? ");
		String nomeCliente = sc.next();
		Cliente clienteAtual = new Cliente(null, null);
		for (int i = 0; i < listaClientes.size(); i++) {
			clienteAtual = listaClientes.get(i);
			if (nomeCliente.compareTo(clienteAtual.getNome()) == 0) {
				break;
			}
		}
		if (clienteAtual.getNome() == null) {
			System.out.println("Cliente inexistente! ");
			sc.close();
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
			sc.close();
			return false;
		}
		Sinistro novoSinistro = new Sinistro(endereco, data, this, veiculoAtual, clienteAtual);
		listaSinistros.add(novoSinistro);
		return true;
	}
	
	/*
	 * Visualizes the accident
	 */
	public boolean visualizarSinistro(String cliente) {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i++) {
			atual = listaSinistros.get(i);
			if (cliente.compareTo(atual.getCliente().getNome()) == 0) { 
				System.out.print(atual.toString());
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Visualizes all the accidents
	 */
	public void listarSinistros() {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i++) {
			atual = listaSinistros.get(i);
			System.out.print(atual.toString());

		}
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
