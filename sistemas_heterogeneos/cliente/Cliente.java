import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 12000;

    public static void main(String[] args) {
        Turma turmaT = new Turma();

        new Cliente().cadastrarTurma( turmaT );
        sendMessage(turmaT);
        
        System.out.println("Finalizou!");
    }
    

    public static void sendMessage( Object message ) {

        try {
        
            Socket socket = new Socket( HOST_NAME, PORT );
        
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            osw.write(message.toString(), 0, message.toString().length());

            osw.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }

    /* *************************************************************************** */
    /* *************************** METODOS/OPERACOES ***************************** */

    private void cadastrarTurma( Cliente.Turma turmaT )  {
        String turma, ano, curso;
    
        System.out.println("\033[H\033[2J");

        System.out.println("\t===============================================");
        System.out.println("\t\t\tCADASTRO DA TURMA");
        System.out.println("\t===============================================");

        try {

            BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in) );
    
            System.out.print("Nome da turma: ");
            turma = teclado.readLine();

            System.out.print("Ano: ");
            ano = teclado.readLine();

            System.out.print("Curso: ");
            curso = teclado.readLine();
    
            turmaT.setAno(ano);
            turmaT.setCurso(curso);
            turmaT.setTurma(turma);

            cadastraAluno(turmaT);

            System.out.println("\033[H\033[2J");
            // System.out.println(turmaT);

            // exibeAlunos(turmaT);

            teclado.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }// cadastrarTurma

    private void cadastraAluno( Cliente.Turma turma ){
        String nome, matriculado;
        boolean isMatriculado = false;

        System.out.println("\033[H\033[2J");

        try {


            BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in) );
            String opcao;

            do {

                System.out.println("\t===============================================");
                System.out.println("\t\t\tCADASTRO DE ALUNOS");
                System.out.println("\t===============================================");

                System.out.print("Nome: ");
                nome = teclado.readLine();

                System.out.print("Esta matriculado? Sim (s) Nao (n): ");
                matriculado = teclado.readLine();

                if( matriculado.equalsIgnoreCase("s") ) isMatriculado = true;

                Aluno aluno = new Aluno();
                aluno.setMatriculado(isMatriculado);
                aluno.setNome(nome);

                turma.adicionaAluno(aluno);

                System.out.print("Deseja continuar? Sim (s) Nao (n): ");
                opcao = teclado.readLine();

                isMatriculado = false;
                System.out.println("\033[H\033[2J");
            }while( !opcao.equalsIgnoreCase("n") );
            
            teclado.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }// cadastraAluno

    public void name() {
        
    } void exibeAlunos( Cliente.Turma turma ){
        for (Aluno aluno : turma.getAlunos()) {
            System.out.println(aluno);
        }
    }// 

    /* *************************************************************************** */
    /* **************************** CLASSES INTERNAS ***************************** */

    public static class Aluno {
        private String nome;
        private Integer id;
        private boolean matriculado;

        private static int contador;

        public Aluno(){
            this.id = ++contador;
        }

        public void setNome( String nome ){
            this.nome = nome;
        }

        public void setId( Integer id ){
            this.id = id;
        }

        public void setMatriculado( boolean isMatriculado ){
            this.matriculado = isMatriculado;
        }

        @Override
        public String toString(){
            String json = String.format( "{ 'nome': '%s', 'matriculado': %s, 'id': %d } ", nome, matriculado, id );
            
            return json.replace("'", "\"");
        }
    }

    public static class Turma {
        private String turma;
        private String curso;
        private String ano;

        private List<Cliente.Aluno> alunos;

        public Turma(){
            this.alunos = new ArrayList<>();
        }

        public void setTurma( String turma ){
            this.turma = turma;
        }

        public void setCurso( String curso ){
            this.curso = curso;
        }

        public void setAno( String ano ){
            this.ano = ano;
        }

        public void adicionaAluno( Aluno aluno ){
            this.alunos.add( aluno );
        }

        public Aluno[] getAlunos(){

            return this.alunos.toArray(new Aluno[0]);
        }

        @Override
        public String toString(){

            String alunos = "";

            for (Aluno aluno : this.alunos) {
                alunos += aluno.toString() + ",";
            }

            alunos = alunos.substring(0, alunos.length() - 1 );

            String json =  String.format("{ 'json': { 'turma': '%s', 'curso': '%s', 'ano': %s, 'alunos': [%s] } }", turma, curso, ano, alunos );
            
            return json.replace("'", "\"");
        }
    }
}// class Cliente