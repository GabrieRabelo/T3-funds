
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
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch(opcao){
                case 0 : break;
                case 1 : buscaLivres(umTeatro); break;
                case 2 : buscaContiguas(umTeatro); break;
                case 3 :
                case 4 :
                case 5 :
                default : System.out.println("Opção inválida");
            }
        }while(opcao<0 || opcao>5);
        input.close();
    }

    private static void buscaLivres(Teatro t){
        if(t.teatroLotado()) { 
            System.out.println("O teatro está lotado."); 
            return; 
        }

        Scanner input = new Scanner(System.in);
        
        int setor;
        do{
            System.out.println("\nEscolha o setor desejado: ");
            System.out.println("0 - Norte\n1 - Leste\n2 - Oeste\n3 - Sul");
            setor = input.nextInt();
            if(setor<0 || setor>3) System.out.println("Setor inválido");
            else if(t.setorLotado(setor)) System.out.println("Setor lotado");
        }while(setor<0 || setor>3 || t.setorLotado(setor));

        Poltrona[] pLivres = t.buscaPoltronas(setor);

        System.out.println("Poltronas livres no setor " + setor + ":\n");

        for(int i=0; i<pLivres.length;i++){
            if(i != 0 && i % 10 == 0) System.out.println("\n");
            System.out.print(pLivres[i].getIdentificacao() + " ");
        }
        System.out.println("\n");
        input.close();
        return;
    }
    
    private static void buscaContiguas(Teatro t){
        if(t.teatroLotado()) { 
            System.out.println("O teatro está lotado."); 
            return; 
        }

        Scanner input = new Scanner(System.in);
        
        int setor;
        int n;
        do{
            System.out.println("\nEscolha o setor desejado: ");
            System.out.println("0 - Norte\n1 - Leste\n2 - Oeste\n3 - Sul");
            setor = input.nextInt();
            System.out.println("Informe o número de poltronas desejadas");
            n = input.nextInt();
            if(setor<0 || setor>3) System.out.println("Setor inválido");
            else if(t.setorLotado(setor)) System.out.println("Setor lotado");
        }while(setor<0 || setor>3 || t.setorLotado(setor));

        int[] contiguas = t.buscaPoltronasContiguas(setor, n);

        System.out.println("Filas com " + n + " ou mais poltronas contíguas no setor " + setor + ":\n");

        for(int i=0; i<contiguas.length;i++){
            if(i != 0 && i % 10 == 0) System.out.println("\n");
            System.out.print(contiguas[i] + " ");
        }
        System.out.println("\n");
        input.close();
    }

}