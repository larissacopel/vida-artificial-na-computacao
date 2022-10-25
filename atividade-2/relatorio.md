# Atividade do Código-Fonte do Jogo da Vida


Analise o código-fonte do Jogo da Vida disponibilizado na homepage da disciplina.

Tendo como base este código-fonte, faça o que se pede:

1. **Identifique em qual classe Java (ou quais classes) está a codificação que inicializa o Jogo da Vida com a geração randômica de células vivas e mortas no grid (na tabela).
Identifique em qual trecho do código é feita esta implementação, e explique linha por linha do código-fonte.**

<br>
A classe em que o jogo é inicializado com a geração randômica de células vivas e mortas no grid é TabuleiroGOL. No método preencherTabRandom, cria-se pontos por toda dimensão do tabuleiro:

```
public void preencherTabRandom(int porcentagem) 
{
    for (int x=0; x<dimensaoTabuleiro.width; x++) 
    {
        for (int y=0; y<dimensaoTabuleiro.height; y++) 
        {
            if (Math.random()*100 < porcentagem) 
            {
                addPoint(x,y);
            }
        }//Fim do for y
    }//Fim do for x
}
```

Na primeira linha, temos apenas a definição do método, que trata-se de um método público (acessível pela instância da classe), que recebe como parâmetro um inteiro correspondente à porcentagem de preenchimento do grid e não retorna nada.
Na terceira linha, inicia-se uma iteração para cada posição na abicissa do grid. Na quinta linha, inicia-se uma iteração para cada posição na ordenada do grid, ou seja, estamos percorrendo todos os quadradinhos do tabuleiro, coluna por coluna.
Na sétima linha, gera-se um valor randômico de 0 a 1 através da função Math.random() e multiplica-o por 100, portanto, tem-se um número randômico de 0 a 100. Se o valor obtido por inferior ao percentual informado, adiciona-se um ponto a esse quadradinho (vide linha 9).


2. **Identifique em qual classe Java (ou quais classes) está a codificação que faz a passagem de uma interação do autômato celular para outra interação. Identifique em qual trecho do código é feita a passagem de uma interação para outra, e explique linha por linha do código-fonte. OBS: esta passagem deve ter um comando de repetição, e este é o loop principal do Jogo da Vida.**

A classe em que o jogo faz a passagem de uma interação para outra é  no TabuleiroGOL. No método run, em que temos uma dupla iteração para percorrer todas as linhas e todas as colunas do jogo:

```
for (int x=0; x<dimensaoTabuleiro.width; x++) 
        {
            for (int y=0; y<dimensaoTabuleiro.height; y++) 
            {
            	vizinhosVivos = contarVizinhosVivos(x, y, tabuleiro);
            	            	
                if ( tabuleiro[x][y] ) 
                {
                    if ((vizinhosVivos == 2) || (vizinhosVivos == 3)) 
                    {
                        celulasVivas.add(new Point(x,y));
                    } 
                } else {
                    if (vizinhosVivos == 3) 
                    {
                        celulasVivas.add(new Point(x,y));
                    }
                }// Fim do else
            }// Fim do for j
        }//Fim do for i
```
Obs.: por simplicidade, removi os comentários que existem no código

Na primeira linha temos a estrutura de repetição que irá passar por todo eixo x do tabuleiro. Na terceira linha temos a estrutura de repetição que ira passar por todo o eixo y do tabuleiro. Na quinta linha chamamos o método que ira nos retornar o numero de vizinhos vivos daquele determinado ponto.  Na sétima linha temos uma verificação para saber se aquele ponto do tabuleiro esta vivo, em caso positivo, na nona linha verificamos se o numero de vizinhos vivos é igual a 2 ou 3 e se sim, na linha 11, adicionamos esse ponto dentro do vetor de celulas vivas. Na linha 13, temos o "else" que indica caso a celula não estiver viva. Na linha 15, temos uma verificação para saber se o numero de vizinhos vivos é igual a 3, se sim, na linha 17, será adicionado este ponto no vetor de celulas vivas. 


3. **Identifique em qual classe Java (ou quais classes) está a codificação com as regras para definir quando uma célula irá morrer, quando uma célula irá sobreviver ou quando uma célula irá nascer. Identifique em qual trecho do código é feita esta implementação, e explique linha por linha do código-fonte.**
<br>

A classe é TabuleiroGOL e o método é o run e p trecho de código é o seguinte:

### Sobreviver:

```
if ( tabuleiro[x][y] ) 
{
    if ((vizinhosVivos == 2) || (vizinhosVivos == 3)) 
    {
        celulasVivas.add(new Point(x,y));
    } 
}
```
Obs.: por simplicidade, removi os comentários que existem no código

Na primeira linha do código, realiza-se uma checagem para verificar se o quadradinho que está sendo checada está viva. Na terceira linha, verifica-se se a quantidade de vizinhos vivos da célula atual é igual a dois ou três (a determinação da quantidade de vizinhos vivos é realizada através de outro método já executado antes dessde momento).
Se a célula atual possuir dois ou três vizinhos vivos, ela é adicionada à lista que celulas que estarão vivas após essa iteração. Como trata-se de uma célula que já estava viva, trata-se de uma sobrevivência.

### Nascer: 
```
else {
    if (vizinhosVivos == 3) 
    {
        celulasVivas.add(new Point(x,y));
    }
}
```
Obs.: por simplicidade, removi os comentários que existem no código

A primeira linha do código (else) inicia um bloco para células que não estão vivas. Na segunda linha, verifica-se se a quantidade de vizinhos vivos que a célula atual possui é igual a 3. Caso positivo, na linha 4, adiciona-se a célula à lista que celulas que estarão vivas após essa iteração.

### Morte

A morte não possui um código explícito, porém, como a lista celulasVivas é inicializada com 0 para todas as células, as células que estiverem vivas e não possuírem 2 ou 3 vizinhos vivos, morrerão:

```
if ( tabuleiro[x][y] ) 
{
    if ((vizinhosVivos == 2) || (vizinhosVivos == 3)) 
    {
        celulasVivas.add(new Point(x,y));
    } else {
        /** MORTE **/
    }
} 
```

4. As respostas das questões anteriores devem estar um arquivo DOC ou ODT, e este arquivo deve ser submetido por um dos integrantes da dupla em uma atividade definida pela professora da disciplina.

