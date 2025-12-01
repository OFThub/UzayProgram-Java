/**
*
* @author Ömer Faruk Türkdoğdu  \\//  omer.turkdogdu@ogr.sakarya.edu.tr
* @since 20.04.2025
* <p>
* 	Bu sınıf projenin zaman fonksiyonlarının bulunduğu yerdir.
* 	Bu sınıfta zamanın ilerlemesi, uzay araçlarının seyahat etme zamanının kontrolü ve tarihin yazılması gibi
* fonksiyonları içerir.
* </p>
*/

package simulation;

public class Zaman {
    private int gun;
    private int ay;
    private int yil;

    private static final int[] AY_GUNLERI = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public Zaman(int gun, int ay, int yil) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
    }

    public void ilerletGun() {
        gun++;
        if (gun > AY_GUNLERI[ay - 1]) {
            gun = 1;
            ay++;
            if (ay > 12) {
                ay = 1;
                yil++;
            }
        }
    }

    public boolean esitMi(Zaman diger) {
        return this.gun == diger.gun && this.ay == diger.ay && this.yil == diger.yil;
    }

    public String toString() {
        return gun + "." + ay + "." + yil;
    }

    public int getGun() {
        return gun;
    }

    public int getAy() {
        return ay;
    }

    public int getYil() {
        return yil;
    }
}
