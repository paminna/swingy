package model;

import javax.validation.constraints.NotNull;

public class Hero {

    @NotNull
    private String name;
    private String heroClass;

    @NotNull
    private int level;

    @NotNull
    private int experience;

    @NotNull
    private int attack;

    @NotNull
    private int defence;

    @NotNull
    private int hitPoints;
    private Artifact weapon;
    private Artifact armor;
    private Artifact helmet;
    private String photoProfile;
    private String leftSide;
    private String rightSide;
    private String back;
    private String currentPhoto;


    @NotNull
    private int x;

    @NotNull
    private int y;

    public String getCurrentPhoto() {
        return currentPhoto;
    }

    public void setCurrentPhoto(String currentPhoto) {
        this.currentPhoto = currentPhoto;
    }

    public Hero(String name, String heroClass, int attack, int defence, int hitPoints, String photoProfile,
                String leftSide, String rightSide, String back, int x, int y, int experience, int level) {
        this.name = name;
        this.heroClass = heroClass;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.photoProfile = photoProfile;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.back = back;
        this.x = x;
        this.y = y;
        this.currentPhoto = photoProfile;
        this.experience = experience;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Artifact getWeapon() {
        return weapon;
    }

    public Artifact getHelmet() {
        return helmet;
    }

    public Artifact getArmor() {
        return armor;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    public String getBack() {
        return back;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int expirience) {
        this.experience = expirience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setWeapon(Artifact weapon) {
        this.weapon = weapon;
    }

    public void setHelmet(Artifact helmet) {
        this.helmet = helmet;
    }

    public void setArmor(Artifact armor) {
        this.armor = armor;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
