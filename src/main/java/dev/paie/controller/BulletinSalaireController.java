package dev.paie.controller;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.BulletinJson;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@RestController
@RequestMapping("bulletin")
public class BulletinSalaireController {
	private static final Logger LOG = LoggerFactory.getLogger(BulletinSalaireController.class);

	private BulletinSalaireRepository bulletinSalaireRepository;
	private GradeRepository gradeRepository;
	private PeriodeRepository periodeRepository;
	private ProfilRemunerationRepository profilRemunerationRepository;
	private RemunerationEmployeRepository remunerationEmployeRepository;

	/**
	 * @param bulletinSalaireRepository
	 * @param gradeRepository
	 * @param periodeRepository
	 * @param profilRemunerationRepository
	 * @param remunerationEmployeRepository
	 */
	public BulletinSalaireController(BulletinSalaireRepository bulletinSalaireRepository,
			GradeRepository gradeRepository, PeriodeRepository periodeRepository,
			ProfilRemunerationRepository profilRemunerationRepository,
			RemunerationEmployeRepository remunerationEmployeRepository) {
		super();
		this.bulletinSalaireRepository = bulletinSalaireRepository;
		this.gradeRepository = gradeRepository;
		this.periodeRepository = periodeRepository;
		this.profilRemunerationRepository = profilRemunerationRepository;
		this.remunerationEmployeRepository = remunerationEmployeRepository;
	}

	@PostMapping
	public void CreationBulletin(@RequestBody BulletinJson bullRecu) {
		Periode periode = new Periode();
		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		boolean test = true;
		if (this.periodeRepository.existsById(bullRecu.getPeriodeId())) {
			periode = this.periodeRepository.findById(bullRecu.getPeriodeId()).get();
		} else {
			test = false;
			throw new EntityNotFoundException("periode non trouvée");
		}

		if (this.remunerationEmployeRepository.existsById(bullRecu.getRemunerationEmployeId())) {
			remunerationEmploye = this.remunerationEmployeRepository.findById(bullRecu.getRemunerationEmployeId())
					.get();

		} else {
			test = false;
			throw new EntityNotFoundException("profil rémunération  non trouvé");
		}

		if (test == true) {
			BulletinSalaire bulletinSalaire = new BulletinSalaire();
			bulletinSalaire.setPeriode(periode);
			bulletinSalaire.setRemunerationEmploye(remunerationEmploye);
			bulletinSalaire.setPrimeExceptionnelle(bullRecu.getPrimeExceptionelle());
			this.bulletinSalaireRepository.save(bulletinSalaire);
			ResponseEntity.status(HttpStatus.CREATED).body("le nouveau bulletin a bien été ajouté en bdd");
			LOG.info("bulletin ajouté");

		}

	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<String> reservationPresent(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur " + exception.getMessage());
	}

}
