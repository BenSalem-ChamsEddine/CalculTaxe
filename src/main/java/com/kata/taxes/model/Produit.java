package com.kata.taxes.model;

import static com.kata.taxes.constants.ProduitConstants.EURO_DEVISE;
import static com.kata.taxes.constants.ProduitConstants.SEPARATION_AVEC_DEUX_POINTS;
import static com.kata.taxes.constants.ProduitConstants.TTC;

import com.kata.taxes.services.GestionProduit;
import java.math.BigDecimal;

public class Produit {

	private String nom;
	private Categorie categorie;
	private BigDecimal prixHorsTaxe;
	private String importe;
	private BigDecimal prixTTC;
	private BigDecimal pourcentageTaxe;
	private int quantite;

	public Produit() {
	}

	public Produit(String nom, BigDecimal prixHorsTaxe, int quantite, BigDecimal pourcentageTaxe, BigDecimal prixTTC) {
		this.nom = nom;
		this.prixHorsTaxe = prixHorsTaxe;
		this.quantite = quantite;
		this.pourcentageTaxe = pourcentageTaxe;
		this.prixTTC = prixTTC;
	}

	public Produit(int quantite, String nom, BigDecimal prixHorsTaxe, String estImporte) {
		this.quantite = quantite;
		this.nom = nom;
		this.prixHorsTaxe = prixHorsTaxe;
		this.importe = estImporte;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public BigDecimal getPrixHorsTaxe() {
		return prixHorsTaxe;
	}

	public void setPrixHorsTaxe(BigDecimal prixHorsTaxe) {
		this.prixHorsTaxe = prixHorsTaxe;
	}

	public BigDecimal getPrixTTC() {
		return prixTTC;
	}

	public void setPrixTTC(BigDecimal prixTTC) {
		this.prixTTC = prixTTC;
	}

	public BigDecimal getPourcentageTaxe() {
		return pourcentageTaxe == null ? BigDecimal.ZERO : pourcentageTaxe;
	}

	public void setPourcentageTaxe(BigDecimal pourcentageTaxe) {
		this.pourcentageTaxe = pourcentageTaxe;
	}

	public Produit calculerPrixTtc(GestionProduit gestionProduit) {
		this.prixTTC = gestionProduit.calculerPrixTTC(this);
		return this;
	}

	public Produit calculerTotalPourcentageTaxe(GestionProduit gestionProduit) {
		this.pourcentageTaxe = gestionProduit.calculerTotalPourcentageTaxe(this);
		return this;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void definirCategorie(GestionProduit gestionProduit) {
		this.categorie = gestionProduit.extraireCategorieDepuisNomProduit(this);
	}

	public String getImporte() {
		return importe;
	}


	@Override
	public String toString() {
		return "* " + this.quantite + " " +
			 this.nom + " " +
			 this.getImporte() + " à " +
			 this.prixHorsTaxe +
			 EURO_DEVISE +
			 SEPARATION_AVEC_DEUX_POINTS +
			 this.prixTTC + EURO_DEVISE + " " +
			 TTC;
	}
}
