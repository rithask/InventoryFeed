package com.litmus7.inventoryfeed.util;

import java.io.File;
import java.io.FileFilter;

import com.litmus7.inventoryfeed.exceptions.CsvProcessingException;

public class FilesUtil {
	public static File[] getCsvFiles(String folderPath) {
		if (folderPath == null) {
			throw new CsvProcessingException("Folder path can't be empty");
		}
		File directory = new File(folderPath);

		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".csv");
			}
		};

		File[] files = directory.listFiles(filter);

		return files;
	}
}
