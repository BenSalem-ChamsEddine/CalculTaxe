package com.kata.taxes.services;

import com.kata.taxes.model.Panier;
import java.math.BigDecimal;
import javafx.util.Pair;

public interface GestionPanier {


	Pair<BigDecimal, Double> calculerPrixTTCPanier(Panier panier);


	void afficherFactureDetaillee(Panier panier);

	Panier ajouterProduitsAuPanier(String... produis);
}
