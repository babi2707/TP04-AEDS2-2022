/****************************************
 * 
 * @author Barbara Luciano Araujo
 * Matricula: 748190
 * TP04 - Quest�o 2 - Arvore de arvore em Java
 * 
****************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;

class No {

    // ------------------------- atributos -------------------------
    
    public char elemento; // conte�do do no
    public No esq, dir; // filhos da esq e dir
    public No2 outro;

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public No (char elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }

    public No (char elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }

    // ------------------------------------------------------------
}

class No2 {

    // ------------------------- atributos -------------------------
    
    public Filme elemento; // conte�do do no
    public No2 esq, dir; // filhos da esq e dir

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public No2 (Filme elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    public No2 (Filme elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }

    // ------------------------------------------------------------

}

class Arvore {

    // ------------------------- atributos -------------------------
    
    private No raiz;

    // -------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public Arvore() throws Exception  {
        raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');
    }

    // ------------------------------------------------------------

    // ----------------------- pesquisar -----------------------

    public void pesquisar (String x) {
        
        boolean resp;

        MyIO.println("=> " + x);
        MyIO.print("raiz");

        resp = pesquisar(raiz, x);

        MyIO.println(resp == true ? " SIM" : " NAO");
    }

    public boolean pesquisar (No no, String x) {

        boolean resp = false;

        if (no != null) {
            resp = pesquisarSegundaArvore(no.outro, x);

            if (resp == false) {
                MyIO.print(" ESQ ");

                resp = pesquisar(no.esq, x);
            }

            if (resp == false) {
                MyIO.print(" DIR ");

                resp = pesquisar(no.dir, x);
            }


        } 
            

        return resp;
    }

    public boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;

        if (no == null) {
            resp = false;
        } else if (x.compareTo(no.elemento.getTitulo_Original()) < no.elemento.getTitulo_Original().compareTo(x)) {
            MyIO.print("esq ");

            resp = pesquisarSegundaArvore(no.esq, x);
        } else if (x.compareTo(no.elemento.getTitulo_Original()) > no.elemento.getTitulo_Original().compareTo(x)) {
            MyIO.print("dir ");

            resp = pesquisarSegundaArvore(no.dir, x);
        } else {
            resp = true;
        }

        return resp;
    }

    // ---------------------------------------------------------

    // ----------------------- inserir ----------------------- 

    public void inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(char x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void inserir(Filme x) throws Exception {
        inserir(x, raiz);
    }

    public void inserir(Filme x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");
        } else if (x.getTitulo_Original().charAt(0) < i.elemento) {
            inserir(x, i.esq);
        } else if (x.getTitulo_Original().charAt(0) > i.elemento) {
            inserir(x, i.dir);
        } else {
            i.outro = inserir(x, i.outro);
        }
    }

    private No2 inserir(Filme x, No2 i) throws Exception {
        if (i == null) {
            i = new No2(x);
        } else if (x.getTitulo_Original().compareTo(i.elemento.getTitulo_Original()) < 0) {
            i.esq = inserir(x, i.esq);
        } else if (x.getTitulo_Original().compareTo(i.elemento.getTitulo_Original()) > 0) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }

        return i;
    }

    // -------------------------------------------------------

    // ----------------------- mostrar -----------------------

    public void mostrar () {
        mostrar(raiz);
    }

    public void mostrar (No i) {
        if (i != null) {
            mostrar(i.esq);
            mostrar(i.outro);
            mostrar(i.dir);
        }
    }

    public void mostrar (No2 i) {
        if (i != null) {
            mostrar(i.esq);
            mostrar(i.dir);
        }
    }

    // -------------------------------------------------------
}

class Filme {

    // ------------------------- atributos -------------------------

    private String Nome;
    private String Titulo_Original;
    private Date Data_de_Lancamento;
    private int Duracao;
    private String Genero;
    private String Idioma_Original;
    private String Situacao;
    private float Orcamento;
    private ArrayList<String> Key_Words;

    String folder = "./tmp/filmes/";

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public Filme() {

        Nome = "";
        Titulo_Original = "";
        Duracao = 0;
        Genero = "";
        Idioma_Original = "";
        Situacao = "";
        Orcamento = 0;
        Key_Words = new ArrayList<String>();

    }

    public Filme(String Nome, String Titulo_Original, Date Data_de_Lancamento, int Duracao, String Genero,
            String Idioma_Original, String Situacao, Float Orcamento, ArrayList<String> Key_Words) {

        setNome(Nome);
        setTitulo_Original(Titulo_Original);
        setData_de_Lancamento(Data_de_Lancamento);
        setDuracao(Duracao);
        setGenero(Genero);
        setIdioma_Original(Idioma_Original);
        setSituacao(Situacao);
        setOrcamento(Orcamento);
        setKey_Words(Key_Words);

    }

    // ------------------------------------------------------------

    // ------------------------- gets/sets ------------------------

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTitulo_Original() {
        return Titulo_Original;
    }

    public void setTitulo_Original(String Titulo_Original) {
        this.Titulo_Original = Titulo_Original;
    }

    public Date getData_de_Lancamento() {
        return Data_de_Lancamento;
    }

    public void setData_de_Lancamento(Date Data_de_Lancamento) {
        this.Data_de_Lancamento = Data_de_Lancamento;
    }

    public int getDuracao() {
        return Duracao;
    }

    public void setDuracao(int Duracao) {
        this.Duracao = Duracao;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getIdioma_Original() {
        return Idioma_Original;
    }

    public void setIdioma_Original(String Idioma_Original) {
        this.Idioma_Original = Idioma_Original;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }

    public float getOrcamento() {
        return Orcamento;
    }

    public void setOrcamento(float Orcamento) {
        this.Orcamento = Orcamento;
    }

    public ArrayList<String> getKey_Words() {
        return Key_Words;
    }

    public void setKey_Words(ArrayList<String> Key_Words) {
        this.Key_Words = Key_Words;
    }

    // ------------------------------------------------------------

    // ------------------------ remover tags ----------------------

    public String removeTags(String linha) {

        String resp = "";

        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '<') {
                while (linha.charAt(i) != '>')
                    i++;
            } else {
                resp += linha.charAt(i);
            }
        }

        return resp;
    }

    // ------------------------------------------------------------

    // ------------------------ remover parenteses ----------------------

    public static String removeBrackets(String s) {

        String resp = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                while (s.charAt(i) != ')')
                    i++;
            } else {
                resp += s.charAt(i);
            }
        }

        return resp;
    }

    // ------------------------------------------------------------

    // -------------------------- imprimir ------------------------

    public void print() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        MyIO.println(getNome() + getTitulo_Original() + " " + sdf.format(getData_de_Lancamento()) + " "
                + getDuracao() + " " + getGenero() + " " + getIdioma_Original() + " " + getSituacao() + " "
                + getOrcamento() + " " + getKey_Words());

    }

    // ------------------------------------------------------------

    // -------------------------- leitura ------------------------

    // ----- Nome -----
    public void readNome(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        try {
            String linha = readArq.readLine();

            while (!linha.contains("h2 class")) {
                linha = readArq.readLine();
            }

            linha = readArq.readLine().trim();
            setNome(removeTags(linha));

        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();

    }

    // ----------------

    // ----- Titulo original -----
    public void readTitulo(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {

            while (!linha.contains("Título original")) {

                linha = readArq.readLine();
            }

            this.setTitulo_Original(removeTags(linha.replace("Título original", "").trim()));

        } catch (NullPointerException npe) {
            this.setTitulo_Original(" " + getNome());
        }

        arq.close();

    }

    // ---------------------------

    // ----- Data -----
    public void readData(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {
            while (!linha.contains("class=\"release\"")) {
                linha = readArq.readLine();
            }

            linha = readArq.readLine().trim();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date date = format.parse(removeTags(removeBrackets(linha)));
                this.setData_de_Lancamento(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();

    }

    // ----------------

    // ----- Dura��o -----
    public void readDuracao(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {

            int hrs = 0, min = 0;

            while (!linha.contains("runtime")) {
                linha = readArq.readLine();
            }

            linha = readArq.readLine();
            linha = readArq.readLine().replace(" ", "").trim();

            if (linha.contains("h")) {
                hrs = Integer.parseInt(linha.substring(0, linha.indexOf("h")));
                min = Integer.parseInt(linha.substring((linha.indexOf("h") + 1), (linha.indexOf("m"))));
            } else {
                min = Integer.parseInt(linha.replace(" ", "").substring(0, linha.indexOf("m")).trim());
            }

            int Duration = min + (hrs * 60);

            this.setDuracao(Duration);

        } catch (StringIndexOutOfBoundsException except) {
            this.setDuracao(2);
        }

        arq.close();

    }
    // -------------------

    // ----- Genero -----
    public void readGenero(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {
            while (!linha.contains("genres")) {
                linha = readArq.readLine();
            }

            linha = readArq.readLine().trim();
            linha = readArq.readLine().trim();

            this.setGenero(removeTags(linha).replace("&nbsp;", ""));

        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();
    }

    // ------------------

    // ----- Idioma original -----
    public void readIdioma(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {
            while (!linha.contains("Idioma original")) {
                linha = readArq.readLine();
            }

            this.setIdioma_Original(removeTags(linha).trim().replace("Idioma original ", ""));

        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();

    }

    // ---------------------------

    // ----- Situação -----
    public void readSituacao(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {
            while (!linha.contains("<bdi>Situação")) {
                linha = readArq.readLine();
            }

            this.setSituacao(removeTags(linha).trim().replace("Situação ", ""));
        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();
    }

    // ---------------------------

    // ----- Orçamento -----
    public void readOrcamento(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        while (!linha.contains("<p><strong><bdi>Orçamento")) {
            linha = readArq.readLine();
        }

        linha = linha.trim();
        linha = removeTags(linha);
        linha = linha.replace("Orçamento", "");
        linha = linha.substring(1);
        linha = linha.replace("$", "");

        if (linha.length() == 1) {
            linha = "0";
        } else {
            linha = linha.replace(",", "");
        }

        float budget = Float.parseFloat(linha);
        this.setOrcamento(budget);

        arq.close();

    }

    // -------------------

    // ----- Palavras-chave -----
    public void readKey(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        this.Key_Words = new ArrayList<String>();

        String linha = readArq.readLine();

        try {
            while (!linha.contains("Palavras-chave")) {
                linha = readArq.readLine();
            }

            linha = readArq.readLine();

            while (!linha.contains("</ul>")) {
                if (linha.contains("/keyword/")) {
                    linha = removeTags(linha);
                    this.Key_Words.add(removeTags(linha).trim());
                }

                linha = readArq.readLine();

            }

        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();

    }

    // ---------------------------

    // ------------------------------------------------------------
}

public class ArvArv {

    // --------------------------- is FIM -------------------------

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    }

    // ------------------------------------------------------------

    public static void main(String args[]) throws Exception {

        MyIO.setCharset("utf-8");

        // ----- inicializa��o das vari�veis -----
        String s, s2, title;
        int n;
        Arvore arvore = new Arvore();
        long tempo = System.currentTimeMillis();
        // ----------------------------------------

        // ----- primeira parte do pub.in -----
        while (true) {
            s = MyIO.readLine();

            if (isFim(s) == true) { break; }

            Filme movies = new Filme(); 

            movies = new Filme();
            movies.readNome(s);
            movies.readTitulo(s);
            movies.readData(s);
            movies.readDuracao(s);
            movies.readGenero(s);
            movies.readIdioma(s);
            movies.readSituacao(s);
            movies.readOrcamento(s);
            movies.readKey(s);

            arvore.inserir(movies);
        }
        // ------------------------------------

        // ----- segunda parte do pub.in -----
    
        s2 = MyIO.readLine(); // ler a primeira linha da segunda parte
        n = Integer.parseInt(s2); // primeira linha � a qtd de objetos a serem inseridos/removidos

        // --- for das inserções ---
        for (int i = 0; i < n; i++) {

            s2 = MyIO.readLine();
            String linha = s2.substring(0, 1);
            String x = s2.substring(2);
            
            
            // ----- inserir -----
            if (linha.compareTo("I") == 0) {

                Filme filminho = new Filme(); 

                // ----- ler o arquivo -----
                filminho.readNome(x);
                filminho.readTitulo(x);
                filminho.readData(x);
                filminho.readDuracao(x);
                filminho.readGenero(x);
                filminho.readIdioma(x);
                filminho.readSituacao(x);
                filminho.readOrcamento(x);
                filminho.readKey(x);
                // -------------------------

                // ----- inserir -----
                arvore.inserir(filminho);
                // -------------------

            }
            // --------------------

        }
        // ------------------------------------

        // -----------------------------------

        // ----- terceira parte do pub.in -----
        while (true) {
            title = MyIO.readLine();

            if (isFim(title) == true) { break; }

            arvore.pesquisar(title);
        }
        // ------------------------------------

        // ----- arquivo log -----
        Arq.openWrite("748190_arvoreArvore.txt");
        Arq.println("748190\t " + (System.currentTimeMillis() - tempo) + " ms\t");
        Arq.close();
        // -----------------------
    }
}