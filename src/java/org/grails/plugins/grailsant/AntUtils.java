package org.grails.plugins.grailsant;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


public class AntUtils {

	/**
	 * Tests if a file is a valid .zip.
	 * 
	 * Reference: http://stackoverflow.com/questions/2085637/java-how-to-check-if-a-generated-zip-file-is-corrupted
	 * 
	 * @param file - the file to test
	 * @return true if file is a valid .zip, false otherwise
	 */
	 static boolean isValid(final File file) {
		    ZipFile zipfile = null;
		    try {
		        zipfile = new ZipFile(file);
		        return true;
		    } catch (ZipException e) {
		        return false;
		    } catch (IOException e) {
		        return false;
		    } finally {
		        try {
		            if (zipfile != null) {
		                zipfile.close();
		                zipfile = null;
		            }
		        } catch (IOException e) {
		        }
		    }
		}

}
