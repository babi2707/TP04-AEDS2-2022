/****************************************
 * 
 * @author Barbara Luciano Araujo
 * Matricula: 748190
 * TP04 - Quest?o 3 - Arvore AVL em Java
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
    
    public Filme filme; // conte?do do no
    public No esq, dir; // filhos da esq e dir
    public int nivel; // n?mero de n?veis abaixo do no

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public No (Filme filme) {
        this(filme, null, null, 1);
    }

    public No (Filme filme, No esq, No dir, int nivel) {
        this.filme = filme;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }

    // ------------------------------------------------------------

    // ----------------------- calcular nivel -----------------------

    public void setNivel () {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    public static int getNivel (No no) {
        return (no == null) ? 0 : no.nivel;
    }

    // ---------------------------------------------------------------
}

class Arvore {

    // ------------------------- atributos -------------------------
    
    private No raiz;

    // -------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public Arvore() {
        raiz = null;
    }

    // ------------------------------------------------------------

    // ----------------------- pesquisar -----------------------

    public boolean pesquisar (String x) {
        MyIO.println(x);
        MyIO.print("raiz");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar (String x, No i) {

        boolean resp;

        if (i == null) {
            MyIO.println(" NAO");
            resp = false;
        } else if (x.compareTo(i.filme.getTitulo_Original()) == i.filme.getTitulo_Original().compareTo(x)) {
            MyIO.println(" SIM");
            resp = true;
        } else if (x.compareTo(i.filme.getTitulo_Original()) < i.filme.getTitulo_Original().compareTo(x)) {
            MyIO.print(" esq");
            resp = pesquisar(x, i.esq);
        } else {
            MyIO.print(" dir");
            resp = pesquisar(x, i.dir);
        }

        return resp;
    }

    // ---------------------------------------------------------

    // ----------------------- inserir ----------------------- 

    public void inserir(Filme x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(Filme x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x.getTitulo_Original().compareTo(i.filme.getTitulo_Original()) < i.filme.getTitulo_Original().compareTo(x.getTitulo_Original())) {
            i.esq = inserir(x, i.esq);
        } else if (x.getTitulo_Original().compareTo(i.filme.getTitulo_Original()) > i.filme.getTitulo_Original().compareTo(x.getTitulo_Original())) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }

        return balancear(i);
    }

    // -------------------------------------------------------

    // ----------------------- remover -----------------------

    public void remover (String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover (String x, No i) throws Exception {
        

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.filme.getTitulo_Original()) < i.filme.getTitulo_Original().compareTo(x)) {
            i.esq = remover(x, i.esq);

        } else if (x.compareTo(i.filme.getTitulo_Original()) > i.filme.getTitulo_Original().compareTo(x)) {
            i.dir = remover(x, i.dir);

        } else if (i.dir == null) {
            i = i.esq;

        } else if (i.esq == null) {
            i = i.dir;

        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return balancear(i);
    }

    // -------------------------------------------------------

    // ----------------------- maior esquerda -----------------------

    private No maiorEsq (No i, No j) {

        if (j.dir == null) {
            i.filme = j.filme;
            j = j.esq;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    } 

    // ---------------------------------------------------------------

    // ----------------------- balancear -----------------------

    private No balancear (No no) throws Exception {

        if (no != null) {
            int fator = No.getNivel(no.dir) - No.getNivel(no.esq);

            // ----- se balanceada -----
            if (Math.abs(fator) <= 1) {
                no.setNivel();
            }
            // -------------------------

            // ----- se desbalanceada para direita -----
            else if (fator == 2) {
                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);

                // --- filho da direita tamb?m desbalanceado ---
                if (fatorFilhoDir == -1) { no.dir = rotacionarDir(no.dir); }
                // ---------------------------------------------

                no = rotacionarEsq(no);
            }
            // -----------------------------------------

            // ----- se desbalanceada para esquerda -----
            else if (fator == -2) {
                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);

                // --- filho da esquerda tamb?m desbalanceado ---
                if (fatorFilhoEsq == 1) { no.esq = rotacionarEsq(no.esq); }
                // ----------------------------------------------

                no = rotacionarDir(no);
            }
            // ------------------------------------------

            else {
                throw new Exception("Erro no No (" + no.filme + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }

        return no;
    } 

    // ----------------------------------------------------------

    // ----------------------- rotacionar -----------------------

    // ---------- simples direita ----------
    private No rotacionarDir (No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;
        no.setNivel();
        noEsq.setNivel();

        return noEsq;
    }
    // -------------------------------------

    // ---------- simples esquerda ----------
    private No rotacionarEsq (No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        no.setNivel();
        noDir.setNivel();

        return noDir;
    }
    // --------------------------------------

    // ----------------------------------------------------------
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

            while (!linha.contains("T??tulo original")) {

                linha = readArq.readLine();
            }

            this.setTitulo_Original(removeTags(linha.replace("T??tulo original", "").trim()));

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

    // ----- Dura??o -----
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

    // ----- Situa????o -----
    public void readSituacao(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        try {
            while (!linha.contains("<bdi>Situa????o")) {
                linha = readArq.readLine();
            }

            this.setSituacao(removeTags(linha).trim().replace("Situa????o ", ""));
        } catch (IOException except) {
            except.printStackTrace();
        }

        arq.close();
    }

    // ---------------------------

    // ----- Or??amento -----
    public void readOrcamento(String arquivo) throws Exception {

        FileReader arq = new FileReader(folder + arquivo);
        BufferedReader readArq = new BufferedReader(arq);

        String linha = readArq.readLine();

        while (!linha.contains("<p><strong><bdi>Or??amento")) {
            linha = readArq.readLine();
        }

        linha = linha.trim();
        linha = removeTags(linha);
        linha = linha.replace("Or??amento", "");
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

    // ----- tudo -----
    public void readAll(String arquivo) throws Exception {
        readNome(arquivo);
        readTitulo(arquivo);
        readData(arquivo);
        readDuracao(arquivo);
        readGenero(arquivo);
        readIdioma(arquivo);
        readSituacao(arquivo);
        readOrcamento(arquivo);
        readKey(arquivo);
    }
    // ----------------
}

public class AVL {

    // --------------------------- is FIM -------------------------

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    }

    // ------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        MyIO.setCharset("utf-8");

        // ----- inicializa??o das vari?veis -----
        String s;
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
            movies.readAll(s);

            arvore.inserir(movies);
        }
        // ------------------------------------

        // ----- segunda parte do pub.in -----

        String s2 = "";
        s2 = MyIO.readLine(); // ler a primeira linha da segunda parte
        n = Integer.parseInt(s2); // primeira linha ? a qtd de objetos a serem inseridos/removidos

        // --- for das inser??es e remo??es ---
        for (int i = 0; i < n; i++) {

            s2 = MyIO.readLine();
            String linha = s2.substring(0, 1);
            String x = s2.substring(2);
            
            
            // ----- inserir -----
            if (linha.compareTo("I") == 0) {

                Filme filminho = new Filme(); 

                // ----- ler o arquivo -----
                filminho.readAll(x);
                // -------------------------

                // ----- inserir -----
                arvore.inserir(filminho);
                // -------------------

            }
            // --------------------

            // ----- remover -----

            else if (linha.compareTo("R") == 0) {

                //arvore.remover(x);

            }

            // --------------------------

        }
        // ------------------------------------

        // -----------------------------------

        // ----- terceira parte do pub.in -----
        String title = "";
        while (true) {
            title = MyIO.readLine();

            if (isFim(title) == true) { break; }

            arvore.pesquisar(title);
        }
        // ------------------------------------

        // ----- arquivo log -----
        Arq.openWrite("748190_avl.txt");
        Arq.println("748190\t " + (System.currentTimeMillis() - tempo) + " ms\t");
        Arq.close();
        // -----------------------

    }
}