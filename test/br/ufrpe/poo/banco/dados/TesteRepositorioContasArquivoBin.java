package br.ufrpe.poo.banco.dados;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaImposto;

public class TesteRepositorioContasArquivoBin {

    private RepositorioContasArquivoBin repositorio;

    // Caminho para o arquivo de teste que guarda as informações de cada conta
    private final String ARQUIVO_TESTE = "contas_teste.dat";

    // Deletar o arquivo de teste antes da execução de cada teste
    @Before
    public void setUp() throws RepositorioException {
        File arquivo = new File(ARQUIVO_TESTE);
        if (arquivo.exists()) {
            arquivo.delete();
        }
        repositorio = new RepositorioContasArquivoBin();
    }

    // Testa o construtor
    @Test
    public void RepContasArqBinTest() throws RepositorioException, IOException {
        ContaImposto conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);

        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        assertNotNull(novoRepositorio.procurar("123"));
    }

    // Testa se as contas são lidas corretamente no arquivo
    @Test
    public void lerArquivoTest() throws RepositorioException, IOException {
        ContaImposto conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);

        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        assertNotNull(novoRepositorio.procurar("123"));
    }

    // Testa se as contas são gravadas corretamente no arquivo
    @Test
    public void testGravarArquivo() throws RepositorioException, IOException {
        ContaImposto conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);

        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        ContaImposto contaLida = (ContaImposto) novoRepositorio.procurar("123");
        assertNotNull(contaLida);
    }

    // Testa se as contas são removidas corretamente do arquivo
    @Test
    public void testRemover() throws RepositorioException, IOException {
        ContaImposto conta = new ContaImposto("123", 1000);
        repositorio.inserir(conta);
        repositorio.remover("123");

        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        assertNull(novoRepositorio.procurar("123"));
    }

    // Testa se o interator retorna corretamente as contas do repositorio
    @Test
    public void testGetIterator() throws RepositorioException, IOException {
        ContaImposto conta1 = new ContaImposto("123", 1000);
        ContaImposto conta2 = new ContaImposto("456", 2000);

        repositorio.inserir(conta1);
        repositorio.inserir(conta2);

        IteratorContaAbstrata iterator = repositorio.getIterator();
        assertTrue(iterator.hasNext());
        assertEquals("123", iterator.next().getNumero());
        assertEquals("456", iterator.next().getNumero());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoverContaNaoExistente() throws RepositorioException, IOException {
        // Tenta remover uma conta que não existe no repositório
        boolean resultado = repositorio.remover("999");
        
        // Verifica que o método retorna false, indicando que a conta não foi removida
        assertFalse(resultado);

        // Verifica que a conta ainda não existe no repositório
        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        assertNull(novoRepositorio.procurar("999"));
    }

    @Test
    public void testAtualizarContaNaoExistente() throws RepositorioException, IOException {
        ContaImposto conta = new ContaImposto("999", 1000);
        
        // Tenta atualizar uma conta que não existe no repositório
        boolean resultado = repositorio.atualizar(conta);
        
        // Verifica que o método retorna false, indicando que a conta não foi atualizada
        assertFalse(resultado);

        // Verifica que a conta não foi adicionada ao repositório
        RepositorioContasArquivoBin novoRepositorio = new RepositorioContasArquivoBin();
        assertNull(novoRepositorio.procurar("999"));
    }

}
