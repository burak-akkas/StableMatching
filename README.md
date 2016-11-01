# Folder Structure
+ jar
  - match.jar
  + Test Dataset
    - m10.txt (N = 10)
    - w10.txt (N = 10)
    - ...
+ src
  - SampleGenerator.java
  - FileHandler.java
  - Main.java

# Compiling
- Main class uses FileHandler class to handle text I/O. 
- SampleGenerator class uses FileHandler class to handle text I/O.
- Main class uses SampleGenerator class for generating sample data.
- FileHandler class should compiled first, than SampleGenerator and the last Main class,
-- In src:
   > javac FileHandler.java
   > javac SampleGenerator.java
   > javac Main.java
   
# Running
- From compiled source
-- For matching:
   > java Main -w woman_pref_file.txt -m man_pref_file.txt
-- For generating sample data:
   > java Main -s sampleDataSize

- From JAR
> cd jar
-- For matching:
   > java -jar match.jar -w woman_pref_file.txt -m man_pref_file.txt
-- For generating sample data:
   > java -jar match.jar -s sampleDataSize
