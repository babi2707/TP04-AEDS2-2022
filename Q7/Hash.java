/****************************************
 * 
 * @author Barbara Luciano Araujo
 * Matricula: 748190
 * TP04 - Quest�o 7 - Tabela Hash Indireta 
 * com Lista Simples em Java
 * 
****************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;

class Celula {

    // ---------- atributos ----------

    public Filme filme;
    public Celula prox;

    // -------------------------------

    // ---------- construtores ----------

    public Celula() {
        this.prox = null;
    }

    public Celula(Filme filme) {
        this.filme = filme;
        this.prox = null;
    }

    public Celula(Filme filme, Celula prox) {
        this.filme = filme;
        this.prox = prox;
    }

    // ----------------------------------
}

class Lista {

    // ---------- atributos ----------

    private Celula primeiro, ultimo;

    // -------------------------------

    // ---------- construtores ----------

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    // ----------------------------------

    // ---------- inserir ----------

    public void inserir (Filme x) {

        Celula tmp = new Celula(x);

        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo) {
            ultimo = tmp;
        }

        tmp = null;

    }

    // -------------------------------

    // ---------- pesquisar ----------

    public boolean pesquisar(String x, int pos) {
        boolean resp = false;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.filme.getTitulo_Original().compareTo(x) == x.compareTo(i.filme.getTitulo_Original())) {
                MyIO.println("Posicao: " + pos);
                resp = true;
                i = ultimo;
            }
        }

        return resp;
    }

    // -----------------------------

}

class HashIndireta {

    // ------------------------- atributos -------------------------
    Lista[] tabela;
    int m;
    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------
    public HashIndireta() {}

    public HashIndireta(int m) {
        this.m = m;
        this.tabela = new Lista[this.m];

        for (int i = 0; i < m; i++){
            tabela[i] = new Lista();
        }

    }
    // ------------------------------------------------------------

    public int h (String elemento) {
        int soma = 0;

        for (char x : elemento.toCharArray()) { 
            soma += x; 
        }

        return soma % m;
    }

    // ----------------------- inserir -----------------------

    public void inserir (Filme elemento) {
        int pos = h(elemento.getTitulo_Original());

        tabela[pos].inserir(elemento);
    }

    // -------------------------------------------------------

    // ----------------------- pesquisar -----------------------
    
    public boolean pesquisar (String elemento) {
        int pos = h(elemento);

        return tabela[pos].pesquisar(elemento, h(elemento));
    }

    // ---------------------------------------------------------

    // ----------------------- rehash -----------------------

    public int rehash (String elemento) {
        int soma = 0;

        for(char x : elemento.toCharArray()) {
            soma += x;
        }

        return ++soma % m;
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

public class Hash {

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
        HashIndireta table = new HashIndireta(21);
        long tempo = System.currentTimeMillis();
        // ----------------------------------------

        // ----- primeira parte do pub.in -----
        while (true) {
            s = MyIO.readLine();

            if (isFim(s) == true) { break; }

            Filme movies = new Filme(); 

            movies.readAll(s);

            table.inserir(movies);
        }
        // ------------------------------------

        // -----------------------------------

        // ----- segunda parte do pub.in -----
        String title;
        while (true) {
            title = MyIO.readLine();

            if (isFim(title) == true) { break; }

            MyIO.println("=> " + title);

            if (table.pesquisar(title) == false) {
                MyIO.println("NAO");
            }
        }
        // -----------------------------------

        // ----- arquivo log -----
        Arq.openWrite("748190_hashIndireta.txt");
        Arq.println("748190\t " + (System.currentTimeMillis() - tempo) + " ms\t");
        Arq.close();
        // -----------------------

    }
}