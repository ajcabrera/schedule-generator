3 Equipment: Elevator, Ordenadores, Osciloscopios

Buildings:
a5 -> Elevator
b5 -> Elevator
c6 -> No equipment


Classrooms:
(nombre -> plazas, piso, building, equipments)
a5101 -> 30, 1, a5, No
a5e01 -> 70, 0, a5, No
a5102 -> 50, 1, a5, No
a5203 -> 60, 2, a5, Osciloscopios
a5103 -> 50, 1, a5, Ordenadores
a5104 -> 55, 1, a5, No
c6001 -> 50, 0, c6, Ordenadores
c6002 -> 40, 0, c6, No
c6101 -> 40, 1, c6, No
b5201 -> 45, 2, b5, No
b5202 -> 50, 2, b5, Osciloscopios
b5203 -> 30, 2, b5, Ordenadores & Osciloscopios
a5e02 -> 100, 0, a5, No

Degree: 
1) Informatica tipo Grau

AcademicPlan:
1) 2018

Assignaturas:
(nombre -> plazas, (tipo de classe, tamaño clase, Longitud session, Numero sessiones por semana)
Pro1 -> 300, (Teoria, 50, 2, 1), (Lab, 25, 1, 1)
Fisica -> 200, (Teoria, 40, 2, 1), (Lab, 25, 2, 1)
FM -> 200, (Teoria, 35, 2, 1), (Prob, 20, 1, 1)
IC -> 150, (Teoria, 60, 2, 1), (Lab, 35, 1, 2)
Pro2 -> 150, (Teoria, 50, 2, 1), (Prob 30, 1, 1)
EC -> 150, (Teoria, 50, 1, 1), (Lab, 25, 2, 1)
PE -> 120, (Teoria, 40, 2, 1)
EDA -> 100, (Teoria, 30, 2, 1), (Lab, 25, 2, 1)
AC -> 150, (Teoria, 80, 1, 1), (Lab, 40, 2, 1)
BD -> 100, (Teoria, 40, 1, 1), (Prob, 40,2, 1)
TGA -> 50, (Lab, 30, 2, 1)
ML -> 80, (Teoria, 40, 1, 1), (Lab 20, 2, 1)
CS -> 50, (Lab, 25, 2, 1)
LP -> 50, (Lab, 25, 2, 2)
EEE -> 70, (Teoria, 50, 2, 2)

Level & Corequisit
(tipo, Alias, info)
Level, Nivel1, Pro1, Fisica, IC, FM
Level, Nivel2, Pro2, PE, EEE, AC, BD
Corequisit,Cor1, EEE & PE

Restriciones:
(tipo, Alias, info)
1, HorasExamenes, Monday, 12h-15h All
1, FisicaNotFridayAfternoon, Friday, 14h-20h Fisica
2, FisicaLabsNeedsOsciloscopios, Fisica Lab Osciloscopios
2, Pro1LabNeedsOrdenadores, Pro1 Lab Ordenadores
2, Pro2LabNeedsOrdenadores, Pro2 Prob Ordenadores
3, EEEMorning, Morning EEE
3, AssigBalanced, Balanced FM EDA EC
4, AccesibilityNivel1, Pro1, Fisica, IC, FM
5, ClassroomA5103Unavailable, Cl a5 a5103
6, ImposeCla5101FMProb, Cl a5 a5101 FM Prob
7, FMTeoriaBeforeProb, ML Teoria Lab
8, EDANotTuesday, EDA Tuesday
10, LPLabFriday12ha5102, LP Lab Group10 Friday 12h a5102
11, LabTGAOnWednesday, TGA Lab Wednesday
