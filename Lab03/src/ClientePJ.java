
public class ClientePJ extends Cliente {
	private final String cnpj;
	private Date dataFundacao;
	
	public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao) {
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}
	
	public boolean compararCliente(ClientePJ comparado) {
		if (cnpj.compareTo(comparado.getCnpj()) != 0){
			return false;
		}
		return true;
	}
	
	public boolean validarCNPJ(String cnpj) {
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
			cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
			cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
			cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
			cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
			(cnpj.length() != 14))
			return(false);
		char digito13;
		char digito14;
		int soma = 0;
		int peso = 2;
		
		 //primeiro verificador
		for (int i = 0; i < 11; i++) {
			int digito = (int)(cnpj.charAt(i) - 48);
		    soma = soma + (digito * peso);
		    peso = peso + 1;
		    if (peso == 10)
		    	peso = 2;
		}
		int resto = soma % 11;
		if (resto == 0 || resto == 1) {
			digito13 = '0';
		}
		else {
			digito13 = (char) ((11 - resto) + 48);
		}
		
		//segundo verificador
		soma = 0;
		peso = 2;
		for (int i = 12; i >= 0; i--) {
			int digito = (int)(cnpj.charAt(i) - 48);
			soma = soma + (digito * peso);
			peso = peso + 1;
			if (peso == 10) {
				peso = 2;
			}
		}
		resto = soma % 11;
		if (resto == 0 || resto == 1) {
			digito14 = '0';
		}
		else {
			digito14 = (char) ((11 - resto) + 48);
		}
		
	    if ((digito13 == cnpj.charAt(12)) && (digito14 == cnpj.charAt(13)))
	         return true;
		
	    else {
	    	return false;
	    }
	}

	public String getCnpj() {
		return cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	@Override
	public String toString() {
		return "ClientePJ [cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + "]";
	}

}
