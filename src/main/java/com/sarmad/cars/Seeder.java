package com.sarmad.cars;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Seeder {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("cars");

            System.out.println("Starting seeding...");

            Random random = new Random();

            final String userPassword = getPassword();

            // Create the User collection
            System.out.println("Creating the User collection...");
            MongoCollection<Document> users = database.getCollection("USERS");
            List<Document> usersList = new ArrayList<>(100_000);
            for (int i = 0; i < 100_000; i++) {
                usersList.add(new Document("LOGIN_ID", "admin" + i)
                        .append("FIRST_NAME", "Admin" + i)
                        .append("SECOND_NAME", "Admin" + i)
                        .append("PASSWORD", userPassword));
            }

            var userIds = users.insertMany(usersList).getInsertedIds().values().stream().toList();
            usersList.clear();

            System.out.println("User collection created.");

            // Create the CarModel collection
            System.out.println("Creating the CarModel collection...");
            MongoCollection<Document> carModels = database.getCollection("CAR_MODELS");
            List<Document> carModelsList = new ArrayList<>(100_000);
            String[] carTypes = new String[]{"Sedan", "SUV", "Truck", "Van", "Coupe", "Convertible"};
            String[] carModelNames = new String[]{"Lanser", "Corolla", "Civic", "Accord", "Camry", "Altima", "Maxima", "Sentra"};
            for (int i = 0; i < 100_000; i++) {
                carModelsList.add(new Document("MODEL_NAME", "Model" + i)
                        .append("TYPE", carTypes[random.nextInt(carTypes.length)])
                        .append("MANUFACTURER_YEAR", 1990 + (random.nextInt(30)))
                        .append("MODEL_NAME", carModelNames[random.nextInt(carModelNames.length)]));
            }

            var carIds = carModels.insertMany(carModelsList).getInsertedIds().values().stream().toList();
            carModelsList.clear();

            System.out.println("CarModel collection created.");

            // Create the UserCar collection
            System.out.println("Creating the UserCar collection...");
            MongoCollection<Document> userCars = database.getCollection("USER_CARS");
            List<Document> userCarsList = new ArrayList<>(2_000_000);
            String[] colors = new String[]{"Black", "White", "Red", "Blue", "Green", "Yellow", "Silver", "Gray"};
            for (int i = 0; i < 2_000_000; i++) {
                userCarsList.add(new Document("USER_ID", userIds.get(random.nextInt(100_000)).asObjectId().getValue().toHexString())
                        .append("CAR_MODEL_ID", carIds.get(random.nextInt(100_000)).asObjectId().getValue().toHexString())
                        .append("CAR_PLATE_NUMBER", "ABC" + i)
                        .append("COLOR", colors[random.nextInt(colors.length)]));
            }

            userCars.insertMany(userCarsList);
            userCarsList.clear();

            System.out.println("UserCar collection created.");

            System.out.println("Seeding completed.");
        }
    }

    private static String getPassword() {
        final String password = "12341234";
        System.out.println("User Password: " + password);
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}