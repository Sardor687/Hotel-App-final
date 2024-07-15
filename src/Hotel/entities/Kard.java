package Hotel.entities;

public class Kard {
    private String Kard_name;
    private String Kard_number;
    private String Yaroqlilik_sana;
    private Double balance;
    public Kard(){}

    public Kard(String kard_name, String kard_number, String yaroqlilik_sana, Double balance) {
        Kard_name = kard_name;
        Kard_number = kard_number;
        Yaroqlilik_sana = yaroqlilik_sana;
        this.balance = balance;
    }

    public String getKard_name() {
        return Kard_name;
    }

    public void setKard_name(String kard_name) {
        Kard_name = kard_name;
    }

    public String getKard_number() {
        return Kard_number;
    }

    public void setKard_number(String kard_number) {
        Kard_number = kard_number;
    }

    public String getYaroqlilik_sana() {
        return Yaroqlilik_sana;
    }

    public void setYaroqlilik_sana(String yaroqlilik_sana) {
        Yaroqlilik_sana = yaroqlilik_sana;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Kard{" +
                "Kard_name='" + Kard_name + '\'' +
                ", Kard_number='" + Kard_number + '\'' +
                ", Yaroqlilik_sana='" + Yaroqlilik_sana + '\'' +
                ", balance=" + balance +
                '}';
    }
}
