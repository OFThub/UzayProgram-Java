/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin gezegen fonksiyonlarının bulunduğu yerdir.
* 	Bu sınıfta gezegenin kendine özel tarihi ve nüfusu denetlenir.
* </p>
*/

package simulation;

import java.util.ArrayList;
import java.util.List;

public class Gezegen {
    private String ad;
    private int gunlukSaat;
    private Zaman tarih;
    private int saat;
    private List<Kisi> nufus;

    public Gezegen(String ad, int gunlukSaat, Zaman tarih) {
        this.ad = ad;
        this.gunlukSaat = gunlukSaat;
        this.tarih = tarih;
        this.saat = 0;
        this.nufus = new ArrayList<>();
    }

    public void ilerletSaat() {
        saat++;
        if (saat >= gunlukSaat) {
            saat = 0;
            tarih.ilerletGun();
        }
    }

    public void nufusuGuncelle(List<UzayAraci> araclar) {
        nufus.clear();
        for (UzayAraci a : araclar) {
            if (!a.yoldaMi() && a.getBulunduguGezegen().equalsIgnoreCase(this.ad)) {
                for (Kisi k : a.getYolcular()) {
                    if (k.hayattaMi()) {
                        nufus.add(k);
                    }
                }
            }
        }
    }


    public String getAd() {
        return ad;
    }

    public Zaman getTarih() {
        return tarih;
    }

    public int getSaat() {
        return saat;
    }

    public int getNufusSayisi() {
        return nufus.size();
    }
    
    public int getGunlukSaat() {
    	return gunlukSaat;
    }
}
