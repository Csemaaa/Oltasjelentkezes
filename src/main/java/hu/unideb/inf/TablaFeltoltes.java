/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Vakcina;
import java.time.LocalDate;

/**
 *
 * @author Tamás �?dám
 */
public class TablaFeltoltes extends JPADAO{
    public static void feltolt()
    {
        JPADAO dao = new JPADAO();
        
        if (dao.getAllOrvos().size() == 0)
        {
            FelhasznaloSzemely bacsi = new FelhasznaloSzemely();
            bacsi.setNev("Bacsika teszt");
            bacsi.setEmail("bacsika@bacsikamail.hu");
            bacsi.setJelszo("dsaDSA321");
            bacsi.setNem(FelhasznaloSzemely.NemTipus.FERFI);
            bacsi.setTAJ(772722010);
            bacsi.setSzuletesiDatum(LocalDate.of(1999,02,02));

            dao.save(bacsi);
            //--------------------------------

            Orvos szurkalo = new Orvos();
            szurkalo.setNev("Dr. Ügyes Zsolt");
            szurkalo.setErtekeles((float) 5.0);

            dao.save(szurkalo);

            Orvos szurkalo2 = new Orvos();
            szurkalo2.setNev("Dr. Szuri Káta");
            szurkalo2.setErtekeles((float) 3.2);

            dao.save(szurkalo2);

            Orvos szurkalo3 = new Orvos();
            szurkalo3.setNev("Dr. Ollókezű Edward");
            szurkalo3.setErtekeles((float) 2.0);

            dao.save(szurkalo3);


            Orvos szurkalo4 = new Orvos();
            szurkalo4.setNev("Dr. Padlógáz");
            szurkalo4.setErtekeles((float) 4.2);

            dao.save(szurkalo4);


            Orvos szurkalo5 = new Orvos();
            szurkalo5.setNev("Dr. Harry Potter");
            szurkalo5.setErtekeles((float) 3.6);

            dao.save(szurkalo5);


            Orvos szurkalo6 = new Orvos();
            szurkalo6.setNev("Dr. Lassúkezű Dénes");
            szurkalo6.setErtekeles((float) 1.2);

            dao.save(szurkalo6);


            Vakcina vakcina1 = new Vakcina();

            vakcina1.setNev("Oltas1");
            vakcina1.setLeiras("Oltas1 leiras");
            dao.save(vakcina1);

            Vakcina vakcina2 = new Vakcina();

            vakcina2.setNev("Oltas2");
            vakcina2.setLeiras("Oltas2 leiras");
            dao.save(vakcina2);

            Vakcina vakcina3 = new Vakcina();

            vakcina3.setNev("Oltas3");
            vakcina3.setLeiras("Oltas3 leiras");
            dao.save(vakcina3);

            Vakcina vakcina4 = new Vakcina();

            vakcina4.setNev("Oltas4");
            vakcina4.setLeiras("Oltas4 leiras");
            dao.save(vakcina4);

            Vakcina vakcina5 = new Vakcina();

            vakcina5.setNev("Oltas5");
            vakcina5.setLeiras("Oltas5 leiras");
            dao.save(vakcina5);

            Vakcina vakcina6 = new Vakcina();

            vakcina6.setNev("Oltas6");
            vakcina6.setLeiras("Oltas6 leiras");
            dao.save(vakcina6);

            Vakcina vakcina7 = new Vakcina();

            vakcina7.setNev("Oltas7");
            vakcina7.setLeiras("Oltas7 leiras");
            dao.save(vakcina7);

            Vakcina vakcina8 = new Vakcina();

            vakcina8.setNev("Oltas8");
            vakcina8.setLeiras("Oltas8 leiras");
            dao.save(vakcina8);

            Vakcina vakcina9 = new Vakcina();

            vakcina9.setNev("Oltas9");
            vakcina9.setLeiras("Oltas9 leiras");
            dao.save(vakcina9);

            Vakcina vakcina10 = new Vakcina();

            vakcina10.setNev("Oltas10");
            vakcina10.setLeiras("Oltas10 leiras");
            dao.save(vakcina10);

            Vakcina vakcina11 = new Vakcina();

            vakcina11.setNev("Oltas11");
            vakcina11.setLeiras("Oltas11 leiras");
            dao.save(vakcina11);

            Vakcina vakcina12 = new Vakcina();

            vakcina12.setNev("Oltas12");
            vakcina12.setLeiras("Oltas12 leiras");
            dao.save(vakcina12);

            Vakcina vakcina13 = new Vakcina();

            vakcina13.setNev("Oltas13");
            vakcina13.setLeiras("Oltas13 leiras");
            dao.save(vakcina13);

            Vakcina vakcina14 = new Vakcina();

            vakcina14.setNev("Oltas14");
            vakcina14.setLeiras("Oltas14 leiras");
            dao.save(vakcina14);

            Vakcina vakcina15 = new Vakcina();

            vakcina15.setNev("Oltas15");
            vakcina15.setLeiras("Oltas15 leiras");
            dao.save(vakcina15);

            Vakcina vakcina16 = new Vakcina();

            vakcina16.setNev("Oltas16");
            vakcina16.setLeiras("Oltas16 leiras");
            dao.save(vakcina16);
        }      
    }
}
