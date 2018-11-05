
/**
 * This program is developed by InTeGraTeDCodeTechX and Group-5 members
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class QuickSortImpl<E> extends Application implements Comparator<E>  {
    private List<E> data ;
    private List<E> sortedData ;

    /**
     * Default constructor
     */
    public QuickSortImpl(){

    }

    public QuickSortImpl(E... data){
        this.data = new ArrayList<>();
        Collections.addAll(this.data,data);
        sortData(this.data, new QuickSortImpl<>());

        outputSortedData();
    }//end constructor



    private void outputSortedData(){
        System.out.println("Sorted data :"+Arrays.toString(data.toArray())+"\n");
    }

    /**
     *
     * @param S the LinkedList
     * @param c the comparator Object
     */
    public void sortData(List<E> S, Comparator<E> c){

        if (S.size() <= 1){
            return; //data already sorted
        }
        E pivot    = S.get(S.size()-1); //get the last element - pivot

        //divide
        List<E> L  = new ArrayList<>();
        List<E> E  = new ArrayList<>();
        List<E> G  = new ArrayList<>();

        while(!S.isEmpty()){
            if ( c.compare(S.get(S.size()-1), pivot) == -1){
                L.add(S.get(S.size()-1)); //get last element in the list
                S.remove(S.size()-1); //remove last element
            }else if (c.compare(S.get(S.size()-1), pivot) == 0){
                E.add(S.get(S.size()-1)); //get last element in the list
                S.remove(S.size()-1); //remove last element
            }else if (c.compare(S.get(S.size()-1), pivot)== 1){
                G.add(S.get(S.size()-1)); //get last element in the list
                S.remove(S.size()-1); //remove last element
            }
        }//end while

        //recursive call
        sortData(L, c);
        sortData(G,c);

        /**System.out.println("----------------------------------------------------------------");
         L.forEach(e->{System.out.printf("%s,",e); });
         E.forEach(e->{System.out.printf("%s,",e); });
         G.forEach(e->{System.out.printf("%s,",e); });
         System.out.println("-------------------------------------------------------------\n");
         */

        //conquer
        mergeQuickSortElement(S,L,E,G) ;
    }//end sortData


    private void mergeQuickSortElement(List<E> S, List<E> L ,List<E> E , List<E> G ){

        while(!L.isEmpty()){
            S.add(L.remove(0)); //
        }while (!E.isEmpty()){
            S.add(E.remove(0));
        }while(!G.isEmpty()){
            S.add(G.remove(0));
        }
    }//end method mergeQuickSortElement


    @Override
    public int compare(E o1, E o2) {
        //returns less than or greater than
        if (o1 instanceof Integer) {
            return ((Integer) o1).compareTo((Integer)o2);
        }else if (o1 instanceof String){
            return  ((String) o1).compareTo((String) o2);
        }else if (o1 instanceof Boolean){
            return ((Boolean) o1).compareTo((Boolean)o2);
        }else if ((o1 instanceof Character)){
            return ((Character)o1).compareTo((Character) o2);
        }else if(o1 instanceof Float){
            return ((Float)o1).compareTo((Float) o2);
        }else if(o1 instanceof Byte){
            return ((Byte)o1).compareTo((Byte) o2);
        }else if (o1 instanceof Double){
            return ((Double)o1).compareTo((Double)o2);
        }
        return 0 ; //equal
    }//end compare

    public List<E> getSortedData(){
        return this.sortedData ;
    }

    /**
     *
     * @param newData the new data to be sorted by the program
     */
    public void setData(LinkedList<E> newData){
        this.data = newData ;
    }//end data

    /**
     *
     * @return the Original data is returned
     */
    public List<E> getData() {
        return this.data;
    }//end method

    public static void readInputFile(){

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        float compTime = 0L;
        Long start = 0L ;

        while (true) {
            System.out.println("Select option :\n(1)Test program\n(2)sort data in a file");
            int opt = scan.nextInt();
            if (opt == 1) {

                Random random = new Random();
                Scanner inputHolder = new Scanner(System.in);
                System.out.println("Enter data size :'");
                int dataSize = inputHolder.nextInt();
                Integer[] dataI ;
                Double[] dataD ;


                System.out.println("Choose data type to sort\n(1)Integer\n(2)Double");
                int type = scan.nextInt();

                if (type==1){
                    dataI= new Integer[dataSize];
                    for (int i = 0; i < dataSize; i++) {
                        //data.add(i*random.nextInt(1000000));
                        dataI[i] = Math.abs (random.nextInt(1000));
                    }//end for loop

                    System.out.println("Original Integer Data :" + Arrays.toString(dataI) + "\n");
                    start = System.currentTimeMillis();
                    new QuickSortImpl<>(dataI); // The program kicks off from here
                    compTime = System.currentTimeMillis() - start;
                    System.out.printf("The computational time is: %.3fs\n", compTime / 1000);
                    System.out.println("==========================================================================================================================================================================================================================-----------------------------");

                    dataI = null ; //release the memory used dataI
                }else {
                    dataD = new Double[dataSize];
                    for (int i = 0; i < dataSize; i++) {
                        //data.add(i*random.nextInt(1000000));
                        dataD[i]=(random.nextDouble()*1000);
                    }
                    System.out.println("Original Integer Data :" + Arrays.toString(dataD) + "\n");
                    start = System.currentTimeMillis();
                    new QuickSortImpl<>(dataD); // The program kicks off from here
                    compTime = System.currentTimeMillis() - start;
                    System.out.printf("The computational time is: %.3fs\n", compTime / 1000);
                    System.out.println("==========================================================================================================================================================================================================================-----------------------------");

                    dataD = null ; //release the data used by dataD
                }//tdata to be sorted by the program
            } else {
                //when an input file is preferred
                Application.launch(args);
            }

        }//end while(true)
    }
    @Override
    public void start(Stage stage){
        Button btn = new Button("Select File");
        FileChooser fileChooser = new FileChooser();
        Random random = new Random();
        Scanner inputHolder = new Scanner(System.in);

        Pane pane = new Pane();
        pane.getChildren().add(btn);
        stage.setScene(new Scene(pane));

        try {
            btn.setOnMouseClicked(e -> {
                Path filePath = Paths.get("");

                List<String> lines ;
                fileChooser.setTitle("View Pictures");
                fileChooser.setInitialDirectory(
                        new File(System.getProperty("user.home"))
                );

                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("TXT", "*.txt")
                );

                try {
                    System.out.println("Enter option:\n(1)Number\n(2)Strings");
                    int opt = inputHolder.nextInt();
                    filePath =  fileChooser.showOpenDialog(stage).toPath();
                    if (!filePath.equals(null)){
                        lines = Files.readAllLines(filePath);

                        Collections.addAll(lines,lines.get(0).split("\\s+|\\.+"));
                        Collections.addAll(lines,lines.get(0).split("\\s+|\\.+"));
                        lines.remove(0);

                        System.out.println(lines.toString());
                        Double[] data = new Double[lines.size()];
                        String[] strData = new String[lines.size()];

                        if (opt==1){
                            for(int i = 0 ; i < lines.size()  ; i++){
                                //  if (lines.get(i).matches("[0-9]+ | [0-9]+\\.")){
                                data[i] = Double.parseDouble(lines.get(i));
                                //  }
                            }//end for

                            System.out.println("Length of data : "+data.length);

                            System.out.println("Original Data :" + Arrays.toString(data));
                            Long start = System.currentTimeMillis();//get the current milliseconds time from the system

                            QuickSortImpl<Double> program = new QuickSortImpl<>(data); // The program kicks off from here
                            float compTime = System.currentTimeMillis() - start;
                            // program.outputSortedData(); //prints out the sorted array data
                            System.out.printf("The computational time is: %.3f\ns", compTime / 1000); //gives the computational time in seconds
                            System.out.println("==========================================================================================================================================================================================================================-----------------------------");

                        }//end if opt is 1
                        else {
                            System.out.println("Original Data :"+Arrays.toString(lines.toArray(new String[lines.size()])));
                            Long start = System.currentTimeMillis() ;//get the current milliseconds time from the system
                            QuickSortImpl<String> program =  new QuickSortImpl<>(lines.toArray(new String[lines.size()])); // The program kicks off from here
                            float compTime = System.currentTimeMillis() - start ;
                            program.outputSortedData(); //prints out the sorted array data
                            System.out.printf("The computational time is: %.3fs",compTime/1000); }//end else of opt conditioning
                    }//end statement
                } catch (NullPointerException | IOException e1) {
                    if (filePath==null){
                        System.out.println("No file was selected");
                    }
                    e1.printStackTrace();
                }

            });
        }catch (Exception e){
            System.out.println();
        }

          /*  inputHolder = new Scanner(fileChooser.showOpenDialog(stage)); //this line will selet the file from the user system
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            */
        stage.setMinWidth(100);
        stage.setMinHeight(100);

        stage.show();// display the dialog window
    }//end stage method
}//end class QuickSortImpl
