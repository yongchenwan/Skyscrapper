JFLAGS = -g
JC = javac -cp . max_area.java Skyscrapper.java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	max_area.java \
	Skyscrapper.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class