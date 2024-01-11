# BOTICA Java Template

> [!NOTE]  
> You can see a use case of the template with [RESTest](https://github.com/isa-group/RESTest) at [BOTICA-RESTest](https://github.com/migromarj/BOTICA-RESTest).

## Running your proyect with BOTICA

1. **Use this repository as a template for your project.**

2. **Install BOTICA as a local dependency, following the steps described at [BOTICA README](https://github.com/migromarj/BOTICA#installing-botica-as-a-local-dependency).**

3. **Configure your project.**
    1. Define your bot properties file according to your needs. You can find more information in the [BOTICA Wiki](https://github.com/migromarj/BOTICA/wiki/Bot-definition-file).
    2. Create all the necessary _Launcher_ classes based on the types of bots you have defined. You can find more information in the [BOTICA Wiki](https://github.com/migromarj/BOTICA/wiki/Launcher-classes).
    3. Create the necessary property files to define how configuration file generation and launching of the Collector bot will be carried out. You can find more information in the [BOTICA Wiki](https://github.com/migromarj/BOTICA/wiki/Example-of-use#3-creation-of-properties-files).

4. **Compiling the project.**

    Open a terminal at the project's root and run the following Maven command to compile and package the project.
    ```
    mvn clean install
    ```

5. **Generate the necessary files to launch the BOTICA environment.**

    - To use the default file located in `src/main/resources/BOTICAConfig/configuration-setup.properties`, run the following maven command at the root of the project:
        ```
        mvn exec:java@configuration-setup
        ```
    - To use a file located in another path, run the following maven command at the root of the project:
        ```
        mvn exec:java@configuration-setup -Dexec.args="path/to/file"
        ```

6. **Launch the BOTICA environment.**

    Run the following maven command at the root of the project:
    ```
    mvn exec:exec@launch-botica
    ```

7. **Launch the data collector bot.**

   - To use the default file located in `src/main/resources/BOTICAConfig/collector.properties`, run the following maven command at the root of the project:
       ```
       mvn exec:java@launch-collector
       ```
   - To use a file located in another path, run the following maven command at the root of the project:
       ```
       mvn exec:java@launch-collector -Dexec.args="path/to/file"
       ```
