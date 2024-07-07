# Proiect la disciplina "Programare avansata pe obiecte" - an II ID

- Fiecare student va elabora individual o aplicație de tip consolă (cine știe/dorește poate să creeze și 
o aplicație cu interfață grafică sau o aplicație Web, dar nu se va puncta suplimentar).  


- Condiții obligatorii de evaluare a proiectelor:  
    - să nu prezinte erori de compilare  
    - să respecte cerințele precizate mai jos  

- Arhitectura aplicației:   
    - să fie definite cel puțin 6 acțiuni pe care să efectueze aplicația 
    - să fie utilizate cel puțin 6 clase  

- Implementarea aplicației în Java: 
    - clasele vor avea date membre private / protected și metode publice de acces  
    - cel puțin într-o clasă se vor rescrie metode din clasa Object 
    - se vor utiliza cel puțin două colecții diferite capabile să gestioneze obiectele definite anterior, iar cel puțin una trebuie să fie sortată  
    - se va utiliza moștenirea pentru crearea de clase specializate 
    - cel puțin într-o clasă se va utiliza agregarea sau compoziția  
    - se va implementa facilitatea de autentificare în aplicație 
    - se va implementa cel puțin o clasă care sa expună acțiunile care pot fi efectuate de aplicație  
    - se va implementa o clasă care să permită rularea aplicației prin intermediul unui meniu de tip text 
    - se va asigura persistența datelor utilizând o bază de date relațională și JDBC 
    - să vor realiza operații de tip CRUD (Create, Read, Update și Delete) pentru cel puțin una dintre 
    -lasele utilizate 
    - se va implementa facilitatea de înregistrare într-un fișier de tip CSV a fiecărei executări a uneia 
dintre acțiunile aplicației, sub forma denumire_acțiune, data_și_ora  

- Tema 
    - Platforma de e-learning (Curs, Utilizator, Cursant, Temă, Test de verificare)  


## Design solutie

6 actiuni
- Autentificare utilizator
- Crearea unui curs
- Inscrierea unui cursant la un curs
- Creare unei teme pentru un curs
- Evaluare unui test de verificare
- Afisarea cursurilor disponibile

Clase necesare
- User
- Student
- Course
- Assignment
- Test
- ELearningPlatform (Clasa principala pentru rularea aplicatiei)
