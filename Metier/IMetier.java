package gestiondesprofesseurs.Metier;

import java.util.List;

public interface IMetier {
    //professeur
    void addProfesseur(Professeur p);
    void deleteProfesseur(Professeur p);
    void editProfesseur(Professeur p1, Professeur p2);
    void affectProfToDepartement(Professeur p, Departement d);
    List<Professeur> getAllProfesseurs();
    List<Professeur> getProfesseursByMotCle(String motCle);
    List<Professeur> getProfesseursByDepartement(Departement d);

    //departement

    void addDepartement(Departement d);
    void deleteDepartement(Departement d);
    void modifyDepartement(Departement d1, Departement d2);
    List<Departement> getAllDepartement();





}
