Wichtige Informationen:

    - Es wird immer mit Leerzeichen gezählt.
    - Alle Zahlen müssen Ganzzahlen sein.

Deterministischer endlicher Automat:

    Statischer Automat:

        Der Statische Automat hat die Sprache: L(DEA)=w besteht aus einer geraden Anzahl von a's und b's

    Variable Automat:

        Eingabe um einen Deterministischer endlicher Automat, der eine grade Anzahl von a's und b's hat.

        TODO...

Keller Automat:

    Statischer Automat:
    
        Der Statische Automat hat die Sprache: L(KA)={e^m d c^n b^n a^m} | n >= 1, m >= 0
    
    Variabler Automat:
    
        Eingabe um einen Keller Automaten zu erstellen, der gleich viele a's und b's hat.
        
        Zustände: 2
        Start: 0
        Ende: 1
        
        0 a k0 0 ADD
        0 b k0 0 ADD
        0 a a 0 ADD
        0 a b 0 DELETE
        0 b b 0 ADD
        0 b a 0 DELETE
        b 0 X k0 1 NOTHING