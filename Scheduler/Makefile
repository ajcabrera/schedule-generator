all: compile compress clean

compile: Fonts/AcademicPlan.java Fonts/Building.java Fonts/Classroom.java Fonts/Degree.java Fonts/EducationalInstitution.java Fonts/Equipment.java Fonts/FactoriaAcademica.java Fonts/FactoriaBuilding.java Fonts/FactoriaRestriction.java Fonts/Group.java Fonts/GroupSubject.java Fonts/Program.java Fonts/Restriction.java Fonts/Session.java Fonts/Subject.java
	javac ./Fonts/Program.java ./Fonts/AcademicPlan.java ./Fonts/Building.java ./Fonts/Classroom.java ./Fonts/Degree.java ./Fonts/EducationalInstitution.java ./Fonts/Equipment.java ./Fonts/FactoriaAcademica.java ./Fonts/FactoriaBuilding.java ./Fonts/FactoriaRestriction.java ./Fonts/Group.java ./Fonts/GroupSubject.java ./Fonts/Restriction.java ./Fonts/Session.java ./Fonts/Subject.java

compress:
	(cd ./Fonts; jar -cfe ../Scheduler.jar Program *.class)

clean:
	rm ./Fonts/*.class
