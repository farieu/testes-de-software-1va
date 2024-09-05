package br.ufrpe.poo.banco.negocio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBinTest;
import br.ufrpe.poo.banco.dados.RepositorioContasArrayTest;

@RunWith(Suite.class)
@SuiteClasses({ RepositorioContasArquivoBinTest.class, RepositorioContasArrayTest.class, BancoBranches.class, 
		BancoTest.class, BancoTest2.class, BancoTest3.class, BancoTest4.class, BancoTesteCadastro.class, TestConta2.class,
		TestConta3.class, TesteBanco.class, TesteBancoUnidade.class, TesteCliente.class, TesteConta.class, TesteConta2.class, 
		TesteContaEspecial.class, TesteContaImposto.class, TestePoupanca.class, TesteTransferencia.class })
public class SuiteTodosOsTestes {
}