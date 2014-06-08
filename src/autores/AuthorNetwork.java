package autores;

/**
 * Main class of the project, responsible for UI and delegation of user commands to the correct handlers
 *
 */

public class AuthorNetwork {

	private MenuOption[] mainMenu;
	private boolean isActive;
	private Lobby lobby;
	
	
	private static final String[] mainMenuStrings = {
		"Exit", "Read from file", "Count repeated lines"
	};
	
	
	/**
	 * Empty constructor
	 */
	public AuthorNetwork() {
		this.isActive = true;
		this.mainMenu = null;
		this.lobby = new Lobby();
		this.generateMainMenu(); 
	}
	
	/* ##### Query methods ##### */
	
	/**
	 * Scans the user for a filename, reading from it
	 */
	private void readFromFile() {
		String filename = Scan.scanString("Enter a filename, please");
		System.out.println("Yea... I'm going to read from a file, now: " + filename);
	}
	
	private void countLines() {
		String filename = Scan.scanString("Enter a filename: ");
		int count = this.lobby.countRepeatedLines(filename);
		System.out.println("Number of repeated lines: " + count);
		Scan.pressEnterToContinue();
	}
	
	
	
	
	/* ##### UI methods ##### */
	
	/**
	 * Print a friendly welcome message
	 */
	private static void greet() {
		System.out.println("Hello and welcome to the Author Network.");
	}
	
	/**
	 * Print a friendly goodbye message, setting the app to inactive 
	 */
	private void shutdown() {
		System.out.println("Bye bye.");
		this.isActive = false;
	}
	
	/**
	 * Generates the main menu option to be selected
	 */
	private void generateMainMenu() {
		final AuthorNetwork app = this; // Put the app in context to generate its Menu Options
		this.mainMenu = new MenuOption[] {
				new MenuOption() { public void exec() { app.shutdown(); } },
				new MenuOption() { public void exec() { app.readFromFile(); } },
				new MenuOption() { public void exec() { app.countLines(); } }
		};
	}
	
	/**
	 * Prints the main menu options
	 */
	private static void printMainMenu() {
		int i = 0;
		for(String s : AuthorNetwork.mainMenuStrings)
			System.out.println( (i++) + ". " + s);
	}
	
	/**
	 * Runs a command interpreter, printing the options and scanning the user.
	 * It then proceeds to call the selected functionality 
	 */
	public void commandInterpreter() {
		AuthorNetwork.printMainMenu();
		
		int option = Scan.intInRange(
				"Please select an option",
				0,
				AuthorNetwork.mainMenuStrings.length - 1
				);
		
		this.mainMenu[option].exec();
	}
	
	/**
	 * Keeps the app in a cycle while it is active
	 * The user should select the shutdown option to mark it as "inactive"
	 */
	public void run() {
		while(this.isActive)
			this.commandInterpreter();
	}
	
	/**
	 * Let the party start
	 * @param args
	 */
	public static void main(String[] args) {
		AuthorNetwork.greet();
		new AuthorNetwork().run();
	}

}
