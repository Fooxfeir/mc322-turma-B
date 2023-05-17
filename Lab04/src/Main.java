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
				
				String nome;
				do {
					System.out.println("Qual o nome? ");
					nome = sc.next();
					if (!Validacao.validaNome(nome)) {
						System.out.println("Nome inválido ");
					}
				} while (!Validacao.validaNome(nome));
				
				System.out.println("Qual o endereço? ");
				String endereco = sc.next();
				
				if (comando.compareTo("PF") == 0) {
					String cpf;
					do {
						System.out.println("Qual o CPF? ");
						cpf = sc.next();
						if (!Validacao.validaCPF(cpf)) {
							System.out.println("CPF inválido ");
						}
					} while (!Validacao.validaCPF(cpf));
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
					System.out.println("Quantos funcionários possui? ");
					int qtdeFuncionarios = sc.nextInt();
					ClientePJ novoCliente = new ClientePJ(nome, endereco, cnpj, null, qtdeFuncionarios);
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

					if (novoCliente.validaCNPJ(cnpj) == false) {
						System.out.println("Dados inválidos! ");
					}
					else {
						setePalmos.cadastrarCliente(novoCliente);
					}
				}
			}
			else if (comando.compareTo("Remover_cliente") == 0) {
				System.out.println("Qual seu CPF/CNPJ? ");
				comando = sc.next();
				setePalmos.removerCliente(comando);
			}
			else if (comando.compareTo("Gerar_sinistro") == 0) {
				setePalmos.gerarSinistro();
			}
			else if (comando.compareTo("Visualizar_sinistro") == 0) {
				System.out.println("Qual o CPF/CNPJ do cliente? ");
				String nome = sc.next();
				setePalmos.visualizarSinistro(nome);
			}
			else if (comando.compareTo("Listar_sinistro") == 0) {
				setePalmos.listarSinistros();
			}
			else if (comando.compareTo("Listar_clientes") == 0) {
				setePalmos.listarClientes();
			}

		} while (comando.compareTo("Terminar") != 0);
		sc.close();
		
	}
}
