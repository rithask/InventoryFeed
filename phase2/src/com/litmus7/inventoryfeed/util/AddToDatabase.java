package com.litmus7.inventoryfeed.util;

import java.io.File;
import java.util.List;

import com.litmus7.inventoryfeed.constants.FileConstants;
import com.litmus7.inventoryfeed.dao.InventoryDao;
import com.litmus7.inventoryfeed.model.Product;

public class AddToDatabase extends Thread {

	private final InventoryDao inventoryDao = new InventoryDao();
	private final File file;

	public AddToDatabase(File file) {
		this.file = file;
	}

	@Override
	public void run() {
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
}
