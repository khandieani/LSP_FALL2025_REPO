Assignment 2: CSV ETL Pipeline in Java

Student Name: Khandie Anijah-Obi
Submission Date: 09/9/2025

 Assumptions Made:
- The input CSV file (`products.csv`) is placed in the `data/` folder.
- All rows contain valid data with 4 columns (ProductID, Name, Price, Category).
- Input file does not contain quotes or embedded commas.
- If the file is missing, the program will print an error and exit gracefully.
- An empty file with just a header still produces an output file with the correct header.

Design Notes:
- I used the Extract → Transform → Load (ETL) pattern with 3 methods:
  - `extract()` reads the input CSV.
  - `transform()` applies business logic in order: uppercase → discount → category → price range.
  - `load()` writes the result to a new CSV.
- Used `BigDecimal` and `RoundingMode.HALF_UP` to ensure proper financial rounding.
- Used relative paths so program works across systems.

How I Tested:
- Case A: Normal input with multiple product rows.
- Case B: Empty input file (only header).
- Case C: Missing input file (renamed it to test).
- Verified output by comparing with golden sample and checking `transformed_products.csv`.

AI Usage Summary:

I used ChatGPT to help break down the assignment instructions and clarify the ETL steps.
I also asked it to help me write the extract/transform/load methods using best practices.

How I used it:
- I took the basic structure suggested by AI and adapted it to match the assignment requirements and output format.
- I modified the transformation logic to include category reassignment and price range.

External Internet Sources:

Link: https://www.baeldung.com/java-csv
Usage: Learned how to safely split CSV lines and handle file I/O.
Adapted: Used `split(",", -1)` to avoid skipping empty values.

 Files Submitted:

- `src/org/howard/edu/lsp/assignment2/ETLPipeline.java`
- `data/transformed_products.csv`
- `README.txt`
