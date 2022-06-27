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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author eren_
 */
public class SinavGiris extends JFrame implements ActionListener { //class

    JPanel p; //nesneleri tutmasi icin panel tanimliyoruz
    JLabel baslik, bilgi, bilgi2; // sinav giris formu icinde labellar ile sinav hakkinda bilgi veriyoruz
    JButton basla, hakkimizda; //sinav ekrani veya hakkinda kısmı formuna acabilmek icin buttonlar tanımlıyoruz

    public SinavGiris() { //constructor
        super("Sinav Giris Ekrani"); //tasarladigimiz ekranin yazisi

        setBounds(300, 800, 820, 600);//frame ekran boyutu
        setLocation(600, 200);// program çalistiginda frame ekran konumu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kapatma tusuna bastigimizda programin kapanmasini istiyoruz
        setResizable(false);//frame yeniden boyutlandırma engellenir
        setVisible(true);//frame görünür yapılır

        p = new JPanel();//paneli tanimliyoruz.
        p.setLayout(null);//nesneleri yerlestirmek için null olmali

        baslik = new JLabel("SINAVA HOŞGELDİNİZ");//sinav giris baslik labeli tanimlama ve  yazisi
        baslik.setFont(new Font("Verdana", Font.PLAIN, 20)); // label yazi tipi , yazı boyutu ayarlaması
        baslik.setForeground(Color.black);//label yazi rengi
        baslik.setBounds(300, 0, 250, 250);//label boyutlari

        bilgi = new JLabel("Sınav 5 sorudan oluşmakta olup 20 dakika süre verilmiştir !");//sinav giris bilgi labeli tanimlama veyazisi
        bilgi.setFont(new Font("Verdana", Font.PLAIN, 18));// label yazi tipi , yazı boyutu ayarlaması
        bilgi.setForeground(Color.red);//label yazi rengi
        bilgi.setBounds(150, 50, 600, 300);//label boyutlari

        bilgi2 = new JLabel("Sınava başlamak için Başla butonuna tıklayınız !");//sinav giris bilgi2 labeli tanimlama ve yazisi
        bilgi2.setFont(new Font("Verdana", Font.PLAIN, 18));// label yazi tipi , yazı boyutu ayarlaması
        bilgi2.setForeground(Color.blue);//label yazi rengi
        bilgi2.setBounds(200, 120, 500, 300);//label boyutlari

        basla = new JButton("BAŞLA");// basla butonu tanimlama ve yazisi
        basla.setBackground(Color.green);// buton rengi
        basla.setBounds(150, 350, 200, 100);//buton boyutu
        basla.addActionListener(this);//buton aksiyon dinleyicisi ekleme

        hakkimizda = new JButton("HAKKIMIZDA");// hakkimizda butonu tanimlama ve yazisi
        hakkimizda.setBackground(Color.ORANGE);// buton rengi
        hakkimizda.setBounds(450, 350, 200, 100);//buton boyutu
        hakkimizda.addActionListener(this);//buton aksiyon dinleyicisi ekleme

        p.add(baslik);//panel icine baslik labelini ekleme
        p.add(bilgi);//panel icine bilgi labelini ekleme
        p.add(bilgi2);//panel icine bilgi2 labelini ekleme
        p.add(basla);//panel icinebaslik butonunu ekleme
        p.add(hakkimizda);//panel icine hakkimizda butonunu ekleme
        add(p); // frame icine panel ekleme
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == basla) { //basla butonuna basildiginda yapilacak eylemler
            Sinavİslemleri bilgi = new Sinavİslemleri(); // Sinavİslemleri classindan nesne turetilerek referansini alma
            e.setSource(bilgi); //turetilen nesne ile Sinavİslemleri classini cagirma
            dispose();//frame ekranini kapatma
        }

        if (e.getSource() == hakkimizda) {//hakkimizda butonuna basildiginda yapilacak eylemler
            Hakkimizda kisi = new Hakkimizda();// Hakkimizda classindan nesne turetilerek referansini alma
            e.setSource(kisi);//turetilen nesne ile Hakkimizda classini cagirma
            dispose();//frame ekranini kapatma
        }
    }
}
