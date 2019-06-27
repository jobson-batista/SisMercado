public class Produto {
	private String codigo;
	private String nome;
	private double precoAtacadao;
	private double precoVarejo;
	private boolean perecivel;
	private String validade;
	
	public Produto(String nome, String codigo, double precoAtacado, double precoVarejo, boolean perecivel, String data) {
		this.nome = nome;
		this.codigo = codigo;
		this.precoAtacadao = precoAtacado;
		this.precoVarejo = precoVarejo;
		this.perecivel = perecivel;
		this.validade = data;
	}

	public Produto() {
		this.nome = "";
		this.codigo = "";
		this.precoAtacadao = 0.0;
		this.precoVarejo = 0.0;
		this.perecivel = false;
		this.validade = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public boolean ehPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public String getValidade() {
		return validade;
	}



	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrecoAtacadao() {
		return precoAtacadao;
	}
	public void setPrecoAtacadao(double precoAtacadao) {
		this.precoAtacadao = precoAtacadao;
	}
	public double getPrecoVarejo() {
		return precoVarejo;
	}
	public void setPrecoVarejo(double precoVarejo) {
		this.precoVarejo = precoVarejo;
	}	
	
	@Override
	public String toString() {
		String p = "NÃO";
		if(this.perecivel==true) {
			p = "SIM";
		}
		return "Nome: "+this.nome.toUpperCase()+"\n"
				+ "Código: "+this.codigo+"\n"
				+ "Preço no Atacado: R$ "+this.precoAtacadao+"\n"
				+ "Preço no Varejo: R$ "+this.precoVarejo+"\n"
				+ "Perecível: "+p+"\n"
				+ "Validade: "+this.validade+"\n";
	}
	
}
