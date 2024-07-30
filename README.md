# Esame del corso di ingegneria del software 2023/2024

L'esame consisteva nel realizzare un programma in grado di gestire la suddivisione delle
spese tra un gruppo di persone (simil tricount semplificato). 


Avevamo a disposizione le viste e bisognava realizzare sul momento il progetto, seguendo
una metodologia di sviluppo test driven development e con particolare attenzione al design pattern Model-View-Presenter.
Il programma andava quindi testato il più completamente possibile, per farlo ho utilizzato 
negli unit test anche dei mock objects (framework Mockito).


Le spese riguardano tutto il gruppo e tutti dovranno alla fine contribuire in maniera uguale.
Ogni pagamento viene effettuato inizialmente da una sola persona che sarà quindi in credito mentre gli altri componenti del gruppo sono in debito.
Per ogni persona abbiamo quindi la quantità di credito/debito accumulato.