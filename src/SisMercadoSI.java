import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SisMercadoSI implements SisMercado {

	public List<Usuario> listaUser = new ArrayList<Usuario>();
	public List<Cliente> listaClientePF = new ArrayList<Cliente>();
	public List<Cliente> listaClientePJ = new ArrayList<Cliente>();
	public List<Produto> listaProduto = new ArrayList<Produto>();
	public List<Venda> listaVenda = new ArrayList<Venda>(); // POR ENQUANTO VAZIA :-(
	public GravadorDeDados gravador = new GravadorDeDados();

	public void carregarDados() throws IOException { // Inicializar dados do txt nas listas [INICIO DO PROGRAMA]
		this.listaUser = gravador.recuperaTextoUsuario();
		this.listaClientePF = gravador.recuperaTextoClientePF();
		this.listaClientePJ = gravador.recuperaTextoClientePJ();
		this.listaProduto = gravador.recuperaTextoProduto();
	}

	public void atualizarDados() throws IOException { // Salvar dados das listas nos txt [FIM do PROGRAMA]
		gravador.gravaTextoUsuario(listaUser);
		gravador.gravaTextoClientePF(listaClientePF);
		gravador.gravaTextoClientePJ(listaClientePJ);
		gravador.gravarTextoProduto(listaProduto);
	}

	@Override
	public void cadastrarUsuario(Usuario u) throws UsuarioExisteException, IOException {
		this.listaUser.add(u);
	}

	@Override
	public void cadastrarClientePF(Cliente cliente) throws ClienteExisteException, IOException {
		this.listaClientePF.add(cliente);
	}

	@Override
	public void cadastrarClientePJ(Cliente cliente) throws ClienteExisteException, IOException {
		this.listaClientePJ.add(cliente);
	}

	@Override
	public void cadastrarProduto(Produto p) throws IOException, ProdutoExisteExcpetion {
		this.listaProduto.add(p);
	}

	@Override
	public void cadastrarVenda(Venda v) { // NÃO TESTEI AINDA
		for (Venda venda: listaVenda) {
			if (venda.getIdVenda().equals(v.getIdVenda())) {
				JOptionPane.showMessageDialog(null, "Venda Já Cadastrado!");
			} else {
				listaVenda.add(v);
				JOptionPane.showMessageDialog(null, "Venda cadastrado com sucesso!");
			}
		}

	}

	@Override
	public boolean verificarLogin(String login, String senha) {
		for (Usuario u : listaUser) {
			if (u.getLogin().equalsIgnoreCase(login) && u.getSenha().equalsIgnoreCase(senha)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Usuario> pesquisaUsuariosComNomePrefixo(String prefixo) {
		List<Usuario> listaPrefixo = new ArrayList<Usuario>();
		for (Usuario u : listaUser) {
			if (u.getNome().toLowerCase().startsWith(prefixo.toLowerCase()) == true) {
				listaPrefixo.add(u);
			}
		}

		return listaPrefixo;
	}

	public void obterListaDeClientes() {
		String dados = "+--------- CLIENTES PESSOA FÍSICA ---------+ \n";
		for (Cliente c : listaClientePF) {
			dados += "Nome: " + c.getNome() + "\nCPF: " + c.getCodigo() + "\n\n";
		}

		dados += "\n+--------- CLIENTES PESSOA JURÍDICA ---------+ \n";

		for (Cliente c : listaClientePJ) {
			dados += "Nome: " + c.getNome() + "\nCNPJ: " + c.getCodigo() + "\n\n";
		}
		JOptionPane.showMessageDialog(null, dados);
	}

	public void obterListaDeProdutos() {
		String produtos = "Lista de Produtos:\n";
		for (Produto p : listaProduto) {
			produtos += p.toString() + "\n";
		}
		JOptionPane.showMessageDialog(null, produtos);
	}
	
	public Produto pesquisarProduto(String codigo) {
		Produto prod = new Produto();
		for(Produto p: listaProduto) {
			if(p.getCodigo().equalsIgnoreCase(codigo)) {
				prod = p;
			}
		}
		return prod;
	}
	
	public void finalizarVenda() {
		
	}

	public void novaVenda() throws IOException, ProdutoExisteExcpetion {
		List<Produto> carrinho = new ArrayList<Produto>();
		boolean laco = true;
		while (laco) {
			int escolha1 = Integer
					.parseInt((String) JOptionPane.showInputDialog(null,
							"[1] Adicionar Produto\n[2] Remover Produto\n[3] Finalizar Venda\n"	+ "[4] Cancelar Venda\n[5] Sair do Programa",
							"MENU VENDA", JOptionPane.PLAIN_MESSAGE));
			if(escolha1==1) {
				String codigo = (String) JOptionPane.showInputDialog(null,"Código do Produto",null, escolha1, new ImageIcon("img/si.png"), null, null);
				Produto p = this.pesquisarProduto(codigo);
				try {
					carrinho.add(p);					
					JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "ERRO ao adicionar produto no carrinho!\nProcure o gerente.");
				}
				
			} else if(escolha1==2) {
				String codigo = (String) JOptionPane.showInputDialog(null,"Código do Produto",null, escolha1, new ImageIcon("img/si.png"), null, null);
				Produto p = this.pesquisarProduto(codigo);
				try {
					carrinho.remove(p);					
					JOptionPane.showMessageDialog(null, "Produto Removido com Sucesso!");					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "ERRO ao remover produto do carrinho!\nProcure o gerente.");
				}
			} else if (escolha1==3) {
				
			}
		}
	}
}
