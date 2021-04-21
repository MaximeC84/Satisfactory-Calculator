import java.util.HashMap;



public abstract class Item {

    String name;
    int input;
    int output;
    Item item;
    int inputItem;

    Item(String name){
        this.name = name;
    }

    static public HashMap<ItemEnum, Item > getAllItems(){
        HashMap<ItemEnum, Item> allItemMap = new HashMap<>();

        allItemMap.putAll(Ores.getItems());
        allItemMap.putAll(Smelter.getItems());
        allItemMap.putAll(Constructor.getItems());
    return allItemMap;
    }

   static Item searchInAllItemMap(String itemName) {

        Item item = null;
        Item itemBuffer;


      /* if(!getAllItems().containsValue(itemName.toLowerCase())){
           System.out.println("L'item indiqué est introuvable, merci de réessayer.\n");
           item = null;

       }else {*/

           item = null;
           for (ItemEnum itemIterator : ItemEnum.values()) {

            item = Smelter.getAllItems().get(itemIterator);


            if (itemName.equals(item.name.toLowerCase())) {
                break;
        }
            item = null;

   }

       return item;
    }


    void searchInAllItem(String itemName) {

        Item item = null;
        Item itemBuffer;
        

        item = null;
        for (ItemEnum itemIterator : ItemEnum.values()) {

            item = Smelter.getAllItems().get(itemIterator);


            if (itemName.equals(item.name.toLowerCase())) {
                break;
            }
            item = null;

        }

      
    }
    

    int getQuantity(Ores ore, Production mineur){
        ore.quantity  = mineur.totalOutput;
        return ore.quantity;
    }


    static void displayAllItemList(){

        System.out.println(
                """
                        ---------------------------
                        |      Smelter Item :     |
                        ---------------------------""");

        Smelter.displaySmelterMap();
        System.out.println();
        System.out.println(
                """
                        ---------------------------
                        |    Constructor Item :   |
                        ---------------------------""");

        Constructor.displayConstructorMap();


    }

    abstract Ores getOreSource(Item item);


}


class Ores extends Item {

    int quantity;

    Ores(String name) {
        super(name);
    }

    @Override
    Ores getOreSource(Item item) {
        return null;
    }


    String getName() {
        return name;
    }

    public static HashMap<ItemEnum, Item> getItems() {

        HashMap<ItemEnum, Item> oresMap = new HashMap<ItemEnum, Item>();

        Ores copperOre = new Ores("Copper Ore");
        oresMap.put(ItemEnum.COPPER_ORE, copperOre);

        Ores ironOre = new Ores("Iron Ore");
        oresMap.put(ItemEnum.IRON_ORE, ironOre);

        Ores coalOre = new Ores("Coal Ore");
        oresMap.put(ItemEnum.COAL_ORE, coalOre);

        Ores concrete = new Ores("Limestone");
        oresMap.put(ItemEnum.LIMESTONE, concrete);

        return oresMap;
    }

