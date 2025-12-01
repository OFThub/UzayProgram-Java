/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf konsolda silinme fonksiyonunu içerir
* </p>
*/

package simulation;

import java.io.IOException;

public class Console {
    public static void clear() {
        try {
        	if(System.getProperty("os.name").contains("Windows"))
        		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        	else
        		Runtime.getRuntime().exec("clear");
        }
        catch(IOException | InterruptedException ex){
        	
        }
    }
}
