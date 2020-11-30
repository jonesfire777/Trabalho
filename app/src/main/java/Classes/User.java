package Classes;

public class User {
    public String nome;
    public String pedido;
    public String idade;

    public User(){

    }

    public User(String nome, String pedido, String idade){
        this.nome = nome;
        this.pedido = pedido;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
