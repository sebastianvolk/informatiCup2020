# informatiCup2020 - Pandemic fighter
Proposed solution for participation in the [informatiCup2020](https://github.com/informatiCup/informatiCup2020)

## User Manual

### Clone the repository

If you want to be able to build the sources by yourself you will need to clone our repository.

```sh
git clone https://github.com/sebastianvolk/informatiCup2020.git
cd informatiCup2020
```

### Compile the sources

We use [Apache Maven](https://github.com/apache/maven) as build tool. In order to compile the project you must have installed Maven and the Java Development Kit on your machine. The compile process can be started by the following command.

```sh
mvn clean package
```

After the compile process finished you can find the executable program in the target directory.

```sh
cd target
```

### Download compiled sources

If you just want to execute the program you can download the jar file from our [latest release](https://github.com/sebastianvolk/informatiCup2020/releases/latest).

### Execute and start server

To start the server you need to execute the compiled program. For this it is necessary that the Java Runtime Environment is installed.

```sh
java -jar pandemicfighter-<version>-jar-with-dependencies.jar
```

The variable `<version>` stands for the current version.

Please check the command line output and wait until you see the following success message before starting the game using the [command line tool](https://github.com/informatiCup/informatiCup2020/releases/latest).

```
Server started on port 50123
Quit the server with CONTROL-C
```

## Team BurningTrain

[Maik Gode](https://github.com/MaikG90), [Mirwais Hotak](https://github.com/mirweis), [Danial Mirrezaei Golakhor](https://github.com/danial0) and [Sebastian Volk](https://github.com/sebastianvolk)
