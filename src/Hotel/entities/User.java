package Hotel.entities;

import Hotel.entities.Role.Role;

import java.util.UUID;

public class User {
    private final String Id = UUID.randomUUID().toString();
    private String name;
    private String password;
    private String tel_number;
    private Kard Kard;
    Role role;

    public User(){}

    public User(String name, String password, String tel_number, Hotel.entities.Kard kard, Role role) {
        this.name = name;
        this.password = password;
        this.tel_number = tel_number;
        Kard = kard;
        this.role = role;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public Hotel.entities.Kard getKard() {
        return Kard;
    }

    public void setKard(Hotel.entities.Kard kard) {
        Kard = kard;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tel_number='" + tel_number + '\'' +
                ", Kard=" + Kard +
                ", role=" + role +
                '}';
    }
}

