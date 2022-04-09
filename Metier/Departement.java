package gestiondesprofesseurs.Metier;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Departement implements Serializable {

    private int idDepart;
    private String nomDepart;
    private Button supprimerBtn = new Button();
    private Button modifierBtn = new Button();




    public List<Professeur> professeurs;

    public Departement() {

    }

    public Departement(String nomDeprat)
    {
        this.nomDepart = nomDeprat;
        this.supprimerBtn = new Button("Supprimer");
        this.modifierBtn = new Button("Modifier");

    }

    public Departement(int idDepart, String nomDepart)
    {
        this.idDepart = idDepart;
        this.nomDepart = nomDepart;
        this.supprimerBtn = new Button("Supprimer");
        this.modifierBtn = new Button("Modifier");
    }
    public Button getSupprimerBtn() {
        return supprimerBtn;
    }

    public void setSupprimerBtn(Button supprimerBtn) {
        this.supprimerBtn = supprimerBtn;
    }

    public Button getModifierBtn() {
        return modifierBtn;
    }

    public void setModifierBtn(Button modifierBtn) {
        this.modifierBtn = modifierBtn;
    }

    public int getIdDepart()
    {
        return idDepart;
    }

    public String getNomDepart()
    {
        return nomDepart;
    }

    public void setNomDepart(String newNomDepart)
    {
        nomDepart = newNomDepart;
    }

    public java.util.List<Professeur> getProfesseurs()
    {
        if (professeurs == null)
            professeurs = new java.util.ArrayList<Professeur>();
        return professeurs;
    }

    public java.util.Iterator getIteratorProfesseur()
    {
        if (professeurs == null)
            professeurs = new java.util.ArrayList<Professeur>();
        return professeurs.iterator();
    }

    public void setProfesseurs(java.util.List<Professeur> p)
    {
        removeAllProfesseur();
        for (java.util.Iterator iter = p.iterator(); iter.hasNext();)
            addProfesseurDepart((Professeur)iter.next());
    }

    public void addProfesseurDepart(Professeur p)
    {
        if (p == null)
            return;
        if (this.professeurs == null)
            this.professeurs = new java.util.ArrayList<Professeur>();
        if (!this.professeurs.contains(p))
        {
            this.professeurs.add(p);
            p.setDepartement(this);
        }
    }

    public void removeProfesseurDepart(Professeur p) {
        if (p == null)
            return;
        if (this.professeurs != null)
            if (this.professeurs.contains(p))
            {
                this.professeurs.remove(p);
                p.setDepartement((Departement)null);
            }
    }

    public void removeAllProfesseur() {
        if (professeurs != null)
        {
            Professeur oldProfesseur;
            Iterator iter;
            for (iter = getIteratorProfesseur(); iter.hasNext();)
            {
                oldProfesseur = (Professeur)iter.next();
                iter.remove();
                oldProfesseur.setDepartement((Departement)null);
            }
        }
    }

    @Override
    public String toString() {
        return nomDepart;
    }

}
