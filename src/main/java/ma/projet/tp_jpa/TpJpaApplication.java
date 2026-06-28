package ma.projet.tp_jpa;

import ma.projet.tp_jpa.entities.*;
import ma.projet.tp_jpa.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
public class TpJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ProductRepository productRepo,
            PatientRepository patientRepo,
            MedecinRepository medecinRepo,
            RendezVousRepository rdvRepo,
            ConsultationRepository consultRepo,
            AppUserRepository userRepo,
            AppRoleRepository roleRepo
    ) {
        return args -> {

            // ══ PRODUITS ══════════════════════════════════════
            System.out.println("\n=== Ajouter des produits ===");
            productRepo.save(new Product(null, "Laptop",  12000.0, 5));
            productRepo.save(new Product(null, "Phone",    5000.0, 10));
            productRepo.save(new Product(null, "Tablet",   3500.0, 8));
            productRepo.findAll().forEach(System.out::println);

            // ══ PATIENTS ══════════════════════════════════════
            System.out.println("\n=== Ajouter des patients ===");
            patientRepo.save(new Patient(null, "Ahmed",   new Date(), false, 85));
            patientRepo.save(new Patient(null, "Fatima",  new Date(), true,  60));
            patientRepo.save(new Patient(null, "Youssef", new Date(), false, 92));
            patientRepo.findAll().forEach(System.out::println);

            System.out.println("\n=== Patients malades ===");
            patientRepo.findByMalade(true).forEach(System.out::println);

            // ══ MEDECINS ══════════════════════════════════════
            System.out.println("\n=== Ajouter des médecins ===");
            medecinRepo.save(new Medecin(null, "Dr. Hassan",  "hassan@hopital.ma",  "Cardiologie"));
            medecinRepo.save(new Medecin(null, "Dr. Salma",   "salma@hopital.ma",   "Pédiatrie"));
            medecinRepo.findAll().forEach(System.out::println);

            // ══ RENDEZ-VOUS ═══════════════════════════════════
            System.out.println("\n=== Ajouter des rendez-vous ===");
            Patient p1 = patientRepo.findById(1L).orElseThrow();
            Medecin m1 = medecinRepo.findByNom("Dr. Hassan");

            RendezVous rdv = new RendezVous();
            rdv.setDate(new Date());
            rdv.setStatus(StatusRDV.PENDING);
            rdv.setPatient(p1);
            rdv.setMedecin(m1);
            rdvRepo.save(rdv);
            rdvRepo.findAll().forEach(r ->
                    System.out.println("RDV : " + r.getId() + " | " + r.getStatus()
                            + " | " + r.getPatient().getNom()
                            + " → " + r.getMedecin().getNom())
            );

            // ══ CONSULTATION ══════════════════════════════════
            System.out.println("\n=== Ajouter une consultation ===");
            RendezVous rdv1 = rdvRepo.findAll().get(0);
            Consultation c = new Consultation();
            c.setDateConsultation(new Date());
            c.setRapport("Patient en bonne santé");
            c.setRendezVous(rdv1);
            consultRepo.save(c);
            rdv1.setStatus(StatusRDV.DONE);
            rdvRepo.save(rdv1);
            System.out.println("Consultation sauvegardée ✓");

            // ══ USERS & ROLES ══════════════════════════════════
            System.out.println("\n=== Ajouter des rôles et utilisateurs ===");
            AppRole admin = roleRepo.save(new AppRole(null, "ADMIN"));
            AppRole user  = roleRepo.save(new AppRole(null, "USER"));

            AppUser u1 = new AppUser(null, "wafaa", "1234",
                    java.util.List.of(admin, user));
            AppUser u2 = new AppUser(null, "ahmed", "5678",
                    java.util.List.of(user));
            userRepo.save(u1);
            userRepo.save(u2);
            userRepo.findAll().forEach(u ->
                    System.out.println("User: " + u.getUsername()
                            + " | Roles: " + u.getRoles().stream()
                            .map(AppRole::getRoleName).toList())
            );
        };
    }
}
