Instrukcja uruchomienia:
Należy zmienić rozszerzenie pliku "botrouteplanner.jarREMOVE" na "botrouteplanner.jar", a następnie uruchomić go poleceniem:

java -jar botrouteplanner.jar <grid file name> <job file name>

Opis programu:

Program przyjmuje opis magazynu i poszukiwanego produktu. W odpowiedzi program zwróci najkrótszą ścieżkę dla bota do poszukiwanego produktu a następnie do stacji odbiorczej. Dodatkowo
program wypisze również całkowitą długość ścieżki oraz całkowity koszt czasowy dostarczenia produktu.

Program został napisany w języku Java z wykorzystaniem narzędzia Maven. Całkowita złożoność programu wynosi O(E log V) gdzie E to liczba modułów magazynu a V to liczba możliwych połączeń
między magazynami. Przy wykonaniu zadania został wykorzystany algorytm Dijkstry. 

Opis klas:

Container: klasa symulująca kontener w magazynie;
	pola:
		Module in - moduł w którym znajduje się dany kontener
		int depth - głębokość na jakiej znajduje się dany kontener
		double cost - koszt dotarcia i wyciągnięcia danego kontenera

Helper: klasa zawierająca pomocnicze funkcje
	pola:
		static int road - długość optymalnej ścieżki
		static double cost - koszt optymalnej ścieżki
	metody:
		string fileToString(File) - konwertuje zawartość pliku tekstowego do typu String
		double calculateCost(Module, Module) - oblicza koszt przejść między dwoma modułami
		static String produceAnswer(Container, boolean) - wyznacza ścieżkę od kontenera końcowego do początku ścieżki, od wartości boolean zależy czy jest to ścieżka do produktu czy stacji
		String prepareAnswer(String) - przenosi wartości koszt i długość ścieżki na początek odpowiedzi

Main: klasa przyjmująca dane i wywołująca funkcję generującą odpowiedź

Module: klasa symulująca moduł w magazynie
	pola: 
		int x,y - wspołrzędne modułu
		double cost - koszt dotarcia do danego modułu
		int visited - 0 nieodwiedzony, 1 w kolejce, 2 przetworzony
		char type - typ modułu należący do zbioru {O, B, S, H}
		Module last - moduł z którego prowadzi najkrótsza ścieżka

PathFinder: klasa z pojedynczą metodą implementującą algorytm Dijkstry i wyznaczającą najtańszą ścieżkę
	metody: 
		findPath(lista argumentów przekazanych z klasy RoutePlanner) realizująca odpowiedzialność klasy
		

RoutePlanner: klasa sprawdzająca i przetwarzająca dane do odpowiedniej formy i przekazująca je do klasy obliczającej ścieżkę
	pola:
		char[][] grid - tablica typów modułów
		String[][][] products - tablica produktów trzymanych w postaci String
		int width, height, depth - długość, szerokość i głębokość magazynu
		int startx, starty - koordynaty początkowe bota
		int stationx, stationy - koordynaty stacji
		String productToFind - produkt który należy wyszukać
	metody:
		findRoute(String) - przetwarza dane zapytania o produkt i przekazuje do klasy wyznaczającej ścieżkę 
	
