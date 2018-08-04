package senet;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

import org.random.rjgodoy.trng.MH_SecureRandom;
import org.random.rjgodoy.trng.RjgodoyProvider;

/**
 * @original https://github.com/sanbrother/java-TRNG-client/blob/master/TestTRNG.java
 */
public class GeradorAleatoriedade {

	// Some logging configuration
	static {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime",           "false");
		System.setProperty("org.apache.commons.logging.simplelog.showlogname",            "false");
		System.setProperty("org.apache.commons.logging.simplelog.showShortLogname",       "true");


		/*There are four log modules in this library:
		 *
		 * org.apache.commons.logging.simplelog.log.MH_SecureRandomSpi
		 *   (the SecureRandom engine)
		 *
		 * org.apache.commons.logging.simplelog.log.MH_HttpClient
		 *   (the HTTP client connecting to www.random.org)
		 *
		 * org.apache.commons.logging.simplelog.log.MH_Daemon
		 *   (the pool daemon)
		 *
		 * org.apache.commons.logging.simplelog.log.RjgodoyProvider
		 *   (the provider which registers MH_SecureRandomSpi with the Java API)
		 */
		System.setProperty("org.apache.commons.logging.simplelog.log.MH_SecureRandomSpi",  "info");
		System.setProperty("org.apache.commons.logging.simplelog.log.MH_HttpClient",       "info");
		System.setProperty("org.apache.commons.logging.simplelog.log.MH_Daemon",           "info");
		System.setProperty("org.apache.commons.logging.simplelog.log.RjgodoyProvider",     "info");
	}


	public int obterInteiroAleatorio(int limite) {
		// IMPORTANT: system properties must be set before requesting the SecureRandom instance!

		// Sets the user information (for the HTTP User-Agent header)
		System.setProperty(MH_SecureRandom.USER,"user@example.org");

		// How many minutes will we wait befor aborting the connection?
		System.setProperty(MH_SecureRandom.TIMEOUT,"2");

		// Use TLS for connecting the server.
		System.setProperty(MH_SecureRandom.SSL_PROTOCOL,"TLSv1");
		//System.setProperty(MH_SecureRandom.SSL_PROVIDER,"SunJSSE");

		// Uncomment this to verify the server certificate
		// System.setProperty(MH_SecureRandom.CERTFILE,"www_random_org.cer");

		// Configure how many HTTP redirects will be followed
		// Normally, there shouldn't be any redirect.
		System.setProperty(MH_SecureRandom.MAX_REDIRECTS,"5");


		// register the provider
		Security.addProvider(new RjgodoyProvider());

		// creates a SecureRandom object using the MH_TRNG algorithm (which access www.random.org)
		SecureRandom srandom;
		try {
			srandom = SecureRandom.getInstance("MH_TRNG");
		} catch (NoSuchAlgorithmException e) {
			return new Random().nextInt(limite);
		}

		System.err.flush(); Thread.yield();

		return srandom.nextInt(limite);
	}

}
