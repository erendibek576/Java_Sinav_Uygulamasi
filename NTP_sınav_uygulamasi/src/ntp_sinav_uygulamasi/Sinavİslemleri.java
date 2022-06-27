/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntp_sinav_uygulamasi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 *
 * @author eren_
 */
public class Sinavİslemleri extends JFrame implements ActionListener { //class

    JLabel soru; //diziden cekilecek sorularin yer alacagi label yazisi
    JRadioButton radioButton[] = new JRadioButton[6]; //soru icin turetilecek radiobutton dizisi
    ButtonGroup bg; //buton gruplama =>radiobutonlarda tek secim yapılabilmesini saglar
    JButton btnCevapla, btnGec;//sorulari cevaplamayi veya bos gecmeyi saglayan butonlar
    int dogruSayisi = 0, yanlisSayisi = 0, bosSayisi = 0, mevcut_soru_cevap = 0, toplamPuan = 0;
    //sorularda radiobuton secimine gore dogru,yanlis,bos ve toplamPuani hesaplayacak degiskenler

    JFrame f2;//frame icinde sonucEkrani icin f2 frame ekranı ekleme
    JLabel lblDogru, lblDogruSayisi;//sonucEkrani icinde dogru yazisini ve toplanan dogru sayisini yazdiran labellar
    JLabel lblYanlis, lblYanlisSayisi;//sonucEkrani icinde yanlis yazisini ve toplanan yanlis sayisini yazdiran labellar
    JLabel lblBos, lblBosSayisi;//sonucEkrani icinde bos yazisini ve toplanan bos sayisini yazdiran labellar
    JLabel puan, puanSayisi;//sonucEkrani icinde puan yazisini ve toplanan puan sayisini yazdiran labellar
    JLabel sonuc; //sonucEkrani icinde toplanan puan sayisina gore yazi yazdiran label
    JButton basaDon; //sonucEkrani icinde SinavGiris formuna geri dondurmesini sagliyoruz

    JLabel sinavSure;//Sinavİslemleri icinde tanimlanan sure bilgisini yazdiran label
    Timer timer;//Sinavİslemleri icinde sinav icin tanimlanan sure bilgisini ayarlama

