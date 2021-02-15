package com.kata.taxes.services;

import com.kata.taxes.model.Categorie;
import com.kata.taxes.model.Produit;
import java.math.BigDecimal;

public interface GestionProduit {


	Produit ajouterProduitAuPanierEtCalculeDeTaxe(String produit);

	BigDecimal calculerTotalPourcentageTaxe(Produit produit);

	Categorie extraireCategorieDepuisNomProduit(Produit produit);

	BigDecimal calculerDifferenceAvecTaxe(Produit produit);

	BigDecimal calculerPrixTTC(Produit produit);

	boolean verifierValiditeeProduit(Produit produit);


}
