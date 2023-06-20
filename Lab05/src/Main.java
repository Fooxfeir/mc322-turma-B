import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc;
		MenuOperacoes operacao;
		SubmenuOperacoes subOperacao;
		String comando;
		int opcao;
		Seguradora setePalmos = new Seguradora("83.562.625/0001-74", "Sete Palmos", "399", "casa", "email");
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
						System.out.println("Qual a data de nascimento? ");
						String nascimento = sc.next();
						Date data = new Date(nascimento);
						ClientePF novoCliente = new ClientePF(nome, "9887", "casa", "email", cpf, genero, "em", data);
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
							novoCliente.cadastrarVeiculo(carro);
						}
						setePalmos.cadastrarCliente(novoCliente);
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
						System.out.println("Qual a data de fundação? ");
						String fundacao = sc.next();
						Date data = new Date(fundacao);
						ClientePJ novoCliente = new ClientePJ(nome, "923", endereco, "email", cnpj, data, qtdeFuncionarios);
						System.out.println("Quantas frotas possui? ");
						int qntFrotas = sc.nextInt();
						for (int i = 0; i < qntFrotas; i++) {
							System.out.println("Qual o código dessa frota? ");
							String code = sc.next();
							Frota novaFrota = new Frota(code);
							System.out.println("Quantos carros possui? ");
							int qntCarros = sc.nextInt();
							for (int j = 0; j < qntCarros; j++) {
								System.out.println("Qual a placa? ");
								String placa = sc.next();
								System.out.println("Qual a marca? ");
								String marca = sc.next();
								System.out.println("Qual o modelo? ");
								String modelo = sc.next();
								System.out.println("Qual o ano? ");
								int ano = sc.nextInt();
								Veiculo carro = new Veiculo(placa, marca, modelo, ano);
								novaFrota.addVeiculo(carro);
							}
						}
						setePalmos.cadastrarCliente(novoCliente);
					}
					break;
				case CADASTRAR_SEGURADORA:
					System.out.println("Qual o CNPJ? ");
					String cnpj = sc.next();
					System.out.println("Qual o nome? ");
					nome = sc.next();
					System.out.println("Qual o endereço? ");
					endereco = sc.next();
					System.out.println("Qual o telefone? ");
					String telefone = sc.next();
					System.out.println("Qual o email? ");
					String email = sc.next();
					Seguradora nova_seguradora = new Seguradora(cnpj, nome, telefone, endereco, email);
					seguradoras.add(nova_seguradora);
					break;
				case CADASTRAR_VEICULO:
					String cpf;
					do {
						System.out.println("Qual o CPF do cliente? ");
						cpf = sc.next();
					}while (!Validacao.validaCPF(cpf));
					//adiciona o veículo na lista do cliente
					for (Cliente cliente : setePalmos.getListaClientes()) {
						if (cliente.identificar().compareTo(cpf) == 0) {
							ClientePF clientePF = (ClientePF) cliente;
							System.out.println("Qual a placa? ");
							String placa = sc.next();
							System.out.println("Qual a marca? ");
							String marca = sc.next();
							System.out.println("Qual o modelo? ");
							String modelo = sc.next();
							System.out.println("Qual o ano? ");
							int ano = sc.nextInt();
							Veiculo carro = new Veiculo(placa, marca, modelo, ano);
							clientePF.cadastrarVeiculo(carro);
							break;
						}
					}
					//atualiza os valores dos seguros desse cliente
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro instanceof SeguroPF) {
							SeguroPF seguroPF = (SeguroPF) seguro;
							if (seguroPF.getCliente().identificar().compareTo(cpf) == 0) {
								seguroPF.calcularValor();
							}
						}
					}
					break;
				case CADASTRAR_FROTA:
					do {
						System.out.println("Qual o CNPJ do cliente? ");
						cnpj = sc.next();
					}while (!Validacao.validaCNPJ(cnpj));
					for (Cliente cliente : setePalmos.getListaClientes()) {
						ClientePJ clientePJ = (ClientePJ) cliente;						
						System.out.println("Qual o código da frota?");
						String code = sc.next();
						Frota frota = new Frota(code);
						clientePJ.cadastrarFrota(frota);
					}
				case VOLTAR:
					break;
				default:
					break;
				}
				break;
				
			case AUTORIZACOES:
				SubmenuOperacoes submenuA[] = MenuOperacoes.AUTORIZACOES.getSubmenu();
				for (int i = 0; i < submenuA.length; i++) {
					System.out.println(i + "-" + submenuA[i]);
				}
				opcao = sc.nextInt();
				subOperacao = submenuA[opcao];
				switch (subOperacao){
				case AUTORIZAR_CONDUTOR:
					System.out.println("Qual o id do seguro? ");
					int id = sc.nextInt();
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro.getId() == id) {
							System.out.println("Qual o cpf do condutor? ");
							String cpf = sc.next();
							Condutor novoCondutor = new Condutor(cpf, null, null, null, null, null);
							seguro.autorizarCondutor(novoCondutor);
						}
					}
					break;
				case DESAUTORIZAR_CONDUTOR:
					System.out.println("Qual o id do seguro? ");
					id = sc.nextInt();
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro.getId() == id) {
							System.out.println("Qual o cpf do condutor? ");
							String cpf = sc.next();
							seguro.desautorizarCondutor(cpf);
							seguro.calcularValor();
						}
					}
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				
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
					for (Sinistro sinistro : setePalmos.getSinistrosPorCliente(identificador)) {
						System.out.println(sinistro);
					}
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
				case LISTAR_SEGURO_POR_CLIENTE:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					identificador = sc.next();
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro instanceof SeguroPF) {
							SeguroPF seguroPF = (SeguroPF) seguro;
							if (seguroPF.getCliente().identificar().compareTo(identificador) == 0) {
								for (Sinistro sinistro : seguroPF.getListaSinistros()) {
									System.out.println(sinistro);
								}
							}
						}
						else {
							SeguroPJ seguroPJ = (SeguroPJ) seguro;
							if (seguroPJ.getCliente().identificar().compareTo(identificador) == 0) {
								for (Sinistro sinistro : seguroPJ.getListaSinistros()) {
									System.out.println(sinistro);
								}
							}
						}
					}
				case LISTAR_CONDUTOR_POR_SEGURO:
					System.out.println("Qual o id do seguro? ");
					int id = sc.nextInt();
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro.getId() == id) {
							for (Condutor condutor : seguro.getListaCondutores()) {
								System.out.println(condutor);
							}
						}
					}
				case LISTAR_FROTA_POR_CLIENTE:
					System.out.println("Qual o CNPJ do cliente? ");
					identificador = sc.next();
					for (Cliente cliente : setePalmos.getListaClientes()) {
						if (cliente.identificar().compareTo(identificador) == 0) {
							ClientePJ clientePJ = (ClientePJ) cliente;
							for (Frota frota : clientePJ.getListaFrota()) {
								System.out.println(frota);
							}

						}
					}

				case LISTAR_VEICULO_POR_CLIENTE:
					System.out.println("Qual o CPF/CNPJ do cliente? ");
					identificador = sc.next();
					setePalmos.listarVeiculoPorCliente(identificador);
					break;
				case LISTAR_VEICULO_POR_SEGURADORA:
					System.out.println("Qual o nome da seguradora? ");
					identificador = sc.next();
					for (Seguradora seguradora : seguradoras) {
						if (seguradora.getNome().compareTo(identificador) == 0) {
							for (Cliente cliente : seguradora.getListaClientes()) {
								seguradora.listarVeiculoPorCliente(cliente.identificar());
							}
						}
					}
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				
				break;
			
			case GERAR_SEGURO:
				System.out.println("Qual o CPF/CNPJ do cliente? ");
				String identificador = sc.next();
				Cliente clienteSeguro = null;
				for (Cliente cliente : setePalmos.getListaClientes()) {
					if (cliente.identificar().compareTo(identificador) == 0) {
						clienteSeguro = cliente;
					}
				}
				if (clienteSeguro instanceof ClientePF) {
					ClientePF clientePF = (ClientePF) clienteSeguro;
					System.out.println("Qual a placa do veiculo? ");
					String placa = sc.next();
					Veiculo veiculoSeguro = null;
					for (Veiculo veiculo : clientePF.getListaVeiculos()) {
						if (veiculo.getPlaca().compareTo(placa) == 0) {
							veiculoSeguro = veiculo;
						}
					}
					setePalmos.gerarSeguro(clientePF, veiculoSeguro);
				}
				if (clienteSeguro instanceof ClientePJ) {
					ClientePJ clientePJ = (ClientePJ) clienteSeguro;
					System.out.println("Qual o código da frota? ");
					String code = sc.next();
					Frota frotaSeguro = null;
					for (Frota frota : clientePJ.getListaFrota()) {
						if (frota.getCode().compareTo(code) == 0) {
							frotaSeguro = frota;
						}
					}
					setePalmos.gerarSeguro(clientePJ, frotaSeguro);
				}
				break;
			
			case EXCLUIR:
				SubmenuOperacoes submenu2[] = MenuOperacoes.EXCLUIR.getSubmenu();
				for (int i = 0; i < submenu2.length; i++) {
					System.out.println(i + "-" + submenu2[i]);
				}
				opcao = sc.nextInt();
				subOperacao = submenu2[opcao];
				switch(subOperacao) {
				case EXCLUIR_CLIENTE:
					System.out.println("Qual seu CPF/CNPJ? ");
					identificador = sc.next();
					setePalmos.removerCliente(identificador);
					break;
				case EXCLUIR_SINISTRO:
					System.out.println("Qual o id do seguro? ");
					int id = sc.nextInt();
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro.getId() == id) {
							seguro.removerSinistro();
						}
					}
					break;	
				case EXCLUIR_VEICULO:
					System.out.println("Qual o CPF do cliente? ");
					identificador = sc.next();
					System.out.println("Qual a placa do veículo? ");
					String placa = sc.next();
					for (Cliente cliente : setePalmos.getListaClientes()) {
						if (cliente.identificar().compareTo(identificador) == 0) {
							ClientePF clientePF = (ClientePF) cliente;
							clientePF.removerVeiculo(placa);
							for (Seguro seguro : setePalmos.getListaSeguros()) {
								if (seguro instanceof SeguroPF) {
									SeguroPF seguroPF = (SeguroPF) seguro;
									if (seguroPF.getCliente().identificar().compareTo(identificador) == 0) {
										seguroPF.calcularValor();
									}
								}
							}
						}
					}
					break;
				case VOLTAR:
					break;
				default:
					break;
				}
				break;
			
			case ATUALIZAR_FROTA:
				String cnpj;
				do {
					System.out.println("Qual o CNPJ do cliente? ");
					cnpj = sc.next();
				}while (!Validacao.validaCNPJ(cnpj));
				System.out.println("Qual o código da frota?");
				String code = sc.next();
				//adiciona o veículo na lista do cliente
				for (Cliente cliente : setePalmos.getListaClientes()) {
					if (cliente.identificar().compareTo(cnpj) == 0) {
						ClientePJ clientePJ = (ClientePJ) cliente;
						System.out.println("Qual a placa? ");
						String placa = sc.next();
						clientePJ.atualizarFrota(placa, code);
						break;
					}
					for (Seguro seguro : setePalmos.getListaSeguros()) {
						if (seguro instanceof SeguroPJ) {
							SeguroPJ seguroPJ = (SeguroPJ) seguro;
							if (seguroPJ.getCliente().identificar().compareTo(cnpj) == 0) {
								seguroPJ.calcularValor();
							}
						}
					}
				}
			
			case GERAR_SINISTRO:
				System.out.println("Qual o id do seguro? ");
				int id = sc.nextInt();
				for (Seguro seguro : setePalmos.getListaSeguros()) {
					if (seguro.getId() == id) {
						seguro.gerarSinistro();
						seguro.calcularValor();
					}
				}
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
