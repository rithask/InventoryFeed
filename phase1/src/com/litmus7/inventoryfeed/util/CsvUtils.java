package com.litmus7.inventoryfeed.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventoryfeed.exceptions.CsvProcessingException;
import com.litmus7.inventoryfeed.model.Product;

public class CsvUtils {
	public static List<Product> readProductsFromCsv(File file) {
		if (file == null) {
			throw new CsvProcessingException("CSV file not found");
		}
		
		List<Product> products = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split(",");
				Product product = new Product();
				product.setSku(Integer.parseInt(fields[0]));
				product.setProductName(fields[1]);
				product.setQuantity(Integer.parseInt(fields[2]));
				product.setPrice(Float.parseFloat(fields[3]));
				products.add(product);
			}
		} catch (IOException e) {
            throw new CsvProcessingException("Failed to read CSV: " + file.getName(), e);
		}
		
		return products;
	}
}
