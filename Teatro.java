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

    /**
     * Este método devolve o nome do teatro
     * @return retorna o atributo nome
     */
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

    /**
     * Este método varre o teatro procurando pelo parâmetro inserido, e verifica se está lotado,
     * na primeira poltrona livre ele retorna falso.
     * @param setor setor informado
     * @return verdadeiro o falso pro estado "lotado".
     */
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

    /**
     * Este método varre o teatro e verifica se está lotado na primeira poltrona livre ele retorna falso.
     * @return verdadeiro o falso pro estado "lotado".
     */
    public boolean teatroLotado(){
        for(int i=0; i<sala.length;i++){
            for(int j=0; j<sala[i].length;j++){
                    if(!(sala[i][j].estaOcupada())) return false;
            }
        }
        return true;
    }

    /**
     * Este método varre a matriz e verifica se está vazia, retornando false na primeira poltrona ocupada.
     * @return true se estiverem todas não ocupadas
     */
    public boolean teatroVazio(){
        for(int i=0; i<sala.length;i++){
            for(int j=0; j<sala[i].length;j++){
                    if(sala[i][j].estaOcupada()) return false;
            }
        }
        return true;
    }

    /**
     * Este método varre o teatro e busca, em um setor informado, suas poltronas livres
     * @param setor setor a ser verificado
     * @return vetor de poltrona indicando sua disponibilidade.
     */
    public Poltrona[] buscaPoltronas(int setor){
        if(setor<0 || setor>3) return null;
        int cont=0;
        for(int i=0;i<sala.length;i++){
            for(int j=0;j<sala[i].length;j++){
                if(sala[i][j].getSetor()==setor){
                    if(!sala[i][j].estaOcupada()) cont++;
                }
            }
        }

        Poltrona[] livres = new Poltrona[cont];
        int k=0;
        for(int i=0;i<sala.length;i++){
            for(int j=0;j<sala[i].length;j++){
                if(sala[i][j].getSetor()==setor){
                    if(!sala[i][j].estaOcupada()){
                        livres[k]=sala[i][j];
                        k++;
                    }
                }
            }
        }
        return livres;
    }

    /**
     * Este método retorna as poltronas contíguas em determinado setor, a partir de um valor minimo informado
     * @param setor Setor a ser varrido
     * @param n Número mínimo de poltronas a devolver
     * @return Retorna a matriz 
     */
    public int[] buscaPoltronasContiguas(int setor, int n){
        int contL=0;
        for(int i=0;i<sala.length;i++){
            int cont=0;
            for(int j=0;j<sala[i].length;j++){
                if(sala[i][j].getSetor()==setor){
                    if(!sala[i][j].estaOcupada()) cont++;
                        else cont=0;
                    if(cont>=n){
                        contL++;
                        break;
                    }
                }
            }

        }
        if(contL == 0) return null;

        int[] contiguas = new int[contL];

        int cont=0, k=0;
        for(int i=0;i<sala.length;i++){
            for(int j=0;j<sala[i].length;j++){
                if(sala[i][j].getSetor()==setor){
                    if(!sala[i][j].estaOcupada()) cont++;
                        else cont=0;
                    if(cont>=n){
                        contiguas[k]=i;
                        k++;
                        break;
                    }
                }
            }
        }
        return contiguas;
    }

    /**
     * Busca a fila com mais poltronas livres no setor desejado
     * @param setor
     * @return retorna o índice da fila que está livre
     */
    public int buscaFilaMaisLivre(int setor){
    int cont, k=0;
        for(int i=0; i<sala.length;i++){
            cont = 0;
            for(int j=0; j<sala[i].length;j++){
                if(sala[i][j].getSetor()==setor){
                    if(!sala[i][j].estaOcupada()){
                        cont ++;
                    }
                }
            }
            if(cont>k) k = i;
        }
        return k;
    }

    public double calculaBilheteria(){
        double total=0;
        if(teatroVazio()) return total;

        for(int i=0;i<sala.length;i++){
            for(int j=0;i<sala[i].length;j++){
                if(sala[i][j].estaOcupada()){
                    int setor = sala[i][j].getSetor();
                    switch(setor){
                        case 0 : total += 150; break;
                        case 1 : total += 80; break;
                        case 2 : total += 75; break;
                        case 3 : total += 50; break;
                    }
                }
            }
        }
        return total;
    }

    /**
     * Este método devolve uma poltrona pela sua identificação
     * @param id identificação a ser buscada
     */
    public Poltrona poltronaID(String id){
        for (int i = 0; i < sala.length; i ++) {
            for (int j = 0; j < sala[i].length; j ++) {
                if (id.equals(sala[i][j].getIdentificacao())) return sala[i][j];
            }
        }
        return null;
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
