package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;

/**
 * Classe de teste respons�vel por testar as condi��es dos m�todos
 * adicionarConta e removerConta da classe Cliente.
 * 
 * @author Aluno
 * 
 */
public class TesteCliente {

	/**
	 * Testa a inser��o de uma nova conta vinculada ao cliente
	 */
	@Test
	public void adicionarContaTest() {
		Cliente c1 = new Cliente("nome", "123");
		try {
			c1.adicionarConta("1");
		} catch (ClienteJaPossuiContaException e) {
			fail();
		}
		assertEquals(c1.procurarConta("1"), 0);
	}

	/**
	 * Testa a condi��o da tentativa de adicionar uma conta j� existente � lista
	 * de contas do cliente
	 * 
	 * @throws ClienteJaPossuiContaException
	 */
	@Test(expected = ClienteJaPossuiContaException.class)
	public void adicionarContaJaExistenteTest()
			throws ClienteJaPossuiContaException {
		Cliente c1 = new Cliente("nome", "123");
		c1.adicionarConta("1"); // adiciona a conta a 1� vez
		c1.adicionarConta("1"); // tentativa de adicionar a mesma conta
								// novamente
	}

	/**
	 * Teste a remo��o de uma conta da lista de contas do cliente
	 */
	@Test
	public void removerContaClienteTest() {
		Cliente c1 = new Cliente("nome", "123");
		try {
			c1.adicionarConta("1"); // adiciona conta com n�mero 1
			c1.removerConta("1"); // remove a conta de n�mero 1
		} catch (Exception e) {
			fail("Exce��o inesperada lancada!");
		}

		assertEquals(c1.procurarConta("1"), -1);
	}

	/**
	 * Testa a remo��o de uma determinada conta que n�o est� vinculada ao
	 * cliente
	 * 
	 * @throws ClienteNaoPossuiContaException
	 */
	@Test(expected = ClienteNaoPossuiContaException.class)
	public void removerContaClienteSemContaTest()
			throws ClienteNaoPossuiContaException {
		Cliente c1 = new Cliente("nome", "123");
		c1.removerConta("1"); // tenta remover a conta de um cliente sem contas
	}

	// Testa se o nome do cliente é retornado corretamente
	@Test
	public void getNomeTest() {
	    Cliente cliente = new Cliente("nomeTeste", "123");
	    assertEquals("nomeTeste", cliente.getNome());
	}

	// Testa se o nome do cliente é alterado corretamente
	@Test
	public void setNomeTest() {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.setNome("novoNome");
	    assertEquals("novoNome", cliente.getNome());
	}

	// festa se o CPF do cliente é retornado corretamente
	@Test
	public void getCpfTest() {
	    Cliente cliente = new Cliente("Nome", "123");
	    assertEquals("123", cliente.getCpf());
	}

	// Testa se o CPF do cliente é alterado corretamente
	@Test
	public void setCpfTest() {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.setCpf("456");
	    assertEquals("456", cliente.getCpf());
	}

	// Testa se o array de contas do cliente é retornado corretamente
	@Test
	public void getContasTest() throws ClienteJaPossuiContaException {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.adicionarConta("1");
	    cliente.adicionarConta("2");
	    cliente.adicionarConta("3");

	    ArrayList<String> contasEsperadas = new ArrayList<String>();
	    contasEsperadas.add("1");
	    contasEsperadas.add("2");
	    contasEsperadas.add("3");

	    assertEquals(contasEsperadas, cliente.getContas());
	}

	// Testa se as contas do cliente são removidas corretamente
	@Test
	public void removerTodasAsContasTest() throws ClienteJaPossuiContaException {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.adicionarConta("1");
	    cliente.adicionarConta("2");
	    cliente.adicionarConta("3");
	    cliente.removerTodasAsContas();
	    assertNull(cliente.getContas());
	}
	
	// Testa se uma conta do cliente é encontrada corretamente
	@Test
	public void procurarContaTest() throws ClienteJaPossuiContaException {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.adicionarConta("1");
	    assertEquals(0, cliente.procurarConta("1"));
	    assertEquals(-1, cliente.procurarConta("2"));
	}

	//  Testa se o número de uma conta é retornado corretamente
	@Test
	public void consultarNumeroContaTest() throws ClienteJaPossuiContaException {
		Cliente cliente = new Cliente("Nome", "123");
		cliente.adicionarConta("1");
		assertEquals("1", cliente.consultarNumeroConta(0));
	}

	// Testa se dois objetos "Cliente" são comparados corretamente com base no CPF
	@Test
	public void equalsTest() {
	    Cliente cliente1 = new Cliente("clienteUm", "123");
	    Cliente cliente2 = new Cliente ("clienteDois", "123");
	    Cliente cliente3 = new Cliente("clienteTres", "456");

	    assertTrue(cliente1.equals(cliente2));
	    assertFalse(cliente1.equals(cliente3));
	    assertFalse(cliente1.equals(new Object()));
	}

	// Testa se a apresentação da classe "Cliente" é retornada corretamente
	@Test
	public void toStringTest() throws ClienteJaPossuiContaException {
	    Cliente cliente = new Cliente("Nome", "123");
	    cliente.adicionarConta("1");
	    cliente.adicionarConta("2");
	    String stringEsperada = "Nome: Nome\nCPF: 123\nContas: [1, 2]";
	    assertEquals(stringEsperada, cliente.toString());
	}

}
