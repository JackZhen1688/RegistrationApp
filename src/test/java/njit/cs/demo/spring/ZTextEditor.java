package njit.cs.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class ZTextEditor {

	private String fristName;
	private ZSpellChecker zSpellChecker;

	public String getFristName() {
		return fristName;
	}

	public void setFristName(String fristName) {
		this.fristName = fristName;
	}

	//#1. a setter method to inject the dependency.
	/***********************************************************
	 * Setter-based DI is accomplished by the container calling 
	 * setter methods on your beans after invoking a no-argument 
	 * constructor or no-argument static factory method to 
	 * instantiate your bean
	 * *********************************************************/
	public void setzSpellChecker(ZSpellChecker zSpellChecker) {
		this.zSpellChecker = zSpellChecker;
		System.out.println("Inside the ZTextEditor: setter inject." );
	}

	//    a getter method to return spellChecker
	@Autowired(required=false)
	@Qualifier("zSpellChecker")
	public ZSpellChecker getzSpellChecker() {
		return zSpellChecker;
	}
	
	//#2. a constructor method to inject the dependency.
	/*********************************************************
	 * Constructor-based DI is accomplished when the container 
	 * invokes a class constructor with a number of arguments, 
	 * each representing a dependency on other class
	 * *******************************************************/
	/*--------------------------------------------------
	@Autowired
	public ZTextEditor(ZSpellChecker zSpellChecker) {
	      this.zSpellChecker = zSpellChecker;    <--- without creating a new instance
	}
	----------------------------------------------------*/
	
	public void spellCheck() {
		zSpellChecker.checkSpell();
	}
}

/*--------------------------------------------
 * Traditional way to initial a instance value
 * from the constructor
 *-------------------------------------------- 
public class ZTextEditor {

	private String name;
	private ZSpellChecker zSpellChecker;
	
	public ZTextEditor(String name) {
	    this.name = name;
	    zSpellChecker = new ZSpellChecker();    <--- create a new instance
	}

	public void spellCheck() {
		zSpellChecker.checkSpell();
	}
	
}	

Here, we have removed the total control from TextEditor and 
kept it somewhere else (ie. XML configuration file) and the dependency 
(ie. class SpellChecker) is being injected into the class TextEditor 
through a Class Constructor. Thus flow of control has been "inverted" 
by Dependency Injection (DI) because you have effectively delegated 
dependances to some external system.

Second method of injecting dependency is through Setter Methods of TextEditor 
class where we will create SpellChecker instance and this instance will be 
used to call setter methods to initialize TextEditor's properties.

//#3. if annotation define as @Service("webReqDao") 
      you can directly call:  @Autowired 
                               private WebReqDao webReqDao;
      if annotation define as @Repository   
                               public class JdbcApplicationDao 
      you can directly call:  @Autowired
                               private JdbcApplicationDao jdbcApplicationDao;
******************************************************************************/