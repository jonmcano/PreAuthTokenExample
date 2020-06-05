package com.jaspersoft.ps.sso;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	CustomPreProcessor cpp = new CustomPreProcessor();
    	try {
    		
    		// Use one time ID
        	String enc = cpp.encrypt("u=3gmts_user3|r=ROLE_WH_MGR,ROLE_EMPLOYEE|o=pepsi|pa1=004");
    		
        	System.out.println(enc);
        	System.out.println(cpp.decrypt(enc));
        	

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
