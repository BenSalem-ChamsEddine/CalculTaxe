package com.kata.taxes.services;

import static com.kata.taxes.constants.ProduitConstants.MONTANT_DES_TAXES;
import static com.kata.taxes.constants.ProduitConstants.SEPARATION_AVEC_DEUX_POINTS;
import static com.kata.taxes.constants.ProduitConstants.TOTAL;

import com.kata.taxes.model.Panier;
import com.kata.taxes.model.Produit;
import java.math.BigDecimal;
import java.util.Arrays;
import javafx.util.Pair;

public class GestionPanierImpl implements GestionPanier {

	private GestionProduit gestionProduit;

	public GestionPanierImpl() {
		this.gestionProduit = new GestionProduitImpl();
	}


	@Override
	public Pair<BigDecimal, Double> calculerPrixTTCPanier(Panier panier) {
		BigDecimal totalTaxe = BigDecimal
			 .valueOf(panier.getListeProduit().stream().mapToDouble(p -> gestionProduit.calculerDifferenceAvecTaxe(p).doubleValue()).sum())
			 .setScale(2, BigDecimal.ROUND_HALF_UP);
		double totalPrixTTCPanier = panier.getListeProduit().stream().mapToDouble(p -> p.getPrixTTC().doubleValue()).sum();
		return new Pair<>(totalTaxe, totalPrixTTCPanier);
	}

	@Override
	public void afficherFactureDetaillee(Panier panier) {
		panier.getListeProduit().forEach(produit -> System.out.println(produit.toString()));
		Pair<BigDecimal, Double> pair = calculerPrixTTCPanier(panier);
		System.out.println(MONTANT_DES_TAXES + SEPARATION_AVEC_DEUX_POINTS + pair.getKey().doubleValue());
		System.out.println(TOTAL + SEPARATION_AVEC_DEUX_POINTS + pair.getValue());
	}

	@Override
	public Panier ajouterProduitsAuPanier(String... produit) {
		Panier panier = new Panier();
		Arrays.stream(produit).forEach(p -> {
			Produit produitAAjouter = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe(p);
			panier.ajouterProduitAuPanier(produitAAjouter);
		});

		return panier;
	}
}


