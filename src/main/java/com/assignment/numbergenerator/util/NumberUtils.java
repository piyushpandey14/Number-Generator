package com.assignment.numbergenerator.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NumberUtils {

	public static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);

	@Value("${file.temporary.location}")
	private String directory;

	public boolean numberGenerator(long taskId, int goal, int step) {
		StringBuilder sb = new StringBuilder();

		for (int i = goal; i >= 0; i = i - step) {
			sb.append(i);
			if (i - step >= 0)
				sb.append(",");
		}
		return writeToFile(taskId, sb.toString());
	}

	public boolean writeToFile(long taskId, String text) {
		boolean isSuccess = true;
		BufferedWriter writer = null;
		try {
			String filename = directory + taskId + "_output.txt";
			File file = new File(filename);
			file.getParentFile().mkdirs();
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
		} catch (IOException e) {
			isSuccess = false;
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				logger.error("Error Occurred while closing resource writer", e);
			}
		}
		return isSuccess;
	}

	public String readFromFile(long taskId) {
		BufferedReader reader = null;
		String result = null;
		try {
			String filename = directory + taskId + "_output.txt";
			File file = new File(filename);
			if (file.exists()) {
				reader = new BufferedReader(new FileReader(file));
				result = reader.readLine();
			}
		} catch (Exception e) {
			logger.error("Error Occurred in reading file", e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				logger.error("Error Occurred in reading file", e);
			}
		}
		return result;
	}
}
