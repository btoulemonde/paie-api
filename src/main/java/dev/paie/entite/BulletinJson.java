package dev.paie.entite;

import java.math.BigDecimal;

public class BulletinJson {

	private BigDecimal primeExceptionelle;
	private int periodeId;
	private int remunerationEmployeId;
	private BigDecimal salaireBrut;

	/**
	 * Getter
	 * 
	 * @return the primeExceptionelle
	 */
	public BigDecimal getPrimeExceptionelle() {
		return primeExceptionelle;
	}

	/**
	 * Setter
	 * 
	 * @param primeExceptionelle
	 *            the primeExceptionelle to set
	 */
	public void setPrimeExceptionelle(BigDecimal primeExceptionelle) {
		this.primeExceptionelle = primeExceptionelle;
	}

	/**
	 * Getter
	 * 
	 * @return the periodeId
	 */
	public int getPeriodeId() {
		return periodeId;
	}

	/**
	 * Setter
	 * 
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(int periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * Getter
	 * 
	 * @return the salaireBrut
	 */
	public BigDecimal getSalaireBrut() {
		return salaireBrut;
	}

	/**
	 * Setter
	 * 
	 * @param salaireBrut
	 *            the salaireBrut to set
	 */
	public void setSalaireBrut(BigDecimal salaireBrut) {
		this.salaireBrut = salaireBrut;
	}

	/**
	 * Getter
	 * 
	 * @return the remunerationEmployeId
	 */
	public int getRemunerationEmployeId() {
		return remunerationEmployeId;
	}

	/**
	 * Setter
	 * 
	 * @param remunerationEmployeId
	 *            the remunerationEmployeId to set
	 */
	public void setRemunerationEmployeId(int remunerationEmployeId) {
		this.remunerationEmployeId = remunerationEmployeId;
	}

}
