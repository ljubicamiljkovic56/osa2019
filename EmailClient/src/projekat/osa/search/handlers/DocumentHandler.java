package projekat.osa.search.handlers;

import java.io.File;

import projekat.osa.search.model.IndexUnit;

public abstract class DocumentHandler {
	
	public abstract IndexUnit getIndexUnit(File file);
	public abstract String getText(File file);

}
