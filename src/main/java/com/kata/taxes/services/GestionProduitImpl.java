package com.kata.taxes.services;

import static com.kata.taxes.constants.ProduitConstants.EXTRACT_PRODUIT_REGEX;
import static com.kata.taxes.constants.ProduitConstants.IMPORTE_MOT_CLE_AVEC_REGEX;

import com.kata.taxes.model.Categorie;
import com.kata.taxes.model.Produit;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionProduitImpl implements GestionProduit {


	private static Map<String, Categorie> listeProduitAvecCategorie;

	public GestionProduitImpl() {
		remplirListeProduitDeReference();
	}

	private static Produit extraireProduitDepuisChaine(String product) {
		Matcher matcher = Pattern
			 .compile(EXTRACT_PRODUIT_REGEX)
			 .matcher(product);

		matcher.matches();
		int quantite = Integer.parseInt(matcher.group(1));
		String importe = extraireImporteSiExiste(matcher.group(2));
		String nom = matcher.group(2).replaceAll(IMPORTE_MOT_CLE_AVEC_REGEX, "");
		BigDecimal prixHorsTaxe = new BigDecimal(matcher.group(3));

		return new Produit(quantite, nom, prixHorsTaxe, importe);
	}

	private static String extraireImporteSiExiste(String enonce) {
		Matcher matcher = Pattern
			 .compile(IMPORTE_MOT_CLE_AVEC_REGEX)
			 .matcher(enonce);

		if (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	private static BigDecimal round(BigDecimal value) {
		BigDecimal bigDecimal = new BigDecimal("0.05");
		BigDecimal divide = value.divide(bigDecimal);
		BigDecimal t = divide.setScale(0, BigDecimal.ROUND_UP);
		return t.multiply(bigDecimal);

	}

	private void remplirListeProduitDeReference() {
		listeProduitAvecCategorie = new HashMap<String, Categorie>();
		listeProduitAvecCategorie.put("livre", Categorie.LIVRE);
		listeProduitAvecCategorie.put("cd", Categorie.AUTRE);
		listeProduitAvecCategorie.put("chocolat", Categorie.NOURRITURE);
		listeProduitAvecCategorie.put("pilule", Categorie.MEDICAMENT);
		listeProduitAvecCategorie.put("parfum", Categorie.AUTRE);
	}

	@Override
	public Categorie extraireCategorieDepuisNomProduit(Produit produit) {
		Optional<String> categoryKey = listeProduitAvecCategorie.keySet().stream().filter(p -> produit.getNom().toLowerCase().contains(p)).findFirst();
		return listeProduitAvecCategorie.get(categoryKey.orElse(null));
	}

	@Override
	public Produit ajouterProduitAuPanierEtCalculeDeTaxe(String p) {
		Produit produit = extraireProduitDepuisChaine(p);
		produit.definirCategorie(this);
		produit.calculerTotalPourcentageTaxe(this);
		produit.calculerPrixTtc(this);
		return produit;
	}

	@Override
	public BigDecimal calculerTotalPourcentageTaxe(Produit produit) {
		int taxe = 0;
		if (produit.getImporte() != null && !produit.getImporte().isEmpty()) {
			taxe += 5;
		}

		if (Categorie.LIVRE.equals(produit.getCategorie())) {
			taxe += 10;
		} else if (Categorie.AUTRE.equals(produit.getCategorie())) {
			taxe += 20;
		}

		return BigDecimal.valueOf(taxe);
	}

	@Override
	public BigDecimal calculerPrixTTC(Produit produit) {
		return round(produit.getPrixHorsTaxe().add(produit.getPrixHorsTaxe()
			 .multiply(produit.getPourcentageTaxe())
			 .divide(BigDecimal.valueOf(100)))
			 .multiply(BigDecimal.valueOf(produit.getQuantite())));
	}

	@Override
	public BigDecimal calculerDifferenceAvecTaxe(Produit produit) {
		return calculerPrixTTC(produit).subtract(produit.getPrixHorsTaxe().multiply(BigDecimal.valueOf(produit.getQuantite())));
	}

	@Override
	public boolean verifierValiditeeProduit(Produit produit) {
		return produit != null && produit.getQuantite() > 0
			 && produit.getNom() != null && !produit.getNom().isEmpty()
			 && produit.getPrixHorsTaxe().doubleValue() >= 0;
	}
}
