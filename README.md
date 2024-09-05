# Repositório - Testes de Software (1VA) 
Este repositório contém a documentação dos mutantes e os resultados de cobertura dos testes, desenvolvidos para a disciplina de Testes de Software.

## Mutantes 🧬
Os mutantes foram elaborados e organizados em branches distintas, cada uma contendo um erro específico introduzido em uma classe.

- **Banco**: duplica o valor creditado no método *creditar*.

  ![Comparação Banco](https://i.imgur.com/Ht5iDlH.png)
  
- **Cliente**: remove a conta anterior à que deve ser removida no método *removerConta*.

  ![Comparação Cliente](https://i.imgur.com/qwRnZXg.png)

- **Conta**: o saldo da conta será reduzido em 13 unidades a mais do que o valor solicitado para o método *debitar*.

  ![Comparação Conta](https://i.imgur.com/frvXBGR.png)

- **ContaAbstrata**: adiciona um valor fixo de 1000 ao saldo inicial, fazendo com que o saldo seja sempre maior do que o valor passado.

  ![Comparação ContaAbstrata](https://i.imgur.com/rkNzja4.png)

- **ContaEspecial**: o bonus não é zerado após ser creditado ao saldo no método *renderBonus*.

  ![Comparação ContaEspecial](https://i.imgur.com/Ki7gWt4.png)
  
- **Conta Imposto**: soma ao saldo da conta o valor total a ser debitado, ao invés de subtrair, através do método *debitar*.

  ![Comparação ContaImposto](https://i.imgur.com/t9ikfZt.png)

- **Poupanca**: o saldo é multiplicado em 4 vezes antes de se calcular o rendimento dos juros no método *renderJuros*.

  ![Comparação Poupanca](https://i.imgur.com/kc3bkuR.png)

- **Repositório Contas Arquivo Bin**: a gravação do arquivo só é realizada quando não é possível inserir uma conta, no método *gravarArquivo*.

  ![Comparação ArquivoBin](https://i.imgur.com/N86PWq3.png)

- **Repositório Contas Array**: durante a varredura do array, pula o índice de 2 em 2.

  ![Comparação ContasArray](https://i.imgur.com/VNQlGaO.png)


## Cobertura de Testes JUnit + EclEmma 📄
Os testes para garantir a melhoria na cobertura foram elaborados e organizados em pastas distintas, referentes a sua localização na pasta **src**.

  ![Local dos Testes](https://i.imgur.com/Lmirv5Z.png)

- **Dados**: contém testes referentes aos arquivos *RepostórioContasArquivoBin* e *RepositorioContasArray*.
- **Negocio**: contém testes referentes aos arquivos *Banco*, *Cliente*, *Conta*, *ContaAbstrata*, *ContaEspecial*, *ContaImposto* e *Poupanca*. 
Testes pré-existentes necessitaram de **modificações** para garantir a cobertura dos métodos por completo, e foi necessário a criação de **novos testes** para cobrir métodos que não estavam cobertos. Para rodar os testes, deve-se executar o arquivo **SuiteTodosOsTestes.java**, que chama todas as classes de teste para executarem, gerando um relatório EclEmma unificado.

### Cobertura de Instruções

  ![CoverageInstructions](https://i.imgur.com/rNHpjzw.png)

- A cobertura de instruções se demonstrou eficiente em quase todas as classes, com exceção das classes **Banco** e **RepostórioContasArquivoBin**, com 82.8% e 75.4%, respectivamente, de cobertura.

### Cobertura de Branches

  ![CoverageBranches](https://i.imgur.com/TXudDCL.png)

- As classes **ContaEspecial** e **Poupanca** não demonstram relatório na cobertura de branches porque não possuem estruturas de controle (como if, for, etc.). Portanto, a cobertura de branches não se aplica a elas. Já para a classe **Banco**, todas as branches não conseguem ser alcançadas, resultado em uma cobertura de 75%.
### Cobertura de Linhas

  ![CoverageInstructions](https://i.imgur.com/q1OATk2.png)

- A maioria das classes propostas obteve cobertura 100% com base nos testes, com exceção de **Banco** e **RepostórioContasArquivoBin**, com 84.2% e 81.7%, respectivamente. A diferença na cobertura dessas classes está **diretamente relacionada à cobertura de branches**, que não atingiu 100% em algumas partes do código. Isso significa que, embora a cobertura de linhas seja alta, a lógica condicional não foi completamente testada.

### Problemas ⚠
As classes *Banco* e *RepositórioContasArquivoBin* não obtiveram cobertura integral em todas as categorias devido a **problemas identificados com o mau funcionamento das classes e suas estruturas**. Esses problemas impactaram a capacidade de realizar testes completos e cobrir todas as funcionalidades propostas pelas classes.

Para detalhes sobre os erros e questões específicas, foram dispostos de reports na aba Issues no repositório, onde as falhas conhecidas estão descritas de forma mais detalhada.

