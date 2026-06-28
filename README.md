# TP JPA Hibernate Spring Data

## Description
Mini projet Spring Boot démontrant l'utilisation de JPA/Hibernate
avec Spring Data pour la gestion d'une base de données.

## Technologies utilisées
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- H2 Database (en mémoire)
- Lombok
- Maven

## Structure du projet
src/main/java/ma/projet/tp_jpa/

├── entities/

│   ├── Product.java

│   ├── Patient.java

│   ├── Medecin.java

│   ├── RendezVous.java

│   ├── Consultation.java

│   ├── AppUser.java

│   ├── AppRole.java

│   └── StatusRDV.java (Enum)

├── repositories/

│   ├── ProductRepository.java

│   ├── PatientRepository.java

│   ├── MedecinRepository.java

│   ├── RendezVousRepository.java

│   ├── ConsultationRepository.java

│   ├── AppUserRepository.java

│   └── AppRoleRepository.java

└── TpJpaApplication.java

## Entités et Relations
- **Product** : entité simple avec id, name, price, quantity
- **Patient** : id, nom, dateNaissance, malade, score
- **Medecin** : id, nom, email, specialite
- **RendezVous** : @ManyToOne Patient, @ManyToOne Medecin
- **Consultation** : @OneToOne RendezVous
- **AppUser** : @ManyToMany AppRole

## Opérations testées
- ✅ Ajouter des enregistrements
- ✅ Consulter tous les enregistrements
- ✅ Consulter par id
- ✅ Rechercher par critères
- ✅ Mettre à jour
- ✅ Supprimer

## Configuration H2
```properties
spring.datasource.url=jdbc:h2:mem:productsdb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Accès H2 Console
- URL : http://localhost:8080/h2-console
- JDBC URL : jdbc:h2:mem:productsdb
- Username : sa
- Password : (vide)

## Lancer le projet
```bash
mvn spring-boot:run
```

## Auteur
Wafaa Boukchouch