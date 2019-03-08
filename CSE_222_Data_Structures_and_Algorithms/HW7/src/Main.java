import java.util.NavigableMap;

/**
 * This is the test class.<br>
 */
public class Main
{
    public static void main(String args[]){

        System.out.println("QUESTION 1: ");
        System.out.print("\n\n");
        final Boolean q1 = Q1Test();
        System.out.println("\n\nQUESTION 2: ");
        System.out.print("\n");
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            System.out.println("\nYour tests is done. Make sure that you test all methods of class!! " );
            return;
        }
    }

    /**
     * Test of Q1.<br>
     * @return True if the test is completed.<br>
     */
    public static Boolean Q1Test(){

        try {
            NavigableMap<String, String> turkey = new BinaryNavMap<String, String>();
            turkey.put("uskudar", "istanbul");
            turkey.put("kadıkoy", "istanbul");
            turkey.put("cekirge", "bursa");
            turkey.put("gebze", "kocaeli");
            turkey.put("niksar", "tokat");
            turkey.put("kecıoren", "ankara");
            turkey.put("aksaray", "istanbul");
            turkey.put("foca", "izmir");
            turkey.put("manavgat", "antalya");
            turkey.put("kahta", "adıyaman");
            turkey.put("biga", "canakkale");

            System.out.println("SO, HERE ARE THE TESTS:\n");

            System.out.println("THE ORIGINAL SET ODDS IS: " + turkey);
            System.out.println("\nentrySet() METHOD: " + turkey.entrySet() + "\n");
            System.out.println("firstEntry() METHOD: " + turkey.firstEntry() + "\n");
            System.out.println("lastEntry() METHOD: " + turkey.lastEntry()+ "\n");
            System.out.println("lowerEntry() METHOD: " + turkey.lowerEntry("gebze")+ "\n");
            System.out.println("lowerKey() METHOD: " + turkey.lowerKey("cekirge")+ "\n");
            System.out.println("floorEntry() METHOD: " + turkey.floorEntry("kadıkoy")+ "\n");
            System.out.println("floorKey() METHOD: " + turkey.floorKey("kahta")+ "\n");
            System.out.println("ceilingEntry() METHOD: " + turkey.ceilingEntry("kecıoren")+ "\n");
            System.out.println("ceilingKey() METHOD: " + turkey.ceilingKey("foca")+ "\n");
            System.out.println("higherEntry() METHOD: " + turkey.higherEntry("manavgat")+ "\n");
            System.out.println("higherKey() METHOD: " + turkey.higherKey("niksar")+ "\n");
            System.out.println("subMap() METHOD: " + turkey.subMap("gebze",true, "uskudar", false)+ "\n");
            System.out.println("subMap() METHOD WITH RETURN SortedMap: " + turkey.subMap("gebze", "uskudar")+ "\n");
            System.out.println("headMap() METHOD: " + turkey.headMap("gebze",false)+ "\n");
            System.out.println("headMap() METHOD WITH RETURN SortedMap: " + turkey.headMap("gebze")+ "\n");
            System.out.println("tailMap() METHOD: " + turkey.tailMap("gebze",false)+ "\n");
            System.out.println("tailMap() METHOD WITH RETURN SortedMap: " + turkey.tailMap("gebze")+ "\n");
            System.out.println("firstKey() METHOD: " + turkey.firstKey()+ "\n");
            System.out.println("lastKey() METHOD: " + turkey.lastKey()+ "\n");
            System.out.println("navigableKeySet() METHOD: " + turkey.navigableKeySet()+ "\n");
            System.out.println("pollFirstEntry() METHOD: " + turkey.pollFirstEntry()+ "\n");
            System.out.println("pollLastEntry() METHOD: " + turkey.pollLastEntry()+ "\n");
            System.out.println("NEW SET AFTER DELETION FIRST AND THE LAST ENTRY: " + turkey);


            //you should write more test function to show your solution
            //your test must contain all methods to get full points!!!
            //you also may need to owerwrite some methods to provide BST RULES

            /* *some links to help you

               https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
               https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html

            * */
        }
        catch(IllegalArgumentException e){
            System.out.println("ERROR: there is a problem with arguments that you give.");
        }
        catch(NullPointerException e){
            System.out.println("ERROR: THERE IS A NULL POINTER EXCEPTION!");
        }
        return Boolean.TRUE;

    }
    /**
     * Test of Q2.<br>
     * @return True if the test is completed.<br>
     */
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<String,String>();
        turkey.put("edremit","balikesir");
        turkey.put("edremit","van");
        turkey.put("kemalpasa","bursa");
        turkey.put("kemalpasa","izmir");
        turkey.put("ortakoy","istanbul");//we assume a district
        turkey.put("ortakoy","aksaray");
        turkey.put("ortakoy","corum");
        turkey.put("kecıoren","ankara");
        turkey.put("pinarbasi","kastamonu");
        turkey.put("pinarbasi","kayseri");
        turkey.put("eregli","konya");
        turkey.put("eregli","tekirdag");
        turkey.put("eregli","zonguldak");
        turkey.put("golbasi","adıyaman");
        turkey.put("golbasi","ankara");
        turkey.put("biga","canakkale");

        System.out.println(turkey);
        System.out.println("\nSize: " + turkey.size());
        System.out.println("Get: " + turkey.get("pinarbasi"));
        System.out.println("Deleted: " + turkey.remove("golbasi"));
        System.out.println(turkey);


        /* *test all

            V get(Object key);

            V put(K key, V value);

            V remove(Object key);

            int size();

        * */


        return Boolean.TRUE;
    }


}
