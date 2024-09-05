package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class BancoTest4 {

    private Banco banco;
    private IRepositorioClientes repositorioClientesMock;
    private IRepositorioContas repositorioContasMock;
    private Cliente cliente;
    private ContaAbstrata conta;

    @Before
    public void setUp() throws RepositorioException {
        repositorioClientesMock = Mockito.mock(IRepositorioClientes.class);
        repositorioContasMock = Mockito.mock(IRepositorioContas.class);
        banco = new Banco(repositorioClientesMock, repositorioContasMock);
        cliente = new Cliente("Caio", "12345678900"); // CPF aleatório
        conta = new ContaEspecial("5", 1000.0);

        // Setup mocks
        when(repositorioClientesMock.procurar(cliente.getCpf())).thenReturn(cliente);
        when(repositorioContasMock.existe(conta.getNumero())).thenReturn(true);
    }

    @Test
    public void testRemoverClienteNaoCadastrado() {
        // CPF que não está cadastrado
        String cpfInexistente = "99999999999";

        // Configura o mock para retornar null, indicando que o cliente não existe
        when(repositorioClientesMock.procurar(cpfInexistente)).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(ClienteNaoCadastradoException.class, () -> {
            banco.removerCliente(cpfInexistente);
        });

        System.out.println("Exceção ClienteNaoCadastradoException lançada corretamente para CPF não cadastrado.");
    }
}
