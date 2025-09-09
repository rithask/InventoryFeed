package com.litmus7.inventoryfeed.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventoryfeed.constants.FileConstants;
import com.litmus7.inventoryfeed.dao.InventoryDao;
import com.litmus7.inventoryfeed.exceptions.CsvProcessingException;
import com.litmus7.inventoryfeed.model.Product;
import com.litmus7.inventoryfeed.util.AddToDatabase;
import com.litmus7.inventoryfeed.util.FilesUtil;

public class InventoryService {

	InventoryDao inventoryDao = new InventoryDao();

	public boolean readAndSaveDataFromCsvFiles() throws InterruptedException {
		String inputFolder = FileConstants.INPUT_FOLDER;
		File[] csvFiles = FilesUtil.getCsvFiles(inputFolder);

		if (csvFiles == null) {
			throw new CsvProcessingException("No csv files found in the folder: " + inputFolder);
		}
		if (csvFiles.length == 0) {
			throw new CsvProcessingException("Input folder is empty");
		}

		List<AddToDatabase> threads = new ArrayList<>();
		for (File file : csvFiles) {
			AddToDatabase thread = new AddToDatabase(file);
			thread.start();
			threads.add(thread);
		}

		for (AddToDatabase t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new CsvProcessingException("Thread interrupted while processing CSVs", e);
			}
		}
		return true;
	}

	public List<Product> fetchAllProducts() {
		return inventoryDao.getAllProducts();
	}
}