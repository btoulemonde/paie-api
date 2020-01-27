package dev.paie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.paie.controller.BulletinSalaireController;
import dev.paie.entite.Periode;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Component

public class Startup {

	private static final Logger LOG = LoggerFactory.getLogger(BulletinSalaireController.class);

	private BulletinSalaireRepository bulletinSalaireRepository;
	private PeriodeRepository periodeRepository;
	private RemunerationEmployeRepository remunerationEmployeRepository;

	/**
	 * @param bulletinSalaireRepository
	 * @param periodeRepository
	 * @param remunerationEmployeRepository
	 */
	public Startup(BulletinSalaireRepository bulletinSalaireRepository, PeriodeRepository periodeRepository,
			RemunerationEmployeRepository remunerationEmployeRepository) {
		super();
		this.bulletinSalaireRepository = bulletinSalaireRepository;
		this.periodeRepository = periodeRepository;
		this.remunerationEmployeRepository = remunerationEmployeRepository;
	}

	@EventListener(ContextRefreshedEvent.class)

	public void init(){

		LOG.info("Démarrage de l'application");
		
		if(this.periodeRepository.count() == 0){
			List<Periode> periodes = new ArrayList<Periode>();
			periodes.add(new Periode(LocalDate.of(2019, 01, 01), LocalDate.of(2019, 01, 31)));
			periodes.add(new Periode(LocalDate.of(2019, 02, 01), LocalDate.of(2019, 02, 28)));
			periodes.add(new Periode(LocalDate.of(2019, 03, 01), LocalDate.of(2019, 03, 31)));
			this.periodeRepository.saveAll(periodes);
			
			
		}

		if (this.bulletinSalaireRepository.count() == 0) {
			Periode p1 = this.periodeRepository.findById(1)
					.orElseThrow(() -> new EntityNotFoundException("Periode non trouvée"));
			Periode p2 = this.periodeRepository.findById(2)
					.orElseThrow(() -> new EntityNotFoundException("Periode non trouvée"));
			RemunerationEmploye r1 = this.remunerationEmployeRepository.findById(2)
					.orElseThrow(() -> new EntityNotFoundException("remu non trouvée"));

			RemunerationEmploye r2 = this.remunerationEmployeRepository.findById(3)
					.orElseThrow(() -> new EntityNotFoundException("remu non trouvée"));
			
			bulletinSalaireRepository

		}

	}

}