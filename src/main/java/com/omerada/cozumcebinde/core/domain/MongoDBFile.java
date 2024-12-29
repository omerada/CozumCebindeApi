package com.omerada.cozumcebinde.core.domain;

import lombok.Data;

@Data
public class MongoDBFile {
	private String filename;
	private String fileType;
	private String fileSize;
	private byte[] file;
}
