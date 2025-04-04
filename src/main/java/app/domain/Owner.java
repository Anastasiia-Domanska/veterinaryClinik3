package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Owner {

    private int id;
    private String name;
    private String mail;
    private String phone;
    private boolean active;
    private List<Pet> pets = new ArrayList<>();

    public Owner() {
    }

    public Owner(int id, String name, String mail, String phone, boolean active, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.active = active;
        this.pets = pets;
    }

    public Owner(String name, String phone, String mail) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public Owner(int id, String mail, String phone) {
        this.id = id;
        this.mail = mail;
        this.phone = phone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id && active == owner.active && Objects.equals(name, owner.name) && Objects.equals(mail, owner.mail) && Objects.equals(phone, owner.phone) && Objects.equals(pets, owner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, phone, active, pets);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", pets=" + pets +
                '}';
    }

}
