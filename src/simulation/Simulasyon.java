/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin simülasyon sınıfıdır. Projenin çalışma döngüsünü denetleyen kısımdır.
* 	Diğer sınıftaki fonksiyonların çağırılması, konsola yazılacak ifadelerin gerçekleştirilmesi
* konsolun silinmesi gibi denetimleri yapar.
* </p>
*/

package simulation;

import java.util.*;

public class Simulasyon {

    private boolean tumAraclarVardiMi = false;

    public static List<Gezegen> gezegenler = new ArrayList<>();
    public static List<Kisi> kisiler = new ArrayList<>();
    public static List<UzayAraci> araclar = new ArrayList<>();

    public void baslat() {
        DosyaOkuma.dosyalarYukle();

        while (!tumAraclarVardiMi) {

            zamanlariIlerle();
            omurleriGuncelle();
            araclariGuncelle();
            gezegenleriGuncelle();

            Console.clear();
            durumYazdir();

            tumAraclarVardiMi = kontrolTumAraclar() || tumAraclarImha() || tumAraclarYoldaVeBeklemedeDegil();
        }

        System.out.println("Simülasyon sona erdi.\n");
        durumYazdir();
    }

    private void zamanlariIlerle() {
        for (Gezegen g : gezegenler) {
            g.ilerletSaat();
        }
    }

    private void omurleriGuncelle() {
        for (Kisi k : kisiler) {
            k.kalanOmruAzalt();
        }
    }

    private void araclariGuncelle() {
        for (UzayAraci a : araclar) {
            a.guncelle();
        }
    }

    private void gezegenleriGuncelle() {
        for (Gezegen g : gezegenler) {
            g.nufusuGuncelle(araclar);
        }
    }

    private boolean kontrolTumAraclar() {
        for (UzayAraci a : araclar) {
            if (!a.hedefeVardiMi()) {
                return false;
            }
        }
        return true;
    }

    private boolean tumAraclarImha() {
        for (UzayAraci a : araclar) {
            if (!a.imhaMi()) {
                return false;
            }
        }
        return true;
    }

    private boolean tumAraclarYoldaVeBeklemedeDegil() {
        for (UzayAraci a : araclar) {
            String durum = a.getDurum();
            if (durum.equals("BEKLEMEDE") || durum.equals("YOLDA")) {
                return false;
            }
        }
        return true;
    }

    private void durumYazdir() {
        System.out.print("GEZEGENLER:\t");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-25s", "--- " + g.getAd() + " ---");
        }
        System.out.println();

        System.out.print("TARİH:\t\t");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-25s", g.getTarih());
        }
        System.out.println();

        System.out.print("SAAT:\t\t");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-25s", "   " + g.getSaat() + ":00");
        }
        System.out.println();

        System.out.print("NÜFUS:\t\t");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-25s", "    " + g.getNufusSayisi());
        }
        System.out.println("\n");

        System.out.println("UZAY ARAÇLARI:");
        System.out.printf("%-12s%-12s%-12s%-12s%-22s%-25s\n", "Araç adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih");
        for (UzayAraci a : araclar) {
            System.out.printf("%-12s%-14s%-12s%-16s%-22s%-25s\n",
                a.getAd(), a.getDurum(), a.getCikis(), a.getVaris(), a.getKalanSaat(), a.hedefeVarisTarihi());
        }
    }


}