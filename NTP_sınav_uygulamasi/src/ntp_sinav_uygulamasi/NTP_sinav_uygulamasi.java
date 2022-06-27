/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntp_sinav_uygulamasi;

public class NTP_sinav_uygulamasi { //class

    public NTP_sinav_uygulamasi() { //constructor
        SinavGiris giris = new SinavGiris();// SinavGiris classindan nesne turetilerek referansini alma
        giris.getClass();  //turetilen nesne ile SinavGiris classini cagirma
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { // ana main
        new NTP_sinav_uygulamasi(); //NTP_sinav_uygulamasi constructorından  referans alma
    }
}
