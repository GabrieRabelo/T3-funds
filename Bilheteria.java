
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
        System.out.println("Bem-vindo ao " + umTeatro.getNome());
        do{
            System.out.println("\nEscolha uma opcao: ");
            System.out.println("1 - Mostrar poltronas livres");
            System.out.println("2 - Fila com poltronas contiguas");
            System.out.println("3 - Fila com mais poltronas livres");
            System.out.println("4 - Valor arrecadado");
            System.out.println("5 - Vender ingresso");
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch(opcao){
                case 1 : buscaLivres(umTeatro); break;
                case 2 : buscaContiguas(umTeatro); break;
                case 3 : buscaFilaMaisLivre(umTeatro); break;
                case 4 : buscaValorArrecadado(umTeatro); break;
                case 5 : vendeIngresso(umTeatro); break;
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

        if(contiguas==null){
            System.out.println("Não há filas com este número de poltronas contíguas.");
            return;
        }

        System.out.println("Filas com " + n + " ou mais poltronas contiguas no setor " + setor + ":\n");

        String letras ="ABCDEFGHIJKL";

        for(int i=0; i<contiguas.length;i++){
            if(i!=0 && i%10==0) System.out.println("\n");
            System.out.println(letras.charAt(i) + " ");
        }
        System.out.println("\n");
    }

    public static void buscaFilaMaisLivre(Teatro t){
        if(t.teatroLotado()) { 
            System.out.println("O teatro esta lotado."); 
            return; 
        }
        
        Scanner input = new Scanner(System.in);
        String letras ="ABCDEFGHIJKL";
        int setor;
        do{
            System.out.println("\nEscolha o setor desejado: ");
            System.out.println("0 - Norte\n1 - Leste\n2 - Oeste\n3 - Sul");
            setor = input.nextInt();
            if(setor<0 || setor>3) System.out.println("Setor invalido");
            else if(t.setorLotado(setor)) System.out.println("Setor lotado");
        }while(setor<0 || setor>3 || t.setorLotado(setor));

        System.out.println("\nA fila mais livre do setor " + setor + " é: " + letras.charAt(t.buscaFilaMaisLivre(setor)));
    }

    public static void buscaValorArrecadado(Teatro t){
        System.out.println("\nValor arrecadado até agora: R$" + t.calculaBilheteria());
        
    }

    public static void vendeIngresso(Teatro t){
        if(t.teatroLotado()) { System.out.println("Teatro lotado."); return; }
        Scanner input = new Scanner(System.in);
        
        Poltrona p;
        String id;
        int quant;
        do{
            
            buscaLivres(t);
            do{
                System.out.println("Quantos ingressos deseja comprar?");
                quant = input.nextInt();
                if(quant == 0) return;
                if(quant>9) System.out.println("Para comprar mais que nove ingressos repita a compra com menos quantidade.");
            }while(quant < 0 || quant > 9);

            System.out.println("Digite o id da poltrona EX: B6. 0 para sair");
            id = input.next().toUpperCase();
            if(id.equals("0")) return;
            boolean compra = t.reservaPoltrona(id, quant);
            if (compra == false) System.out.println("Poltrona ocupada ou nao encontrada.");
                else {
                    System.out.print("Compra realizada com sucesso");
                    return;
                }
        }while(true);
    }
}