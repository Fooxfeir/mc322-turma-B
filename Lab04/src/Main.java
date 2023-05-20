import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc;
		MenuOperacoes operacao;
		SubmenuOperacoes subOperacao;
		String comando;
		int opcao;
		Seguradora setePalmos = new Seguradora("setePalmos", "Curitiba", "05922", "setePalmos@gmail.com");
		ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
		seguradoras.add(setePalmos);
		System.out.println("Bem-vindo ao sistema da Sete Palmos!");
		do {
			System.out.println("\nQual operação você quer efetuar? ");
			MenuOperacoes menu[] = MenuOperacoes.values();
			for (int i = 1; i < menu.length; i++) {
				System.out.println(i + "-" + menu[i]);
			}
			System.out.println("0-" + menu[0]);
			sc = new Scanner(System.in);
			opcao = sc.nextInt();
			operacao = MenuOperacoes.values()[opcao];
			
			switch (operacao) {
			
			
			case CADASTROS:
				SubmenuOperacoes submenu0[] = MenuOperacoes.CADASTROS.getSubmenu();
				for (int i = 0; i < submenu0.length; i++) {
					System.out.println(i + "-" + submenu0[i]);
				}
				opcao = sc.nextInt();
				subOperacao = submenu0[opcao];
				switch(subOperacao) {
				case CADASTRAR_CLIENTE:
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
						System.out.println("Qual o ano de nascimento? ");
						int nascimento = sc.nextInt();
						Date data = new Date(1, 1, nascimento);
						Date hoje = new Date(1, 1, 2023);
						ClientePF novoCliente = new ClientePF(nome, endereco, cpf, genero, data, "EM", hoje, "Média");
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
						setePalmos.cadastrarCliente(novoCliente);
						setePalmos.calcularPrecoSeguroCliente(cpf);
					}
					else {
						String cnpj;
						do {
							System.out.println("Qual o CNPJ? ");
							cnpj = sc.next();
							if (!Validacao.validaCNPJ(cnpj)) {
								System.out.println("CNPJ inválido ");
							}
						} while (!Validacao.validaCNPJ(cnpj));
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
						setePalmos.cadastrarCliente(novoCliente);
						setePalmos.calcularPrecoSeguroCliente(cnpj);
					}
					break;
				case CADASTRAR_SEGURADORA:
					System.out.println("Qual o nome? ");
					nome = sc.next();
					System.out.println("Qual o endereço? ");
					endereco = sc.next();
					System.out.println("Qual o telefone? ");
					String telefone = sc.next();
					System.out.println("Qual o email? ");
					String email = sc.next();
					Seguradora nova_seguradora = new Seguradora(nome, endereco, telefone, email);
					seguradoras.add(nova_seguradora);
					break;
				case CADASTRAR_VEICULO:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					String identificador = sc.next();
					System.out.println("Qual a placa? ");
					String placa = sc.next();
					System.out.println("Qual a marca? ");
					String marca = sc.next();
					System.out.println("Qual o modelo? ");
					String modelo = sc.next();
					System.out.println("Qual o ano? ");
					int ano = sc.nextInt();
					Veiculo carro = new Veiculo(placa, marca, modelo, ano);
					setePalmos.cadastrarVeiculo(identificador, carro);
					setePalmos.calcularPrecoSeguroCliente(identificador);
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				break;
				
				
			case LISTAR:
				SubmenuOperacoes submenu1[] = MenuOperacoes.LISTAR.getSubmenu();
				for (int i = 0; i < submenu1.length; i++) {
					System.out.println(i + "-" + submenu1[i]);
				}
				opcao = sc.nextInt();
				subOperacao = submenu1[opcao];
				switch(subOperacao) {
				case LISTAR_CLIENTE:
					setePalmos.listarClientes();
					break;
				case LISTAR_SINISTROS_POR_CLIENTE:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					String identificador = sc.next();
					setePalmos.visualizarSinistro(identificador);
					break;
				case LISTAR_SINISTROS_POR_SEGURADORA:
					System.out.println("Qual o nome da seguradora? ");
					identificador = sc.next();
					for (Seguradora seguradora : seguradoras) {
						if (seguradora.getNome().compareTo(identificador) == 0) {
							seguradora.listarSinistros();
						}
					}
					break;
				case LISTAR_VEICULO_POR_CLIENTE:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					identificador = sc.next();
					setePalmos.listarVeiculos(identificador);
					break;
				case LISTAR_VEICULO_POR_SEGURADORA:
					System.out.println("Qual o nome da seguradora? ");
					identificador = sc.next();
					for (Seguradora seguradora : seguradoras) {
						if (seguradora.getNome().compareTo(identificador) == 0) {
							seguradora.listarVeiculos();
						}
					}
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				
				break;
				
			
			case EXCLUIR:
				SubmenuOperacoes submenu2[] = MenuOperacoes.EXCLUIR.getSubmenu();
				for (int i = 0; i < submenu2.length; i++) {
					System.out.println(i + "-" + submenu2[i]);
				}
				opcao = sc.nextInt();
				subOperacao = submenu2[opcao];
				String identificador;
				switch(subOperacao) {
				case EXCLUIR_CLIENTE:
					System.out.println("Qual seu CPF/CNPJ? ");
					identificador = sc.next();
					setePalmos.removerCliente(identificador);
					break;
				case EXCLUIR_SINISTRO:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					identificador = sc.next();
					System.out.println("Qual a data do sinistro? ");
					String data = sc.next();
					setePalmos.removerSinistro(identificador, data);
					setePalmos.calcularPrecoSeguroCliente(identificador);
					break;
				case EXCLUIR_VEICULO:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					identificador = sc.next();
					System.out.println("Qual a placa do veículo? ");
					String placa = sc.next();
					setePalmos.removerVeiculo(identificador, placa);
					setePalmos.calcularPrecoSeguroCliente(identificador);
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				break;
				
			
			case GERAR_SINISTRO:
				setePalmos.gerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Qual o CPF/CNPJ do cliente de origem? ");
				String id1 = sc.next();
				System.out.println("Qual o CPF/CNPJ do cliente de destino? ");
				String id2 = sc.next();
				setePalmos.transferirSeguro(id1, id2);
				break;
			case CALCULAR_RECEITA_SEGURADORA:
				double receita = setePalmos.calcularReceita();
				System.out.println("Sua receita é: " + receita);
				break;
			case SAIR:
				break;
			default:
				break;
			}
		}while (operacao != MenuOperacoes.SAIR);
		sc.close();
	}
}
