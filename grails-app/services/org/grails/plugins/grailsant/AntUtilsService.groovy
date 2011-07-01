package org.grails.plugins.grailsant

/*
 * Reference: http://preferisco.blogspot.com/2010/06/using-goovy-antbuilder-to-zip-unzip.html
 * Reference: http://groovy.codehaus.org/Using+Ant+from+Groovy
 * Reference: http://groovy-almanac.org/creating-a-zipfile-with-antbuilder/
 * Reference: http://jira.grails.org/browse/GRAILS-5675
 * Reference: http://ant.apache.org/manual/
 */


class AntUtilsService {

	static transactional = false

	def unzip(String zipFile,  String destDir) {

		return unzip( zipFile,   destDir, "", false)
	}

	def unzip(String zipFile,  String destDir, String mapperType) {

		return unzip( zipFile,   destDir, mapperType, false)
	}

	def unzip(String zipFile,  String destDir, Boolean overwrite) {

		return unzip( zipFile,   destDir, "", false)
	}

	def unzip(String zipFile,  String destDir, String mapperType, Boolean overwrite) {

		def ant = new AntBuilder();   // create an antbuilder
		if(isValidZip(zipFile)) {
			if (mapperType) {
				ant.unzip(  src: zipFile, dest:destDir,  overwrite:overwrite) {mapper(type:mapperType)}
			} else {
				ant.unzip(  src: zipFile, dest:destDir,  overwrite:overwrite)
			}
		}
		else {
			throw new UnzipException(
				message: "Invalid zip file.", fileName: zipFile)
		}
	}

	def zip(String destfile,String basedir) {
		return zip(destfile,basedir,"**/*.*", "")
	}



	def zip(String destfile,String basedir,String includes) {
		return zip(destfile,basedir,includes, "")
	}


	def zip(String destfile,String basedir,String includes,String excludes) {

		def ant = new AntBuilder();   // create an antbuilder

		ant.zip(destfile: destfile,
				basedir:  basedir,
				includes: includes,
				excludes: excludes)

	}

	def isValidZip(String file){
		def theFile = new File(file)
		return isValidZip(theFile)
	}

	def isValidZip(File file) {
		return AntUtils.isValid(file)
	}

}
