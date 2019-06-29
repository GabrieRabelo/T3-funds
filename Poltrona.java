/**
 * Write a description of class Poltrona here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Poltrona{
    private String identificacao;
    private int setor;
    private boolean ocupada;
    
    /**
     * Esse método constrói uma poltrona para o teatro, com id, setor e se está ocupada.(Não ocupada por padrão)
     * @param identificacao identificação é letra/numero indicando sua cardinalidade na matriz (ex: A1, B3 etc..)
     * @param setor setor indica qual setor do teatro a poltrona se encontra, sendo que cada setor há diferença de preços, sendo abstraído por pontos cardeais (Ex: Norte)
     */
    public Poltrona(String identificacao, int setor){
        this.identificacao = identificacao;
        this.setor = setor;
        ocupada = false;
    }
    
    public String getIdentificacao(){ return identificacao; }
    public int getSetor(){ return setor;}
    public boolean estaOcupada(){ return ocupada;}
    
    public void reserva(){
        ocupada = true;
    }
    public void libera(){
        ocupada = false;
    }
    public String toString(){
        if(ocupada) return setor +"O";
        return setor + "L";
    }
}
