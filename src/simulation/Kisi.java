/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin kişi fonksiyonlarının bulunduğu yerdir.
* 	Bu sınıftan kişiyle ilgili bilgiler elde edilir.
* </p>
*/

package simulation;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String uzayAraciAdi;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public void kalanOmruAzalt() {
        if (kalanOmur > 0) {
            kalanOmur--;
        }
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    public String getIsim() {
        return isim;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String toString() {
        return isim + " | Yaş: " + yas + " | Kalan Ömür: " + kalanOmur + " | Araç: " + uzayAraciAdi;
    }
}
