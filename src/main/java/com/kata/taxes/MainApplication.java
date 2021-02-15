package com.kata.taxes;

import com.kata.taxes.model.Panier;
import com.kata.taxes.services.GestionPanier;
import com.kata.taxes.services.GestionPanierImpl;

public class MainApplication {

	public static void main(String[] args) {

		GestionPanier gestionPanier = new GestionPanierImpl();

		//input Panier 1
		String produit1Panier1 = "* 2 livres à 12.49€";
		String produit2Panier1 = "* 1 CD musical à 14.99€";
		String produit3Panier1 = "* 3 barres de chocolat à 0.85€";
		Panier panier1 = gestionPanier.ajouterProduitsAuPanier(produit1Panier1, produit2Panier1, produit3Panier1);

		gestionPanier.afficherFactureDetaillee(panier1);
		System.out.println();

		//input panier 2
		String produit1Panier2 = "* 2 boîtes de chocolats importée à 10€";
		String produit2Panier2 = "* 3 flacons de parfum importé à 47.50€";
		Panier panier2 = gestionPanier.ajouterProduitsAuPanier(produit1Panier2, produit2Panier2);

		gestionPanier.afficherFactureDetaillee(panier2);
		System.out.println();

		//input panier 3
		String produit1Panier3 = "* 2 flacons de parfum importé à 27.99€";
		String produit2Panier3 = "* 1 flacon de parfum à 18.99€";
		String produit3Panier3 = "* 3 boîtes de pilules contre la migraine à 9.75€";
		String produit4Panier3 = "* 2 boîtes de chocolats importés à 11.25€";
		Panier panier3 = gestionPanier.ajouterProduitsAuPanier(produit1Panier3, produit2Panier3, produit3Panier3, produit4Panier3);

		gestionPanier.afficherFactureDetaillee(panier3);

	}

}
