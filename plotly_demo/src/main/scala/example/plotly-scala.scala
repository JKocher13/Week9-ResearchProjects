import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import Array._
import scala.collection.mutable.ListBuffer
import co.theasi.plotly._

object Main extends App {
	var count = 0
	val nrows: Int = 404
	val ncols: Int = 6
	val rows = Array.ofDim[String](nrows, ncols)
	val bufferedSource = Source.fromFile("CV_LatLon_21Jan_12Mar.csv")
	for (line <- bufferedSource.getLines) {
		rows(count) = line.split(",").map(_.trim)
		count += 1 }
	var lat = new ListBuffer[String]()
	for(i <- 1 until nrows){
		lat += rows(i)(0)
	}
	var lon = new ListBuffer[String]()
	for(i <- 1 until nrows){
		lon += rows(i)(1)
	}
	var conf = new ListBuffer[String]()
	for(i <- 1 until nrows){
		conf += rows(i)(3)
	}
	var recov = new ListBuffer[String]()
	for(i <- 1 until nrows){
		recov += rows(i)(4)
	}
	var death = new ListBuffer[String]()
	for(i <- 1 until nrows){
		death += rows(i)(5)
	}
	lat.toVector
	lon.toVector
	conf.toVector
	recov.toVector
	death.toVector

	val xaxisOptions = AxisOptions().title("deaths")
	val yaxisOptions = AxisOptions().title("recoveries")



	val p = Plot()
	  .xAxisOptions(xaxisOptions)
	  .yAxisOptions(yaxisOptions)
	  .withScatter(death, recov, ScatterOptions()
	    .mode(ScatterMode.Marker)
	    .name("Above")
	    .marker(
	      MarkerOptions()
	        .size(10)
	        .color(152, 0, 0, 0.8)
	        .lineWidth(2)
	        .lineColor(0, 0, 0)))

	  draw(p, "death v recov", writer.FileOptions(overwrite=true))

	val xaxisOptions_lat = AxisOptions().title("Latitude")
	val xaxisOptions_lon = AxisOptions().title("Longitude")
	val yaxisOptions_conf =AxisOptions().title("Confirmed Cases")

	val leftPlot = Plot()
  		.withScatter(lat, conf, ScatterOptions().mode(ScatterMode.Marker).name("Latitude"))
  		.xAxisOptions(xaxisOptions_lat)
		.yAxisOptions(yaxisOptions_conf)

  	val rightPlot = Plot()
  		.withScatter(lon, conf, ScatterOptions().mode(ScatterMode.Marker).name("Longitude"))
  		.xAxisOptions(xaxisOptions_lon)
		.yAxisOptions(yaxisOptions_conf)

  	val figure = RowFigure(2)
  		.plot(0) { leftPlot }
  		.plot(1) { rightPlot }
  	draw(figure, "lat vs long with new cases", writer.FileOptions(overwrite=true))

}  
