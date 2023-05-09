import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc;
		String comando;
		Seguradora setePalmos = new Seguradora("setePalmos", "Curitiba", "05922", "setePalmos@gmail.com");
		do {
			System.out.println("Bem-vindo ao sistema da Sete Palmos! Qual operação você quer efetuar? ");
			sc = new Scanner(System.in);
			comando = sc.next();
			if (comando.compareTo("Cadastrar_cliente") == 0) {
				System.out.println("PF ou PJ? ");
				comando = sc.next();
				System.out.println("Qual o nome? ");
				String nome = sc.next();
				System.out.println("Qual o endereço? ");
				String endereco = sc.next();
				if (comando.compareTo("PF") == 0) {
					System.out.println("Qual o CPF? ");
					String cpf = sc.next();
					System.out.println("Qual o genero? ");
					String genero = sc.next();
					ClientePF novoCliente = new ClientePF(nome, endereco, cpf, genero, null, "EM", null, "Média");
					System.out.println("Quantos carros possui? ");
					int qntCarros = sc.nextInt();
					for (int i = 0; i < qntCarros; i++) {
						System.out.println("Qual a placa? ");
						String placa = sc.next();
						System.out.println("Qual a marca? ");
						String marca = sc.next();
						System.out.println("Qual o modelo? ");
						String modelo = sc.next();
						System.out.println("Qual o ano? ");
						int ano = sc.nextInt();
						Veiculo carro = new Veiculo(placa, marca, modelo, ano);
						novoCliente.adiocionarVeiculo(carro);
					}
					if (novoCliente.validarCPF(cpf) == false) {
						System.out.println("Dados inválidos! ");
					}
					else {
						setePalmos.cadastrarCliente(novoCliente);
					}
				}
				else {
					System.out.println("Qual o CNPJ? ");
					String cnpj = sc.next();
					ClientePJ novoCliente = new ClientePJ(nome, endereco, cnpj, null);
					System.out.println("Quantos carros possui? ");
					int qntCarros = sc.nextInt();
					for (int i = 0; i < qntCarros; i++) {
						System.out.println("Qual a placa? ");
						String placa = sc.next();
						System.out.println("Qual a marca? ");
						String marca = sc.next();
						System.out.println("Qual o modelo? ");
						String modelo = sc.next();
						System.out.println("Qual o ano? ");
						int ano = sc.nextInt();
						Veiculo carro = new Veiculo(placa, marca, modelo, ano);
						novoCliente.adiocionarVeiculo(carro);
					}

					if (novoCliente.validarCNPJ(cnpj) == false) {
						System.out.println("Dados inválidos! ");
					}
					else {
						setePalmos.cadastrarCliente(novoCliente);
					}
				}
			}
			else if (comando.compareTo("Remover_cliente") == 0) {
				System.out.println("PF ou PJ? ");
				comando = sc.next();
				if (comando.compareTo("PF") == 0) {
					System.out.println("Qual o CPF? ");
					String cpf = sc.next();
					ClientePF removido = new ClientePF(null, null, null, cpf, null, null, null, null);
					setePalmos.removerCliente(removido);
				}
				else {
					System.out.println("Qual o CNPJ? ");
					String cnpj = sc.next();
					ClientePJ removido = new ClientePJ(null, null, cnpj, null);
					setePalmos.removerCliente(removido);
				}
					
			}
			else if (comando.compareTo("Gerar_sinistro") == 0) {
				setePalmos.gerarSinistro();
			}
			else if (comando.compareTo("Visualizar_sinistro") == 0) {
				System.out.println("Qual o nome do cliente? ");
				String nome = sc.next();
				setePalmos.visualizarSinistro(nome);
			}
			else if (comando.compareTo("Listar_sinistro") == 0) {
				setePalmos.listarSinistros();
			}
			else if (comando.compareTo("Listar_clientes") == 0) {
				System.out.println("PF ou PJ? ");
				comando = sc.next();
				setePalmos.listarClientes(comando);
			}

		} while (comando.compareTo("Terminar") != 0);
		sc.close();
		
	}
}
