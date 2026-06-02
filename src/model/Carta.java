package model;

public class Carta {
    private int id;
    private String nome;
    private int forca;
    private int velocidade;
    private int habilidade;
    private int equipamento;
    private int inteligencia;
    private boolean superTrunfo;
    private boolean utilizada;

    public Carta() {
    }

    public Carta(int id, String nome, int forca, int velocidade, int habilidade, int equipamento, int inteligencia, boolean superTrunfo, boolean utilizada) {
        this.id = id;
        this.nome = nome;
        this.forca = forca;
        this.velocidade = velocidade;
        this.habilidade = habilidade;
        this.equipamento = equipamento;
        this.inteligencia = inteligencia;
        this.superTrunfo = superTrunfo;
        this.utilizada = utilizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }


    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }


    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }


    public int getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(int equipamento) {
        this.equipamento = equipamento;
    }


    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }


    public boolean isSuperTrunfo() {
        return superTrunfo;
    }

    public void setSuperTrunfo(boolean superTrunfo) {
        this.superTrunfo = superTrunfo;
    }


    public boolean isUtilizada() {
        return utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }


    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", forca=" + forca +
                ", velocidade=" + velocidade +
                ", habilidade=" + habilidade +
                ", equipamento=" + equipamento +
                ", inteligencia=" + inteligencia +
                ", superTrunfo=" + superTrunfo +
                ", utilizada=" + utilizada +
                '}';
    }
}
