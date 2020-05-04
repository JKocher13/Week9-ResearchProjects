import scala.io.StdIn._

object Main extends App {

	var evenList = 
		for {i <- 1 to 20
			if (i % 2) == 0
		} yield i

	for (i <- evenList){
		println(i)
	}

	var numberGuess = 0
	var favNum = 0

	do{
		printf("Guess an even Number \n")
		numberGuess = readLine.toInt
	} while(numberGuess != 12)

	printf("You guessed the secret number!")

	printf("What is your favorite number Number? \n")
	favNum = readLine.toInt

	def addToIt(num1 : Int, num2 : Int) : Int = {
		return num1 + num2
	}

	var added = addToIt(favNum, numberGuess)

	printf("Your favorite number added to the secrect number is " + added +"\n")



}  
