import java.util.HashMap;

public class Craft{



    }


class Production extends Craft{
    String name;
    int factor;
    int totalOutput;
    int totalInput;
    int energyRequired;
    int quantity;




    Production(String name, int factor, int energyRequired, Integer quantity){
        this.name = name;
        this.factor =  factor;
        this.energyRequired = energyRequired;
        this.quantity = quantity;


    }

    public Production(String name, int energyRequired, Integer quantity) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.quantity= quantity;
    }





    public int getFactor() {
        return factor;
    }

    public String getName() {
        return name;
    }






    static public HashMap<ProductionEnum, Production> inProductionList(){
        HashMap<ProductionEnum, Production> productionList = new HashMap<>();
        Production mk1 = new Production("Mineur Mk.1", 1,5,0);
        productionList.put(ProductionEnum.MINOR_MK1, mk1);

        Production mk2 = new Production("Mineur Mk.2",2, 12,0);
        productionList.put(ProductionEnum.MINOR_MK2, mk2);

        Production mk3 = new Production("Mineur Mk.3", 4,30,0);
        productionList.put(ProductionEnum.MINOR_MK3, mk3);

        Production smelter = new Production("Smelter",4,0);
        productionList.put(ProductionEnum.SMELTER, smelter);

        Production constructor = new Production("Constructor",4,0);
        productionList.put(ProductionEnum.CONSTRUCTOR, constructor);

        return productionList;
    }




//Calcul de l'output total des Smelters
    int getMinorOutput( int nbGisement, Node node, Production mineur){

       mineur.totalOutput = mineur.totalOutput + ( node.getQuantity() * mineur.getFactor() );


        return mineur.totalOutput;
    }

    //Calcul de l'énergie utilisée
int getpowerNeeded(Production structure){
        int powerNeeded = 0;

        powerNeeded = powerNeeded + structure.energyRequired * structure.quantity;

        return powerNeeded;
}

void getAllSmelterInfo(Ores ore, Production smelter, Item item){

smelter.getSmelterquantity(ore,smelter,item);

System.out.println("Il vous faudra " + smelter.quantity + " Smelter. Et une capacité de " + smelter.getpowerNeeded(smelter) + " MW.");

smelter.getSmelterOutput(smelter,ore, item);
    System.out.println("Production : " + smelter.totalOutput);

}



//Calcul de la quantité de Smelter
int getSmelterquantity(Ores ore, Production smelter, Item item){

        smelter.quantity = smelter.quantity + ore.quantity / item.inputItem;
        return smelter.quantity;
}


int getSmelterOutput(Production smelter,Ores ore, Item item){
        smelter.totalOutput = smelter.totalOutput + smelter.quantity * item.output;
        return smelter.totalOutput;
}



int getConstructorQuantity(Production smelter,Production constructor, Item item){
        int nbconstructor = 0;
        constructor.quantity = constructor.quantity + smelter.totalOutput/item.inputItem;

        return nbconstructor;
}


}

class Node extends Craft{
    String name;
    int quantity;

    Node(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    void getObject(Node node, Node node1){
        node.name = node1.getName();
        node.quantity = node1.getQuantity();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {

        return quantity;
    }

    static public HashMap<NodeEnum, Node> inNodeList(){
        HashMap<NodeEnum, Node> nodeList = new HashMap<>();

        Node impureNode = new Node("Impure Node",30);
        nodeList.put(NodeEnum.IMPURE, impureNode);
        Node normalNode = new Node("Normal Node",60);
        nodeList.put(NodeEnum.NORMAL, normalNode);
        Node pureNode = new Node("Pure Node",120);
        nodeList.put(NodeEnum.PURE, pureNode);

        return  nodeList;
    }


}
