import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GravadorDeDados {
	private final static String ARQUIVO_USUARIO = "txt/usuarios.txt";
	private final static String ARQUIVO_CLIENTE_PF = "txt/clientespf.txt";
	private final static String ARQUIVO_CLIENTE_PJ = "txt/clientespj.txt";
	private final static String ARQUIVO_PRODUTO = "txt/produtos.txt";
	
	//############ MÉTODO AYLA - LP ##############
	
	public List<String> recuperaTextoDeArquivo(String nomeArquivo) throws IOException{
		BufferedReader leitor = null;
		List<String> textoLido = new ArrayList<String>();
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String texto = null;
			do {
				texto = leitor.readLine(); //Lê a proxima linha do file
				if(texto != null) {
					textoLido.add(texto);
				} 
			}while(texto != null);
		} finally {
			if(leitor != null) {
				leitor.close();
			}
		}
		return textoLido;
	}
	
	
	public void gravaTextoEmArquivo(List<String> texto, String nomeArquivo) throws IOException{
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(nomeArquivo));
			for(String s: texto) {
				gravador.write(s+"\n");
			}
		} finally {
			if(gravador!=null) {
				gravador.close();
			}
		}
	}
	
	//############ MÉTODO PARA USUÁRIO ##############
	
	public void gravaTextoUsuario(List<Usuario> users) throws IOException{ // GRAVAR USUARIOS NO TXT
		List<String> dadosUsuario = new ArrayList<String>();
		for(Usuario u: users) {
			String dadoTemp = u.getLogin()+"##"+u.getNome()+"##"+u.getSenha();
			dadosUsuario.add(dadoTemp);
		}
		this.gravaTextoEmArquivo(dadosUsuario, ARQUIVO_USUARIO);
	}
	
	public List<Usuario> recuperaTextoUsuario() throws IOException{ //RECUPERAR USUARIOS DO TXT
		List<String> lista1 = recuperaTextoDeArquivo(ARQUIVO_USUARIO);
		List<Usuario> lista2 = new ArrayList<Usuario>();
		for(String s:lista1) {
			String[] dados = s.split("##");
			Usuario novoU = new Usuario(dados[1],dados[0],dados[2]);
			lista2.add(novoU);
		}
		return lista2; 
	}
	
	//############ MÉTODOS PARA CLIENTE ##############
	
	
	public void gravaTextoClientePF(List<Cliente> clientes) throws IOException{ //GRAVAR CLIENTES PF NO TXT
		List<String> dadosCliente = new ArrayList<>();
		for(Cliente c:clientes) {
			String dadoTemp = c.getCodigo()+"##"+c.getNome().toUpperCase();
			dadosCliente.add(dadoTemp);
		}
		this.gravaTextoEmArquivo(dadosCliente, ARQUIVO_CLIENTE_PF);
	}
	
	public void gravaTextoClientePJ(List<Cliente> clientes) throws IOException{ //GRAVAR CLIENTES PJ NO TXT
		List<String> dadosCliente = new ArrayList<>();
		for(Cliente c:clientes) {
			String dadoTemp = c.getCodigo()+"##"+c.getNome().toUpperCase();
			dadosCliente.add(dadoTemp);
		}
		this.gravaTextoEmArquivo(dadosCliente, ARQUIVO_CLIENTE_PJ);
	}
	
	public List<Cliente> recuperaTextoClientePF() throws IOException{  //RECUPERAR CLIENTES PF NO TXT
		List<String> lista1 = this.recuperaTextoDeArquivo(ARQUIVO_CLIENTE_PF);
		List<Cliente> lista2 = new ArrayList<>();
		for(String s:lista1) {
			String[] dados = s.split("##");
			ClientePF novoC = new ClientePF(dados[0],dados[1]);
			lista2.add(novoC);
		}
		return lista2;
	}
	
	public List<Cliente> recuperaTextoClientePJ() throws IOException{ //RECUPERAR CLIENTES PJ NO TXT
		List<String> lista1 = this.recuperaTextoDeArquivo(ARQUIVO_CLIENTE_PJ);
		List<Cliente> lista2 = new ArrayList<>();
		for(String s:lista1) {
			String[] dados = s.split("##");
			ClientePF novoC = new ClientePF(dados[0],dados[1]);
			lista2.add(novoC);
		}
		return lista2;
	}
	
	//############ MÉTODOS PARA PRODUTO ##############
	
	public void gravarTextoProduto(List<Produto> listaP) throws IOException {
		List<String> lista1 = new ArrayList<>();
		for (Produto p:listaP) {
			String dadosTemp = p.getNome().toUpperCase()+"##"+p.getCodigo()+"##"+p.getPrecoAtacadao()+"##"+p.getPrecoVarejo()+"##"+p.ehPerecivel()+"##"+p.getValidade();
			lista1.add(dadosTemp);
		}
		this.gravaTextoEmArquivo(lista1, ARQUIVO_PRODUTO);
	}
	
	public List<Produto> recuperaTextoProduto() throws IOException {
		List<String> lista1 = this.recuperaTextoDeArquivo(ARQUIVO_PRODUTO);
		List<Produto> lista2 = new ArrayList<>();
		if(lista1!=null) {
			for(String s:lista1) {
				String[] dados = s.split("##");
				try {
					Produto novoP = new Produto(dados[0],dados[1],Double.parseDouble(dados[2]),Double.parseDouble(dados[3]),Boolean.parseBoolean(dados[4]),dados[5]);				
					lista2.add(novoP);
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Erro ao acessar item da lista. ");
				}
			}			
		}
		
		return lista2;
	}
	
}
