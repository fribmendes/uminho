package autores;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class responsible for:<br>
 * <ul>
 * 		<li>Reading from a file;</li>
 * 		<li>Populating the database accordingly;</li>
 * 		<li>Communicating with the structures</li>
 * </ul>
 *
 */

@SuppressWarnings("serial")
public class AuthorNetwork  implements Serializable {
	private String currentFile;
	private Statistics stats;
	private GlobalAuthorNetwork network;
	
	/**
	 * Empty constructor
	 */
	public AuthorNetwork() {
		this.currentFile = "";
		this.stats = new Statistics();
		this.network = new GlobalAuthorNetwork();
	}

	/**
	 * Returns the read filename
	 * @return read file name
	 */
	public String getCurrentFile() {
		return this.currentFile;
	}
	
	/**
	 * Returns the total number of solo publications
	 * @return total number of solo publications
	 */
	public int getSoloPublications() {
		return stats.getSoloArticles();
	}
	
	/**
	 * Return the number of authors who only published alone
	 * @return number of authors who only published alone
	 */
	public int getTotalSoloAuthors() {
		return this.network.getSoloAuthors().size();
	}
	
	/**
	 * Return the number of authors who never published alone
	 * @return number of authors who never published alone
	 */
	public int getTotalNonSoloAuthors() {
		return this.network.getNonSoloAuthors().size();
	}
	
	/**
	 * Returns the total number of authors
	 * @return total number of authors
	 */
	public int getTotalAuthors() {
		return network.totalAuthors();
	}
	
	/**
	 * Returns the total number of names read
	 * @return total number of names read
	 */
	public int getTotalNamesRead() {
		return stats.getTotalNames();
	}
	
	/**
	 * Returns the total number of publications
	 * @return total number of publications
	 */
	public int getTotalPublications() {
		return stats.getTotalArticles();
	}
	
	/**
	 * Returns a tuple containing minimum and maximum years that have publications
	 * @return tuple containing minimum and maximum years that have publications
	 */
	public Tuple<Integer, Integer> getYearInterval() {
		return network.getYearInterval();
	}
	
	/**
	 * Returns a set containing the name of all the coauthors of the author with the given name
	 * @param name
	 * @return set containing the name of all the coauthors of the author with the given name
	 */
	public NavigableSet<String> getCoauthorsOf(String name) {
		return this.network.getCoauthorsOf(name);
	}
	
	/**
	 * Returns a navigable map of the year table. The table shall contain an association of year - number of publications
	 * @return navigable map of the year table. The table shall contain an association of year - number of publications
	 */
	public NavigableMap<Integer, Integer> getYearTable() {
		return this.network.getYearTable();
	}
	
	/**
	 * Returns a set with a certain number of authors that published in the year interval
	 * @param min first year of the interval
	 * @param max last year of the interval
	 * @param nrAuthors maximum number of authors to display
	 * @return set with a certain number of authors that published in the year interval
	 */
	public NavigableSet<Tuple<String, Integer>> topPublishersInInterval(int min, int max, int nrAuthors) {
		return this.network.topPublishers(min, max, nrAuthors);
	}
	
	/**
	 * Returns a set with the names of authors that published in the given year interval
	 * @param min first year of the interval
	 * @param max last year of the interval
	 * @return set with the names of authors that published in the given year interval
	 * @throws NoAuthorsInIntervalException
	 */
	public NavigableSet<String> authorsInInterval(int min, int max) throws NoAuthorsInIntervalException {
		return this.network.authorsInInterval(min, max);
	}
	
	/**
	 * Returns the number of authors with more publications than the given number
	 * @param nrPublications
	 * @return number of authors with more publications than the given number
	 */
	public int nrAuthorsWithOver(int nrPublications) {
		return this.network.nrAuthorsWithOver(nrPublications);
	}
	
	/**
	 * Returns a set of strings with the co-author information for the given author in the given year
	 * @param year
	 * @param author
	 * @return set of strings with the co-author information for the given author in the given year
	 * @throws NoSuchYearException
	 * @throws NoSuchAuthorException
	 */
	public Tuple<Set<String>, Integer> authorPartnershipInfo(int year, String author) throws NoSuchYearException, NoSuchAuthorException {
		return this.network.authorPartnershipInfo(year, author);
	}
	
