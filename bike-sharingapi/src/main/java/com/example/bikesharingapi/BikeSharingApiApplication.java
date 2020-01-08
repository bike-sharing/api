package com.example.bikesharingapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BikeSharingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeSharingApiApplication.class, args);

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://bicyclesharing-4eafb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException exception) {
            System.out.println("Error occured an initialising Firebase Admin SDK: " + exception.getMessage());
        }


    }

}
