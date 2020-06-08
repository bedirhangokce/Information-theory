import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main{


    public Main(String fileName) throws IOException {


        Scanner scanner = new Scanner(new FileReader(fileName));
        String oneWord;
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNext()) {

            oneWord = scanner.next();
            oneWord = oneWord.replaceAll("\\p{Punct}", "");
            oneWord = oneWord.toLowerCase();
            words.add(oneWord);

        }
        String[] splitedText = words.toArray(new String[0]);

        computeEntropy(splitedText);
        computeAvgLengthByFirstChar(splitedText);

    }

    private void computeEntropy(String[] text) throws FileNotFoundException {
        TreeMap<String,Integer> treeMap=new TreeMap<>();
        double setOfNumbers = 0;
        double entropy=0;

        for (int i=0; i<text.length;i++){

            if ( treeMap.containsKey(text[i]) ){
                treeMap.put(text[i],treeMap.get(text[i])+1);
            }else{
                treeMap.put(text[i],1);
            }
        }

        for (Integer i:treeMap.values()) {
            setOfNumbers+=i;
        }
        System.out.println(setOfNumbers);

        for (Integer i:treeMap.values()
        ) {
            for(int k = 0; k < i; k++)
                entropy+= -((i/setOfNumbers)) * (Math.log(i/setOfNumbers)/Math.log(2) ) ;
        }
        System.out.println("Entropy :"+entropy);


    }

    private void computeAvgLengthByFirstChar(String[] text) throws FileNotFoundException {
        //First map will take the lenght of words according to first char
        TreeMap<Character,Integer> Map1=new TreeMap<>();
        //Second map will take count of words according to first char
        TreeMap<Character,Integer> Map2=new TreeMap<>();

        TreeMap<Character, Double> Map3=new TreeMap<>();

        for (String s : text) {

            if (Map1.containsKey(s.charAt(0))) {
                Map1.put(s.charAt(0), Map1.get(s.charAt(0)) + s.length());
                Map2.put(s.charAt(0), Map2.get(s.charAt(0)) + 1);

            } else {
                Map1.put(s.charAt(0), s.length());
                Map2.put(s.charAt(0), 1);
            }
        }

        ArrayList<Double> arrayList3=new ArrayList<>();//sum

        //how many of first char
        ArrayList<Integer> arrayList = new ArrayList<>(Map1.values());
        //lengths of each char's  string.length()
        ArrayList<Integer> arrayList2 = new ArrayList<>(Map2.values());




        for (int i =0;i<arrayList.size();i++){

            arrayList3.add(arrayList.get(i)/ ( (double)arrayList2.get(i)));

        }

        //toprint
        ArrayList<Character> arrayList4 = new ArrayList<>(Map1.keySet());

        for (int i =0; i<arrayList3.size();i++){
            Map3.put(arrayList4.get(i),arrayList3.get(i));
        }

        System.out.println("InitialCharacter = AverageLength");
        for (Map.Entry<Character, Double> entry : Map3.entrySet()) {
            System.out.println(entry);
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the file name with extension:");
        Scanner scanner = new Scanner(System.in);
        String textName= scanner.nextLine();
        new Main(textName);
        System.out.println("Process Finished");


    }
}