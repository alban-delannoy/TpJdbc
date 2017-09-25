package fr.univ_amu.iut;

import fr.univ_amu.iut.beans.Etudiant;
import fr.univ_amu.iut.beans.Module;
import fr.univ_amu.iut.beans.Prof;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static fr.univ_amu.iut.TestJDBC.CONNECT_URL;

/**
 * Created by d16015154 on 25/09/17.
 */
public class TestAsso1 {
    static final String req = "SELECT * " +
            "FROM PROF "; //+
    //"WHERE VILLE_ET = 'AIX-EN-PROVENCE'";

    public static void main(String[] args) throws SQLException {
        // Connexion a la base
        System.out.println("Connexion a " + CONNECT_URL);
        try (Connection conn = ConnexionUnique.getInstance().getConnection()){
            System.out.println("Connecte\n");
            // Creation d'une instruction SQL
            Statement stmt = conn.createStatement();
            // Execution de la requete
            System.out.println("Execution de la requete : " + req );
            ResultSet rset = stmt.executeQuery(req);
            // Affichage du resultat
            ArrayList<Prof> profs = new ArrayList<Prof>();
            while (rset.next()){
                Prof prof = new Prof();
                Module module = new Module();
                System.out.print(rset.getInt("NUM_PROF") + " ");
                System.out.print(rset.getString("NOM_PROF") + " ");
                System.out.println(rset.getString("PRENOM_PROF"));
                module.setCode(rset.getString("MAT_SPEC"));
                prof.setNomProf(rset.getString("NOM_PROF"));
                prof.setPrenomProf(rset.getString("PRENOM_PROF"));
                prof.setVilleProf(rset.getString("VILLE_PROF"));
                prof.setAdrProf(rset.getString("ADR_PROF"));
                prof.setCpProf(rset.getString("CP_PROF"));
                profs.add(prof);
                System.out.println(prof.toString());
            }
            // Fermeture de l'instruction (liberation des ressources)
            stmt.close();
            System.out.println("\nOk.\n");
        } catch (SQLException e) {
            e.printStackTrace();// Arggg!!!
            System.out.println(e.getMessage() + "\n");
        }
    }
}
