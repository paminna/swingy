package model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author RMNurgalieva
 */
public class Artifact {

    @NotNull
    private int id;

    @Size(min = 3)
    private String photo;
    private String type;
    private int defence;
    private int attack;
    private int hitPoints;

    public Artifact(int id, String photo, String type, int defence, int attack, int hitPoints) {
        this.id = id;
        this.photo = photo;
        this.type = type;
        this.defence = defence;
        this.attack = attack;
        this.hitPoints = hitPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
