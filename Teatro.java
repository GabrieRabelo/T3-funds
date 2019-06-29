/**
 * Write a description of class Bilheteria here.
 *
 * @author (Gabriel)
 * @version (v1)
 */
public class Teatro{
    private String nomeSala;
    private Poltrona[][] sala;
   
    /**
    * Este método constrói uma matriz de poltronas, inicializando todas poltronas do teatro, nulas por padrão e inicializadas por um metodo.
    * @param nomeSala
    */
    public Teatro(String nomeSala){
            this.nomeSala = nomeSala;
            sala = new Poltrona[12][20];
            inicializa();
    }

    public String getNome(){
        return nomeSala;
    }

    /**
    * Este método inicializa as poltronas do teatro, indicando seu setor dependendo da posição em que ela se encontra.
    */
    public void inicializa(){
            String letras ="ABCDEFGHIJKL";
            int setor;
            for(int i=0; i<sala.length; i++){
                if(i<=2) setor = 0;
                else setor = 3;
                for(int j=0; j<sala[i].length; j++){
                    if(i>2 && i<8){
                        if(j<=9) setor = 1;
                        else setor = 2;
                    }
                    sala[i][j] = new Poltrona(""+letras.charAt(i)+j,setor);
                }
            }
    }

    public boolean setorLotado(int setor){
        for(int i=0; i<sala.length;i++){
            for(int j=0; j<sala[i].length;j++){
                if(setor==sala[i][j].getSetor()){
                    if(!(sala[i][j].estaOcupada())) return false;
                }
            }
        }
        return true;
    }

    public boolean teatroLotado(){
        for(int i=0; i<sala.length;i++){
            for(int j=0; j<sala[i].length;j++){
                    if(!(sala[i][j].estaOcupada())) return false;
            }
        }
        return true;
    }
    
    public Poltrona[] buscaPoltronas(int setor){
        if(setor<0 || setor>3) return null;
        int cont=0;
        for(int i=0;i<sala.length;i++){
            for(int j=0;j<sala[i].length;j++){
                if(setor==sala[i][j].getSetor()){
                    if(!sala[i][j].estaOcupada()) cont++;
                }
            }
        }

        Poltrona[] livres = new Poltrona[cont];
        int k=0;
        for(int i=0;i<sala.length;i++){
            for(int j=0;j<sala[i].length;j++){
                if(setor==sala[i][j].getSetor()){
                    if(!sala[i][j].estaOcupada()){
                        livres[k]=sala[i][j];
                        k++;
                    }
                }
            }
        }
        return livres;
    }

    public String toString(){
        String letras ="ABCDEFGHIJKL";
        String msg = "Sala de Teatro " + nomeSala+ "\n   ";
        for(int i=0; i<sala[0].length; i++){ 
            if(i<9) msg = msg + (i+1) + "    ";
            else msg = msg + (i+1) + "   ";
            }
        msg = msg + "\n";
        for(int i=0; i<sala.length; i++){
            msg = msg + letras.charAt(i) + "  ";
            for(int j=0; j<sala[i].length; j++){
                msg = msg + sala[i][j] + "   ";
            }
            msg = msg + "\n";
        }
        return msg;
   }
}
