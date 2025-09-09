package com.litmus7.inventoryfeed.controller;

import java.util.List;

import com.litmus7.inventoryfeed.model.Product;
import com.litmus7.inventoryfeed.model.Response;
import com.litmus7.inventoryfeed.service.InventoryService;

public class InventoryController {
	InventoryService inventoryService = new InventoryService();

	public Response<?> readAndSaveDataFromCsvFiles() {
		try {
			if (inventoryService.readAndSaveDataFromCsvFiles()) {
				return Response.success("Products added successfully", null);
			} else {
				return Response.error("Failed");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	public Response<List<Product>> fetchAllProducts() {
		try {
			List<Product> products = inventoryService.fetchAllProducts();
			return Response.success("Products succesfully fetched", products);
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
}
