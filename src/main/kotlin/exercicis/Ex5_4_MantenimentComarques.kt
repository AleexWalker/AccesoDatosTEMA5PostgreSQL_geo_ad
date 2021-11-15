package exercicis



import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.EventQueue

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import org.hibernate.Query
import org.hibernate.Session

//import classes.SessionFactoryUtil
import classes.Comarca
import org.hibernate.cfg.Configuration
import java.awt.event.WindowEvent
import java.util.logging.Level
import java.util.logging.LogManager

import kotlin.system.exitProcess

class FinestraMantenimentComarques : JFrame() {
    val etIni = JLabel("Manteniment de COMARQUES")
    val etNom = JLabel("Nom comarca")
    val etProv = JLabel("Nom província")

    val nomComarca = JTextField()
    val nomProvincia = JTextField()

    val primer = JButton("<<")
    val anterior = JButton("<")
    val seguent = JButton(">")
    val ultim = JButton(">>")

    val eixir = JButton("Eixir")

    val pDalt = JPanel(FlowLayout())
    val pCentre = JPanel(GridLayout(8, 0))
    val pDades = JPanel(GridLayout(2, 2))
    val pBotonsMov = JPanel(FlowLayout())
    val pEixir = JPanel(FlowLayout())

    val sessio = Configuration().configure().buildSessionFactory().openSession()
    //val s = SessionFactoryUtil.sessionFactory.openSession();

    var llistaComarques = ArrayList<Comarca>()
    var indActual = 0;

    init {
        defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        setTitle("HIBERNATE: Manteniment Comarques")

        setBounds(100, 100, 350, 400)
        setLayout(BorderLayout())

        getContentPane().add(pCentre, BorderLayout.CENTER)
        getContentPane().add(JPanel(FlowLayout()), BorderLayout.WEST)
        getContentPane().add(JPanel(FlowLayout()), BorderLayout.EAST)
        getContentPane().add(pEixir, BorderLayout.SOUTH)

        pDalt.add(etIni)
        pCentre.add(pDalt)

        pDades.add(etNom)
        pDades.add(nomComarca)
        pDades.add(etProv)
        pDades.add(nomProvincia)
        pCentre.add(pDades)

        nomComarca.setEditable(false)
        nomProvincia.setEditable(false)

        pCentre.add(JPanel(FlowLayout()))

        pBotonsMov.add(primer)
        pBotonsMov.add(anterior)
        pBotonsMov.add(seguent)
        pBotonsMov.add(ultim)
        pCentre.add(pBotonsMov)

        pEixir.add(eixir)

        llistaComarques = agafarComarques()
        visComarca()

        primer.addActionListener() { primer() }
        anterior.addActionListener() { anterior() }
        seguent.addActionListener() { seguent() }
        ultim.addActionListener() { ultim() }

        eixir.addActionListener() { eixir()}
    }

    fun agafarComarques(): ArrayList<Comarca> {
        var llista = ArrayList<Comarca>()
        // ací aniran les sentències per a omplir (i retornar) la llista de comarques

        val queryComarcas = sessio.createQuery("select nomC from Comarca")
        for (comarca in queryComarcas.list()) {
            comarca as Comarca
            llista.add(comarca)
        }
        return llista
    }

    fun visComarca() {
        // Mètode per a visualitzar la comarca marcada per l'índex que ve com a paràmetre

        controlBotons()
    }

    fun primer() {

        visComarca()
    }

    fun anterior() {

        visComarca()
    }

    fun seguent() {

        visComarca()
    }

    fun ultim() {

        visComarca()
    }

    fun controlBotons() {
        // Mètode per a habilitar/deshabilitar els botons anterior i següent, si s'està en la primera o última comarca
        // No us oblideu d'habilitar-los quan toque

    }

    fun eixir() {
        //accions per a tancar i per a eixir

        exitProcess(0)
    }
}

fun main() {
    LogManager.getLogManager().getLogger("").level = Level.SEVERE
    EventQueue.invokeLater {
        FinestraMantenimentComarques().isVisible = true
    }
}

