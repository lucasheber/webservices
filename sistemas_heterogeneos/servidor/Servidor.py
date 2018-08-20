import socket
import json as JSON

HOST = '127.0.0.1'     # Endereco IP do Servidor
PORT = 12000            # Porta que o Servidor esta

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
orig = (HOST, PORT)
tcp.bind(orig)
tcp.listen(1)


def alunos_matriculados(json):
    turmas = JSON.loads(json)

    nome_turma = turmas['json']['turma'].decode('utf-8')
    ano = turmas['json']['ano']
    curso = turmas['json']['curso'].decode('utf-8')

    qtd_alunos = len( turmas['json']['alunos'] )
    
    cont_matriculados = 0;

    for posicao in range(0, qtd_alunos):
        aluno = turmas['json']['alunos'][posicao]
        
        if aluno['matriculado'] == True:
            cont_matriculados += 1;

        posicao += 1

    print('\n\tA turma \"{}\" de {} do curso \"{}\" \n\tpossui {} aluno(s), dos quais {} estao devidamente matriculados.\n'.format(nome_turma, ano, curso, qtd_alunos, cont_matriculados ).encode('utf-8'))

while True:
    print 'Aguardando a conexao...'

    con, cliente = tcp.accept()

    print 'Conexao estabelecida {}'.format(cliente)

    msg = con.recv(2048).decode('utf-8');

    alunos_matriculados(msg)

    print '\nFinalizando conexao do cliente', cliente
    con.close()
    break;