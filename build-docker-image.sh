./mvnw clean install
docker build -t fishbowl_fish fishbowl-fish
docker build -t fishbowl_manager fishbowl-manager
