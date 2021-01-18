package njit.cs.demo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class ZTestInjectionMain {

	public static void main(String[] args) {
		
		/*
		ZSpellChecker zSpellChecker = new ZSpellChecker();
		ZTextEditor zTextEditor = new ZTextEditor();
		zTextEditor.setFristName("jack");
		zTextEditor.setzSpellChecker(zSpellChecker);
		*/
		
	    ApplicationContext context = 
		   //new ClassPathXmlApplicationContext("ZBeans.xml");
	   	   new FileSystemXmlApplicationContext("src/test/java/njit/cs/demo/spring/config/ZBeans.xml");

	       ZTextEditor zTextEditor = (ZTextEditor) context.getBean("textEditor");

        System.out.println("fistname="+zTextEditor.getFristName());
	    zTextEditor.spellCheck();
	       
		final Foo test = (Foo)context.getBean("foo");
		System.out.println("from foo method:run() "+test.run("hello"));

	}
	

}
