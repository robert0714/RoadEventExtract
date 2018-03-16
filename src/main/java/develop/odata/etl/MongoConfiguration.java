/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package develop.odata.etl;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate; 

@Configuration
@EnableMongoRepositories(basePackages= {"develop.odata.etl"})
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration.class,
		org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration.class,
		org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration.class , 
		org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration.class})
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "roadevent";
	}

	@Value("${spring.data.mongodb.uri}")
	private String uri;
	 
	
	@Override
	public Mongo mongo() throws Exception {
		//使用mongo replica-set或shard cluster會比較方便
		return new MongoClient(new MongoClientURI(uri));
	}
	@Bean
	public RestTemplate getRest() {
		return new RestTemplate();
	}

	 
	
    
	
}
