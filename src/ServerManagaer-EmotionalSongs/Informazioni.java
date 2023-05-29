import java.io.Serializable;

public class Informazioni implements Serializable{
    private static final long serialVersionUID = 1;

    private String Nome;
    private int età;
    private boolean verità;
    public Informazioni(){}

    public Informazioni(String nome,int età){
        this.Nome = nome;
        this.età = età;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome){
        this.Nome = nome;
    }

    public int getEtà() {
        return età;
    }

    public void setEtà(int età){
        this.età = età;
    }

    public boolean getVerità() {
        return verità;
    }

    public void setVerità(Boolean verità){
        this.verità = verità;
    }

    public void display(){
        System.out.println();
        System.out.println(Nome + età);
    }
   
}
