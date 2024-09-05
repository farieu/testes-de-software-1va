package br.ufrpe.poo.banco.dados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaImposto;

public class RepositorioContasArrayTest {
    
    private RepositorioContasArray repositorio;

    // Configura o ambiente (repositório) antes da execução de cada teste
    @Before
    public void setUpRepositorio() throws RepositorioException {
        repositorio = new RepositorioContasArray();
    }

    // Testa se uma conta é inserida corretamente no repositório
    @Test
    public void inserirContaTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        boolean inserida = repositorio.inserir(conta);
        assertTrue(inserida);
        assertNotNull(repositorio.procurar("123"));
    }

    // Testa se não é possível inserir uma conta existente
    @Test
    public void inserirContaExistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        boolean inserida = repositorio.inserir(conta);
        assertFalse(inserida);
    }
    
    // Testa se uma conta é encontrada corretamente
    @Test
    public void procurarContaExistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);
        ContaAbstrata contaEncontrada = repositorio.procurar("123");
        assertNotNull(contaEncontrada);
        assertEquals(contaEncontrada, conta);
    }

    // Testa o comportamento do método ao procurar uma conta inexistente
    @Test
    public void procurarContaInexistenteTest() {
        ContaAbstrata contaEncontrada = repositorio.procurar("456");
        assertNull(contaEncontrada);
    }

    // Testa se uma conta existente é removida corretamente
    @Test
    public void removerContaExistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);
        boolean removida = repositorio.remover("123");
        assertTrue(removida);
        assertNull(repositorio.procurar("123"));
    }

    // Testa o comportamento do método tentar remover uma conta inexistente
    @Test
    public void removerContaInexistenteTest() throws RepositorioException {
        boolean removida = repositorio.remover("456");
        assertFalse(removida);
    }

    // Testa se uma conta existente é atualizada corretamente
    @Test
    public void atualizarContaExistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);
        ContaAbstrata contaAtualizada = new ContaImposto("123", 2000);
        boolean atualizada = repositorio.atualizar(contaAtualizada);
        assertTrue(atualizada);
        assertEquals(2000, contaAtualizada.getSaldo(), 0);
    }

    // Testa o comportamento do método ao tentar atualizar uma conta inexistente
    @Test
    public void atualizarContaInexistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        boolean atualizada = repositorio.atualizar(conta);
        assertFalse(atualizada);
    }

    // Testa se o método retorna 'true' para uma conta existente
    @Test
    public void existeContaExistenteTest() throws RepositorioException {
        ContaAbstrata conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);
        boolean contaExiste = repositorio.existe("123");
        assertTrue(contaExiste);
    }

    // Testa se o método retorna 'false' para uma conta inexistente
    @Test
    public void existeContaInexistenteTest() {
        boolean contaExiste = repositorio.existe("456");
        assertFalse(contaExiste);
    }

    // Testa se o iterator retorna todas as contas do repositorio
    @Test
    public void getIteratorTest() throws RepositorioException {
        ContaAbstrata conta1 = new ContaImposto("123", 1000);
        ContaAbstrata conta2 = new ContaImposto("456", 1000);
        repositorio.inserir(conta1);
        repositorio.inserir(conta2);

        IteratorContaAbstrata iterator = repositorio.getIterator();
        int count = 0;
        while (iterator.hasNext()) {
            ContaAbstrata conta = iterator.next();
            assertNotNull(conta);
            count++;
        }
        assertEquals(2, count);
    }
    
    // Testa se o array 'contas' é redimensionado corretamente ao inserir mais de 100 contas
    @Test
    public void inserirContaRedimensionaArrayTest() throws RepositorioException {
        // Insere 100 contas para preencher o array original
        for (int i = 0; i < 100; i++) {
            ContaAbstrata conta = new ContaImposto(String.valueOf(i), 1000);
            boolean inserida = repositorio.inserir(conta);
            assertTrue(inserida);
        }

        // Insere a 101ª conta, que deve acionar o redimensionamento do array
        ContaAbstrata conta101 = new ContaImposto("101", 1000);
        boolean inserida101 = repositorio.inserir(conta101);
        assertTrue(inserida101);

        // Verifica se a 101ª conta foi inserida corretamente
        ContaAbstrata contaProcurada = repositorio.procurar("101");
        assertNotNull(contaProcurada);
        assertEquals("101", contaProcurada.getNumero());
    }

}