	/**
	 * Resets the statistics and sets the new filename
	 * @param filename
	 */
	private void reset(String filename) {
		this.stats = new Statistics();
		this.currentFile = filename;
		this.network = new GlobalAuthorNetwork();
	}
	/**
	 * Returns a navigable set of authors started by the given initial
	 * @param c
	 * @return navigable set of authors started by the given initial
	 */
	public NavigableSet<String> getAuthorsBy(char c) {
		return this.network.getAuthorsBy(c);
	}
	
	/**
	 * Reads from a file, populating the database
	 * @param filename name of the file to be read
	 */
	public void readFromFile(String filename) throws IOException {
		this.reset(filename);
		
		BufferedReader br = new BufferedReader( new FileReader(filename) );
		String line = br.readLine();
		
		while(line != null) {
			if(line.length() > 1)
				processData( getLineArgs(line) );
			
			line = br.readLine();
		}
		
		br.close(); // I don't know if this won't give some exceptions
	}
	
	/**
	 * Receives a line, splitting it into valid information to be processed
	 * The information shall be returned as a Collection
	 * @param line
	 * @return 
	 */
	private List<String> getLineArgs(String line) {
		ArrayList<String> args = new ArrayList<>();
		
		for( String s : line.split(",") )
			args.add( s.trim() );
		
		return args;
	}
	
	/**
	 * Processes the data, inserting to databases
	 * @param args
	 */
	private void processData(List<String> args) {
		int year = Integer.parseInt( args.get(args.size() - 1) );
		List<String> authorArgs = args.subList(0, args.size() - 1);
		
		this.network.addPublication(year, authorArgs);
		
		this.stats.process(authorArgs);
	}
	
	/**
	 * Counts the number of repeated lines for a given file.
	 * @param filename
	 * @return number of repeated lines for a given file.
	 */
	public int countRepeatedLines(String filename) throws IOException {
		TreeSet<String> lineTree = new TreeSet<>();
		int repeatedLines = 0;
		
		BufferedReader br = new BufferedReader( new FileReader(filename) );
		String line = br.readLine();
		
		while(line != null) {
			if(line.length() > 1) {
				if( lineTree.contains(line) ) repeatedLines++;
				else lineTree.add(line);	
			}
			
			line = br.readLine();
		}
		
		br.close();
		
		return repeatedLines;
	}
	
	/**
	 * Returns a set list of common coauthors between a collection of a maximum of 3 authors. They must be in a year interval
	 * @param authors
	 * @param min first year of the interval
	 * @param max last year of the interval
	 * @return
	 */
	public NavigableSet<String> commonCoauthors(Collection<String> authors, int min, int max) {
		return this.network.getCommonCoauthors(authors, min, max);
	}
	
	/**
	 * Returns a set with the pairs of authors who published the most in the given interval
	 * @param min first year
	 * @param max last year
	 * @param nrAuthors number of authors
	 * @return
	 */
	public NavigableSet<Tuple<Tuple<String, String>, Integer>> topPairs(int min, int max, int nrAuthors) {
		return this.network.topPairs(min, max, nrAuthors);
	}
	
	
	/**
	 * Writes the structure to a file
	 * @param filename
	 * @throws IOException
	 */
	public void writeToFile(String filename) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(filename) );
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
	
	/**
	 * Reads the structure from a file
	 * @param filename
	 * @return structure
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static AuthorNetwork readStructureFromFile(String filename) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream (filename) );
		AuthorNetwork l = (AuthorNetwork) ois.readObject();
		ois.close();
		return l;
	}
	
	
	private class Statistics implements Serializable {
		private int totalArticles;
		private int totalNames;
		private int soloArticles;
		
		/**
		 * Empty constructor
		 */
		public Statistics() {
			this.totalArticles = 0;
			this.totalNames = 0;
			this.soloArticles = 0;
		}
		
		/**
		 * Returns total number of articles read
		 * @return
		 */
		public int getTotalArticles() {
			return this.totalArticles;
		}
		
		/**
		 * Returns total number of names read
		 * @return
		 */
		public int getTotalNames() {
			return this.totalNames;
		}
		
		/**
		 * Returns total number of solo articles
		 * @return
		 */
		public int getSoloArticles() {
			return this.soloArticles;
		}
		
		/**
		 * Updates the totals of articles, names and solo articles
		 * @param publication Information of the publication
		 */
		public void process(List<String> publication) {
			this.totalArticles++;
			this.totalNames += publication.size();
			if(publication.size() == 1)	// if the publication only has one author
				this.soloArticles++;
		}
	}	
	
}
