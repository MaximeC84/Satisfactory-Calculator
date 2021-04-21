import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;



public class Main {
    public static void main(String[]args){

            Scanner sc= new Scanner(System.in);

            HashMap<String ,Item> bufferList = new HashMap<>();

        Item.displayAllItemList();



        Item chosenItem;

        do {
            System.out.println();
            System.out.println("Quel item voulez vous  ?");

            String itemName = sc.nextLine().toLowerCase();

            //ore.getQuantity(ore, mineur); //OK
            chosenItem = Item.searchInAllItemMap(itemName);
            if (chosenItem == null){
                System.out.println("L'item que vous avez indiqué est incorrect, merci de recommencer.\n");
            }
            System.out.println();
        }while(chosenItem == null);

        bufferList.put("Item", chosenItem);
        Ores oreSource;
        oreSource = chosenItem.getOreSource(chosenItem);



        System.out.println("Combien de gisements de " + oreSource.name + " avez-vous ?");

            int nbGisement = sc.nextInt();
            sc.nextLine();

            Production mineur = null;
            Node gisementChoisi = null;
            Production smelter= Production.inProductionList().get(ProductionEnum.SMELTER);

             boolean again;

            for (int i = 1; i <= nbGisement; i++) {


            do {
                System.out.println("Quel est la pureté du gisement n° " + i + " sur lequel vous voulez mettre une machine ?  (IMPUR,NORMAL,PUR)");

                String typeGisement = sc.nextLine().toLowerCase();

                switch (typeGisement) {
                    case "impur":
                        gisementChoisi = Node.inNodeList().get(NodeEnum.IMPURE);
                        again= false;
                        break;
                    case "normal":
                        gisementChoisi = Node.inNodeList().get(NodeEnum.NORMAL);
                        again= false;
                        break;
                    case "pur":
                        gisementChoisi = Node.inNodeList().get(NodeEnum.PURE);
                        again= false;
                        break;
                    default:
                        System.out.println("Ce que vous avez indiqué est erroné. Veuillez recommencer.\n");
                        again = true;
                        break;

                } }while(again);
            again = true;

            do{

                System.out.println("Quel est le type d'extracteur que vous utilisez ? (MK1,MK2,MK3)");

                String mk = sc.nextLine().toLowerCase();



                switch (mk) {
                    case "mk1":
                        mineur = Production.inProductionList().get(ProductionEnum.MINOR_MK1);
                        again = false;
                        break;
                    case "mk2":
                        mineur = Production.inProductionList().get(ProductionEnum.MINOR_MK2);
                        again = false;
                        break;
                    case "mk3":
                        mineur = Production.inProductionList().get(ProductionEnum.MINOR_MK3);
                        again = false;
                        break;
                    default:
                        System.out.println("Ce que vous avez indiqué est erroné. Veuillez recommencer.\n");
                        again = true;
                        break;
                }
                System.out.println();
            }while(again);

            oreSource.quantity = oreSource.quantity +smelter.getMinorOutput(nbGisement,gisementChoisi,mineur);
            }
        System.out.println("Vous avez a disponibilité " + oreSource.quantity + " minerais.\n");


        smelter.getAllSmelterInfo(oreSource,smelter,chosenItem);

        System.out.println();










    }
}

