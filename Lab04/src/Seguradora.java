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
	
	public void listarClientes() {
		for (Cliente i : listaClientes) {
			System.out.println(i.toString());
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
		Cliente clienteAtual = null;
		for (int i = 0; i < listaClientes.size(); i++) {
			clienteAtual = listaClientes.get(i);
			if (nomeCliente.compareTo(clienteAtual.getNome()) == 0) {
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
		double valor = calcularPrecoSeguroCliente(clienteAtual.identificar());
		clienteAtual.setValorSeguro(valor);
		listaSinistros.add(novoSinistro);
		return true;
	}
	
	/*
	 * Visualizes the accident
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
	 * Visualizes all the accidents
	 */
	public void listarSinistros() {
		Sinistro atual;
		for (int i = 0; i < listaSinistros.size(); i++) {
			atual = listaSinistros.get(i);
			System.out.print(atual.toString());

		}
	} 
	
	public double calcularPrecoSeguroCliente(String id) {
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
		return preco;
		
	}
	
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
		//checar se ele passa uma cópia ou um ponteiro
		//programa quebra se for cópia
		ArrayList<Veiculo> listaOrigem = clienteOrigem.getListaVeiculos();
		ArrayList<Veiculo> listaDestino = clienteDestino.getListaVeiculos();
		for (int i = 0; i < listaOrigem.size(); i++) {
			listaDestino.add(listaOrigem.get(0));
			listaOrigem.remove(0);										
		}
		clienteOrigem.setValorSeguro(0);
		double novoValor = calcularPrecoSeguroCliente(clienteDestino.identificar());
		clienteDestino.setValorSeguro(novoValor);
		
	}
	
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
