package Control;

import Entity.Combatant.Player.Player;
import Entity.Item.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemInteract{
    static public Item decideItem(Player user){
        ArrayList<Item> items = user.getInventory();
        int i = 1;
        for (Item item: items){
            System.out.println(i++ + ". " + item.getName() + " : " + item.getDescription());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Pick an item: ");
        int choice = sc.nextInt();
        while (choice > items.size() || choice < 1){
            System.out.println("Pick an available item: ");
            choice = sc.nextInt();
        };
        sc.close();
        return items.get(choice-1);
    }
}