/****************************************
 * 
 * @author Barbara Luciano Araujo
 * Matricula: 748190
 * TP04 - Questão 9 - Operações ABP em Java
 * 
****************************************/

import java.util.Scanner;
import java.io.IOException;

class No {

    // ------------------------- atributos -------------------------
    
    public String elemento; // conte�do do no
    public No esq, dir; // filhos da esq e dir

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public No (String elemento) {
        this(elemento, null, null);
    }

    public No (String elemento, No esq, No dir) {
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

    public Arvore() {
        raiz = null;
    }

    // ------------------------------------------------------------

    // ----------------------- pesquisar -----------------------

    public boolean pesquisar (String x) throws Exception {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar (String x, No i) throws Exception {

        boolean resp;

        if (i == null) {
            resp = false;
        } else if (x.compareTo(i.elemento) == i.elemento.compareTo(x)) {
            resp = true;
        } else if (x.compareTo(i.elemento) < i.elemento.compareTo(x)) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }

        return resp;
    }

    // ---------------------------------------------------------

    // ----------------------- inserir ----------------------- 

    public void inserir(String x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(String x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x.compareTo(i.elemento) < i.elemento.compareTo(x)) {
            i.esq = inserir(x, i.esq);
        } else if (x.compareTo(i.elemento) > i.elemento.compareTo(x)) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    // -------------------------------------------------------

    // ----------------------- remover -----------------------

    public void remover (String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover (String x, No i) throws Exception {
        

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.elemento) < i.elemento.compareTo(x)) {
            i.esq = remover(x, i.esq);

        } else if (x.compareTo(i.elemento) > i.elemento.compareTo(x)) {
            i.dir = remover(x, i.dir);

        } else if (i.dir == null) {
            i = i.esq;

        } else if (i.esq == null) {
            i = i.dir;

        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    // -------------------------------------------------------

    // ----------------------- caminhar central -----------------------

    public void caminharCentral () {
        caminharCentral(raiz);
    }

    private void caminharCentral (No i) {
       if (i != null) {
           caminharCentral(i.esq); // elementos da esquerda
           System.out.print(i.elemento + " "); // conteúdo do nó
           caminharCentral(i.dir); // elementos da direita
       }
    }

    // ----------------------------------------------------------------

    // ----------------------- caminhar pre -----------------------

    public void caminharPre () {
        caminharPre(raiz);
    }

    private void caminharPre (No i) {
       if (i != null) {
        System.out.print(i.elemento + " "); // conteúdo do nó
        caminharPre(i.esq); // elementos da esquerda
        caminharPre(i.dir); // elementos da direita
       }
    }

    // -------------------------------------------------------------

    // ----------------------- caminhar pos -----------------------

    public void caminharPos () {
        caminharPos(raiz);
    }

    private void caminharPos (No i) {
       if (i != null) {
        caminharPos(i.esq); // elementos da esquerda
        caminharPos(i.dir); // elementos da direita
        System.out.print(i.elemento + " "); // conteúdo do nó
       }
    }

    // -------------------------------------------------------------

    // ----------------------- maior esquerda -----------------------

    private No maiorEsq (No i, No j) {

        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    } 

    // ---------------------------------------------------------------
}

public class ABP {

    public static void main(String args[]) throws Exception {

        // ----- inicialização das variáveis -----
        String linha = "";
        // ----------------------------------------

        Scanner entrada = new Scanner(System.in);

        while(entrada.hasNextLine()) {

            Arvore arvore = new Arvore();

            linha = entrada.nextLine();
            String subs = linha.substring(0, 1);

            if ((subs.compareTo("I") == 0) && (linha.length() == 3)) {
                arvore.inserir(linha.substring(2));
            }
        }

        entrada.close();

    }
}