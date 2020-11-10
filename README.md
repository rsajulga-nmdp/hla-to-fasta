# hla-to-fasta
Convert the output from hml-to-sequence and create a FASTA file


Requires Java 8 and Maven

## Build

```
mvn clean install
```

## Run
```
java -jar target/hla2fasta-1.0-SNAPSHOT.jar
```

## Tests

The functionality of this program is verified through BDD (behavior-driven development). [JBehave](https://jbehave.org/) is used to facilitate BDD tests. To check the status of tests ran through Maven, check `target/jbehave/view/reports.html`.