    public Sinavİslemleri() { //constructor
        super("Sinav Ekrani"); //tasarladigimiz ekranin yazisi

        setSize(600, 500);//frame ekran boyutu
        setLocation(700, 300);// program çalistiginda frame ekran konumu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kapatma tusuna bastigimizda programin kapanmasini istiyoruz
        setResizable(false);//yeniden boyutlandırma engellenir
        setVisible(true);//frame görünür yapılır
        setLayout(null);//nesneleri yerlestirmek için null olmali

        soru = new JLabel();//sorular dizisinden cekilen sorunun yer aldigi label yazisi
        soru.setFont(new Font("Verdana", Font.BOLD, 12));// label yazi tipi , yazı boyutu ayarlaması

        bg = new ButtonGroup();//uretilecek radiobutonları gruplama => tek secime izin verir
        for (int i = 1; i < 6; i++) { // for dongusu 
            radioButton[i] = new JRadioButton(); // i degerine gore radiobuton uretme => dizide 5 tane cevap yer aldıgı icin 5 radiobuton ürettik 
            add(radioButton[i]);//i degerine gore uretilen radiobutonunu frame ekranımıza ekledik
            bg.add(radioButton[i]);//i degerine gore uretilen radiobutonunu grupladık
        }

        soru.setBounds(50, 10, 500, 100);//label boyutlari
        radioButton[1].setBounds(50, 90, 500, 20);//radiobutton[1] boyutlari
        radioButton[2].setBounds(50, 120, 500, 20);//radiobutton[2] boyutlari
        radioButton[3].setBounds(50, 150, 500, 20);//radiobutton[3] boyutlari
        radioButton[4].setBounds(50, 180, 500, 20);//radiobutton[4] boyutlari
        radioButton[5].setBounds(50, 210, 500, 20);//radiobutton[5] boyutlari

        radioButton[1].setFont(new Font("Verdana", Font.PLAIN, 12)); // radiobutton[1] yazi tipi , yazı boyutu ayarlaması
        radioButton[2].setFont(new Font("Verdana", Font.PLAIN, 12)); // radiobutton[2]  yazi tipi , yazı boyutu ayarlaması
        radioButton[3].setFont(new Font("Verdana", Font.PLAIN, 12)); // radiobutton[3]  yazi tipi , yazı boyutu ayarlaması
        radioButton[4].setFont(new Font("Verdana", Font.PLAIN, 12)); // radiobutton[4] yazi tipi , yazı boyutu ayarlaması
        radioButton[5].setFont(new Font("Verdana", Font.PLAIN, 12)); // radiobutton[5] yazi tipi , yazı boyutu ayarlaması

        set();//soru  methodunu cagirir.

        btnCevapla = new JButton("Cevapla"); // btnCevapla butonu tanimlama ve yazisi
        btnCevapla.setBounds(100, 250, 100, 30);//buton boyutlari
        btnCevapla.addActionListener(this);//buton aksiyon dinleyicisi ekleme

        btnGec = new JButton("Geç");// btnGec butonu tanimlama ve yazisi
        btnGec.setBounds(270, 250, 100, 30);//buton boyutlari
        btnGec.addActionListener(this);//buton aksiyon dinleyicisi ekleme

        sinavSure = new JLabel();//sinav suresini tanımlama ve yazisi
        sinavSure.setBounds(510, 0, 200, 100);//label boyutlari

        sinavSure();//sinav suresi methodunu cagirir.

        add(soru);// frame icine soru labelini ekleme
        add(btnCevapla);// frame icine btnCevapla butonunu ekleme
        add(btnGec);// frame icine btnGec butonunu ekleme
        add(sinavSure);// frame icine sinavSure labelini ekleme

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!bg.isSelected(null)) {//butongroup secilmis ise true dondurur.
            if (e.getSource() == btnCevapla) { //btnCevapla butonuna basildiginda yapilacak eylemler
                check();//cevap methodundan deger dondurur.

                mevcut_soru_cevap++;//mevcut_soru_cevap sayisina 1 ekleme yapar.
                set();//mevcut_soru_cevap sayisina gore soru  getirir.

            }
        }
        if (e.getActionCommand().equals("Cevapla")) {//btnCevapla butonuna basildiginda yapilacak eylemler

            if (mevcut_soru_cevap == 5) {//mevcut_soru_cevap sayisi 5 e esit oldugunda  true dondurur.
                btnGec.setEnabled(false);//btnGec butonunu devre disi birakir

                timer.stop();//timer' ı durdurur.
                dispose();//frame ekranini kapatma

                //f2 => sonucEkrani icinde sonucları gosterme
                f2 = new JFrame("SONUÇ EKRANI");//tasarladigimiz ekranin yazisi
                f2.setSize(350, 375);//frame ekran boyutu
                f2.setLocation(800, 300);// program çalistiginda frame ekran konumu
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kapatma tusuna bastigimizda programin kapanmasini istiyoruz
                f2.setResizable(false);//yeniden boyutlandırma engellenir
                f2.setVisible(true);//frame görünür yapılır
                f2.setLayout(null);//nesneleri yerlestirmek için null olmali

                lblDogru = new JLabel("Doğru Sayısı: ");//sinav dogru sayisi yazisi labeli tanimlama 
                lblDogru.setFont(new Font("Verdana", Font.PLAIN, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblDogru.setBounds(100, 50, 125, 25);//label boyutlari

                lblDogruSayisi = new JLabel(String.valueOf(dogruSayisi));//sinav dogru sayisi labeli tanimlama ve dogru sayisini yazdirma
                lblDogruSayisi.setFont(new Font("Verdana", Font.BOLD, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblDogruSayisi.setBounds(200, 50, 125, 25);//label boyutlari

                lblYanlis = new JLabel("Yanlış Sayısı: ");//sinav yanlis sayisi yazisi labeli tanimlama 
                lblYanlis.setFont(new Font("Verdana", Font.PLAIN, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblYanlis.setBounds(100, 75, 150, 25);//label boyutlari

                lblYanlisSayisi = new JLabel(String.valueOf(yanlisSayisi));//sinav yanlis sayisi labeli tanimlama ve yanlis sayisini yazdirma
                lblYanlisSayisi.setFont(new Font("Verdana", Font.BOLD, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblYanlisSayisi.setBounds(200, 75, 125, 25);//label boyutlari

                lblBos = new JLabel("Boş Sayısı: ");//sinav bos sayisi yazisi labeli tanimlama 
                lblBos.setFont(new Font("Verdana", Font.PLAIN, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblBos.setBounds(100, 100, 250, 25);//label boyutlari

                lblBosSayisi = new JLabel(String.valueOf(bosSayisi));//sinav bos sayisi labeli tanimlama ve bos sayisini yazdirma
                lblBosSayisi.setFont(new Font("Verdana", Font.BOLD, 14));// label yazi tipi , yazı boyutu ayarlaması
                lblBosSayisi.setBounds(200, 100, 125, 25);//label boyutlari

                puan = new JLabel("Puan Sayısı: ");//sinav puan sayisi yazisi labeli tanimlama 
                puan.setFont(new Font("Verdana", Font.PLAIN, 14));// label yazi tipi , yazı boyutu ayarlaması
                puan.setBounds(100, 125, 125, 25);//label boyutlari

                puanSayisi = new JLabel(String.valueOf(toplamPuan));//sinav toplam puan sayisi labeli tanimlama ve toplam puan sayisini yazdirma
                puanSayisi.setFont(new Font("Verdana", Font.BOLD, 14));// label yazi tipi , yazı boyutu ayarlaması
                puanSayisi.setBounds(200, 125, 125, 25);//label boyutlari

                sonuc = new JLabel("-");///sinav toplam puan sayisina gore aciklama yazisi yazan labeli tanimlama 
                sonuc.setFont(new Font("Verdana", Font.BOLD, 14));// label yazi tipi , yazı boyutu ayarlaması
                sonuc.setBounds(50, 100, 300, 200);//buton boyutu

                basaDon = new JButton("Başa Dön");// basa don butonu tanimlama ve yazisi
                basaDon.setFont(new Font("Verdana", Font.PLAIN, 14));// label yazi tipi , yazı boyutu ayarlaması
                basaDon.setBackground(Color.yellow);// buton rengi
                basaDon.setBounds(80, 250, 150, 75);//buton boyutu

                f2.add(lblDogru);// frame icine lblDogru labelini ekleme
                f2.add(lblDogruSayisi);// frame icine lblDogruSayisi labelini ekleme
                f2.add(lblYanlis);// frame icine lblYanlis labelini ekleme
                f2.add(lblYanlisSayisi);// frame icine lblYanlisSayisi labelini ekleme
                f2.add(lblBos);// frame icine lblBos labelini ekleme
                f2.add(lblBosSayisi);// frame icine lblBosSayisi labelini ekleme
                f2.add(puan);// frame icine puan labelini ekleme
                f2.add(puanSayisi);// frame icine puanSayisi labelini ekleme
                f2.add(sonuc);// frame icine sonuc labelini ekleme
                f2.add(basaDon);// frame icine basaDon labelini ekleme

                //toplam puana gore sonuc ekranında acıklama yazdirma
                if (toplamPuan >= 40) {//toplamPuan 40'tan buyuk veya esit ise true dondurur.
                    sonuc.setText(" Tebrikler sınavı başarıyla geçtiniz!");//sonuc labelina yazdirir ve kullaniciya sunar
                } else {//toplamPuan 40'tan buyuk degil ise false dondurur.
                    sonuc.setText("Maalesef sınavdan kaldınız!");//sonuc labelina yazdirir ve kullaniciya sunar
                }

                basaDon.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a) {
                        if (a.getSource() == basaDon) {//basaDon butonuna basildiginda yapilacak eylemler
                            SinavGiris bilgi = new SinavGiris();// SinavGiris classindan nesne turetilerek referansini alma
                            a.setSource(bilgi);//turetilen nesne ile SinavGiris classini cagirma
                            f2.dispose();//f2 frame ekranini kapatma
                        }
                    }

                });
            }

        }

        if (e.getActionCommand().equals("Geç")) { //Gec butonuna basildiginda yapilacak eylemler

            if (bg.isSelected(null)) {//bg=>butongroup bos ise (herhangi bir radiobutona basilmamis) true döndürür.
                check();
                {//diziden cekilen sorunun bos gecilip gecilmedigini kontrol eder.(bos kontrolu)

                }
                mevcut_soru_cevap++; //mevcut_soru_cevap sayisina 1 ekleme yapar.
                set();//mevcut_soru_cevap sayisina gore soru ve cevaplari getirir.

                if (mevcut_soru_cevap == 4) {//mevcut_soru_cevap sayisi 5 e esit oldugunda  true dondurur.           
                    btnGec.setEnabled(false);//btnGec butonunu devre disi birakir
                }

            }
        }
    }

    void set() {// soru  methodu
        TestSorular sinav = new TestSorular();// TestSorular classindan nesne turetilerek referansini alma
        bg.clearSelection();//buttongroup radiobuton secimi temizleme
        if (mevcut_soru_cevap == 0) { //mevcut_soru_cevap  0 ' a esit oldugunda true deger döndurur.
            soru.setText(sinav.sorular[0][0]);//diziden 0.indeksteki  0.elemani   yazdirir.
            radioButton[1].setText(sinav.sorular[0][1]);//radibuton[1] ' e diziden 0.indeksteki 1.elemani  yazdirir.
            radioButton[2].setText(sinav.sorular[0][2]);//radibuton[2] ' e diziden 0.indeksteki 2.elemani  yazdirir.
            radioButton[3].setText(sinav.sorular[0][3]);//radibuton[3] ' e diziden 0.indeksteki  3.elemani  yazdirir.
            radioButton[4].setText(sinav.sorular[0][4]);//radibuton[4] ' e diziden 0.indeksteki 4.elemani  yazdirir.
            radioButton[5].setText(sinav.sorular[0][5]);//radibuton[5] ' e diziden 0.indeksteki 5.elemani  yazdirir.

        }
        if (mevcut_soru_cevap == 1) {//mevcut_soru_cevap  1 ' e sit oldugunda true deger döndurur.
            soru.setText(sinav.sorular[1][0]);//diziden 1.indeksteki  0.elemani   yazdirir.
            radioButton[1].setText(sinav.sorular[1][1]);//radibuton[1] ' e diziden 1.indeksteki 1.elemani  yazdirir.
            radioButton[2].setText(sinav.sorular[1][2]);//radibuton[2] ' e diziden 1.indeksteki 2.elemani  yazdirir.
            radioButton[3].setText(sinav.sorular[1][3]);//radibuton[3] ' e diziden 1.indeksteki  3.elemani  yazdirir.
            radioButton[4].setText(sinav.sorular[1][4]);//radibuton[4] ' e diziden 1.indeksteki 4.elemani  yazdirir.
            radioButton[5].setText(sinav.sorular[1][5]);//radibuton[5] ' e diziden 1.indeksteki 5.elemani  yazdirir.
        }
        if (mevcut_soru_cevap == 2) {//mevcut_soru_cevap  2 ' ye esit oldugunda true deger döndurur.
            soru.setText(sinav.sorular[2][0]);//diziden 2.indeksteki  0.elemani   yazdirir.
            radioButton[1].setText(sinav.sorular[2][1]);//radibuton[1] ' e diziden 2.indeksteki 1.elemani  yazdirir.
            radioButton[2].setText(sinav.sorular[2][2]);//radibuton[2] ' e diziden 2.indeksteki 2.elemani  yazdirir.
            radioButton[3].setText(sinav.sorular[2][3]);//radibuton[3] ' e diziden 2.indeksteki  3.elemani  yazdirir.
            radioButton[4].setText(sinav.sorular[2][4]);//radibuton[4] ' e diziden 2.indeksteki 4.elemani  yazdirir.
            radioButton[5].setText(sinav.sorular[2][5]);//radibuton[5] ' e diziden 2.indeksteki 5.elemani  yazdirir.
        }
        if (mevcut_soru_cevap == 3) {//mevcut_soru_cevap  3 ' e esit oldugunda true deger döndurur.
            soru.setText(sinav.sorular[3][0]);//diziden 0.indeksteki  0.elemani   yazdirir.
            radioButton[1].setText(sinav.sorular[3][1]);//radibuton[1] ' e diziden 3.indeksteki 1.elemani  yazdirir.
            radioButton[2].setText(sinav.sorular[3][2]);//radibuton[2] ' e diziden 3.indeksteki 2.elemani  yazdirir.
            radioButton[3].setText(sinav.sorular[3][3]);//radibuton[3] ' e diziden 3.indeksteki  3.elemani  yazdirir.
            radioButton[4].setText(sinav.sorular[3][4]);//radibuton[4] ' e diziden 3.indeksteki 4.elemani  yazdirir.
            radioButton[5].setText(sinav.sorular[3][5]);//radibuton[5] ' e diziden 3.indeksteki 5.elemani  yazdirir.
        }
        if (mevcut_soru_cevap == 4) {//mevcut_soru_cevap  4 ' e esit oldugunda true deger döndurur.
            soru.setText(sinav.sorular[4][0]);//diziden 0.indeksteki  0.elemani   yazdirir.
            radioButton[1].setText(sinav.sorular[4][1]);//radibuton[1] ' e diziden 4.indeksteki 1.elemani  yazdirir.
            radioButton[2].setText(sinav.sorular[4][2]);//radibuton[2] ' e diziden 4.indeksteki 2.elemani  yazdirir.
            radioButton[3].setText(sinav.sorular[4][3]);//radibuton[3] ' e diziden 4.indeksteki  3.elemani  yazdirir.
            radioButton[4].setText(sinav.sorular[4][4]);//radibuton[4] ' e diziden 4.indeksteki 4.elemani  yazdirir.
            radioButton[5].setText(sinav.sorular[4][5]);//radibuton[5] ' e diziden 4.indeksteki 5.elemani  yazdirir.
        }

    }

    boolean check() { //cevap methodu
        if (mevcut_soru_cevap == 0) {//mevcut_soru_cevap  0 ' a esit oldugunda true deger döndurur.
            if (bg.isSelected(null)) {//buttongroup icindeki radiobutonlarindan herhangi biri isaretlenmemis ise true dondurur.
                bosSayisi++;//bos sayisi arttirim
            } else if (radioButton[3].isSelected()) {//bu cevap secilmis ise true dondurur.(dogru cevap alimina gore puan arttirimi)
                toplamPuan += 20; //puan arttirim
                dogruSayisi++;//dogru sayisi arttirim
            } else { //yukaridaki secenek secilmemis ise false dondurur.(yanlisSayisini arttirir)
                yanlisSayisi++;//yanlisSayisi arttirim
            }

        }
        if (mevcut_soru_cevap == 1) {//mevcut_soru_cevap  1 ' e esit oldugunda true deger döndurur.
            if (bg.isSelected(null)) {//buttongroup icindeki radiobutonlarindan herhangi biri isaretlenmemis ise true dondurur.
                bosSayisi++;//bos sayisi arttirim
            } else if (radioButton[4].isSelected()) {//bu cevap secilmis ise true dondurur.(dogru cevap alimina gore puan arttirimi)
                toplamPuan += 20;//puan arttirim
                dogruSayisi++;//dogru sayisi arttirim
            } else {//yukaridaki secenek secilmemis ise false dondurur.(yanlisSayisini arttirir)
                yanlisSayisi++;//yanlisSayisi arttirim
            }

        }
        if (mevcut_soru_cevap == 2) {//mevcut_soru_cevap  2 ' ye esit oldugunda true deger döndurur.
            if (bg.isSelected(null)) {//buttongroup icindeki radiobutonlarindan herhangi biri isaretlenmemis ise true dondurur.
                bosSayisi++;//bos sayisi arttirim
            } else if (radioButton[4].isSelected()) {//bu cevap secilmis ise true dondurur.(dogru cevap alimina gore puan arttirimi)
                toplamPuan += 20;//puan arttirim
                dogruSayisi++;//dogru sayisi arttirim
            } else {//yukaridaki secenek secilmemis ise false dondurur.(yanlisSayisini arttirir)
                yanlisSayisi++;//yanlisSayisi arttirim

            }
        }
        if (mevcut_soru_cevap == 3) {//mevcut_soru_cevap  3 ' e esit oldugunda true deger döndurur.
            if (bg.isSelected(null)) {//buttongroup icindeki radiobutonlarindan herhangi biri isaretlenmemis ise true dondurur.
                bosSayisi++;//bos sayisi arttirim
            } else if (radioButton[1].isSelected()) {//bu cevap secilmis ise true dondurur.(dogru cevap alimina gore puan arttirimi)
                toplamPuan += 20;//puan arttirim
                dogruSayisi++;//dogru sayisi arttirim
            } else {//yukaridaki secenek secilmemis ise false dondurur.(yanlisSayisini arttirir)
                yanlisSayisi++;//yanlisSayisi arttirim

            }
        }

        if (mevcut_soru_cevap == 4) {//mevcut_soru_cevap  4 ' e esit oldugunda true deger döndurur.
            if (bg.isSelected(null)) {//buttongroup icindeki radiobutonlarindan herhangi biri isaretlenmemis ise true dondurur.
                bosSayisi++;//bos sayisi arttirim
            } else if (radioButton[5].isSelected()) {//bu cevap secilmis ise true dondurur.(dogru cevap alimina gore puan arttirimi)
                toplamPuan += 20;//puan arttirim
                dogruSayisi++;
            } else {//yukaridaki secenek secilmemis ise false dondurur.(yanlisSayisini arttirir)
                yanlisSayisi++;//yanlisSayisi arttirim

            }
        }

        return false;//mevcut_soru_cevap degeri hicbirine esit degil ise false deger dondurur.
    }

    public void sinavSure() {//sinav suresi methodu
        timer = new Timer(100, new ActionListener() {//timer ekleme ,sure hizi ve aksiyon dinleyicisi ekleme
            public int second = 0; //saniye tanimi
            public int minute = 20; //dakika tanimi
            public int hour = 0;//saat tanimi

            @Override
            public void actionPerformed(ActionEvent e) {
                String zaman = hour + " : " + minute + " : " + second; //saat,dakika,saniye birlesimi
                sinavSure.setText(zaman); //zaman bilgisini sinavSure labelina ekleme

                if (second > 0 && second < 60) { //saniye 0 ve 60 'ın arasında oldugunda true deger dondurur
                    second--; //saniye azaltir
                }

                if (second == 0) { //saniye 0 oldugunda true deger dondurur

                    if (minute > 0 && minute < 60) {//dakika 0 ve 60 'ın arasında oldugunda true deger dondurur

                        minute--;//dakika azaltir
                        second = 59; //saniyeyi 59 yaparak tekrardan baslatir

                    }

                    if (hour == 0 && minute == 0 && second == 0) { //saat,dakika,saniye => 0 oldugunda true deger dondurur.
                        timer.stop();//timer'i durdurur
                        JOptionPane.showMessageDialog(null, "Sınav süreniz bitmiştir!"); //Sinav suresi bittigine dair bilgi mesaji veren dialog penceresi

                    }

                }

                if (hour > 23 || minute > 59 || second > 59) {//saat 23 'ten buyuk veya dakika 59 'dan buyuk veya saniye 59' dan buyuk oldugunda true deger dondurur.

                    timer.stop();//timer'i durdurur
                }

            }

        });
        timer.start();//timer'i baslatir. Eklenilmezse timer baslatilamaz.
    }
}
