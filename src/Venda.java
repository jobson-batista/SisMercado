import java.util.ArrayList;
import java.util.List;

public class Venda {
	private String idVenda;
	private String idCaixa;
	private String funcionarioVenda;
	private double totalVenda;
	private double trocoVenda;
	
	private List<Venda> listaVenda = new ArrayList<Venda>();
	private List<Produto> listaProduto = new ArrayList<Produto>();
	
	public String getIdVenda() {
		return idVenda;
	}

	public Venda(String idVenda, String idCaixa, String funcionarioVenda) {
		this.funcionarioVenda = funcionarioVenda;
		this.idCaixa = idCaixa;
		this.idVenda = idVenda;
	}
	
	public void cadastrarVenda(Venda venda) {
		listaVenda.add(venda);
	}
	
	public void addProduto(Produto p) {
		listaProduto.add(p);
	}
	
	public String gerarCupomFiscal(Venda venda){
		return "######## CUPOM FISCAL ########\n"
				+ "ID Venda: "+this.idVenda+"\n"
				+ "ID Caixa: "+this.idCaixa+"\n"
				+ "Funcionário: "+this.funcionarioVenda+"\n"
				+ "Total:R$ "+this.totalVenda+"\n"
				+ "Troco: R$ "+this.trocoVenda+"\n";

	}
	
	public void finalizarVenda(Venda venda) {
		gerarCupomFiscal(venda);
		
	}
	
}
