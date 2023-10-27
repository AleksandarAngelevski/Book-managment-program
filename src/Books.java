public class Books {
    private int inventarenBroj;
    private String ime;
    private String avtor;
    private String izdavatel;
    private String godinaNaIzdavanje;
    private int cena;

    @Override
    public String toString() {
        return "Ja vnesovte knigata "+ime+" od "+avtor;
    }

    public Books(int inventarenBroj, String ime, String avtor, String izdavatel, String godinaNaIzdavanje, int cena) {
        this.inventarenBroj = inventarenBroj;
        this.ime = ime;
        this.avtor = avtor;
        this.izdavatel = izdavatel;
        this.godinaNaIzdavanje = godinaNaIzdavanje;
        this.cena = cena;
    }
    public Books(){}
    public int getInventarenBroj() {
        return inventarenBroj;
    }

    public String getIme() {
        return ime;
    }

    public String getAvtor() {
        return avtor;
    }

    public String getIzdavatel() {
        return izdavatel;
    }

    public String getGodinaNaIzdavanje() {
        return godinaNaIzdavanje;
    }

    public int getCena() {
        return cena;
    }
}
