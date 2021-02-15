package com.kata.taxes.services;

import com.kata.taxes.model.Panier;
import com.kata.taxes.model.ValeurPanier;

public interface GestionPanier {


	ValeurPanier calculerPrixTTCPanier(Panier panier);


	void afficherFactureDetaillee(Panier panier);

	Panier ajouterProduitsAuPanier(String... produis);
}
