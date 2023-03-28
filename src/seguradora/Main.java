package seguradora;

public class Main {

	public static void main(String[] args) {
		//creates a new client
		Cliente newCliente = new Cliente("Luiggi", "059.272.649-56", 19, "Rua", "29/10/2003");
		System.out.println(newCliente.toString());
		
		//checks for cpf validity
		if (newCliente.validarCPF(newCliente.getCpf())) {
			System.out.println("CPF válido");
		}
		
		//creates a new seguradora
		Seguradora newSeguradora = new Seguradora("Sete Palmos", "Curitiba", "666", "7plms@orkut.com");
		System.out.println(newSeguradora.toString());
		
		//changes information, checking alterations.
		newSeguradora.setEmail("novo@gmail.com");
		System.out.println(newSeguradora.toString());

		//new vehicle
		Veiculo newVeiculo = new Veiculo("FST42ER", "E-racing", "Speed4");
		System.out.println(newVeiculo.toString());
		
		//new sinistro
		Sinistro newSinistro = new Sinistro("Campinas", "14/04/2023");
		System.out.println(newSinistro.getId());
		
		
		//checks if id is increasing
		Sinistro newSinistro2 = new Sinistro("Campinas", "15/04/2023");
		System.out.println(newSinistro2.getId());


		
		
	}

}
