package com.litmus7.inventoryfeed.app;

import java.util.List;

import com.litmus7.inventoryfeed.controller.InventoryController;
import com.litmus7.inventoryfeed.model.Product;
import com.litmus7.inventoryfeed.model.Response;

public class InventoryFeedApp {
	public static void main(String args[]) {
		InventoryController controller = new InventoryController();

		Response response = controller.readAndSaveDataFromCsvFiles();
		if (response.isSuccess()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getMessage());
		}

		Response<List<Product>> response2 = controller.fetchAllProducts();
		if (response2.isSuccess()) {
			for (Product product : response2.getData()) {
				System.out.println(product);
			}
		} else {
			System.out.println(response2.getMessage());
		}
	}
}
