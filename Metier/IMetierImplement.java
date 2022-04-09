package gestiondesprofesseurs.Metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IMetierImplement implements IMetier{
    Connection connection = SignletonConnexionDB.getConnection();


    @Override
    public void addProfesseur(Professeur p) {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO professeur(idDeprat,nomProf,prenom,cin,adresse,telephone,email,dateRecrutement) values(?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,p.getNomProf());
            preparedStatement.setString(3,p.getPrenom());
            preparedStatement.setString(4,p.getCin());
            preparedStatement.setString(5,p.getAdresse());
            preparedStatement.setString(6,p.getTelephone());
            preparedStatement.setString(7,p.getEmail());
            preparedStatement.setDate(8,  Date.valueOf(p.getDateRecrutement()));
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

}

    @Override
    public void deleteProfesseur(Professeur p) {

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM professeur WHERE idProf = ?");
            preparedStatement.setInt(1,p.getIdProf());
            preparedStatement.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void editProfesseur(Professeur p1, Professeur p2) {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE professeur SET nomProf=?, prenom=?, cin=?, adresse=?, telephone=?, email=?, dateRecrutement=? WHERE idProf=?");
            preparedStatement.setString(1, p2.getNomProf());
            preparedStatement.setString(2, p2.getPrenom());
            preparedStatement.setString(3, p2.getCin());
            preparedStatement.setString(4, p2.getAdresse());
            preparedStatement.setString(5, p2.getTelephone());
            preparedStatement.setString(6, p2.getEmail());
            preparedStatement.setDate(7, Date.valueOf(p2.getDateRecrutement()));
            preparedStatement.setInt(8,p1.getIdProf());
            preparedStatement.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void affectProfToDepartement(Professeur p, Departement d) {
        p.setDepartement(d);
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE professeur SET idDeprat=? WHERE idProf=?");
            preparedStatement.setInt(1, d.getIdDepart());
            preparedStatement.setInt(2, p.getIdProf());
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public List<Professeur> getAllProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM professeur");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                Professeur prof = new Professeur(rs.getInt("idProf"), rs.getString("nomProf"), rs.getString("prenom"), rs.getString("cin"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("email"), rs.getDate("dateRecrutement").toLocalDate());
                professeurs.add(prof);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return professeurs;

    }

    @Override
    public List<Professeur> getProfesseursByMotCle(String motCle) {
        List<Professeur> professeurs = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM professeur WHERE" +
                    " nomProf LIKE ? OR prenom LIKE ? OR cin " +
                    "LIKE ? OR adresse LIKE ? OR telephone" +
                    " LIKE ? OR email LIKE ? OR dateRecrutement LIKE ?");
            preparedStatement.setString(1,"%"+motCle+"%");
            preparedStatement.setString(2,"%"+motCle+"%");
            preparedStatement.setString(3,"%"+motCle+"%");
            preparedStatement.setString(4,"%"+motCle+"%");
            preparedStatement.setString(5,"%"+motCle+"%");
            preparedStatement.setString(6,"%"+motCle+"%");
            preparedStatement.setString(7,"%"+motCle+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                PreparedStatement ptsm = connection.prepareStatement("SELECT * FROM departement WHERE idDeprat=?");
                ptsm.setInt(1, rs.getInt("idDeprat"));
                ResultSet result = ptsm.executeQuery();
                Departement departement = new Departement();
                if(result.next())
                {
                    departement = new Departement(result.getInt("idDeprat"), result.getString("nomDepart"));
                }
                Professeur p = new Professeur(rs.getInt("idProf"), rs.getString("nomProf"), rs.getString("prenom"), rs.getString("cin"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("email"), rs.getDate("dateRecrutement").toLocalDate());
                p.setDepartement(departement);
                professeurs.add(p);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return professeurs;

    }

    @Override
    public List<Professeur> getProfesseursByDepartement(Departement d) {

        List<Professeur> professeurs = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from professeur where idDeprat=?");
            preparedStatement.setInt(1, d.getIdDepart());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                Professeur prof = new Professeur(rs.getInt("idProf"), rs.getString("nomProf"), rs.getString("prenom"), rs.getString("cin"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("email"), rs.getDate("dateRecrutement").toLocalDate());
                prof.setDepartement(d);
                professeurs.add(prof);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return professeurs;

    }

    @Override
    public void addDepartement(Departement d) {

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO departement(nomDepart) values(?)");
            preparedStatement.setString(1,d.getNomDepart());
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteDepartement(Departement d) {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM departement WHERE idDeprat = ?");
            preparedStatement.setInt(1,d.getIdDepart());

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void modifyDepartement(Departement d1, Departement d2) {

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE departement SET nomDepart=? WHERE idDeprat=?");
            preparedStatement.setString(1, d2.getNomDepart());
            preparedStatement.setInt(2, d1.getIdDepart());

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public List<Departement> getAllDepartement() {
        List<Departement> departements = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departement");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                Departement d = new Departement(rs.getInt("idDeprat"), rs.getString("nomDepart"));
                departements.add(d);
                List<Professeur> professeurList = getProfesseursByDepartement(d);
                d.setProfesseurs(professeurList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return departements;
    }


}

