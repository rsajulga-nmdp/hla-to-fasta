
Scenario: Parsing a sequence file

Given an HML file as test_sequences.txt
When I want to parse the file for results
Then the resulting output for locus A will be expected as exp_test_sequences_A.fasta
And the resulting output for locus B will be expected as exp_test_sequences_B.fasta