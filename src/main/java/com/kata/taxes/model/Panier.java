package com.kata.taxes.model;


import java.util.ArrayList;
import java.util.List;

public class Panier {

	List<Produit> listeProduit = new ArrayList<>();
	Double prixTTC;
	Double totalTaxe;

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void ajouterProduitAuPanier(Produit produit) {
		this.listeProduit.add(produit);
	}

	public Double getPrixTTC() {
		return prixTTC;
	}

	public void setPrixTTC(Double prixTTC) {
		this.prixTTC = prixTTC;
	}

	public Double getTotalTaxe() {
		return totalTaxe;
	}

	public void setTotalTaxe(Double totalTaxe) {
		this.totalTaxe = totalTaxe;
	}
}
