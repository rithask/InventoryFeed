package com.litmus7.inventoryfeed.service;

import java.io.File;
import java.util.List;

import com.litmus7.inventoryfeed.constants.FileConstants;
import com.litmus7.inventoryfeed.dao.InventoryDao;
import com.litmus7.inventoryfeed.exceptions.CsvProcessingException;
import com.litmus7.inventoryfeed.model.Product;
import com.litmus7.inventoryfeed.util.CsvUtils;
import com.litmus7.inventoryfeed.util.FilesUtil;

public class InventoryService {

	InventoryDao inventoryDao = new InventoryDao();

	public boolean readAndSaveDataFromCsvFiles() {
		String inputFolder = FileConstants.INPUT_FOLDER;
		File[] csvFiles = FilesUtil.getCsvFiles(inputFolder);

		if (csvFiles == null) {
			throw new CsvProcessingException("No csv files found in the folder: " + inputFolder);
		}
		if (csvFiles.length == 0) {
			throw new CsvProcessingException("Input folder is empty");
		}

		for (File file : csvFiles) {
			List<Product> products = CsvUtils.readProductsFromCsv(file);

			boolean filesAddedFlag = false;
			try {
				filesAddedFlag = inventoryDao.createProducts(products);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

			if (filesAddedFlag) {
				FilesUtil.moveFile(file, FileConstants.PROCESSED_FOLDER);
			} else {
				FilesUtil.moveFile(file, FileConstants.ERROR_FOLDER);
			}
		}

		return true;
	}

	public List<Product> fetchAllProducts() {
		return inventoryDao.getAllProducts();
	}
}