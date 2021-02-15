package com.kata.taxes.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.kata.taxes.model.Categorie;
import com.kata.taxes.model.Produit;
import com.kata.taxes.services.implementation.GestionProduitImpl;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class GestionProduitTest {

	private static final String CHOCOLAT = "chocolat";
	private GestionProduit gestionProduit;

	@Before
	public void setUp() {
		gestionProduit = new GestionProduitImpl();
	}

	@Test
	public void doitRetournerMemePrixPourCategorieNourriture() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 barres de chocolat à 0.85€");
		assertEquals(2.55, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxeCinqPourCentPourCategorieNourritureImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 2 chocolats importé  à 10.00€");
		assertEquals(5, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerPrixAvecTaxePourCategorieNourritureImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 2 boîtes de chocolats importée à 10€");
		assertEquals(21, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitRetournerMemePrixPourCategorieMedicament() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 boîtes de pilules contre la migraine à 9.75€");
		assertEquals(29.25, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxeCinqPourCentPourCategorieMedicamentImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 boîtes de pilules contre la migraine importés à 9.75€");
		assertEquals(5, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerPrixAvecTaxePourCategorieMedicamentImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 boîtes de pilules contre la migraine importés à 9.75€");
		assertEquals(30.75, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxeDixPourCentPourCategorieLivre() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 2 livres à 12.49€");
		assertEquals(10, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxe15PourCentPourCategorieLivreImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 2 livres importés à 12.49€");
		assertEquals(15, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerPrixAvecTaxePourCategorieLivre() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 2 livres à 12.49€");
		assertEquals(27.5, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxe20PourCentPourCategorieAutre() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 1 flacon de parfum à 18.99€");
		assertEquals(20, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerTaxe25PourCentPourCategorieAutreImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 flacons de parfum importé à 47.50€");
		assertEquals(25, produit.getPourcentageTaxe().doubleValue(), 0);
	}

	@Test
	public void doitRetournerPrixPlusTaxe25PourCentPourCategorieAutreImportee() {
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe("* 3 flacons de parfum importé à 47.50€");
		assertEquals(178.15, produit.getPrixTTC().doubleValue(), 0);
	}


	@Test
	public void doitAjouterProduitAuPanierEtCalculeDeTaxe() {
		String enonce = "* 3 flacons de parfum importé à 47.50€";
		Produit produit = gestionProduit.ajouterProduitAuPanierEtCalculeDeTaxe(enonce);
		assertEquals("flacons de parfum ", produit.getNom());
		assertEquals(3, produit.getQuantite());
		assertEquals(47.50, produit.getPrixHorsTaxe().doubleValue(), 0);
		assertEquals(25, produit.getPourcentageTaxe().doubleValue(), 0);
		assertEquals(Categorie.AUTRE, produit.getCategorie());
		assertEquals(178.15, produit.getPrixTTC().doubleValue(), 0);
	}

	@Test
	public void doitCalculerTotalPourcentageTaxe() {
		Produit produit = creerProduit();
		produit.setCategorie(Categorie.AUTRE);
		BigDecimal totalTaxe = gestionProduit.calculerTotalPourcentageTaxe(produit);
		assertEquals(25, totalTaxe.doubleValue(), 0);
	}

	private Produit creerProduit() {
		return new Produit(2, "parfum", BigDecimal.valueOf(10), "importé");
	}

	@Test
	public void extraireCategorieDepuisNomProduit() {
		Categorie categorie = gestionProduit.extraireCategorieDepuisNomProduit(creerProduit());
		assertEquals(Categorie.AUTRE, categorie);
	}

	@Test
	public void calculerDifferenceAvecTaxe() {
		Produit produit = creerProduit();
		produit.setPrixTTC(BigDecimal.valueOf(25));
		produit.setPourcentageTaxe(BigDecimal.valueOf(25));
		BigDecimal differenceAvecTaxe = gestionProduit.calculerDifferenceAvecTaxe(produit);
		assertEquals(5, differenceAvecTaxe.doubleValue(), 0);
	}

	@Test
	public void calculerPrixTTC() {
		{
			Produit produit = creerProduit();
			produit.setPourcentageTaxe(BigDecimal.valueOf(25));
			BigDecimal prixTTC = gestionProduit.calculerPrixTTC(produit);
			assertEquals(25, prixTTC.doubleValue(), 0);
		}
	}

	@Test
	public void doitRetournerProduitValide() {
		Produit produit = new Produit();
		produit.setNom(CHOCOLAT);
		produit.setPrixHorsTaxe(BigDecimal.valueOf(12.49));
		produit.setQuantite(1);

		assertTrue(gestionProduit.verifierValiditeeProduit(produit));
	}

	@Test
	public void doitRetournerProduitInvalideSiPasDeQuantite() {
		Produit produit = new Produit();
		produit.setNom(CHOCOLAT);
		produit.setPrixHorsTaxe(BigDecimal.valueOf(12.49));

		assertFalse(gestionProduit.verifierValiditeeProduit(produit));
	}

	@Test
	public void doitRetournerProduitInvalideSiPasDeNom() {
		Produit produit = new Produit();
		produit.setPrixHorsTaxe(BigDecimal.valueOf(12.49));
		produit.setQuantite(2);

		assertFalse(gestionProduit.verifierValiditeeProduit(produit));
	}

	@Test
	public void doitRetournerProduitInvalideSiPrixNegatif() {
		Produit produit = new Produit();
		produit.setNom(CHOCOLAT);
		produit.setPrixHorsTaxe(BigDecimal.valueOf(-12.49));
		produit.setQuantite(2);

		assertFalse(gestionProduit.verifierValiditeeProduit(produit));
	}
}
