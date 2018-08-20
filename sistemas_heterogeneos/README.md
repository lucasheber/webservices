# Sistemas Heterogêneos

	Atividade realizada para realizar a comunicação entre cliente-servidor, utilizando sockets, no qual um dos pares esteja em uma linguagem de programação diferente.
	A atividade coniste em que o cliente possa realizar o cadastro de uma turma e posteriomente os alunos pertencentes a esta turma, indicando, na hora do cadastro, se o aluno está matriculado ou não. Em seguida esses dados são envidados para o servidor.
	O servirdor deve ler esses dados e exibir uma mesagem com as informações obtidas sobre o mesmo.


## O que foi usado.

	* Para o cliente foi utilizado a linguagem Java.
	* Para o servidor foi utilizado a linguagem Python.
	
	Para o formato do dados envidados foi adotado o formato JSON.

## Como usar.

	```
	1. Start o servidor `python servidor/Servidor.py`
	2. Gere o bytecode do cliente em `javac cliente/Cliente.java`
	3. Em seguida start o cliente `java cliente/Cliente`
	```

## Pré-requisitos
	
	* Java >= 1.5
	* Python >= 3