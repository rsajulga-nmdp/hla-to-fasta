# hla-to-fasta
Convert the output from hml-to-sequence and create a FASTA file


Requires Java 8 and Maven

## Build

```
mvn clean install
```

## Usage
This script takes the sequence (txt) output from [hml2seq](https://github.com/nmdp-bioinformatics/hml-to-sequence) and formats it into a FASTA file for downstream usage with [SeqAnn](https://github.com/nmdp-bioinformatics/seq-ann).
```
java -jar target/hla2fasta-1.0-SNAPSHOT.jar sequences.txt
```
The output files are divided by loci (denoted by filename suffixes) and appear in the working directory. For example, having a *sequences.txt* input file will create *sequences_A.txt*, *sequences_B.txt*, etc...

## Tests

The functionality of this program is verified through BDD (behavior-driven development). [JBehave](https://jbehave.org/) is used to facilitate BDD tests. To check the status of tests ran through Maven, check `target/jbehave/view/reports.html`.
