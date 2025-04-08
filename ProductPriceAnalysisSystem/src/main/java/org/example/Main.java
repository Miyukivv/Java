package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws AmbigiousProductException {
        Product.clearProducts();

        Path garniturPath       = Path.of("src/main/resources/nonfood/garnitur.csv");
        Path mydloPath          = Path.of("src/main/resources/nonfood/mydlo.csv");
        Path cebulaPath         = Path.of("src/main/resources/food/cebula.csv");
        Path jajaPath           = Path.of("src/main/resources/food/jaja.csv");
        Path burakiPath         = Path.of("src/main/resources/food/buraki.csv");
        Path chlebPath          = Path.of("src/main/resources/food/chleb_pszenno-zytni.csv");
        Path wieprzBezKosciPath = Path.of("src/main/resources/food/mieso_wieprzowe_bez_kosci.csv");
        Path woloweZKosciaPath  = Path.of("src/main/resources/food/mieso_wolowe_z_koscia.csv");

        Product.addProducts(NonFoodProduct::fromCsv,   garniturPath);
        Product.addProducts(NonFoodProduct::fromCsv,   mydloPath);
        Product.addProducts(FoodProduct::fromCsv,      cebulaPath);
        Product.addProducts(FoodProduct::fromCsv,      jajaPath);
        Product.addProducts(FoodProduct::fromCsv,      burakiPath);
        Product.addProducts(FoodProduct::fromCsv,      chlebPath);
        Product.addProducts(FoodProduct::fromCsv,      wieprzBezKosciPath);
        Product.addProducts(FoodProduct::fromCsv,      woloweZKosciaPath);

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(Product.getProduct("Garnitur"));
        allProducts.add(Product.getProduct("Mydło"));
        allProducts.add(Product.getProduct("Cebula"));
        allProducts.add(Product.getProduct("Jaja"));
        allProducts.add(Product.getProduct("Buraki"));
        allProducts.add(Product.getProduct("Chleb"));
        allProducts.add(Product.getProduct("Mięso wieprzowe bez"));
        allProducts.add(Product.getProduct("Mięso wołowe z"));

        System.out.println("<Lista wszystkich zapisanych produktów>");
        for (Product p : Product.getListOfProducts()) {
            System.out.println(" - " + p.getName());
        }

        System.out.println("\n<Porównanie cen w maju 2010 vs. maju 2011>");
        for (Product prod : allProducts) {
            double price2010 = prod.getPrice(2010, 5);
            double price2011 = prod.getPrice(2011, 5);
            System.out.printf("-> %-40s | 2010.V = %.2f PLN | 2011.V = %.2f PLN%n",
                    prod.getName(), price2010, price2011);
        }

        Cart cart = new Cart();
        for (Product prod : allProducts) {
            cart.addProduct(prod, 2);
        }

        double cartPrice2010 = cart.getPrice(2010, 5);
        double cartPrice2011 = cart.getPrice(2011, 5);

        Map<Product, Long> productCounts = cart.getCartList().stream()
                .collect(Collectors.groupingBy(p -> p, LinkedHashMap::new, Collectors.counting()));

        System.out.println("\n<Analiza koszyka>");

        System.out.println("\n<Koszyk w maju 2010>");
        productCounts.forEach((product, count) -> {
            double singlePrice = product.getPrice(2010, 5);
            double totalItem   = singlePrice * count;
            System.out.printf("   %s x %d -> cena 1 szt. = %.2f PLN, łączna = %.2f PLN%n",
                    product.getName(), count, singlePrice, totalItem);
        });
        System.out.printf("==> Łączna wartość koszyka (2010.V) = %.2f PLN%n", cartPrice2010);

        System.out.println("\n<Koszyk w maju 2011>");
        productCounts.forEach((product, count) -> {
            double singlePrice = product.getPrice(2011, 5);
            double totalItem   = singlePrice * count;
            System.out.printf("   %s x %d -> cena 1 szt. = %.2f PLN, łączna = %.2f PLN%n",
                    product.getName(), count, singlePrice, totalItem);
        });
        System.out.printf("==> Łączna wartość koszyka (2011.V) = %.2f PLN%n", cartPrice2011);

        double inflationCart = cart.getInflation(2010, 5, 2011, 5);
        System.out.println("\nRoczna inflacja koszyka (2010.V -> 2011.V) = " + inflationCart + " %");

        System.out.println("\n<Wyszukiwanie produktu po prefiksie 'C'>");
        try {
            Product found = Product.getProduct("C");
            System.out.println("Znaleziono produkt: " + found.getName());
        } catch (AmbigiousProductException e) {
            System.err.println("AmbiguousProductException: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Brak produktu o podanym prefiksie!");
        }

        System.out.println("\n<Próba pobrania ceny spoza zakresu>");
        try {
            double price2030 = allProducts.get(1).getPrice(2030, 1);
            System.out.println("Cena produktu " + allProducts.get(1).getName() + " (2030.I) = " + price2030);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Wyjątek: " + e.getMessage());
        }

    }
}
