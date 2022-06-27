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

/**
 *
 * @author eren_
 */
public class Hakkimizda extends JFrame implements ActionListener {

    JLabel hazirlayan, adSoyad; //Hakkimizda formunda hazirlayan yazisi, hazirlayan kisinin ad ve soyad bilgisini veriyoruz 
    JButton sinavaDon; //SinavGiris formuna geri dondurmesini sagliyoruz

    public Hakkimizda() { //constructor

        super("Hakkımızda");
        setSize(500, 500);//frame ekran boyutu
        setLocation(700, 300);// program çalistiginda frame ekran konumu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kapatma tusuna bastigimizda programin kapanmasini istiyoruz
        setResizable(false);//yeniden boyutlandırma engellenir
        setVisible(true);//frame görünür yapılır
        setLayout(null);//nesneleri yerlestirmek için null olmali

        hazirlayan = new JLabel("HAZIRLAYAN");//hazirlayan labeli  tanimlama ve yazisi
        hazirlayan.setFont(new Font("Verdana", Font.PLAIN, 20));// label yazi tipi , yazı boyutu ayarlaması
        hazirlayan.setForeground(Color.black);//label yazi rengi
        hazirlayan.setBounds(175, 0, 200, 200);//label boyutlari

        adSoyad = new JLabel("MUHAMMED EREN DİBEK");//hazirlayanin adSoyad  labeli  tanimlama ve yazisi
        adSoyad.setFont(new Font("Verdana", Font.PLAIN, 20));// label yazi tipi , yazı boyutu ayarlaması
        adSoyad.setForeground(Color.blue);//label yazi rengi
        adSoyad.setBounds(120, 50, 300, 300);//label boyutlari

        sinavaDon = new JButton("SINAVA DÖN");//sinavaDon butonu tanimlama ve yazisi
        sinavaDon.setFont(new Font("Verdana", Font.PLAIN, 18));// buton yazi tipi , yazı boyutu ayarlaması
        sinavaDon.setBackground(Color.CYAN);//buton yazi rengi
        sinavaDon.setBounds(150, 300, 200, 100);//buton boyutlari
        sinavaDon.addActionListener(this);//buton aksiyon dinleyicisi ekleme

        add(hazirlayan);//frame icine hazirlayan labelini ekleme
        add(adSoyad);//frame icine adSoyad labelini ekleme
        add(sinavaDon);//frame icine sinavaDon butonunu ekleme
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == sinavaDon) { //sinavaDon butonuna basildiginda yapilacak eylemler
            SinavGiris giris = new SinavGiris();// SinavGiris classindan nesne turetilerek referansini alma
            a.setSource(giris);//turetilen nesne ile SinavGiris classini cagirma
            dispose();//frame ekranini kapatma
        }
    }
}
