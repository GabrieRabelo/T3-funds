
/**
 * Write a description of class Bilheteria here.
 *
 * @author (Gabriel)
 * @version (v1)
 */
import java.util.Scanner;
public class Bilheteria{
    public static void main(String args[]){
        Teatro umTeatro = new Teatro("Tabajara Cultura");
        Scanner input = new Scanner(System.in);
        System.out.println("\f" + umTeatro);
        int opcao;
        do{
            System.out.println("Bem-vindo ao " + umTeatro.getNome() + " \nEscolha uma opcao: ");
            System.out.println("1 - Mostrar poltronas livres");
            System.out.println("2 - Fila com poltronas contiguas");
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch(opcao){
                case 1 : buscaLivres(umTeatro); break;
                case 2 : buscaContiguas(umTeatro); break;
                case 0 : break;
                default : System.out.println("Opção invalida");
            }
        }while(opcao!=0);
    }

    private static void buscaLivres(Teatro t){
        if(t.teatroLotado()) { 
            System.out.println("O teatro esta lotado."); 
            return; 
        }

        Scanner input = new Scanner(System.in);
        
        int setor;
        do{
            System.out.println("\nEscolha o setor desejado: ");
            System.out.println("0 - Norte\n1 - Leste\n2 - Oeste\n3 - Sul");
            setor = input.nextInt();
            if(setor<0 || setor>3) System.out.println("Setor invalido");
            else if(t.setorLotado(setor)) System.out.println("Setor lotado");
        }while(setor<0 || setor>3 || t.setorLotado(setor));

        Poltrona[] pLivres = t.buscaPoltronas(setor);

        System.out.println("Poltronas livres no setor " + setor + ":\n");

        for(int i=0; i<pLivres.length;i++){
            if(i != 0 && i % 10 == 0) System.out.println("\n");
            System.out.print(pLivres[i].getIdentificacao() + " ");
        }
        System.out.println("\n");
        return;
    }
    
    private static void buscaContiguas(Teatro t){
        if(t.teatroLotado()) { 
            System.out.println("O teatro esta lotado."); 
            return; 
        }

        Scanner input = new Scanner(System.in);
        
        int setor;
        int n;
        do{
            System.out.println("\nEscolha o setor desejado: ");
            System.out.println("0 - Norte\n1 - Leste\n2 - Oeste\n3 - Sul");
            setor = input.nextInt();
            System.out.println("Informe o numero de poltronas desejadas");
            n = input.nextInt();
            if(setor<0 || setor>3) System.out.println("Setor invalido");
            else if(t.setorLotado(setor)) System.out.println("Setor lotado");
        }while(setor<0 || setor>3 || t.setorLotado(setor));

        int[] contiguas = t.buscaPoltronasContiguas(setor, n);

        System.out.println("Filas com " + n + " ou mais poltronas contiguas no setor " + setor + ":\n");

        String letras ="ABCDEFGHIJKL";

        for(int i=0; i<contiguas.length;i++){
            if(i != 0 && i % 10 == 0) System.out.println("\n");
            System.out.println(letras.charAt(i) + " ");
        }
    }

}