    static void displayOreMap() {            //OK

        Ores item = null;
        String itemName;


        for (ItemEnum itemIterator : ItemEnum.values()) {
            itemName = itemIterator.name;

            item = (Ores) item.getItems().get(itemIterator);


            if (item == null) {

            } else if (itemName.equals(item.name)) {
                System.out.println(" - " + item.name);

            }
        }
    }
}

    class Smelter extends Item {

        Ores oreNeeded;

        Smelter(String name, Ores oreNeeded, int inputItem, int output) {
            super(name);
            this.inputItem = inputItem;
            this.output = output;
            this.oreNeeded = oreNeeded;
        }
        public Ores getOreNeeded(){
            return oreNeeded;
        }

        public int getInput() {
            return input;
        }

        public Item getItem() {
            return item;
        }

        public String getName() {
            return name;
        }


        public static HashMap<ItemEnum, Item> getItems() {

            HashMap<ItemEnum, Item> smelterMap = new HashMap<>();

            Smelter copperIngot = new Smelter("Copper Ingot", (Ores) Ores.getItems().get(ItemEnum.COPPER_ORE), 30, 30);
            smelterMap.put(ItemEnum.COPPER_INGOT, copperIngot);

            Smelter ironIngot = new Smelter("Iron Ingot", (Ores) Ores.getItems().get(ItemEnum.IRON_ORE), 30, 30);
            smelterMap.put(ItemEnum.IRON_INGOT, ironIngot);

            return smelterMap;
        }


  /*  public static void iterateItemEnumValue() {
        System.out.println("Iterating over ItemEnum enum");
        String itemName;
        for(ItemEnum item : ItemEnum.values()) {
            itemName = item.name;
            System.out.println(itemName);
        }
    }*/


        public static void displaySmelterMap() {
            Item item = null;

            String itemName;

            for (ItemEnum itemIterator : ItemEnum.values()) {
                itemName = itemIterator.name;

                item = Smelter.getItems().get(itemIterator);


                if (item == null) {

                } else if (itemName.equals(item.name)) {
                    System.out.println(" - " + item.name);

                }

            }

        }

        @Override
        Ores getOreSource(Item item) {
            Ores ore;
            ore = ((Smelter)item).oreNeeded;
            return ore;
        }


    }

    class Constructor extends Item{


        Item itemInput;

        Constructor(String name, Item itemInput, int inputItem, int output) {
            super(name);
            this.inputItem = inputItem;
            this.output = output;
            this.itemInput = itemInput;
        }



        public static HashMap<ItemEnum, Item> getItems() {

            HashMap<ItemEnum, Item> constructorMap = new HashMap<>();

            Constructor concrete = new Constructor("Concrete",Ores.getItems().get(ItemEnum.LIMESTONE),45,15);
            constructorMap.put(ItemEnum.CONCRETE, concrete);

            Constructor ironPlate = new Constructor("Iron Plate",Smelter.getItems().get(ItemEnum.IRON_INGOT),30,20);
            constructorMap.put(ItemEnum.IRON_PLATE, ironPlate);

            Constructor rod = new Constructor("Rod",Smelter.getItems().get(ItemEnum.IRON_INGOT),15,15);
            constructorMap.put(ItemEnum.ROD, rod);

            Constructor screw = new Constructor("Screw", rod, 10,40);
            constructorMap.put(ItemEnum.SCREW, screw);

            Constructor wire = new Constructor("Wire",Smelter.getItems().get(ItemEnum.COPPER_INGOT),15,30);
            constructorMap.put(ItemEnum.WIRE, wire);

            Constructor cable = new Constructor("Cable",wire,60,30);
            constructorMap.put(ItemEnum.CABLE, cable);

            Constructor copperSheet = new Constructor("Copper Sheet",Smelter.getItems().get(ItemEnum.COPPER_INGOT),20,10);
            constructorMap.put(ItemEnum.COPPER_SHEET, copperSheet);


            return constructorMap;
        }




        public static void displayConstructorMap() {
            Item item = null;

            String itemName;

            for (ItemEnum itemIterator : ItemEnum.values()) {
                itemName = itemIterator.name;

                item = Constructor.getItems().get(itemIterator);


                if (item == null) {

                } else if (itemName.equals(item.name)) {
                    System.out.println(" - " + item.name);

                }

            }}


        @Override
        Ores getOreSource(Item item) {
            Ores oresource = null;
            Constructor itemtest = (Constructor) item;

            Item itemBuffer;
            if (((Constructor) item).itemInput instanceof Smelter){
                oresource = ((Constructor) item).itemInput.getOreSource(itemInput);
            }
            else if (((Constructor) item).itemInput instanceof Constructor){
                itemBuffer = ((Constructor) item).itemInput;
                oresource = itemBuffer.getOreSource(itemBuffer);

            }



            return oresource;
        }
    }


