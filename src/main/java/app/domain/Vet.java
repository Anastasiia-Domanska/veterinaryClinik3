package app.domain;

import java.util.Objects;

public class Vet {

    private int id;
    private String name;
    private String role;  // должность
    private boolean active;

    public Vet(int id, String name, String role, boolean active) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.active = active;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        Vet vet = (Vet) o;
        return id == vet.id && active == vet.active && Objects.equals(name, vet.name) && Objects.equals(role, vet.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, active);
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
