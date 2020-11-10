package org.nmdp.steps;

import org.hamcrest.Matchers;
//import org.jbehave.core.annotations.Alias;
//import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.nmdp.App;

//import static org.hamcrest.Matchers.equalTo;

public class ParseSeqsSteps {

	private String seqFilename;
	
	public ParseSeqsSteps() {
		
	}
	
	private String getFilepath(String filename, boolean fullPath) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File file = new File(classloader.getResource(filename).getFile());
		String absolutePath = file.getAbsolutePath();
		if (fullPath) {
			return absolutePath;
		} else {
			String filepath = absolutePath.
					substring(0, absolutePath.lastIndexOf(File.separator));
			return filepath;
		}
	}
	
	private String removeExt(String filename) {
		String[] splitName = filename.split("\\.");
		if (splitName.length > 0) {
			return splitName[0];
		} else {
			return filename;
		}
	}
	
	private List<String> readLines(String filename) {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(
					Paths.get(new File(filename).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	private void runHla2fa(String input) {
		try {
			App.main(new String[]{input});
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	@Given("an HML file as $hmlFilename")
	public void loadingHmlFile(String hmlFilename) {
		this.seqFilename = hmlFilename;
	}
	
	@When("I want to parse the file for results")
	public void parseHmlFile() {
		runHla2fa(getFilepath(seqFilename, true));
	}
	
	@Then("the resulting output for locus $locus will be expected as $expOutfilename")
	public void evaluateHmlFile(String locus, String expOutfilename) {
		String expOutFilepath = getFilepath(expOutfilename, true);
		String obsOutfilename = removeExt(seqFilename) + "_" + locus + ".fasta";
//		String obsOutFilepath = getFilepath(obsOutfilename, true);
		assertThat(readLines(obsOutfilename),
				Matchers.equalTo(readLines(expOutFilepath)));
	}
}
