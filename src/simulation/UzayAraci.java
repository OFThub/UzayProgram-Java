/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin uzay aracı fonksiyonlarının bulunduğu yerdir.
* 	Bu sınıfta uzay aracının durumunun güncellenmesi, çıkış varış durumları, nüfusun duruma göre güncellenmesi ve
* hedefe varış tarihinin hesaplanması gibi fonksiyonları içerir.
* </p>
*/

package simulation;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String ad;
    private String cikis;
    private String varis;
    private Zaman cikisTarihi;
    private int mesafe;
    private int kalanSaat;
    public boolean yolda;
    private boolean hedefeVardi;
    private List<Kisi> yolcular;

    public UzayAraci(String ad, String cikis, String varis, Zaman cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.kalanSaat = mesafe;
        this.yolda = false;
        this.hedefeVardi = false;
        this.yolcular = new ArrayList<>();
    }

    public void guncelle() {
        Gezegen cikisGezegen = Simulasyon.gezegenler.stream()
                .filter(g -> g.getAd().equals(cikis)).findFirst().orElse(null);

        if (!yolda && !hedefeVardi && cikisGezegen != null && cikisGezegen.getTarih().esitMi(cikisTarihi)) {
            yolda = true;
        }

        if (yolda && kalanSaat > 0) {
            kalanSaat--;
            if (kalanSaat == 0) {
                yolda = false;
                hedefeVardi = true;
            }
        }
    }

    public boolean yoldaMi() {
        return yolda;
    }

    public boolean hedefeVardiMi() {
        return hedefeVardi;
    }

    public boolean imhaMi() {
        return yolcular.stream().noneMatch(Kisi::hayattaMi);
    }

    public String getBulunduguGezegen() {
        if (yolda) return "Yolda";
        return hedefeVardi ? varis : cikis;
    }

    public String getAd() {
        return ad;
    }
    
    public String getDurum() {
        if (imhaMi()) return "IMHA";
        if (yolda) return "YOLDA";
        if (hedefeVardi) return "VARDI";
        return "BEKLEMEDE";
    }
    
    public String getCikis() {
        return cikis;
    }
    
    public String getVaris() {
        return varis;
    }

    public int getKalanSaat() {
        return kalanSaat;
    }

    public List<Kisi> getYolcular() {
        return yolcular;
    }

    public void yolcuEkle(Kisi k) {
        yolcular.add(k);
    }

    public Zaman hedefeVarisTarihi() {
        Gezegen hedefGezegen = Simulasyon.gezegenler.stream()
                .filter(g -> g.getAd().equalsIgnoreCase(varis))
                .findFirst()
                .orElse(null);

        if (hedefGezegen == null) return null;

        Zaman varisTarihi = new Zaman(cikisTarihi.getGun(), cikisTarihi.getAy(), cikisTarihi.getYil());
        int gunlukSaat = hedefGezegen.getGunlukSaat();
        int kalanSaat = mesafe;

        while (kalanSaat > 0) {
            kalanSaat -= gunlukSaat;
            varisTarihi.ilerletGun();
        }

        return varisTarihi;
    }
    
    @Override
    public String toString() {
        String durum = imhaMi() ? "IMHA" : (yolda ? "YOLDA" : "BEKLEMEDE");
        return ad+"\t"+durum +"\t"+(imhaMi() ? "--" : kalanSaat) +"\t"+ getBulunduguGezegen();
               
               
    }
}