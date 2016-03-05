/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import objects.LPARs;



/** Assumes UTF-8 encoding. JDK 7+. */
public class readHDL {

  public static void main(String... aArgs) throws IOException {
    readHDL parser = new readHDL("ddrsap02");
    parser.processLineByLine();
//    System.out.println(String.valueOf(lpar.getLparName()+" : "+lpar.getLparID()+" : "+lpar.getHostname()+" : "+lpar.getEntCapacity()));

    log("Done.");
  }
  
  /**
   Constructor.
   @param lparName full name of an existing, readable file.
  */
  public readHDL(String lparName){
      String aFileName = "./Systems/"+lparName+"/hds/dlnkmgr_view-lu-item_all.txt";
    fFilePath = Paths.get(aFileName);
  }
  
  
  /** Template method that calls {@link #processLine(String)}.
     * @throws java.io.IOException */
  public final void processLineByLine() throws IOException {
    try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
String header1 = scanner.nextLine();
String header2 = scanner.nextLine();
System.out.println(scanner.delimiter());
while (scanner.hasNext()){
scanner.useDelimiter(": \\p{javaWhitespace}+");
System.out.println(scanner.delimiter());
String ProductLine = scanner.nextLine();
 System.out.println("Echo ProductLine : "+ProductLine);

String Product  = scanner.next();
String ProductName  = scanner.next();
String SerialNumLine  = scanner.nextLine();
String Serial = scanner.next();
String SerialNum = scanner.next();
String LunCountLine = scanner.nextLine();
 System.out.println("Echo Test Here : "+LunCountLine.replaceAll("\\s", ""));
String lus = scanner.next();
System.out.println(lus.replaceAll(" ", ""));
String luncounts = scanner.next();
System.out.println(luncounts.replaceAll(" ", ""));
scanner.nextLine();
String HeaderLine = scanner.nextLine();

int luncount = Integer.valueOf(luncounts.replaceAll("\\s", ""));

for(int n=luncount; n>0; n--){
String DataLine = scanner.nextLine();
    processLine(DataLine);
    System.out.print(DataLine);
    String duplicateLine = scanner.nextLine();
} 


    }
    }
  }
  

  protected void processLine(String aLine){
             
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(" ");
//    lparsList.add(lpar);
//    log("size"+lparsList.size());
//    scanner.nextLine();
    if (scanner.hasNext()){

      //assumes the line has a certain structure
      
      String LunID = scanner.next();
      String SLPR = scanner.next();
      String HdiskName = scanner.next();    
      String VG = scanner.next();
      String OSPath = scanner.next();
      String PathID = scanner.next();
      String PathName = scanner.next();
      String ChaPort = scanner.next();
      String CLRP = scanner.next();
      String Status = scanner.next();
      String Type = scanner.next();
      String IOCount = scanner.next();
      String IOErr = scanner.next();
      String dnum = scanner.next();
      String IEP = scanner.next();
      
    }
    else {
      log("Empty or invalid File. Unable to process.");
    }
  
//     ^     
//     System.out.println(String.valueOf(lpar.getLparName()));
  } 
 
  // PRIVATE 
  private final Path fFilePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  public static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "\"";
    return QUOTE + aText + QUOTE;
  }
   public static void printLparsList(List<LPARs> lparsListToPrint) {  
   log("List Size is "+lparsListToPrint.size());
//   log("Partition Name :" + lparsListToPrint.get(0).getLparName()
//     + ",   Node Name :" + lparsListToPrint.get(0).getHostname()+",  Entitled Capacity :"+lparsListToPrint.get(0).getDesiredCPU());  
  }  
 
} 