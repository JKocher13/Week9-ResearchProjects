import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import com.redis._
import Array._
import scala.collection.mutable.ListBuffer

object Main extends App {
	val r = new RedisClient("localhost", 6379)
	var count = 0
	val nrows: Int = 991
	val ncols: Int = 17
	val rows = Array.ofDim[String](nrows, ncols)
	val bufferedSource = Source.fromFile("mr-boston-flattened.csv")
	for (line <- bufferedSource.getLines) {
		rows(count) = line.split(",").map(_.trim)
		count += 1 }

	for (i <- 0 until nrows) {
		r.sadd(rows(i)(0), rows(i)(3), rows(i)(5), rows(i)(7), rows(i)(9), rows(i)(11), rows(i)(13))
	}


	for (i <- 0 until nrows) {
		r.sadd((rows(i)(0)+ "_instructions"), (rows(i)(3)+" "+rows(i)(2)), (rows(i)(5)+" "+rows(i)(4)), (rows(i)(7)+" "+rows(i)(6)), (rows(i)(9)+" "+rows(i)(8)), (rows(i)(11)+" "+rows(i)(10))+((rows(i)(13)+" "+rows(i)(12))),rows(i)(14))}

	r.sadd("bar", "","","","","","","Soda water (optional)", "Orange blossoms (optional): orange wedge","Tiki (such as Bittermen's 'Elemakule) or Angostura bitters (optional) Garnish: Fresh mint sprig","Lemon twist (optional)")
	println("Do you have Gin? Y/N \n")
	var gin = ""
	gin = scala.io.StdIn.readLine()
	if (gin == "Y")r.sadd("bar","Gin", "Old Mr. Boston Dry Gin",  "Sloe gin", "Tanqueray gin")

	val lemonJuice = scala.io.StdIn.readLine("Do you have lemon juice? Y/N \n")
	if (lemonJuice == "Y")r.sadd("bar", "Fresh lemon juice", "Lemon Juice", "Juice of a Lemon")
	val lightRum = scala.io.StdIn.readLine("Do you have Light Rum? Y/N \n")
	if (lightRum == "Y"){
		r.sadd("bar","Light Rum")
	}
	val vodka = scala.io.StdIn.readLine("Do you have Vodka? Y/N \n")
	if (vodka == "Y"){
		r.sadd("bar","Vodka")
	}
	val dryVermouth = scala.io.StdIn.readLine("Do you have Dry Vermouth? Y/N \n")
	if (dryVermouth == "Y")r.sadd("bar","Dry Vermouth")
	val simple = scala.io.StdIn.readLine("Do you have Simple Syrup (Sugar and Water)? Y/N \n")
	if (simple == "Y"){
		r.sadd("bar","Simple Syrup")
	}
	val tripleSec = scala.io.StdIn.readLine("Do you have Triple Sec)? Y/N \n")
	if (tripleSec == "Y"){
		r.sadd("bar","Triple Sec")
	}
	val powderedSugar  = scala.io.StdIn.readLine("Do you have powdered sugar)? Y/N \n")
	if (powderedSugar == "Y"){
		r.sadd("bar","Powdered Sugar")
	}
	val grenadine  = scala.io.StdIn.readLine("Do you have Grenadine )? Y/N \n")
	if (grenadine == "Y"){
		r.sadd("bar","Grenadine")
	}
	val sweetVermouth  = scala.io.StdIn.readLine("Do you have Sweet Vermouth? Y/N \n")
	if (sweetVermouth == "Y"){
		r.sadd("bar","Sweet Vermouth")
	}
	val brandy  = scala.io.StdIn.readLine("Do you have Brandy ? Y/N \n")
	if (brandy == "Y"){
		r.sadd("bar","Brandy")
	}
	val eggWhite  = scala.io.StdIn.readLine("Do you have Egg White)? Y/N \n")
	if (eggWhite == "Y"){
		r.sadd("bar","Egg White")
	}
	val bitters  = scala.io.StdIn.readLine("Do you have Bittters? Y/N \n")
	if (bitters == "Y"){
		r.sadd("bar","Angostura Bitters", "Bitters", "Orange Bitters")
	}
	val tequila  = scala.io.StdIn.readLine("Do you have tequila? Y/N \n")
	if (tequila == "Y"){
		r.sadd("bar","Tequila", "Blanco tequila",  "Reposado tequila", "Anejo tequila")
	}
	val pineappleJuice  = scala.io.StdIn.readLine("Do you have Pineapple Juice? Y/N \n")
	if (pineappleJuice == "Y"){
		r.sadd("bar","Pineapple Juice",  "Pineapple or grapefruit juice", "Lime soda, pineapple wedge, maraschino cherry or lime wheel")
	}
	val whiskey = scala.io.StdIn.readLine("Do you have Bourbon? Y/N \n")
	if (whiskey == "Y"){
		r.sadd("bar","Bourbon whiskey")
	}
	val orangeJuice = scala.io.StdIn.readLine("Do you have Orange Juice? Y/N \n")
	if (whiskey == "Y"){
		r.sadd("bar","Orange Juice")
	}
	val mint = scala.io.StdIn.readLine("Do you have mint? Y/N \n")
	if (mint == "Y"){
		r.sadd("bar","Fresh mint leaves", "4 Fresh mint leaves")
	}
	val drinks = ListBuffer[String]()
	for (i <- 0 until nrows) {
		if (r.sinter("bar" , rows(i)(0)) == r.smembers(rows(i)(0)))
		{
			drinks += rows(i)(0)}
		}
	val drinksList = drinks.toList
	
	for (x <- drinksList){
		println(x)
	}
	r.del("bar")

	}







