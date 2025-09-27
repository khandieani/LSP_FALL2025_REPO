# Assignment 3 Reflection

## Design Differences

The key differences between Assignment 2 and Assignment 3 implementations are:

1. **Modular Design**: Assignment 3 breaks down the monolithic ETL pipeline into distinct classes with clear responsibilities:
   - `Product`: Represents the data model
   - `Transformer` and `ProductTransformer`: Handle data transformation logic
   - `FileHandler` and `CsvFileHandler`: Handle file I/O operations
   - `ETLPipeline`: Orchestrates the overall process

2. **Encapsulation**: Each class now properly encapsulates its data and behavior:
   - `Product` class encapsulates product attributes and related calculations
   - File handling logic is encapsulated in the `CsvFileHandler` class
   - Transformation rules are encapsulated in the `ProductTransformer` class

3. **Single Responsibility Principle**: Each class has a single, well-defined responsibility:
   - `Product` manages product data and state
   - `FileHandler` handles file I/O operations
   - `Transformer` focuses on data transformation rules

## Object-Oriented Concepts Used

1. **Objects and Classes**:
   - Created proper classes to represent domain concepts
   - Each class represents a specific entity or responsibility
   - Objects maintain their own state and behavior

2. **Encapsulation**:
   - Private fields with public getters/setters
   - Internal implementation details hidden
   - Clear public interfaces for each class

3. **Inheritance**:
   - `ProductTransformer` extends the abstract `Transformer` class
   - `CsvFileHandler` implements the `FileHandler` interface

4. **Polymorphism**:
   - `FileHandler` interface allows for different file handling implementations
   - `Transformer` abstract class enables different transformation strategies

## Testing and Validation

To ensure Assignment 3 works the same as Assignment 2:

1. **Input/Output Validation**:
   - Used the same input file (products.csv)
   - Compared the generated transformed_products.csv from both implementations
   - Verified that the output files are identical

2. **Error Handling**:
   - Tested with missing input files
   - Tested with malformed data rows
   - Verified error messages and handling are consistent

3. **Business Rules**:
   - Verified 10% discount on Electronics
   - Confirmed Premium Electronics category for items over $500
   - Checked price range calculations
   - Validated case transformations (uppercase names)

4. **Processing Statistics**:
   - Compared row counts (read, transformed, skipped)
   - Verified that both implementations report the same statistics

The object-oriented version maintains the exact same functionality while providing better organization, maintainability, and extensibility.