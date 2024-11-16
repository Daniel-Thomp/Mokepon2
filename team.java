import java.util.ArrayList;

public class team {
    Mokepon[] monsters = new Mokepon[7];
    Mokepon inBattle = new Mokepon();

    ArrayList<String> bag = new ArrayList<>();
    ArrayList<Integer> bagAmount = new ArrayList<>();
    
    
    public team(){
        for (int i = 1; i < monsters.length; i++) {
            monsters[i] = new Mokepon();
        }
    }
    public void add2Bag(String item, int quantity){
        boolean newItem = true;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i) == item) {
                bagAmount.set(i, bagAmount.get(i)+quantity);
                newItem = false;
            }
        }
        if (newItem) {
            bag.add(item);
            bagAmount.add(quantity);
        }
    }
    public boolean add2team(Mokepon mon){
        boolean searching = true;
        int i = 1;
        while (searching && i<7) {
            if (monsters[i].name == "--") {
                searching = false;
            } else {
                i++;
            }
        }
        if (!searching) {
            monsters[i] = mon;
            Main.animator.text(mon.name + " was added to your party");
            return true;
        } else {
            System.out.println("Party full oh shiz");
            return false;
        }
    }
    
}
