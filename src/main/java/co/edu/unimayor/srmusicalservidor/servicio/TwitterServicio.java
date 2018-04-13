/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.servicio;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.auth.AccessToken;

/**
 *
 * @author admin
 */
@Service
public class TwitterServicio {

    private static final Log log = LogFactory.getLog(TwitterServicio.class);

    public Twitter autenticarTwitter() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            // Twitter Consumer key & Consumer Secret
            twitter.setOAuthConsumer("MB1BUSegCKyzz0Zxf8QhaWpA5", "7FeuVqyGFt6qCJs8IBo59jQI6P3khaK4jVmq13PzFJxzeda7Op");
            // Twitter Access token & Access token Secret
            twitter.setOAuthAccessToken(new AccessToken("1837672465-eRV1Fu2cDfyexzpRilSYCwT7Y11dWP3xSsM8T1o",
                    "rZnby1WIri9vtNnMwX1fUxmF8Zk2AQAob4aH5BGuxV4Iy"));
            return twitter;
        } catch (Exception e) {
            log.error("Error autenticarTwitter", e);
            return null;
        }
    }

    @Scheduled(cron = "${constantes.CRON_REVISION_TWITTER}")
    public synchronized void cronVerificacionSesionCRON() throws TwitterException {
        Twitter twitter = autenticarTwitter();
        ResponseList statusReponseList = twitter.getUserTimeline();
        statusReponseList.forEach((item) -> {
            log.info("Respuesta " + item.toString());;
        });
    }
}
