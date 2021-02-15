package com.kata.taxes.model;


import java.util.ArrayList;
import java.util.List;

public class Panier {

	List<Produit> listeProduit = new ArrayList<Produit>();
	Double prixTTC;
	Double TotalTaxe;

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
		return TotalTaxe;
	}

	public void setTotalTaxe(Double totalTaxe) {
		TotalTaxe = totalTaxe;
	}
}
