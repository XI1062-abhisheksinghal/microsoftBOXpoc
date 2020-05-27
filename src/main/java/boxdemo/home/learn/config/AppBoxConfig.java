package boxdemo.home.learn.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.DeveloperEditionEntityType;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

@Configuration
public class AppBoxConfig {
	
	private BoxConfig boxConfig;
	
	private static String boxClientId="mademv7tnjfay8wh5erhz4381cd1f0q0";
	private static String boxClientSecret="cfx3WnRX6knGklJ2Fu8797d5p12ag2Yq";
	private static String enterpriseId="264002212";
	private static String publicKeyId=" ";
	private static String privateKey=" ";
	private static String passphrase=" ";
	private static final int MAX_CACHE_ENTRIES = 100;
	
	 private static final String USER_ID = "x9CLtu7hSXDGDarCAsQLhxZTjnWW0wPQ";
	
	private static BoxDeveloperEditionAPIConnection api; 
	IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
	
    @PostConstruct
    public void init() {
        boxConfig = new BoxConfig(boxClientId, boxClientSecret,enterpriseId,publicKeyId,privateKey,passphrase);
    }

    public BoxAPIConnection createBoxConnection() {
    	
    	api = new BoxDeveloperEditionAPIConnection(USER_ID, DeveloperEditionEntityType.USER, boxConfig,
                accessTokenCache);
       System.out.println("====="+api.getBaseURL());
       
        return api;
    }

}
