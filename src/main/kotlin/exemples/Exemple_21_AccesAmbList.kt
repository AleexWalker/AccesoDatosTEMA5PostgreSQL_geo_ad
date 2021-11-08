package exemples

import classes.Comarca
import org.hibernate.cfg.Configuration
import java.util.logging.Level
import java.util.logging.LogManager

fun main(args: Array<String>) {
    LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
    val sessio = Configuration().configure().buildSessionFactory().openSession()
    val q = sessio.createQuery ("from Comarca")

    for (c in q.list()) {
        c as Comarca
        println(c.nomC + " --- " + c.provincia)
    }

    val llista = q.list ()
    /*val it = llista.iterator ()
    while (it.hasNext()) {
        val com = it.next() as Comarca
        println(com.nomC + " - " + com.provincia)
    }*/
    sessio.close()
}

