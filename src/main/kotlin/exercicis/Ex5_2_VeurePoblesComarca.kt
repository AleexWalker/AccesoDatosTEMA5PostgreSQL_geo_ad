package exercicis

import classes.Comarca
import classes.Poblacio
import org.hibernate.cfg.Configuration
import java.awt.EventQueue
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JLabel
import javax.swing.JTextField
import java.awt.Color
import java.lang.NullPointerException
import java.util.logging.Level
import java.util.logging.LogManager
import javax.swing.JScrollPane

class Finestra : JFrame() {
    val etiqueta = JLabel("Comarca:")
    val etIni = JLabel("Introdueix la comarca:")
    val com = JTextField(15)
    val consultar = JButton("Consultar")
    val area = JTextArea()

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("HIBERNATE: Visualitzar Comarques i Pobles")
        setBounds(100, 100, 450, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout ())
        panell1.add(etIni)
        panell1.add(com)
        panell1.add(consultar)
        getContentPane().add(panell1, BorderLayout.NORTH)

        val panell2 = JPanel(BorderLayout ())
        panell2.add(etiqueta, BorderLayout.NORTH)
        area.setForeground(Color.blue)
        val scroll = JScrollPane(area)
        panell2.add(scroll, BorderLayout.CENTER)
        getContentPane().add(panell2, BorderLayout.CENTER)

        consultar.addActionListener() {
            etiqueta.setText("Comarca: " + com.text)
            visualitzaCom(com.text)
        }
    }
    fun visualitzaCom(comarca: String){
        // Instruccions per a llegir la comarca que arriba com a paràmetre (s'ha de deixar en un objecte Comarca).
        // S'ha de cuidar que si no exiteix la comarca, en el JTextArea es pose que no existeix.
        // La manera d'anar introduint informació en el JTextArea és area.append("Linia que es vol introduir ")
        try {
            area.text = ""

            LogManager.getLogManager().getLogger("").level = Level.SEVERE
            val sessio = Configuration().configure().buildSessionFactory().openSession()
            //val objecte = sessio.load("classes.Comarca", comarca) as Comarca

            val queryUsuario = sessio.createQuery("from Comarca where nom_c = '${com.text}'")
            val comarcaQuery = queryUsuario.uniqueResult() as Comarca

            area.append("La comarca " + comarcaQuery.nomC + " te " + comarcaQuery.poblacions.size + " pobles:\n\n")
            for (i in comarcaQuery.poblacions) {
                area.append(i.nom + "\n")
            }
        } catch (e : NullPointerException) {
            area.text = "¡Comarca no trobada, proba una altra vegada!"
        }
    }
}

fun main() {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}