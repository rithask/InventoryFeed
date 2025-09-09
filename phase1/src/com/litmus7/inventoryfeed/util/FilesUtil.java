package com.litmus7.inventoryfeed.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.litmus7.inventoryfeed.exceptions.CsvProcessingException;
import com.litmus7.inventoryfeed.exceptions.FileProcessingException;

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

	public static void moveFile(File sourceFile, String destinationDir) {
		Path destinationPath = Paths.get(destinationDir, sourceFile.getName());

		try {
			Files.move(sourceFile.toPath(), destinationPath, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			throw new FileProcessingException(
					"Failed to move file " + sourceFile.getName() + " to " + destinationDir + ": " + e.getMessage(), e);
		}
	}
}
