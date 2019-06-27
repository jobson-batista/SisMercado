
public abstract class Cliente {
	private String nome;
	
	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public abstract String getCodigo();

	@Override
	public String toString() {
		return "\nNome: " +this.nome+"\nCódigo: "+this.getCodigo()+"\n";
	}
	
	public abstract boolean equals(Object obj);
	
	public abstract int hashCode();
	
	
}
