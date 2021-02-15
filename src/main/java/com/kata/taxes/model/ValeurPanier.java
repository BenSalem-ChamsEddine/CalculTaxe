package com.kata.taxes.model;

import java.math.BigDecimal;

public class ValeurPanier {

	private BigDecimal montantTotalPanier;
	private BigDecimal totalTaxesPanier;


	public ValeurPanier(BigDecimal montantTotalPanier, BigDecimal totalTaxesPanier) {
		this.montantTotalPanier = montantTotalPanier;
		this.totalTaxesPanier = totalTaxesPanier;
	}

	public BigDecimal getMontantTotalPanier() {
		return montantTotalPanier;
	}

	public void setMontantTotalPanier(BigDecimal montantTotalPanier) {
		this.montantTotalPanier = montantTotalPanier;
	}

	public BigDecimal getTotalTaxesPanier() {
		return totalTaxesPanier;
	}

	public void setTotalTaxesPanier(BigDecimal totalTaxesPanier) {
		this.totalTaxesPanier = totalTaxesPanier;
	}
}
