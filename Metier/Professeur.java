package gestiondesprofesseurs.Metier;

import gestiondesprofesseurs.Metier.Departement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Professeur  implements Serializable {
    private int idProf;
    private String nomProf;
    private String prenom;
    private String cin;
    private String adresse;
    private String telephone;
    private String email;
    private LocalDate dateRecrutement;

    /** @pdRoleInfo migr=no name=metier.Departement assc=association1 mult=1..1 */
    private Departement departement;

    public Professeur() {
        // TODO: implement
    }

    public Professeur(String nomProf, String prenom, String cin, String adresse, String telephone, String email, LocalDate dateRecrutement)
    {
        this.nomProf = nomProf;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.dateRecrutement = dateRecrutement;
    }
    public Professeur(int idProf, String nomProf, String prenom, String cin, String adresse, String telephone, String email, LocalDate dateRecrutement)
    {
        this.idProf = idProf;
        this.nomProf = nomProf;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.dateRecrutement = dateRecrutement;
    }

    public int getIdProf()
    {
        return idProf;
    }

    public String getNomProf()
    {
        return nomProf;
    }

    public void setNomProf(String newNomProf)
    {
        nomProf = newNomProf;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String newPrenom)
    {
        prenom = newPrenom;
    }

    public String getCin()
    {
        return cin;
    }

    public void setCin(String newCin)
    {
        cin = newCin;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String newAdresse)
    {
        adresse = newAdresse;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String newTelephone)
    {
        telephone = newTelephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public LocalDate getDateRecrutement()
    {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate newDateRecrutement)
    {
        dateRecrutement = newDateRecrutement;
    }

    public Departement getDepartement()
    {
        return departement;
    }

    public void setDepartement(Departement newDepartement)
    {
        if (this.departement == null || !this.departement.equals(newDepartement))
        {
            if (this.departement != null)
            {
                Departement oldDepartement = this.departement;
                this.departement = null;
                oldDepartement.removeProfesseurDepart(this);
            }
            if (newDepartement != null)
            {
                this.departement = newDepartement;
                this.departement.addProfesseurDepart(this);
            }
        }
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "idProf=" + idProf +
                ", nomProf='" + nomProf + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", dateRecrutement=" + dateRecrutement +
                '}';
    }

}
