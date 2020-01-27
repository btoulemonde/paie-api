package dev.paie.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.paie.controller.BulletinSalaireController;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Periode;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.vue.BulletinSalaireVue;

@Service
public class BulletinService {

	private static final Logger LOG = LoggerFactory.getLogger(BulletinSalaireController.class);

	private BulletinSalaireRepository bulletinSalaireRepository;
	private PeriodeRepository periodeRepository;
	private RemunerationEmployeRepository remunerationEmployeRepository;

	/**
	 * @param bulletinSalaireRepository
	 * @param periodeRepository
	 * @param remunerationEmployeRepository
	 */
	public BulletinService(BulletinSalaireRepository bulletinSalaireRepository, PeriodeRepository periodeRepository,
			RemunerationEmployeRepository remunerationEmployeRepository) {
		super();
		this.bulletinSalaireRepository = bulletinSalaireRepository;
		this.periodeRepository = periodeRepository;
		this.remunerationEmployeRepository = remunerationEmployeRepository;
	}

	public BulletinSalaire postBulletins(BigDecimal primeExceptionelle, int periodeId, int remunerationEmployeId) {

		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		bulletinSalaire.setPeriode(this.periodeRepository.findById(periodeId)
				.orElseThrow(() -> new EntityNotFoundException("periode non trouvée")));
		bulletinSalaire.setRemunerationEmploye(this.remunerationEmployeRepository.findById(remunerationEmployeId)
				.orElseThrow(() -> new EntityNotFoundException("profil rémunération  non trouvé")));

		bulletinSalaire.setPrimeExceptionnelle(primeExceptionelle);

		LOG.info("bulletin ajouté");
		return this.bulletinSalaireRepository.save(bulletinSalaire);

	}

	public List<BulletinSalaireVue> listerBulletins() {
		List<BulletinSalaire> bulletins = this.bulletinSalaireRepository.findAll();
		List<BulletinSalaireVue> bulletinsVue = new ArrayList<>();

		for (BulletinSalaire bulletin : bulletins) {
			String matricule = bulletin.getRemunerationEmploye().getMatricule();
			BigDecimal nbHeuresBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase();
			BigDecimal tauxBase = bulletin.getRemunerationEmploye().getGrade().getTauxBase();
			BigDecimal primeExeptionelle = bulletin.getPrimeExceptionnelle();
			Periode periode = bulletin.getPeriode();
			BigDecimal salaireBrut = nbHeuresBase.multiply(tauxBase).add(primeExeptionelle);
			List<Cotisation> cotisations = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations();

			BigDecimal netImposable = salaireBrut;
			for (Cotisation cotisation : cotisations) {
				if (!cotisation.getImposable() && cotisation.getTauxSalarial() != null) {
					netImposable = netImposable.subtract(salaireBrut.multiply(cotisation.getTauxSalarial()));
				}
			}
			BigDecimal netAPayer = netImposable;
			for (Cotisation cotisation : cotisations) {
				if (cotisation.getImposable() && cotisation.getTauxSalarial() != null) {
					netAPayer = netImposable.subtract(salaireBrut.multiply(cotisation.getTauxSalarial()));
				}

			}
			BulletinSalaireVue bulletinVue = new BulletinSalaireVue();
			bulletinVue.setDateCreation(ZonedDateTime.now());
			bulletinVue.setMatricule(matricule);
			bulletinVue.setNetAPayer(netAPayer);
			bulletinVue.setNetImposable(netImposable);
			bulletinVue.setPeriode(periode);
			bulletinVue.setSalaireBrut(salaireBrut);
			bulletinsVue.add(bulletinVue);

		}
		return bulletinsVue;

	}

}
