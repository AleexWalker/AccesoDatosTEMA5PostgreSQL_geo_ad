package exemples

import classes.Comarca
import org.hibernate.cfg.Configuration
import java.util.logging.Level
import java.util.logging.LogManager

fun main(args: Array<String>) {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    val sessio = Configuration().configure().buildSessionFactory().openSession()

    val com = sessio.load("classes.Comarca", "Alt Maestrat") as Comarca
    print("Comarca " + com.nomC + ": ")
    print(com.provincia)
    println(" (" + com.poblacions.size + " pobles)")

    for (p in com.poblacions)
        println("\t" + p.nom)
    sessio.close()
}