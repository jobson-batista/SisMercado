import java.io.IOException;
import java.util.List;

public interface SisMercado {
	public void cadastrarUsuario(Usuario u) throws UsuarioExisteException,IOException;
	public void cadastrarClientePF(Cliente c) throws ClienteExisteException,IOException;
	public void cadastrarClientePJ(Cliente c) throws ClienteExisteException,IOException;
	public void cadastrarProduto(Produto c) throws ProdutoExisteExcpetion,IOException;
	public void cadastrarVenda(Venda v);
	public boolean verificarLogin(String login, String senha) throws IOException;
	public List<Usuario> pesquisaUsuariosComNomePrefixo(String prefixo);
	public void obterListaDeClientes() throws IOException;
	
}
