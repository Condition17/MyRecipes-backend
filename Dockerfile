# Base java:8
FROM java:8

# Add jar to container
ADD /target/MyRecipe*.jar recipes.jar

# Entry in json format
ENTRYPOINT ["java", "-jar", "/recipes.jar"]