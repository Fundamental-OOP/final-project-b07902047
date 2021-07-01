
build:
	javac -cp . -sourcepath src -d out/ src/FlappyBird/*.java
	java -cp out/ FlappyBird.Main
