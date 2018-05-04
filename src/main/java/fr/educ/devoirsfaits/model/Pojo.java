package fr.educ.devoirsfaits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Pojo {

    @Id
    @GeneratedValue
    private int id;
    private String first_name;
    private String last_name;
    private String password;

    public Pojo(int id, String first_name, String last_name, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }

    public Pojo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
