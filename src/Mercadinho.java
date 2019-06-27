import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Mercadinho {

	public static void main(String[] args) throws ClienteExisteException, IOException, ProdutoExisteExcpetion {
		SisMercadoSI mercado = new SisMercadoSI();
		mercado.carregarDados(); // INICIALIZAR DADOS NAS LISTAS
		boolean loop1 = false;

		while (!loop1) {
			int escolha1 = Integer.parseInt((String)JOptionPane.showInputDialog(null, "[1] Cadastrar-se\n[2] Login\n[3] Sair","MENU PRINCIPAL", JOptionPane.ERROR_MESSAGE,new ImageIcon("img/login.png"), null, null)); // MENU
																													// INICIAL
			if (escolha1 == 1) {

				String nome = (String) JOptionPane.showInputDialog(null, "Digite seu nome","NOME",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/si.png"), null, null);
				String login = (String) JOptionPane.showInputDialog(null, "Digite o novo login","NOVO LOGIN",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/login.png"), null, null);
				String senha = (String) JOptionPane.showInputDialog(null,"Digite a nova senha","SENHA",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/login.png"), null, null);
				if (nome.equals("") || senha.equals("") || login.equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPO(S) VAZIO(S)! Tente Novamente","ERRO",JOptionPane.ERROR_MESSAGE);
				} else {
					Usuario novoUser = new Usuario(nome, login, senha);
					try {
						mercado.cadastrarUsuario(novoUser);
					} catch (UsuarioExisteException erro) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar USUÁRIO: " + erro.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (escolha1 == 2) {
				String login = (String)JOptionPane.showInputDialog(null,"Digite seu Login","LOGIN",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/si.png"), null, null);
				String senha = (String)JOptionPane.showInputDialog(null,"Digite sua Senha","SENHA",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/si.png"), null, null);
				boolean acesso = mercado.verificarLogin(login, senha);
				if (acesso == true) {
					while (acesso) {
						int escolha2 = Integer.parseInt((String)JOptionPane.showInputDialog(null,"[1] Cadastrar Cliente Pessoa Física\n[2] Cadastrar Cliente Pessoa Jurídica\n"
										+ "[3] Obter Lista de Clientes\n[4] Pesquisa Usuários Com Prefixo\n[5] Cadastrar Produto\n[6] Listar Produtos\n"
										+ "[7] Nova Venda (EM BREVE) \n[8]Sair","MENU",JOptionPane.ERROR_MESSAGE,new ImageIcon("img/si.png"), null, null));
						
						if (escolha2 == 1) { // Cadastrar Cliente Físico
							String nome = JOptionPane.showInputDialog("Nome");
							String cpf = JOptionPane.showInputDialog("CPF");
							ClientePF clientePF = new ClientePF(nome, cpf);
							try {
								mercado.cadastrarClientePF(clientePF);
								JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso!");
							} catch (ClienteExisteException erro) {
								JOptionPane.showMessageDialog(null,
										"Erro ao Cadastrar Cliente Físico: " + erro.getMessage());
							}

						} else if (escolha2 == 2) { // Cadastrar Cliente Jurídico
							String nome = JOptionPane.showInputDialog("Nome");
							String cnpj = JOptionPane.showInputDialog("CNPJ");
							ClientePF clientePJ = new ClientePF(nome, cnpj);
							try {
								mercado.cadastrarClientePJ(clientePJ);
								JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso!");
							} catch (ClienteExisteException erro) {
								JOptionPane.showMessageDialog(null,
										"Erro ao Cadastrar Cliente Jurídico: " + erro.getMessage());
							}
						} else if (escolha2 == 3) { // Obter Lista de Clientes
							mercado.obterListaDeClientes();

						} else if (escolha2 == 4) { // Pesquisa Usuários Com Prefixo
							List<Usuario> listaU = new ArrayList<Usuario>();
							String usuarios = "";
							String pf = JOptionPane.showInputDialog("Digite o PREFIXO");
							listaU = mercado.pesquisaUsuariosComNomePrefixo(pf);
							for (Usuario u : listaU) {
								if (login.equals("admin") && senha.equals("admin")) {
									usuarios += "NOME: " + u.getNome().toUpperCase() + "\nLOGIN: " + u.getLogin()
											+ "\nSENHA: " + u.getSenha() + "\n";
								} else {
									usuarios += "NOME: " + u.getNome() + "\n" + u.getLogin() + "\n";
								}
							}

							JOptionPane.showMessageDialog(null, usuarios);

						} else if (escolha2 == 8) { // SAIR
							mercado.atualizarDados();
							acesso = false;

						} else if (escolha2 == 5) { // CADASTRAR PRODUTO
							String validade = "--/--/----";
							String nome = JOptionPane.showInputDialog("Nome do Produto");
							String codigo = JOptionPane.showInputDialog("Código do Produto");
							double precoAtacado = Double
									.parseDouble(JOptionPane.showInputDialog("Preço no Atacado (Mais de 10 Produtos)"));
							double precoVarejo = Double
									.parseDouble(JOptionPane.showInputDialog("Preço no Varejo (Menos de 10 Produtos)"));
							validade = JOptionPane.showInputDialog(
									"Validade do Produto [No formato: dd/mm/aaaa]\n Caso não seja perecível deixe em branco");
							boolean perecivel = false;
							if (validade != null) {
								perecivel = true;
							}
							Produto novoProduto = new Produto(nome, codigo, precoAtacado, precoVarejo, perecivel,
									validade);
							mercado.cadastrarProduto(novoProduto);

						} else if (escolha2 == 6) { // OBTER LISTA DE PRODUTOS
							mercado.obterListaDeProdutos();

						} else if (escolha2 == 7) { // CADASTRAR VENDA [EM TESTES AINDA]
							//mercado.cadastrarVenda(v);
							JOptionPane.showMessageDialog(null, "NAS PRÓXIMAS ATUALIZAÇÕES!","AVISO!",JOptionPane.ERROR_MESSAGE);
							/*String idVenda = JOptionPane.showInputDialog("ID da Venda");
							String idCaixa = JOptionPane.showInputDialog("ID do Caixa");
							String funcionarioVenda = JOptionPane.showInputDialog("Nome do Funcionario");
							double valorCliente = Double.parseDouble(JOptionPane.showInputDialog("Valor Pago pelo Cliente"));
							
							//double troco = valorCliente - totalVenda;
							double totalVenda = Double.parseDouble(JOptionPane.showInputDialog("Valor da Venda"));*/
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
				}

			} else if (escolha1 == 3) {
				int encerrar = JOptionPane.showConfirmDialog(null,"Encerrar?", "SAIR DO PROGRAMA", JOptionPane.YES_NO_OPTION);
				if(encerrar==0) {
					loop1 = true;
					mercado.atualizarDados();
				}
			}
		}

	}

}
