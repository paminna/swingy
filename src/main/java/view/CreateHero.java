package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Hero;

/**
 * @author RMNurgalieva
 */
public class CreateHero {
    public static ArrayList<Hero> heroes;
    public static int current_hero = 0;

    static {
        Hero hero1 = new Hero("Hero1", "Warrior", 13, 5, 7,
                "/Users/regina/Desktop/swingy/src/main/resources/hero/murshmello_front.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/murshmello_left.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/murshmello_right.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/murshmello_back.png",
                0, 0);
        Hero hero2 = new Hero("Hero2", "Warrior", 20, 3, 10,
                "/Users/regina/Desktop/swingy/src/main/resources/hero/girl_front.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/girl_left.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/girl_right.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/girl_back.png",
                0, 0);
        Hero hero3 = new Hero("Hero3", "Warrior", 8, 6, 9,
                "/Users/regina/Desktop/swingy/src/main/resources/hero/elf_front.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/elf_left.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/elf_right.png",
                "/Users/regina/Desktop/swingy/src/main/resources/hero/elf_back.png",
                0, 0);
        heroes = new ArrayList<Hero>();
        heroes.add(hero1);
        heroes.add(hero2);
        heroes.add(hero3);
        current_hero = 0;
    }

    public static ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public static int getCurrent_hero() {
        return current_hero;
    }
}
