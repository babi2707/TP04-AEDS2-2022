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
    
    public int elemento; // conte�do do no
    public No esq, dir; // filhos da esq e dir

    // ------------------------------------------------------------

    // ----------------------- construtores -----------------------

    public No (int elemento) {
        this(elemento, null, null);
    }

    public No (int elemento, No esq, No dir) {
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

    public boolean pesquisar (int x) throws Exception {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar (int x, No i) throws Exception {

        boolean resp;

        if (i == null) {
            resp = false;
        } else if (x == i.elemento) {
            resp = true;
        } else if (x < i.elemento) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }

        return resp;
    }

    // ---------------------------------------------------------

    // ----------------------- inserir ----------------------- 

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
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

    // -------------------------------------------------------

    // ----------------------- remover -----------------------

    public void remover (int x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover (int x, No i) throws Exception {
        

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);

        } else if (x > i.elemento) {
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
        int n, num;
        // ----------------------------------------

        Scanner entrada = new Scanner(System.in);

        linha = entrada.nextLine();

        n = Integer.parseInt(linha.trim()); // qtd de casos

        for (int i = 0; i < n; i++) {
            Arvore arvore = new Arvore();

            linha = entrada.nextLine().trim();
            num = Integer.parseInt(linha); // qtd de números para serem inseridos na árvore
            String aux = entrada.nextLine().trim(); // números a ser inseridos
            String[] arv = aux.split(" "); // array para guardar os números

            for (int j = 0; j < num; j++) {
                arvore.inserir(Integer.parseInt(arv[j]));
            }

            System.out.println("Case " + (i + 1) + ":");
            System.out.print("Pre.: ");
            arvore.caminharPre();
            System.out.println();
            System.out.print("In..: ");
            arvore.caminharCentral();
            System.out.println();
            System.out.print("Post: ");
            arvore.caminharPos();
            System.out.println();
            System.out.println();

        }

        entrada.close();

    }
}