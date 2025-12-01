/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin dosya okuma fonksiyonlarının bulunduğu yerdir.
* 	Bu sınıfta 3 dosya da okunup içindeki bilglier ait olduğu sınıflara aktarılır
* </p>
*/

package simulation;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DosyaOkuma {

    public static void dosyalarYukle() {
        try {
            List<String> gezegenSatirlari = Files.readAllLines(Paths.get("Resources/Gezegenler.txt"));
            for (String satir : gezegenSatirlari) {
                String[] parcalar = satir.split("#");
                String ad = parcalar[0];
                int gunlukSaat = Integer.parseInt(parcalar[1]);
                String[] tarihStr = parcalar[2].split("\\.");
                int gun = Integer.parseInt(tarihStr[0]);
                int ay = Integer.parseInt(tarihStr[1]);
                int yil = Integer.parseInt(tarihStr[2]);
                Zaman zaman = new Zaman(gun, ay, yil);
                Simulasyon.gezegenler.add(new Gezegen(ad, gunlukSaat, zaman));
            }

            List<String> aracSatirlari = Files.readAllLines(Paths.get("Resources/Araclar.txt"));
            for (String satir : aracSatirlari) {
                String[] parcalar = satir.split("#");
                String ad = parcalar[0];
                String cikis = parcalar[1];
                String varis = parcalar[2];
                String[] tarihStr = parcalar[3].split("\\.");
                int gun = Integer.parseInt(tarihStr[0]);
                int ay = Integer.parseInt(tarihStr[1]);
                int yil = Integer.parseInt(tarihStr[2]);
                Zaman cikisTarihi = new Zaman(gun, ay, yil);
                int mesafe = Integer.parseInt(parcalar[4]);
                UzayAraci arac = new UzayAraci(ad, cikis, varis, cikisTarihi, mesafe);
                Simulasyon.araclar.add(arac);
            }

            List<String> kisiSatirlari = Files.readAllLines(Paths.get("Resources/Kisiler.txt"));
            for (String satir : kisiSatirlari) {
                String[] parcalar = satir.split("#");
                String isim = parcalar[0];
                int yas = Integer.parseInt(parcalar[1]);
                int kalanOmur = Integer.parseInt(parcalar[2]);
                String aracAdi = parcalar[3];
                Kisi kisi = new Kisi(isim, yas, kalanOmur, aracAdi);
                Simulasyon.kisiler.add(kisi);

                for (UzayAraci a : Simulasyon.araclar) {
                    if (a.getAd().equals(aracAdi)) {
                        a.yolcuEkle(kisi);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}