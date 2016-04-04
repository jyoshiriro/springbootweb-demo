package vc.com.rico.domains;

public enum TipoEscolaEnum {
	
	INTEGRAL("i"), NORMAL("n");
	
	private final String valor;

	private TipoEscolaEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
