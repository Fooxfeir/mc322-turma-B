
public enum MenuOperacoes {
	SAIR,
	CADASTROS(new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_FROTA,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	AUTORIZACOES(new SubmenuOperacoes[] {
			SubmenuOperacoes.AUTORIZAR_CONDUTOR,
			SubmenuOperacoes.DESAUTORIZAR_CONDUTOR,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR(new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_CLIENTE,
			SubmenuOperacoes.LISTAR_SEGURO_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_CONDUTOR_POR_SEGURO,
			SubmenuOperacoes.LISTAR_FROTA_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_SINISTROS_POR_SEGURADORA,
			SubmenuOperacoes.LISTAR_SINISTROS_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULO_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULO_POR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR(new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SINISTRO,
			SubmenuOperacoes.VOLTAR
	}),
	ATUALIZAR_FROTA,
	GERAR_SEGURO,
	GERAR_SINISTRO,
	TRANSFERIR_SEGURO,
	CALCULAR_RECEITA_SEGURADORA;
	
	private final SubmenuOperacoes[] submenuOperacoes;
	
	MenuOperacoes(){
		this.submenuOperacoes = null;
	}
	
	MenuOperacoes(SubmenuOperacoes[] submenuOperacoes){
		this.submenuOperacoes = submenuOperacoes;
	}
	
	public SubmenuOperacoes[] getSubmenu(){
		return submenuOperacoes;
	}
	
}
