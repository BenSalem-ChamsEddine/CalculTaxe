package com.kata.taxes.services;

import static org.junit.Assert.assertEquals;

import com.kata.taxes.model.Panier;
import com.kata.taxes.model.Produit;
import java.math.BigDecimal;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

public class GestionPanierTest {

	private GestionPanier gestionPanier;

	@Before
	public void setUp() {
		gestionPanier = new GestionPanierImpl();
	}

	@Test
	public void calculerPrixTTCPanier() {
		Pair<BigDecimal, Double> prixTTCPanier = gestionPanier.calculerPrixTTCPanier(creerPanier());
		assertEquals(208.1, prixTTCPanier.getValue(), 0);
	}

	@Test
	public void calculerTotaltaxes() {
		String produit1Panier3 = "* 2 flacons de parfum importé à 27.99€";
		String produit2Panier3 = "* 1 flacon de parfum à 18.99€";
		String produit3Panier3 = "* 3 boîtes de pilules contre la migraine à 9.75€";
		String produit4Panier3 = "* 2 boîtes de chocolats importés à 11.25€";
		Panier panier3 = gestionPanier.ajouterProduitsAuPanier(produit1Panier3, produit2Panier3, produit3Panier3, produit4Panier3);
		Pair<BigDecimal, Double> prixTTCPanier = gestionPanier.calculerPrixTTCPanier(panier3);
		assertEquals(18.98, prixTTCPanier.getKey().doubleValue(), 0);
	}

	private Panier creerPanier() {
		Produit produit1 = new Produit("chocolat", BigDecimal.valueOf(2.45), 1, BigDecimal.valueOf(0), BigDecimal.valueOf(2.45));

		Produit produit2 = new Produit("livre", BigDecimal.valueOf(12.49), 2, BigDecimal.valueOf(10), BigDecimal.valueOf(27.5));
		Produit produit3 = new Produit("parfum", BigDecimal.valueOf(47.5), 3, BigDecimal.valueOf(25), BigDecimal.valueOf(178.15));
		Panier panier = new Panier();
		panier.ajouterProduitAuPanier(produit1);
		panier.ajouterProduitAuPanier(produit2);
		panier.ajouterProduitAuPanier(produit3);
		return panier;
	}

	@Test
	public void ajouterProduitsAuPanier() {
		String produit1 = "* 2 livres à 12.49€";
		String produit2 = "* 1 CD musical à 14.99€";
		String produit3 = "* 3 barres de chocolat à 0.85€";
		Panier panier = gestionPanier.ajouterProduitsAuPanier(produit1, produit2, produit3);
		assertEquals(3, panier.getListeProduit().size());
	}
}
